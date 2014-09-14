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

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winit.svr.impl.LabelServerInfoImpl;
import com.winit.svr.impl.util.IoUtil;
import com.winit.svr.impl.util.ReflectUtil;



/** Helper for initializing and closing process engines in server environments.
 * <br>
 * All created {@link LabelServer}s will be registered with this class.
 * <br>
 * The activiti-webapp-init webapp will
 * call the {@link #init()} method when the webapp is deployed and it will call the 
 * {@link #destroy()} method when the webapp is destroyed, using a context-listener 
 * (<code>org.activiti.impl.servlet.listener.ProcessEnginesServletContextListener</code>).  That way, 
 * all applications can just use the {@link #getProcessEngines()} to 
 * obtain pre-initialized and cached process engines. <br>
 * <br>
 * Please note that there is <b>no lazy initialization</b> of process engines, so make sure the 
 * context-listener is configured or {@link LabelServer}s are already created so they were registered
 * on this class.<br>
 * <br>
 * The {@link #init()} method will try to build one {@link LabelServer} for 
 * each activiti.cfg.xml file found on the classpath.  If you have more then one,
 * make sure you specify different process.engine.name values.
 *  
 * @author Tom Baeyens
 * @author Joram Barrez
 */
public abstract class LabelServers {
  
  private static Logger log = LoggerFactory.getLogger(LabelServers.class);
  
  public static final String NAME_DEFAULT = "default";
  
  protected static boolean isInitialized = false; 
  protected static Map<String, LabelServer> labelServers = new HashMap<String, LabelServer>();
  protected static Map<String, LabelServerInfo> processEngineInfosByName = new HashMap<String, LabelServerInfo>();
  protected static Map<String, LabelServerInfo> processEngineInfosByResourceUrl = new HashMap<String, LabelServerInfo>();
  protected static List<LabelServerInfo> labelServerInfos = new ArrayList<LabelServerInfo>();
  
  /** Initializes all process engines that can be found on the classpath for 
   * resources <code>activiti.cfg.xml</code> (plain Activiti style configuration)
   * and for resources <code>activiti-context.xml</code> (Spring style configuration). */
  public synchronized static void init() {
    if (!isInitialized()) {
      if(labelServers == null) {
        // Create new map to store process-engines if current map is null
        labelServers = new HashMap<String, LabelServer>();        
      }
      ClassLoader classLoader = ReflectUtil.getClassLoader();
      Enumeration<URL> resources = null;
      try {
        resources = classLoader.getResources("activiti.cfg.xml");
      } catch (IOException e) {
        throw new LabelIllegalArgumentException("problem retrieving activiti.cfg.xml resources on the classpath: "+System.getProperty("java.class.path"), e);
      }
      
      // Remove duplicated configuration URL's using set. Some classloaders may return identical URL's twice, causing duplicate startups
      Set<URL> configUrls = new HashSet<URL>();
      while (resources.hasMoreElements()) {
        configUrls.add( resources.nextElement() );
      }
      for (Iterator<URL> iterator = configUrls.iterator(); iterator.hasNext();) {
        URL resource = iterator.next();
        log.info("Initializing process engine using configuration '{}'",  resource.toString());
        initProcessEnginFromResource(resource);
      }
      
      try {
        resources = classLoader.getResources("activiti-context.xml");
      } catch (IOException e) {
        throw new LabelIllegalArgumentException("problem retrieving activiti-context.xml resources on the classpath: "+System.getProperty("java.class.path"), e);
      }
      while (resources.hasMoreElements()) {
        URL resource = resources.nextElement();
        log.info("Initializing process engine using Spring configuration '{}'",  resource.toString());
        initProcessEngineFromSpringResource(resource);
      }

      setInitialized(true);
    } else {
      log.info("Process engines already initialized");
    }
  }

  protected static void initProcessEngineFromSpringResource(URL resource) {
    try {
      Class< ? > springConfigurationHelperClass = ReflectUtil.loadClass("org.activiti.spring.SpringConfigurationHelper");
      Method method = springConfigurationHelperClass.getMethod("buildProcessEngine", new Class<?>[]{URL.class});
      LabelServer labelServer = (LabelServer) method.invoke(null, new Object[]{resource});
      
      String processEngineName = labelServer.getName();
      LabelServerInfo labelServerInfo = new LabelServerInfoImpl(processEngineName, resource.toString(), null);
      processEngineInfosByName.put(processEngineName, labelServerInfo);
      processEngineInfosByResourceUrl.put(resource.toString(), labelServerInfo);
      
    } catch (Exception e) {
      throw new LabelException("couldn't initialize process engine from spring configuration resource "+resource.toString()+": "+e.getMessage(), e);
    } 
  }
 
  /**
   * Registers the given process engine. No {@link LabelServerInfo} will be 
   * available for this process engine. An engine that is registered will be closed
   * when the {@link LabelServers#destroy()} is called.
   */
  public static void registerProcessEngine(LabelServer labelServer) {
    labelServers.put(labelServer.getName(), labelServer);
  }
  
  /**
   * Unregisters the given process engine.
   */
  public static void unregister(LabelServer labelServer) {
    labelServers.remove(labelServer.getName());
  }

  private static LabelServerInfo initProcessEnginFromResource(URL resourceUrl) {
    LabelServerInfo labelServerInfo = processEngineInfosByResourceUrl.get(resourceUrl.toString());
    // if there is an existing process engine info
    if (labelServerInfo!=null) {
      // remove that process engine from the member fields
      labelServerInfos.remove(labelServerInfo);
      if (labelServerInfo.getException()==null) {
        String processEngineName = labelServerInfo.getName();
        labelServers.remove(processEngineName);
        processEngineInfosByName.remove(processEngineName);
      }
      processEngineInfosByResourceUrl.remove(labelServerInfo.getResourceUrl());
    }

    String resourceUrlString = resourceUrl.toString();
    try {
      log.info("initializing process engine for resource {}", resourceUrl);
      LabelServer labelServer = buildProcessEngine(resourceUrl);
      String processEngineName = labelServer.getName();
      log.info("initialised process engine {}", processEngineName);
      labelServerInfo = new LabelServerInfoImpl(processEngineName, resourceUrlString, null);
      labelServers.put(processEngineName, labelServer);
      processEngineInfosByName.put(processEngineName, labelServerInfo);
    } catch (Throwable e) {
      log.error("Exception while initializing process engine: {}", e.getMessage(), e);
      labelServerInfo = new LabelServerInfoImpl(null, resourceUrlString, getExceptionString(e));
    }
    processEngineInfosByResourceUrl.put(resourceUrlString, labelServerInfo);
    labelServerInfos.add(labelServerInfo);
    return labelServerInfo;
  }

  private static String getExceptionString(Throwable e) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    return sw.toString();
  }

  private static  LabelServer buildProcessEngine(URL resource) {
    InputStream inputStream = null;
    try {
      inputStream = resource.openStream();
      LabelServerConfiguration labelServerConfiguration = LabelServerConfiguration.createLabelServerConfigurationFromInputStream(inputStream);
      return labelServerConfiguration.buildLabelServer();
      
    } catch (IOException e) {
      throw new LabelIllegalArgumentException("couldn't open resource stream: "+e.getMessage(), e);
    } finally {
      IoUtil.closeSilently(inputStream);
    }
  }
  
  /** Get initialization results. */
  public static List<LabelServerInfo> getProcessEngineInfos() {
    return labelServerInfos;
  }

  /** Get initialization results. Only info will we available for process engines
   * which were added in the {@link LabelServers#init()}. No {@link LabelServerInfo}
   * is available for engines which were registered programatically.
  */
  public static LabelServerInfo getProcessEngineInfo(String processEngineName) {
    return processEngineInfosByName.get(processEngineName);
  }

  public static LabelServer getDefaultProcessEngine() {
    return getProcessEngine(NAME_DEFAULT);
  }

  /** obtain a process engine by name.  
   * @param processEngineName is the name of the process engine or null for the default process engine.  */
  public static LabelServer getProcessEngine(String processEngineName) {
    if (!isInitialized()) {
      init();
    }
    return labelServers.get(processEngineName);
  }
  
  /** retries to initialize a process engine that previously failed.
   */
  public static LabelServerInfo retry(String resourceUrl) {
    log.debug("retying initializing of resource {}", resourceUrl);
    try {
      return initProcessEnginFromResource(new URL(resourceUrl));
    } catch (MalformedURLException e) {
      throw new LabelIllegalArgumentException("invalid url: "+resourceUrl, e);
    }
  }
  
  /** provides access to process engine to application clients in a 
   * managed server environment.  
   */
  public static Map<String, LabelServer> getProcessEngines() {
    return labelServers;
  }
  
  /** closes all process engines.  This method should be called when the server shuts down. */
  public synchronized static void destroy() {
    if (isInitialized()) {
      Map<String, LabelServer> engines = new HashMap<String, LabelServer>(labelServers);
      labelServers = new HashMap<String, LabelServer>();
      
      for (String processEngineName: engines.keySet()) {
        LabelServer labelServer = engines.get(processEngineName);
        try {
          labelServer.close();
        } catch (Exception e) {
          log.error("exception while closing {}", (processEngineName==null ? "the default process engine" : "process engine "+processEngineName), e);
        }
      }
      
      processEngineInfosByName.clear();
      processEngineInfosByResourceUrl.clear();
      labelServerInfos.clear();
      
      setInitialized(false);
    }
  }
  
  public static boolean isInitialized() {
    return isInitialized;
  }
  
  public static void setInitialized(boolean isInitialized) {
    LabelServers.isInitialized = isInitialized;
  }
}
