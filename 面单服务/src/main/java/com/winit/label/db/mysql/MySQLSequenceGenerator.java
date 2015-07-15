package com.winit.label.db.mysql;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.winit.label.db.SequenceGenerator;


/**
 * 序列操作接口的MySQL实现
 * @author longsheng.wang
 *
 */
public class MySQLSequenceGenerator implements SequenceGenerator
{
	protected String NAME_SPACE = this.getClass().getName();
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public long nextVal(String seqKey)
	{
		Long value = sqlSession.selectOne(NAME_SPACE+".nextVal", seqKey);
		return value==null?0:value.longValue();
	}

	@Override
	public long currentVal(String seqKey)
	{
		Long value =  sqlSession.selectOne(NAME_SPACE+".currVal", seqKey);
		return value==null?0:value.longValue();
	}

	@Override
	public long setVal(String seqKey, long value)
	{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("seqKey", seqKey);
		params.put("value", value);
		Long value1 =  sqlSession.selectOne(NAME_SPACE+".setVal", params);
		return value1==null?0:value1.longValue();
	}

}
