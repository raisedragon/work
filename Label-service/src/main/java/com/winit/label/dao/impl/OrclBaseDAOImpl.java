package com.winit.label.dao.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winit.commons.mybatis.Page;
import com.winit.label.dao.BaseDAO;


@Component("orclBaseDAOImpl")
public class OrclBaseDAOImpl implements BaseDAO {

	@Autowired
	private SqlSession orclSqlSession;
	
	@SuppressWarnings("unchecked")
	public int save(String statement,Object obj) {
		int count = 0;
		if(obj instanceof List){
			List<Object> saveList = (List<Object>) obj;
			if(saveList != null && saveList.size() > 0){
				if(saveList.size() <= INSERT_MAX_COUNT){
					count+=orclSqlSession.insert(statement,saveList);
				}else {
					List<Object> tempList = new ArrayList<Object>();
					// 数据量较大时分次保存
					for(int i=0;i<saveList.size();i++){
						tempList.add(saveList.get(i));
						// 每100条记录保存一次
						if((i > 0 && i%INSERT_MAX_COUNT == 0) 
								// 最后一次可能不是100的整数倍，也须保存
								|| (i == (saveList.size() - 1))){
							count+=orclSqlSession.insert(statement,tempList);
							tempList.clear();
						}
					}
				}
			}
		}else {
			count+=orclSqlSession.insert(statement,obj);
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
					count = orclSqlSession.update(statement, saveList);
				}else {
					List<Object> tempList = new ArrayList<Object>();
					// 数据量较大时分次保存
					for(int i=0;i<saveList.size();i++){
						tempList.add(saveList.get(i));
						// 每100条记录保存一次
						if((i > 0 && i%INSERT_MAX_COUNT == 0) 
								// 最后一次可能不是100的整数倍，也须保存
								|| (i == (saveList.size() - 1))){
							count += orclSqlSession.update(statement, tempList);
							tempList.clear();
						}
					}
				}
			}
		}else {
			count = orclSqlSession.update(statement, obj);
		}
		return count;
	}

	public int remove(String statement) {
		return orclSqlSession.delete(statement);
	}

	public int remove(String statement,Object o) {
		return orclSqlSession.delete(statement, o);
	}

	public Object single(String statement,Object o) {
		return orclSqlSession.selectOne(statement, o);
	}

	public List<?> list(String statement,Object o) {
		return orclSqlSession.selectList(statement, o);
	}

	public Map<String, Object> selectMap(String statement,Object o, String key) {
		return orclSqlSession.selectMap(statement, o, key);
	}

	@Override
	public List<?> listByPage(String statement, Object parameter, Page page)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
