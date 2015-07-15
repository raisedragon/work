package com.winit.label.manager.impl.de.dhl30;

import com.winit.label.support.ConfigUtil;

public class Dhl30Config
{
	public static String	BARCODE_SEQ_KEY	= "WT_dhlDeBarcode_seq";
	public static String DHL30_LABEL_FILENAME="Dhl_30Lable.jrxml";

	public static String WINIT_BRANCH_CODE()
	{
		return ConfigUtil.getValue("WINIT_BRANCH_CODE"); // 55
	}

	public static String DEFAULT_BRANCH_CODE()
	{
		return ConfigUtil.getValue("DEFAULT_BRANCH_CODE");
	} // 00


	// 配置变量
	public static String SEND_COMPANY()
	{
		return ConfigUtil.getValue("DHL_SEND_COMPANY");
	}

	public static String SEND_ADDRESS()
	{
		return ConfigUtil.getValue("DHL_SEND_ADDRESS");
	}

	public static String COUNTRY_NAME_POSTAL()
	{
		return ConfigUtil.getValue("COUNTRY_NAME_POSTAL");
	}

	public static String BARCODE_NO()
	{
		return ConfigUtil.getValue("DHL_WINIT_BRAN_CHCODE");
	}

}
