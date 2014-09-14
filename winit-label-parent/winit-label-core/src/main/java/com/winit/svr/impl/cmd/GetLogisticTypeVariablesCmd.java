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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.winit.svr.LabelIllegalArgumentException;
import com.winit.svr.LabelObjectNotFoundException;
import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.persistence.entity.LogisticTypeEntity;
import com.winit.svr.label.LogisticType;



/**
 * @author Tom Baeyens
 */
public class GetLogisticTypeVariablesCmd implements Command<Map<String, Object>>, Serializable {

  private static final long serialVersionUID = 1L;
  protected String logisticTypeId;
  protected Collection<String> variableNames;
  protected boolean isLocal;

  public GetLogisticTypeVariablesCmd(String logisticTypeId, Collection<String> variableNames, boolean isLocal) {
    this.logisticTypeId = logisticTypeId;
    this.variableNames = variableNames;
    this.isLocal = isLocal;
  }

  public Map<String, Object> execute(CommandContext commandContext) {
    if(logisticTypeId == null) {
      throw new LabelIllegalArgumentException("logisticTypeId is null");
    }
    
    LogisticTypeEntity logisticType = commandContext
      .getLogisticTypeManager()
      .findByLogisticTypeId(logisticTypeId);
    
    if (logisticType==null) {
      throw new LabelObjectNotFoundException("logisticType "+logisticTypeId+" doesn't exist", LogisticType.class);
    }

    Map<String, Object> logisticTypeVariables;
    if (isLocal) {
      logisticTypeVariables = logisticType.getVariablesLocal();
    } else {
      logisticTypeVariables = logisticType.getVariables();
    }
    
    if (variableNames==null) {
      variableNames = logisticTypeVariables.keySet();
    }
    
    // this copy is made to avoid lazy initialization outside a command context
    Map<String, Object> variables = new HashMap<String, Object>();
    for (String variableName: variableNames) {
      variables.put(variableName, logisticType.getVariable(variableName));
    }
    
    return variables;
  }
}
