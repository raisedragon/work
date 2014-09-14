package com.winit.svr.impl;

import com.winit.svr.identity.Group;
import com.winit.svr.identity.NativeGroupQuery;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.interceptor.CommandExecutor;

import java.util.List;
import java.util.Map;

public class NativeGroupQueryImpl extends AbstractNativeQuery<NativeGroupQuery, Group> implements NativeGroupQuery {

  private static final long serialVersionUID = 1L;
  
  public NativeGroupQueryImpl(CommandContext commandContext) {
    super(commandContext);
  }

  public NativeGroupQueryImpl(CommandExecutor commandExecutor) {
    super(commandExecutor);
  }


 //results ////////////////////////////////////////////////////////////////
  
  public List<Group> executeList(CommandContext commandContext, Map<String, Object> parameterMap, int firstResult, int maxResults) {
    return commandContext
      .getGroupIdentityManager()
      .findGroupsByNativeQuery(parameterMap, firstResult, maxResults);
  }
  
  public long executeCount(CommandContext commandContext, Map<String, Object> parameterMap) {
    return commandContext
      .getGroupIdentityManager()
      .findGroupCountByNativeQuery(parameterMap);
  }

}