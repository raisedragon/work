package com.winit.label.service;

import java.util.List;

import com.winit.commons.mybatis.Page;
import com.winit.label.model.SysConfig;
import com.winit.label.query.SysConfigQuery;



/**
 * 
 * <P>系统参数</P>
 * @author {庄坚发}
 */
public interface SysConfigService{
	public void insert(SysConfig sysConfig);
	
	public void update(SysConfig sysConfig);
	
	public void delete(String id);
	
	public SysConfig select(String id);
	
	public SysConfig selectByName(String name);
	
	public String getValue(String key);
	
	public List<SysConfig> getAllPaging(Page page);
	
	public List<SysConfig> selectByQueryCriteria(SysConfigQuery query,Page page);
}
