package com.winit.svr.impl.cmd;

import java.io.Serializable;

import com.winit.svr.ActivitiException;
import com.winit.svr.ActivitiIllegalArgumentException;
import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandContext;


public class GetTableNameCmd implements Command<String>, Serializable {

  private static final long serialVersionUID = 1L;

  private Class<?> entityClass;

  public GetTableNameCmd(Class< ? > entityClass) {
    this.entityClass = entityClass;
  }

  public String execute(CommandContext commandContext) {
    if(entityClass == null) {
      throw new ActivitiIllegalArgumentException("entityClass is null");
    }
    return commandContext
      .getTableDataManager()
      .getTableName(entityClass, true);
  }

}
