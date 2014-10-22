package com.winit.label.manager.impl.au.ubi;

import com.winit.label.support.ConfigUtil;

public class UbiConfig
{
	
	public static String UBI_SMARTPARCEL_ACCESS_KEY()
	{
		return ConfigUtil.getValue("WT_UBI_SMARTPARCEL_ACCESS_KEY");
	}

	public static String UBI_SMARTPARCEL_SECRET_KEY()
	{
		return ConfigUtil.getValue("WT_UBI_SMARTPARCEL_SECRET_KEY");
	}

	public static String UBI_SMARTPARCEL_API_URL()
	{
		return ConfigUtil.getValue("WT_UBI_SMARTPARCEL_API_URL");
	}

	public static String UBI_SMARTPARCEL_ORDER_PATH()
	{
		return ConfigUtil.getValue("WT_UBI_SMARTPARCEL_ORDER_PATH");
	}

	public static String UBI_SMARTPARCEL_LABELS_PATH()
	{
		return ConfigUtil.getValue("WT_UBI_SMARTPARCEL_LABELS_PATH");
	}

	public static String UBI_SMARTPARCEL_TRACKING_NUMBER_PATH()
	{
		return ConfigUtil.getValue("WT_UBI_SMARTPARCEL_TRACKING_NUMBER_PATH");
	}

	public static String UBI_SMARTPARCEL_MANIFEST_PATH()
	{
		return ConfigUtil.getValue("WT_UBI_SMARTPARCEL_MANIFEST_PATH");
	}

	public static String UBI_SMARTPARCEL_TRACKING_EVENT_PATH()
	{
		return ConfigUtil.getValue("WT_UBI_SMARTPARCEL_TRACKING_EVENT_PATH");
	}
	
}
