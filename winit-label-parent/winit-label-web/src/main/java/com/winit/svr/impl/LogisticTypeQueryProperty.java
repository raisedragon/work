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

import java.util.HashMap;
import java.util.Map;

import com.winit.svr.identity.GroupQuery;
import com.winit.svr.query.QueryProperty;



/**
 * Contains the possible properties that can be used by the {@link GroupQuery}.
 * 
 * @author Joram Barrez
 */
public class LogisticTypeQueryProperty implements QueryProperty {
  
  private static final long serialVersionUID = 1L;

  private static final Map<String, LogisticTypeQueryProperty> properties = new HashMap<String, LogisticTypeQueryProperty>();

  public static final LogisticTypeQueryProperty LOGISTICTYPE_ID = new LogisticTypeQueryProperty("RES.ID_");
  public static final LogisticTypeQueryProperty NAME = new LogisticTypeQueryProperty("RES.NAME_");
  
  private String name;

  public LogisticTypeQueryProperty(String name) {
    this.name = name;
    properties.put(name, this);
  }

  public String getName() {
    return name;
  }
  
  public static LogisticTypeQueryProperty findByName(String propertyName) {
    return properties.get(propertyName);
  }

}
