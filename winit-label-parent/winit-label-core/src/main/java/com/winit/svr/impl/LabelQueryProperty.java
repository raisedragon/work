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
import com.winit.svr.label.Label.StatusType;
import com.winit.svr.query.QueryProperty;



/**
 * @author longsheng.wang
 *
 */
public class LabelQueryProperty implements QueryProperty {
  
  private static final long serialVersionUID = 1L;

  private static final Map<String, LabelQueryProperty> properties = new HashMap<String, LabelQueryProperty>();
  public static final LabelQueryProperty LABEL_ID = new LabelQueryProperty("RES.ID_");
  public static final LabelQueryProperty DOCUMENTNO = new LabelQueryProperty("RES.DOCUMENTNO_");
  public static final LabelQueryProperty TRACKNO = new LabelQueryProperty("RES.TRACKNO_");
  public static final LabelQueryProperty STATUS = new LabelQueryProperty("RES.STATUS_");
  
  private String name;

  public LabelQueryProperty(String name) {
    this.name = name;
    properties.put(name, this);
  }

  public String getName() {
    return name;
  }
  
  public static LabelQueryProperty findByName(String propertyName) {
    return properties.get(propertyName);
  }

}
