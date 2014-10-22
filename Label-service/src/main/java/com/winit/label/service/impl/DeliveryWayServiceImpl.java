package com.winit.label.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.winit.label.model.DeliveryWay;
import com.winit.label.service.DeliveryWayService;



@Service("deliveryWayService")
public class DeliveryWayServiceImpl extends BaseServiceImpl implements DeliveryWayService {

	private String DELIVERYWAY_NAMESPACE = DeliveryWay.class.getName();
	
	private String UPDATE_DELIVERYWAY_BY_MAP = "updateDeliveryWayByMap";
	
	private String UPDATE_DELIVERYWAY = "updateDeliveryWay";
	
	private String REMOVE_DELIVERYWAY_BY_MAP = "removeDeliveryWayByMap";

	private String ADD_DELIVERYWAY = "addDeliveryWay";
	
	private String ADD_DELIVERYWAY_LIST = "addDeliveryWayList";
	
	private String QUERY_DELIVERYWAY_BY_MAP = "queryDeliveryWayByMap";
	
	private String QUERY_COUNT_BY_MAP = "queryCountByMap";

	
	@Override
	public void saveDeliveryWay(DeliveryWay lp) {
		save(DELIVERYWAY_NAMESPACE+"."+ADD_DELIVERYWAY, lp);
	}

	@Override
	public void saveDeliveryWayList(List<DeliveryWay> list) {
		save(DELIVERYWAY_NAMESPACE+"."+ADD_DELIVERYWAY_LIST, list);
	}

	@Override
	public void updateDeliveryWayByMap(Map<String, Object> map) {
		update(DELIVERYWAY_NAMESPACE+"."+UPDATE_DELIVERYWAY_BY_MAP, map);
	}

	@Override
	public void updateDeliveryWay(DeliveryWay parm) {
		update(DELIVERYWAY_NAMESPACE+"."+UPDATE_DELIVERYWAY, parm);
	}

	@Override
	public void removeDeliveryWayByMap(Map<String, Object> map) {
		update(DELIVERYWAY_NAMESPACE+"."+REMOVE_DELIVERYWAY_BY_MAP, map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeliveryWay> queryDeliveryWayByMap(Map<String, Object> map) {
		return (List<DeliveryWay>) list(DELIVERYWAY_NAMESPACE+"."+QUERY_DELIVERYWAY_BY_MAP, map);
	}
	
	@Override
	public int queryCountByMap(Map<String, Object> map){
		return selectCount(DELIVERYWAY_NAMESPACE+"."+QUERY_COUNT_BY_MAP, map);
	}

	@Override
	public DeliveryWay findByCode(String code)
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("code", code);
		List<DeliveryWay> list = 	(List<DeliveryWay>) list(DELIVERYWAY_NAMESPACE+"."+QUERY_DELIVERYWAY_BY_MAP, map);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
