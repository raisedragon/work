package com.winit.label.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winit.commons.cache.CCache;
import com.winit.commons.spring.AppUtil;
import com.winit.exception.LabelException;
import com.winit.label.model.SysConfig;
import com.winit.label.service.SysConfigService;


public class ConfigUtil
{
	
	private static SysConfigService sysConfigService;
	
	/**	Static Logger	*/
	private static Logger	s_log	= LoggerFactory.getLogger (ConfigUtil.class);
	/** Cache			*/
	private static CCache<String, String> s_cache = new CCache<String, String>(SysConfig.class.getName(), 40, 0);
	
	/** Reset Cache
	 * 
	 */
	public static void resetCache()
	{
		s_cache.reset();
	}
	/**
	 * Get system configuration property of type string
	 * @param Name
	 * @param defaultValue
	 * @return String
	 */
	public static String getValue(String Name, String defaultValue)
	{
		return getValue(Name, defaultValue, 0, 0);
	}
	
	/**
	 * Get system configuration property of type string
	 * @param Name
	 * @return String
	 */
	public static String getValue(String Name)
	{
		return getValue(Name, null);
	}
	
	/**
	 * Get system configuration property of type int
	 * @param Name
	 * @param defaultValue
	 * @return int
	 */
	public static int getIntValue(String Name, int defaultValue)
	{
		String s = getValue(Name);
		if (s == null)
			return defaultValue; 
		
		if (s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Integer.parseInt(s);
		}
		catch (NumberFormatException e)
		{
			s_log.error( "getIntValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}
	
	/**
	 * Get system configuration property of type double
	 * @param Name
	 * @param defaultValue
	 * @return double
	 */
	public static double getDoubleValue(String Name, double defaultValue)
	{
		String s = getValue(Name);
		if (s == null || s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Double.parseDouble(s);
		}
		catch (NumberFormatException e)
		{
			s_log.error( "getDoubleValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}
	
	/**
	 * Get system configuration property of type boolean
	 * @param Name
	 * @param defaultValue
	 * @return boolean
	 */
	public static boolean getBooleanValue(String Name, boolean defaultValue)
	{
		String s = getValue(Name);
		if (s == null || s.length() == 0)
			return defaultValue;
		
		if ("Y".equalsIgnoreCase(s))
			return true;
		else if ("N".equalsIgnoreCase(s))
			return false;
		else
			return Boolean.valueOf(s).booleanValue();
	}
	
	/**
	 * Get client configuration property of type string
	 * @param Name
	 * @param defaultValue
	 * @param Client ID
	 * @return String
	 */
	public static String getValue(String Name, String defaultValue, int AD_Client_ID)
	{
		return getValue(Name, defaultValue, AD_Client_ID, 0);
	}
	
	/**
	 * Get system configuration property of type string
	 * @param Name
	 * @param Client ID
	 * @return String
	 */
	public static String getValue(String Name, int AD_Client_ID)
	{
		return (getValue(Name, null, AD_Client_ID));
	}
	
	/**
	 * Get system configuration property of type int
	 * @param Name
	 * @param defaultValue
	 * @param Client ID
	 * @return int
	 */
	public static int getIntValue(String Name, int defaultValue, int AD_Client_ID)
	{
		String s = getValue(Name, AD_Client_ID);
		if (s == null)
			return defaultValue; 
		
		if (s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Integer.parseInt(s);
		}
		catch (NumberFormatException e)
		{
			s_log.error( "getIntValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}
	
	/**
	 * Get system configuration property of type double
	 * @param Name
	 * @param defaultValue
	 * @param Client ID
	 * @return double
	 */
	public static double getDoubleValue(String Name, double defaultValue, int AD_Client_ID)
	{
		String s = getValue(Name, AD_Client_ID);
		if (s == null || s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Double.parseDouble(s);
		}
		catch (NumberFormatException e)
		{
			s_log.error("getDoubleValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}
	
	/**
	 * Get system configuration property of type boolean
	 * @param Name
	 * @param defaultValue
	 * @param Client ID
	 * @return boolean
	 */
	public static boolean getBooleanValue(String Name, boolean defaultValue, int AD_Client_ID)
	{
		String s = getValue(Name, AD_Client_ID);
		if (s == null || s.length() == 0)
			return defaultValue;
		
		if ("Y".equalsIgnoreCase(s))
			return true;
		else if ("N".equalsIgnoreCase(s))
			return false;
		else
			return Boolean.valueOf(s).booleanValue();
	}

	private static void ensureInit(){
		if(sysConfigService!=null){
			return;
		}
		sysConfigService =(SysConfigService) AppUtil.getBean(SysConfigService.class);
		if(sysConfigService==null){
			throw new LabelException("ISysConfigService instance not found");
		}
	}
	
	
	/**
	 * Get client configuration property of type string
	 * @param Name
	 * @param defaultValue
	 * @param Client ID
	 * @param Organization ID
	 * @return String
	 */
	public static String getValue(String name, String defaultValue, int AD_Client_ID, int AD_Org_ID)
	{
		//TODO
		ensureInit();
		String key = name;
		String str = s_cache.get(key);
		if (str != null)
			return str;
		if (str == null && s_cache.containsKey(key)) // found null key
			return defaultValue;
		
		
		str = sysConfigService.getValue(key);
		
		
		//
		if (str != null) {
			str = str.trim();
			s_cache.put(key, str);
			return str;
		}
		else {
			// anyways, put the not found key as null
			s_cache.put(key, null);
			return defaultValue;
		}
	}
	
	/**
	 * Get system configuration property of type string
	 * @param Name
	 * @param Client ID
	 * @param Organization ID
	 * @return String
	 */
	public static String getValue(String Name, int AD_Client_ID, int AD_Org_ID)
	{
		return getValue(Name, null, AD_Client_ID, AD_Org_ID);
	}
	
	/**
	 * Get system configuration property of type int
	 * @param Name
	 * @param defaultValue
	 * @param Client ID
	 * @param Organization ID
	 * @return int
	 */
	public static int getIntValue(String Name, int defaultValue, int AD_Client_ID, int AD_Org_ID)
	{
		String s = getValue(Name, AD_Client_ID, AD_Org_ID);
		if (s == null)
			return defaultValue; 
		
		if (s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Integer.parseInt(s);
		}
		catch (NumberFormatException e)
		{
			s_log.error("getIntValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}
	
	/**
	 * Get system configuration property of type double
	 * @param Name
	 * @param defaultValue
	 * @param Client ID
	 * @param Organization ID
	 * @return double
	 */
	public static double getDoubleValue(String Name, double defaultValue, int AD_Client_ID, int AD_Org_ID)
	{
		String s = getValue(Name, AD_Client_ID, AD_Org_ID);
		if (s == null || s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Double.parseDouble(s);
		}
		catch (NumberFormatException e)
		{
			s_log.error( "getDoubleValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}
	
	/**
	 * Get system configuration property of type boolean
	 * @param Name
	 * @param defaultValue
	 * @param Client ID
	 * @param Organization ID
	 * @return boolean
	 */
	public static boolean getBooleanValue(String Name, boolean defaultValue, int AD_Client_ID, int AD_Org_ID)
	{
		String s = getValue(Name, AD_Client_ID, AD_Org_ID);
		if (s == null || s.length() == 0)
			return defaultValue;
		
		if ("Y".equalsIgnoreCase(s))
			return true;
		else if ("N".equalsIgnoreCase(s))
			return false;
		else
			return Boolean.valueOf(s).booleanValue();
	}
}
