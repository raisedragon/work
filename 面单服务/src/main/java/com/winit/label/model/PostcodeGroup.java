package com.winit.label.model;

import java.util.Date;

public class PostcodeGroup
{
	private Long	id;
	private Date	created;
	private String	createdby;
	private Date	updated;
	private String	updatedby;
	private String	postcode;
	private Long	LogisticsDistId;

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

	public String getPostcode()
	{
		return postcode;
	}

	public void setPostcode(String postcode)
	{
		this.postcode = postcode;
	}

	public Long getLogisticsDistId()
	{
		return LogisticsDistId;
	}

	public void setLogisticsDistId(Long logisticsDistId)
	{
		LogisticsDistId = logisticsDistId;
	}

}
