package com.winit.svr.impl.cmd; 


import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandContext;

public class DeletePropertyCmd  implements Command<Void>{
  
  private static final long serialVersionUID = 1L;

  private final String name;

  public DeletePropertyCmd(String name) {
    this.name=name;
  }
  
  public Void execute(CommandContext commandContext) {
	  commandContext.getPropertyEntityManager().deletePropertyEntity(name);
	  return null;
  }
  
  
}
