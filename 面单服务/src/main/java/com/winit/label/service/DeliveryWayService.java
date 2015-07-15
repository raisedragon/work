package com.winit.label.service;

import java.util.List;
import java.util.Map;

import com.winit.label.model.DeliveryWay;


public interface DeliveryWayService {

	/**
	 * 
	 * <p>保存单个对象/p>
	 * @param lp
	 * @author {庄坚发}
	 */
	void saveDeliveryWay(DeliveryWay lp);
	
	/**
	 * 
	 * <p>批量保存</p>
	 * @param list
	 * @author {庄坚发}
	 */
	void saveDeliveryWayList(List<DeliveryWay> list);
	
	/**
	 * 
	 * <p>根据传参MAP更新对象</p>
	 * @param map
	 * @author {庄坚发}
	 */
	void updateDeliveryWayByMap(Map<String,Object> map);
	
	/**
	 * 
	 * <p>根据MODEL对象更新</p>
	 * @param parm
	 * @author {庄坚发}
	 */
	void updateDeliveryWay(DeliveryWay parm);
	
	/**
	 * 
	 * <p>根据传参MAP删除对象</p>
	 * @param map
	 * @author {庄坚发}
	 */
	void removeDeliveryWayByMap(Map<String,Object> map);
	
	/**
	 * 
	 * <p>根据传参MAP查询对象</p>
	 * @param map
	 * @return
	 * @author {庄坚发}
	 */
	List<DeliveryWay> queryDeliveryWayByMap(Map<String,Object> map);
	
	/**
	 * 
	 * <p>查询开关是否打开</p>
	 * @param map
	 * @return
	 * @author {庄坚发}
	 */
	public int queryCountByMap(Map<String, Object> map);
	
	
	public DeliveryWay findByCode(String code);
}
