package com.winit.svr.spring.annotations;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.winit.svr.spring.SpringLabelServerConfiguration;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple no-op implementation of {@link ActivitiConfigurer} that is suitable as
 * a base-class for other implementations.
 * 
 * @author Josh Long
 */
public class AbstractLabelConfigurer implements ActivitiConfigurer {
	@Override
	public void processDefinitionResources(List<Resource> resourceList) {
	}

	@Override
	public void postProcessSpringLabelServerConfiguration(
	    SpringLabelServerConfiguration springLabelServerConfiguration) {
	}

	@Override
	public DataSource dataSource() {
		return null;
	}

	protected List<ClassPathResource> classPathResourcesForProcessDefinitions(String... pds) {
		List<ClassPathResource> classPathResources = new ArrayList<ClassPathResource>();
		for (String pd : pds) {
			classPathResources.add(new ClassPathResource(pd));
		}
		return classPathResources;

	}
}
