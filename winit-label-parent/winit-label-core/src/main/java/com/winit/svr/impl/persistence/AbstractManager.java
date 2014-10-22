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

package com.winit.svr.impl.persistence;

import com.winit.svr.impl.cfg.LabelServerConfigurationImpl;
import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.db.DbSqlSession;
import com.winit.svr.impl.db.PersistentObject;
import com.winit.svr.impl.interceptor.Session;
import com.winit.svr.impl.persistence.entity.ByteArrayEntityManager;
import com.winit.svr.impl.persistence.entity.GroupIdentityManager;
import com.winit.svr.impl.persistence.entity.IdentityInfoEntityManager;
import com.winit.svr.impl.persistence.entity.MembershipIdentityManager;
import com.winit.svr.impl.persistence.entity.ResourceEntityManager;
import com.winit.svr.impl.persistence.entity.UserIdentityManager;
import com.winit.svr.impl.persistence.entity.VariableInstanceEntityManager;


/**
 * @author Tom Baeyens
 * @author Joram Barrez
 */
public abstract class AbstractManager implements Session {
  
  public void insert(PersistentObject persistentObject) {
    getDbSqlSession().insert(persistentObject);
  }

  public void delete(PersistentObject persistentObject) {
    getDbSqlSession().delete(persistentObject);
  }

  public <T extends PersistentObject> T select(Class<T> clazz,String id){
	  return getDbSqlSession().selectById(clazz, id);
  }
  
  protected DbSqlSession getDbSqlSession() {
    return getSession(DbSqlSession.class);
  }

  protected <T> T getSession(Class<T> sessionClass) {
    return Context.getCommandContext().getSession(sessionClass);
  }

  protected ResourceEntityManager getResourceManager() {
    return getSession(ResourceEntityManager.class);
  }
  
  protected ByteArrayEntityManager getByteArrayManager() {
    return getSession(ByteArrayEntityManager.class);
  }


  

  protected VariableInstanceEntityManager getVariableInstanceManager() {
    return getSession(VariableInstanceEntityManager.class);
  }
  
  protected UserIdentityManager getUserIdentityManager() {
    return getSession(UserIdentityManager.class);
  }
  
  protected GroupIdentityManager getGroupIdentityManager() {
    return getSession(GroupIdentityManager.class);
  }
  
  protected IdentityInfoEntityManager getIdentityInfoManager() {
    return getSession(IdentityInfoEntityManager.class);
  }
  
  protected MembershipIdentityManager getMembershipIdentityManager() {
    return getSession(MembershipIdentityManager.class);
  }
  
  protected LabelServerConfigurationImpl getProcessEngineConfiguration() {
  	return Context.getLabelServerConfiguration();
  }
  
  public void close() {
  }

  public void flush() {
  }
}
