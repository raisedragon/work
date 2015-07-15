package com.winit.label.service;

import java.util.List;

import com.winit.commons.mybatis.Page;
import com.winit.label.model.PostcodeGroup;

public interface PostcodeGroupService
{

	
	/**
	 * 获取指定物流分区的邮编分组
	 * @param logisticsDistId 物流分区ID
	 * @param page 分页信息
	 * @return
	 */
	public abstract List<PostcodeGroup> getByLogisticsDistId(Long logisticsDistId, Page page);

}