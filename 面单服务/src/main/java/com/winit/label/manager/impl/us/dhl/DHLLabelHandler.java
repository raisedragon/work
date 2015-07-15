
package com.winit.label.manager.impl.us.dhl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.winit.exception.LabelBusinessException;
import com.winit.exception.LabelSystemException;
import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.impl.us.dhl.model.ErrorResponse;
import com.winit.label.manager.impl.us.dhl.model.ObjectFactory;
import com.winit.label.manager.impl.us.dhl.model.ShipmentRequest;
import com.winit.label.manager.impl.us.dhl.model.ShipmentResponse;
import com.winit.label.manager.impl.us.dhl.model.ShipmentValidateErrorResponse;
import com.winit.label.manager.impl.us.dhl.model.datatypes.Condition;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.Billing;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.Consignee;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.Contact;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.DimensionUnit;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.Dutiable;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.Label;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.Piece;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.Pieces;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.Reference;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.Request;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.ServiceHeader;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.ShipmentDetails;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.Shipper;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.WeightUnit;
import com.winit.label.manager.impl.us.dhl.model.datatypes_global.YesNo;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;
import com.winit.label.model.RequestMessage.ClassifyProduct;
import com.winit.label.model.RequestMessage.Product;



/**
 * US DHL(国际？)面单 Manager
 * @author longsheng.wang
 *
 */
@Component("US_DHLLabelHandler")
public class  DHLLabelHandler implements LabelHandler{      
	
	private static Logger logger =LoggerFactory.getLogger(DHLLabelHandler.class);
	
	private Marshaller marshaller = null;
	private Unmarshaller unmarshaller = null;
	
	private HttpClient httpClient = null;
	
	/**
	 * 默认构造函数。<br/>
	 * 初始分Jaxb marshaller/unmarshaller,httpClient
	 * @throws JAXBException
	 */
	public DHLLabelHandler() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		marshaller =  jaxbContext.createMarshaller();
		unmarshaller =  jaxbContext.createUnmarshaller();
		httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60 * 1000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(60 * 1000);
		
	}


	
	
	@Override
	public Result handle(RequestMessage requestMessage, DeliveryWay deliveryWay) throws Exception{
			String requestXml = buildRequestXml(requestMessage,deliveryWay);
			logger.info(requestXml);
			String responseXml = request(requestXml, DHLConfig.SERVICE_URL());
			logger.info(responseXml);
			Result result = handleResponse(responseXml,requestMessage,deliveryWay);
			return result;
	}

	

	/**
	 * 构造请求报文
	 * @param exWarehouse
	 * @return
	 * @throws JAXBException
	 * @throws DatatypeConfigurationException
	 */
	private String buildRequestXml(RequestMessage requestMessage, DeliveryWay deliveryWay) throws JAXBException, DatatypeConfigurationException {
		String msgTmep = "'%s' :Value '%s' with length '%s', exceeds maximum length facet of '%s'";
		if(requestMessage.getConsignee().getName()!=null)
			checkLength(requestMessage.getConsignee().getName(), 35, String.format(msgTmep, "Confignee Name",requestMessage.getConsignee().getName(),requestMessage.getConsignee().getName().length(),35));
		if(requestMessage.getConsignee().getCity()!=null)
			checkLength(requestMessage.getConsignee().getCity(), 35, String.format(msgTmep, "Confignee City",requestMessage.getConsignee().getCity(),requestMessage.getConsignee().getCity().length(),35));
		if(requestMessage.getConsignee().getAddress1()!=null)
			checkLength(requestMessage.getConsignee().getAddress1(), 35, String.format(msgTmep, "Confignee Address1",requestMessage.getConsignee().getAddress1(),requestMessage.getConsignee().getAddress1().length(),35));
		if(requestMessage.getConsignee().getAddress2()!=null)
			checkLength(requestMessage.getConsignee().getAddress2(), 35, String.format(msgTmep, "Confignee Address2",requestMessage.getConsignee().getAddress2(),requestMessage.getConsignee().getAddress2().length(),35));
		if(requestMessage.getConsignee().getAddress3()!=null)
			checkLength(requestMessage.getConsignee().getAddress3(), 35, String.format(msgTmep, "Confignee Address3",requestMessage.getConsignee().getAddress3(),requestMessage.getConsignee().getAddress3().length(),35));
		if(requestMessage.getConsignee().getState()!=null)
			checkLength(requestMessage.getConsignee().getState(), 35, String.format(msgTmep, "Confignee State",requestMessage.getConsignee().getState(),requestMessage.getConsignee().getState().length(),35));
		
		ShipmentRequest request = this.createShipmentRequest(requestMessage, deliveryWay);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		marshaller.marshal(request, baos);
		String clientRequestXML = baos.toString();
		return clientRequestXML;
	}




	/**
	 * 发送请求报文，返回响应报文
	 * @param requestXml 请求报文
	 * @param httpURL 请求地址
	 * @return 响应报文
	 * @throws IOException
	 * @throws JAXBException
	 */
	public String request (String requestXml, String httpURL) throws IOException, JAXBException{
		
		PostMethod method = new PostMethod(httpURL);
		RequestEntity entity = new StringRequestEntity(requestXml,"application/x-www-form-urlencoded","UTF-8");
		method.setRequestEntity(entity);;

		try{
			httpClient.executeMethod(method);
		}catch (IOException ex) {
			throw new  BussinessAdempiereException(ex);
		}
		if(HttpStatus.SC_OK!=method.getStatusCode()){
			throw new  RuntimeException(method.getStatusText());
		}

		InputStream is = method.getResponseBodyAsStream();


		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		StringBuilder result = new StringBuilder();
		String line = "";

		while ((line = rd.readLine()) != null) {
			result.append(line).append("\n");
		}

		return result.toString();


	}
	





	private String getGlobalProductCode(DeliveryWay deliveryWay) {
		return deliveryWay.getServiceCode();
	}

	/**
	 * 根据国家代码，返回对应国家的英文形式全称
	 * @param countryCode
	 * @return
	 */
	private String getCountryNameByCode(String countryCode) {
		String code = this.getLastCountryCode(countryCode);
		return new Locale("", code).getDisplayCountry(Locale.US);
	}

	/**
	 * 根据输入的国家代码，返回标准国家代码
	 * @param countryCode
	 * @return
	 */
	private String getLastCountryCode(String countryCode) {
		if("UK".equals(countryCode)){
			return "GB";
		}
		return countryCode;
	}



	
	/**
	 * 根据出库单，创建请求对象
	 * @param exWarehouse
	 * @return
	 * @throws DatatypeConfigurationException
	 */
	private ShipmentRequest  createShipmentRequest(RequestMessage requestMessage, DeliveryWay deliveryWay) throws DatatypeConfigurationException{
		StringBuffer contents = new StringBuffer();
		List<Product> plist = requestMessage.getProducts();
		double totalValue = 0;
		for (Product p:plist)
		{
			if(contents.length()>0){
				contents.append(" ");
			}
			contents.append(p.getName());
			
			double unitValue = 0;
			
			for(ClassifyProduct classifyProduct : p.getClassifyProducts()) {
				String countryCode = classifyProduct.getCountryCode();
				if(countryCode.equalsIgnoreCase("US")) {
					unitValue = unitValue + classifyProduct.getPriceImports();
					break;
				}
			}
			totalValue +=unitValue;
		}

		
		
			Date currentDate = new Date();
			GregorianCalendar currentCalendar = new GregorianCalendar();
			currentCalendar.setTime(currentDate);
			
			ShipmentRequest shipmentRequest = new ShipmentRequest();
			//TODO
			shipmentRequest.setSchemaVersion(new BigDecimal("1.0"));
			Request request = new Request();
			ServiceHeader serviceHeader = new ServiceHeader();
			XMLGregorianCalendar messageTime = DatatypeFactory.newInstance()
			        .newXMLGregorianCalendar(currentCalendar);
			
			serviceHeader.setMessageTime(messageTime);
			serviceHeader.setMessageReference(getMesageReference(requestMessage));
			serviceHeader.setPassword(DHLConfig.PASSWORD());
			serviceHeader.setSiteID(DHLConfig.SITE_ID());
			request.setServiceHeader(serviceHeader);
			
			shipmentRequest.setRequest(request);
			
			shipmentRequest.setRegionCode(DHLConfig.REGION_CODE());
			
			shipmentRequest.setRequestedPickupTime(DHLConfig.REQUESTED_PICKUP_TIME());
			
			shipmentRequest.setLanguageCode(DHLConfig.LANGUAGE_CODE());
			
			shipmentRequest.setPiecesEnabled(DHLConfig.PIECES_ENABLED());
			
			Billing billing = new Billing();
			billing.setBillingAccountNumber(DHLConfig.BILLING_ACCOUNT_NUMBER());
			billing.setDutyAccountNumber(DHLConfig.DUTY_ACCOUNT_NUMBER());
			billing.setDutyPaymentType(DHLConfig.DUTY_PAYMENT_TYPE());
			billing.setShipperAccountNumber(DHLConfig.SHIPPER_ACCOUNT_NUMBER());
			billing.setShippingPaymentType(DHLConfig.SHIPPING_PAYMENT_TYPE());
			shipmentRequest.setBilling(billing);
			
			Consignee consignee = new Consignee();
			consignee.getAddressLine().add(requestMessage.getConsignee().getAddress1());
			if(StringUtils.isNotEmpty(requestMessage.getConsignee().getAddress2())){
				consignee.getAddressLine().add(requestMessage.getConsignee().getAddress2());
			}
			if(StringUtils.isNotEmpty(requestMessage.getConsignee().getAddress3())){
				consignee.getAddressLine().add(requestMessage.getConsignee().getAddress3());
			}
			consignee.setCity(requestMessage.getConsignee().getCity());
			consignee.setCompanyName(requestMessage.getConsignee().getName());
			Contact contact = new Contact();
			contact.setEmail(requestMessage.getConsignee().getEmail());
			contact.setPersonName(requestMessage.getConsignee().getName());
			contact.setPhoneNumber(StringUtils.isEmpty(requestMessage.getConsignee().getPhone())?DHLConfig.DEFAULT_PHONE_NUM():requestMessage.getConsignee().getPhone());
			consignee.setContact(contact);
			consignee.setCountryCode(getLastCountryCode(requestMessage.getConsignee().getCountryCode()));
			consignee.setCountryName(getCountryNameByCode(requestMessage.getConsignee().getCountryCode()));
			consignee.setDivision(requestMessage.getConsignee().getState());
			consignee.setPostalCode(requestMessage.getConsignee().getPostcode());
			shipmentRequest.setConsignee(consignee);
			
			Dutiable dutiable = new Dutiable();
			dutiable.setDeclaredValue(new BigDecimal(totalValue).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
			dutiable.setDeclaredCurrency("USD");
			shipmentRequest.setDutiable(dutiable);
			
			Reference reference = new Reference();
			reference.setReferenceID(requestMessage.getDocumentNo());
			shipmentRequest.getReference().add(reference);
			
			ShipmentDetails shipmentDetails = new ShipmentDetails();
			shipmentDetails.setNumberOfPieces(1);
			Pieces pieces = new Pieces();
			Piece piece = new Piece();
			piece.setPieceID("1");
			pieces.getPiece().add(piece);
			shipmentDetails.setPieces(pieces);
			shipmentDetails.setWeight(BigDecimal.valueOf(requestMessage.getWeight()).setScale(3, BigDecimal.ROUND_HALF_UP));
			shipmentDetails.setWeightUnit(WeightUnit.K);
			shipmentDetails.setGlobalProductCode(getGlobalProductCode(deliveryWay));
			XMLGregorianCalendar date = DatatypeFactory.newInstance()
			        .newXMLGregorianCalendarDate(currentCalendar.get(Calendar.YEAR),
			        		currentCalendar.get(Calendar.MONTH)+1,
			        		currentCalendar.get(Calendar.DATE),
			        		 DatatypeConstants.FIELD_UNDEFINED
			        		);
			shipmentDetails.setDate(date);
			//TODO
	//		shipmentDetails.setInsuredAmount(value);
			shipmentDetails.setDimensionUnit(DimensionUnit.C);
			shipmentDetails.setCurrencyCode("USD");
			shipmentDetails.setContents(contents.length()==0?"Unknow":limitStr(contents.toString(),90));
			shipmentRequest.setShipmentDetails(shipmentDetails);
			
			Shipper shipper = new Shipper();
			shipper.getAddressLine().add(DHLConfig.SENDER_ADDRESS1());
			if(StringUtils.isNotEmpty(DHLConfig.SENDER_ADDRESS2())){
				shipper.getAddressLine().add(DHLConfig.SENDER_ADDRESS2());
			}
			if(StringUtils.isNotEmpty(DHLConfig.SENDER_ADDRESS3())){
				shipper.getAddressLine().add(DHLConfig.SENDER_ADDRESS3());
			}
			shipper.setCity(DHLConfig.SENDER_CITY());
			shipper.setCompanyName(DHLConfig.SENDER_COMPANY_NAME());
			Contact senderContact = new Contact();
			senderContact.setEmail(DHLConfig.SENDER_CONTACT_EMAIL());
			senderContact.setFaxNumber(DHLConfig.SENDER_CONTACT_FAX_NUMBER());
			senderContact.setPersonName(DHLConfig.SENDER_CONTACT_PERSON_NAME());
			senderContact.setPhoneExtension(DHLConfig.SENDER_CONTACT_PHONE_EXTENSION());
			senderContact.setPhoneNumber(DHLConfig.SENDER_CONTACT_PHONE_NUMBER());
			senderContact.setTelex(DHLConfig.SENDER_CONTACT_TELEX());
			shipper.setContact(senderContact);
			shipper.setCountryCode(DHLConfig.SENDER_COUNTRY_CODE());
			shipper.setCountryName(DHLConfig.SENDER_COUNTRY_NAME());
			shipper.setDivision(DHLConfig.SENDER_DIVISION());
			shipper.setDivisionCode(DHLConfig.SENDER_DIVISION_CODE());
			shipper.setPostalCode(DHLConfig.SENDER_POSTAL_CODE());
			shipper.setRegisteredAccount(DHLConfig.SENDER_REGISTERED_ACCOUNT());
			shipper.setShipperID(DHLConfig.SENDER_SHIPPER_ID());
			shipmentRequest.setShipper(shipper);
			shipmentRequest.setRequestArchiveDoc(YesNo.N);
			shipmentRequest.setLabelImageFormat(DHLConfig.LABEL_IMAGE_FORMAT());
			
			Label label = new Label();
			label.setLabelTemplate(DHLConfig.LABEL_TEMPLATE());
			label.setLogo(DHLConfig.LABEL_LOGO());
			shipmentRequest.setLabel(label);
			
			return shipmentRequest;
		}



	/**
	 * 解析并处理返回报文
	 * @param trxName
	 * @param responseXml
	 * @param exWarehouse
	 * @return
	 * @throws Exception
	 */
	private Result handleResponse(String responseXml,RequestMessage requestMessage, DeliveryWay deliveryWay) throws Exception{
		ByteArrayInputStream bais  = new ByteArrayInputStream(responseXml.getBytes());;
		Object obj = unmarshaller.unmarshal(bais);
		if(obj instanceof ErrorResponse){
			ErrorResponse error = (ErrorResponse) obj;
			StringBuffer sbuf = new StringBuffer();
			boolean isSysErr = false;
			for(Condition condition:error.getResponse().getStatus().getCondition()){
				sbuf.append("["+condition.getConditionCode()+"] "+condition.getConditionData()+"\n");
				if(DHLConfig.SHIPMENT_VALID_SVR_SYS_ERR_CODES().contains(condition.getConditionCode())){
					isSysErr = true;
				}
			}
			if(isSysErr){
				throw new LabelSystemException(sbuf.toString());
			}else{
				throw new LabelBusinessException(sbuf.toString());
			}
		}
		if(obj instanceof ShipmentValidateErrorResponse){
			ShipmentValidateErrorResponse error = (ShipmentValidateErrorResponse) obj;
			StringBuffer sbuf = new StringBuffer();
			boolean isSysErr = false;
			for(Condition condition:error.getResponse().getStatus().getCondition()){
				sbuf.append("["+condition.getConditionCode()+"] "+condition.getConditionData()+"\n");
				if(DHLConfig.SHIPMENT_VALID_SVR_SYS_ERR_CODES().contains(condition.getConditionCode())){
					isSysErr = true;
				}
			}
			if(isSysErr){
				throw new LabelSystemException(sbuf.toString());
			}else{
				throw new LabelBusinessException(sbuf.toString());
			}
		}
		ShipmentResponse response = (ShipmentResponse) obj;
		if(response.getLabelImage().size()<1){
			throw new LabelBusinessException("Shipment Response contains 0 LableImage");
		}
		byte[] datas = response.getLabelImage().get(0).getOutputImage();
		
		String code = new String(Base64.encodeBase64(datas));
		
		//保存快递单号
		String trackingNo = response.getAirwayBillNumber();
		
		logger.info("tracking no.: "+trackingNo);
		logger.info("label image code: "+code);
		
		return new Result(code, trackingNo);
	}

	private String limitStr(String str,int len){
		if(str==null){
			return str;
		}
		if(str.length()<=len){
			return str;
		}
		return str.substring(0,len);
	}



	private String getMesageReference(RequestMessage requestMessage) {
		String documentNo = StringUtils.leftPad(requestMessage.getDocumentNo(),16,"0");
		String time = StringUtils.leftPad(""+System.currentTimeMillis(),16,"0");
		return limitStr(documentNo+time,32);
	}

	private void checkLength(String str,int len,String msg){
		if(str!=null){
			if(str.length()>len)
				throw new BussinessAdempiereException(msg);
		}
	}




	@Override
	public boolean isIdempotent() {
		return false;
	}
}