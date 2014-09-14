package com.winit.svr.impl.cmd; 

import java.util.Collection;

import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.persistence.entity.LogisticTypeEntity;


/**
 * @author roman.smirnov
 * @author Joram Barrez
 */
public class RemoveLogisticTypeVariablesCmd  implements Command<Void>{
  
  private static final long serialVersionUID = 1L;

  private final Collection<String> variableNames;
  private final boolean isLocal;
  private final String logisticTypeId;

  public RemoveLogisticTypeVariablesCmd(String logisticTypeId, Collection<String> variableNames, boolean isLocal) {
    this.variableNames = variableNames;
    this.isLocal = isLocal;
    this.logisticTypeId=logisticTypeId;
  }
  
  public Void execute(CommandContext commandContext) {
	  LogisticTypeEntity logisticType = commandContext.getLogisticTypeManager().findByLogisticTypeId(logisticTypeId);
    if (isLocal) {
      logisticType.removeVariablesLocal(variableNames);
    } else {
      logisticType.removeVariables(variableNames);
    }
    
    return null;
  }
  
  
}
