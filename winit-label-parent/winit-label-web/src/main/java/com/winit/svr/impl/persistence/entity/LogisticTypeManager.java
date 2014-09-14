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

import java.util.List;
import java.util.Map;

import com.winit.svr.delegate.event.ActivitiEventType;
import com.winit.svr.delegate.event.impl.ActivitiEventBuilder;
import com.winit.svr.impl.LogisticTypeQueryImpl;
import com.winit.svr.impl.Page;
import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.db.DbSqlSession;
import com.winit.svr.impl.db.PersistentObject;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.persistence.AbstractManager;
import com.winit.svr.label.LogisticType;
import com.winit.svr.label.LogisticTypeQuery;


/**
 * @author longsheng.wang
 *
 */
public class LogisticTypeManager extends AbstractManager {


  public void insertLogisticType(LogisticType logisticType) {
    getDbSqlSession().insert((PersistentObject) logisticType);
    
    if(getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
    	getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
    			ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_CREATED, logisticType));
    	getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
    			ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_INITIALIZED, logisticType));
    }
  }

  public void updateLogisticType(LogisticType updatedLogisticType) {
    CommandContext commandContext = Context.getCommandContext();
    DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
    dbSqlSession.update((LogisticTypeEntity) updatedLogisticType);
    
    if(getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
    	getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
    			ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_UPDATED, updatedLogisticType));
    }
  }

  public void deleteLogisticType(String logisticTypeId) {
	  LogisticTypeEntity logisticType = getDbSqlSession().selectById(LogisticTypeEntity.class, logisticTypeId);
    
    if(logisticType != null) {
    	if(getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
      	getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
      			ActivitiEventBuilder.createMembershipEvent(ActivitiEventType.MEMBERSHIPS_DELETED, logisticTypeId, null));
      }
    	
    	getDbSqlSession().delete("deleteMembershipsByLogisticTypeId", logisticTypeId);
    	getDbSqlSession().delete(logisticType);
    	
    	if(getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
    		getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
    				ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_DELETED, logisticType));
    	}
    }
  }

  public LogisticTypeQuery createNewLogisticTypeQuery() {
    return new LogisticTypeQueryImpl(Context.getProcessEngineConfiguration().getCommandExecutor());
  }

  @SuppressWarnings("unchecked")
  public List<LogisticType> findLogisticTypeByQueryCriteria(LogisticTypeQueryImpl query, Page page) {
    return getDbSqlSession().selectList("selectLogisticTypeByQueryCriteria", query, page);
  }

  public long findLogisticTypeCountByQueryCriteria(LogisticTypeQueryImpl query) {
    return (Long) getDbSqlSession().selectOne("selectLogisticTypeCountByQueryCriteria", query);
  }

  @SuppressWarnings("unchecked")
  public List<LogisticType> findLogisticTypesByUser(String userId) {
    return getDbSqlSession().selectList("selectLogisticTypesByUserId", userId);
  }

  @SuppressWarnings("unchecked")
  public List<LogisticType> findLogisticTypesByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
    return getDbSqlSession().selectListWithRawParameter("selectLogisticTypeByNativeQuery", parameterMap, firstResult, maxResults);
  }

  public long findLogisticTypeCountByNativeQuery(Map<String, Object> parameterMap) {
    return (Long) getDbSqlSession().selectOne("selectLogisticTypeCountByNativeQuery", parameterMap);
  }
  
  public boolean isNewLogisticType(LogisticType logisticType) {
    return ((LogisticTypeEntity) logisticType).getRevision() == 0;
  }
  
}
