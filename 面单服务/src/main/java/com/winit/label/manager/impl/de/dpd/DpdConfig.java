package com.winit.label.manager.impl.de.dpd;

import com.winit.label.support.ConfigUtil;

public class DpdConfig
{

	public static String WT_GERMANY_COMPANYNAME()
	{
		return ConfigUtil.getValue("WT_GERMANY_COMPANYNAME"); // Winit Germany
																// GmbH
	}

	public static String WT_GERMANY_CITY()
	{
		return ConfigUtil.getValue("WT_GERMANY_CITY"); // Bremen
	}

	public static String WT_GERMANY_ADDRESS1()
	{
		return ConfigUtil.getValue("WT_GERMANY_ADDRESS1"); // Merkur street 44
	}

	public static String WT_GERMANY_POSTCODE()
	{
		return ConfigUtil.getValue("WT_GERMANY_POSTCODE"); // 28197

	}

	public static String WT_DPD_USERNAME()
	{
		return ConfigUtil.getValue("WT_DPD_USERNAME"); // Winit888
	}

	public static String WT_DPD_PASSWORD()
	{
		return ConfigUtil.getValue("WT_DPD_PASSWORD"); // f9h55
	}

	public static String WT_DPD_CUSTOMER_NUMBER()
	{
		return ConfigUtil.getValue("WT_DPD_CUSTOMER_NUMBER"); // 3070001
	}

	public static String WT_DPD_LOGIN_API_URL()
	{
		return ConfigUtil.getValue("WT_DPD_LOGIN_API_URL"); // https://public-ws-stage.dpd.com/services/LoginService/V2_0/
	}

	public static String WT_DPD_SHIPMENT_API_URL()
	{
		return ConfigUtil.getValue("WT_DPD_SHIPMENT_API_URL"); // https://public-ws-stage.dpd.com/services/ShipmentService/V2_0/
	}

	public static String WT_DPD_TRACKING_API_URL()
	{
		return ConfigUtil.getValue("WT_DPD_TRACKING_API_URL"); // https://public-ws-stage.dpd.com/services/ParcelLifeCycleService/V1_0/
	}



}