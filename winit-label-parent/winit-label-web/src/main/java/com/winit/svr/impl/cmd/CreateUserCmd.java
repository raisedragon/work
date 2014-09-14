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

import com.winit.svr.ActivitiIllegalArgumentException;
import com.winit.svr.identity.User;
import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandContext;


/**
 * @author Tom Baeyens
 */
public class CreateUserCmd implements Command<User>, Serializable {

  private static final long serialVersionUID = 1L;
  
  protected String userId;
  
  public CreateUserCmd(String userId) {
    if(userId == null) {
      throw new ActivitiIllegalArgumentException("userId is null");
    }
    this.userId = userId;
  }

  public User execute(CommandContext commandContext) {
    return commandContext
      .getUserIdentityManager()
      .createNewUser(userId);
  }
}
