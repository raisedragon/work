package com.winit.svr.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



import com.winit.label.model.RequestMessage;
import com.winit.label.model.ResponseMessage;
import com.winit.svr.LabelIllegalArgumentException;
import com.winit.svr.LabelService;
import com.winit.svr.impl.cmd.CreateLabelQueryCmd;
import com.winit.svr.impl.cmd.CreateLogisticTypeQueryCmd;
import com.winit.svr.impl.cmd.DeleteLabelCmd;
import com.winit.svr.impl.cmd.DeleteLogisticTypeCmd;
import com.winit.svr.impl.cmd.GenerateLabelCmd;
import com.winit.svr.impl.cmd.GetLogisticTypeVariableCmd;
import com.winit.svr.impl.cmd.GetLogisticTypeVariablesCmd;
import com.winit.svr.impl.cmd.HasLogisticTypeVariableCmd;
import com.winit.svr.impl.cmd.RemoveLogisticTypeVariablesCmd;
import com.winit.svr.impl.cmd.SaveLabelCmd;
import com.winit.svr.impl.cmd.SaveLogisticTypeCmd;
import com.winit.svr.impl.cmd.SetLogisticTypeVariablesCmd;
import com.winit.svr.label.Label;
import com.winit.svr.label.LabelQuery;
import com.winit.svr.label.LogisticType;
import com.winit.svr.label.LogisticTypeQuery;

public class LabelServiceImpl extends ServiceImpl implements LabelService
{


	@Override
	public void saveLogisticType(LogisticType logisticType)
	{
		  commandExecutor.execute(new SaveLogisticTypeCmd(logisticType));
		
	}

	@Override
	public void deleteLogisticType(String logisticTypeId)
	{
		 commandExecutor.execute(new DeleteLogisticTypeCmd(logisticTypeId));
		
	}

	@Override
	public LogisticTypeQuery createLogisticTypeQuery()
	{
		return  commandExecutor.execute(new CreateLogisticTypeQueryCmd());
	}

	@Override
	public ResponseMessage generateLabel(RequestMessage requestMessage)
	{
		return  commandExecutor.execute(new GenerateLabelCmd(requestMessage));
	}

	@Override
	public void saveLabel(Label label)
	{
		commandExecutor.execute(new SaveLabelCmd(label));
		
	}

	@Override
	public void deleteLabel(String labelId)
	{
		commandExecutor.execute(new DeleteLabelCmd(labelId));
		
		
	}

	@Override
	public LabelQuery createLabelQuery()
	{
		return  commandExecutor.execute(new CreateLabelQueryCmd());
	}
	
	@Override
	  public Map<String, Object> getLogisticTypeVariables(String logisticTypeId) {
	    return commandExecutor.execute(new GetLogisticTypeVariablesCmd(logisticTypeId, null, false));
	  }

	  public Map<String, Object> getLogisticTypeVariablesLocal(String logisticTypeId) {
	    return commandExecutor.execute(new GetLogisticTypeVariablesCmd(logisticTypeId, null, true));
	  }

	  public Map<String, Object> getLogisticTypeVariables(String logisticTypeId, Collection<String> variableNames) {
	    return commandExecutor.execute(new GetLogisticTypeVariablesCmd(logisticTypeId, variableNames, false));
	  }

	  public Map<String, Object> getLogisticTypeVariablesLocal(String logisticTypeId, Collection<String> variableNames) {
	    return commandExecutor.execute(new GetLogisticTypeVariablesCmd(logisticTypeId, variableNames, true));
	  }

	  public Object getLogisticTypeVariable(String logisticTypeId, String variableName) {
	    return commandExecutor.execute(new GetLogisticTypeVariableCmd(logisticTypeId, variableName, false));
	  }
	  
	  
	  public Object getLogisticTypeVariableLocal(String logisticTypeId, String variableName) {
	    return commandExecutor.execute(new GetLogisticTypeVariableCmd(logisticTypeId, variableName, true));
	  }
	  
	  
	  public void setLogisticTypeVariable(String logisticTypeId, String variableName, Object value) {
	    if(variableName == null) {
	      throw new LabelIllegalArgumentException("variableName is null");
	    }
	    Map<String, Object> variables = new HashMap<String, Object>();
	    variables.put(variableName, value);
	    commandExecutor.execute(new SetLogisticTypeVariablesCmd(logisticTypeId, variables, false));
	  }
	  
	  public void setLogisticTypeVariableLocal(String logisticTypeId, String variableName, Object value) {
	    if(variableName == null) {
	      throw new LabelIllegalArgumentException("variableName is null");
	    }
	    Map<String, Object> variables = new HashMap<String, Object>();
	    variables.put(variableName, value);
	    commandExecutor.execute(new SetLogisticTypeVariablesCmd(logisticTypeId, variables, true));
	  }

	  public void setLogisticTypeVariables(String logisticTypeId, Map<String, ? extends Object> variables) {
	    commandExecutor.execute(new SetLogisticTypeVariablesCmd(logisticTypeId, variables, false));
	  }

	  public void setLogisticTypeVariablesLocal(String logisticTypeId, Map<String, ? extends Object> variables) {
	    commandExecutor.execute(new SetLogisticTypeVariablesCmd(logisticTypeId, variables, true));
	  }

	  public void removeLogisticTypeVariable(String logisticTypeId, String variableName) {
	    Collection<String> variableNames = new ArrayList<String>();
	    variableNames.add(variableName);
	    commandExecutor.execute(new RemoveLogisticTypeVariablesCmd(logisticTypeId, variableNames, false));
	  }

	  public void removeLogisticTypeVariableLocal(String logisticTypeId, String variableName) {
	    Collection<String> variableNames = new ArrayList<String>(1);
	    variableNames.add(variableName);
	    commandExecutor.execute(new RemoveLogisticTypeVariablesCmd(logisticTypeId, variableNames, true));
	  }

	  public void removeLogisticTypeVariables(String logisticTypeId, Collection<String> variableNames) {
	    commandExecutor.execute(new RemoveLogisticTypeVariablesCmd(logisticTypeId, variableNames, false));
	  }

	  public void removeLogisticTypeVariablesLocal(String logisticTypeId, Collection<String> variableNames) {
	    commandExecutor.execute(new RemoveLogisticTypeVariablesCmd(logisticTypeId, variableNames, true));
	  }

	@Override
	public boolean hasLogisticTypeVariable(String logisticTypeId, String variableName)
	{
		  return commandExecutor.execute(new HasLogisticTypeVariableCmd(logisticTypeId, variableName, false));
	}

	@Override
	public boolean hasLogisticTypeVariableLocal(String logisticTypeId, String variableName)
	{
		  return commandExecutor.execute(new HasLogisticTypeVariableCmd(logisticTypeId, variableName, true));
	}




}
