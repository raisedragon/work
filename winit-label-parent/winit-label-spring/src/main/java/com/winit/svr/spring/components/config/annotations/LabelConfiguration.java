package com.winit.svr.spring.components.config.annotations;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.winit.svr.IdentityService;
import com.winit.svr.LabelException;
import com.winit.svr.LabelServer;
import com.winit.svr.LabelServerConfiguration;
import com.winit.svr.LabelService;
import com.winit.svr.ManagementService;
import com.winit.svr.PropertyService;
import com.winit.svr.spring.LabelServerFactoryBean;
import com.winit.svr.spring.SpringLabelServerConfiguration;
import com.winit.svr.spring.annotations.ActivitiConfigurer;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;


/**
 * {@code @Configuration} class that registers a
 * {@link com.winit.svr.LabelServer process engine} which has the
 * {@code bean} name {@code labelService}. The result is comparable to
 * {@code <activiti:annotation-driven>}.
 * 
 * @author Josh Long
 * @see com.winit.svr.spring.annotations.EnableActiviti
 */
@Configuration
public class LabelConfiguration {

	@Autowired(required = false)
	private List<TaskExecutor> executors;

	@Autowired(required = false)
	private List<ActivitiConfigurer> activitiConfigurers;

	@Autowired(required = false)
	private Map<String, DataSource> dataSources;

	@Autowired(required = false)
	private List<PlatformTransactionManager> platformTransactionManagers;


	@Bean
	public SpringLabelServerConfiguration springLabelServerConfiguration() {
		ActivitiConfigurer configurer = activitiConfigurer(activitiConfigurers);
		List<Resource> processDefinitionResources = new ArrayList<Resource>();
		configurer.processDefinitionResources(processDefinitionResources);
		SpringLabelServerConfiguration engine = new SpringLabelServerConfiguration();
		if (processDefinitionResources.size() > 0) {
			engine.setDeploymentResources(processDefinitionResources
			    .toArray(new Resource[processDefinitionResources.size()]));
		}
		DataSource dataSource = dataSource(configurer, dataSources);
		engine.setDataSource(dataSource);
		engine.setTransactionManager(platformTransactionManager(dataSource));
		engine.setDatabaseSchemaUpdate(LabelServerConfiguration.DB_SCHEMA_UPDATE_TRUE);
		configurer.postProcessSpringLabelServerConfiguration(engine);
		return engine;
	}

	@Bean
	public LabelServerFactoryBean labelService(SpringLabelServerConfiguration springLabelServerConfiguration) {
		LabelServerFactoryBean labelServiceFactoryBean = new LabelServerFactoryBean();
		labelServiceFactoryBean.setLabelServerConfiguration(springLabelServerConfiguration);
		return labelServiceFactoryBean;
	}

	@Bean
	public ManagementService managementService(LabelServer labelService) {
		return labelService.getManagementService();
	}


	@Bean
	public IdentityService identityService(LabelServer labelService) {
	  return labelService.getIdentityService();
	}
	
	@Bean
	public LabelService labelService(LabelServer labelService) {
	  return labelService.getLabelService();
	}
	
	
	@Bean
	public PropertyService propertyService(LabelServer labelService) {
	  return labelService.getPropertyService();
	}
	
	


	/*
	 * @Bean public static ProcessScopeBeanFactoryPostProcessor processScope() {
	 * return new ProcessScopeBeanFactoryPostProcessor(); }
	 * 
	 * @Bean public SharedProcessInstanceFactoryBean
	 * processInstanceFactoryBean(SharedProcessInstanceHolder
	 * sharedProcessInstanceHolder) { return new
	 * SharedProcessInstanceFactoryBean(sharedProcessInstanceHolder); }
	 * 
	 * @Bean public SharedProcessInstanceHolder processScopeContextHolder() {
	 * return new SharedProcessInstanceHolder(); }
	 */

	protected PlatformTransactionManager platformTransactionManager(final DataSource dataSource) {
		return first(this.platformTransactionManagers,
		    new ObjectFactory<PlatformTransactionManager>() {
			    @Override
			    public PlatformTransactionManager getObject() throws BeansException {
				    return new DataSourceTransactionManager(dataSource);
			    }
		    });
	}

	protected ActivitiConfigurer activitiConfigurer(final List<ActivitiConfigurer> activitiConfigurers) {

		return new ActivitiConfigurer() {
			@Override
			public void processDefinitionResources(List<Resource> resourceList) {
				List<Resource> resources = new ArrayList<Resource>();

				// lets first see if any exist in the default place:
				Resource defaultClassPathResourceMatcher = new ClassPathResource("classpath:/processes/**bpmn20.xml");

				if (defaultClassPathResourceMatcher.exists()) {
					resources.add(defaultClassPathResourceMatcher);
				}

				if (activitiConfigurers != null && activitiConfigurers.size() > 0) {
					for (ActivitiConfigurer ac : activitiConfigurers) {
						ac.processDefinitionResources(resources);
					}
				}

				resourceList.addAll(resources);
			}

			@Override
			public void postProcessSpringLabelServerConfiguration(SpringLabelServerConfiguration springLabelServerConfiguration) {
				if (activitiConfigurers != null) {
					for (ActivitiConfigurer configurer : activitiConfigurers) {
						configurer.postProcessSpringLabelServerConfiguration(springLabelServerConfiguration);
					}
				}
			}

			@Override
			public DataSource dataSource() {
				return null;
			}
		};
	}

	/**
	 * Sifts through beans available and returns the right one based on some common heuristics
	 */
	@SuppressWarnings("unchecked")
  private DataSource dataSource(ActivitiConfigurer activitiConfigurer, Map<String, DataSource> dataSourceMap) {
		DataSource ds = null;
		if (activitiConfigurer != null) {
			DataSource dataSource = activitiConfigurer.dataSource();
			if (null != dataSource) {
				ds = dataSource;
			}
		}

		if (dataSourceMap != null) {
		
			if (dataSourceMap.size() > 0) {
				for (DataSource d : dataSourceMap.values()) {
					ds = d;
				}
			}
	
			String defaultName = "activitiDataSource";
			if (dataSourceMap.containsKey(defaultName)) {
				ds = dataSourceMap.get(defaultName);
			}
			
		}

		// If no datasource is found at this point, we create a simple in-memory H2
		if (ds == null) {
			
			try {
				SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
				simpleDriverDataSource.setDriverClass((Class<? extends Driver>) Class.forName("org.h2.Driver"));
				simpleDriverDataSource.setUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000");
				simpleDriverDataSource.setUsername("sa");
				simpleDriverDataSource.setPassword("");
				ds = simpleDriverDataSource;
			}
            catch (ClassNotFoundException e) {
				throw new LabelException("No dataSource bean was found. Tried to create default H2 in memory database, "
						+ "but couldn't find the driver on the classpath");
			}
		}
		
		return ds;
	}

	private static <T> T first(List<T> tList, ObjectFactory<T> tObjectFactory) {
		T rt;
		if (tList != null && tList.size() > 0) {
			rt = tList.iterator().next();
		} else {
			rt = tObjectFactory.getObject();
		}
		return rt;
	}
}
