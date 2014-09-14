package com.winit.svr.impl;

import com.winit.svr.identity.NativeUserQuery;
import com.winit.svr.identity.User;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.interceptor.CommandExecutor;

import java.util.List;
import java.util.Map;

public class NativeUserQueryImpl extends AbstractNativeQuery<NativeUserQuery, User> implements NativeUserQuery {

  private static final long serialVersionUID = 1L;
  
  public NativeUserQueryImpl(CommandContext commandContext) {
    super(commandContext);
  }

  public NativeUserQueryImpl(CommandExecutor commandExecutor) {
    super(commandExecutor);
  }


 //results ////////////////////////////////////////////////////////////////
  
  public List<User> executeList(CommandContext commandContext, Map<String, Object> parameterMap, int firstResult, int maxResults) {
    return commandContext
      .getUserIdentityManager()
      .findUsersByNativeQuery(parameterMap, firstResult, maxResults);
  }
  
  public long executeCount(CommandContext commandContext, Map<String, Object> parameterMap) {
    return commandContext
      .getUserIdentityManager()
      .findUserCountByNativeQuery(parameterMap);
  }

}