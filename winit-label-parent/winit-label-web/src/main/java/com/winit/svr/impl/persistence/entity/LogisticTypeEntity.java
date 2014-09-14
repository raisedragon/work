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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.winit.svr.identity.Picture;
import com.winit.svr.identity.User;
import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.db.HasRevision;
import com.winit.svr.impl.db.PersistentObject;
import com.winit.svr.label.LogisticType;



/**
 * @author longsheng.wang
 *
 */
public class LogisticTypeEntity implements LogisticType, Serializable, PersistentObject, HasRevision {

  private static final long serialVersionUID = 1L;

  protected String id;
  protected int revision;
  protected String name;
  
  
  public LogisticTypeEntity() {
  }
  
  public LogisticTypeEntity(String id) {
    this.id = id;
  }
  
  public void delete() {
    Context.getCommandContext()
      .getDbSqlSession()
      .delete(this);
  }
  
  public Object getPersistentState() {
    Map<String, Object> persistentState = new HashMap<String, Object>();
    persistentState.put("name", name);
    return persistentState;
  }
  
  public int getRevisionNext() {
    return revision+1;
  }
  


  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  
  
  
  public int getRevision() {
    return revision;
  }
  public void setRevision(int revision) {
    this.revision = revision;
  }

@Override
public String getName()
{
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setName()
{
	// TODO Auto-generated method stub
	
}

}
