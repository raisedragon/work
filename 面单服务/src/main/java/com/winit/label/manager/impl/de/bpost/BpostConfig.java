package com.winit.label.manager.impl.de.bpost;

import com.winit.label.support.ConfigUtil;

public class BpostConfig
{
	public static String BPOST_DE_LABEL_FILENAME(){
		return ConfigUtil.getValue("BPOST_DE_LABEL_FILENAME","bpost_de.jrxml"); 
	}

	public static String BPOST_DE_02_LABEL_FILENAME ="bpost_de_02.jrxml"; 
	
	
	
	//BPOST指定德国仓地址信息
	public static String BPOST_DE_SENDER(){
		return ConfigUtil.getValue("BPOST_DE_SENDER", "bpost/ebay");
	}
	public static String BPOST_DE_ADDRESS1(){
		return ConfigUtil.getValue("BPOST_DE_ADDRESS1", "Postbus 71762");
	}
	public static String BPOST_DE_TOWN(){
		return ConfigUtil.getValue("BPOST_DE_TOWN", "PT Rotterdam");
	}
	public static String BPOST_DE_POSTCODE(){
		return ConfigUtil.getValue("BPOST_DE_POSTCODE", "3000");
	}
			
}
