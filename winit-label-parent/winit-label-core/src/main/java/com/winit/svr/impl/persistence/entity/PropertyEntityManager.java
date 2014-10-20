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

import com.winit.svr.delegate.event.ActivitiEventType;
import com.winit.svr.delegate.event.impl.ActivitiEventBuilder;
import com.winit.svr.identity.Group;
import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.db.DbSqlSession;
import com.winit.svr.impl.db.PersistentObject;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.persistence.AbstractManager;


/**
 * @author Tom Baeyens
 */
public class PropertyEntityManager extends AbstractManager {

  public PropertyEntity findPropertyById(String propertyId) {
    return getDbSqlSession().selectById(PropertyEntity.class, propertyId);
  }
  
  public boolean isNewProperty(PropertyEntity property) {
    return  property.getRevision() == 0;
  }
  
  public void insertPropertyEntity(PropertyEntity PropertyEntity) {
	    getDbSqlSession().insert((PersistentObject) PropertyEntity);
	    
	    if(getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
	    	getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
	    			ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_CREATED, PropertyEntity));
	    	getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
	    			ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_INITIALIZED, PropertyEntity));
	    }
	  }

	  public void updatePropertyEntity(PropertyEntity updatedPropertyEntity) {
	    CommandContext commandContext = Context.getCommandContext();
	    DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
	    dbSqlSession.update( updatedPropertyEntity);
	    
	    if(getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
	    	getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
	    			ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_UPDATED, updatedPropertyEntity));
	    }
	  }
	  
	  
	  public void deletePropertyEntity(String name) {
	    	getDbSqlSession().delete("deletePropertyByName", name);
	  }
  
}
