package com.winit.label.manager.impl.gb.dpd.model;

public class Invoice
{
	protected AuditInfo				auditInfo;
	protected String				countryOfOrigin;
	protected InvoiceBillingDetails	invoiceBillingDetails;
	protected String				invoiceCustomsNumber;
	protected String				invoiceExportReason;
	protected boolean				invoiceIsDeclared;
	protected InvoiceItem[]			invoiceItem;
	protected String				invoiceTermsOfDelivery;
	protected int					invoiceType;
	protected int					totalItems;
	protected float					totalValue;
	protected float					totalWeight;

	public AuditInfo getAuditInfo()
	{
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo)
	{
		this.auditInfo = auditInfo;
	}

	public String getCountryOfOrigin()
	{
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin)
	{
		this.countryOfOrigin = countryOfOrigin;
	}

	public InvoiceBillingDetails getInvoiceBillingDetails()
	{
		return invoiceBillingDetails;
	}

	public void setInvoiceBillingDetails(InvoiceBillingDetails invoiceBillingDetails)
	{
		this.invoiceBillingDetails = invoiceBillingDetails;
	}

	public String getInvoiceCustomsNumber()
	{
		return invoiceCustomsNumber;
	}

	public void setInvoiceCustomsNumber(String invoiceCustomsNumber)
	{
		this.invoiceCustomsNumber = invoiceCustomsNumber;
	}

	public String getInvoiceExportReason()
	{
		return invoiceExportReason;
	}

	public void setInvoiceExportReason(String invoiceExportReason)
	{
		this.invoiceExportReason = invoiceExportReason;
	}

	public boolean isInvoiceIsDeclared()
	{
		return invoiceIsDeclared;
	}

	public void setInvoiceIsDeclared(boolean invoiceIsDeclared)
	{
		this.invoiceIsDeclared = invoiceIsDeclared;
	}

	public InvoiceItem[] getInvoiceItem()
	{
		return invoiceItem;
	}

	public void setInvoiceItem(InvoiceItem[] invoiceItem)
	{
		this.invoiceItem = invoiceItem;
	}

	public String getInvoiceTermsOfDelivery()
	{
		return invoiceTermsOfDelivery;
	}

	public void setInvoiceTermsOfDelivery(String invoiceTermsOfDelivery)
	{
		this.invoiceTermsOfDelivery = invoiceTermsOfDelivery;
	}

	public int getInvoiceType()
	{
		return invoiceType;
	}

	public void setInvoiceType(int invoiceType)
	{
		this.invoiceType = invoiceType;
	}

	public int getTotalItems()
	{
		return totalItems;
	}

	public void setTotalItems(int totalItems)
	{
		this.totalItems = totalItems;
	}

	public float getTotalValue()
	{
		return totalValue;
	}

	public void setTotalValue(float totalValue)
	{
		this.totalValue = totalValue;
	}

	public float getTotalWeight()
	{
		return totalWeight;
	}

	public void setTotalWeight(float totalWeight)
	{
		this.totalWeight = totalWeight;
	}

}
