
package com.winit.svr.impl.cmd;

import java.io.Serializable;

import com.winit.svr.LabelObjectNotFoundException;
import com.winit.svr.LabelIllegalArgumentException;
import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.persistence.entity.LogisticTypeEntity;
import com.winit.svr.label.LogisticType;




public class HasLogisticTypeVariableCmd implements Command<Boolean>, Serializable {

  private static final long serialVersionUID = 1L;
  protected String logisticTypeId;
  protected String variableName;
  protected boolean isLocal;

  public HasLogisticTypeVariableCmd(String logisticTypeId, String variableName, boolean isLocal) {
    this.logisticTypeId = logisticTypeId;
    this.variableName = variableName;
    this.isLocal = isLocal;
  }

  public Boolean execute(CommandContext commandContext) {
    if(logisticTypeId == null) {
      throw new LabelIllegalArgumentException("logisticTypeId is null");
    }
    if(variableName == null) {
      throw new LabelIllegalArgumentException("variableName is null");
    }
    
    LogisticTypeEntity logisticType = commandContext
      .getLogisticTypeManager()
      .findByLogisticTypeId(logisticTypeId);
    
    if (logisticType==null) {
      throw new LabelObjectNotFoundException("logisticType "+logisticTypeId+" doesn't exist", LogisticType.class);
    }
    boolean hasVariable = false;
    
    if (isLocal) {
      hasVariable = logisticType.hasVariableLocal(variableName);
    } else {
      hasVariable = logisticType.hasVariable(variableName);
    }
    
    return hasVariable;
  }
}
