package com.winit.label.service;

import java.util.List;

import com.winit.commons.mybatis.Page;
import com.winit.label.model.LogisticsDist;

public interface LogisticsDistService
{

	/**
	 * 获取指定派送方式的分区
	 * @param deliveryWayId 派送方式ID
	 * @param page 分页信息
	 * @return
	 */
	public abstract List<LogisticsDist> getByDeliveryWayId(Long deliveryWayId, Page page);

}