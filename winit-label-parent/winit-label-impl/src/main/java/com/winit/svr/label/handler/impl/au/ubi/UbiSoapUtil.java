package com.winit.svr.label.handler.impl.au.ubi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.google.common.base.Preconditions;
import com.winit.svr.label.context.ContextUtils;
import com.winit.svr.label.handler.impl.au.ubi.model.Credential;
import com.winit.svr.label.handler.impl.au.ubi.model.HmacSHA1;
import com.winit.svr.label.handler.impl.au.ubi.model.HttpDateTimeFormatter;
import com.winit.svr.label.handler.impl.au.ubi.model.Method;
import com.winit.svr.label.handler.impl.au.ubi.model.NormalizedRequest;
import com.winit.svr.label.handler.impl.au.ubi.model.NormalizedRequestHelper;

/**
 * UBI 封装SOAP报文的工具类
 * @author temuser2
 *
 */
public class UbiSoapUtil {
	/** eParcel 配置数据 */
	public static String	UBI_API_USERNAME() {
		return ContextUtils.getValue("WT_UBI_API_USERNAME");						// winit , sztest
	}
	public static String	UBI_API_PASSWORD() {
		return ContextUtils.getValue("WT_UBI_API_PASSWORD");						// b1ea6c18469e89628d7b2e9550f2603bl3rn8nnd , e05e3f9a84edcd9478ad425f088343e56axf7iou
	}
	public static String	UBI_API_URL() {
		return ContextUtils.getValue("WT_UBI_API_URL");							// http://115.28.55.157/szyys/webservice/ordersOpenServiceHeader
	}
	public static String	UBI_LABLE_IMAGE_HOST() {
		return ContextUtils.getValue("WT_UBI_LABLE_IMAGE_HOST");					// http://115.28.55.157/
	}
	public static String	UBI_LABLE_ACTION_HOST() {
		return ContextUtils.getValue("WT_UBI_LABLE_ACTION_HOST");					// http://115.28.55.157/szyys/
	}

	/** SmartParcel 配置数据 */
	public static String	UBI_SMARTPARCEL_ACCESS_KEY() {
		return ContextUtils.getValue("WT_UBI_SMARTPARCEL_ACCESS_KEY");				// testkZ7yrMx2EeOpvgAVXUFE0A
	}
	public static String	UBI_SMARTPARCEL_SECRET_KEY() {
		return ContextUtils.getValue("WT_UBI_SMARTPARCEL_SECRET_KEY");				// QnQ3stWbEeOpvgAVXUFE0A
	}
	public static String	UBI_SMARTPARCEL_API_URL() {
		return ContextUtils.getValue("WT_UBI_SMARTPARCEL_API_URL");				// http://smartparcel.chinacloudapp.cn/services
	}
	public static String	UBI_SMARTPARCEL_ORDER_PATH() {
		return ContextUtils.getValue("WT_UBI_SMARTPARCEL_ORDER_PATH");				// /integration/shipper/orders
	}
	public static String	UBI_SMARTPARCEL_LABELS_PATH() {
		return ContextUtils.getValue("WT_UBI_SMARTPARCEL_LABELS_PATH");			// /integration/shipper/labels
	}
	public static String	UBI_SMARTPARCEL_TRACKING_NUMBER_PATH() {
		return ContextUtils.getValue("WT_UBI_SMARTPARCEL_TRACKING_NUMBER_PATH");	// /integration/shipper/trackingNumbers
	}
	public static String	UBI_SMARTPARCEL_MANIFEST_PATH() {
		return ContextUtils.getValue("WT_UBI_SMARTPARCEL_MANIFEST_PATH");			// /integration/shipper/manifests
	}
	public static String	UBI_SMARTPARCEL_TRACKING_EVENT_PATH() {
		return ContextUtils.getValue("WT_UBI_SMARTPARCEL_TRACKING_EVENT_PATH");	// /integration/shipper/trackingEvents
	}

	/** eParcel SOAP 方法名 */
	public static String	ADD_ORDERS								= "addOrders";
	public static String	ADD_ORDERS_SOLD_ITEMS					= "addOrdersSoldItems";
	public static String	MADE_TRACKING_NUMBER					= "madeTrackingNumber";
	public static String	PRINT_LABELS							= "printLabels";
	public static String	TRACKING_ORDERS							= "trackingOrders";
	public static String	GET_ORDERS								= "getOrders";
	public static String	FORE_ORDERS								= "foreOrders";
	public static String	UPLOAD_MANIFEST							= "uploadManifest";

	public static final String	APPLICATION_JSON						= "application/json";
	
	/**
	 * 发送请求
	 * @param method 方法名
	 * @param param 参数
	 * @return
	 * @throws Exception
	 */
	public static String postToEParcel(String method, String param) throws Exception {
		return postToEParcel(method, param, null);
	}
	
	public static String postToEParcel(String method, String arg0, String arg1) throws Exception {
		String result = "";
		
		String message = getSoapMessage(method, arg0, arg1);
		InputStream is = post(UBI_API_URL(), message.getBytes("utf-8"));
		String response = new String(UbiSoapUtil.readInputStream(is), "utf-8");
		if(response != null && response.length() > 0) {
			result = parseResponse(response);
		}
		
		return result;
	}
	
	/**
	 * 拼装SOAP报文
	 * @param method 方法名
	 * @param param 参数
	 * @return 报文
	 */
	private static String getSoapMessage(String method, String arg0, String arg1) {
		StringBuilder buffer = new StringBuilder();
		
		buffer.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:yys=\"yysOrdersWebService\">");
			buffer.append("<soapenv:Header>");
				buffer.append("<userName>").append(UBI_API_USERNAME()).append("</userName>");
				buffer.append("<token>").append(UBI_API_PASSWORD()).append("</token>");
			buffer.append("</soapenv:Header>");
			
			buffer.append("<soapenv:Body>");
				buffer.append("<yys:" + method + ">");
					buffer.append("<arg0>").append(arg0).append("</arg0>");
					if(arg1 != null) {
						buffer.append("<arg1>").append(arg1).append("</arg1>");
					}
				buffer.append("</yys:" + method + ">");
			buffer.append("</soapenv:Body>");
		buffer.append("</soapenv:Envelope>");
		
		return buffer.toString();
	}
	
	/**
	 * 发送请求
	 * @param url
	 * @param data
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	private static InputStream post(String url, byte[] data) throws HttpException, IOException  {
		PostMethod post = new PostMethod(url);
		InputStream paramStream = new ByteArrayInputStream(data, 0, data.length);
		RequestEntity entity = new InputStreamRequestEntity(paramStream, data.length, "application/soap+xml; charset=utf-8");
		post.setRequestEntity(entity);
		
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
	
	/**
	 * 解析回执报文
	 * @param xml
	 * @return
	 * @throws XMLStreamException
	 */
	private static String parseResponse(String xml) throws XMLStreamException {
		XMLInputFactory xif = XMLInputFactory.newFactory();
        XMLStreamReader xsr = xif.createXMLStreamReader(new StringReader(xml));
        xsr.nextTag(); // Envelope
        xsr.nextTag(); // Body
        xsr.nextTag(); // Response
        xsr.nextTag(); // result
        return xsr.getElementText();
	}
	
	
	/*******************************************************
	 * 
	 * 以下方法用于发送请求给SmartParcel
	 * 
	 *******************************************************/
	
	public static InputStream postToSmartParcel(String path, String data) throws Exception {
		Map<String, String> header = UbiSoapUtil.authorize(path);
		PostMethod post = new PostMethod(UbiSoapUtil.UBI_SMARTPARCEL_API_URL() + path);
		
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
		Credential credential = getCredential(UBI_SMARTPARCEL_ACCESS_KEY(), UBI_SMARTPARCEL_SECRET_KEY());
		String date = HttpDateTimeFormatter.formatDateTime(new Date());
		
		Preconditions.checkNotNull(request);
		request.setBaseAddress(UBI_SMARTPARCEL_API_URL());
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
}
