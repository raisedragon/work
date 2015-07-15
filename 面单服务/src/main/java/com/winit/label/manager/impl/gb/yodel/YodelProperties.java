package com.winit.label.manager.impl.gb.yodel;

import java.util.Date;

public class YodelProperties
{
	private Long	id;
	private Date	created;
	private Long	createdBy;
	private Date	updated;
	private Long	updatedBy;
	private String	key;
	private String	value;
	private String	version;
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Date getCreated()
	{
		return created;
	}
	public void setCreated(Date created)
	{
		this.created = created;
	}
	public Long getCreatedBy()
	{
		return createdBy;
	}
	public void setCreatedBy(Long createdBy)
	{
		this.createdBy = createdBy;
	}
	public Date getUpdated()
	{
		return updated;
	}
	public void setUpdated(Date updated)
	{
		this.updated = updated;
	}
	public Long getUpdatedBy()
	{
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy)
	{
		this.updatedBy = updatedBy;
	}
	public String getKey()
	{
		return key;
	}
	public void setKey(String key)
	{
		this.key = key;
	}
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	public String getVersion()
	{
		return version;
	}
	public void setVersion(String version)
	{
		this.version = version;
	}


}
