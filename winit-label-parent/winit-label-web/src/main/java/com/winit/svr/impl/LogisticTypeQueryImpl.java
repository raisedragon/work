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

package com.winit.svr.impl;

import java.util.List;

import com.winit.svr.ActivitiIllegalArgumentException;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.interceptor.CommandExecutor;
import com.winit.svr.label.LogisticType;
import com.winit.svr.label.LogisticTypeQuery;


/**
 * @author Joram Barrez
 */
public class LogisticTypeQueryImpl extends AbstractQuery<LogisticTypeQuery, LogisticType> implements LogisticTypeQuery {
  
  private static final long serialVersionUID = 1L;
  protected String id;
  protected String name;
  protected String nameLike;
  

  public LogisticTypeQueryImpl() {
  }

  public LogisticTypeQueryImpl(CommandContext commandContext) {
    super(commandContext);
  }

  public LogisticTypeQueryImpl(CommandExecutor commandExecutor) {
    super(commandExecutor);
  }

  public LogisticTypeQuery logisticTypeId(String id) {
    if (id == null) {
      throw new ActivitiIllegalArgumentException("Provided id is null");
    }
    this.id = id;
    return this;
  }
  
  
  public LogisticTypeQuery logisticTypeNameLike(String nameLike) {
    if (nameLike == null) {
      throw new ActivitiIllegalArgumentException("Provided nameLike is null");
    }
    this.nameLike = nameLike;
    return this;
  }
  
  

  public LogisticTypeQuery potentialStarter(String procDefId) {
    if (procDefId == null) {
      throw new ActivitiIllegalArgumentException("Provided processDefinitionId is null or empty");
    }
    return this;
    
  }
  
  //sorting ////////////////////////////////////////////////////////
  
  public LogisticTypeQuery orderByLogisticTypeId() {
    return orderBy(LogisticTypeQueryProperty.LOGISTICTYPE_ID);
  }
  
  public LogisticTypeQuery orderByLogisticTypeName() {
    return orderBy(LogisticTypeQueryProperty.NAME);
  }
  
  
  //results ////////////////////////////////////////////////////////
  
  public long executeCount(CommandContext commandContext) {
    checkQueryOk();
    return commandContext
      .getLogisticTypeManager()
      .findLogisticTypeCountByQueryCriteria(this);
  }
  
  public List<LogisticType> executeList(CommandContext commandContext, Page page) {
    checkQueryOk();
    return commandContext
      .getLogisticTypeManager()
      .findLogisticTypeByQueryCriteria(this, page);
  }
  
  //getters ////////////////////////////////////////////////////////
  
  public String getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getNameLike() {
    return nameLike;
  }


  
}
