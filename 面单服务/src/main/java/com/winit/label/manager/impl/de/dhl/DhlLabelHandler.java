package com.winit.label.manager.impl.de.dhl;

import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.winit.commons.jasperreports.JasperReportUtils;
import com.winit.label.manager.LabelHandler;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;
import com.winit.label.model.RequestMessage.Consignee;

/**
 * DHL DE面单获取
 * @author longsheng.wang
 *
 */
@Component
public class DhlLabelHandler implements LabelHandler {
	
	private static Logger	s_log	= LoggerFactory.getLogger(DhlLabelHandler.class);
	

	
	/**
	 * 构建请求报文
	 * @param map
	 * @param serviceType
	 * @return
	 * @throws Exception
	 */
	private String buildRequestXml(RequestMessage requestMessage,DeliveryWay deliveryWay) throws Exception{
		// date
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(date);
		
		//shipperStreet
		int shipperIndex = DhlConfig.DHLDE_LABLE_ADDRESS1().lastIndexOf(" ")+1;
		String streetName =DhlConfig.DHLDE_LABLE_ADDRESS1().substring(0, shipperIndex);
		String streetNumber = DhlConfig.DHLDE_LABLE_ADDRESS1().substring(shipperIndex);
		
		//receiverStreet
		StringBuffer streetBuf = new StringBuffer();
		streetBuf.append(requestMessage.getConsignee().getAddress1());
		if(requestMessage.getConsignee().getAddress2() != null) {
			streetBuf.append(" ").append(requestMessage.getConsignee().getAddress2());
		}
		if(requestMessage.getConsignee().getAddress3() != null) {
			streetBuf.append(" ").append(requestMessage.getConsignee().getAddress3());
		}
		String street = streetBuf.toString();
//		int receiverIndex = street.lastIndexOf(" ")+1;
//		String receiverStreetName = street.substring(0,receiverIndex);
//		String receiverStreetNumber = street.substring(receiverIndex);
		
		//countryCode
		String countryCode =deliveryWay.getServiceCode()=="EPN"?"DE":requestMessage.getConsignee().getCountryCode();
		// 英国编码转换
		if("UK".equals(countryCode)){
			countryCode = "GB";
		}
		
		//WeightInKG
		String WeightInKG = null;
		if(requestMessage.getWeight()>0.1){
			WeightInKG = String.valueOf(requestMessage.getWeight());
		}else{
			WeightInKG = "0.1";
		}
		
		//contactPerson
		String contactPerson = "";
		String parentID = DhlConfig.DHLDE_LABEL_PARTNERID();
		if("BPI".equals(deliveryWay.getServiceCode())){
			contactPerson = requestMessage.getConsignee().getName();
			parentID = DhlConfig.DHLDE_LABEL_PARTNERID_BPI();
		}
		
		String requestXml = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\""
			+" xmlns:cis=\"http://dhl.de/webservice/cisbase\" xmlns:de=\"http://de.ws.intraship\">"
			+"<soap:Header>"
				+"<cis:Authentification>"
					+"<cis:user>"+DhlConfig.DHLDE_LABEL_USER()+"</cis:user>"
					+"<cis:signature>"+DhlConfig.DHLDE_LABEL_SIGNATURE()+"</cis:signature>"
					+"<cis:type>0</cis:type>"
				+"</cis:Authentification>"
			+"</soap:Header>"
			+"<soap:Body>"
				+"<de:CreateShipmentDDRequest>"
					+"<cis:Version>"
						+"<cis:majorRelease>1</cis:majorRelease>"
						+"<cis:minorRelease>0</cis:minorRelease>"
					+"</cis:Version>"
					+"<ShipmentOrder>"
						+"<SequenceNumber>1</SequenceNumber>"
						+"<Shipment>"
							+"<ShipmentDetails>"
								+"<ProductCode>"+deliveryWay.getServiceCode()+"</ProductCode>"
								+"<ShipmentDate>"+dateStr+"</ShipmentDate>"
								+"<cis:EKP>"+DhlConfig.DHLDE_LABEL_EKP()+"</cis:EKP>"
								+"<Attendance>"
									+"<cis:partnerID>"+parentID+"</cis:partnerID>"
								+"</Attendance>"
								+"<CustomerReference>"+requestMessage.getDocumentNo()+"</CustomerReference>"
								+"<ShipmentItem>"
									+"<WeightInKG>"+WeightInKG+"</WeightInKG>"
									+"<PackageType>PK</PackageType>"
								+"</ShipmentItem>"
							+"</ShipmentDetails>"
							+"<Shipper>"
								+"<Company>"
									+"<cis:Company>"
										+"<cis:name1>"+DhlConfig.DHLDE_LABLE_COMPANYNAME()+"</cis:name1>"
									+"</cis:Company>"
								+"</Company>"
								+"<Address>"
									+"<cis:streetName>"+streetName+"</cis:streetName>"
									+"<cis:streetNumber>"+streetNumber+"</cis:streetNumber>"
									+"<cis:Zip>"
										+"<cis:germany>"+DhlConfig.DHLDE_LABLE_POSTCODE()+"</cis:germany>"
									+"</cis:Zip>"
									+"<cis:city>"+DhlConfig.DHLDE_LABLE_CITY()+"</cis:city>"
									+"<cis:Origin>"
										+"<cis:countryISOCode>DE</cis:countryISOCode>"
									+"</cis:Origin>"
								+"</Address>"
								+"<Communication>"
									+"<cis:phone>0049-30-763291</cis:phone>"
								+"</Communication>"
							+"</Shipper>"
							+"<Receiver>"
								+"<Company>"
//									+"<cis:Person>"
//										+"<cis:firstname> </cis:firstname>"
//										+"<cis:lastname> </cis:lastname>"
//									+"</cis:Person>"
									+"<cis:Company>"
										+"<cis:name1>"+requestMessage.getConsignee().getName()+"</cis:name1>"
										+"<cis:name2>"+requestMessage.getConsignee().getPostcode()+"</cis:name2>"
									+"</cis:Company>"
								+"</Company>"
								+"<Address>"
									+"<cis:streetName>"+street+"</cis:streetName>"
									+"<cis:streetNumber>"+requestMessage.getConsignee().getHouseNo()+"</cis:streetNumber>"
									+"<cis:Zip>"
										+"<cis:other>"+requestMessage.getConsignee().getPostcode()+"</cis:other>"
									+"</cis:Zip>"
									+"<cis:city>"+requestMessage.getConsignee().getCity()+"</cis:city>"
									+"<cis:Origin>"
										+"<cis:countryISOCode>"+countryCode+"</cis:countryISOCode>"
									+"</cis:Origin>"
								+"</Address>"
								+"<Communication>"
									+"<cis:phone>"+requestMessage.getConsignee().getPhone()+"</cis:phone>"
									+"<cis:contactPerson>" + contactPerson + "</cis:contactPerson>"
							    +"</Communication>"
							+"</Receiver>"
						+"</Shipment>"
//						+"<PRINTONLYIFCODEABLE>1</PRINTONLYIFCODEABLE>"
					+"</ShipmentOrder>"
				+"</de:CreateShipmentDDRequest>"
			+"</soap:Body>"
		+"</soap:Envelope>";
		return requestXml;
	}
	
	/**
	 * 发送http请求
	 * @param xmlRequest
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private String getResponseXml(String xmlRequest,String url) throws Exception{
		String responseXml = null;
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		try {
			StringRequestEntity entity = new StringRequestEntity(xmlRequest);
			post.setRequestHeader("Content-Type", "application/soap+xml;charset=utf-8;action=\"urn:createShipmentDD\"");
			post.setRequestEntity(entity);
			String basic = "Basic "+ Base64.encodeBase64String((DhlConfig.DHLDE_LABEL_USERNAME()+":"+DhlConfig.DHLDE_LABEL_PASSWORD()).getBytes());
			post.setRequestHeader("Authorization", basic);
			int statusCode = client.executeMethod(post);
			if(statusCode != HttpStatus.SC_OK){
				s_log.error("Create DHL label,reqeust error.");
				return null;
			}
			responseXml = IOUtils.toString(post.getResponseBodyAsStream());
		} catch (Exception e) {
			s_log.error("Create DHL label error.", e);
			throw new Exception("error");
		} finally {
			post.releaseConnection();
		}
		return responseXml;
    }
	
	/**
	 * 处理返回报文
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	private Map<String,Object> resolveXml(String xml,String documentNo) throws Exception{
		//返回参数
		Map<String,Object> map = new HashMap<String,Object>();
		//将返回值转换成xml文档
		Document doc = DocumentHelper.parseText(xml);
		//获取根节点
		Element rootElt = doc.getRootElement();
		Element Body = rootElt.element("Body");
		Element CreateShipmentResponse = Body.element("CreateShipmentResponse");
		Element CreationState = CreateShipmentResponse.element("CreationState");
		Element StatusCode = CreationState.element("StatusCode");
		if(!StatusCode.getTextTrim().equals("0")){
			// 获取错误信息
			Iterator<?> iter = CreationState.elementIterator("StatusMessage");
			StringBuffer buf = new StringBuffer();
			while(iter.hasNext()){
				Element ele = (Element) iter.next();
				buf.append(ele.getTextTrim());
				buf.append(";");
			}
			map.put("msg", buf.toString());
		}else {
			// 保存快递单号
			Element shipperNum = CreationState.element("ShipmentNumber");
			String trackingNo = shipperNum.elementText("shipmentNumber");
			
//			MOWMSExWarehouse mExWarehouse = MOWMSExWarehouse.getByDocumentNo(Env.getCtx(), documentNo, null);
//			mExWarehouse.setTrackingNo(trackingNo);
//			mExWarehouse.saveEx();
			
			// 获取子节点LabelImage
			Element Labelurl = CreationState.element("Labelurl");
			map.put("labelImage", getBase64ImageFromUrl(Labelurl.getTextTrim()));
			map.put("trackingNo", trackingNo);
		}
		return map;
	}
	
	/**
	 * 下载PDF，返回base64编码
	 * @param imageUrl
	 * @return
	 * @throws Exception
	 */
	private String getBase64ImageFromUrl(String imageUrl) throws Exception{
		URL url = new URL(imageUrl);
		URLConnection conn = url.openConnection();
		byte[] data = IOUtils.toByteArray(conn.getInputStream());
		return Base64.encodeBase64String(data);
		
		/*PDDocument document = PDDocument.load(conn.getInputStream());
		PDPage pdPage = (PDPage)document.getDocumentCatalog().getAllPages().get(0);
		
		BufferedImage image = RenderUtil.convertToImage(pdPage, 1, 256);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", out);
		byte[] bytes = Base64.encodeBase64(out.toByteArray());
		return new String(bytes, "utf-8");*/
	}

	@Override
	public Result handle(RequestMessage requestMessage, DeliveryWay deliveryWay) throws Exception
	{
		String serviceCode = deliveryWay.getServiceCode();
		if("EPN".equalsIgnoreCase(serviceCode) || "BPI".equalsIgnoreCase(serviceCode)){
			String requestXml = buildRequestXml(requestMessage, deliveryWay);
			s_log.info(requestXml);
			String responseXml = getResponseXml(requestXml,DhlConfig.DHLDE_LABEL_URL());
			s_log.info(responseXml);
			//请求出错
			if(responseXml == null){
				throw new Exception("error");
			}
			Map<String,Object> resultMap = resolveXml(responseXml,requestMessage.getDocumentNo());
			//response返回错误信息
			if(null != resultMap.get("msg")){
				String msg = resultMap.get("msg").toString();
				throw new Exception(msg);
			}
			String code = resultMap.get("labelImage").toString();
			return new Result(code,(String) resultMap.get("trackingNo"));
		}else{
			Map<String, Object> params = new HashMap<String, Object>();
			//放入出库单号，用于查询发货地址和邮编
			params.put("documentNo", requestMessage.getDocumentNo());
			// Sender
			params.put("senderCompanyname",DhlConfig.DHLDE_LABLE_COMPANYNAME());
			params.put("senderAddress",DhlConfig.DHLDE_LABLE_ADDRESS1());
			params.put("senderCity", DhlConfig.DHLDE_LABLE_CITY());
			params.put("senderPostcode",  DhlConfig.DHLDE_LABLE_POSTCODE());
			params.put("serviceCode", serviceCode);
			
			Consignee consignee = requestMessage.getConsignee();
			List<String> address = new ArrayList<String>();
			if(!StringUtils.isEmpty(consignee.getAddress1())){
				address.add(consignee.getAddress1());
			}
			if(!StringUtils.isEmpty(consignee.getAddress2())){
				address.add(consignee.getAddress2());
			}
			if(!StringUtils.isEmpty(consignee.getAddress3())){
				address.add(consignee.getAddress3());
			}
			
			
			params.put("NAME", ensureNotNull(consignee.getName()));
			params.put("ADDRESS", ensureNotNull(StringUtils.join(address.iterator()," ")));
			params.put("CITY", ensureNotNull(consignee.getCity()));
			params.put("POSTAL", ensureNotNull(consignee.getPostcode()));
			params.put("COUNTRYNAME", ensureNotNull(getDisplayCountry(consignee.getCountryCode())));
			params.put("WEIGHT", requestMessage.getWeight());
			
			String code = JasperReportUtils.generatePdfAsBase64(DhlConfig.DHLDE_LABEL_FILENAME(), params);
			return new Result(code, null);
		}
	}

	@Override
	public boolean isIdempotent()
	{
		return true;
	}
	/**
	 * 获取国家名称
	 * 
	 * @param code ISO 国家代码
	 * @return
	 */
	private static String getDisplayCountry(String code)
	{
		if ("UK".equals(code))
		{
			code = "GB";
		}
		return new Locale("", code).getDisplayCountry(Locale.US);
	}
	
	private String ensureNotNull(String str){
		if(str==null){
			return "";
		}else{
			return str;
		}
	}
}
