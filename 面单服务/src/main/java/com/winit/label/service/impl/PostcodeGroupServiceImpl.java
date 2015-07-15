package com.winit.label.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.winit.commons.mybatis.Page;
import com.winit.label.model.PostcodeGroup;
import com.winit.label.service.PostcodeGroupService;

@Service
public class PostcodeGroupServiceImpl extends BaseServiceImpl implements PostcodeGroupService
{
	private String NAME_SPACE = PostcodeGroup.class.getName();
	
	/* (non-Javadoc)
	 * @see com.winit.label.service.impl.PostcodeGroupService#getByLogisticsDistId(java.lang.String, com.winit.commons.mybatis.Page)
	 */
	@Override
	public List<PostcodeGroup> getByLogisticsDistId(Long logisticsDistId,Page page){
		@SuppressWarnings("unchecked")
		List<PostcodeGroup> list =  (List<PostcodeGroup>) this.listByPage(NAME_SPACE+".getByLogisticsDistId", logisticsDistId, page);
		return list;
	}
}
