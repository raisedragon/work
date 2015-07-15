package com.winit.label.model;

public class Trigger
{
	protected Long			id;
	protected String		name;
	protected String		group;
	protected String		clazz;
	protected String		description;
	protected Long			jobId;

	protected EntityStatus	status	= EntityStatus.ACTIVE;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getGroup()
	{
		return group;
	}

	public void setGroup(String group)
	{
		this.group = group;
	}

	public String getClazz()
	{
		return clazz;
	}

	public void setClazz(String clazz)
	{
		this.clazz = clazz;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Long getJobId()
	{
		return jobId;
	}

	public void setJobId(Long jobId)
	{
		this.jobId = jobId;
	}

	public EntityStatus getStatus()
	{
		return status;
	}

	public void setStatus(EntityStatus status)
	{
		this.status = status;
	}

}
