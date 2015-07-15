package com.winit.label.manager.impl.gb.royalmail;

import org.springframework.stereotype.Service;

import com.winit.label.service.impl.BaseServiceImpl;

@Service
public class RoyalMailSoftCodeService   extends BaseServiceImpl
{
	
	protected static final String NAME_SPACE = RoyalMailSoftCode.class.getName();

	
	
	/**
	 * 查找邮编对应的SoftCode
	 * @param postcode 邮编
	 * @return
	 */
	public String getSoftCode(String postcode){
		if(postcode==null){
			return null;
		}
		String key = postcode.split("\\s")[0];
		
		return (String)this.single(NAME_SPACE+".getSoftCode", key);
	}
	
}
