package com.winit.svr.spring.annotations;

import org.springframework.core.io.Resource;

import com.winit.svr.spring.SpringLabelServerConfiguration;

import javax.sql.DataSource;

import java.util.List;

public interface ActivitiConfigurer {
	
	void processDefinitionResources(List<Resource> resourceList);

	void postProcessSpringLabelServerConfiguration(SpringLabelServerConfiguration springLabelServerConfiguration);

	DataSource dataSource();
}