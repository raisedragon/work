package com.winit.label.manager.impl.gb.dpd.model;


public class ListOfServiceData
{
	protected boolean	invoiceRequired;
	protected boolean	isLiabilityAllowed;
	protected Network	network;
	protected Product	product;
	protected Service	service;
	public boolean isInvoiceRequired()
	{
		return invoiceRequired;
	}
	public void setInvoiceRequired(boolean invoiceRequired)
	{
		this.invoiceRequired = invoiceRequired;
	}
	public boolean isLiabilityAllowed()
	{
		return isLiabilityAllowed;
	}
	public void setLiabilityAllowed(boolean isLiabilityAllowed)
	{
		this.isLiabilityAllowed = isLiabilityAllowed;
	}
	public Network getNetwork()
	{
		return network;
	}
	public void setNetwork(Network network)
	{
		this.network = network;
	}
	public Product getProduct()
	{
		return product;
	}
	public void setProduct(Product product)
	{
		this.product = product;
	}
	public Service getService()
	{
		return service;
	}
	public void setService(Service service)
	{
		this.service = service;
	}
	
}
