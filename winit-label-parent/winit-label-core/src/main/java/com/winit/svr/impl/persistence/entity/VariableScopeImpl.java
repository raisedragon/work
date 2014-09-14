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
package com.winit.svr.impl.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.winit.svr.LabelException;
import com.winit.svr.delegate.VariableScope;
import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.javax.el.ELContext;
import com.winit.svr.impl.variable.VariableType;
import com.winit.svr.impl.variable.VariableTypes;



/**
 * @author Tom Baeyens
 * @author Joram Barrez
 * @author Tijs Rademakers
 */
public abstract class VariableScopeImpl implements Serializable, VariableScope {
  
  private static final long serialVersionUID = 1L;
  
  protected Map<String, VariableInstanceEntity> variableInstances = null;
  protected List<VariableInstanceEntity> variableInstanceList = null;
  
  protected ELContext cachedElContext;

  protected String id = null;

  protected abstract List<VariableInstanceEntity> loadVariableInstances();
  protected abstract void initializeVariableInstanceBackPointer(VariableInstanceEntity variableInstance);

  protected void ensureVariableInstancesInitialized() {
    if (variableInstances==null) {
      variableInstances = new HashMap<String, VariableInstanceEntity>();
      variableInstanceList = new ArrayList<VariableInstanceEntity>();
      
      CommandContext commandContext = Context.getCommandContext();
      if (commandContext == null) {
        throw new LabelException("lazy loading outside command context");
      }
      List<VariableInstanceEntity> variableInstancesList = loadVariableInstances();
      for (VariableInstanceEntity variableInstance : variableInstancesList) {
        variableInstances.put(variableInstance.getName(), variableInstance);
        variableInstanceList.add(variableInstance);
      }
    }
  }
  
  public Map<String, Object> getVariables() {
    return collectVariables(new HashMap<String, Object>());
  }
  
  protected Map<String, Object> collectVariables(HashMap<String, Object> variables) {
    ensureVariableInstancesInitialized();
    return variables;
  }
  
  public Object getVariable(String variableName) {
    ensureVariableInstancesInitialized();
    VariableInstanceEntity variableInstance = variableInstances.get(variableName);
    if (variableInstance!=null) {
      return variableInstance.getValue();
    }
    return null;
  }
  
  public Object getVariableLocal(String variableName) {
    ensureVariableInstancesInitialized();
    VariableInstanceEntity variableInstance = variableInstances.get(variableName);
    if (variableInstance!=null) {
      return variableInstance.getValue();
    }
    return null;
  }
  
  public boolean hasVariables() {
    ensureVariableInstancesInitialized();
    if (!variableInstances.isEmpty()) {
      return true;
    }
    return false;
  }

  public boolean hasVariablesLocal() {
    ensureVariableInstancesInitialized();
    return !variableInstances.isEmpty();
  }

  public boolean hasVariable(String variableName) {
    if (hasVariableLocal(variableName)) {
      return true;
    }
    return false;
  }

  public boolean hasVariableLocal(String variableName) {
    ensureVariableInstancesInitialized();
    return variableInstances.containsKey(variableName);
  }

  protected Set<String> collectVariableNames(Set<String> variableNames) {
    ensureVariableInstancesInitialized();
    for (VariableInstanceEntity variableInstance: variableInstances.values()) {
      variableNames.add(variableInstance.getName());
    }
    return variableNames;
  }

  public Set<String> getVariableNames() {
    return collectVariableNames(new HashSet<String>());
  }
  
  public Map<String, Object> getVariablesLocal() {
    Map<String, Object> variables = new HashMap<String, Object>();
    ensureVariableInstancesInitialized();
    for (VariableInstanceEntity variableInstance: variableInstances.values()) {
      variables.put(variableInstance.getName(), variableInstance.getValue());
    }
    return variables;
  }

  public Set<String> getVariableNamesLocal() {
    ensureVariableInstancesInitialized();
    return variableInstances.keySet();
  }

  public void createVariablesLocal(Map<String, ? extends Object> variables) {
    if (variables!=null) {
      for (Map.Entry<String, ? extends Object> entry: variables.entrySet()) {
        createVariableLocal(entry.getKey(), entry.getValue());
      }
    }
  }

  public void setVariables(Map<String, ? extends Object> variables) {
    if (variables!=null) {
      for (String variableName : variables.keySet()) {
        setVariable(variableName, variables.get(variableName));
      }
    }
  }
  
  public void setVariablesLocal(Map<String, ? extends Object> variables) {
    if (variables!=null) {
      for (String variableName : variables.keySet()) {
        setVariableLocal(variableName, variables.get(variableName));
      }
    }
  }

  public void removeVariables() {
    ensureVariableInstancesInitialized();
    Set<String> variableNames = new HashSet<String>(variableInstances.keySet());
    for (String variableName: variableNames) {
      removeVariable(variableName);
    }
  }
  
  public void removeVariablesLocal() {
    List<String> variableNames = new ArrayList<String>(getVariableNamesLocal());
    for (String variableName: variableNames) {
      removeVariableLocal(variableName);
    }
  }
  
  
  public void removeVariables(Collection<String> variableNames) {
    if (variableNames != null) {
      for (String variableName : variableNames) {
        removeVariable(variableName);
      }
    }
  }

  public void removeVariablesLocal(Collection<String> variableNames) {
    if (variableNames != null) {
      for (String variableName : variableNames) {
        removeVariableLocal(variableName);
      }
    }
  }

//  public void setVariable(String variableName, Object value) {
//    setVariable(variableName, value);
//  }

  public void setVariable(String variableName, Object value) {
    if (hasVariableLocal(variableName)) {
      setVariableLocal(variableName, value);
      return;
    } 
    createVariableLocal(variableName, value);
  }

  public Object setVariableLocal(String variableName, Object value) {
    ensureVariableInstancesInitialized();
    VariableInstanceEntity variableInstance = variableInstances.get(variableName);
    if (variableInstance == null) {
      createVariableLocal(variableName, value);
    } else {
      updateVariableInstance(variableInstance, value);
    }
    
    return null;
  }
  

  /** only called when a new variable is created on this variable scope.
   * This method is also responsible for propagating the creation of this 
   * variable to the history. */
  public void createVariableLocal(String variableName, Object value) {
    ensureVariableInstancesInitialized();
    
    if (variableInstances.containsKey(variableName)) {
      throw new LabelException("variable '"+variableName+"' already exists. Use setVariableLocal if you want to overwrite the value");
    }
    
    createVariableInstance(variableName, value);
  }


  public void removeVariable(String variableName) {
    ensureVariableInstancesInitialized();
    if (variableInstances.containsKey(variableName)) {
      removeVariableLocal(variableName);
      return;
    }
  }

  public void removeVariableLocal(String variableName) {
    ensureVariableInstancesInitialized();
    VariableInstanceEntity variableInstance = variableInstances.remove(variableName);
    if (variableInstance != null) {
      deleteVariableInstanceForExplicitUserCall(variableInstance);
      variableInstanceList.remove(variableInstance);
    }
  }

  protected void deleteVariableInstanceForExplicitUserCall(VariableInstanceEntity variableInstance) {
    variableInstance.delete();
    variableInstance.setValue(null);

  }

  protected void updateVariableInstance(VariableInstanceEntity variableInstance, Object value) {
	
      // type should be changed
	 if ((variableInstance != null) && (!variableInstance.getType().isAbleToStore(value))) {
		    VariableTypes variableTypes = Context
		    	      .getLabelServerConfiguration()
		    	      .getVariableTypes();
		    VariableType newType = variableTypes.findVariableType(value);
		    variableInstance.setValue(null);
		    variableInstance.setType(newType);
		    variableInstance.forceUpdate();
		    variableInstance.setValue(value);
		    VariableInstanceEntity.touch(variableInstance);
	  } else
		  variableInstance.setValue(value);

  }

  protected VariableInstanceEntity createVariableInstance(String variableName, Object value) {
    VariableTypes variableTypes = Context
      .getLabelServerConfiguration()
      .getVariableTypes();
    
    VariableType type = variableTypes.findVariableType(value);
 
    VariableInstanceEntity variableInstance = VariableInstanceEntity.createAndInsert(variableName, type, value);
    initializeVariableInstanceBackPointer(variableInstance);
    variableInstances.put(variableName, variableInstance);
    variableInstanceList.add(variableInstance);

    return variableInstance;
  }

  
  /** 
   * Execution variable updates have activity instance ids, but historic task variable updates don't.
   */
  protected boolean isActivityIdUsedForDetails() {
    return true;
  }

  // getters and setters //////////////////////////////////////////////////////

  public ELContext getCachedElContext() {
    return cachedElContext;
  }
  public void setCachedElContext(ELContext cachedElContext) {
    this.cachedElContext = cachedElContext;
  }
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
}
