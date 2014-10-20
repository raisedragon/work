/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.winit.svr.impl.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winit.svr.LabelException;
import com.winit.svr.ActivitiOptimisticLockingException;
import com.winit.svr.ActivitiWrongDbException;
import com.winit.svr.LabelServer;
import com.winit.svr.LabelServerConfiguration;
import com.winit.svr.delegate.event.ActivitiEventType;
import com.winit.svr.delegate.event.ActivitiVariableEvent;
import com.winit.svr.delegate.event.impl.ActivitiEventBuilder;
import com.winit.svr.impl.GroupQueryImpl;
import com.winit.svr.impl.Page;
import com.winit.svr.impl.UserQueryImpl;
import com.winit.svr.impl.cfg.LabelServerConfigurationImpl;
import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.db.upgrade.DbUpgradeStep;
import com.winit.svr.impl.interceptor.Session;
import com.winit.svr.impl.persistence.entity.PropertyEntity;
import com.winit.svr.impl.persistence.entity.VariableInstanceEntity;
import com.winit.svr.impl.util.IoUtil;
import com.winit.svr.impl.util.ReflectUtil;
import com.winit.svr.impl.variable.DeserializedObject;


/** responsibilities:
 *   - delayed flushing of inserts updates and deletes
 *   - optional dirty checking
 *   - db specific statement name mapping
 *   
 * @author Tom Baeyens
 * @author Joram Barrez
 */
public class DbSqlSession implements Session {
  
  private static final Logger log = LoggerFactory.getLogger(DbSqlSession.class);
  
  private static final Pattern CLEAN_VERSION_REGEX = Pattern.compile("\\d\\.\\d*");
  
  private static final List<ActivitiVersion> ACTIVITI_VERSIONS = new ArrayList<ActivitiVersion>();
  static {
	  
	  /* Previous */
	  
	  ACTIVITI_VERSIONS.add(new ActivitiVersion("5.7"));
	  ACTIVITI_VERSIONS.add(new ActivitiVersion("5.8"));
	  ACTIVITI_VERSIONS.add(new ActivitiVersion("5.9"));
	  ACTIVITI_VERSIONS.add(new ActivitiVersion("5.10"));
	  ACTIVITI_VERSIONS.add(new ActivitiVersion("5.11"));
	  
	  // 5.12.1 was a bugfix release on 5.12 and did NOT change the version in ACT_GE_PROPERTY
	  // On top of that, DB2 create script for 5.12.1 was shipped with a 'T' suffix ...
	  ACTIVITI_VERSIONS.add(new ActivitiVersion("5.12", Arrays.asList("5.12.1", "5.12T")));
	  
	  ACTIVITI_VERSIONS.add(new ActivitiVersion("5.13"));
	  ACTIVITI_VERSIONS.add(new ActivitiVersion("5.14"));
	  ACTIVITI_VERSIONS.add(new ActivitiVersion("5.15"));
	  ACTIVITI_VERSIONS.add(new ActivitiVersion("5.15.1"));
	  ACTIVITI_VERSIONS.add(new ActivitiVersion("5.16"));
	  
	  /* Current */
	  
	  ACTIVITI_VERSIONS.add(new ActivitiVersion(LabelServer.VERSION));
  }

  protected SqlSession sqlSession;
  protected DbSqlSessionFactory dbSqlSessionFactory;
  protected List<PersistentObject> insertedObjects = new ArrayList<PersistentObject>();
  protected Map<Class<?>, Map<String, CachedObject>> cachedObjects = new HashMap<Class<?>, Map<String,CachedObject>>();
  protected List<DeleteOperation> deleteOperations = new ArrayList<DeleteOperation>();
  protected List<DeserializedObject> deserializedObjects = new ArrayList<DeserializedObject>();
  protected String connectionMetadataDefaultCatalog;
  protected String connectionMetadataDefaultSchema;

  public DbSqlSession(DbSqlSessionFactory dbSqlSessionFactory) {
    this.dbSqlSessionFactory = dbSqlSessionFactory;
    this.sqlSession = dbSqlSessionFactory
      .getSqlSessionFactory()
      .openSession();
  }

  public DbSqlSession(DbSqlSessionFactory dbSqlSessionFactory, Connection connection, String catalog, String schema) {
    this.dbSqlSessionFactory = dbSqlSessionFactory;
    this.sqlSession = dbSqlSessionFactory
      .getSqlSessionFactory()
      .openSession(connection);
    this.connectionMetadataDefaultCatalog = catalog;
    this.connectionMetadataDefaultSchema = schema;
  }
  
  // Touch  ///////////////////////////////////////////////////////////////////
  // brings the given persistenObject to the top if it already exists
  public void touch(PersistentObject persistentObject) {
	  if (persistentObject.getId()==null) {
		  throw new LabelException("Cannot touch " + persistentObject.getClass() + " with no id");
	  }
	  if (insertedObjects.contains(persistentObject)) {
		  insertedObjects.remove(persistentObject);
		  insertedObjects.add(persistentObject);
		  cachePut(persistentObject, false);
	  } 
   }
	  
  // insert ///////////////////////////////////////////////////////////////////
  
  
  public void insert(PersistentObject persistentObject) {
    if (persistentObject.getId()==null) {
      String id = dbSqlSessionFactory.getIdGenerator().getNextId();  
      persistentObject.setId(id);
    }
    insertedObjects.add(persistentObject);
    cachePut(persistentObject, false);
  }
  
  // update ///////////////////////////////////////////////////////////////////
  
  public void update(PersistentObject persistentObject) {
    cachePut(persistentObject, false);
  }
  
  public void update(String statement, Object parameters) {
     String updateStatement = dbSqlSessionFactory.mapStatement(statement);
     getSqlSession().update(updateStatement, parameters);
  }
  
  // delete ///////////////////////////////////////////////////////////////////

  public void delete(String statement, Object parameter) {
    deleteOperations.add(new BulkDeleteOperation(statement, parameter));
  }
  
  public void delete(PersistentObject persistentObject) {
    for (DeleteOperation deleteOperation: deleteOperations) {
        if (deleteOperation.sameIdentity(persistentObject)) {
          log.debug("skipping redundant delete: {}", persistentObject);
          return; // Skip this delete. It was already added.
        }
    }
    
    deleteOperations.add(new CheckedDeleteOperation(persistentObject));
  }

  public interface DeleteOperation {
    
    boolean sameIdentity(PersistentObject other);

    void clearCache();
    
    void execute();
    
  }

  /**
   * Use this {@link DeleteOperation} to execute a dedicated delete statement.
   * It is important to note there won't be any optimistic locking checks done 
   * for these kind of delete operations!
   * 
   * For example, a usage of this operation would be to delete all variables for
   * a certain execution, when that certain execution is removed. The optimistic locking
   * happens on the execution, but the variables can be removed by a simple
   * 'delete from var_table where execution_id is xxx'. It could very well be there
   * are no variables, which would also work with this query, but not with the 
   * regular {@link CheckedDeleteOperation}. 
   */
  public class BulkDeleteOperation implements DeleteOperation {
    private String statement;
    private Object parameter;
    
    public BulkDeleteOperation(String statement, Object parameter) {
      this.statement = dbSqlSessionFactory.mapStatement(statement);
      this.parameter = parameter;
    }
    
    @Override
    public boolean sameIdentity(PersistentObject other) {
      // this implementation is unable to determine what the identity of the removed object(s) will be.
      return false;
    }

    @Override
    public void clearCache() {
      // this implementation cannot clear the object(s) to be removed from the cache.
    }
    
    @Override
    public void execute() {
      sqlSession.delete(statement, parameter);
    }
    
    @Override
    public String toString() {
      return "bulk delete: " + statement + "(" + parameter + ")";
    }
  }
  
  /**
   * A {@link DeleteOperation} that checks for concurrent modifications if the persistent object implements {@link HasRevision}.
   * That is, it employs optimisting concurrency control. Used when the persistent object has been fetched already.
   */
  public class CheckedDeleteOperation implements DeleteOperation {
    protected final PersistentObject persistentObject;
    
    public CheckedDeleteOperation(PersistentObject persistentObject) {
      this.persistentObject = persistentObject;
    }
    
    @Override
    public boolean sameIdentity(PersistentObject other) {
      return persistentObject.getClass().equals(other.getClass())
          && persistentObject.getId().equals(other.getId());
    }

    @Override
    public void clearCache() {
      cacheRemove(persistentObject.getClass(), persistentObject.getId());
    }
    
    public void execute() {
      String deleteStatement = dbSqlSessionFactory.getDeleteStatement(persistentObject.getClass());
      deleteStatement = dbSqlSessionFactory.mapStatement(deleteStatement);
      if (deleteStatement == null) {
        throw new LabelException("no delete statement for " + persistentObject.getClass() + " in the ibatis mapping files");
      }
      
      // It only makes sense to check for optimistic locking exceptions for objects that actually have a revision
      if (persistentObject instanceof HasRevision) {
        int nrOfRowsDeleted = sqlSession.delete(deleteStatement, persistentObject);
        if (nrOfRowsDeleted == 0) {
          throw new ActivitiOptimisticLockingException(persistentObject + " was updated by another transaction concurrently");
        }
      } else {
        sqlSession.delete(deleteStatement, persistentObject);
      }
    }

    public PersistentObject getPersistentObject() {
      return persistentObject;
    }

    @Override
    public String toString() {
      return "delete " + persistentObject;
    }
  }
  
  
  /**
   * A bulk version of the {@link CheckedDeleteOperation}.
   */
  public class BulkCheckedDeleteOperation implements DeleteOperation {
  	
  	protected Class<? extends PersistentObject> persistentObjectClass;
    protected List<PersistentObject> persistentObjects = new ArrayList<PersistentObject>();
    
    public BulkCheckedDeleteOperation(Class<? extends PersistentObject> persistentObjectClass) {
    	this.persistentObjectClass = persistentObjectClass;
    }
    
    public void addPersistentObject(PersistentObject persistentObject) {
    	persistentObjects.add(persistentObject);
    }
    
    @Override
    public boolean sameIdentity(PersistentObject other) {
    	for (PersistentObject persistentObject : persistentObjects) {
    		if (persistentObject.getClass().equals(other.getClass()) && persistentObject.getId().equals(other.getId())) {
    			return true;
    		}
    	}
    	return false;
    }

    @Override
    public void clearCache() {
    	for (PersistentObject persistentObject : persistentObjects) {
    		cacheRemove(persistentObject.getClass(), persistentObject.getId());
    	}
    }
    
    public void execute() {
    	
    	if (persistentObjects.size() == 0) {
    		return;
    	}
    	
      String bulkDeleteStatement = dbSqlSessionFactory.getBulkDeleteStatement(persistentObjectClass);
      bulkDeleteStatement = dbSqlSessionFactory.mapStatement(bulkDeleteStatement);
      if (bulkDeleteStatement == null) {
        throw new LabelException("no bulk delete statement for " + persistentObjectClass + " in the mapping files");
      }
      
      // It only makes sense to check for optimistic locking exceptions for objects that actually have a revision
      if (persistentObjects.get(0) instanceof HasRevision) {
        int nrOfRowsDeleted = sqlSession.delete(bulkDeleteStatement, persistentObjects);
        if (nrOfRowsDeleted < persistentObjects.size()) {
          throw new ActivitiOptimisticLockingException("One of the entities " + persistentObjectClass 
          		+ " was updated by another transaction concurrently while trying to do a bulk delete");
        }
      } else {
        sqlSession.delete(bulkDeleteStatement, persistentObjects);
      }
    }
    
    public Class<? extends PersistentObject> getPersistentObjectClass() {
			return persistentObjectClass;
		}

		public void setPersistentObjectClass(
		    Class<? extends PersistentObject> persistentObjectClass) {
			this.persistentObjectClass = persistentObjectClass;
		}

		public List<PersistentObject> getPersistentObjects() {
			return persistentObjects;
		}

		public void setPersistentObjects(List<PersistentObject> persistentObjects) {
			this.persistentObjects = persistentObjects;
		}

		@Override
    public String toString() {
      return "bulk delete of " + persistentObjects.size() + (persistentObjects.size() > 0 ? " entities of " + persistentObjects.get(0).getClass() : 0 );
    }
  }
  
  // select ///////////////////////////////////////////////////////////////////

  @SuppressWarnings({ "rawtypes" })
  public List selectList(String statement) {
    return selectList(statement, null, 0, Integer.MAX_VALUE);
  }
  
  @SuppressWarnings("rawtypes")
  public List selectList(String statement, Object parameter) {  
    return selectList(statement, parameter, 0, Integer.MAX_VALUE);
  }
  
  @SuppressWarnings("rawtypes")
  public List selectList(String statement, Object parameter, Page page) {   
    if (page!=null) {
      return selectList(statement, parameter, page.getFirstResult(), page.getMaxResults());
    } else {
      return selectList(statement, parameter, 0, Integer.MAX_VALUE);
    }
  }
  
  @SuppressWarnings("rawtypes")
  public List selectList(String statement, ListQueryParameterObject parameter, Page page) {   
    return selectList(statement, parameter);
  }

  @SuppressWarnings("rawtypes")
  public List selectList(String statement, Object parameter, int firstResult, int maxResults) {   
    return selectList(statement, new ListQueryParameterObject(parameter, firstResult, maxResults));
  }
  
  @SuppressWarnings("rawtypes")
  public List selectList(String statement, ListQueryParameterObject parameter) {
    return selectListWithRawParameter(statement, parameter, parameter.getFirstResult(), parameter.getMaxResults());
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public List selectListWithRawParameter(String statement, Object parameter, int firstResult, int maxResults) {
    statement = dbSqlSessionFactory.mapStatement(statement);    
    if (firstResult == -1 ||  maxResults == -1) {
      return Collections.EMPTY_LIST;
    }    
    List loadedObjects = sqlSession.selectList(statement, parameter);
    return filterLoadedObjects(loadedObjects);
  }
  
  @SuppressWarnings({ "rawtypes" })
  public List selectListWithRawParameterWithoutFilter(String statement, Object parameter, int firstResult, int maxResults) {
    statement = dbSqlSessionFactory.mapStatement(statement);    
    if (firstResult == -1 ||  maxResults == -1) {
      return Collections.EMPTY_LIST;
    }    
    return sqlSession.selectList(statement, parameter);
  }

  public Object selectOne(String statement, Object parameter) {
    statement = dbSqlSessionFactory.mapStatement(statement);
    Object result = sqlSession.selectOne(statement, parameter);
    if (result instanceof PersistentObject) {
      PersistentObject loadedObject = (PersistentObject) result;
      result = cacheFilter(loadedObject);
    }
    return result;
  }
  
  @SuppressWarnings("unchecked")
  public <T extends PersistentObject> T selectById(Class<T> entityClass, String id) {
    T persistentObject = cacheGet(entityClass, id);
    if (persistentObject!=null) {
      return persistentObject;
    }
    String selectStatement = dbSqlSessionFactory.getSelectStatement(entityClass);
    selectStatement = dbSqlSessionFactory.mapStatement(selectStatement);
    persistentObject = (T) sqlSession.selectOne(selectStatement, id);
    if (persistentObject==null) {
      return null;
    }
    cachePut(persistentObject, true);
    return persistentObject;
  }

  // internal session cache ///////////////////////////////////////////////////
  
  @SuppressWarnings("rawtypes")
  protected List filterLoadedObjects(List<Object> loadedObjects) {
    if (loadedObjects.isEmpty()) {
      return loadedObjects;
    }
    if (!(loadedObjects.get(0) instanceof PersistentObject)) {
      return loadedObjects;
    }
    
    List<PersistentObject> filteredObjects = new ArrayList<PersistentObject>(loadedObjects.size());
    for (Object loadedObject: loadedObjects) {
      PersistentObject cachedPersistentObject = cacheFilter((PersistentObject) loadedObject);
      filteredObjects.add(cachedPersistentObject);
    }
    return filteredObjects;
  }

  protected CachedObject cachePut(PersistentObject persistentObject, boolean storeState) {
    Map<String, CachedObject> classCache = cachedObjects.get(persistentObject.getClass());
    if (classCache==null) {
      classCache = new HashMap<String, CachedObject>();
      cachedObjects.put(persistentObject.getClass(), classCache);
    }
    CachedObject cachedObject = new CachedObject(persistentObject, storeState);
    classCache.put(persistentObject.getId(), cachedObject);
    return cachedObject;
  }
  
  /** returns the object in the cache.  if this object was loaded before, 
   * then the original object is returned.  if this is the first time 
   * this object is loaded, then the loadedObject is added to the cache. */
  protected PersistentObject cacheFilter(PersistentObject persistentObject) {
    PersistentObject cachedPersistentObject = cacheGet(persistentObject.getClass(), persistentObject.getId());
    if (cachedPersistentObject!=null) {
      return cachedPersistentObject;
    }
    cachePut(persistentObject, true);
    return persistentObject;
  }

  @SuppressWarnings("unchecked")
  protected <T> T cacheGet(Class<T> entityClass, String id) {
    CachedObject cachedObject = null;
    Map<String, CachedObject> classCache = cachedObjects.get(entityClass);
    if (classCache!=null) {
      cachedObject = classCache.get(id);
    }
    if (cachedObject!=null) {
      return (T) cachedObject.getPersistentObject();
    }
    return null;
  }
  
  protected void cacheRemove(Class<?> persistentObjectClass, String persistentObjectId) {
    Map<String, CachedObject> classCache = cachedObjects.get(persistentObjectClass);
    if (classCache==null) {
      return;
    }
    classCache.remove(persistentObjectId);
  }
  
  @SuppressWarnings("unchecked")
  public <T> List<T> findInCache(Class<T> entityClass) {
    Map<String, CachedObject> classCache = cachedObjects.get(entityClass);
    if (classCache!=null) {
      List<T> entities = new ArrayList<T>(classCache.size());
      for (CachedObject cachedObject: classCache.values()) {
        entities.add((T) cachedObject.getPersistentObject());
      }
      return entities;
    }
    return Collections.emptyList();
  }
  
  public <T> T findInCache(Class<T> entityClass, String id) {
    return cacheGet(entityClass, id);
  }
  
  public static class CachedObject {
    protected PersistentObject persistentObject;
    protected Object persistentObjectState;
    
    public CachedObject(PersistentObject persistentObject, boolean storeState) {
      this.persistentObject = persistentObject;
      if (storeState) {
        this.persistentObjectState = persistentObject.getPersistentState();
      }
    }

    public PersistentObject getPersistentObject() {
      return persistentObject;
    }

    public Object getPersistentObjectState() {
      return persistentObjectState;
    }
  }

  // deserialized objects /////////////////////////////////////////////////////
  
  public void addDeserializedObject(Object deserializedObject, byte[] serializedBytes, VariableInstanceEntity variableInstanceEntity) {
    addDeserializedObject(new DeserializedObject(deserializedObject, serializedBytes, variableInstanceEntity));
  }
  
  public void addDeserializedObject(DeserializedObject deserializedObject) {
  	deserializedObjects.add(deserializedObject);
  }

  // flush ////////////////////////////////////////////////////////////////////

  public void flush() {
    List<DeleteOperation> removedOperations = removeUnnecessaryOperations();
    
    flushDeserializedObjects();
    List<PersistentObject> updatedObjects = getUpdatedObjects();
    
    if (log.isDebugEnabled()) {
      log.debug("flush summary: {} insert, {} update, {} delete.", insertedObjects.size(), updatedObjects.size(), deleteOperations.size());
      for (PersistentObject insertedObject: insertedObjects) {
        log.debug("  insert {}", insertedObject);
      }
      for (PersistentObject updatedObject: updatedObjects) {
        log.debug("  update {}", updatedObject);
      }
      for (DeleteOperation deleteOperation: deleteOperations) {
        log.debug("  {}", deleteOperation);
      }
      log.debug("now executing flush...");
    }

    flushInserts();
    flushUpdates(updatedObjects);
    flushDeletes(removedOperations);
  }

  /**
   * Clears all deleted and inserted objects from the cache, 
   * and removes inserts and deletes that cancel each other.
   */
  protected List<DeleteOperation> removeUnnecessaryOperations() {
    List<DeleteOperation> removedDeleteOperations = new ArrayList<DeleteOperation>();

    for (Iterator<DeleteOperation> deleteIt = deleteOperations.iterator(); deleteIt.hasNext();) {
      DeleteOperation deleteOperation = deleteIt.next();
      
      for (Iterator<PersistentObject> insertIt = insertedObjects.iterator(); insertIt.hasNext();) {
        PersistentObject insertedObject = insertIt.next();
        
        // if the deleted object is inserted,
        if (deleteOperation.sameIdentity(insertedObject)) {
          // remove the insert and the delete, they cancel each other
          insertIt.remove();
          deleteIt.remove();
          // add removed operations to be able to fire events
          removedDeleteOperations.add( deleteOperation);
        }
      }
      
      // in any case, remove the deleted object from the cache
      deleteOperation.clearCache();
    }
    
    for (PersistentObject insertedObject: insertedObjects) {
      cacheRemove(insertedObject.getClass(), insertedObject.getId());
    }

    return removedDeleteOperations;
  }
  
  /**
   * Optimizes the given delete operations:
   * for example, if there are two deletes for two different variables, merges this into
   * one bulk delete which improves performance
   */
  protected List<DeleteOperation> optimizeDeleteOperations(List<DeleteOperation> deleteOperations) {
  	
  	// No optimization possible for 0 or 1 operations
  	if (deleteOperations.size() <= 1) {
  		return deleteOperations;
  	}
  	
  	List<DeleteOperation> optimizedDeleteOperations = new ArrayList<DbSqlSession.DeleteOperation>();
  	boolean[] checkedIndices = new boolean[deleteOperations.size()];
  	for (int i=0; i<deleteOperations.size(); i++) {
  		
  		if (checkedIndices[i] == true) {
  			continue;
  		}
  		
  		DeleteOperation deleteOperation = deleteOperations.get(i);
  		boolean couldOptimize = false;
  		if (deleteOperation instanceof CheckedDeleteOperation) {
  			
  			PersistentObject persistentObject = ((CheckedDeleteOperation) deleteOperation).getPersistentObject();
  			if (persistentObject instanceof BulkDeleteable) {
				String bulkDeleteStatement = dbSqlSessionFactory.getBulkDeleteStatement(persistentObject.getClass());
				bulkDeleteStatement = dbSqlSessionFactory.mapStatement(bulkDeleteStatement);
				if (bulkDeleteStatement != null) {
					BulkCheckedDeleteOperation bulkCheckedDeleteOperation = null;
					
					// Find all objects of the same type
					for (int j=0; j<deleteOperations.size(); j++) {
						DeleteOperation otherDeleteOperation = deleteOperations.get(j);
						if (j != i && checkedIndices[j] == false && otherDeleteOperation instanceof CheckedDeleteOperation) {
							PersistentObject otherPersistentObject = ((CheckedDeleteOperation) otherDeleteOperation).getPersistentObject();
							if (otherPersistentObject.getClass().equals(persistentObject.getClass())) {
	  							if (bulkCheckedDeleteOperation == null) {
	  								bulkCheckedDeleteOperation = new BulkCheckedDeleteOperation(persistentObject.getClass());
	  								bulkCheckedDeleteOperation.addPersistentObject(persistentObject);
	  								optimizedDeleteOperations.add(bulkCheckedDeleteOperation);
	  							}
	  							couldOptimize = true;
	  							bulkCheckedDeleteOperation.addPersistentObject(otherPersistentObject);
	  							checkedIndices[j] = true;
							} else {
							    // We may only optimize subsequent delete operations of the same type, to prevent messing up 
							    // the order of deletes of related entities which may depend on the referenced entity being deleted before
							    break;
							}
						}
						
					}
				}
  			}
  		}
  		
   		if (!couldOptimize) {
  			optimizedDeleteOperations.add(deleteOperation);
  		}
  		checkedIndices[i]=true;
  		
  	}
  	return optimizedDeleteOperations;
  }

  protected void flushDeserializedObjects() {
    for (DeserializedObject deserializedObject: deserializedObjects) {
      deserializedObject.flush();
    }
  }

  public List<PersistentObject> getUpdatedObjects() {
    List<PersistentObject> updatedObjects = new ArrayList<PersistentObject>();
    for (Class<?> clazz: cachedObjects.keySet()) {
      
      Map<String, CachedObject> classCache = cachedObjects.get(clazz);
      for (CachedObject cachedObject: classCache.values()) {
        
        PersistentObject persistentObject = cachedObject.getPersistentObject();
        if (!isPersistentObjectDeleted(persistentObject)) {
          Object originalState = cachedObject.getPersistentObjectState();
          if (persistentObject.getPersistentState() != null && 
          		!persistentObject.getPersistentState().equals(originalState)) {
            updatedObjects.add(persistentObject);
          } else {
            log.trace("loaded object '{}' was not updated", persistentObject);
          }
        }
        
      }
      
    }
    return updatedObjects;
  }
  
  protected boolean isPersistentObjectDeleted(PersistentObject persistentObject) {
    for (DeleteOperation deleteOperation : deleteOperations) {
      if (deleteOperation.sameIdentity(persistentObject)) {
        return true;
      }
    }
    return false;
  }
  
  public <T extends PersistentObject> List<T> pruneDeletedEntities(List<T> listToPrune) {   
    List<T> prunedList = new ArrayList<T>(listToPrune);
    for (T potentiallyDeleted : listToPrune) {
      for (DeleteOperation deleteOperation: deleteOperations) {
          
        if (deleteOperation.sameIdentity(potentiallyDeleted)) {
          prunedList.remove(potentiallyDeleted);
        }
          
      }
    }
    return prunedList;
  }

  protected void flushInserts() {
    for (PersistentObject insertedObject: insertedObjects) {
      String insertStatement = dbSqlSessionFactory.getInsertStatement(insertedObject);
      insertStatement = dbSqlSessionFactory.mapStatement(insertStatement);

      if (insertStatement==null) {
        throw new LabelException("no insert statement for "+insertedObject.getClass()+" in the ibatis mapping files");
      }
      
      log.debug("inserting: {}", insertedObject);
      sqlSession.insert(insertStatement, insertedObject);
      
      // See http://jira.codehaus.org/browse/ACT-1290
      if (insertedObject instanceof HasRevision) {
        ((HasRevision) insertedObject).setRevision(((HasRevision) insertedObject).getRevisionNext());
      }
    }
    insertedObjects.clear();
  }

  protected void flushUpdates(List<PersistentObject> updatedObjects) {
    for (PersistentObject updatedObject: updatedObjects) {
      String updateStatement = dbSqlSessionFactory.getUpdateStatement(updatedObject);
      updateStatement = dbSqlSessionFactory.mapStatement(updateStatement);
      
      if (updateStatement==null) {
        throw new LabelException("no update statement for "+updatedObject.getClass()+" in the ibatis mapping files");
      }
      
      log.debug("updating: {}", updatedObject);
      int updatedRecords = sqlSession.update(updateStatement, updatedObject);
      if (updatedRecords!=1) {
        throw new ActivitiOptimisticLockingException(updatedObject + " was updated by another transaction concurrently");
      } 
      
      // See http://jira.codehaus.org/browse/ACT-1290
      if (updatedObject instanceof HasRevision) {
        ((HasRevision) updatedObject).setRevision(((HasRevision) updatedObject).getRevisionNext());
      }
      
    }
    updatedObjects.clear();
  }

  protected void flushDeletes(List<DeleteOperation> removedOperations) {
    boolean dispatchEvent = Context.getLabelServerConfiguration().getEventDispatcher().isEnabled();

    flushRegularDeletes(dispatchEvent);

    if (dispatchEvent) {
      dispatchEventsForRemovedOperations(removedOperations);
    }

    deleteOperations.clear();
  }

  protected void dispatchEventsForRemovedOperations(List<DeleteOperation> removedOperations) {
    for (DeleteOperation delete : removedOperations) {
      // dispatch removed delete events
      if (delete instanceof CheckedDeleteOperation) {
        CheckedDeleteOperation checkedDeleteOperation = (CheckedDeleteOperation) delete;
        PersistentObject persistentObject = checkedDeleteOperation.getPersistentObject();
        if (persistentObject instanceof VariableInstanceEntity) {
          VariableInstanceEntity variableInstance = (VariableInstanceEntity) persistentObject;
          Context.getLabelServerConfiguration().getEventDispatcher().dispatchEvent(
            createVariableDeleteEvent(variableInstance)
          );
        }
      }
    }
  }

  protected static ActivitiVariableEvent createVariableDeleteEvent(VariableInstanceEntity variableInstance) {
    return ActivitiEventBuilder.createVariableEvent(ActivitiEventType.VARIABLE_DELETED, variableInstance.getName(), null, variableInstance.getType(),
    		variableInstance.getTaskId(), variableInstance.getExecutionId(), variableInstance.getProcessInstanceId(), null);
  }

  protected void flushRegularDeletes(boolean dispatchEvent) {
  	List<DeleteOperation> optimizedDeleteOperations = optimizeDeleteOperations(deleteOperations);
    for (DeleteOperation delete : optimizedDeleteOperations) {
//  	for (DeleteOperation delete : deleteOperations) {
      log.debug("executing: {}", delete);

      delete.execute();

      //  fire event for variable delete operation. (BulkDeleteOperation is not taken into account)
      if (dispatchEvent) {
        //  prepare delete event to fire for variable delete operation. (BulkDeleteOperation is not taken into account)
        if (delete instanceof CheckedDeleteOperation) {
          CheckedDeleteOperation checkedDeleteOperation = (CheckedDeleteOperation) delete;
          PersistentObject persistentObject = checkedDeleteOperation.getPersistentObject();
          if (persistentObject instanceof VariableInstanceEntity) {
            VariableInstanceEntity variableInstance = (VariableInstanceEntity) persistentObject;
            Context.getLabelServerConfiguration().getEventDispatcher().dispatchEvent(
              createVariableDeleteEvent(variableInstance)
            );
          }
        } else if (delete instanceof BulkCheckedDeleteOperation) {
        	BulkCheckedDeleteOperation bulkCheckedDeleteOperation = (BulkCheckedDeleteOperation) delete;
        	if (VariableInstanceEntity.class.isAssignableFrom(bulkCheckedDeleteOperation.getPersistentObjectClass())) {
        		for (PersistentObject persistentObject : bulkCheckedDeleteOperation.getPersistentObjects()) {
        			 VariableInstanceEntity variableInstance = (VariableInstanceEntity) persistentObject;
               Context.getLabelServerConfiguration().getEventDispatcher().dispatchEvent(
                 createVariableDeleteEvent(variableInstance)
               );
        		}
        	}
        }
      }
    }
  }

  public void close() {
    sqlSession.close();
  }

  public void commit() {
    sqlSession.commit();
  }

  public void rollback() {
    sqlSession.rollback();
  }
  
  // schema operations ////////////////////////////////////////////////////////
  
  public void dbSchemaCheckVersion() {
    try {
      String dbVersion = getDbVersion();
      if (!LabelServer.VERSION.equals(dbVersion)) {
        throw new ActivitiWrongDbException(LabelServer.VERSION, dbVersion);
      }

      String errorMessage = null;
      if (!isEngineTablePresent()) {
        errorMessage = addMissingComponent(errorMessage, "server");
      }
      if (dbSqlSessionFactory.isDbHistoryUsed() && !isHistoryTablePresent()) {
        errorMessage = addMissingComponent(errorMessage, "history");
      }
      if (dbSqlSessionFactory.isDbIdentityUsed() && !isIdentityTablePresent()) {
        errorMessage = addMissingComponent(errorMessage, "identity");
      }
      
      if (errorMessage!=null) {
        throw new LabelException("Activiti database problem: "+errorMessage);
      }
      
    } catch (Exception e) {
      if (isMissingTablesException(e)) {
        throw new LabelException("no activiti tables in db. set <property name=\"databaseSchemaUpdate\" to value=\"true\" or value=\"create-drop\" (use create-drop for testing only!) in bean processEngineConfiguration in activiti.cfg.xml for automatic schema creation", e);
      } else {
        if (e instanceof RuntimeException) {
          throw (RuntimeException) e;
        } else {
          throw new LabelException("couldn't get db schema version", e);
        }
      }
    }

    log.debug("activiti db schema check successful");
  }

  protected String addMissingComponent(String missingComponents, String component) {
    if (missingComponents==null) {
      return "Tables missing for component(s) "+component;
    }
    return missingComponents+", "+component;
  }

  protected String getDbVersion() {
    String selectSchemaVersionStatement = dbSqlSessionFactory.mapStatement("selectDbSchemaVersion");
    return (String) sqlSession.selectOne(selectSchemaVersionStatement);
  }

  public void dbSchemaCreate() {
    LabelServerConfigurationImpl processEngineConfiguration = Context.getLabelServerConfiguration();
    
    if (isEngineTablePresent()) {
      String dbVersion = getDbVersion();
      if (!LabelServer.VERSION.equals(dbVersion)) {
        throw new ActivitiWrongDbException(LabelServer.VERSION, dbVersion);
      }
    } else {
      dbSchemaCreateEngine();
    }


    if (processEngineConfiguration.isDbIdentityUsed()) {
      dbSchemaCreateIdentity();
    }
  }

  protected void dbSchemaCreateIdentity() {
    executeMandatorySchemaResource("create", "identity");
  }


  protected void dbSchemaCreateEngine() {
    executeMandatorySchemaResource("create", "server");
  }

  public void dbSchemaDrop() {
    executeMandatorySchemaResource("drop", "server");
    if (dbSqlSessionFactory.isDbIdentityUsed()) {
      executeMandatorySchemaResource("drop", "identity");
    }
  }

  public void dbSchemaPrune() {
    if (isHistoryTablePresent() && !dbSqlSessionFactory.isDbHistoryUsed()) {
      executeMandatorySchemaResource("drop", "history");
    }
    if (isIdentityTablePresent() && dbSqlSessionFactory.isDbIdentityUsed()) {
      executeMandatorySchemaResource("drop", "identity");
    }
  }

  public void executeMandatorySchemaResource(String operation, String component) {
    executeSchemaResource(operation, component, getResourceForDbOperation(operation, operation, component), false);
  }

  public static String[] JDBC_METADATA_TABLE_TYPES = {"TABLE"};

	public String dbSchemaUpdate() {

		String feedback = null;
		boolean isUpgradeNeeded = false;
		int matchingVersionIndex = -1;

		if (isEngineTablePresent()) {

			PropertyEntity dbVersionProperty = selectById(PropertyEntity.class,"schema.version");
			String dbVersion = dbVersionProperty.getValue();

			// Determine index in the sequence of Activiti releases
			int index = 0;
			while (matchingVersionIndex < 0 && index < ACTIVITI_VERSIONS.size()) {
				if (ACTIVITI_VERSIONS.get(index).matches(dbVersion)) {
					matchingVersionIndex = index;
				} else {
					index++;
				}
			}

			// Exception when no match was found: unknown/unsupported version
			if (matchingVersionIndex < 0) {
				throw new LabelException(
				    "Could not update Activiti database schema: unknown version from database: '"
				        + dbVersion + "'");
			}

			isUpgradeNeeded = (matchingVersionIndex != (ACTIVITI_VERSIONS.size() - 1));

			if (isUpgradeNeeded) {
				dbVersionProperty.setValue(LabelServer.VERSION);

				PropertyEntity dbHistoryProperty;
				if ("5.0".equals(dbVersion)) {
					dbHistoryProperty = new PropertyEntity("schema.history", "create(5.0)");
					insert(dbHistoryProperty);
				} else {
					dbHistoryProperty = selectById(PropertyEntity.class, "schema.history");
				}

				// Set upgrade history
				String dbHistoryValue = dbHistoryProperty.getValue() + " upgrade(" + dbVersion + "->" + LabelServer.VERSION + ")";
				dbHistoryProperty.setValue(dbHistoryValue);

				// Engine upgrade
				dbSchemaUpgrade("server", matchingVersionIndex);
				feedback = "upgraded Activiti from " + dbVersion + " to "+ LabelServer.VERSION;
			}

		} else {
			dbSchemaCreateEngine();
		}
		
    
    if (isIdentityTablePresent()) {
      if (isUpgradeNeeded) {
        dbSchemaUpgrade("identity", matchingVersionIndex);
      }
    } else if (dbSqlSessionFactory.isDbIdentityUsed()) {
      dbSchemaCreateIdentity();
    }
    
    return feedback;
  }

  public boolean isEngineTablePresent(){
    return isTablePresent("ACT_RU_EXECUTION");
  }
  public boolean isHistoryTablePresent(){
    return isTablePresent("ACT_HI_PROCINST");
  }
  public boolean isIdentityTablePresent(){
    return isTablePresent("ACT_ID_USER");
  }

  public boolean isTablePresent(String tableName) {
  	// ACT-1610: in case the prefix IS the schema itself, we don't add the prefix, since the
  	// check is already aware of the schema
  	if (!dbSqlSessionFactory.isTablePrefixIsSchema()) {
  		tableName = prependDatabaseTablePrefix(tableName);
  	}
  	
    Connection connection = null;
    try {
      connection = sqlSession.getConnection();
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      ResultSet tables = null;

      String catalog = this.connectionMetadataDefaultCatalog;
      if (dbSqlSessionFactory.getDatabaseCatalog() != null && dbSqlSessionFactory.getDatabaseCatalog().length() > 0) {
        catalog = dbSqlSessionFactory.getDatabaseCatalog();
      }

      String schema = this.connectionMetadataDefaultSchema;
      if (dbSqlSessionFactory.getDatabaseSchema() != null && dbSqlSessionFactory.getDatabaseSchema().length() > 0) {
        schema = dbSqlSessionFactory.getDatabaseSchema();
      }
      
      String databaseType = dbSqlSessionFactory.getDatabaseType();
      
      if ("postgres".equals(databaseType)) {
        tableName = tableName.toLowerCase();
      }
      
      try {
        tables = databaseMetaData.getTables(catalog, schema, tableName, JDBC_METADATA_TABLE_TYPES);
        return tables.next();
      } finally {
        try {
          tables.close();
        } catch (Exception e) {
          log.error("Error closing meta data tables", e);
        }
      }
      
    } catch (Exception e) {
      throw new LabelException("couldn't check if tables are already present using metadata: "+e.getMessage(), e);
    }
  }
  
  protected boolean isUpgradeNeeded(String versionInDatabase) {
    if(LabelServer.VERSION.equals(versionInDatabase)) {
      return false;
    }
    
    String cleanDbVersion = getCleanVersion(versionInDatabase);
    String[] cleanDbVersionSplitted = cleanDbVersion.split("\\.");
    int dbMajorVersion = Integer.valueOf(cleanDbVersionSplitted[0]);
    int dbMinorVersion = Integer.valueOf(cleanDbVersionSplitted[1]);
    
    String cleanEngineVersion = getCleanVersion(LabelServer.VERSION);
    String[] cleanEngineVersionSplitted = cleanEngineVersion.split("\\.");
    int engineMajorVersion = Integer.valueOf(cleanEngineVersionSplitted[0]);
    int engineMinorVersion = Integer.valueOf(cleanEngineVersionSplitted[1]);
      
    if((dbMajorVersion > engineMajorVersion)
            || ( (dbMajorVersion <= engineMajorVersion) && (dbMinorVersion > engineMinorVersion) )) {
      throw new LabelException("Version of activiti database (" + versionInDatabase + ") is more recent than the engine (" + LabelServer.VERSION +")");
    } else if(cleanDbVersion.compareTo(cleanEngineVersion) == 0) {
      // Versions don't match exactly, possibly snapshot is being used
      log.warn("Engine-version is the same, but not an exact match: {} vs. {}. Not performing database-upgrade.", versionInDatabase, LabelServer.VERSION);
      return false;
    }
    return true;
  }
  
  protected String getCleanVersion(String versionString) {
    Matcher matcher = CLEAN_VERSION_REGEX.matcher(versionString);
    if(!matcher.find()) {
      throw new LabelException("Illegal format for version: " + versionString);
    }
    
    String cleanString = matcher.group();
    try {
      Double.parseDouble(cleanString); // try to parse it, to see if it is really a number
      return cleanString;
    } catch(NumberFormatException nfe) {
      throw new LabelException("Illegal format for version: " + versionString);
    }
  }
  
  protected String prependDatabaseTablePrefix(String tableName) {
    return dbSqlSessionFactory.getDatabaseTablePrefix() + tableName;    
  }
  
  protected void dbSchemaUpgrade(final String component, final int currentDatabaseVersionsIndex) {
  	ActivitiVersion activitiVersion = ACTIVITI_VERSIONS.get(currentDatabaseVersionsIndex);
  	String dbVersion = activitiVersion.getMainVersion();
    log.info("upgrading activiti {} schema from {} to {}", component, dbVersion, LabelServer.VERSION);
    
    // Actual execution of schema DDL SQL
    for (int i=currentDatabaseVersionsIndex + 1; i<ACTIVITI_VERSIONS.size(); i++) {
    	String nextVersion = ACTIVITI_VERSIONS.get(i).getMainVersion();
    	
    	// Taking care of -SNAPSHOT version in development
      if (nextVersion.endsWith("-SNAPSHOT")) {
      	nextVersion = nextVersion.substring(0, nextVersion.length()-"-SNAPSHOT".length());
      }
      
      dbVersion = dbVersion.replace(".", "");
      nextVersion = nextVersion.replace(".", "");
      log.info("Upgrade needed: {} -> {}. Looking for schema update resource for component '{}'", dbVersion, nextVersion, component);
    	executeSchemaResource("upgrade", component, getResourceForDbOperation("upgrade", "upgradestep." + dbVersion + ".to." + nextVersion, component), true);
    	dbVersion = nextVersion;
    }
  }
  
  public String getResourceForDbOperation(String directory, String operation, String component) {
    String databaseType = dbSqlSessionFactory.getDatabaseType();
    return "com/winit/db/" + directory + "/label." + databaseType + "." + operation + "."+component+".sql";
  }

  public void executeSchemaResource(String operation, String component, String resourceName, boolean isOptional) {
    InputStream inputStream = null;
    try {
      inputStream = ReflectUtil.getResourceAsStream(resourceName);
      if (inputStream == null) {
        if (isOptional) {
          log.info("no schema resource {} for {}", resourceName, operation);
        } else {
          throw new LabelException("resource '" + resourceName + "' is not available");
        }
      } else {
        executeSchemaResource(operation, component, resourceName, inputStream);
      }

    } finally {
      IoUtil.closeSilently(inputStream);
    }
  }

  private void executeSchemaResource(String operation, String component, String resourceName, InputStream inputStream) {
    log.info("performing {} on {} with resource {}", operation, component, resourceName);
    String sqlStatement = null;
    String exceptionSqlStatement = null;
    try {
      Connection connection = sqlSession.getConnection();
      Exception exception = null;
      byte[] bytes = IoUtil.readInputStream(inputStream, resourceName);
      String ddlStatements = new String(bytes);
      
      // Special DDL handling for certain databases
      try {
	    	String databaseType = dbSqlSessionFactory.getDatabaseType();
	    	if (databaseType.equals("mysql")) {
		     DatabaseMetaData databaseMetaData = connection.getMetaData();
		     int majorVersion = databaseMetaData.getDatabaseMajorVersion();
		     int minorVersion = databaseMetaData.getDatabaseMinorVersion();
		     log.info("Found MySQL: majorVersion=" + majorVersion + " minorVersion=" + minorVersion);
		      
		     // Special care for MySQL < 5.6
		     if (majorVersion <= 5 && minorVersion < 6) {
		       ddlStatements = updateDdlForMySqlVersionLowerThan56(ddlStatements);
		     }
	    	}
      } catch (Exception e) {
        log.info("Could not get database metadata", e);
      }
      
      BufferedReader reader = new BufferedReader(new StringReader(ddlStatements));
      String line = readNextTrimmedLine(reader);
      while (line != null) {
        if (line.startsWith("# ")) {
          log.debug(line.substring(2));
          
        } else if (line.startsWith("-- ")) {
          log.debug(line.substring(3));
          
        } else if (line.startsWith("execute java ")) {
          String upgradestepClassName = line.substring(13).trim();
          DbUpgradeStep dbUpgradeStep = null;
          try {
            dbUpgradeStep = (DbUpgradeStep) ReflectUtil.instantiate(upgradestepClassName);
          } catch (LabelException e) {
            throw new LabelException("database update java class '"+upgradestepClassName+"' can't be instantiated: "+e.getMessage(), e);
          }
          try {
            log.debug("executing upgrade step java class {}", upgradestepClassName);
            dbUpgradeStep.execute(this);
          } catch (Exception e) {
            throw new LabelException("error while executing database update java class '"+upgradestepClassName+"': "+e.getMessage(), e);
          }
          
        } else if (line.length()>0) {
          
          if (line.endsWith(";")) {
            sqlStatement = addSqlStatementPiece(sqlStatement, line.substring(0, line.length()-1));
            Statement jdbcStatement = connection.createStatement();
            try {
              // no logging needed as the connection will log it
              log.debug("SQL: {}", sqlStatement);
              jdbcStatement.execute(sqlStatement);
              jdbcStatement.close();
            } catch (Exception e) {
              if (exception == null) {
                exception = e;
                exceptionSqlStatement = sqlStatement;
              }
              log.error("problem during schema {}, statement {}", operation, sqlStatement, e);
            } finally {
              sqlStatement = null; 
            }
          } else {
            sqlStatement = addSqlStatementPiece(sqlStatement, line);
          }
        }
        
        line = readNextTrimmedLine(reader);
      }

      if (exception != null) {
        throw exception;
      }
      
      log.debug("activiti db schema {} for component {} successful", operation, component);
      
    } catch (Exception e) {
      throw new LabelException("couldn't "+operation+" db schema: "+exceptionSqlStatement, e);
    }
  }
  
  /**
   * MySQL is funny when it comes to timestamps and dates.
   *  
   * More specifically, for a DDL statement like 'MYCOLUMN timestamp(3)':
   *   - MySQL 5.6.4+ has support for timestamps/dates with millisecond (or smaller) precision. 
   *     The DDL above works and the data in the table will have millisecond precision
   *   - MySQL < 5.5.3 allows the DDL statement, but ignores it.
   *     The DDL above works but the data won't have millisecond precision
   *   - MySQL 5.5.3 < [version] < 5.6.4 gives and exception when using the DDL above.
   *   
   * Also, the 5.5 and 5.6 branches of MySQL are both actively developed and patched.
   * 
   * Hence, when doing auto-upgrade/creation of the Activiti tables, the default 
   * MySQL DDL file is used and all timestamps/datetimes are converted to not use the 
   * millisecond precision by string replacement done in the method below.
   * 
   * If using the DDL files directly (which is a sane choice in production env.),
   * there is a distinction between MySQL version < 5.6.
   */
  protected String updateDdlForMySqlVersionLowerThan56(String ddlStatements) {
	  return ddlStatements.replace("timestamp(3)", "timestamp")
			  			  .replace("datetime(3)", "datetime")
			  			  .replace("TIMESTAMP(3)", "TIMESTAMP")
			  			  .replace("DATETIME(3)", "DATETIME");
  }

  protected String addSqlStatementPiece(String sqlStatement, String line) {
    if (sqlStatement==null) {
      return line;
    }
    return sqlStatement + " \n" + line;
  }
  
  protected String readNextTrimmedLine(BufferedReader reader) throws IOException {
    String line = reader.readLine();
    if (line!=null) {
      line = line.trim();
    }
    return line;
  }
  
  protected boolean isMissingTablesException(Exception e) {
    String exceptionMessage = e.getMessage();
    if(e.getMessage() != null) {      
      // Matches message returned from H2
      if ((exceptionMessage.indexOf("Table") != -1) && (exceptionMessage.indexOf("not found") != -1)) {
        return true;
      }
      
      // Message returned from MySQL and Oracle
      if (((exceptionMessage.indexOf("Table") != -1 || exceptionMessage.indexOf("table") != -1)) && (exceptionMessage.indexOf("doesn't exist") != -1)) {
        return true;
      }
      
      // Message returned from Postgres
      if (((exceptionMessage.indexOf("relation") != -1 || exceptionMessage.indexOf("table") != -1)) && (exceptionMessage.indexOf("does not exist") != -1)) {
        return true;
      }
    }
    return false;
  }
  
  public void performSchemaOperationsLabelServerBuild() {
    String databaseSchemaUpdate = Context.getLabelServerConfiguration().getDatabaseSchemaUpdate();
    if (LabelServerConfigurationImpl.DB_SCHEMA_UPDATE_DROP_CREATE.equals(databaseSchemaUpdate)) {
      try {
        dbSchemaDrop();
      } catch (RuntimeException e) {
        // ignore
      }
    }
    if ( com.winit.svr.LabelServerConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP.equals(databaseSchemaUpdate) 
         || LabelServerConfigurationImpl.DB_SCHEMA_UPDATE_DROP_CREATE.equals(databaseSchemaUpdate)
         || LabelServerConfigurationImpl.DB_SCHEMA_UPDATE_CREATE.equals(databaseSchemaUpdate)
       ) {
      dbSchemaCreate();
      
    } else if (com.winit.svr.LabelServerConfiguration.DB_SCHEMA_UPDATE_FALSE.equals(databaseSchemaUpdate)) {
//      dbSchemaCheckVersion();
      
    } else if (LabelServerConfiguration.DB_SCHEMA_UPDATE_TRUE.equals(databaseSchemaUpdate)) {
      dbSchemaUpdate();
    }
  }

  public void performSchemaOperationsProcessEngineClose() {
    String databaseSchemaUpdate = Context.getLabelServerConfiguration().getDatabaseSchemaUpdate();
    if (com.winit.svr.LabelServerConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP.equals(databaseSchemaUpdate)) {
      dbSchemaDrop();
    }
  }
  
  public <T> T getCustomMapper(Class<T> type) {
	  return sqlSession.getMapper(type);
  }

  public UserQueryImpl createUserQuery() {
    return new UserQueryImpl();
  }
  public GroupQueryImpl createGroupQuery() {
    return new GroupQueryImpl();
  }

  // getters and setters //////////////////////////////////////////////////////
  
  public SqlSession getSqlSession() {
    return sqlSession;
  }
  public DbSqlSessionFactory getDbSqlSessionFactory() {
    return dbSqlSessionFactory;
  }


}
