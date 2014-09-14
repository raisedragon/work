package com.winit.svr.impl.cfg;

import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandConfig;
import com.winit.svr.impl.interceptor.CommandExecutor;
import com.winit.svr.impl.interceptor.CommandInterceptor;

/**
 * Command executor that passes commands to the first interceptor in the chain.
 * If no {@link CommandConfig} is passed, the default configuration will be used.  
 * 
 * @author Marcus Klimstra (CGI)
 */
public class CommandExecutorImpl implements CommandExecutor {

  private final CommandConfig defaultConfig;
  private final CommandInterceptor first;
  
  public CommandExecutorImpl(CommandConfig defaultConfig, CommandInterceptor first) {
    this.defaultConfig = defaultConfig;
    this.first = first;
  }
  
  public CommandInterceptor getFirst() {
    return first;
  }

  @Override
  public CommandConfig getDefaultConfig() {
    return defaultConfig;
  }
  
  @Override
  public <T> T execute(Command<T> command) {
    return execute(defaultConfig, command);
  }

  @Override
  public <T> T execute(CommandConfig config, Command<T> command) {
    return first.execute(config, command);
  }

}
