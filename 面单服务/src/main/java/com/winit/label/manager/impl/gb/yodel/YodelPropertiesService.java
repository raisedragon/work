package com.winit.label.manager.impl.gb.yodel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.winit.commons.cache.CCache;
import com.winit.label.model.SysConfig;
import com.winit.label.service.impl.BaseServiceImpl;

@Component
public class YodelPropertiesService extends BaseServiceImpl
{
	private static String NAME_SPACE = YodelProperties.class.getName();
	
	public void add(YodelProperties yodelProperties){
		this.save(NAME_SPACE+".insert", yodelProperties);
	}
	
	public  String getValue(String key,String version,String defaultValue){
		String str = getValue(key,version);
		if (str != null) {
			str = str.trim();
			return str;
		}
		else {
			return defaultValue;
		}
	}
	
	/**
	 * 获取当前GAZETTEER版本
	 * @return
	 */
	public String getCurrentGazetteerVersion(){
		return (String) this.single(NAME_SPACE+".getCurrentGazetteerVersion", null);
	}

	
	/**
	 * 根据Key获取配置值 
	 * @param key
	 * @param version
	 * @return
	 */
	public String getValue(String key,String version){
		return (String) single(NAME_SPACE+".getValue",key);
	}
}
