package com.winit.label.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

import com.winit.commons.mybatis.Page;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.SysConfig;
import com.winit.label.query.SysConfigQuery;
import com.winit.label.service.SysConfigService;

@Service("sysConfigService")
public class SysConfigServiceImpl extends BaseServiceImpl implements SysConfigService{
	private static String SYSCONFIG_NAMESPACE = SysConfig.class.getName();
	
	public SysConfig select(String id){
		SysConfig sysConfig = (SysConfig) single(SYSCONFIG_NAMESPACE+".selectById", id);
		return sysConfig;
	}
	
	
	@Override
	public SysConfig selectByName(String key) {
		SysConfig sysConfig = (SysConfig) single(SYSCONFIG_NAMESPACE+".selectByName", key);
		return sysConfig;
	}
	
	@Override
	public String getValue(String key) {
		SysConfig sysConfig = (SysConfig) single(SYSCONFIG_NAMESPACE+".selectByName", key);
		if(sysConfig==null){
			return null;
		}else{
			return sysConfig.getValue();
		}
	}
	
	public List<SysConfig> getAllPaging(Page page){
		return (List<SysConfig>) listByPage(SYSCONFIG_NAMESPACE+".selectAll", null, page);
	}

	
	/**
	 * 通过查询条件与对象，查询系统配置项
	 * @param query 查询条件对象
	 * @param page 分页对象
	 * @return
	 */
	public List<SysConfig> selectByQueryCriteria(SysConfigQuery query,Page page){
		return (List<SysConfig>) listByPage(SYSCONFIG_NAMESPACE+".selectByQueryCriteria", query, page);
	}
	
	@Override
	public void insert(SysConfig sysConfig)
	{
		super.save(SYSCONFIG_NAMESPACE+".insert", sysConfig);
		
	}

	@Override
	public void update(SysConfig sysConfig)
	{
		super.update(SYSCONFIG_NAMESPACE+".update", sysConfig);
		
	}

	@Override
	public void delete(String id)
	{
		super.remove(SYSCONFIG_NAMESPACE+".delete", id);
		
	}
	
}
