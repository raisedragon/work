/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.winit.svr.impl.persistence.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.db.BulkDeleteable;
import com.winit.svr.impl.db.HasRevision;
import com.winit.svr.impl.db.PersistentObject;
import com.winit.svr.impl.variable.ValueFields;
import com.winit.svr.impl.variable.VariableType;

/**
 * @author Tom Baeyens
 * @author Marcus Klimstra (CGI)
 */
public class VariableInstanceEntity implements ValueFields, PersistentObject, HasRevision, BulkDeleteable, Serializable
{
	public final static String LOGISTIC_TYPE_LABEL_IMPL_CLASS = "LOGISTIC_TYPE_LABEL_IMPL_CLASS";
	private static final long	serialVersionUID	= 1L;

	public static enum VariableOwner
	{
		LogisticType("LogisticType"), Label("Label");

		private String	desc;

		VariableOwner(String desc)
		{
			this.desc = desc;
		}

		public String getDesc()
		{
			return desc;
		}
	}

	protected String				id;
	protected int					revision;

	protected String				name;
	protected VariableType			type;

	protected String				processInstanceId;
	protected String				executionId;
	protected String				taskId;

	protected Long					longValue;
	protected Double				doubleValue;
	protected String				textValue;
	protected String				textValue2;
	protected final ByteArrayRef	byteArrayRef	= new ByteArrayRef();

	protected Object				cachedValue;
	protected boolean				forcedUpdate;
	protected boolean				deleted			= false;

	protected VariableOwner			owner;
	protected String				referenceKey;

	// Default constructor for SQL mapping
	protected VariableInstanceEntity()
	{
	}

	public static void touch(VariableInstanceEntity VariableInstanceEntity)
	{
		Context.getCommandContext().getDbSqlSession().touch(VariableInstanceEntity);

	}

	public static VariableInstanceEntity createAndInsert(String name, VariableType type, Object value)
	{
		VariableInstanceEntity VariableInstanceEntity = create(name, type, value);

		Context.getCommandContext().getDbSqlSession().insert(VariableInstanceEntity);

		return VariableInstanceEntity;
	}

	public static VariableInstanceEntity create(String name, VariableType type, Object value)
	{
		VariableInstanceEntity VariableInstanceEntity = new VariableInstanceEntity();
		VariableInstanceEntity.name = name;
		VariableInstanceEntity.type = type;
		VariableInstanceEntity.setValue(value);
		return VariableInstanceEntity;
	}

	//
	// public void setExecution(ExecutionEntity execution) {
	// this.executionId = execution.getId();
	// this.processInstanceId = execution.getProcessInstanceId();
	// forceUpdate();
	// }
	//
	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#forceUpdate()
	 */
	
	public void forceUpdate()
	{
		forcedUpdate = true;

	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#delete()
	 */
	
	public void delete()
	{
		Context.getCommandContext().getDbSqlSession().delete(this);

		byteArrayRef.delete();
		deleted = true;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getPersistentState()
	 */
	
	public Object getPersistentState()
	{
		Map<String, Object> persistentState = new HashMap<String, Object>();
		if (longValue != null)
		{
			persistentState.put("longValue", longValue);
		}
		if (doubleValue != null)
		{
			persistentState.put("doubleValue", doubleValue);
		}
		if (textValue != null)
		{
			persistentState.put("textValue", textValue);
		}
		if (textValue2 != null)
		{
			persistentState.put("textValue2", textValue2);
		}
		if (byteArrayRef.getId() != null)
		{
			persistentState.put("byteArrayValueId", byteArrayRef.getId());
		}
		if (forcedUpdate)
		{
			persistentState.put("forcedUpdate", Boolean.TRUE);
		}
		return persistentState;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getRevisionNext()
	 */
	
	public int getRevisionNext()
	{
		return revision + 1;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#isDeleted()
	 */
	
	public boolean isDeleted()
	{
		return deleted;
	}

	// lazy initialized relations
	// ///////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#setProcessInstanceId(java.lang.String)
	 */
	
	public void setProcessInstanceId(String processInstanceId)
	{
		this.processInstanceId = processInstanceId;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#setExecutionId(java.lang.String)
	 */
	
	public void setExecutionId(String executionId)
	{
		this.executionId = executionId;
	}

	// byte array value
	// /////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getBytes()
	 */
	
	
	public byte[] getBytes()
	{
		return byteArrayRef.getBytes();
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#setBytes(byte[])
	 */
	
	
	public void setBytes(byte[] bytes)
	{
		byteArrayRef.setValue("var-" + name, bytes);
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getByteArrayValue()
	 */
	
	
	@Deprecated
	public ByteArrayEntity getByteArrayValue()
	{
		return byteArrayRef.getEntity();
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getByteArrayValueId()
	 */
	
	
	@Deprecated
	public String getByteArrayValueId()
	{
		return byteArrayRef.getId();
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#setByteArrayValue(byte[])
	 */
	
	
	@Deprecated
	public void setByteArrayValue(byte[] bytes)
	{
		setBytes(bytes);
	}

	// value
	// ////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getValue()
	 */
	
	public Object getValue()
	{
		if (!type.isCachable() || cachedValue == null)
		{
			cachedValue = type.getValue(this);
		}
		return cachedValue;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#setValue(java.lang.Object)
	 */
	
	public void setValue(Object value)
	{
		type.setValue(value, this);
		cachedValue = value;
	}

	// getters and setters
	// //////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getId()
	 */
	
	public String getId()
	{
		return id;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#setId(java.lang.String)
	 */
	
	public void setId(String id)
	{
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getRevision()
	 */
	
	public int getRevision()
	{
		return revision;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#setRevision(int)
	 */
	
	public void setRevision(int revision)
	{
		this.revision = revision;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getName()
	 */
	
	public String getName()
	{
		return name;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getType()
	 */
	
	public VariableType getType()
	{
		return type;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#setType(com.winit.svr.impl.variable.VariableType)
	 */
	
	public void setType(VariableType type)
	{
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getProcessInstanceId()
	 */
	
	public String getProcessInstanceId()
	{
		return processInstanceId;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getTaskId()
	 */
	
	public String getTaskId()
	{
		return taskId;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#setTaskId(java.lang.String)
	 */
	
	public void setTaskId(String taskId)
	{
		this.taskId = taskId;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getExecutionId()
	 */
	
	public String getExecutionId()
	{
		return executionId;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getLongValue()
	 */
	
	public Long getLongValue()
	{
		return longValue;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#setLongValue(java.lang.Long)
	 */
	
	public void setLongValue(Long longValue)
	{
		this.longValue = longValue;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getDoubleValue()
	 */
	
	public Double getDoubleValue()
	{
		return doubleValue;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#setDoubleValue(java.lang.Double)
	 */
	
	public void setDoubleValue(Double doubleValue)
	{
		this.doubleValue = doubleValue;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getTextValue()
	 */
	
	public String getTextValue()
	{
		return textValue;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#setTextValue(java.lang.String)
	 */
	
	public void setTextValue(String textValue)
	{
		this.textValue = textValue;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getTextValue2()
	 */
	
	public String getTextValue2()
	{
		return textValue2;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#setTextValue2(java.lang.String)
	 */
	
	public void setTextValue2(String textValue2)
	{
		this.textValue2 = textValue2;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getCachedValue()
	 */
	
	public Object getCachedValue()
	{
		return cachedValue;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#setCachedValue(java.lang.Object)
	 */
	
	public void setCachedValue(Object cachedValue)
	{
		this.cachedValue = cachedValue;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getOwner()
	 */
	
	public VariableOwner getOwner()
	{
		return owner;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#setOwner(com.winit.svr.impl.persistence.entity.VariableInstanceEntity.VariableOwner)
	 */
	
	public void setOwner(VariableOwner owner)
	{
		this.owner = owner;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#getRefenceKey()
	 */
	
	public String getReferenceKey()
	{
		return referenceKey;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.VariableInstanceEntity#setRefenceKey(java.lang.String)
	 */
	
	public void setReferenceKey(String referenceKey)
	{
		this.referenceKey = referenceKey;
	}

	// misc methods
	// /////////////////////////////////////////////////////////////

	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("VariableInstanceEntity[");
		sb.append("id=").append(id);
		sb.append(", name=").append(name);
		sb.append(", type=").append(type != null ? type.getTypeName() : "null");
		if (longValue != null)
		{
			sb.append(", longValue=").append(longValue);
		}
		if (doubleValue != null)
		{
			sb.append(", doubleValue=").append(doubleValue);
		}
		if (textValue != null)
		{
			sb.append(", textValue=").append(StringUtils.abbreviate(textValue, 40));
		}
		if (textValue2 != null)
		{
			sb.append(", textValue2=").append(StringUtils.abbreviate(textValue2, 40));
		}
		if (byteArrayRef.getId() != null)
		{
			sb.append(", byteArrayValueId=").append(byteArrayRef.getId());
		}
		sb.append("]");
		return sb.toString();
	}

}
