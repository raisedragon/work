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

package com.winit.svr.impl.db;

import com.winit.svr.LabelServers;
import com.winit.svr.impl.LabelServerImpl;
import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandConfig;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.interceptor.CommandExecutor;


/**
 * @author Tom Baeyens
 */
public class DbSchemaDrop {

  public static void main(String[] args) {
    LabelServerImpl processEngine = (LabelServerImpl) LabelServers.getDefaultProcessEngine();
    CommandExecutor commandExecutor = processEngine.getLabelServerConfiguration().getCommandExecutor();
    CommandConfig config = new CommandConfig().transactionNotSupported();
    commandExecutor.execute(config, new Command<Object>() {
      public Object execute(CommandContext commandContext) {
        commandContext
          .getSession(DbSqlSession.class)
          .dbSchemaDrop();
        return null;
      }
    });
  }
}
