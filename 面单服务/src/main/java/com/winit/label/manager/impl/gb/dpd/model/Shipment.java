package com.winit.label.manager.impl.gb.dpd.model;

import java.util.Date;

public class Shipment
{
protected Date collectionDate ;
protected Consignment[] consignment ;
protected Invoice invoice ;
protected boolean isPrinted ;
protected boolean isVoided ;
protected Long manifestId ;
protected Long shipmentId ;
protected Long shipmentType ;
protected Long userId ;
public Date getCollectionDate()
{
	return collectionDate;
}
public void setCollectionDate(Date collectionDate)
{
	this.collectionDate = collectionDate;
}
public Consignment[] getConsignment()
{
	return consignment;
}
public void setConsignment(Consignment[] consignment)
{
	this.consignment = consignment;
}
public Invoice getInvoice()
{
	return invoice;
}
public void setInvoice(Invoice invoice)
{
	this.invoice = invoice;
}
public boolean isPrinted()
{
	return isPrinted;
}
public void setPrinted(boolean isPrinted)
{
	this.isPrinted = isPrinted;
}
public boolean isVoided()
{
	return isVoided;
}
public void setVoided(boolean isVoided)
{
	this.isVoided = isVoided;
}
public Long getManifestId()
{
	return manifestId;
}
public void setManifestId(Long manifestId)
{
	this.manifestId = manifestId;
}
public Long getShipmentId()
{
	return shipmentId;
}
public void setShipmentId(Long shipmentId)
{
	this.shipmentId = shipmentId;
}
public Long getShipmentType()
{
	return shipmentType;
}
public void setShipmentType(Long shipmentType)
{
	this.shipmentType = shipmentType;
}
public Long getUserId()
{
	return userId;
}
public void setUserId(Long userId)
{
	this.userId = userId;
}


}
