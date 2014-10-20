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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.persistence.entity.PropertyEntity;



public class GetPropertyCmd implements Command<PropertyEntity>, Serializable {

  private static final long serialVersionUID = 1L;
  private String key;
  
  public GetPropertyCmd(String key)
{
		this.key = key;
	}

  @SuppressWarnings("unchecked")
  public PropertyEntity execute(CommandContext commandContext) {
    PropertyEntity property = commandContext.getPropertyEntityManager().findPropertyById(key);
  	return  property;
  }

}
