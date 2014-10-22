package com.winit.label.dao;


import java.util.List;
import java.util.Map;

import com.winit.commons.mybatis.Page;

public interface BaseDAO {
	
	static final int INSERT_MAX_COUNT = 100; 

	int save(String statement,Object obj);

	int update(String statement,Object obj);

	int remove(String statement);
	
	int remove(String statement,Object o);

	Object single(String statement,Object o);

	List<?> list(String statement,Object o);
	
	Map<String,Object> selectMap(String statement,Object o,String key);
	
	List<?> listByPage(String statement,Object parameter,Page page);
}
