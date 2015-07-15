package com.winit.label.service.impl;

import java.util.List;

import com.winit.commons.mybatis.Page;
import com.winit.label.model.Job;
import com.winit.label.query.SysConfigQuery;

public class JobServiceImpl extends BaseServiceImpl
{
	private static String NAME_SPACE = Job.class.getName();
	public List<Job> selectByQueryCriteria(SysConfigQuery query,Page page){
		return (List<Job>) this.listByPage(NAME_SPACE+".selectAll", query, page);
	}
}
