package com.winit.label.manager.impl.gb.yodel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.winit.label.service.impl.BaseServiceImpl;


@Component
public class YodelServiceService extends BaseServiceImpl
{
	private static String NAME_SPACE = YodelService.class.getName(); 
	
	public void add(YodelService yodelService){
		this.save(NAME_SPACE+".insert", yodelService);
	}
	
	/**
	 * 根据ServiceID和版本号获取 Service
	 * @param serviceId
	 * @param version
	 * @return
	 */
	public YodelService getByServiceCodeAndVersion(String serviceCode,String version){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serviceCode", serviceCode);
		map.put("version", version);
		return (YodelService)this.single(NAME_SPACE+".getByServiceCodeAndVersion", map);
//		String whereClause=" "+MYodelService.COLUMNNAME_ServiceCode+"=? and "+MYodelService.COLUMNNAME_Version+"=?";
//		MYodelService yodelService = new Query(Env.getCtx(), MYodelService.Table_Name, whereClause, null)
//				.setParameters(serviceCode,version)
//				.first();
//		return yodelService;
	}

	
	
	/**
	 * 根据ServiceID和版本号获取 Service
	 * @param serviceId
	 * @param version
	 * @return
	 */
	public YodelService getByServiceIdAndVersion( String serviceId,String version){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serviceId", serviceId);
		map.put("version", version);
		return (YodelService)this.single(NAME_SPACE+".getByServiceIdAndVersion", map);
//		String whereClause=" "+MYodelService.COLUMNNAME_ServiceID+"=? and "+MYodelService.COLUMNNAME_Version+"=?";
//		MYodelService yodelService = new Query(Env.getCtx(), MYodelService.Table_Name, whereClause, null)
//				.setParameters(serviceId,version)
//				.first();
//		return yodelService;
	}
	
	

	public YodelService getByProductCodeAndFeatureCode(String productCode, String featureCode, String version)
	{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productCode", productCode);
		map.put("featureCode", featureCode);
		map.put("version", version);
		return (YodelService)this.single(NAME_SPACE+".getByProductCodeAndFeatureCode", map);
//		String whereClause=" "+MYodelService.COLUMNNAME_ProductCode+"=? " +
//				" and "+MYodelService.COLUMNNAME_FeatureCode+"=? " +
//				" and "+MYodelService.COLUMNNAME_Version+"=?";
//		MYodelService yodelService = new Query(Env.getCtx(), MYodelService.Table_Name, whereClause, null)
//				.setParameters(productCode,featureCode,version)
//				.first();
//		return yodelService;
	}
}
