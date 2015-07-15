package com.winit.label.manager.impl.de.deutschepost.oneclickforapp;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import com.winit.label.manager.impl.de.deutschepost.oneclickforapp.model.Address;
import com.winit.label.support.ConfigUtil;

/**
 * 
 * DE DeutschePost oneclickforapp 配置实用类。
 * <br/>设置类属性为final，且构造函数为私有类型，防止类被实例化。
 * @author longsheng.wang
 *
 */
public final class DeutschePostConfig {
	/**
	 * 构造函数为私有类型，防止类被实例化。
	 */
	private DeutschePostConfig(){}
	
	/**
	 * 服务接口地址
	 * @return
	 */
	public static String SERVICE_URL(){
		return getConfigBlankAsNull("DE_DEPOST_1C4APP_SERVICE_URL", "https://internetmarke.deutschepost.de/OneClickForAppV2/OneClickForAppServiceV2");
	}
	
	/**
	 * 服务账号
	 * @return
	 */
	public static String SERVICE_USERNAME(){
		return getConfigBlankAsNull("DE_DEPOST_1C4APP_SERVICE_USERNAME", "pcf_10@zq4nnzgbnbvt3.webpage.t-com.de");
	}
	
	/**
	 * 服务密码
	 * @return
	 */
	public static String SERVICE_PASSWORD(){
		return getConfigBlankAsNull("DE_DEPOST_1C4APP_SERVICE_PASSWORD", "Wipupo85");
	}
	
	/**
	 * 服务请求报文的时区
	 * @return
	 */
	public static String SERVICE_TIME_ZONE(){
		return getConfigBlankAsNull("DE_DEPOST_1C4APP_SERVICE_TIME_ZONE", "Europe/Berlin");
	}
	
	/**
	 * 服务分配的PARTNER ID
	 * @return
	 */
	public static String SERVICE_PARTNER_ID(){
		return getConfigBlankAsNull("DE_DEPOST_1C4APP_SERVICE_PARTNER_ID", "ASDWI");
	}
	
	/**
	 * 服务分配的SCHLUESSEL DPWN MEINMARKTPLATZ
	 * @return
	 */
	public static String SERVICE_SCHLUESSEL_DPWN_MEINMARKTPLATZ(){
		return getConfigBlankAsNull("DE_DEPOST_1C4APP_SERVICE_SCHLUESSEL_DPWN_MEINMARKTPLATZ", "cUj7QWYPfRCXoKY2IUYHSe4bauIUPOZi");
	}
	
	public static String SERVICE_KEY_PHASE(){
		return getConfigBlankAsNull("DE_DEPOST_1C4APP_SERVICE_KEY_PHASE", "1");
	}

	public static String SENDER_COMPANY(){
		return getConfigBlankAsNull("DE_DEPOST_1C4APP_SENDER_COMPANY", "Winit Germany GmbH");
	}

	public static String SENDER_CITY(){
		return getConfigBlankAsNull("DE_DEPOST_1C4APP_SENDER_CITY", "Bremen");
	}
	
	public static String SENDER_COUNTRY(){
		return getConfigBlankAsNull("DE_DEPOST_1C4APP_SENDER_COUNTRY", "DE");
	}
	public static String SENDER_HOUSE_NO(){
		return getConfigBlankAsNull("DE_DEPOST_1C4APP_SENDER_HOUSE_NO", "49");
	}
	public static String SENDER_STREET(){
		return getConfigBlankAsNull("DE_DEPOST_1C4APP_SENDER_SENDER_STREET", "Martinistr");
	}
	public static String SENDER_ZIP(){
		return getConfigBlankAsNull("DE_DEPOST_1C4APP_SENDER_ZIP", "28197");
	}
	public static String DEFAULT_HOUSE_NO(){
		return getConfigBlankAsNull("DE_DEPOST_1C4APP_SENDER_HOUSE_NO", ".");
	}
	
	private static String getConfigBlankAsNull(String key){
		return getConfigBlankAsNull(key,null);
	}
	
	private static String getConfigBlankAsNull(String key,String defVal){
		String val = ConfigUtil.getValue(key,defVal);
		if(val==null){
			return val;
		}
		if(StringUtils.isBlank(val)){
			return defVal;
		}
		return val;
	}
}
