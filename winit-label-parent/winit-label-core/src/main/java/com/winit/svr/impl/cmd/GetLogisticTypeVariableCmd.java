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

package com.winit.svr.impl.cmd;

import java.io.Serializable;

import com.winit.svr.LabelIllegalArgumentException;
import com.winit.svr.LabelObjectNotFoundException;
import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.persistence.entity.LogisticTypeEntity;
import com.winit.svr.label.LogisticType;



/**
 * @author Tom Baeyens
 */
public class GetLogisticTypeVariableCmd implements Command<Object>, Serializable {

  private static final long serialVersionUID = 1L;
  protected String logisticTypeId;
  protected String variableName;
  protected boolean isLocal;

  public GetLogisticTypeVariableCmd(String logisticTypeId, String variableName, boolean isLocal) {
    this.logisticTypeId = logisticTypeId;
    this.variableName = variableName;
    this.isLocal = isLocal;
  }

  public Object execute(CommandContext commandContext) {
    if(logisticTypeId == null) {
      throw new LabelIllegalArgumentException("logisticTypeId is null");
    }
    if(variableName == null) {
      throw new LabelIllegalArgumentException("variableName is null");
    }
    
    LogisticTypeEntity logisticType = commandContext
      .getLogisticTypeManager()
      .findByLogisticTypeId(logisticTypeId);
    
    if (logisticType==null) {
      throw new LabelObjectNotFoundException("logisticType "+logisticTypeId+" doesn't exist", LogisticType.class);
    }
    
    Object value;
    
    if (isLocal) {
      value = logisticType.getVariableLocal(variableName);
    } else {
      value = logisticType.getVariable(variableName);
    }
    
    return value;
  }
}
