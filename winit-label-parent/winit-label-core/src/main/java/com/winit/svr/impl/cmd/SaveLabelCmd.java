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
import com.winit.svr.label.Label;


/**
 * @author Joram Barrez
 */
public class SaveLabelCmd implements Command<Void>, Serializable {
  
  private static final long serialVersionUID = 1L;
  protected Label label;
  
  public SaveLabelCmd(Label label) {
    this.label = label;
  }
  
  public Void execute(CommandContext commandContext) {
    if(label == null) {
      throw new LabelIllegalArgumentException("label is null");
    }
    if (commandContext.getLabelManager().isNewLabel(label)) {
      commandContext
        .getLabelManager()
        .insertLabel(label);
    } else {
      commandContext
        .getLabelManager()
        .updateLabel(label);
    }
    
    return null;
  }
}
