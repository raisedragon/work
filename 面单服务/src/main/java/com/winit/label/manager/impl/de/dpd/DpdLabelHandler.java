package com.winit.label.manager.impl.de.dpd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.winit.commons.http.DefaultSecureProtocolSocketFactory;
import com.winit.commons.http.HttpClientUtil;
import com.winit.commons.http.UtilSSLSocketFactory;
import com.winit.exception.LabelBusinessException;
import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.impl.de.dpd.login.response.AuthenticationFault;
import com.winit.label.manager.impl.de.dpd.shipment.Address;
import com.winit.label.manager.impl.de.dpd.shipment.FaultCodeType;
import com.winit.label.manager.impl.de.dpd.shipment.GeneralShipmentData;
import com.winit.label.manager.impl.de.dpd.shipment.Parcel;
import com.winit.label.manager.impl.de.dpd.shipment.ParcelInformationType;
import com.winit.label.manager.impl.de.dpd.shipment.ProductAndServiceData;
import com.winit.label.manager.impl.de.dpd.shipment.ShipmentResponse;
import com.winit.label.manager.impl.de.dpd.shipment.ShipmentServiceData;
import com.winit.label.manager.impl.de.dpd.shipment.StoreOrders;
import com.winit.label.manager.impl.de.dpd.shipment.StoreOrdersResponse;
import com.winit.label.manager.impl.de.dpd.shipment.StoreOrdersResponseType;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;

/**
 * 调用DPD shipment API，获取面单
 * @author temuser2
 *
 */
@Component
public class DpdLabelHandler implements LabelHandler
{
	
	private DecimalFormat df = new DecimalFormat("#");
	
	private static HttpClient httpClient;
	
	private HttpClient getHttpClient(){
		if(httpClient==null){
			httpClient = new HttpClient();
			Protocol https = new Protocol("https",(ProtocolSocketFactory) new UtilSSLSocketFactory().trustAllCertificates().disableDiffieHellman(), 443);
			Protocol.registerProtocol("https", https);
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60 * 1000);
			httpClient.getHttpConnectionManager().getParams().setSoTimeout(60 * 1000);
		}
//		httpClient.getHostConfiguration().setHost(host, port, authhttps);
		return httpClient;
	}
	
	
	
//	public String getShipLableCode(Map<String, Object> map, Properties ctx, String documentNo, String trxName)
//			throws Exception
//	{
//		String code = getCode(map, documentNo);
//		return code;
//	}
	
	/**
	 * 
	 * @param message 请求消息对象
	 * @param reAuth 如果授权失败，是否更新授权信息后重试
	 * @return
	 * @throws Exception
	 */
	public Result getCode(RequestMessage message,boolean reAuth) throws Exception {
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
		System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire.header", "debug");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");
		String trackingNo = null;
		String request = getShipmentSoap(message);
		String response = HttpClientUtil.post(DpdConfig.WT_DPD_SHIPMENT_API_URL(), request.getBytes("utf-8"),"text/xml;charset=UTF-8",null);
		Object obj = parseResponse(response);
		if(obj instanceof String) {
			// 错误信息
			throw new LabelBusinessException((String) obj);
		}
		if(obj instanceof AuthenticationFault) {
			if(reAuth){
				// 认证失效，重新获取认证信息
				DpdLogin dl = new DpdLogin();
				dl.login();
				return getCode(message,false);
			}else{
				throw new LabelBusinessException(((AuthenticationFault)obj).getErrorMessage());
			}
			
		} else {
			StoreOrdersResponse sor = (StoreOrdersResponse) obj;
			StoreOrdersResponseType result = sor.getOrderResult();
			byte[] data = result.getParcellabelsPDF();
			
			List<ShipmentResponse> list = result.getShipmentResponses();
			if(list.size() > 0) {
				ShipmentResponse sr = list.get(0);
				
				// 获取label失败
				List<FaultCodeType> faults = sr.getFaults();
				if(faults.size() > 0) {
					String msg = faults.get(0).getMessage();
					throw new RuntimeException(msg);
				}
				
				// 获取label成功，取出trackingNo
				List<ParcelInformationType> parcelInfos = sr.getParcelInformation();
				if(parcelInfos.size() > 0) {
					trackingNo = parcelInfos.get(0).getParcelLabelNumber();
					
//					MOWMSExWarehouse mExWarehouse = MOWMSExWarehouse.getByDocumentNo(Env.getCtx(), documentNo, null);
//					mExWarehouse.setTrackingNo(trackingNo);
//					mExWarehouse.saveEx();
				}
			}
			
			// 对label编码
//			Base64.encodeBase64(data);
//			return new String(Base64.encodeBase64(data), "utf-8");
			return new Result( new String(Base64.encodeBase64(data), "utf-8"), trackingNo);
		}
	}
	
	public StoreOrders getStoreOrders(RequestMessage request) {
		// 发件人
		Address sender = new Address();
		sender.setName1(DpdConfig.WT_GERMANY_COMPANYNAME());
		sender.setStreet(DpdConfig.WT_GERMANY_ADDRESS1());
		sender.setCountry("DE");
		sender.setZipCode(DpdConfig.WT_GERMANY_POSTCODE());
		sender.setCity(DpdConfig.WT_GERMANY_CITY());
		sender.setCustomerNumber(DpdConfig.WT_DPD_CUSTOMER_NUMBER());
		
		StringBuffer street = new StringBuffer();
		street.append(request.getConsignee().getAddress1());
		if(StringUtils.isEmpty(request.getConsignee().getAddress2())) {
			street.append(" ").append(request.getConsignee().getAddress2());
		}
		if(StringUtils.isEmpty(request.getConsignee().getAddress3())) {
			street.append(" ").append(request.getConsignee().getAddress3());
		}
		String address = street.toString();
		
		// 收件人
		Address recipient = new Address();
		recipient.setName1(request.getConsignee().getName());
		if(address.length() < 35) {
			recipient.setStreet(address);
		} else {
			recipient.setContact(address.substring(0, 20));
			recipient.setStreet(address.substring(20));
		}
		String countryCode = request.getConsignee().getCountryCode();
		if(countryCode != null && countryCode.toUpperCase().equals("UK")) {
			countryCode = "GB";
		}
		recipient.setCountry(countryCode.toUpperCase());
		recipient.setZipCode(StringUtils.deleteWhitespace(request.getConsignee().getPostcode()).toUpperCase());
		recipient.setCity(request.getConsignee().getCity());
		
		// 如果超过12点，则dateStr是下一天的日期
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);
		if(hour >= 12) {
			now.add(Calendar.DATE, 1);
		}
		String dateStr = new SimpleDateFormat("yyyyMMdd").format(now.getTime());
		
		GeneralShipmentData gsd = new GeneralShipmentData();
		gsd.setIdentificationNumber("MPS" + StringUtils.leftPad(request.getDocumentNo(), 14, "0") + dateStr); // MPS + ParcelNumber + dateStr
		gsd.setMpsCustomerReferenceNumber1(request.getDocumentNo());
		gsd.setSendingDepot(DpdLogin.DEPOT);
		gsd.setProduct("CL");
		gsd.setSender(sender);
		gsd.setRecipient(recipient);
		
		// 包裹重量
		double weight =request.getWeight(); // gram
		String parcelWeight = df.format(weight / 10); // in 10 gram unit
		
		// 包裹
		Parcel parcel = new Parcel();
		parcel.setWeight(Integer.valueOf(parcelWeight));
		
		List<Parcel> parcels = new ArrayList<Parcel>();
		parcels.add(parcel);
		
		ProductAndServiceData pasd = new ProductAndServiceData();
		pasd.setOrderType("consignment");
		
		ShipmentServiceData ssd = new ShipmentServiceData();
		ssd.setGeneralShipmentData(gsd);
		ssd.setParcels(parcels);
		ssd.setProductAndServiceData(pasd);
		
		List<ShipmentServiceData> order = new ArrayList<ShipmentServiceData>();
		order.add(ssd);
		
		StoreOrders orders = new StoreOrders();
		orders.setPaperFormat("A6");
		orders.setOrder(order);
		
		return orders;
	}
	
	/**
	 * 获取shipment报文
	 * @param authToken
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String getShipmentSoap(RequestMessage request) throws Exception {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<soapenv:Envelope");
			buffer.append(" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"");
			buffer.append(" xmlns:ns=\"http://dpd.com/common/service/types/Authentication/2.0\"");
			buffer.append(" xmlns:ns1=\"http://dpd.com/common/service/types/ShipmentService/2.0\">");
			buffer.append("<soapenv:Header>");
				buffer.append("<ns:authentication>");
					buffer.append("<delisId>").append(DpdConfig.WT_DPD_USERNAME()).append("</delisId>");
					buffer.append("<authToken>").append(DpdLogin.AUTH).append("</authToken>");
					buffer.append("<messageLanguage>en_EN</messageLanguage>");
				buffer.append("</ns:authentication>");
			buffer.append("</soapenv:Header>");
			buffer.append("<soapenv:Body>");
				buffer.append(getStoreOrdersXml(request));
			buffer.append("</soapenv:Body>");
		buffer.append("</soapenv:Envelope>");
		
		return buffer.toString();
	}
	
	/**
	 * 封装StoreOrders对象，并将其转换成xml
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String getStoreOrdersXml(RequestMessage request) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		StoreOrders orders = getStoreOrders(request);
		JAXBElement<StoreOrders> element = new JAXBElement<StoreOrders>(new QName("ns1:storeOrders"), StoreOrders.class, orders);
		JAXBContext ctx = JAXBContext.newInstance(StoreOrders.class);
		Marshaller m = ctx.createMarshaller();
		m.marshal(element, out);
		
		return new String(out.toByteArray(), "utf-8").replaceAll("<\\?(.[^\\?]*)\\?>", "");
	}
	
	/**
	 * 解析回执报文
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public Object parseResponse(String xml) throws Exception {
		XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xif.createXMLStreamReader(new ByteArrayInputStream(xml.getBytes("utf-8")));
        
        String errorMsg = "";
        Boolean success = null;
        while(xsr.hasNext()) {
        	xsr.next();
        	if(xsr.getEventType() == XMLStreamReader.START_ELEMENT) {
	        	if(xsr.getLocalName().equals("storeOrdersResponse")) {
	        		success = true;
	        		break;
	        	}
	        	
	        	if(xsr.getLocalName().equals("authenticationFault")) {
	        		success = false;
	        		break;
	        	}
	        	
	        	if(xsr.getLocalName().equals("faultstring")) {
	        		errorMsg = xsr.getElementText();
	        	}
        	}
        }
        
        if(success == null) {
        	return errorMsg;
        } else if(success.booleanValue() == true) {
	        JAXBContext jc = JAXBContext.newInstance(StoreOrdersResponse.class);
	        Unmarshaller unmarshaller = jc.createUnmarshaller();
	        JAXBElement<StoreOrdersResponse> element = unmarshaller.unmarshal(xsr, StoreOrdersResponse.class);
	        xsr.close();
	
	        return element.getValue();
        } else if(success.booleanValue() == false) {
        	JAXBContext jc = JAXBContext.newInstance(AuthenticationFault.class);
	        Unmarshaller unmarshaller = jc.createUnmarshaller();
	        JAXBElement<AuthenticationFault> element = unmarshaller.unmarshal(xsr, AuthenticationFault.class);
	        xsr.close();
	
	        return element.getValue();
        } else {
        	return null;
        }
	}

	@Override
	public Result handle(RequestMessage requestMessage, DeliveryWay deliveryWay) throws Exception
	{
		Result result = getCode(requestMessage,true);
		return result;
	}

	@Override
	public boolean isIdempotent()
	{
		return true;
	}

}
