package com.winit.svr;

import java.util.Collection;
import java.util.Map;

import com.winit.label.model.RequestMessage;
import com.winit.label.model.ResponseMessage;
import com.winit.svr.label.Label;
import com.winit.svr.label.LabelQuery;
import com.winit.svr.label.LogisticType;
import com.winit.svr.label.LogisticTypeQuery;

public interface LabelService
{
	
	void saveLogisticType(LogisticType logisticType);
	
	void deleteLogisticType(String logisticTypeId);
	
	LogisticTypeQuery createLogisticTypeQuery();
	
	
	void saveLabel(Label label);
	
	void deleteLabel(String labelId);
	
	LabelQuery createLabelQuery();
	
	
	ResponseMessage generateLabel(RequestMessage requestMessage);
	
	
	  public void setLogisticTypeVariable(String logisticTypeId, String variableName, Object value);

	  /** set variables on a logistic type.  If the variable is not already existing, it will be created in the 
	   * most outer scope.  This means the process instance in case this logistic type is related to an 
	   * execution. */
	  void setLogisticTypeVariables(String logisticTypeId, Map<String, ? extends Object> variables);

	  /** set variable on a logistic type.  If the variable is not already existing, it will be created in the 
	   * logistic type.  */
	  void setLogisticTypeVariableLocal(String logisticTypeId, String variableName, Object value);

	  /** set variables on a logistic type.  If the variable is not already existing, it will be created in the 
	   * logistic type.  */
	  void setLogisticTypeVariablesLocal(String logisticTypeId, Map<String, ? extends Object> variables);

	  /** get a variables and search in the logistic type scope and if available also the execution scopes. */
	  Object getLogisticTypeVariable(String logisticTypeId, String variableName);
	  
	  /** checks whether or not the logistic type has a variable defined with the given name, in the logistic type scope and if available also the execution scopes. */
	  boolean hasLogisticTypeVariable(String logisticTypeId, String variableName);

	  /** checks whether or not the logistic type has a variable defined with the given name. */
	  Object getLogisticTypeVariableLocal(String logisticTypeId, String variableName);
	  
	  /** checks whether or not the logistic type has a variable defined with the given name, local logistic type scope only. */
	  boolean hasLogisticTypeVariableLocal(String logisticTypeId, String variableName);

	  /** get all variables and search in the logistic type scope and if available also the execution scopes. 
	   * If you have many variables and you only need a few, consider using {@link #getLogisticTypeVariables(String, Collection)} 
	   * for better performance.*/
	  Map<String, Object> getLogisticTypeVariables(String logisticTypeId);

	  /** get all variables and search only in the logistic type scope.
	  * If you have many logistic type local variables and you only need a few, consider using {@link #getLogisticTypeVariablesLocal(String, Collection)} 
	  * for better performance.*/
	  Map<String, Object> getLogisticTypeVariablesLocal(String logisticTypeId);

	  /** get values for all given variableNames and search only in the logistic type scope. */
	  Map<String, Object> getLogisticTypeVariables(String logisticTypeId, Collection<String> variableNames);

	  /** get a variable on a logistic type */
	  Map<String, Object> getLogisticTypeVariablesLocal(String logisticTypeId, Collection<String> variableNames);
	  
	  /**
	   * Removes the variable from the logistic type.
	   * When the variable does not exist, nothing happens.
	   */
	  void removeLogisticTypeVariable(String logisticTypeId, String variableName);

	  /**
	   * Removes the variable from the logistic type (not considering parent scopes).
	   * When the variable does not exist, nothing happens.
	   */
	  void removeLogisticTypeVariableLocal(String logisticTypeId, String variableName);

	  /**
	   * Removes all variables in the given collection from the logistic type.
	   * Non existing variable names are simply ignored.
	   */
	  void removeLogisticTypeVariables(String logisticTypeId, Collection<String> variableNames);

	  /**
	   * Removes all variables in the given collection from the logistic type (not considering parent scopes).
	   * Non existing variable names are simply ignored.
	   */
	  void removeLogisticTypeVariablesLocal(String logisticTypeId, Collection<String> variableNames);
}
