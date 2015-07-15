package com.winit.label.manager.impl.gb.yodel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.winit.label.support.ConfigUtil;

public abstract class YodelConfig
{
	
	public static String METER(){
		return ConfigUtil.getValue("WT_YODEL_METER");
	}
	
	
	public static String SERVICE_WEIGHT_RANGE(String serviceCode){
		return ConfigUtil.getValue(("WT_YODEL_SERVICE_WEIGHT_RANGE_"+serviceCode).toUpperCase());
	}
	
	public static String SERVICE_TYPE(String serviceCode){
		return ConfigUtil.getValue(("WT_YODEL_SERVICE_TYPE_"+serviceCode).toUpperCase());
	}
	
	public static String LABLE_FROM(){
		String address1 = ConfigUtil.getValue("WT_YODEL_SENDER_ADDRESS1");
		String address2 = ConfigUtil.getValue("WT_YODEL_SENDER_ADDRESS2");
		String city = ConfigUtil.getValue("WT_YODEL_SENDER_CITY");
		String postcode = ConfigUtil.getValue("WT_YODEL_SENDER_POSTCODE");
		
		List<String> from = new ArrayList<String>();
		if(StringUtils.isNotEmpty(address1)){
			from.add(address1);
		}
		if(StringUtils.isNotEmpty(address2)){
			from.add(address2);
		}
		if(StringUtils.isNotEmpty(city)){
			from.add(city);
		}
		if(StringUtils.isNotEmpty(postcode)){
			from.add(postcode);
		}
		return StringUtils.join(from.iterator(), ",");
	}
	
	/**
	 * Licence Plate Barcode Value prefix
	 * @return
	 */
	public static String LICENCE_PLATE_NUMBERS_PREFIX(){
		return ConfigUtil.getValue("WT_YODEL_LICENCE_PLATE_NUMBERS_PREFIX");
	}
	
	
	public static String GAZETTEER_FTP_HOST(){
		return ConfigUtil.getValue("WT_YODEL_GAZETTEER_FTP_HOST");
	}
	
	
	
	public static int GAZETTEER_FTP_PORT(){
		return ConfigUtil.getIntValue("WT_YODEL_GAZETTEER_FTP_PORT", 21);
	}
	
	public static String GAZETTEER_FTP_PATH(){
		return ConfigUtil.getValue("WT_YODEL_GAZETTEER_FTP_PATH");
	}
	public static String GAZETTEER_FTP_USERNAME()
	{
		return ConfigUtil.getValue("WT_YODEL_GAZETTEER_FTP_USERNAME");
	}

	public static String GAZETTEER_FTP_PASSWORD()
	{
		return ConfigUtil.getValue("WT_YODEL_GAZETTEER_FTP_PASSWORD");
	}
	public static String GAZETTEER_ZIP_FILE_NAME_TEMPLATE = "gaz%s.zip";
	public static String GAZETTEER_ACTIVATE_FILE_NAME_TEMPLATE = "ACTIVATE.%s";
	public static String GAZETTEER_VERSION_FILE_NAME_TEMPLATE = "VERSION.%s";
	public static String GAZETTEER_SERVICE_FILE_NAME_TEMPLATE	= "SERVICE.%s";
	public static String GAZETTEER_REAMUSID_FILE_NAME_TEMPLATE	= "REAMUSID.%s";
	public static String GAZETTEER_DESTINATION_STATION_FILE_NAME_TEMPLATE	= "DESTINATION_STATION.%s";
	public static String GAZETTEER_DESTINATION_PRDSERVICES_FILE_NAME_TEMPLATE	= "DESTINATION_PRDSERVICES.%s";
	public static String GAZETTEER_DESTINATION_EXCEPTION_FILE_NAME_TEMPLATE	= "DESTINATION_EXCEPTION.%s";
	public static String GAZETTEER_ZIP_FILE_NAME = "gaz.{3}\\.zip";
	
	public static String IN_USERED_SERVICES_STR()
	{
		return ConfigUtil.getValue("WT_YODEL_IN_USERED_SERVICES_STR");
	}
	
	
	
	public static String TRACKNO_SEQ_KEY(){
		return  "WT_YODEL_TRACKNO_SEQ";
	}
}
	
	
