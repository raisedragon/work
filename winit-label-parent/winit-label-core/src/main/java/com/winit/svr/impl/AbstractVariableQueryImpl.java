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

import java.util.ArrayList;
import java.util.List;

import com.winit.svr.LabelIllegalArgumentException;
import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.interceptor.CommandExecutor;
import com.winit.svr.impl.variable.VariableTypes;
import com.winit.svr.query.Query;


/**
 * Abstract query class that adds methods to query for variable values.
 * 
 * @author Frederik Heremans
 */
public abstract class AbstractVariableQueryImpl<T extends Query<?,?>, U> extends AbstractQuery<T, U> {

  private static final long serialVersionUID = 1L;
  
  protected List<QueryVariableValue> queryVariableValues = new ArrayList<QueryVariableValue>();
  
  public AbstractVariableQueryImpl() {
  }

  public AbstractVariableQueryImpl(CommandContext commandContext) {
    super(commandContext);
  }

  public AbstractVariableQueryImpl(CommandExecutor commandExecutor) {
    super(commandExecutor);
  }

  @Override
  public abstract long executeCount(CommandContext commandContext) ;

  @Override
  public abstract List<U> executeList(CommandContext commandContext, Page page);
  
  public T variableValueEquals(String name, Object value) {
    return variableValueEquals(name, value, true);
  }
  
  @SuppressWarnings("unchecked")
  public T variableValueEquals(String name, Object value, boolean localScope) {
    addVariable(name, value, QueryOperator.EQUALS, localScope);
    return (T) this;
  }
  
  public T variableValueEquals(Object value) {
    return variableValueEquals(value, true);
  }
  
  @SuppressWarnings("unchecked")
  public T variableValueEquals(Object value, boolean localScope) {
    queryVariableValues.add(new QueryVariableValue(null, value, QueryOperator.EQUALS, localScope));
    return (T) this;
  }
  
  public T variableValueEqualsIgnoreCase(String name, String value) {
    return variableValueEqualsIgnoreCase(name, value, true);
  }
  
  @SuppressWarnings("unchecked")
  public T variableValueEqualsIgnoreCase(String name, String value, boolean localScope) {
    if(value == null) {
      throw new LabelIllegalArgumentException("value is null");
    }
    addVariable(name, value.toLowerCase(), QueryOperator.EQUALS_IGNORE_CASE, localScope);
    return (T)this;
  }
  
  public T variableValueNotEqualsIgnoreCase(String name, String value) {
    return variableValueNotEqualsIgnoreCase(name, value, true);
  }
  
  @SuppressWarnings("unchecked")
  public T variableValueNotEqualsIgnoreCase(String name, String value, boolean localScope) {
    if(value == null) {
      throw new LabelIllegalArgumentException("value is null");
    }
    addVariable(name, value.toLowerCase(), QueryOperator.NOT_EQUALS_IGNORE_CASE, localScope);
    return (T)this;
  }
  
  public T variableValueNotEquals(String name, Object value) {
    return variableValueNotEquals(name, value, true);
  }
  
  @SuppressWarnings("unchecked")
  public T variableValueNotEquals(String name, Object value, boolean localScope) {
    addVariable(name, value, QueryOperator.NOT_EQUALS, localScope);
    return (T) this;
  }
  
  public T variableValueGreaterThan(String name, Object value) {
    return variableValueGreaterThan(name, value, true);
  }
  
  @SuppressWarnings("unchecked")
  public T variableValueGreaterThan(String name, Object value, boolean localScope) {
    addVariable(name, value, QueryOperator.GREATER_THAN, localScope);
    return (T) this;
  }
  
  public T variableValueGreaterThanOrEqual(String name, Object value) {
    return variableValueGreaterThanOrEqual(name, value, true);
  }
  
  @SuppressWarnings("unchecked")
  public T variableValueGreaterThanOrEqual(String name, Object value, boolean localScope) {
    addVariable(name, value, QueryOperator.GREATER_THAN_OR_EQUAL, localScope);
    return (T) this;
  }
  
  public T variableValueLessThan(String name, Object value) {
    return variableValueLessThan(name, value, true);
  }
  
  @SuppressWarnings("unchecked")
  public T variableValueLessThan(String name, Object value, boolean localScope) {
    addVariable(name, value, QueryOperator.LESS_THAN, localScope);
    return (T) this;
  }
  
  public T variableValueLessThanOrEqual(String name, Object value) {
    return variableValueLessThanOrEqual(name, value, true);
  }
  
  @SuppressWarnings("unchecked")
  public T variableValueLessThanOrEqual(String name, Object value, boolean localScope) {
    addVariable(name, value, QueryOperator.LESS_THAN_OR_EQUAL, localScope);
    return (T) this;
  }
  
  public T variableValueLike(String name, String value) {
    return variableValueLike(name, value, true);
  }
  
  @SuppressWarnings("unchecked")
  public T variableValueLike(String name, String value, boolean localScope) {
    addVariable(name, value, QueryOperator.LIKE, localScope);
    return (T)this;
  }
  
  private void addVariable(String name, Object value, QueryOperator operator, boolean localScope) {
    if(name == null) {
      throw new LabelIllegalArgumentException("name is null");
    }
    if(value == null || isBoolean(value)) {
      // Null-values and booleans can only be used in EQUALS and NOT_EQUALS
      switch(operator) {
      case GREATER_THAN:
        throw new LabelIllegalArgumentException("Booleans and null cannot be used in 'greater than' condition");
      case LESS_THAN:
        throw new LabelIllegalArgumentException("Booleans and null cannot be used in 'less than' condition");
      case GREATER_THAN_OR_EQUAL:
        throw new LabelIllegalArgumentException("Booleans and null cannot be used in 'greater than or equal' condition");
      case LESS_THAN_OR_EQUAL:
        throw new LabelIllegalArgumentException("Booleans and null cannot be used in 'less than or equal' condition");
      }
      
      if(operator == QueryOperator.EQUALS_IGNORE_CASE && !(value instanceof String))
      {
        throw new LabelIllegalArgumentException("Only string values can be used with 'equals ignore case' condition");
      }
      
      if(operator == QueryOperator.NOT_EQUALS_IGNORE_CASE && !(value instanceof String))
      {
        throw new LabelIllegalArgumentException("Only string values can be used with 'not equals ignore case' condition");
      }
      
      if(operator == QueryOperator.LIKE && !(value instanceof String))
      {
        throw new LabelIllegalArgumentException("Only string values can be used with 'like' condition");
      }
    }
    queryVariableValues.add(new QueryVariableValue(name, value, operator, localScope));
  }
  
  private boolean isBoolean(Object value) {
    if (value == null) {
      return false;
    }
    return Boolean.class.isAssignableFrom(value.getClass()) || boolean.class.isAssignableFrom(value.getClass());
  }

  protected void ensureVariablesInitialized() {
    if (!queryVariableValues.isEmpty()) {
      VariableTypes variableTypes = Context
              .getLabelServerConfiguration()
              .getVariableTypes();
      for(QueryVariableValue queryVariableValue : queryVariableValues) {
        queryVariableValue.initialize(variableTypes);
      }
    }
  }

  public List<QueryVariableValue> getQueryVariableValues() {
    return queryVariableValues;
  }   
}
