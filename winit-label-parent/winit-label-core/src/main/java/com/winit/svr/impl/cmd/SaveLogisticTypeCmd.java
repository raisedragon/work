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
import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.label.LogisticType;


/**
 * @author Joram Barrez
 */
public class SaveLogisticTypeCmd implements Command<Void>, Serializable {
  
  private static final long serialVersionUID = 1L;
  protected LogisticType logisticType;
  
  public SaveLogisticTypeCmd(LogisticType logisticType) {
    this.logisticType = logisticType;
  }
  
  public Void execute(CommandContext commandContext) {
    if(logisticType == null) {
      throw new LabelIllegalArgumentException("logisticType is null");
    }
    if (commandContext.getLogisticTypeManager().isNewLogisticType(logisticType)) {
      commandContext
        .getLogisticTypeManager()
        .insertLogisticType(logisticType);
    } else {
      commandContext
        .getLogisticTypeManager()
        .updateLogisticType(logisticType);
    }
    
    return null;
  }
}
