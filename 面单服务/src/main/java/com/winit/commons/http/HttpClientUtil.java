package com.winit.commons.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;

/**
 * HttpClient工具类，用于发送http请求
 * @author temuser2
 *
 */
@SuppressWarnings("deprecation")
public class HttpClientUtil
{

	/**
	 * 可以访问https请求的HttpClient
	 * @return
	 */
	private static HttpClient getHttpClient() {
		Protocol https = new Protocol("https",new SocketFactoryWrapper(new UtilSSLSocketFactory().trustAllCertificates().disableDiffieHellman()), 443);
		Protocol.registerProtocol("https", https);
		
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60 * 1000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(60 * 1000);
		
		return httpClient;
	}
	
	/**
	 * 发送post请求
	 * @param url 请求地址
	 * @param data 报文数据
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String post(String url, byte[] data) throws HttpException, IOException  {
		return post(url, data, "application/soap+xml", null);
	}
	
	public static String post(String url, byte[] data, String contentType, Map<String, String> header) throws HttpException, IOException  {
		PostMethod post = new PostMethod(url);
		InputStream paramStream = new ByteArrayInputStream(data, 0, data.length);
		RequestEntity entity = new InputStreamRequestEntity(paramStream, data.length, contentType + "; charset=utf-8");
		post.setRequestEntity(entity);
		
		if(header != null) {
			for(String key : header.keySet()) {
				post.addRequestHeader(key, header.get(key));
			}
		}
		
		InputStream result = null;
		try {
			getHttpClient().executeMethod(post);
			InputStream in = post.getResponseBodyAsStream();
			result = copyInputStream(in);
		} finally {
			post.releaseConnection();
		}
		
		return readInputStream(result);
	}
	
	/**
	 * 复制InputStream
	 * 避免post断开连接后，InputStream不能读取数据的问题
	 * 
	 * @param in InputStream
	 * @return
	 * @throws IOException
	 */
	private static InputStream copyInputStream(InputStream in) throws IOException {
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
		
		return new ByteArrayInputStream(data, 0, data.length);
    }
	
	/**
	 * 读取InputStream
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static String readInputStream(InputStream in) throws IOException {
		String result = "";
		
		InputStreamReader reader = new InputStreamReader(in);
		try {
			StringBuffer content = new StringBuffer();
			
			int len = -1;
			char[] cbuf = new char[1024];
			while((len = reader.read(cbuf)) != -1) {
				content.append(cbuf, 0, len);
			}
			
			result = content.toString();
		} finally {
			reader.close();
			in.close();
		}
		
		return result;
	}
	
}
