package com.winit.label.manager.impl.us.ups;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winit.exception.LabelBusinessException;
import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.impl.us.ups.model.confirm.accessrequest.AccessRequest;
import com.winit.label.manager.impl.us.ups.model.confirm.request.BillShipperType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.CN22ContentType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.CN22FormType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.CodeType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.EEIInformationType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.InternationalFormsType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.LabelImageFormatCodeDescriptionType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.LabelPrintMethodCodeDescriptionType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.LabelSpecificationType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.PackageServiceOptionsDeliveryConfirmationType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.PackageServiceOptionsType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.PackageType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.PackageWeightType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.PackagingTypeType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.PaymentInformationType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.PrepaidType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.ProductType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.ProductWeightType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.RateInformationType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.ReferenceNumberType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.RequestType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.ServiceType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.ShipToAddressType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.ShipToType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.ShipmentConfirmRequest;
import com.winit.label.manager.impl.us.ups.model.confirm.request.ShipmentServiceOptionsDeliveryConfirmationType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.ShipmentServiceOptionsType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.ShipmentType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.ShipperAddressType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.ShipperType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.TransactionReferenceType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.UnitOfMeasurementType;
import com.winit.label.manager.impl.us.ups.model.confirm.request.UnitType;
import com.winit.label.manager.impl.us.ups.model.confirm.response.ShipmentConfirmResponse;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;
import com.winit.label.model.RequestMessage.ClassifyProduct;
import com.winit.label.model.RequestMessage.Consignee;
import com.winit.label.model.RequestMessage.Product;
import com.winit.label.support.ConfigUtil;


@Component
public class UpsLabelHandler implements LabelHandler
{

	protected static final Logger logger = Logger.getLogger(UpsLabelHandler.class);  
	
	
	@Autowired
	private JaxbShipAcceptClient jaxbShipAcceptClient;
	
	@Override
	public Result handle(RequestMessage requestMsg,DeliveryWay dw) throws Exception{
		Result result = null;
		StringWriter strWriter = null;
		String msg = null;
		try
		{
			// Create JAXBContext and marshaller for AccessRequest object
			JAXBContext accessRequestJAXBC = JAXBContext.newInstance(AccessRequest.class.getPackage().getName());
			Marshaller accessRequestMarshaller = accessRequestJAXBC.createMarshaller();
			com.winit.label.manager.impl.us.ups.model.confirm.accessrequest.ObjectFactory accessRequestObjectFactory = new com.winit.label.manager.impl.us.ups.model.confirm.accessrequest.ObjectFactory();
			AccessRequest accessRequest = accessRequestObjectFactory.createAccessRequest();
			populateAccessRequest(accessRequest);
			JAXBContext shipConfirmRequestJAXBC = JAXBContext.newInstance(ShipmentConfirmRequest.class.getPackage()
					.getName());
			Marshaller shipConfirmRequestMarshaller = shipConfirmRequestJAXBC.createMarshaller();
			com.winit.label.manager.impl.us.ups.model.confirm.request.ObjectFactory requestObjectFactory = new com.winit.label.manager.impl.us.ups.model.confirm.request.ObjectFactory();
			ShipmentConfirmRequest shipConfirmRequest = requestObjectFactory.createShipmentConfirmRequest();
			populateShipConfirmRequest(shipConfirmRequest, requestMsg,dw);
			strWriter = new StringWriter();
			accessRequestMarshaller.marshal(accessRequest, strWriter);
			shipConfirmRequestMarshaller.marshal(shipConfirmRequest, strWriter);
			strWriter.flush();
			strWriter.close();
			logger.info("Request: " + strWriter.getBuffer());
			String strResults = contactService(strWriter.getBuffer().toString());
			// Parse response object
			JAXBContext shipConfirmJAXBC = JAXBContext
					.newInstance(ShipmentConfirmResponse.class.getPackage().getName());
			Unmarshaller shipConfirmUnmarshaller = shipConfirmJAXBC.createUnmarshaller();
			ByteArrayInputStream input = new ByteArrayInputStream(strResults.getBytes());
			Object objResponse = shipConfirmUnmarshaller.unmarshal(input);
			ShipmentConfirmResponse shipconfirmResponse = (ShipmentConfirmResponse) objResponse;
			logger.info("Response Status Description: " + shipconfirmResponse.getResponse().getResponseStatusDescription());
			if ("1".equals(shipconfirmResponse.getResponse().getResponseStatusCode()))
			{
				Map<String,Object> map = jaxbShipAcceptClient.getBaseCode(shipconfirmResponse.getShipmentDigest(),requestMsg.getDocumentNo());
				String trackNo = map.get("trackNo").toString();
				String fileCode = map.get("fileCode").toString();
				result = new Result(fileCode, trackNo);
			}else{
				msg=shipconfirmResponse.getResponse().getError().get(0).getErrorCode()+shipconfirmResponse.getResponse().getError().get(0).getErrorDescription();
				throw new LabelBusinessException(msg);
			}
		}
		finally
		{
			try
			{
				if (strWriter != null)
				{
					strWriter.close();
					strWriter = null;
				}
			}
			catch (Exception e)
			{
				logger.error(e.getMessage());
			}
		}
		return result;
	}

	private  void populateShipConfirmRequest(ShipmentConfirmRequest shipRequest,RequestMessage requestMsg,DeliveryWay dw)
	{
		Consignee consignee = requestMsg.getConsignee();
		String documentNo = requestMsg.getDocumentNo().trim();
		String serviceCode = dw.getServiceCode().trim();
		String city = consignee.getCity().trim();
		RequestType request = new RequestType();
		TransactionReferenceType transactionReference = new TransactionReferenceType();
		transactionReference.setCustomerContext(ConfigUtil.getValue("WT_UPS_CUSTOMERCONTEXT"));
		request.setTransactionReference(transactionReference);
		request.setRequestAction("shipConfirm");
		request.setRequestOption("nonvalidate");
		shipRequest.setRequest(request);
		ShipmentType shpmnt = new ShipmentType();
		

		

		/** *******Shipper******************** */
		ShipperType shipper = new ShipperType();
		shipper.setName(ConfigUtil.getValue("WT_UPS_NAME").toString()+documentNo);
		shipper.setShipperNumber(ConfigUtil.getValue("WT_UPS_SHIPPERNUMBER"));
//		shipper.setPhoneNumber(MSysConfig.getValue("WT_UPS_PHONENUMBER"));
		shipper.setAttentionName(ConfigUtil.getValue("WT_UPS_ATTENTIONNAME").toString()+documentNo);
		
		ShipperAddressType shipperAddress = new ShipperAddressType();
		shipperAddress.setAddressLine1(ConfigUtil.getValue("WT_UPS_ADDRESSLINE1"));
		shipperAddress.setAddressLine2(ConfigUtil.getValue("WT_UPS_ADDRESSLINE2"));
		shipperAddress.setAddressLine3(ConfigUtil.getValue("WT_UPS_ADDRESSLINE3"));
		shipperAddress.setCity(ConfigUtil.getValue("WT_UPS_CITY"));
		shipperAddress.setPostalCode(ConfigUtil.getValue("WT_UPS_POSTALCODE"));
		shipperAddress.setStateProvinceCode(ConfigUtil.getValue("WT_UPS_PROVINCECODE"));
		shipperAddress.setCountryCode(ConfigUtil.getValue("WT_UPS_COUNTRYCODE"));
		shipper.setAddress(shipperAddress);

		shpmnt.setShipper(shipper);
		/** ******Shipper********************* */

		/** ************ShipTo****************** */
		ShipToType shipTo = new ShipToType();
		shipTo.setCompanyName(consignee.getName());
//		if(null!=map.get("BuyPhoneNumber")&&map.get("BuyPhoneNumber").toString().length()>9&&map.get("BuyPhoneNumber").toString().length()<16){
//		shipTo.setPhoneNumber(map.get("BuyPhoneNumber"));
//		}
		ShipToAddressType shipToAddress = new ShipToAddressType();
		if(StringUtils.isNotEmpty(consignee.getAddress1())){
		shipToAddress.setAddressLine1(consignee.getAddress1());
		}
		if(StringUtils.isNotEmpty(consignee.getAddress2())){
		shipToAddress.setAddressLine2(consignee.getAddress2());
		}else{
			shipToAddress.setAddressLine2(" ");
		}
		if(StringUtils.isNotEmpty(consignee.getAddress3())){
		shipToAddress.setAddressLine3(consignee.getAddress3());
		}else{
			shipToAddress.setAddressLine3(" ");
		}
		shipToAddress.setCity(consignee.getCity());
		shipToAddress.setPostalCode(consignee.getPostcode());
		shipToAddress.setCountryCode(consignee.getCountryCode());
		shipToAddress.setStateProvinceCode(consignee.getState());
		shipTo.setAddress(shipToAddress);
		shpmnt.setShipTo(shipTo);
		/** ***********ShipTo********************* */

		/** ********Service********************** */
		ServiceType service = new ServiceType();
		service.setCode(serviceCode);
		shpmnt.setService(service);
		/** ********Service********************** */

		/** ******************Package***************** */
		PackageType pkg1 = new PackageType();
		PackagingTypeType pkgingType = new PackagingTypeType();
		pkgingType.setCode("02");
		pkg1.setPackagingType(pkgingType);
		
		ReferenceNumberType  type= new ReferenceNumberType();
		type.setCode("WT");
		type.setValue(requestMsg.getDocumentNo());
		pkg1.getReferenceNumber().add(type);
		
		PackageType[] pkgArray = { pkg1 };
		
		//TODO 测试时，注意重量的计算是否正确
		PackageWeightType weight = new PackageWeightType();
		if(requestMsg.getWeight() > 0.1){
			weight.setWeight(String.valueOf(requestMsg.getWeight()));
		}else{
			weight.setWeight("0.1");
		}
		UnitOfMeasurementType shpUnitOfMeas = new UnitOfMeasurementType();
		shpUnitOfMeas.setCode("LBS");
        shpUnitOfMeas.setDescription("Pounds");
		if("93".equals(serviceCode)){
			if(requestMsg.getWeight() < 1){
				  service.setCode("92");
				  shpmnt.setService(service);
				  if(requestMsg.getWeight() * 16 > 0.1){
						weight.setWeight(String.valueOf(requestMsg.getWeight() * 16));
					}else{
						weight.setWeight("0.1");
				   } 
				  shpUnitOfMeas.setCode("OZS");
		          shpUnitOfMeas.setDescription("Ounces");
			}
		}
		weight.setUnitOfMeasurement(shpUnitOfMeas);
		
		pkg1.setPackageWeight(weight);
		shpmnt.getPackage().add(pkgArray[0]);

		/** ******************Package***************** */
		if(("93".equals(serviceCode) || "92".equals(serviceCode)) 
				&& ("FPO".equalsIgnoreCase(city)) || "APO".equalsIgnoreCase(city)){
			// ---------------------CN22 Start----------------//
			
			/********** CodeType ***********************/
			CodeType codeType = new CodeType();
			codeType.setCode("LBS");
			/******** ProductWeightType **********************/
	
			/********** ProductWeightType ***********************/
			ProductWeightType productWeightType = new ProductWeightType();
			productWeightType.setUnitOfMeasurement(codeType);
			DecimalFormat fnum = new DecimalFormat("##0.00"); 
			productWeightType.setWeight(fnum.format(Double.valueOf(weight.getWeight())));
			/******** ProductWeightType **********************/
	
			/********** CN22ContentType ***********************/
			
			List<Product> products = requestMsg.getProducts();
			int totalCount = 0;
			double totalValue = 0;
			for(Product product:products){
				totalCount+=product.getQty();
				List<ClassifyProduct> classifyProducts = product.getClassifyProducts();
				for(ClassifyProduct classifyProduct : classifyProducts) {
					String countryCode = classifyProduct.getCountryCode();
					if(countryCode.equalsIgnoreCase("US")) {
						//TODO 检查是否要乘产品数量
						totalValue = totalValue + classifyProduct.getPriceImports() * product.getQty();
						break;
					}
				}
			}
			
			CN22ContentType cn22ContentType = new CN22ContentType();
			String contentQuantity = totalCount+"";
			// 商品总数
			cn22ContentType.setCN22ContentQuantity(contentQuantity);
			// 描述
			cn22ContentType.setCN22ContentDescription("text cn22");
			cn22ContentType.setCN22ContentWeight(productWeightType);
			// 总值
			cn22ContentType.setCN22ContentTotalValue(fnum.format(totalValue));
			cn22ContentType.setCN22ContentCurrencyCode("USD");
			/******** CN22ContentType **********************/
	
			/********** CN22FormType ***********************/
			CN22FormType cn22FormType = new CN22FormType();
			// label尺寸
			cn22FormType.setLabelSize("6");
			// 打印页数
			cn22FormType.setPrintsPerPage("1");
			// 打印类型
			cn22FormType.setLabelPrintType("GIF");
			// 1 = GIFT 2 = DOCUMENTS 3 = COMMERCIAL SAMPLE 4 = OTHER
			cn22FormType.setCN22Type("4");
			// 描述
			cn22FormType.setCN22OtherDescription("text cn22");
			// 头部
			cn22FormType.setFoldHereText("fold");
			cn22FormType.getCN22Content().add(cn22ContentType);
			/******** CN22FormType **********************/
	
			/******** ProductType **********************/
			ProductType productType = new ProductType();
			// 商品描述
			productType.getDescription().add("Some Goods");
			productType.setUnit(new UnitType());
			
			productType.setEEIInformation(new EEIInformationType());
			/******** ProductType **********************/
	
			/******** InternationalForms **********************/
			InternationalFormsType internationalForms = new InternationalFormsType();
			internationalForms.setCN22Form(cn22FormType);
			internationalForms.getFormType().add("09");
			internationalForms.getProduct().add(productType);
			/******** InternationalForms **********************/
	
			/******** ShipmentServiceOptions ******************/
			ShipmentServiceOptionsType shipmentServiceOptionsType = new ShipmentServiceOptionsType();
			shipmentServiceOptionsType.setInternationalForms(internationalForms);
			shpmnt.setShipmentServiceOptions(shipmentServiceOptionsType);
			/******** ShipmentServiceOptions ******************/
			// ---------------------------CN22 END-------------------------//
		}
		
		/** *************Payment Information***************** */
		PaymentInformationType payInfo = new PaymentInformationType();
		PrepaidType prepaid = new PrepaidType();
		BillShipperType billShipper = new BillShipperType();
		billShipper.setAccountNumber(ConfigUtil.getValue("WT_UPS_SHIPPERNUMBER"));
		prepaid.setBillShipper(billShipper);
		payInfo.setPrepaid(prepaid);
		shpmnt.setPaymentInformation(payInfo);
		/** *************Payment Information***************** */

		/** **********Label Specification ******************** */
		LabelSpecificationType labelSpecType = new LabelSpecificationType();
		LabelImageFormatCodeDescriptionType labelImageFormat = new LabelImageFormatCodeDescriptionType();
		labelImageFormat.setCode("GIF");
		labelImageFormat.setDescription("GIF");
		labelSpecType.setLabelImageFormat(labelImageFormat);

		LabelPrintMethodCodeDescriptionType labelPrintmethod = new LabelPrintMethodCodeDescriptionType();
		labelPrintmethod.setCode("GIF");
		labelPrintmethod.setDescription("gif file");
		labelSpecType.setLabelPrintMethod(labelPrintmethod);

		labelSpecType.setHTTPUserAgent("Mozilla/4.5");
		shipRequest.setLabelSpecification(labelSpecType);
		/** ***********Label Specification********************* */

		/*************** InternationalForms ********************/
		/*************** InternationalForms **********************/
		if("93".equals(serviceCode)||"92".equals(serviceCode)){
			shpmnt.setUSPSEndorsement("1");
			shpmnt.setSubClassification("MA");
		}
		shpmnt.setDescription("Some Goods");
		
		
		shipRequest.setShipment(shpmnt);
		
		/** ******************DCISType***************** */
		if("US".equalsIgnoreCase(requestMsg.getConsignee().getCountryCode())||
			"VI".equalsIgnoreCase(requestMsg.getConsignee().getState())){
			for(PackageType pkg :shpmnt.getPackage()){
				PackageServiceOptionsType packageServiceOptions = pkg.getPackageServiceOptions();
				if(packageServiceOptions==null){
					packageServiceOptions = new PackageServiceOptionsType();
					pkg.setPackageServiceOptions(packageServiceOptions);
				}
				PackageServiceOptionsDeliveryConfirmationType deliveryConfirmation = new PackageServiceOptionsDeliveryConfirmationType();
				deliveryConfirmation.setDCISType("2");
				packageServiceOptions.setDeliveryConfirmation(deliveryConfirmation);
			}
			
		}else{
			ShipmentServiceOptionsType shipmentServiceOptions =  shpmnt.getShipmentServiceOptions();
			if(shipmentServiceOptions==null){
				shipmentServiceOptions = new ShipmentServiceOptionsType();
				shpmnt.setShipmentServiceOptions(shipmentServiceOptions);
			}
			ShipmentServiceOptionsDeliveryConfirmationType shipmentServiceOptionsDeliveryConfirmation = new ShipmentServiceOptionsDeliveryConfirmationType();
			shipmentServiceOptionsDeliveryConfirmation.setDCISType("2");
			shipmentServiceOptions.setDeliveryConfirmation(shipmentServiceOptionsDeliveryConfirmation);
//			
		}
		/** ******************DCISType***************** */

	}

	private String contactService(String xmlInputString) throws Exception
	{
		String outputStr = null;
		OutputStream outputStream = null;
		try
		{

			URL url = new URL(ConfigUtil.getValue("WT_LABLE_API_ENDPOINT_URL").toString()+ConfigUtil.getValue("WT_LABLE_API_ENDPOINT_URL_CONFIRM_PATH"));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			logger.info("Client established connection with " + url);
			// Setup HTTP POST parameters
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setConnectTimeout(30000);
			connection.setReadTimeout(30000);

			outputStream = connection.getOutputStream();
			outputStream.write(xmlInputString.getBytes());
			outputStream.flush();
			outputStream.close();
			logger.info("Http status = " + connection.getResponseCode() + " " + connection.getResponseMessage());

			outputStr = readURLConnection(connection);
			logger.info(outputStr);
		}
		catch (Exception e)
		{
			logger.error("Error sending data to server");
		}
		finally
		{
			if (outputStream != null)
			{
				outputStream.close();
				outputStream = null;
			}
		}
		return outputStr;
	}

	/**
	 * This method read all of the data from a URL connection to a String
	 */

	public static String readURLConnection(URLConnection uc) throws Exception
	{
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			int letter = 0;
			reader.readLine();
			while ((letter = reader.read()) != -1)
			{
				buffer.append((char) letter);
			}
			reader.close();
		}
		catch (Exception e)
		{
			logger.error("Could not read from URL: " + e);
		}
		finally
		{
			if (reader != null)
			{
				reader.close();
				reader = null;
			}
		}
		return buffer.toString();
	}

	/**
	 * Populates the access request object.
	 * 
	 * @param accessRequest
	 */
	private void populateAccessRequest(AccessRequest accessRequest)
	{
		accessRequest.setAccessLicenseNumber(ConfigUtil.getValue("WT_LABLE_API_LICENSE_NUMBER"));
		accessRequest.setUserId(ConfigUtil.getValue("WT_LABLE_API_USER_NAME"));
		accessRequest.setPassword(ConfigUtil.getValue("WT_LABLE_API_PASSWORD"));
	}

	@Override
	public boolean isIdempotent()
	{
		return true;
	}
}
