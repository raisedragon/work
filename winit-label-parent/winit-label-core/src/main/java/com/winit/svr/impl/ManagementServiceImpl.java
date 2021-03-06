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
package com.winit.svr.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.winit.svr.LabelIllegalArgumentException;
import com.winit.svr.ManagementService;
import com.winit.svr.event.EventLogEntry;
import com.winit.svr.impl.cmd.CustomSqlExecution;
import com.winit.svr.impl.cmd.DeleteEventLogEntry;
import com.winit.svr.impl.cmd.ExecuteCustomSqlCmd;
import com.winit.svr.impl.cmd.GetEventLogEntriesCmd;
import com.winit.svr.impl.cmd.GetPropertiesCmd;
import com.winit.svr.impl.cmd.GetTableCountCmd;
import com.winit.svr.impl.cmd.GetTableMetaDataCmd;
import com.winit.svr.impl.cmd.GetTableNameCmd;
import com.winit.svr.impl.db.DbSqlSession;
import com.winit.svr.impl.db.DbSqlSessionFactory;
import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandConfig;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.management.TableMetaData;
import com.winit.svr.management.TablePageQuery;


/**
 * @author Tom Baeyens
 * @author Joram Barrez
 * @author Falko Menge
 * @author Saeid Mizaei
 */
public class ManagementServiceImpl extends ServiceImpl implements ManagementService {

  public Map<String, Long> getTableCount() {
    return commandExecutor.execute(new GetTableCountCmd());
  }
  
  public String getTableName(Class<?> activitiEntityClass) {
    return commandExecutor.execute(new GetTableNameCmd(activitiEntityClass));    
  }
  
  public TableMetaData getTableMetaData(String tableName) {
    return commandExecutor.execute(new GetTableMetaDataCmd(tableName));
  }



  public TablePageQuery createTablePageQuery() {
    return new TablePageQueryImpl(commandExecutor);
  }
  


  public Map<String, String> getProperties() {
    return commandExecutor.execute(new GetPropertiesCmd());
  }

  public String databaseSchemaUpgrade(final Connection connection, final String catalog, final String schema) {
    CommandConfig config = commandExecutor.getDefaultConfig().transactionNotSupported();
    return commandExecutor.execute(config, new Command<String>(){
      public String execute(CommandContext commandContext) {
        DbSqlSessionFactory dbSqlSessionFactory = (DbSqlSessionFactory) commandContext.getSessionFactories().get(DbSqlSession.class);
        DbSqlSession dbSqlSession = new DbSqlSession(dbSqlSessionFactory, connection, catalog, schema);
        commandContext.getSessions().put(DbSqlSession.class, dbSqlSession);
        return dbSqlSession.dbSchemaUpdate();
      }
    });
  }
  
  public <T> T executeCommand(Command<T> command) {
    if (command == null) {
      throw new LabelIllegalArgumentException("The command is null");
    }
    return commandExecutor.execute(command);
  }
  
  public <T> T executeCommand(CommandConfig config, Command<T> command) {
    if (config == null) {
      throw new LabelIllegalArgumentException("The config is null");
    }
    if (command == null) {
      throw new LabelIllegalArgumentException("The command is null");
    }
    return commandExecutor.execute(config, command);
  }
  
  @Override
	public <MapperType, ResultType> ResultType executeCustomSql(CustomSqlExecution<MapperType, ResultType> customSqlExecution) {
  	Class<MapperType> mapperClass = customSqlExecution.getMapperClass();
  	return commandExecutor.execute(new ExecuteCustomSqlCmd<MapperType, ResultType>(mapperClass, customSqlExecution));
	}
  
  @Override
  public List<EventLogEntry> getEventLogEntries(Long startLogNr, Long pageSize) {
  	return commandExecutor.execute(new GetEventLogEntriesCmd(startLogNr, pageSize));
  }
  
  @Override
  public List<EventLogEntry> getEventLogEntriesByProcessInstanceId(String processInstanceId) {
    return commandExecutor.execute(new GetEventLogEntriesCmd(processInstanceId));
  }
  
  @Override
  public void deleteEventLogEntry(long logNr) {
  	commandExecutor.execute(new DeleteEventLogEntry(logNr));
  }

}
