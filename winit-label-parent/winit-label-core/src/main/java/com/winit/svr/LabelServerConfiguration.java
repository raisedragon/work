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

package com.winit.svr;

import java.io.InputStream;
import java.util.List;

import javax.sql.DataSource;


import com.winit.svr.impl.cfg.BeansConfigurationHelper;
import com.winit.svr.impl.cfg.StandaloneInMemLabelServerConfiguration;
import com.winit.svr.impl.cfg.StandaloneLabelServerConfiguration;
import com.winit.svr.label.LabelHandler;


/** Configuration information from which a process engine can be build.
 * 
 * <p>Most common is to create a process engine based on the default configuration file:
 * <pre>ProcessEngine processEngine = ProcessEngineConfiguration
 *   .createProcessEngineConfigurationFromResourceDefault()
 *   .buildProcessEngine();
 * </pre>
 * </p>
 * 
 * <p>To create a process engine programatic, without a configuration file, 
 * the first option is {@link #createLabelServerEngineConfiguration()}
 * <pre>ProcessEngine processEngine = ProcessEngineConfiguration
 *   .createStandaloneProcessEngineConfiguration()
 *   .buildProcessEngine();
 * </pre>
 * This creates a new process engine with all the defaults to connect to 
 * a remote h2 database (jdbc:h2:tcp://localhost/activiti) in standalone 
 * mode.  Standalone mode means that Activiti will manage the transactions 
 * on the JDBC connections that it creates.  One transaction per 
 * service method.
 * For a description of how to write the configuration files, see the 
 * userguide.
 * </p>
 * 
 * <p>The second option is great for testing: {@link #createStandalonInMemeProcessEngineConfiguration()}
 * <pre>ProcessEngine processEngine = ProcessEngineConfiguration
 *   .createStandaloneInMemProcessEngineConfiguration()
 *   .buildProcessEngine();
 * </pre>
 * This creates a new process engine with all the defaults to connect to 
 * an memory h2 database (jdbc:h2:tcp://localhost/activiti) in standalone 
 * mode.  The DB schema strategy default is in this case <code>create-drop</code>.  
 * Standalone mode means that Activiti will manage the transactions 
 * on the JDBC connections that it creates.  One transaction per 
 * service method.
 * </p>
 * 
 * <p>On all forms of creating a process engine, you can first customize the configuration 
 * before calling the {@link #buildLabelServer()} method by calling any of the 
 * setters like this:
 * <pre>ProcessEngine processEngine = ProcessEngineConfiguration
 *   .createProcessEngineConfigurationFromResourceDefault()
 *   .setMailServerHost("gmail.com")
 *   .setJdbcUsername("mickey")
 *   .setJdbcPassword("mouse")
 *   .buildProcessEngine();
 * </pre>
 * </p>
 * 
 * @see LabelServers 
 * @author Tom Baeyens
 */
public abstract class LabelServerConfiguration implements LabelServerServices {
  
  /** Checks the version of the DB schema against the library when 
   * the process engine is being created and throws an exception
   * if the versions don't match. */
  public static final String DB_SCHEMA_UPDATE_FALSE = "false";
  
  /** Creates the schema when the process engine is being created and 
   * drops the schema when the process engine is being closed. */
  public static final String DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";

  /** Upon building of the process engine, a check is performed and 
   * an update of the schema is performed if it is necessary. */
  public static final String DB_SCHEMA_UPDATE_TRUE = "true";
  
  /** The tenant id indicating 'no tenant' */
  public static final String NO_TENANT_ID = "";

  protected String labelServerName = LabelServers.NAME_DEFAULT;
  protected int idBlockSize = 2500;
  protected boolean jobExecutorActivate;

  protected String mailServerHost = "localhost";
  protected String mailServerUsername; // by default no name and password are provided, which 
  protected String mailServerPassword; // means no authentication for mail server
  protected int mailServerPort = 25;
  protected boolean useSSL = false;
  protected boolean useTLS = false;
  protected String mailServerDefaultFrom = "activiti@localhost";
  protected String mailSessionJndi;

  protected String databaseType;
  protected String databaseSchemaUpdate = DB_SCHEMA_UPDATE_FALSE;
  protected String jdbcDriver = "org.h2.Driver";
  protected String jdbcUrl = "jdbc:h2:tcp://localhost/activiti";
  protected String jdbcUsername = "sa";
  protected String jdbcPassword = "";
  protected String dataSourceJndiName = null;
  protected boolean isDbIdentityUsed = true;
  protected boolean isDbHistoryUsed = true;
  protected int jdbcMaxActiveConnections;
  protected int jdbcMaxIdleConnections;
  protected int jdbcMaxCheckoutTime;
  protected int jdbcMaxWaitTime;
  protected boolean jdbcPingEnabled = false;
  protected String jdbcPingQuery = null;
  protected int jdbcPingConnectionNotUsedFor;
  protected int jdbcDefaultTransactionIsolationLevel;
  protected DataSource dataSource;
  protected boolean transactionsExternallyManaged = false;
  
  protected String jpaPersistenceUnitName;
  protected Object jpaEntityManagerFactory;
  protected boolean jpaHandleTransaction;
  protected boolean jpaCloseEntityManager;

  /** define the default wait time for a failed job in seconds */
  protected int defaultFailedJobWaitTime = 10;
  /** define the default wait time for a failed async job in seconds */
  protected int asyncFailedJobWaitTime = 10;


  /**
   * Allows configuring a database table prefix which is used for all runtime operations of the process engine.
   * For example, if you specify a prefix named 'PRE1.', activiti will query for executions in a table named
   * 'PRE1.ACT_RU_EXECUTION_'. 
   * 
   * <p />
   * <strong>NOTE: the prefix is not respected by automatic database schema management. If you use 
   * {@link LabelServerConfiguration#DB_SCHEMA_UPDATE_CREATE_DROP} 
   * or {@link LabelServerConfiguration#DB_SCHEMA_UPDATE_TRUE}, activiti will create the database tables 
   * using the default names, regardless of the prefix configured here.</strong>  
   * 
   * @since 5.9
   */
  protected String databaseTablePrefix = "";

  /**
   * database catalog to use
   */
  protected String databaseCatalog = "";

  /**
   * In some situations you want to set the schema to use for table checks / generation if the database metadata
   * doesn't return that correctly, see https://jira.codehaus.org/browse/ACT-1220,
   * https://jira.codehaus.org/browse/ACT-1062
   */
  protected String databaseSchema = null;
  
  /**
   * Set to true in case the defined databaseTablePrefix is a schema-name, instead of an actual table name
   * prefix. This is relevant for checking if Activiti-tables exist, the databaseTablePrefix will not be used here
   * - since the schema is taken into account already, adding a prefix for the table-check will result in wrong table-names.
   * 
   *  @since 5.15
   */
  protected boolean tablePrefixIsSchema = false;
  
  protected boolean isCreateDiagramOnDeploy = true;
  
  protected String xmlEncoding = "UTF-8";
  
  protected String defaultCamelContext = "camelContext";
  
  protected String activityFontName = "Arial";
  protected String labelFontName = "Arial";
  
  protected ClassLoader classLoader;
  protected LabelServerLifecycleListener labelServerLifecycleListener;
  
  protected List<LabelHandler> labelHanlers = null; 

  /** use one of the static createXxxx methods instead */
  protected LabelServerConfiguration() {
  }

  public abstract LabelServer buildLabelServer();
  
  public static LabelServerConfiguration createLabelServerConfigurationFromResourceDefault() {
    return createLabelServerConfigurationFromResource("server.cfg.xml", "labelServerConfiguration");
  }

  public static LabelServerConfiguration createLabelServerConfigurationFromResource(String resource) {
    return createLabelServerConfigurationFromResource(resource, "labelServerConfiguration");
  }

  public static LabelServerConfiguration createLabelServerConfigurationFromResource(String resource, String beanName) {
    return BeansConfigurationHelper.parseLabelServerConfigurationFromResource(resource, beanName);
  }
  
  public static LabelServerConfiguration createLabelServerConfigurationFromInputStream(InputStream inputStream) {
    return createLabelServerConfigurationFromInputStream(inputStream, "labelServerConfiguration");
  }

  public static LabelServerConfiguration createLabelServerConfigurationFromInputStream(InputStream inputStream, String beanName) {
    return BeansConfigurationHelper.parseLabelServerConfigurationFromInputStream(inputStream, beanName);
  }

  public static LabelServerConfiguration createLabelServerEngineConfiguration() {
    return new StandaloneLabelServerConfiguration();
  }

  public static LabelServerConfiguration createStandaloneInMemLabelServerConfiguration() {
    return new StandaloneInMemLabelServerConfiguration();
  }

// TODO add later when we have test coverage for this
//  public static ProcessEngineConfiguration createJtaProcessEngineConfiguration() {
//    return new JtaProcessEngineConfiguration();
//  }
  

  // getters and setters //////////////////////////////////////////////////////
  
  public String getProcessEngineName() {
    return labelServerName;
  }

  public LabelServerConfiguration setProcessEngineName(String labelServerName) {
    this.labelServerName = labelServerName;
    return this;
  }
  
  public int getIdBlockSize() {
    return idBlockSize;
  }
  
  public LabelServerConfiguration setIdBlockSize(int idBlockSize) {
    this.idBlockSize = idBlockSize;
    return this;
  }
  
  
  public String getMailServerHost() {
    return mailServerHost;
  }
  
  public LabelServerConfiguration setMailServerHost(String mailServerHost) {
    this.mailServerHost = mailServerHost;
    return this;
  }
  
  public String getMailServerUsername() {
    return mailServerUsername;
  }
  
  public LabelServerConfiguration setMailServerUsername(String mailServerUsername) {
    this.mailServerUsername = mailServerUsername;
    return this;
  }
  
  public String getMailServerPassword() {
    return mailServerPassword;
  }
  
  public LabelServerConfiguration setMailServerPassword(String mailServerPassword) {
    this.mailServerPassword = mailServerPassword;
    return this;
  }

  public String getMailSesionJndi() {
    return mailSessionJndi;
  }
  
  public LabelServerConfiguration setMailSessionJndi(String mailSessionJndi) {
    this.mailSessionJndi = mailSessionJndi;
    return this;
  }

  public int getMailServerPort() {
    return mailServerPort;
  }
  
  public LabelServerConfiguration setMailServerPort(int mailServerPort) {
    this.mailServerPort = mailServerPort;
    return this;
  }
  
  public boolean getMailServerUseSSL() {
	  return useSSL;
  }
  
  public LabelServerConfiguration setMailServerUseSSL(boolean useSSL) {
	  this.useSSL = useSSL;
	  return this;
  }
  
  public boolean getMailServerUseTLS() {
    return useTLS;
  }
  
  public LabelServerConfiguration setMailServerUseTLS(boolean useTLS) {
    this.useTLS = useTLS;
    return this;
  }
  
  public String getMailServerDefaultFrom() {
    return mailServerDefaultFrom;
  }
  
  public LabelServerConfiguration setMailServerDefaultFrom(String mailServerDefaultFrom) {
    this.mailServerDefaultFrom = mailServerDefaultFrom;
    return this;
  }
  
  public String getDatabaseType() {
    return databaseType;
  }
  
  public LabelServerConfiguration setDatabaseType(String databaseType) {
    this.databaseType = databaseType;
    return this;
  }

  public String getDatabaseSchemaUpdate() {
    return databaseSchemaUpdate;
  }
  
  public LabelServerConfiguration setDatabaseSchemaUpdate(String databaseSchemaUpdate) {
    this.databaseSchemaUpdate = databaseSchemaUpdate;
    return this;
  }
  
  public DataSource getDataSource() {
    return dataSource;
  }
  
  public LabelServerConfiguration setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    return this;
  }
  
  public String getJdbcDriver() {
    return jdbcDriver;
  }
  
  public LabelServerConfiguration setJdbcDriver(String jdbcDriver) {
    this.jdbcDriver = jdbcDriver;
    return this;
  }

  public String getJdbcUrl() {
    return jdbcUrl;
  }

  public LabelServerConfiguration setJdbcUrl(String jdbcUrl) {
    this.jdbcUrl = jdbcUrl;
    return this;
  }
  
  public String getJdbcUsername() {
    return jdbcUsername;
  }
 
  public LabelServerConfiguration setJdbcUsername(String jdbcUsername) {
    this.jdbcUsername = jdbcUsername;
    return this;
  }
  
  public String getJdbcPassword() {
    return jdbcPassword;
  }
 
  public LabelServerConfiguration setJdbcPassword(String jdbcPassword) {
    this.jdbcPassword = jdbcPassword;
    return this;
  }
  
  public boolean isTransactionsExternallyManaged() {
    return transactionsExternallyManaged;
  }
  
  public LabelServerConfiguration setTransactionsExternallyManaged(boolean transactionsExternallyManaged) {
    this.transactionsExternallyManaged = transactionsExternallyManaged;
    return this;
  }
  
  
  public boolean isDbIdentityUsed() {
    return isDbIdentityUsed;
  }
  
  public LabelServerConfiguration setDbIdentityUsed(boolean isDbIdentityUsed) {
    this.isDbIdentityUsed = isDbIdentityUsed;
    return this;
  }
  
  public boolean isDbHistoryUsed() {
    return isDbHistoryUsed;
  }
  
  public LabelServerConfiguration setDbHistoryUsed(boolean isDbHistoryUsed) {
    this.isDbHistoryUsed = isDbHistoryUsed;
    return this;
  }
  
  public int getJdbcMaxActiveConnections() {
    return jdbcMaxActiveConnections;
  }
  
  public LabelServerConfiguration setJdbcMaxActiveConnections(int jdbcMaxActiveConnections) {
    this.jdbcMaxActiveConnections = jdbcMaxActiveConnections;
    return this;
  }
  
  public int getJdbcMaxIdleConnections() {
    return jdbcMaxIdleConnections;
  }
  
  public LabelServerConfiguration setJdbcMaxIdleConnections(int jdbcMaxIdleConnections) {
    this.jdbcMaxIdleConnections = jdbcMaxIdleConnections;
    return this;
  }
  
  public int getJdbcMaxCheckoutTime() {
    return jdbcMaxCheckoutTime;
  }
  
  public LabelServerConfiguration setJdbcMaxCheckoutTime(int jdbcMaxCheckoutTime) {
    this.jdbcMaxCheckoutTime = jdbcMaxCheckoutTime;
    return this;
  }
 
  public int getJdbcMaxWaitTime() {
    return jdbcMaxWaitTime;
  }
  
  public LabelServerConfiguration setJdbcMaxWaitTime(int jdbcMaxWaitTime) {
    this.jdbcMaxWaitTime = jdbcMaxWaitTime;
    return this;
  }
  
  public boolean isJdbcPingEnabled() {
    return jdbcPingEnabled;
  }

  public LabelServerConfiguration setJdbcPingEnabled(boolean jdbcPingEnabled) {
    this.jdbcPingEnabled = jdbcPingEnabled;
    return this;
  }

  public String getJdbcPingQuery() {
      return jdbcPingQuery;
  }

  public LabelServerConfiguration setJdbcPingQuery(String jdbcPingQuery) {
    this.jdbcPingQuery = jdbcPingQuery;
    return this;
  }

  public int getJdbcPingConnectionNotUsedFor() {
      return jdbcPingConnectionNotUsedFor;
  }

  public LabelServerConfiguration setJdbcPingConnectionNotUsedFor(int jdbcPingNotUsedFor) {
    this.jdbcPingConnectionNotUsedFor = jdbcPingNotUsedFor;
    return this;
  }

  public int getJdbcDefaultTransactionIsolationLevel() {
    return jdbcDefaultTransactionIsolationLevel;
  }

  public LabelServerConfiguration setJdbcDefaultTransactionIsolationLevel(int jdbcDefaultTransactionIsolationLevel) {
    this.jdbcDefaultTransactionIsolationLevel = jdbcDefaultTransactionIsolationLevel;
    return this;
  }

  public boolean isJobExecutorActivate() {
    return jobExecutorActivate;
  }
  
  public LabelServerConfiguration setJobExecutorActivate(boolean jobExecutorActivate) {
    this.jobExecutorActivate = jobExecutorActivate;
    return this;
  }
  
  public ClassLoader getClassLoader() {
    return classLoader;
  }
  
  public LabelServerConfiguration setClassLoader(ClassLoader classLoader) {
    this.classLoader = classLoader;
    return this;
  }

  public Object getJpaEntityManagerFactory() {
    return jpaEntityManagerFactory;
  }

  public LabelServerConfiguration setJpaEntityManagerFactory(Object jpaEntityManagerFactory) {
    this.jpaEntityManagerFactory = jpaEntityManagerFactory;
    return this;
  }

  public boolean isJpaHandleTransaction() {
    return jpaHandleTransaction;
  }

  public LabelServerConfiguration setJpaHandleTransaction(boolean jpaHandleTransaction) {
    this.jpaHandleTransaction = jpaHandleTransaction;
    return this;
  }
  
  public boolean isJpaCloseEntityManager() {
    return jpaCloseEntityManager;
  }

  public LabelServerConfiguration setJpaCloseEntityManager(boolean jpaCloseEntityManager) {
    this.jpaCloseEntityManager = jpaCloseEntityManager;
    return this;
  }

  public String getJpaPersistenceUnitName() {
    return jpaPersistenceUnitName;
  }

  public LabelServerConfiguration setJpaPersistenceUnitName(String jpaPersistenceUnitName) {
    this.jpaPersistenceUnitName = jpaPersistenceUnitName;
    return this;
  }

  public String getDataSourceJndiName() {
    return dataSourceJndiName;
  }

  public LabelServerConfiguration setDataSourceJndiName(String dataSourceJndiName) {
    this.dataSourceJndiName = dataSourceJndiName;
    return this;
  }

  public String getDefaultCamelContext() {
    return defaultCamelContext;
  }
  
  public LabelServerConfiguration setDefaultCamelContext(String defaultCamelContext) {
    this.defaultCamelContext = defaultCamelContext;
    return this;
  }
  
  public boolean isCreateDiagramOnDeploy() {
    return isCreateDiagramOnDeploy;
  }

  public LabelServerConfiguration setCreateDiagramOnDeploy(boolean createDiagramOnDeploy) {
    this.isCreateDiagramOnDeploy = createDiagramOnDeploy;
    return this;
  }

  public String getActivityFontName() {
    return activityFontName;
  }

  public LabelServerConfiguration setActivityFontName(String activityFontName) {
    this.activityFontName = activityFontName;
    return this;
  }
  
  public LabelServerConfiguration setProcessEngineLifecycleListener(LabelServerLifecycleListener labelServerLifecycleListener) {
    this.labelServerLifecycleListener = labelServerLifecycleListener;
    return this;
  }
  
  public LabelServerLifecycleListener getProcessEngineLifecycleListener() {
    return labelServerLifecycleListener;
  }

  public String getLabelFontName() {
    return labelFontName;
  }

  public LabelServerConfiguration setLabelFontName(String labelFontName) {
    this.labelFontName = labelFontName;
    return this;
  }
    
  public String getDatabaseTablePrefix() {
    return databaseTablePrefix;
  }
  
  public LabelServerConfiguration setDatabaseTablePrefix(String databaseTablePrefix) {
    this.databaseTablePrefix = databaseTablePrefix;
    return this;
  }
  
  public LabelServerConfiguration setTablePrefixIsSchema(boolean tablePrefixIsSchema) {
	  this.tablePrefixIsSchema = tablePrefixIsSchema;
	  return this;
  }
  
  public boolean isTablePrefixIsSchema() {
	  return tablePrefixIsSchema;
  }

  public String getDatabaseCatalog() {
    return databaseCatalog;
  }

  public LabelServerConfiguration setDatabaseCatalog(String databaseCatalog) {
    this.databaseCatalog = databaseCatalog;
    return this;
  }

  public String getDatabaseSchema() {
    return databaseSchema;
  }
  
  public LabelServerConfiguration setDatabaseSchema(String databaseSchema) {
    this.databaseSchema = databaseSchema;
    return this;
  }
  
  public String getXmlEncoding() {
    return xmlEncoding;
  }

  public LabelServerConfiguration setXmlEncoding(String xmlEncoding) {
    this.xmlEncoding = xmlEncoding;
    return this;
  }



  public int getDefaultFailedJobWaitTime() {
    return defaultFailedJobWaitTime;
  }

  public LabelServerConfiguration setDefaultFailedJobWaitTime(int defaultFailedJobWaitTime) {
    this.defaultFailedJobWaitTime = defaultFailedJobWaitTime;
    return this;
  }

  public int getAsyncFailedJobWaitTime() {
    return asyncFailedJobWaitTime;
  }

  public LabelServerConfiguration setAsyncFailedJobWaitTime(int asyncFailedJobWaitTime) {
    this.asyncFailedJobWaitTime = asyncFailedJobWaitTime;
    return this;
  }
  
	public List<LabelHandler> getLabelHanlers()
	{
		return labelHanlers;
	}

	public void setLabelHanlers(List<LabelHandler> labelHanlers)
	{
		this.labelHanlers = labelHanlers;
	}
}
