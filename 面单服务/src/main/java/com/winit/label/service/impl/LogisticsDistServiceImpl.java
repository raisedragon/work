package com.winit.label.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.winit.commons.mybatis.Page;
import com.winit.label.model.LogisticsDist;
import com.winit.label.service.LogisticsDistService;


@Service
public class LogisticsDistServiceImpl extends BaseServiceImpl implements LogisticsDistService
{

	
	private String NAME_SPACE = LogisticsDist.class.getName();
	
	/* (non-Javadoc)
	 * @see com.winit.label.service.impl.LogisticsDistService#getByDeliveryWayId(java.lang.String, com.winit.commons.mybatis.Page)
	 */
	@Override
	public List<LogisticsDist> getByDeliveryWayId(Long deliveryWayId,Page page){
		@SuppressWarnings("unchecked")
		List<LogisticsDist> list =  (List<LogisticsDist>) this.listByPage(NAME_SPACE+".getByDeliveryWayId", deliveryWayId, page);
		return list;
	}
}
