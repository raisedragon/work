package com.winit.label.manager.impl.gb.yodel;

import java.util.Date;

public class YodelDestPrdServices
{
	private Long	id;
	private Date	created;
	private Long	createdBy;
	private Date	updated;
	private Long	updatedBy;
	private String	serviceCtrReamusId;
	private String	productCode;
	private String	featureCode;
	private String	allowed;
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

	public String getServiceCtrReamusId()
	{
		return serviceCtrReamusId;
	}

	public void setServiceCtrReamusId(String serviceCtrReamusId)
	{
		this.serviceCtrReamusId = serviceCtrReamusId;
	}

	public String getProductCode()
	{
		return productCode;
	}

	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}

	public String getFeatureCode()
	{
		return featureCode;
	}

	public void setFeatureCode(String featureCode)
	{
		this.featureCode = featureCode;
	}

	public String getAllowed()
	{
		return allowed;
	}

	public void setAllowed(String allowed)
	{
		this.allowed = allowed;
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
