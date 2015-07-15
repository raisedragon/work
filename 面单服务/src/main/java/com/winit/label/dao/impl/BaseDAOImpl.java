package com.winit.label.dao.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winit.commons.mybatis.Page;
import com.winit.commons.mybatis.PaginationInterceptor;
import com.winit.label.dao.BaseDAO;


@Component("baseDAOImpl")
public class BaseDAOImpl implements BaseDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@SuppressWarnings("unchecked")
	public int save(String statement,Object obj) {
		int count = 0;
		if(obj instanceof List){
			List<Object> saveList = (List<Object>) obj;
			if(saveList != null && saveList.size() > 0){
				if(saveList.size() <= INSERT_MAX_COUNT){
					count+=sqlSession.insert(statement,saveList);
				}else {
					List<Object> tempList = new ArrayList<Object>();
					// 数据量较大时分次保存
					for(int i=0;i<saveList.size();i++){
						tempList.add(saveList.get(i));
						// 每100条记录保存一次
						if((i > 0 && i%INSERT_MAX_COUNT == 0) 
								// 最后一次可能不是100的整数倍，也须保存
								|| (i == (saveList.size() - 1))){
							count+=sqlSession.insert(statement,tempList);
							tempList.clear();
						}
					}
				}
			}
		}else {
			count+=sqlSession.insert(statement,obj);
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public int update(String statement,Object obj) {
		int count = 0;
		if(obj instanceof List){
			List<Object> saveList = (List<Object>) obj;
			if(saveList != null && saveList.size() > 0){
				if(saveList.size() <= INSERT_MAX_COUNT){
					count = sqlSession.update(statement, saveList);
				}else {
					List<Object> tempList = new ArrayList<Object>();
					// 数据量较大时分次保存
					for(int i=0;i<saveList.size();i++){
						tempList.add(saveList.get(i));
						// 每100条记录保存一次
						if((i > 0 && i%INSERT_MAX_COUNT == 0) 
								// 最后一次可能不是100的整数倍，也须保存
								|| (i == (saveList.size() - 1))){
							count += sqlSession.update(statement, tempList);
							tempList.clear();
						}
					}
				}
			}
		}else {
			count = sqlSession.update(statement, obj);
		}
		return count;
	}

	public int remove(String statement) {
		return sqlSession.delete(statement);
	}

	public int remove(String statement,Object o) {
		return sqlSession.delete(statement, o);
	}

	public Object single(String statement,Object o) {
		return sqlSession.selectOne(statement, o);
	}

	public List<?> list(String statement,Object o) {
		return sqlSession.selectList(statement, o);
	}

	public Map<String, Object> selectMap(String statement,Object o, String key) {
		return sqlSession.selectMap(statement, o, key);
	}
	
	
	public List<?> listByPage(String statement,Object parameter,Page page){
		return selectListPagination(statement, parameter, page);
	}
	
	
	private <T> List<T> selectListPagination(String statement,Object parameter,Page page){
		PaginationInterceptor.startPage(page);
		List<T> list =  sqlSession.selectList(statement, parameter);
		return list;
	}
}
