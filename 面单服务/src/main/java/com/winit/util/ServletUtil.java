package com.winit.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ServletUtil {

	public static Map<String, String> paramToMap(HttpServletRequest request) {
		HashMap<String, String> map = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		Map<Object, Object> m = request.getParameterMap();
		// Object v = null;
		for (Map.Entry<Object, Object> entry : m.entrySet()) {
			// v =
			// if()
			map.put(entry.getKey().toString(), ((String[]) entry.getValue())[0]);
		}
		return map;
	}
}
