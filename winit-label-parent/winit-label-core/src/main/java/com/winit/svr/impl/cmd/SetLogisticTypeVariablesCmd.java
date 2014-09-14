
package com.winit.svr.impl.cmd;

import java.util.Map;

import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.persistence.entity.LogisticTypeEntity;

public class SetLogisticTypeVariablesCmd  implements Command<Void>
{

	private static final long				serialVersionUID	= 1L;

	protected Map<String, ? extends Object>	variables;
	protected boolean						isLocal;
	protected String						logisticTypeId;

	public SetLogisticTypeVariablesCmd(String logisticTypeId, Map<String, ? extends Object> variables, boolean isLocal)
	{
		this.logisticTypeId = logisticTypeId;
		this.variables = variables;
		this.isLocal = isLocal;
	}

	public Void execute(CommandContext commandContext)
	{
		LogisticTypeEntity logisticType = commandContext.getLogisticTypeManager().findByLogisticTypeId(logisticTypeId);
		if (isLocal)
		{
			logisticType.setVariablesLocal(variables);
		}
		else
		{
			logisticType.setVariables(variables);
		}

		logisticType.forceUpdate();
		return null;
	}

}
