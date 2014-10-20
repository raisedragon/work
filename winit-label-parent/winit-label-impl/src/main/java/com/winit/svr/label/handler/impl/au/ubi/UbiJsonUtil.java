package com.winit.svr.label.handler.impl.au.ubi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.winit.svr.LabelException;
import com.winit.svr.label.context.ContextUtils;



/**
 * 调用接口发送和返回的json数据拼装和解析
 * 
 * @author wujiaohua
 * 
 *         2014-4-4
 */
public class UbiJsonUtil {
	/**
	 * 新增订单主体字段
	 */
	public static final String[] ADD_ORDERS_KEYS = { "trackNo", "turnTrackNo", "shipmentId",
			"countryId", "customerCode", "paymentTypeId", "packTypeId", "notes", "buyerId", "sellerId", "company",
			"recipients", "addr1", "addr2", "addr3", "city", "state", "zip", "tel", "email", "senderCompany", "sender",
			"senderAddr", "senderTel" };


	/**
	 * 新增订单物品明细字段
	 */
	public static final String[] ADD_ORDERS_SOLD_ITEM_KEYS = { "ordersId", "name", "modelName", "price", "unitId",
			"platformId", "platformCategory", "platformOrdersId", "transactionId", "soldPrice", "soldQuantity",
			"colorId", "sizeId", "weight", "weightUnitId", "sku", "storage" };
	
	/**
	 * 封装JSON
	 */
	public static String getJsonArray(Map<String, Object> map, String[] keys) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		return getJsonArray(list, keys);
	}

	/**
	 * 封装JSON
	 */
	public static String getJsonArray(List<Map<String, Object>> list, String[] keys) {
		JSONArray array = new JSONArray();
		
		for (Map<String, Object> map : list) {
			JSONObject object = new JSONObject();
			for (String key : keys) {
				Object value = map.get(key);
				if (value != null) {
					object.put(key, value);
				} else {
					object.put(key, "");
				}
			}
			array.add(object);
		}

		return array.toString();
	}

	/**
	 * 封装JSON
	 */
	public static String getJsonArray(Object orderId) {
		JSONArray array = new JSONArray();
		array.add(orderId);
		return array.toString();
	}
	
	/**
	 * 封装JSON
	 */
	public static String getJsonArray(List<Object> orderIds) {
		JSONArray array = new JSONArray(orderIds);
		return array.toString();
	}
	
	/**
	 * 封装JSON
	 */
	public static String getJsonObject(Map<String, Object> map) {
		JSONObject obj = new JSONObject(map);
		return obj.toString();
	}

	/**
	 * 解析JSON
	 */
	public static String parseEParcelJsonArray(String json, String resultKey) {
		StringBuffer buffer = new StringBuffer();
		
		JSONArray array = (JSONArray) JSONArray.parse(json);
		for(int i = 0; i < array.size(); i++) {
			JSONObject obj = array.getJSONObject(i);
			
			String statueStr = obj.getString("statue");
			if ("error".equals(statueStr)) {
				Integer ordersId = obj.getInteger("ordersId");
				JSONArray errorCodeArray = obj.getJSONArray("errorCode");
				if(errorCodeArray == null) {
					errorCodeArray = obj.getJSONArray("ERROR_CODE");
				}
				
				StringBuffer errors = new StringBuffer();
				if(ordersId != null) {
					errors.append("ordersId: ").append(ordersId).append(", ");
				}
				
				errors.append("errorCode: [");
				for(int j = 0; j < errorCodeArray.size(); j++) {
					Integer code = errorCodeArray.getInteger(j);
					//TODO
					String error = code + ": " + ContextUtils.getValue("UbiErrorCode_" + code);
					errors.append(error);
					if(j < errorCodeArray.size() - 1) {
						errors.append(", ");
					}
				}
				errors.append("]");
				
				throw new LabelException(errors.toString());
			} else {
				buffer.append(obj.getString(resultKey));
			}
		}
		
		return buffer.toString();
	}

	/**
	 * 查询订单状态
	 * 
	 * @param json
	 * @return
	 */
	public static String parseEParcelGetOrdersResult(String json) {
		JSONObject obj = JSONObject.parseObject(json);
		JSONArray rows = obj.getJSONArray("rows");
		String status = "";
		if(rows != null && rows.size() > 0) {
			status = rows.getJSONObject(0).getString("status");
		}
		return status;
	}

	/**
	 * 获取派送轨迹
	 * 
	 * @param json
	 * @return
	 */
	public static List<Map<String, Object>> parseEParcelTrackingOrdersResult(String json) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		JSONArray array = (JSONArray) JSONArray.parse(json);
		for (int i = 0; i < array.size(); i++) {
			JSONObject obj = array.getJSONObject(i);
			String statueStr = obj.getString("statue");
			if ("error".equals(statueStr)) {
				JSONArray errorCode = obj.getJSONArray("errorCode");
				throw new LabelException(errorCode.toString());
			} else {
				JSONArray desc = obj.getJSONArray("description");
				for (int j = 0; j < desc.size(); j++) {
					JSONObject descObj = desc.getJSONObject(j);
					
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("time", descObj.get("time"));
					map.put("location", descObj.get("location"));
					map.put("trackingMsg", descObj.get("event"));
					map.put("name", descObj.get("name"));
					
					result.add(map);
				}
			}
		}
		
		return result;
	}
	
	public static String parseSmartParcelOrderResult(String json, String resultKey) {
		StringBuffer result = new StringBuffer();
		
		try {
			JSONObject obj = JSONObject.parseObject(json);
			String errorType = obj.getString("errorType");
			if("error".equals(errorType)) {
				String message = obj.getString("message");
				throw new LabelException(message);
			}
		} catch (LabelException e) {
			throw e;
		} catch (Exception e) {
			JSONArray array = (JSONArray) JSONArray.parse(json);
			JSONObject obj = array.getJSONObject(0);
			
			String statueStr = obj.getString("status");
			if ("Failed".equals(statueStr)) {
				int code = obj.getIntValue("code");
				//TODO
				String error = code + ": " +ContextUtils.getValue("UbiSmartParcelErrorCode_" + code);
				// 如果对方返回的code是0，则把对方返回的json记录下来，以便和对方确认
				if(code == 0) {
					error = code + ": " + json;
				}
				StringBuffer errors = new StringBuffer();
				errors.append("errorCode: [");
				errors.append(error);
				errors.append("]");
				
				throw new LabelException(errors.toString());
			} else {
				result.append(obj.getString(resultKey));
			}
		}
		
		return result.toString();
	}
	
	public static String parseSmartParcelTrackingNumberResult(String json) {
		String trackingNo = null;
		
		try {
			JSONObject obj = JSONObject.parseObject(json);
			String errorType = obj.getString("errorType");
			if("error".equals(errorType)) {
				String message = obj.getString("message");
				throw new LabelException(message);
			}
		} catch (LabelException e) {
			throw e;
		} catch (Exception e) {
			JSONArray array = (JSONArray) JSONArray.parse(json);
			trackingNo = array.getString(0);
		}
		
		return trackingNo;
	}
	
	public static List<Map<String, Object>> parseSmartParcelTrackingEventResult(String json) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		try {
			JSONObject obj = JSONObject.parseObject(json);
			String errorType = obj.getString("errorType");
			if("error".equals(errorType)) {
				String message = obj.getString("message");
				throw new LabelException(message);
			}
			
			return null;
		} catch (LabelException e) {
			throw e;
		} catch (Exception e) {
			JSONArray array = (JSONArray) JSONArray.parse(json);
			JSONArray events = array.getJSONArray(0);
			
			for (int j = 0; j < events.size(); j++) {
				JSONObject descObj = events.getJSONObject(j);
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("time", descObj.getLong("eventTime"));
				map.put("status", descObj.getString("eventCode"));
				map.put("trackingMsg", descObj.getString("activity"));
				map.put("location", descObj.getString("location"));
				
				result.add(map);
			}
		}
		
		return result;
	}

}
