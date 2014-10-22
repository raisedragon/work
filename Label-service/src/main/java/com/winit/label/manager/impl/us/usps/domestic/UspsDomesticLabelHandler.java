package com.winit.label.manager.impl.us.usps.domestic;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.winit.exception.LabelException;
import com.winit.label.manager.LabelHandler;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;



/**
 * USPS面单获取
 * @author kang.wang
 *
 */
@Component
public class UspsDomesticLabelHandler implements LabelHandler {
	
	protected static final Logger logger = Logger.getLogger(UspsDomesticLabelHandler.class);  

	
	@Override
	public Result handle(RequestMessage requestMsg,DeliveryWay dw) throws Exception{
		String url =  UspsDomesticConfig.SERVICE_URL();
		//效验
		if(StringUtils.isEmpty(url)){
			logger.error("url is null");
			throw new LabelException("url is null");
		}
		
		//构建requestXml
		String requestXml = buildRequestXml(requestMsg,dw);
		//获取responseXml
		
		String responseXml = getResponseXml(requestXml,url);
		//请求出错
		if(StringUtils.isEmpty(responseXml)){
			throw new LabelException("error");
		}
		Map<String,Object> resultMap = resolveXml(responseXml);
		//response返回错误信息
		if(null != resultMap.get("msg")){
			throw new LabelException(resultMap.get("msg").toString());
		}
		String trackNo = resultMap.get("barcodeNumber").toString().substring(8);
		String fileCode = resultMap.get("labelImage").toString();
		return new Result(fileCode, trackNo);
	}
	
	private String buildRequestXml(RequestMessage requestMsg, DeliveryWay dw){
		String userName = UspsDomesticConfig.SERVICE_USERNAME();//ConfigUtil.getValue(Constants.SYSCONFIG,"WT_USPS_USERNAME");
		//效验
		if(StringUtils.isEmpty(userName)){
			logger.error("userName is null");
			throw new LabelException("userName is null");
		}
		String password = UspsDomesticConfig.SERVICE_PASSWORD();//ConfigUtil.getValue(Constants.SYSCONFIG,"WT_USPS_PASSWORD");
		//效验
		if(StringUtils.isEmpty(password)){
			logger.error("password is null");
			throw new LabelException("password is null");
		}
//		
		String serviceCode = dw.getServiceCode();
		//效验
		if(StringUtils.isEmpty(serviceCode)){
			logger.error("serviceCode is null");
			throw new LabelException("serviceCode is null");
		}
		
		
		// fromPhone
		String[] phoneNums = UspsDomesticConfig.SENDER_PHONENUMBER().split("-");
		String fromPhone = new StringBuffer(phoneNums[0]).
									append(phoneNums[1]).
									append(phoneNums[2]).toString();
		// fromAddress2
		String fromAddress2 = UspsDomesticConfig.SENDER_ADDRESSLINE2();
		if(StringUtils.isBlank(fromAddress2)){
			fromAddress2 = UspsDomesticConfig.SENDER_ADDRESSLINE1();
		}
		// toAddress2
		String toAddress2 = requestMsg.getConsignee().getAddress2();
		if(StringUtils.isNotBlank( requestMsg.getConsignee().getAddress3())){
			toAddress2 +=  requestMsg.getConsignee().getAddress3();
		}
		// packWeight
		BigDecimal packWeight = new BigDecimal(requestMsg.getWeight());
		//盎司转换成kg 1kg = 35.27396194958oz
		DecimalFormat fnum = new DecimalFormat("##0.00"); 
		BigDecimal weight = packWeight.multiply(new BigDecimal(35.27396194958));
		// toZipCode
		String[] toZipCode = requestMsg.getConsignee().getPostcode().split("-");
		//服务
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
		Calendar ca=Calendar.getInstance();
		ca.setTime(new Date());
		ca.add(Calendar.HOUR_OF_DAY, 3);
		String priceOptions= "<PriceOptions/>";
		String container = UspsDomesticConfig.FIRST_CLASS_CONTAINER();//ConfigUtil.getValue(Constants.SYSCONFIG,"WT_USPS_FIRST-CLASS_CONTAINER");
		if(serviceCode.equalsIgnoreCase("FIRST CLASS") && weight.compareTo(new BigDecimal(13)) > 0){
			container = UspsDomesticConfig.FIRST_PRIORITY_CONTAINER();//ConfigUtil.getValue(Constants.SYSCONFIG,"WT_USPS_PRIORITY_CONTAINER");
			priceOptions = "<PriceOptions>Commercial Plus</PriceOptions>";
		}else if(serviceCode.equalsIgnoreCase("PRIORITY")){
			container = UspsDomesticConfig.FIRST_PRIORITY_CONTAINER();//ConfigUtil.getValue(Constants.SYSCONFIG,"WT_USPS_PRIORITY_CONTAINER");
		}
		String xml =  "<eVSRequest  USERID='"+userName+"' PASSWORD='"+password+"'>"
		   +"<Option />"
		   +"<Revision/>"
		   +"<ImageParameters>"
		       +"<ImageParameter>4x6LabelL</ImageParameter>"
		   +"</ImageParameters>"
		   +"<FromName>"+UspsDomesticConfig.SENDER_NAME()+"</FromName>"
		   +"<FromFirm/>"
		   +"<FromAddress1/>"
		   +"<FromAddress2>"+fromAddress2+"</FromAddress2>"
		   +"<FromCity>"+ UspsDomesticConfig.SENDER_CITY() +"</FromCity>"
		   +"<FromState>" + UspsDomesticConfig.SENDER_PROVINCECODE() + "</FromState>"
		   +"<FromZip5>"+UspsDomesticConfig.SENDER_POSTALCODE() + "</FromZip5>"
		   +"<FromZip4/>"
		   +"<FromPhone>" + fromPhone  + "</FromPhone>"
		   +"<AllowNonCleansedOriginAddr>true</AllowNonCleansedOriginAddr>"
		   +"<ToName>" + requestMsg.getConsignee().getName() + "</ToName>"
		   +"<ToFirm></ToFirm>"
		   +"<ToAddress1>" + StringUtils.defaultIfEmpty(toAddress2,"") + "</ToAddress1>"
		   +"<ToAddress2>" + requestMsg.getConsignee().getAddress1()+ "</ToAddress2>"
		   +"<ToCity>" + requestMsg.getConsignee().getCity() + "</ToCity>"
		   +"<ToState>" + requestMsg.getConsignee().getState()+ "</ToState>"
		   +"<ToZip5>"+toZipCode[0]+"</ToZip5>"
		   +"<ToZip4/>"
		   +"<ToPhone></ToPhone>"
		   +"<AllowNonCleansedDestAddr>true</AllowNonCleansedDestAddr>"
		   //小于1120
		   +"<WeightInOunces>"+fnum.format(weight)+"</WeightInOunces>"
		   //enumeration=EXPRESS
		   //enumeration=PRIORITY
		   //enumeration=FIRST CLASS
		   //enumeration=METRO POST
		   +"<ServiceType>"+serviceCode+"</ServiceType>"
		   +"<Container>"+container+"</Container>"
		   +"<Width>"+requestMsg.getWidth()+"</Width>"
		   +"<Length>"+requestMsg.getLength()+"</Length>"
		   +"<Height>"+requestMsg.getHeight()+"</Height>"
//		   +"<Machinable>false</Machinable>"
		   +priceOptions
		   +"<ShipDate>"+df.format(ca.getTime())+"</ShipDate>"
		   +"<CustomerRefNo>"+requestMsg.getDocumentNo()+"</CustomerRefNo>"
		   +"<ReceiptOption/>"
		   +"<ImageType>PDF</ImageType>"
		   +"<HoldForManifest/>"
		   +"<NineDigitRoutingZip/>"
		   +"<CarrierRelease>true</CarrierRelease>"
		   +"<DropOffTime/>"
		   +"<ReturnCommitments/>"
		   +"<PrintCustomerRefNo>true</PrintCustomerRefNo>"
		+"</eVSRequest>";	
		return xml;
	}
	
	private String getResponseXml(String xmlRequest,String url) {
    	String srt2 = null;
    	try
		{
			PostMethod post = new PostMethod(url);
			RequestEntity entity = new StringRequestEntity("API=eVS&XML="+xmlRequest, "text/xml", null);
			post.setRequestEntity(entity);
			// Create a HttpClient to do the transfer
			HttpClient httpClient = new HttpClient();
			int result = httpClient.executeMethod(post);
			if(result != HttpStatus.SC_OK){
				logger.error( "Create USPS label,reqeust error.");
				return null;
			}
			// Get the response as bytes or a stream for parsing the xml response
			byte[] xmlBytes = post.getResponseBody();
			srt2 = new String(xmlBytes,"UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			logger.error( "Create USPS label,transfer error.");
			throw new LabelException("error");
		}
		catch (HttpException e)
		{
			logger.error("Create USPS label,address error.", e);
			throw new LabelException("error");
		}
		catch (IOException e)
		{
			logger.error( "Create USPS label,get response error.", e);
			throw new LabelException("error");
		}catch(Exception e){
			logger.error( "Create USPS label error.", e);
			throw new LabelException("error");
		}
		return srt2;
    }
	
	private Map<String,Object> resolveXml(String xml) throws DocumentException{
		//返回参数
		Map<String,Object> map = new HashMap<String,Object>();
		//将返回值转换成xml文档
		Document doc = DocumentHelper.parseText(xml);
		//获取根节点
		Element rootElt = doc.getRootElement();
		if(rootElt != null && "Error".equalsIgnoreCase(rootElt.getName())){
			// 获取错误信息
			Element Description = rootElt.element("Description");
			map.put("msg", Description.getTextTrim());
		}else {
			// 获取子节点LabelImage
			Element LabelImage = rootElt.element("LabelImage");
			map.put("labelImage", LabelImage.getTextTrim());
			Element barcodeNumber = rootElt.element("BarcodeNumber");
			map.put("barcodeNumber", barcodeNumber.getTextTrim());
		}
		return map;
	}


	@Override
	public boolean isIdempotent()
	{
		return false;
	}
	
}
