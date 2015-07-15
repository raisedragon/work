package com.winit.label.model;

import java.util.Date;

public class LogisticsDist
{
	private Long	id;
	private Date	created;
	private String	createdby;
	private Date	updated;
	private String	updatedby;
	private String	name;
	private String	description;
	private Long	deliveryWayId;

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

	public String getCreatedby()
	{
		return createdby;
	}

	public void setCreatedby(String createdby)
	{
		this.createdby = createdby;
	}

	public Date getUpdated()
	{
		return updated;
	}

	public void setUpdated(Date updated)
	{
		this.updated = updated;
	}

	public String getUpdatedby()
	{
		return updatedby;
	}

	public void setUpdatedby(String updatedby)
	{
		this.updatedby = updatedby;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Long getDeliveryWayId()
	{
		return deliveryWayId;
	}

	public void setDeliveryWayId(Long deliveryWayId)
	{
		this.deliveryWayId = deliveryWayId;
	}

}
