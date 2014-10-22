package com.winit.util;

import org.apache.commons.codec.binary.Base64;

public class Base64Encoder {

	public static String encode(String base){
		return Base64.encodeBase64String(base.getBytes());
	}
}
