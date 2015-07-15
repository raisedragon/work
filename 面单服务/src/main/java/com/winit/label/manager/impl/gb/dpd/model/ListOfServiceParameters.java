package com.winit.label.manager.impl.gb.dpd.model;


public class ListOfServiceParameters
{
	protected Integer businessUnit; 
	protected CollectionDetails collectionDetails ;
	protected DeliveryDetails deliveryDetails ;
	protected Integer deliveryDirection ;
	protected Integer numberOfParcels ;
	protected Integer shipmentType ;
	protected Float totalWeight ;
	public Integer getBusinessUnit()
	{
		return businessUnit;
	}
	public void setBusinessUnit(Integer businessUnit)
	{
		this.businessUnit = businessUnit;
	}
	public CollectionDetails getCollectionDetails()
	{
		return collectionDetails;
	}
	public void setCollectionDetails(CollectionDetails collectionDetails)
	{
		this.collectionDetails = collectionDetails;
	}
	public DeliveryDetails getDeliveryDetails()
	{
		return deliveryDetails;
	}
	public void setDeliveryDetails(DeliveryDetails deliveryDetails)
	{
		this.deliveryDetails = deliveryDetails;
	}
	public Integer getDeliveryDirection()
	{
		return deliveryDirection;
	}
	public void setDeliveryDirection(Integer deliveryDirection)
	{
		this.deliveryDirection = deliveryDirection;
	}
	public Integer getNumberOfParcels()
	{
		return numberOfParcels;
	}
	public void setNumberOfParcels(Integer numberOfParcels)
	{
		this.numberOfParcels = numberOfParcels;
	}
	public Integer getShipmentType()
	{
		return shipmentType;
	}
	public void setShipmentType(Integer shipmentType)
	{
		this.shipmentType = shipmentType;
	}
	public Float getTotalWeight()
	{
		return totalWeight;
	}
	public void setTotalWeight(Float totalWeight)
	{
		this.totalWeight = totalWeight;
	}
	
	
}
