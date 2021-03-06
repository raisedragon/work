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
import com.winit.svr.identity.Group;
import com.winit.svr.identity.GroupQuery;
import com.winit.svr.impl.GroupQueryImpl;
import com.winit.svr.impl.Page;
import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.db.DbSqlSession;
import com.winit.svr.impl.db.PersistentObject;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.persistence.AbstractManager;


/**
 * @author Tom Baeyens
 * @author Saeid Mirzaei
 * @author Joram Barrez
 */
public class GroupEntityManager extends AbstractManager implements GroupIdentityManager {

  public Group createNewGroup(String groupId) {
    return new GroupEntity(groupId);
  }

  public void insertGroup(Group group) {
    getDbSqlSession().insert((PersistentObject) group);
    
    if(getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
    	getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
    			ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_CREATED, group));
    	getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
    			ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_INITIALIZED, group));
    }
  }

  public void updateGroup(Group updatedGroup) {
    CommandContext commandContext = Context.getCommandContext();
    DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
    dbSqlSession.update((GroupEntity) updatedGroup);
    
    if(getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
    	getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
    			ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_UPDATED, updatedGroup));
    }
  }

  public void deleteGroup(String groupId) {
    GroupEntity group = getDbSqlSession().selectById(GroupEntity.class, groupId);
    
    if(group != null) {
    	if(getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
      	getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
      			ActivitiEventBuilder.createMembershipEvent(ActivitiEventType.MEMBERSHIPS_DELETED, groupId, null));
      }
    	
    	getDbSqlSession().delete("deleteMembershipsByGroupId", groupId);
    	getDbSqlSession().delete(group);
    	
    	if(getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
    		getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
    				ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_DELETED, group));
    	}
    }
  }

  public GroupQuery createNewGroupQuery() {
    return new GroupQueryImpl(Context.getLabelServerConfiguration().getCommandExecutor());
  }

  @SuppressWarnings("unchecked")
  public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
    return getDbSqlSession().selectList("selectGroupByQueryCriteria", query, page);
  }
  
  public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
    return (Long) getDbSqlSession().selectOne("selectGroupCountByQueryCriteria", query);
  }

  @SuppressWarnings("unchecked")
  public List<Group> findGroupsByUser(String userId) {
    return getDbSqlSession().selectList("selectGroupsByUserId", userId);
  }

  @SuppressWarnings("unchecked")
  public List<Group> findGroupsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
    return getDbSqlSession().selectListWithRawParameter("selectGroupByNativeQuery", parameterMap, firstResult, maxResults);
  }

  public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
    return (Long) getDbSqlSession().selectOne("selectGroupCountByNativeQuery", parameterMap);
  }
  
  @Override
  public boolean isNewGroup(Group group) {
    return ((GroupEntity) group).getRevision() == 0;
  }
  
}
