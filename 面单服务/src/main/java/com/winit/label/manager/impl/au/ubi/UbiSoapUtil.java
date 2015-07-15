package com.winit.label.manager.impl.au.ubi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.google.common.base.Preconditions;
import com.winit.label.manager.impl.au.ubi.model.Credential;
import com.winit.label.manager.impl.au.ubi.model.HmacSHA1;
import com.winit.label.manager.impl.au.ubi.model.HttpDateTimeFormatter;
import com.winit.label.manager.impl.au.ubi.model.Method;
import com.winit.label.manager.impl.au.ubi.model.NormalizedRequest;
import com.winit.label.manager.impl.au.ubi.model.NormalizedRequestHelper;

/**
 * UBI 封装SOAP报文的工具类
 * @author temuser2
 *
 */
public class UbiSoapUtil {

	public static InputStream postToSmartParcel(String path, String data) throws Exception {
		Map<String, String> header = UbiSoapUtil.authorize(path);
		PostMethod post = new PostMethod(UbiConfig.UBI_SMARTPARCEL_API_URL() + path);
		
		RequestEntity entity = new StringRequestEntity(data, "application/json", "utf-8");
		post.setRequestEntity(entity);
		
		for(String key : header.keySet()) {
			post.setRequestHeader(key, header.get(key));
		}
		
		
		byte[] result = null;
		try {
			HttpClient httpClient = new HttpClient();
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30 * 1000);
			httpClient.getHttpConnectionManager().getParams().setSoTimeout(30 * 1000);
			
			httpClient.executeMethod(post);
			InputStream in = post.getResponseBodyAsStream();
			result = readInputStream(in);
		} finally {
			post.releaseConnection();
		}
		
		return readByteArray(result);
	}
	
	public static Map<String, String> authorize(String path) {
		NormalizedRequest request = new NormalizedRequest();
		Credential credential = getCredential(UbiConfig.UBI_SMARTPARCEL_ACCESS_KEY(), UbiConfig.UBI_SMARTPARCEL_SECRET_KEY());
		String date = HttpDateTimeFormatter.formatDateTime(new Date());
		
		Preconditions.checkNotNull(request);
		request.setBaseAddress(UbiConfig.UBI_SMARTPARCEL_API_URL());
		request.withPath(path);
		request.withMethod(Method.POST);
		request.withHeader("X-WallTech-Date", date);
		
		String sign = sign(credential, request);
		
		Map<String, String> header = new HashMap<String, String>();
		header.put("X-WallTech-Date", date);
		header.put("Authorization", sign);
		return header;
	}
	
	private static Credential getCredential(String accessKey, String secretKey) {
		Credential credential = new Credential();
		credential.setAccessKey(accessKey);
		credential.setSecretKey(secretKey);
		return credential;
	}

	private static String sign(Credential credential, NormalizedRequest request) {
		String stringToSign = NormalizedRequestHelper.normalize(request);
		String signature = HmacSHA1.calculate(credential.getSecretKey(), stringToSign);
		return MessageFormat.format("WallTech {0}:{1}", new Object[] { credential.getAccessKey(), signature });
	}
	
	public static byte[] readInputStream(InputStream in) throws IOException {
		byte[] data = new byte[0];
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
	        byte[] buffer = new byte[1024];
	        int len = 0;
	        while( (len = in.read(buffer)) !=-1 ){
	        	out.write(buffer, 0, len);
	        }
	        data = out.toByteArray();
		} finally {
			out.close();
	        in.close();
		}
		
		return data;
    }
	
	public static InputStream readByteArray(byte[] data) {
		return new ByteArrayInputStream(data, 0, data.length);
	}
	
}
