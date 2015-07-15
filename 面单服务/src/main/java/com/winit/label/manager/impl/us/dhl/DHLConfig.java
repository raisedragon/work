package com.winit.label.manager.impl.us.dhl;

import java.util.HashSet;
import java.util.Set;

import com.winit.label.manager.impl.us.dhl.model.datatypes_global.DutyTaxPaymentType;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.LabelImageFormat;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.PiecesEnabled;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.RegionCode;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.ShipmentPaymentType;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.YesNo;
import com.winit.label.support.ConfigUtil;


public class DHLConfig {
	
	public static String SITE_ID(){
		return ConfigUtil.getValue("WT_US_DHL_SITE_ID","CustomerTest");
	}
	
	public static String PASSWORD(){
		return ConfigUtil.getValue("WT_US_DHL_PASSWORD","alkd89nBV");
	}
	
	public static PiecesEnabled PIECES_ENABLED(){
		return PiecesEnabled.Y;
	}
	
	public static String SHIPPER_ACCOUNT_NUMBER(){
		return ConfigUtil.getValue("WT_US_DHL_SHIPPER_ACCOUNT_NUMBER","803921577");
	}
	
	public static ShipmentPaymentType SHIPPING_PAYMENT_TYPE(){
		String v= ConfigUtil.getValue("WT_US_DHL_SHIPPING_PAYMENT_TYPE","S");
		return ShipmentPaymentType.fromValue(v);
	}
	
	public static String BILLING_ACCOUNT_NUMBER(){
		return ConfigUtil.getValue("WT_US_DHL_BILLING_ACCOUNT_NUMBER","803921577");
	}
	
	
	public static DutyTaxPaymentType DUTY_PAYMENT_TYPE(){
		String v =  ConfigUtil.getValue("WT_US_DHL_DUTY_PAYMENT_TYPE","S");
		return DutyTaxPaymentType.fromValue(v);
	}
	
	public static String DUTY_ACCOUNT_NUMBER(){
		return ConfigUtil.getValue("WT_US_DHL_DUTY_ACCOUNT_NUMBER","803921577");
	}

	public static YesNo REQUESTED_PICKUP_TIME() {
		String val = ConfigUtil.getValue("WT_US_DHL_REQUESTED_PICKUP_TIME","N");
		return YesNo.fromValue(val);
	}
	
	public static String SENDER_SHIPPER_ID (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_SHIPPER_ID","803921577");
	}
	public static String SENDER_COMPANY_NAME (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_COMPANY_NAME","WINIT");
	}
	public static String SENDER_REGISTERED_ACCOUNT (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_REGISTERED_ACCOUNT","803921577");
	}
	public static String SENDER_ADDRESS1 (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_ADDRESS1","adress1");
	}
	public static String SENDER_ADDRESS2 (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_ADDRESS2","adress2");
	}
	public static String SENDER_ADDRESS3 (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_ADDRESS3","address3");
	}
	public static String SENDER_CITY  (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_CITY","Tempe");
	}
	public static String SENDER_DIVISION  (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_DIVISION","New Yuo");
	} 
	public static String SENDER_DIVISION_CODE (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_DIVISION_CODE","NY");
	}
	public static String SENDER_POSTAL_CODE  (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_POSTAL_CODE","85281");
	}
	public static String SENDER_COUNTRY_CODE (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_COUNTRY_CODE","US");
	}
	public static String SENDER_COUNTRY_NAME (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_COUNTRY_NAME","United States");
	}
	public static String SENDER_CONTACT_PERSON_NAME  (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_CONTACT_PERSON_NAME","WINIT");
	}
	public static String SENDER_CONTACT_PHONE_NUMBER  (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_CONTACT_PHONE_NUMBER","1234567890");
	}
	public static String SENDER_CONTACT_PHONE_EXTENSION  (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_CONTACT_PHONE_EXTENSION");
	}
	public static String SENDER_CONTACT_FAX_NUMBER  (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_CONTACT_FAX_NUMBER");
	}
	public static String SENDER_CONTACT_TELEX  (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_CONTACT_TELEX");
	}
	public static String SENDER_CONTACT_EMAIL  (){
		return ConfigUtil.getValue("WT_US_DHL_SENDER_CONTACT_EMAIL");
	}

	public static LabelImageFormat LABEL_IMAGE_FORMAT() {
		String v =  ConfigUtil.getValue("WT_US_DHL_LABEL_IMAGE_FORMAT",LabelImageFormat.ZPL_2.toString());
		return LabelImageFormat.fromValue(v);
	}

	public static String LABEL_TEMPLATE() {
		return ConfigUtil.getValue("WT_US_DHL_LABEL_TEMPLATE","6X4_thermal");
	}

	public static YesNo LABEL_LOGO() {
		String v =  ConfigUtil.getValue("WT_US_DHL_LABEL_LOGO",YesNo.Y.toString());
		return YesNo.fromValue(v);
	}
	
	public static String DEFAULT_PHONE_NUM(){
		return ConfigUtil.getValue("WT_US_DHL_DEFAULT_PHONE_NUM","0");
	}
	
	public static  String LANGUAGE_CODE() {
		return ConfigUtil.getValue("WT_US_DHL_LANGUAGE_CODE","en");
	}

	public static String SERVICE_URL() {
		return   ConfigUtil.getValue("WT_US_DHL_SERVICE_URL","https://xmlpitest-ea.dhl.com/XMLShippingServlet?isUTF8Support=true");
	}
	
	
	public static String TRACKING_ACTION_STATUS_SUCCESS(){
		return   ConfigUtil.getValue("WT_US_DHL_TRACKING_ACTION_STATUS_SUCCESS","success");
	}
	

	public static String TRANSPORTMODE_IDS(){
		return  ConfigUtil.getValue("WT_US_DHL_TRANSPORTMODE_IDS","");
	}
	
	public static Set<String> TRACKING_DELIVERED_EVENT_CODES(){
		String strs =  ConfigUtil.getValue("WT_US_DHL_TRACKING_DELIVERED_EVENT_CODES","OK");
		String[] strAry = strs.split(",");
		Set<String> set = new HashSet<String>();
		for(String str:strAry){
			set.add(str);
		}
		return set;
		
	}
	
	public static RegionCode REGION_CODE() {
		return RegionCode.AM;
	}
	
	
	public static Set<String> SHIPMENT_VALID_SVR_SYS_ERR_CODES(){
		String strs =  ConfigUtil.getValue("WT_US_DHL_SHIPMENT_VALID_SVR_SYS_ERR_CODES","108,211");
		String[] strAry = strs.split(",");
		Set<String> set = new HashSet<String>();
		for(String str:strAry){
			set.add(str);
		}
		return set;
	}
	
}
