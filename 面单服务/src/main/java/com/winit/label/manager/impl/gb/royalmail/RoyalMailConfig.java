package com.winit.label.manager.impl.gb.royalmail;

import com.winit.label.support.ConfigUtil;

public class RoyalMailConfig
{
	public static final String	ROYALMAIL_TRACKED_48H						= "TPL01";
	public static final String	ROYALMAIL_48H_NONTRACKED					= "RM-NONTRACKED";

	public static final String	ROYALMAIL_TRACKED_24_REPORT					= "RoyalMailTracked_24.jrxml";
	public static final String	ROYALMAIL_TRACKED_48H_REPORT				= "RoyalMailTracked_48H.jrxml";
	public static final String	ROYALMAIL_48H_NONTRACKED_REPORT				= "RoyalMail48H_NonTracked.jrxml";
	public static final String	ROYALMAIL_NONTRACKED_STL_SIGNATURE_REPORT	= "RoyalMail_NonTracked_STL_Signature.jrxml";

	/** RoyalMail Tracked 24 w/Signature */
	public static String PRODUCT_PREFIX_24_SIGN()
	{
		return ConfigUtil.getValue("WT_ROYALMAIL_PRODUCT_PREFIX_24_SIGN");
	}

	/** RoyalMail Tracked 24 non-Signature */
	public static String PRODUCT_PREFIX_24_NO_SIGN()
	{
		return ConfigUtil.getValue("WT_ROYALMAIL_PRODUCT_PREFIX_24_NO_SIGN");
	}

	/** RoyalMail Tracked 48 (Hi-vol.) w/Signature */
	public static String PRODUCT_PREFIX_48_SIGN()
	{
		return ConfigUtil.getValue("WT_ROYALMAIL_PRODUCT_PREFIX_48_SIGN");
	}

	/** RoyalMail Tracked 48 (Hi-vol.) non-Signature */
	public static String PRODUCT_PREFIX_48_NO_SIGN()
	{
		return ConfigUtil.getValue("WT_ROYALMAIL_PRODUCT_PREFIX_48_NO_SIGN");
	}
	
	public static String SORT_CODE(){
		return ConfigUtil.getValue("WT_ROYALMAIL_PRODUCT_PREFIX_48_NO_SIGN");
	}

	public static String RETURN_ADDRESS_UK_FULLADDRESS()
	{
		return ConfigUtil.getValue("WT_UK_FULLADDRESS","");
	}
	public static String RETURN_ADDRESS_ROYALMAIL_NONTRACKED_POBOX()
	{
		return ConfigUtil.getValue("WT_RoyalMail_NonTracked_POBox","");
	}

}
