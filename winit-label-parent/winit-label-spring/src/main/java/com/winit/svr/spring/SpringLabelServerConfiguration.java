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

package com.winit.svr.spring;

import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import com.winit.svr.LabelException;
import com.winit.svr.LabelServer;
import com.winit.svr.LabelServerConfiguration;
import com.winit.svr.LabelServers;
import com.winit.svr.impl.cfg.LabelServerConfigurationImpl;
import com.winit.svr.impl.cfg.StandaloneLabelServerConfiguration;
import com.winit.svr.impl.interceptor.CommandConfig;
import com.winit.svr.impl.interceptor.CommandInterceptor;
import com.winit.svr.impl.variable.EntityManagerSession;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author Tom Baeyens
 * @author David Syer
 * @author Joram Barrez
 * @author Tiese Barrell
 */
public class SpringLabelServerConfiguration extends LabelServerConfigurationImpl implements ApplicationContextAware {

  protected PlatformTransactionManager transactionManager;
  protected String deploymentName = "SpringAutoDeployment";
  protected Resource[] deploymentResources = new Resource[0];
  protected String deploymentMode = "default";
  protected ApplicationContext applicationContext;
  protected Integer transactionSynchronizationAdapterOrder = null;
//  private Collection<AutoDeploymentStrategy> deploymentStrategies = new ArrayList<AutoDeploymentStrategy>();

  public SpringLabelServerConfiguration() {
    this.transactionsExternallyManaged = true;
//    deploymentStrategies.add(new DefaultAutoDeploymentStrategy());
//    deploymentStrategies.add(new SingleResourceAutoDeploymentStrategy());
//    deploymentStrategies.add(new ResourceParentFolderAutoDeploymentStrategy());
  }

  @Override
  public LabelServer buildLabelServer() {
    LabelServer LabelServer = super.buildLabelServer();
    LabelServers.setInitialized(true);
//    autoDeployResources(LabelServer);
    return LabelServer;
  }

  public void setTransactionSynchronizationAdapterOrder(Integer transactionSynchronizationAdapterOrder) {
    this.transactionSynchronizationAdapterOrder = transactionSynchronizationAdapterOrder;
  }

  @Override
  protected void initDefaultCommandConfig() {
    if (defaultCommandConfig == null) {
      defaultCommandConfig = new CommandConfig().setContextReusePossible(true);
    }
  }

  @Override
  protected CommandInterceptor createTransactionInterceptor() {
    if (transactionManager == null) {
      throw new LabelException("transactionManager is required property for SpringLabelServerConfiguration, use "
              + StandaloneLabelServerConfiguration.class.getName() + " otherwise");
    }

    return new SpringTransactionInterceptor(transactionManager);
  }

  @Override
  protected void initTransactionContextFactory() {
    if (transactionContextFactory == null && transactionManager != null) {
      transactionContextFactory = new SpringTransactionContextFactory(transactionManager, transactionSynchronizationAdapterOrder);
    }
  }

  @Override
  protected void initJpa() {
    super.initJpa();
    if (jpaEntityManagerFactory != null) {
      sessionFactories.put(EntityManagerSession.class, new SpringEntityManagerSessionFactory(jpaEntityManagerFactory, jpaHandleTransaction,
              jpaCloseEntityManager));
    }
  }
//
//  protected void autoDeployResources(LabelServer LabelServer) {
//    if (deploymentResources != null && deploymentResources.length > 0) {
//      final AutoDeploymentStrategy strategy = getAutoDeploymentStrategy(deploymentMode);
//      strategy.deployResources(deploymentName, deploymentResources, LabelServer.getRepositoryService());
//    }
//  }

  @Override
  public LabelServerConfiguration setDataSource(DataSource dataSource) {
    if (dataSource instanceof TransactionAwareDataSourceProxy) {
      return super.setDataSource(dataSource);
    } else {
      // Wrap datasource in Transaction-aware proxy
      DataSource proxiedDataSource = new TransactionAwareDataSourceProxy(dataSource);
      return super.setDataSource(proxiedDataSource);
    }
  }

  public PlatformTransactionManager getTransactionManager() {
    return transactionManager;
  }

  public void setTransactionManager(PlatformTransactionManager transactionManager) {
    this.transactionManager = transactionManager;
  }

  public String getDeploymentName() {
    return deploymentName;
  }

  public void setDeploymentName(String deploymentName) {
    this.deploymentName = deploymentName;
  }

  public Resource[] getDeploymentResources() {
    return deploymentResources;
  }

  public void setDeploymentResources(Resource[] deploymentResources) {
    this.deploymentResources = deploymentResources;
  }

  public ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  public String getDeploymentMode() {
    return deploymentMode;
  }

  public void setDeploymentMode(String deploymentMode) {
    this.deploymentMode = deploymentMode;
  }


}
