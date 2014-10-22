package com.winit.label.manager.impl.us.usps.domestic;

import org.apache.commons.lang.StringUtils;

import com.winit.commons.Constants;
import com.winit.label.support.ConfigUtil;

public class UspsDomesticConfig
{
	public static String SERVICE_URL(){
		return ConfigUtil.getValue(Constants.SYSCONFIG,"WT_USPS_LABEL_URL");
	}
	
	public static String SERVICE_USERNAME(){
		return ConfigUtil.getValue(Constants.SYSCONFIG,"WT_USPS_USERNAME");
	}
	public static String SERVICE_PASSWORD(){
		return ConfigUtil.getValue(Constants.SYSCONFIG,"WT_USPS_PASSWORD");
	}
	
	public static String SENDER_PHONENUMBER(){
		return ConfigUtil.getValue(Constants.SYSCONFIG,"WT_UPS_PHONENUMBER");
	}
	
	public static String SENDER_ADDRESSLINE1(){
		return ConfigUtil.getValue(Constants.SYSCONFIG,"WT_UPS_ADDRESSLINE1");
	}
	
	public static String SENDER_ADDRESSLINE2(){
		return ConfigUtil.getValue(Constants.SYSCONFIG,"WT_UPS_ADDRESSLINE2");
	}
	public static String FIRST_CLASS_CONTAINER(){
		return ConfigUtil.getValue(Constants.SYSCONFIG,"WT_USPS_FIRST-CLASS_CONTAINER");
	}
	public static String FIRST_PRIORITY_CONTAINER(){
		return ConfigUtil.getValue(Constants.SYSCONFIG,"WT_USPS_PRIORITY_CONTAINER");
	}
	
	public static String SENDER_NAME(){
		return ConfigUtil.getValue(Constants.SYSCONFIG,"WT_UPS_NAME");
	}
	public static String SENDER_CITY(){
		return ConfigUtil.getValue(Constants.SYSCONFIG,"WT_UPS_CITY");
	}
	public static String SENDER_PROVINCECODE(){
		return 	ConfigUtil.getValue(Constants.SYSCONFIG,"WT_UPS_PROVINCECODE");
	}
	public static String SENDER_POSTALCODE(){
		return 	ConfigUtil.getValue(Constants.SYSCONFIG,"WT_UPS_POSTALCODE");
	}
	
	
	
}
