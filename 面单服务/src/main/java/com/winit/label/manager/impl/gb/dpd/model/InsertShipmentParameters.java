package com.winit.label.manager.impl.gb.dpd.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsertShipmentParameters
{
	protected String collectionDate; 
	protected boolean collectionOnDelivery ;
	protected List<Consignment> consignment ;
	protected boolean consolidate ;
	protected Invoice invoice ;
	protected Integer job_id ;
	public String getCollectionDate()
	{
		return collectionDate;
	}
	public void setCollectionDate(String collectionDate)
	{
		this.collectionDate = collectionDate;
	}
	public boolean isCollectionOnDelivery()
	{
		return collectionOnDelivery;
	}
	public void setCollectionOnDelivery(boolean collectionOnDelivery)
	{
		this.collectionOnDelivery = collectionOnDelivery;
	}
	public List<Consignment> getConsignment()
	{
		if(consignment==null){
			consignment =  new ArrayList<Consignment>();
		}
		return consignment;
	}
	public void setConsignment(List<Consignment> consignment)
	{
		this.consignment = consignment;
	}
	public boolean isConsolidate()
	{
		return consolidate;
	}
	public void setConsolidate(boolean consolidate)
	{
		this.consolidate = consolidate;
	}
	public Invoice getInvoice()
	{
		return invoice;
	}
	public void setInvoice(Invoice invoice)
	{
		this.invoice = invoice;
	}
	public Integer getJob_id()
	{
		return job_id;
	}
	public void setJob_id(Integer job_id)
	{
		this.job_id = job_id;
	}
	
	
	
	
}
