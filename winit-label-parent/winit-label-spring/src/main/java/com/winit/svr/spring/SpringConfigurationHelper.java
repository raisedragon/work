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

import java.net.URL;
import java.util.Map;

import com.winit.svr.LabelException;
import com.winit.svr.LabelServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.UrlResource;


/**
 * @author Tom Baeyens
 */
public class SpringConfigurationHelper {
  
  private static Logger log = LoggerFactory.getLogger(SpringConfigurationHelper.class);

  public static LabelServer buildLabelServer(URL resource) {
    log.debug("==== BUILDING SPRING APPLICATION CONTEXT AND PROCESS ENGINE =========================================");
    
    ApplicationContext applicationContext = new GenericXmlApplicationContext(new UrlResource(resource));
    Map<String, LabelServer> beansOfType = applicationContext.getBeansOfType(LabelServer.class);
    if ( (beansOfType==null)
         || (beansOfType.isEmpty())
       ) {
      throw new LabelException("no "+LabelServer.class.getName()+" defined in the application context "+resource.toString());
    }
    
    LabelServer processEngine = beansOfType.values().iterator().next();

    log.debug("==== SPRING PROCESS ENGINE CREATED ==================================================================");
    return processEngine;
  }


}
