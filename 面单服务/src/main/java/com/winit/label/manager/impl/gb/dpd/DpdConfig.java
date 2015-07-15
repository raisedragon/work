package com.winit.label.manager.impl.gb.dpd;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.winit.label.support.ConfigUtil;

public class DpdConfig
{
	
	public static String SENDER_ORGANISATION(){
		return ConfigUtil.getValue("GB_DPD_SENDER_ORGANISATION","").trim();
	}
	
	public static String SENDER_COUNTRY_CODE(){
		return ConfigUtil.getValue("GB_DPD_SENDER_COUNTRY_CODE","").trim();
	}
	
	public static String SENDER_POST_CODE(){
		return ConfigUtil.getValue("GB_DPD_SENDER_POST_CODE","").trim();
	}
	
	
	public static String SENDER_STREET(){
		return ConfigUtil.getValue("GB_DPD_SENDER_STREET","").trim();
	}
	
	public static String SENDER_LOCALITY(){
		return ConfigUtil.getValue("GB_DPD_SENDER_LOCALITY","").trim();
	}
	
	
	public static String SENDER_TOWN(){
		return ConfigUtil.getValue("GB_DPD_SENDER_TOWN","").trim();
	}
	
	
	public static String SENDER_COUNTY(){
		return ConfigUtil.getValue("GB_DPD_SENDER_COUNTY","").trim();
	}
	
	public static String SENDER_TELEPHONE(){
		return ConfigUtil.getValue("GB_DPD_SENDER_TELEPHONE","").trim();
	}
	
	public static String SENDER_CONTACT_NAME()
	{
		return ConfigUtil.getValue("GB_DPD_SENDER_CONTACT_NAME","").trim();
	}
	
	public static String USER_NAME(){
		return ConfigUtil.getValue("GB_DPD_USER_NAME","").trim();
	}
	
	public static String PASSWORD(){
		return ConfigUtil.getValue("GB_DPD_PASSWORD","").trim();
	}
	public static String HOST(){
		return ConfigUtil.getValue("GB_DPD_HOST","").trim();
	}
	
	public static String TRACKING_HOST(){
		return ConfigUtil.getValue("GB_DPD_TRACKING_HOST","").trim();
	}
	public static String TRACKING_USERNAME(){
		return ConfigUtil.getValue("GB_DPD_TRACKING_USERNAME","").trim();
	}
	public static String TRACKING_PASSWORD(){
		return ConfigUtil.getValue("GB_DPD_TRACKING_PASSWORD","").trim();
	}
	
	public static Set<Long> DPD_TRANSPORT_MODE_IDS(){
		Set<Long> ids = new HashSet<Long>();
		String idStr =  ConfigUtil.getValue("WT_DPD_ID","").trim();
		String[] idAry = idStr.split(",");
		for(String id:idAry){
			if(StringUtils.isNumeric(id)){
				ids.add(Long.valueOf(id));
			}
		}
		
		if(ids.isEmpty()){
			ids.add(0l);
		}
		
		return ids;
	}
	
	public static Set<String> TRACKING_EVENT_CODE_DELIVERED(){
		Set<String> ids = new HashSet<String>();
		String idStr =  ConfigUtil.getValue("GB_DPD_TRACKING_EVENT_CODE_DELIVERED","").trim();
		String[] idAry = idStr.split(",");
		for(String id:idAry){
			if(StringUtils.isNotEmpty(id)){
				ids.add(id);
			}
		}
		
		return ids;
	}
	
	
	public static int TRACKING_BATCH_COUNT(){
		int count =  ConfigUtil.getIntValue("GB_DPD_TRACKING_BATCH_COUNT",10);
		return count;
	}
	

	public static List<String> LOGISTIC_DIST_ALL_MATCH_POSTAL_GROUP(){
		String strs = ConfigUtil.getValue("GB_DPD_LOGISTIC_DIST_ALL_MATCH_POSTAL_GROUP","").trim();
		String[] strAry = StringUtils.split(strs,",");
		return Arrays.asList(strAry);
	}
	
	public static String SERVICE_BY_LOGISTIC_DIST(String logisticsModeName,String distName){
		String key = "GB_DPD_SERVICE_"+logisticsModeName+"_"+distName;
		return ConfigUtil.getValue(key);
	}


}
