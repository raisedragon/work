package com.winit.label.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winit.commons.mybatis.Page;
import com.winit.label.dao.BaseDAO;
import com.winit.label.service.BaseService;


@Service("baseService")
public class BaseServiceImpl implements BaseService{

	@Autowired
	private BaseDAO baseDAOImpl;
	
	public int save(String statement,Object obj) {
		return baseDAOImpl.save(statement,obj);
	}

	public int update(String statement,Object obj) {
		return baseDAOImpl.update(statement,obj);
	}

	public int remove(String statement) {
		return baseDAOImpl.remove(statement);
	}

	public int remove(String statement,Object obj) {
		return baseDAOImpl.remove(statement,obj);
	}

	public Object single(String statement,Object obj) {
		return baseDAOImpl.single(statement,obj);
	}

	public List<?> list(String statement,Object obj) {
		return baseDAOImpl.list(statement,obj);
	}

	public int selectCount(String statement,Object obj) {
		return (Integer) baseDAOImpl.single(statement,obj);
	}
	
	public Map<String,Object> selectMap(String statement,Object o,String key){
		return baseDAOImpl.selectMap(statement, o, key);
	}
	
	public List<?> listByPage(String statement,Object parameter,Page page){
		return baseDAOImpl.listByPage(statement, parameter, page);
	}
}
