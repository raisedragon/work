package com.winit.label.manager.impl.gb.yodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.winit.label.service.impl.BaseServiceImpl;

@Component
public class YodelDestPrdServicesService extends BaseServiceImpl
{

	private static String NAME_SPACE = YodelDestPrdServices.class.getName();

	
	
	public void add(YodelDestPrdServices entity){
		this.save(NAME_SPACE+".insert", entity);
	}
	
	public List<YodelDestPrdServices> getByServiceCtrReamusID(String serviceCtrReamusID,String productCode, String version)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serviceCtrReamusID", serviceCtrReamusID);
		map.put("productCode", productCode);
		map.put("version", version);
		
		return (List<YodelDestPrdServices>) this.list(NAME_SPACE+".getByServiceCtrReamusID", map);
	}

}
