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

package com.winit.svr.impl.persistence.entity;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winit.svr.LabelException;
import com.winit.svr.identity.Group;
import com.winit.svr.identity.User;
import com.winit.svr.impl.TablePageQueryImpl;
import com.winit.svr.impl.db.PersistentObject;
import com.winit.svr.impl.persistence.AbstractManager;
import com.winit.svr.management.TableMetaData;
import com.winit.svr.management.TablePage;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Tom Baeyens
 */
public class TableDataManager extends AbstractManager {
  
  private static Logger log = LoggerFactory.getLogger(TableDataManager.class);
  
  public static Map<Class<?>, String> apiTypeToTableNameMap = new HashMap<Class<?>, String>();
  public static Map<Class<? extends PersistentObject>, String> persistentObjectToTableNameMap = new HashMap<Class<? extends PersistentObject>, String>();
  
  static {
    
    // Identity module
    persistentObjectToTableNameMap.put(GroupEntity.class, "ACT_ID_GROUP");
    persistentObjectToTableNameMap.put(MembershipEntity.class, "ACT_ID_MEMBERSHIP");
    persistentObjectToTableNameMap.put(UserEntity.class, "ACT_ID_USER");
    persistentObjectToTableNameMap.put(IdentityInfoEntity.class, "ACT_ID_INFO");
    
    // general
    persistentObjectToTableNameMap.put(PropertyEntity.class, "ACT_GE_PROPERTY");
    persistentObjectToTableNameMap.put(ByteArrayEntity.class, "ACT_GE_BYTEARRAY");
    persistentObjectToTableNameMap.put(ResourceEntity.class, "ACT_GE_BYTEARRAY");
    
   

    // identity
    apiTypeToTableNameMap.put(Group.class, "ACT_ID_GROUP");
    apiTypeToTableNameMap.put(User.class, "ACT_ID_USER");

    // TODO: Identity skipped for the moment as no SQL injection is provided here
  }

  public Map<String, Long> getTableCount() {
    Map<String, Long> tableCount = new HashMap<String, Long>();
    try {
      for (String tableName: getTablesPresentInDatabase()) {
        tableCount.put(tableName, getTableCount(tableName));
      }
      log.debug("Number of rows per activiti table: {}", tableCount);
    } catch (Exception e) {
      throw new LabelException("couldn't get table counts", e);
    }
    return tableCount;
  }

  public List<String> getTablesPresentInDatabase() {
    List<String> tableNames = new ArrayList<String>();
    Connection connection = null;
    try {
      connection = getDbSqlSession().getSqlSession().getConnection();
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      ResultSet tables = null;
      try {
        log.debug("retrieving activiti tables from jdbc metadata");
        String databaseTablePrefix = getDbSqlSession().getDbSqlSessionFactory().getDatabaseTablePrefix();
        String tableNameFilter = databaseTablePrefix+"ACT_%";
        if ("postgres".equals(getDbSqlSession().getDbSqlSessionFactory().getDatabaseType())) {
          tableNameFilter = databaseTablePrefix+"act_%";
        }
        if ("oracle".equals(getDbSqlSession().getDbSqlSessionFactory().getDatabaseType())) {
          tableNameFilter = databaseTablePrefix+"ACT" + databaseMetaData.getSearchStringEscape() + "_%";
        }
        tables = databaseMetaData.getTables(null, null, tableNameFilter, getDbSqlSession().JDBC_METADATA_TABLE_TYPES);
        while (tables.next()) {
          String tableName = tables.getString("TABLE_NAME");
          tableName = tableName.toUpperCase();
          tableNames.add(tableName);
          log.debug("  retrieved activiti table name {}", tableName);
        }
      } finally {
        tables.close();
      }
    } catch (Exception e) {
      throw new LabelException("couldn't get activiti table names using metadata: "+e.getMessage(), e); 
    }
    return tableNames;
  }

  protected long getTableCount(String tableName) {
    log.debug("selecting table count for {}", tableName);
    Long count = (Long) getDbSqlSession().selectOne("selectTableCount",
            Collections.singletonMap("tableName", tableName));
    return count;
  }

  @SuppressWarnings("unchecked")
  public TablePage getTablePage(TablePageQueryImpl tablePageQuery, int firstResult, int maxResults) {

    TablePage tablePage = new TablePage();

    @SuppressWarnings("rawtypes")
    List tableData = getDbSqlSession().getSqlSession()
      .selectList("selectTableData", tablePageQuery, new RowBounds(firstResult, maxResults));

    tablePage.setTableName(tablePageQuery.getTableName());
    tablePage.setTotal(getTableCount(tablePageQuery.getTableName()));
    tablePage.setRows((List<Map<String,Object>>)tableData);
    tablePage.setFirstResult(firstResult);
    
    return tablePage;
  }
  
  public String getTableName(Class<?> entityClass, boolean withPrefix) {
    String databaseTablePrefix = getDbSqlSession().getDbSqlSessionFactory().getDatabaseTablePrefix();
    String tableName = null;
    
    if (PersistentObject.class.isAssignableFrom(entityClass)) {
      tableName = persistentObjectToTableNameMap.get(entityClass);
    }
    else {
      tableName = apiTypeToTableNameMap.get(entityClass);
    }
    if (withPrefix) {
      return databaseTablePrefix + tableName;
    }
    else {
      return tableName;
    }
  }

  public TableMetaData getTableMetaData(String tableName) {
    TableMetaData result = new TableMetaData();
    try {
      result.setTableName(tableName);
      DatabaseMetaData metaData = getDbSqlSession()
        .getSqlSession()
        .getConnection()
        .getMetaData();

      if ("postgres".equals(getDbSqlSession().getDbSqlSessionFactory().getDatabaseType())) {
        tableName = tableName.toLowerCase();
      }

      ResultSet resultSet = metaData.getColumns(null, null, tableName, null);
      while(resultSet.next()) {
        String name = resultSet.getString("COLUMN_NAME").toUpperCase();
        String type = resultSet.getString("TYPE_NAME").toUpperCase();
        result.addColumnMetaData(name, type);
      }
      
    } catch (SQLException e) {
      throw new LabelException("Could not retrieve database metadata: " + e.getMessage());
    }

    if(result.getColumnNames().size() == 0) {
      // According to API, when a table doesn't exist, null should be returned
      result = null;
    }
    return result;
  }

}
