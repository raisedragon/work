package com.winit.label.manager.impl.gb.yodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.winit.label.service.impl.BaseServiceImpl;

@Component
public class YodelDestStationService extends BaseServiceImpl
{
	private static String NAME_SPACE = YodelDestStation.class.getName();
	
	public void add(YodelDestStation entity){
		this.save(NAME_SPACE+".insert", entity);
	}
	
	
	/**
	 * 根据ProductCode和版本号获取 DestStation
	 * @param countryCode
	 * @param postcode
	 * @param weight 单位g
	 * @param weight
	 * @param productCode
	 * @param version
	 * @return
	 */
	public  YodelDestStation getByProductCodeAndVersion(String countryCode,String postcode, double weight, String productCode,String version){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("version", version);
		map.put("countryCode", countryCode);
		map.put("postcode", postcode);
		map.put("weight", weight);
		map.put("productCode", productCode);
		return (YodelDestStation) this.single(NAME_SPACE+".getByProductCodeAndVersion", map);
	}
	
	public List<YodelDestStation> getByPostCode(String countryCode,String postcode, String version){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("version", version);
		map.put("countryCode", countryCode);
		map.put("postcode", postcode);
		return (List<YodelDestStation>) this.list(NAME_SPACE+".getByProductCodeAndVersion", map);
	}
}
