package com.winit.label.manager.impl.de.deutschepost;

import com.winit.label.support.ConfigUtil;

public class DeutschePostConfig
{
	
	
	public static String REPORT_FILE = "DeutschePostLable.jrxml";
	
	
	
	public static String BARCODE_PREFIX(){
		return ConfigUtil.getValue("DEUTSCHE_POST_BARCODE_PREFIX");
	}
	
	public static String BARCODE_SUFFIX(){
		return ConfigUtil.getValue("DEUTSCHE_POST_BARCODE_SUFFIX");
	}
	
	
	public static String SENDER_COMPANYNAME(){
		return ConfigUtil.getValue("DEUTSCHE_POST_WINIT_COMPANY_NAME");
	}
	
	public static String SENDER_ADDRESS(){
		return ConfigUtil.getValue("DEUTSCHE_POST_WINIT_ADDRESS");
	}
	
	public static String SENDER_POSTCODE(){
		return ConfigUtil.getValue("DEUTSCHE_POST_WINIT_POSTCODE");
	}
	
	public static String SENDER_CITY(){
		return ConfigUtil.getValue("DEUTSCHE_POST_WINIT_CITY");
	}
	
	/**
	 * Original Number Sequence key
	 */
	public static String SEQ_GETORIGINAL_NUMBER = "wt_DeutschePostLable_seq";
}
