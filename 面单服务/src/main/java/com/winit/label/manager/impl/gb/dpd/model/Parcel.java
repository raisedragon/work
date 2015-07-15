package com.winit.label.manager.impl.gb.dpd.model;

public class Parcel
{
	protected AuditInfo		auditInfo;
	protected boolean		isVoided;
	protected String		labelNumber;
	protected int			packageNumber;
	protected String		parcelNumber;
	protected ParcelProduct	parcelProduct;
	protected String		parcelnetBarcode;
	protected String		parcelnetData;
	protected String		parcelnetLabelNumber;

	public AuditInfo getAuditInfo()
	{
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo)
	{
		this.auditInfo = auditInfo;
	}

	public boolean isVoided()
	{
		return isVoided;
	}

	public void setVoided(boolean isVoided)
	{
		this.isVoided = isVoided;
	}

	public String getLabelNumber()
	{
		return labelNumber;
	}

	public void setLabelNumber(String labelNumber)
	{
		this.labelNumber = labelNumber;
	}

	public int getPackageNumber()
	{
		return packageNumber;
	}

	public void setPackageNumber(int packageNumber)
	{
		this.packageNumber = packageNumber;
	}

	public String getParcelNumber()
	{
		return parcelNumber;
	}

	public void setParcelNumber(String parcelNumber)
	{
		this.parcelNumber = parcelNumber;
	}

	public ParcelProduct getParcelProduct()
	{
		return parcelProduct;
	}

	public void setParcelProduct(ParcelProduct parcelProduct)
	{
		this.parcelProduct = parcelProduct;
	}

	public String getParcelnetBarcode()
	{
		return parcelnetBarcode;
	}

	public void setParcelnetBarcode(String parcelnetBarcode)
	{
		this.parcelnetBarcode = parcelnetBarcode;
	}

	public String getParcelnetData()
	{
		return parcelnetData;
	}

	public void setParcelnetData(String parcelnetData)
	{
		this.parcelnetData = parcelnetData;
	}

	public String getParcelnetLabelNumber()
	{
		return parcelnetLabelNumber;
	}

	public void setParcelnetLabelNumber(String parcelnetLabelNumber)
	{
		this.parcelnetLabelNumber = parcelnetLabelNumber;
	}

}
