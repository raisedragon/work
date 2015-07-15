package com.winit.label.manager.impl.gb.yodel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.winit.label.service.impl.BaseServiceImpl;


@Component
public class YodelReamusIdService extends BaseServiceImpl
{
	private static String NAME_SPACE = YodelReamusId.class.getName();
	
	
	public void add(YodelReamusId entity){
		this.save(NAME_SPACE+".insert", entity);
	}

	/**
	 * 根据ReamusID和版本号获取 YodelReamusId
	 * @param reamusID 
	 * @param version
	 * @return
	 */
	public YodelReamusId getByReamusIdAndVersion(String reamusId,String version){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reamusId", reamusId);
		map.put("version", version);
		return (YodelReamusId) this.single(NAME_SPACE+".getByReamusIdAndVersion", map);
//		String whereClause=" "+MYodelReamusID.COLUMNNAME_ReamusID+"=? and "+MYodelReamusID.COLUMNNAME_Version+"=?";
//		YodelReamusId entity = new Query(Env.getCtx(), MYodelReamusID.Table_Name, whereClause, null)
//				.setParameters(reamusId,version)
//				.first();
//		return entity;
	}
}
