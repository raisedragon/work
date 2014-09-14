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

import java.util.List;

import com.winit.svr.LabelIllegalArgumentException;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.interceptor.CommandExecutor;
import com.winit.svr.label.LogisticType;
import com.winit.svr.label.LogisticTypeQuery;

/**
 * @author longsheng.wang
 *
 */
public class LogisticTypeQueryImpl extends AbstractQuery<LogisticTypeQuery, LogisticType> implements LogisticTypeQuery
{

	private static final long	serialVersionUID	= 1L;
	protected String			id;
	protected String			name;
	protected String			nameLike;
	protected String			code;
	protected String			codeLike;

	public LogisticTypeQueryImpl()
	{
	}

	public LogisticTypeQueryImpl(CommandContext commandContext)
	{
		super(commandContext);
	}

	public LogisticTypeQueryImpl(CommandExecutor commandExecutor)
	{
		super(commandExecutor);
	}

	public LogisticTypeQuery logisticTypeId(String id)
	{
		if (id == null)
		{
			throw new LabelIllegalArgumentException("Provided id is null");
		}
		this.id = id;
		return this;
	}
	
	public LogisticTypeQuery logisticTypeName(String name)
	{
		if (name == null)
		{
			throw new LabelIllegalArgumentException("Provided name is null");
		}
		this.name = name;
		return this;
	}

	public LogisticTypeQuery logisticTypeNameLike(String nameLike)
	{
		if (nameLike == null)
		{
			throw new LabelIllegalArgumentException("Provided nameLike is null");
		}
		this.nameLike = nameLike;
		return this;
	}

	
	public LogisticTypeQuery logisticTypeCode(String code)
	{
		if (code == null)
		{
			throw new LabelIllegalArgumentException("Provided code is null");
		}
		this.code = code;
		return this;
	}

	public LogisticTypeQuery logisticTypeCodeLike(String codeLike)
	{
		if (codeLike == null)
		{
			throw new LabelIllegalArgumentException("Provided codeLike is null");
		}
		this.codeLike = codeLike;
		return this;
	}

	
	

	// sorting ////////////////////////////////////////////////////////

	public LogisticTypeQuery orderByLogisticTypeId()
	{
		return orderBy(LogisticTypeQueryProperty.LOGISTICTYPE_ID);
	}

	public LogisticTypeQuery orderByLogisticTypeName()
	{
		return orderBy(LogisticTypeQueryProperty.NAME);
	}

	// results ////////////////////////////////////////////////////////

	public long executeCount(CommandContext commandContext)
	{
		checkQueryOk();
		return commandContext.getLogisticTypeManager().findLogisticTypeCountByQueryCriteria(this);
	}

	public List<LogisticType> executeList(CommandContext commandContext, Page page)
	{
		checkQueryOk();
		return commandContext.getLogisticTypeManager().findLogisticTypeByQueryCriteria(this, page);
	}

	// getters ////////////////////////////////////////////////////////

	public String getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getNameLike()
	{
		return nameLike;
	}

	public String getCode()
	{
		return code;
	}

	public String getCodeLike()
	{
		return codeLike;
	}

	@Override
	public LogisticTypeQuery LogisticTypeVariableValueEquals(String variableName, Object variableValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogisticTypeQuery LogisticTypeVariableValueEquals(Object variableValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogisticTypeQuery LogisticTypeVariableValueEqualsIgnoreCase(String name, String value)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogisticTypeQuery LogisticTypeVariableValueNotEquals(String variableName, Object variableValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogisticTypeQuery LogisticTypeVariableValueNotEqualsIgnoreCase(String name, String value)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogisticTypeQuery LogisticTypeVariableValueGreaterThan(String name, Object value)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogisticTypeQuery LogisticTypeVariableValueGreaterThanOrEqual(String name, Object value)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogisticTypeQuery LogisticTypeVariableValueLessThan(String name, Object value)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogisticTypeQuery LogisticTypeVariableValueLessThanOrEqual(String name, Object value)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogisticTypeQuery LogisticTypeVariableValueLike(String name, String value)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
