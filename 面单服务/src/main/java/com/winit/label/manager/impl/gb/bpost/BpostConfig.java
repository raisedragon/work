package com.winit.label.manager.impl.gb.bpost;

import com.winit.label.support.ConfigUtil;

public class BpostConfig
{
	public static final String		DSA					= "DSA";
	public static final String		MINIPAK				= "MPK";

	public static final String		BPOST_LABEL			= "bPost.jrxml";
	public static final String		BPOST_DSA_LABEL		= "bPost_DSA.jrxml";
	public static final String		BPOST_MINIPAK_LABEL	= "bPost_miniPak.jrxml";
	public static final String 		SEQ_RANGE_KEY = "wt_bPostBarcode_seq";
	public static String SITE_CODE(){
		return ConfigUtil.getValue("WT_BPOST_SITECODE");
	};
	
	public static String RETURN_ADDRESS_UK_FULLADDRESS()
	{
		return ConfigUtil.getValue("WT_UK_FULLADDRESS","");
	}
}
