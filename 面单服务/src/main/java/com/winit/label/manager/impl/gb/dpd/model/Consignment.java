package com.winit.label.manager.impl.gb.dpd.model;


public class Consignment
{
	protected AuditInfo auditInfo;
	protected CollectionDetails collectionDetails;
	protected String consignmentNumber ;
	protected String consignmentRef ;
	protected float customsValue ;
	protected DeliveryDetails deliveryDetails;
	
	protected String deliveryInstructions ;
	protected boolean liability ;
	protected float liabilityValue ;
	protected String networkCode ;
	protected int numberOfParcels ;
	protected Parcel[] parcels ;
	protected String parcelDescription ;
	protected String shippingRef1 ;
	protected String shippingRef2 ;
	protected String shippingRef3 ;
	protected float totalWeight ;
	public AuditInfo getAuditInfo()
	{
		return auditInfo;
	}
	public void setAuditInfo(AuditInfo auditInfo)
	{
		this.auditInfo = auditInfo;
	}
	public CollectionDetails getCollectionDetails()
	{
		return collectionDetails;
	}
	public void setCollectionDetails(CollectionDetails collectionDetails)
	{
		this.collectionDetails = collectionDetails;
	}
	public String getConsignmentNumber()
	{
		return consignmentNumber;
	}
	public void setConsignmentNumber(String consignmentNumber)
	{
		this.consignmentNumber = consignmentNumber;
	}
	public String getConsignmentRef()
	{
		return consignmentRef;
	}
	public void setConsignmentRef(String consignmentRef)
	{
		this.consignmentRef = consignmentRef;
	}
	public float getCustomsValue()
	{
		return customsValue;
	}
	public void setCustomsValue(float customsValue)
	{
		this.customsValue = customsValue;
	}
	public DeliveryDetails getDeliveryDetails()
	{
		return deliveryDetails;
	}
	public void setDeliveryDetails(DeliveryDetails deliveryDetails)
	{
		this.deliveryDetails = deliveryDetails;
	}
	public String getDeliveryInstructions()
	{
		return deliveryInstructions;
	}
	public void setDeliveryInstructions(String deliveryInstructions)
	{
		this.deliveryInstructions = deliveryInstructions;
	}
	public boolean isLiability()
	{
		return liability;
	}
	public void setLiability(boolean liability)
	{
		this.liability = liability;
	}
	public float getLiabilityValue()
	{
		return liabilityValue;
	}
	public void setLiabilityValue(float liabilityValue)
	{
		this.liabilityValue = liabilityValue;
	}
	public String getNetworkCode()
	{
		return networkCode;
	}
	public void setNetworkCode(String networkCode)
	{
		this.networkCode = networkCode;
	}
	public int getNumberOfParcels()
	{
		return numberOfParcels;
	}
	public void setNumberOfParcels(int numberOfParcels)
	{
		this.numberOfParcels = numberOfParcels;
	}
	
	public Parcel[] getParcels()
	{
		if(parcels==null){
			parcels = new Parcel[]{};
		}
		return parcels;
	}
	public void setParcels(Parcel[] parcels)
	{
		this.parcels = parcels;
	}
	public String getParcelDescription()
	{
		return parcelDescription;
	}
	public void setParcelDescription(String parcelDescription)
	{
		this.parcelDescription = parcelDescription;
	}
	public String getShippingRef1()
	{
		return shippingRef1;
	}
	public void setShippingRef1(String shippingRef1)
	{
		this.shippingRef1 = shippingRef1;
	}
	public String getShippingRef2()
	{
		return shippingRef2;
	}
	public void setShippingRef2(String shippingRef2)
	{
		this.shippingRef2 = shippingRef2;
	}
	public String getShippingRef3()
	{
		return shippingRef3;
	}
	public void setShippingRef3(String shippingRef3)
	{
		this.shippingRef3 = shippingRef3;
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
