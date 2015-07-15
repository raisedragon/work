package com.winit.label.manager.impl.gb.yodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.winit.label.service.impl.BaseServiceImpl;

@Component
public class YodelDestExceptionService extends BaseServiceImpl
{
	private String NAME_SPACE = YodelDestException.class.getName();
	
	
	public void add(YodelDestException entity){
		this.save(NAME_SPACE+".insert", entity);
	}
	
	
	/**
	 * 
	 * @param productCode
	 * @param featureCode
	 * @param version
	 * @param country
	 * @param postcode
	 * @return
	 */
	public YodelDestException getByProductCodeAndVersion(String productCode,
			String featureCode, String version, String country, String postcode)
	{
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("productCode", productCode);
		map.put("featureCode", featureCode);
		map.put("version", version);
		map.put("countryCode", country);
		map.put("postcode", postcode);
		return (YodelDestException) this.single(NAME_SPACE+".getByProductCodeAndVersion", map);
	}
	
	public List<YodelDestStation> getByPostCode(String countryCode,String postcode, String version){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("version", version);
		map.put("countryCode", countryCode);
		map.put("postcode", postcode);
		return (List<YodelDestStation>) this.list(NAME_SPACE+".getByProductCodeAndVersion", map);
	}
}
