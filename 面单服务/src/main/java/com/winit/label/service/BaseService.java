package com.winit.label.service;



import java.util.List;
import java.util.Map;

import com.winit.commons.mybatis.Page;

public interface BaseService {
	int save(String statement,Object obj);

	int update(String statement,Object obj);

	int remove(String statement);
	
	int remove(String statement,Object obj);

	Object single(String statement,Object obj);

	List<?> list(String statement,Object obj);
	
	int selectCount(String statement,Object obj);
	
	Map<String,Object> selectMap(String statement,Object obj,String key);
	
	public List<?> listByPage(String statement,Object parameter,Page page);
}
