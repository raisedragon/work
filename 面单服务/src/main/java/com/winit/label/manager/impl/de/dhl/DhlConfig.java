package com.winit.label.manager.impl.de.dhl;

import com.winit.label.support.ConfigUtil;

public class DhlConfig
{
	public static String DHLDE_LABEL_USERNAME()
	{
		return ConfigUtil.getValue("DHLDE_LABEL_USERNAME");
	}

	public static String DHLDE_LABEL_PASSWORD()
	{
		return ConfigUtil.getValue("DHLDE_LABEL_PASSWORD");
	}

	public static String DHLDE_LABEL_USER()
	{
		return ConfigUtil.getValue("DHLDE_LABEL_USER");
	}

	public static String DHLDE_LABEL_SIGNATURE()
	{
		return ConfigUtil.getValue("DHLDE_LABEL_SIGNATURE");
	}

	public static String DHLDE_LABEL_URL()
	{
		return ConfigUtil.getValue("DHLDE_LABEL_URL");
	}

	public static String DHLDE_LABEL_FILENAME()
	{
		return ConfigUtil.getValue("DHLDE_LABEL_FILENAME");
	}

	public static String DHLDE_LABEL_EKP()
	{
		return ConfigUtil.getValue("DHLDE_LABEL_EKP");
	}

	public static String DHLDE_LABEL_PARTNERID()
	{
		return ConfigUtil.getValue("DHLDE_LABEL_PARTNERID");
	}

	public static String DHLDE_LABEL_PARTNERID_BPI()
	{
		return ConfigUtil.getValue("DHLDE_LABEL_PARTNERID_BPI");
	}

	public static String DHLDE_LABLE_COMPANYNAME()
	{
		return ConfigUtil.getValue("DHLDE_LABLE_COMPANYNAME");
	}

	public static String DHLDE_LABLE_CITY()
	{
		return ConfigUtil.getValue("DHLDE_LABLE_CITY");
	}

	public static String DHLDE_LABLE_ADDRESS1()
	{
		return ConfigUtil.getValue("DHLDE_LABLE_ADDRESS1");
	}

	public static String DHLDE_LABLE_POSTCODE()
	{
		return ConfigUtil.getValue("DHLDE_LABLE_POSTCODE");
	}
}
