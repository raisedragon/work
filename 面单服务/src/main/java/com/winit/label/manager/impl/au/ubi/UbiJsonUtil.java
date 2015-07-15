package com.winit.label.manager.impl.au.ubi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.winit.exception.LabelBusinessException;
import com.winit.label.support.ConfigUtil;

/**
 * UBI json工具类
 * @author kaizhou.chen
 *
 */
public class UbiJsonUtil {
	
	public static String getJsonArray(Object orderId) {
		JSONArray array = new JSONArray();
		array.add(orderId);
		return array.toString();
	}
	
	public static String parseOrderResult(String json, String resultKey) {
		StringBuffer result = new StringBuffer();
		
		try {
			JSONObject obj = JSONObject.parseObject(json);
			String errorType = obj.getString("errorType");
			if("error".equals(errorType)) {
				String message = obj.getString("message");
				throw new LabelBusinessException(message);
			}
		} catch (LabelBusinessException e) {
			throw e;
		} catch (Exception e) {
			JSONArray array = (JSONArray) JSONArray.parse(json);
			JSONObject obj = array.getJSONObject(0);
			
			String statueStr = obj.getString("status");
			if ("Failed".equals(statueStr)) {
				int code = obj.getIntValue("code");
				//TODO
				String error = code + ": " +ConfigUtil.getValue("UbiSmartParcelErrorCode_" + code);
				// 如果对方返回的code是0，则把对方返回的json记录下来，以便和对方确认
				if(code == 0) {
					error = code + ": " + json;
				}
				StringBuffer errors = new StringBuffer();
				errors.append("errorCode: [");
				errors.append(error);
				errors.append("]");
				
				throw new LabelBusinessException(errors.toString());
			} else {
				result.append(obj.getString(resultKey));
			}
		}
		
		return result.toString();
	}
	
	public static String parseTrackingNumberResult(String json) {
		String trackingNo = null;
		
		try {
			JSONObject obj = JSONObject.parseObject(json);
			String errorType = obj.getString("errorType");
			if("error".equals(errorType)) {
				String message = obj.getString("message");
				throw new LabelBusinessException(message);
			}
		} catch (LabelBusinessException e) {
			throw e;
		} catch (Exception e) {
			JSONArray array = (JSONArray) JSONArray.parse(json);
			trackingNo = array.getString(0);
		}
		
		return trackingNo;
	}
	
	public static List<Map<String, Object>> parseTrackingEventResult(String json) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		try {
			JSONObject obj = JSONObject.parseObject(json);
			String errorType = obj.getString("errorType");
			if("error".equals(errorType)) {
				String message = obj.getString("message");
				throw new LabelBusinessException(message);
			}
			
			return null;
		} catch (LabelBusinessException e) {
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
