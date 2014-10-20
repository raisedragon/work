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

import com.winit.svr.impl.LabelServerImpl;
import com.winit.svr.impl.cfg.LabelServerConfigurationImpl;
import com.winit.svr.impl.cfg.SpringBeanFactoryProxyMap;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Dave Syer
 * @author Christian Stettler
 * @author Tom Baeyens
 * @author Joram Barrez
 */
public class LabelServerFactoryBean implements FactoryBean<com.winit.svr.LabelServer>, DisposableBean, ApplicationContextAware {

  protected LabelServerConfigurationImpl labelServerConfiguration;
  protected ApplicationContext applicationContext;
  protected LabelServerImpl labelServer;
  
  public void destroy() throws Exception {
    if (labelServer != null) {
      labelServer.close();
    }
  }

  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  public com.winit.svr.LabelServer getObject() throws Exception {
    initializeTransactionExternallyManaged();
    
    if (labelServerConfiguration.getBeans()==null) {
      labelServerConfiguration.setBeans(new SpringBeanFactoryProxyMap(applicationContext));
    }
    
    labelServer = (LabelServerImpl) labelServerConfiguration.buildLabelServer();

    return labelServer;
  }

  
  protected void initializeTransactionExternallyManaged() {
    if (labelServerConfiguration instanceof SpringLabelServerConfiguration) { // remark: any config can be injected, so we cannot have SpringConfiguration as member
      SpringLabelServerConfiguration engineConfiguration = (SpringLabelServerConfiguration) labelServerConfiguration;
      if (engineConfiguration.getTransactionManager() != null) {
        labelServerConfiguration.setTransactionsExternallyManaged(true);
      }
    }
  }
  
  public Class<com.winit.svr.LabelServer> getObjectType() {
    return com.winit.svr.LabelServer.class;
  }

  public boolean isSingleton() {
    return true;
  }

  // getters and setters //////////////////////////////////////////////////////
  
  public LabelServerConfigurationImpl getLabelServerConfiguration() {
    return labelServerConfiguration;
  }

  
  public void setLabelServerConfiguration(LabelServerConfigurationImpl labelServerConfiguration) {
    this.labelServerConfiguration = labelServerConfiguration;
  }
}
