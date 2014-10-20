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
import com.winit.svr.identity.Group;
import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.persistence.entity.PropertyEntity;


/**
 * @author Joram Barrez
 */
public class SavePropertyCmd implements Command<Void>, Serializable {
  
  private static final long serialVersionUID = 1L;
  protected PropertyEntity propertyEntity;
  
  public SavePropertyCmd(PropertyEntity propertyEntity) {
    this.propertyEntity = propertyEntity;
  }
  
  public Void execute(CommandContext commandContext) {
    if(propertyEntity == null) {
      throw new LabelIllegalArgumentException("group is null");
    }
    
    if(commandContext.getPropertyEntityManager().isNewProperty(propertyEntity)) {
    	commandContext
    		.getPropertyEntityManager()
    		.insertPropertyEntity(propertyEntity);
    } else {
    	commandContext
    		.getPropertyEntityManager()
    		.updatePropertyEntity(propertyEntity);
    }
    return null;
  }

}
