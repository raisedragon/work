package com.winit.label.manager.impl.us.usps.intl;

import com.winit.label.support.ConfigUtil;

public class UspsIntlConfig
{
	public static final String MANIFEST_FILE_FORMAT_VERSION = "8";
	
	public static String SENDER_NAME(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SENDER_NAME","").trim();
	}

	public static String SENDER_FIRST_NAME(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SENDER_FIRST_NAME","").trim();
	}

	public static String SENDER_MIDDLE_INITIAL(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SENDER_MIDDLE_INITIAL","").trim();
	}

	public static String SENDER_LAST_NAME(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SENDER_LAST_NAME","").trim();
	}

	public static String SENDER_BUSINESS_NAME(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SENDER_BUSINESS_NAME","").trim();
	}

	public static String SENDER_ADDRESS_LINE1(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SENDER_ADDRESS_LINE1","").trim();
	}

	public static String SENDER_ADDRESS_LINE2(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SENDER_ADDRESS_LINE2","").trim();
	}

	public static String SENDER_ADDRESS_LINE3(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SENDER_ADDRESS_LINE3","").trim();
	}

	public static String SENDER_CITY(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SENDER_CITY","").trim();
	}

	public static String SENDER_PROVINCE(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SENDER_PROVINCE","").trim();
	}

	public static String SENDER_POSTAL_CODE(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SENDER_POSTAL_CODE","").trim();
	}

	public static String SENDER_COUNTRY_CODE(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SENDER_COUNTRY_CODE","").trim();
	}

	public static String SENDER_ADDRESS_IS_PO_BOX(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SENDER_ADDRESS_IS_PO_BOX","").trim();
	}

	public static String SENDER_PHONE(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SENDER_PHONE","").trim();
	}

	public static String SENDER_EMAIL(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SENDER_EMAIL","").trim();
	}

	public static String SENDER_TAXPAYER_ID(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SENDER_TAXPAYER_ID","").trim();
	}


	
	public static String RECEIVING_AGENT_ID(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_RECEIVING_AGENT_ID", "").trim();
	}
	public static String SHIPPING_AGENT_ID(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SHIPPING_AGENT_ID", "").trim();
	}
	public static String MAILING_AGENT_ID(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_MAILING_AGENT_ID", "").trim();
	}
	
	public static String ALREADY_LOAD_ERR_MSG(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_ALREADY_LOAD_ERR_MSG", "").trim();
	}
	
	public static String LABLE_FORMAT(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_FORMAT", "").trim();
	}
	
	public static final String	RECIPIENT_ADDRESS_IS_PO_BOX	= "N";

	public static final String	PACKAGE_TYPE(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_PACKAGE_TYPE", "").trim();
	}

	public static final String	SHIPPING_CURRENCY_TYPE		= "USD";

	public static final String	WEIGHT_UNIT					= "KG";
	
	public static final String UNIT_OF_MEASUREMENT = "CM";
	
	public static String SERVICE_TYPE(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_SERVICE_TYPE", "").trim();
	}
//	
//	public static String RATE_TYPE(String code){
//		return (String) Context
//				.getCommandContext()
//				.getLogisticTypeManager()
//				.findByLogisticTypeCode(code)
//				.getVariable("serviceCode");
//	}
	
	public static String DOMESTIC_RATE_TYPE(){
		return ConfigUtil.getValue("USPS_DOMESTIC_RATE_TYPE", "").trim();
	}
	
	public static String TRACKING_ID_SEPARATOR(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_TRACKING_ID_SEPARATOR", "|");
	}
	
	
	public static  String	USPS_INTL_GSS_LABELING_USER_ID(){
		return ConfigUtil.getValue("USPS_INTL_GSS_LABELING_USER_ID");
	}
	public static  String	USPS_INTL_GSS_LABELING_PASSWORD	(){
		return ConfigUtil.getValue("USPS_INTL_GSS_LABELING_PASSWORD");
	}
	public static  String	USPS_INTL_GSS_LABELING_LOCATION_ID(){
		return ConfigUtil.getValue("USPS_INTL_GSS_LABELING_LOCATION_ID");
	}

	public static  String	USPS_INTL_GSS_USER_ID(){
		return ConfigUtil.getValue("USPS_INTL_GSS_USER_ID");
	}	
	public static  String	USPS_INTL_GSS_PASSWORD(){
		return ConfigUtil.getValue("USPS_INTL_GSS_PASSWORD");
	}		
	public static  String	USPS_INTL_GSS_LOCATION_ID	(){
		return ConfigUtil.getValue("USPS_INTL_GSS_LOCATION_ID");
	}	
	public static  String	USPS_INTL_GSS_WORKSTATION_ID(){
		return ConfigUtil.getValue("USPS_INTL_GSS_WORKSTATION_ID");
	}		
	public static  String  USPS_INTL_GSS_URL(){
		return ConfigUtil.getValue("USPS_INTL_GSS_URL");
	}
	
	public static final String	USPS_INTL_GSS_CONNECT_TIME_OUT	(){
		return ConfigUtil.getValue("USPS_INTL_GSS_CONNECT_TIME_OUT");
	}	
	public static final String	USPS_INTL_GSS_REQUEST_TIME_OUT	(){
		return ConfigUtil.getValue("USPS_INTL_GSS_REQUEST_TIME_OUT");
	}	
	
	public static final int	PACKAGE_PHYSICAL_COUNT		= 1;

	public static final String	POSTAGE_PAID_CURRENCY_TYPE	= "USD";
	public static final String	VALUE_LOADED				= "N";
	
	public static String PF_COR_EEL(){
		return ConfigUtil.getValue("USPS_INTL_LABEL_PF_COR_EEL", "NOEEI 30.37(a)");
	}

	public static final String	ITEM_VALUE_CURRENCY_TYPE	= "USD";

	public static final String	RETURN_SERVICE_REQUESTED	= "N";
}
