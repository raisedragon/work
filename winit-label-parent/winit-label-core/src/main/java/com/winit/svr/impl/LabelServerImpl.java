/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.winit.svr.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winit.svr.IdentityService;
import com.winit.svr.LabelService;
import com.winit.svr.ManagementService;
import com.winit.svr.LabelServer;
import com.winit.svr.LabelServers;
import com.winit.svr.PropertyService;
import com.winit.svr.delegate.event.ActivitiEventType;
import com.winit.svr.delegate.event.impl.ActivitiEventBuilder;
import com.winit.svr.impl.cfg.LabelServerConfigurationImpl;
import com.winit.svr.impl.cfg.TransactionContextFactory;
import com.winit.svr.impl.interceptor.CommandExecutor;
import com.winit.svr.impl.interceptor.SessionFactory;

/**
 * @author Tom Baeyens
 */
public class LabelServerImpl implements LabelServer
{

	private static Logger					log	= LoggerFactory.getLogger(LabelServerImpl.class);

	protected String						name;
	protected IdentityService				identityService;
	protected ManagementService				managementService;
	protected LabelService					labelService;
	protected PropertyService				propertyService;
	protected CommandExecutor				commandExecutor;
	protected Map<Class<?>, SessionFactory>	sessionFactories;
	protected TransactionContextFactory		transactionContextFactory;
	protected LabelServerConfigurationImpl	labelServerConfiguration;

	public LabelServerImpl(LabelServerConfigurationImpl labelServerConfiguration)
	{
		this.labelServerConfiguration = labelServerConfiguration;
		this.name = labelServerConfiguration.getProcessEngineName();
		this.identityService = labelServerConfiguration.getIdentityService();
		this.managementService = labelServerConfiguration.getManagementService();
		this.labelService = labelServerConfiguration.getLabelService();
		this.propertyService = labelServerConfiguration.getPropertyService();
		this.commandExecutor = labelServerConfiguration.getCommandExecutor();
		this.sessionFactories = labelServerConfiguration.getSessionFactories();
		this.transactionContextFactory = labelServerConfiguration.getTransactionContextFactory();

		commandExecutor.execute(labelServerConfiguration.getSchemaCommandConfig(),
				new SchemaOperationsProcessEngineBuild());

		if (name == null)
		{
			log.info("default activiti ProcessEngine created");
		}
		else
		{
			log.info("ProcessEngine {} created", name);
		}

		LabelServers.registerProcessEngine(this);

		if (labelServerConfiguration.getProcessEngineLifecycleListener() != null)
		{
			labelServerConfiguration.getProcessEngineLifecycleListener().onProcessEngineBuilt(this);
		}

		labelServerConfiguration.getEventDispatcher().dispatchEvent(
				ActivitiEventBuilder.createGlobalEvent(ActivitiEventType.ENGINE_CREATED));
	}

	public void close()
	{
		LabelServers.unregister(this);

		commandExecutor.execute(labelServerConfiguration.getSchemaCommandConfig(),
				new SchemaOperationProcessEngineClose());

		if (labelServerConfiguration.getProcessEngineLifecycleListener() != null)
		{
			labelServerConfiguration.getProcessEngineLifecycleListener().onProcessEngineClosed(this);
		}

		labelServerConfiguration.getEventDispatcher().dispatchEvent(
				ActivitiEventBuilder.createGlobalEvent(ActivitiEventType.ENGINE_CLOSED));
	}

	// getters and setters
	// //////////////////////////////////////////////////////

	public String getName()
	{
		return name;
	}

	public IdentityService getIdentityService()
	{
		return identityService;
	}

	public ManagementService getManagementService()
	{
		return managementService;
	}

	public LabelService getLabelService()
	{
		return labelService;
	}

	public LabelServerConfigurationImpl getLabelServerConfiguration()
	{
		return labelServerConfiguration;
	}

	@Override
	public PropertyService getPropertyService()
	{
		return propertyService;
	}
}
