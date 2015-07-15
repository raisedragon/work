package com.winit.label.manager.impl.gb.dpd.model;

public class InsertShipmentOutputData
{
	protected boolean				consolidated;
	protected long					shipmentId;
	protected ConsignmentDetail[]	consignmentDetail;

	public boolean isConsolidated()
	{
		return consolidated;
	}

	public void setConsolidated(boolean consolidated)
	{
		this.consolidated = consolidated;
	}

	public long getShipmentId()
	{
		return shipmentId;
	}

	public void setShipmentId(long shipmentId)
	{
		this.shipmentId = shipmentId;
	}

	public ConsignmentDetail[] getConsignmentDetail()
	{
		return consignmentDetail;
	}

	public void setConsignmentDetail(ConsignmentDetail[] consignmentDetail)
	{
		this.consignmentDetail = consignmentDetail;
	}

}
