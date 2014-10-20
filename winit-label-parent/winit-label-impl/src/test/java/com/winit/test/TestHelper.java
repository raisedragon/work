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

package com.winit.test;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import junit.framework.AssertionFailedError;

import org.slf4j.LoggerFactory;

import com.winit.svr.LabelException;
import com.winit.svr.LabelObjectNotFoundException;
import com.winit.svr.LabelServer;
import com.winit.svr.LabelServerConfiguration;
import com.winit.svr.impl.LabelServerImpl;
import com.winit.svr.impl.cfg.LabelServerConfigurationImpl;
import com.winit.svr.impl.db.DbSqlSession;
import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.util.ReflectUtil;


/**
 * @author Tom Baeyens
 * @author Joram Barrez
 */
public abstract class TestHelper {
  
  private static org.slf4j.Logger log = LoggerFactory.getLogger(TestHelper.class);

  public static final String EMPTY_LINE = "                                                                                           ";

  public static final List<String> TABLENAMES_EXCLUDED_FROM_DB_CLEAN_CHECK = Arrays.asList(
    "ACT_GE_PROPERTY"
  );

  static Map<String, LabelServer> labelServers = new HashMap<String, LabelServer>(); 
  
  
  
  
  
  // Engine startup and shutdown helpers  ///////////////////////////////////////////////////

  public static LabelServer getLabelServer(String configurationResource) {
	LabelServer labelServer = labelServers.get(configurationResource);
	if (labelServer == null) {
	  log.debug("==== BUILDING LABLE SERVER ========================================================================");
	  labelServer = LabelServerConfiguration
		.createLabelServerConfigurationFromResource(
			configurationResource).buildLabelServer();
		log.debug("==== PROCESS LABLE SERVER =========================================================================");
		labelServers.put(configurationResource, labelServer);
	  }
	  return labelServer;
	}

  public static void closeProcessEngines() {
    for (LabelServer labelServer : labelServers.values()) {
	  labelServer.close();
	}
	labelServers.clear();
  }

  /** 
   * Each test is assumed to clean up all DB content it entered.
   * After a test method executed, this method scans all tables to see if the DB is completely clean. 
   * It throws AssertionFailed in case the DB is not clean.
   * If the DB is not clean, it is cleaned by performing a create a drop. 
   */
  public static void assertAndEnsureCleanDb(LabelServer labelServer) {
    log.debug("verifying that db is clean after test");
    Map<String, Long> tableCounts = labelServer.getManagementService().getTableCount();
    StringBuilder outputMessage = new StringBuilder();
    for (String tableName : tableCounts.keySet()) {
      if (!TABLENAMES_EXCLUDED_FROM_DB_CLEAN_CHECK.contains(tableName)) {
        Long count = tableCounts.get(tableName);
        if (count!=0L) {
          outputMessage.append("  "+tableName + ": " + count + " record(s) ");
        }
      }
    }
    if (outputMessage.length() > 0) {
      outputMessage.insert(0, "DB NOT CLEAN: \n");
      log.error(EMPTY_LINE);
      log.error(outputMessage.toString());

      ((LabelServerImpl)labelServer)
      .getLabelServerConfiguration().getCommandExecutor()
        .execute(new Command<Object>() {
          public Object execute(CommandContext commandContext) {
            DbSqlSession dbSqlSession = commandContext.getSession(DbSqlSession.class);
            dbSqlSession.dbSchemaDrop();
            dbSqlSession.dbSchemaCreate();
            return null;
          }
        });
      
      throw new AssertionError(outputMessage.toString());
    }
  }
  
  // Mockup support ////////////////////////////////////////////////////////
  
  
  
  // Helper method for working with timers ///////////////////////////////////////////////////
  
  private static class InteruptTask extends TimerTask {
    protected boolean timeLimitExceeded = false;
    protected Thread thread;
    public InteruptTask(Thread thread) {
      this.thread = thread;
    }
    public boolean isTimeLimitExceeded() {
      return timeLimitExceeded;
    }
    public void run() {
      timeLimitExceeded = true;
      thread.interrupt();
    }
  }

}
