package com.winit.svr.label.handler.impl.usa.usps;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.axis.message.MessageElement;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import com.winit.label.model.RequestMessage;
import com.winit.label.model.RequestMessage.ClassifyProduct;
import com.winit.label.model.RequestMessage.Consignee;
import com.winit.label.model.RequestMessage.Logistics;
import com.winit.label.model.RequestMessage.Product;
import com.winit.svr.LabelBusinessException;
import com.winit.svr.LabelException;
import com.winit.svr.LabelSystemException;
import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.label.LabelHandler;
import com.winit.svr.label.context.ContextUtils;
import com.winit.svr.label.handler.impl.usa.usps.USPSIntlProcessPackageSoapWrapper.LoadAndRecordLabeledPackageResultWrapper;
import com.winit.svr.label.handler.impl.usa.usps.model.LabelResult;
import com.winit.svr.label.handler.impl.usa.usps.model.LoadAndRecordLabeledPackageXmlDoc;
import com.winit.svr.label.handler.impl.usa.usps.model.TrackResult;
import com.winit.svr.label.handler.impl.usa.usps.model.ext.request.RequestManifest;
import com.winit.svr.label.handler.impl.usa.usps.model.ext.response.DispatchConfirmation;
import com.winit.svr.label.handler.impl.usa.usps.model.ext.response.PackageError;
import com.winit.svr.label.handler.impl.usa.usps.model.ext.response.PackageIdentifier;
import com.winit.svr.label.handler.impl.usa.usps.model.ext.response.ResponseManifest;

public class USPSIntlLabelHandler implements LabelHandler
{

	Logger logger = LoggerFactory.getLogger(USPSIntlLabelHandler.class);
	private static final String MANIFEST_FILE_FORMAT_VERSION = "8";
	
	private static String SENDER_NAME(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SENDER_NAME","").trim();
	}

	private static String SENDER_FIRST_NAME(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SENDER_FIRST_NAME","").trim();
	}

	private static String SENDER_MIDDLE_INITIAL(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SENDER_MIDDLE_INITIAL","").trim();
	}

	private static String SENDER_LAST_NAME(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SENDER_LAST_NAME","").trim();
	}

	private static String SENDER_BUSINESS_NAME(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SENDER_BUSINESS_NAME","").trim();
	}

	private static String SENDER_ADDRESS_LINE1(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SENDER_ADDRESS_LINE1","").trim();
	}

	private static String SENDER_ADDRESS_LINE2(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SENDER_ADDRESS_LINE2","").trim();
	}

	private static String SENDER_ADDRESS_LINE3(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SENDER_ADDRESS_LINE3","").trim();
	}

	private static String SENDER_CITY(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SENDER_CITY","").trim();
	}

	private static String SENDER_PROVINCE(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SENDER_PROVINCE","").trim();
	}

	private static String SENDER_POSTAL_CODE(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SENDER_POSTAL_CODE","").trim();
	}

	private static String SENDER_COUNTRY_CODE(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SENDER_COUNTRY_CODE","").trim();
	}

	private static String SENDER_ADDRESS_IS_PO_BOX(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SENDER_ADDRESS_IS_PO_BOX","").trim();
	}

	private static String SENDER_PHONE(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SENDER_PHONE","").trim();
	}

	private static String SENDER_EMAIL(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SENDER_EMAIL","").trim();
	}

	private static String SENDER_TAXPAYER_ID(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SENDER_TAXPAYER_ID","").trim();
	}


	
	private static String RECEIVING_AGENT_ID(){
		return ContextUtils.getValue("USPS_INTL_LABEL_RECEIVING_AGENT_ID", "").trim();
	}
	private static String SHIPPING_AGENT_ID(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SHIPPING_AGENT_ID", "").trim();
	}
	private static String MAILING_AGENT_ID(){
		return ContextUtils.getValue("USPS_INTL_LABEL_MAILING_AGENT_ID", "").trim();
	}
	
	private static String ALREADY_LOAD_ERR_MSG(){
		return ContextUtils.getValue("USPS_INTL_LABEL_ALREADY_LOAD_ERR_MSG", "").trim();
	}
	
	private static String LABLE_FORMAT(){
		return ContextUtils.getValue("USPS_INTL_LABEL_FORMAT", "").trim();
	}
	
	private static final String	RECIPIENT_ADDRESS_IS_PO_BOX	= "N";

	private static final String	PACKAGE_TYPE(){
		return ContextUtils.getValue("USPS_INTL_LABEL_PACKAGE_TYPE", "").trim();
	}

	private static final String	SHIPPING_CURRENCY_TYPE		= "USD";

	private static final String	WEIGHT_UNIT					= "KG";
	
	private static final String UNIT_OF_MEASUREMENT = "CM";
	
	private static String SERVICE_TYPE(){
		return ContextUtils.getValue("USPS_INTL_LABEL_SERVICE_TYPE", "").trim();
	}
	
	private static String RATE_TYPE(String code){
		return (String) Context
				.getCommandContext()
				.getLogisticTypeManager()
				.findByLogisticTypeCode(code)
				.getVariable("serviceCode");
	}
	
	private static String DOMESTIC_RATE_TYPE(){
		return ContextUtils.getValue("USPS_DOMESTIC_RATE_TYPE", "").trim();
	}
	
	private static String TRACKING_ID_SEPARATOR(){
		return ContextUtils.getValue("USPS_INTL_LABEL_TRACKING_ID_SEPARATOR", "|");
	}
	
	private static final int	PACKAGE_PHYSICAL_COUNT		= 1;

	private static final String	POSTAGE_PAID_CURRENCY_TYPE	= "USD";
	private static final String	VALUE_LOADED				= "N";
	
	private static String PF_COR_EEL(){
		return ContextUtils.getValue("USPS_INTL_LABEL_PF_COR_EEL", "NOEEI 30.37(a)");
	}

	private static final String	ITEM_VALUE_CURRENCY_TYPE	= "USD";

	private static final String	RETURN_SERVICE_REQUESTED	= "N";

	private static final String	STRING_BLANK				= "";
	

	public Result handle(CommandContext commandContext, RequestMessage requestMessage)
	{
		return this.handle(commandContext, requestMessage,true);
	}
	
	@SuppressWarnings("restriction")
	public Result handle(CommandContext commandContext, RequestMessage requestMessage,boolean isIntl)
	{
		String documentNo = requestMessage.getBody().getDocumentNo();
		
		String lableCode = null;
		String trackingNo = null;
		boolean err_flag = false;
		try{
			
			
			RequestManifest manifest = null;
			if(isIntl){
				manifest = getRequestManifest( requestMessage);
			}else{
				manifest = getRequestManifestForDomestic(requestMessage);
			}
			USPSIntlProcessPackageSoapWrapper port = USPSIntlProcessPackageSoapWrapper.getInstance();
			
			LoadAndRecordLabeledPackageXmlDoc xmlDoc = new LoadAndRecordLabeledPackageXmlDoc();
			
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			Document document = factory.newDocumentBuilder().newDocument();
			
			JAXBContext requestCtx = JAXBContext.newInstance(RequestManifest.class);
			Marshaller marshaller = requestCtx.createMarshaller();
			marshaller.marshal(manifest, document);
	//		marshaller.marshal(manifest, System.out);
			
			xmlDoc.set_any(new MessageElement[]{ new MessageElement(document.getDocumentElement()) });
			
			//1.LoadAndRecordLabeledPackageResultWrapper 
			LoadAndRecordLabeledPackageResultWrapper loadAndRecordLabeledPackageResultWrapper = port.loadAndRecordLabeledPackage(xmlDoc);
			
			ResponseManifest responseManifest = loadAndRecordLabeledPackageResultWrapper.getManifest();
			if(responseManifest==null){
				err_flag = true;
				throw new LabelException("Response Manifest is null");
			}
			DispatchConfirmation dispatchConfirmation = responseManifest.getDispatchConfirmation();
			if(dispatchConfirmation.getRejectPackageCount()!=0){
				StringBuffer errMsg = new StringBuffer();
				for(PackageError error:responseManifest.getPackageErrors()){
					errMsg.append(StringUtils.join(error.getErrorDescription().iterator(), "\n"));
				}
				
				if(String.format(ALREADY_LOAD_ERR_MSG(), documentNo,documentNo, documentNo,documentNo,documentNo).equalsIgnoreCase(errMsg.toString().trim())){
					//TODO
//					if(StringUtils.isEmpty(exWarehouse.getTrackingNo())){
						TrackResult trackResult = port.trackPackage(documentNo, MAILING_AGENT_ID(), 1);
						if(0!=trackResult.getResponseCode()){
							err_flag = true;
							throw new LabelException(trackResult.getMessage());
						}
						String[] trackingIds = trackResult.getTrackingId();
						if(trackingIds!=null && trackingIds.length>=1){
							String[] trackingIdAry = StringUtils.split(trackingIds[0], TRACKING_ID_SEPARATOR());
							if(trackingIdAry!=null && trackingIdAry.length>=2){
								trackingNo = trackingIdAry[1];
							}
						}
//					}
				}else{
					err_flag = true;
					throw new LabelException(errMsg.toString());
				}
			}else{
				for(PackageIdentifier packageIdentifier:responseManifest.getPackages().get(0).getPackageIdentifier()){
					trackingNo = packageIdentifier.getPackageID();
					break;
				}
			}
			
			//2.GetImageLabelsForPackage 
			LabelResult labelResult= port.getImageLabelsForPackage(documentNo, MAILING_AGENT_ID(), 1, LABLE_FORMAT());
			if(labelResult.getResponseCode()!=0){
				err_flag = true;
				throw new LabelException(labelResult.getMessage());
			}
			byte[][] labels = labelResult.getLabel();
			for(byte[] l:labels){
				byte[] data = Base64.encodeBase64(l);
				lableCode  = new String(data, "utf-8");
				break;
			}
		}catch(Exception e){
			if(!err_flag){
				throw new LabelBusinessException(e);
			}else{
				throw new LabelSystemException(e);
			}
		}
		return new Result(lableCode, trackingNo);
	}
	
	
	
	private RequestManifest getRequestManifest( RequestMessage requestMessage){
		Consignee consignee = requestMessage.getBody().getConsignee();
		Logistics logistics = requestMessage.getBody().getLogistics();
		List<Product> products = requestMessage.getBody().getProducts();
		//Map<String,Object> parMap = owmsExWarehouse.checkLogisticsType();
		
		RequestManifest manifest = new RequestManifest();
		RequestManifest.Dispatch dispatch = new RequestManifest.Dispatch();
		dispatch.setShippingAgentID(SHIPPING_AGENT_ID());
		dispatch.setReceivingAgentID(RECEIVING_AGENT_ID());
		dispatch.setDispatchID(STRING_BLANK);
//		dispatch.setDispatchDateTime(null);
		dispatch.setTimeZone(STRING_BLANK);
		dispatch.setFileFormatVersion(MANIFEST_FILE_FORMAT_VERSION);

		RequestManifest.Package pkg = new RequestManifest.Package();
		
		pkg.setOrderID(requestMessage.getBody().getDocumentNo());
		pkg.setItemValueCurrencyType(ITEM_VALUE_CURRENCY_TYPE);
		//sender information
		pkg.setSenderName(SENDER_NAME());
		pkg.setSenderFirstName(SENDER_FIRST_NAME());
		pkg.setSenderMiddleInitial(SENDER_MIDDLE_INITIAL());
		pkg.setSenderLastName(SENDER_LAST_NAME());
		pkg.setSenderBusinessName(SENDER_BUSINESS_NAME());
		pkg.setSenderAddressLine1(SENDER_ADDRESS_LINE1());
		pkg.setSenderAddressLine2(SENDER_ADDRESS_LINE2());
		pkg.setSenderAddressLine3(SENDER_ADDRESS_LINE3());
		pkg.setSenderCity(SENDER_CITY());
		pkg.setSenderProvince(SENDER_PROVINCE());
		pkg.setSenderPostalCode(SENDER_POSTAL_CODE());
		pkg.setSenderCountryCode(SENDER_COUNTRY_CODE());
		pkg.setSenderAddressIsPOBox(SENDER_ADDRESS_IS_PO_BOX());
		pkg.setSenderPhone(SENDER_PHONE());
		pkg.setSenderEmail(SENDER_EMAIL());
		pkg.setSenderTaxpayerID(SENDER_TAXPAYER_ID());
		
		//recipient information
		pkg.setRecipientName(trim(consignee.getName(),35));
		pkg.setRecipientFirstName(STRING_BLANK);
		pkg.setRecipientMiddleInitial(STRING_BLANK);
		pkg.setRecipientLastName(STRING_BLANK);
		pkg.setRecipientBusinessName(trim(consignee.getName(),35));
		pkg.setRecipientAddressLine1(trim(consignee.getAddress1(),35));
		pkg.setRecipientAddressLine2(trim(consignee.getAddress2(),35));
		pkg.setRecipientAddressLine3(trim(consignee.getAddress3(),35));
		pkg.setRecipientCity(trim(consignee.getCity(),35));
		pkg.setRecipientProvince(trim(consignee.getProvince(),35));
		pkg.setRecipientPostalCode(consignee.getPostcode());
		pkg.setRecipientCountryCode("UK".equals(consignee.getCountry())?"GB":consignee.getCountry());
		pkg.setRecipientAddressIsPOBox(RECIPIENT_ADDRESS_IS_PO_BOX);
		pkg.setRecipientPhone(consignee.getPhone());
		pkg.setRecipientEmail(consignee.getEmail());

		pkg.setPackageType(PACKAGE_TYPE());
		pkg.setTaxpayerID(STRING_BLANK);
		pkg.setShippingandHandling(STRING_BLANK);
		pkg.setShippingCurrencyType(SHIPPING_CURRENCY_TYPE);
		pkg.setPackageID(requestMessage.getBody().getDocumentNo());
		
		pkg.setWeightUnit(WEIGHT_UNIT);
		//pkg.setPackageLength(parMap.get("lenght"));
		//pkg.setPackageWidth(parMap.get("width"));
		//pkg.setPackageHeight(parMap.get("height"));

		pkg.setUnitOfMeasurement(UNIT_OF_MEASUREMENT);
		pkg.setPackageShape(STRING_BLANK);
		pkg.setPaymentAndDeliveryTerms(STRING_BLANK);
		pkg.setServiceType(SERVICE_TYPE());
		pkg.setRateType(RATE_TYPE(logistics.getCode()));
		pkg.setPackagePhysicalCount(BigInteger.valueOf(PACKAGE_PHYSICAL_COUNT));
		pkg.setPostagePaid(STRING_BLANK);
		pkg.setPostagePaidCurrencyType(POSTAGE_PAID_CURRENCY_TYPE);
		pkg.setMailingAgentID(MAILING_AGENT_ID());
		pkg.setReturnAgentID(STRING_BLANK);
		pkg.setValueLoaded(VALUE_LOADED);
		pkg.setLicenseNumber(STRING_BLANK);
		pkg.setCertificateNumber(STRING_BLANK);
		pkg.setInvoiceNumber(STRING_BLANK);
		pkg.setPFCorEEL(PF_COR_EEL());
		pkg.setReturnServiceRequested(RETURN_SERVICE_REQUESTED);
		pkg.setAttrPackageID(requestMessage.getBody().getDocumentNo());
		BigDecimal pkgWeight = new BigDecimal(0.0);
		for(Product product:products){
			BigDecimal unitValue = new BigDecimal(0.0);
			
			for(ClassifyProduct classifyProduct : product.getClassifyProducts()) {
				String countryCode = classifyProduct.getCountry();
				if(countryCode.equalsIgnoreCase("US")) {
					unitValue = unitValue.add(classifyProduct.getPriceImports());
					break;
				}
			}
			
			pkgWeight = pkgWeight.add(product.getQty().multiply(product.getWeight()));
			
			RequestManifest.Package.Item item = new RequestManifest.Package.Item();
			item.setItemID(product.getSku());
			item.setItemDescription(trim(product.getEname(),32));
			item.setCommodityName(STRING_BLANK);
			item.setCustomsDescription(STRING_BLANK);
			item.setUnitValue(unitValue);
			item.setCountryOfOrigin(STRING_BLANK);
			item.setQuantity(product.getQty());
			item.setHTSNumber(STRING_BLANK);
			item.setMultiSourceFlag(STRING_BLANK);
			item.setOriginalImportCost(STRING_BLANK);
			item.setImportCostCurrencyType(STRING_BLANK);

			pkg.getItem().add(item);
		}
		pkg.setPackageWeight(pkgWeight);
		manifest.getDispatchOrPackage().add(dispatch);
		manifest.getDispatchOrPackage().add(pkg);
		return manifest;
	}
	

	
	public String trim(String str, int len) {
		if(StringUtils.isEmpty(str)){
			return "";
		}
		
		if(str.length()<=len){
			return str;
		}
		return str.substring(0,len);
	}
	
	private RequestManifest getRequestManifestForDomestic(RequestMessage requestMessage){
		Consignee consignee = requestMessage.getBody().getConsignee();
		List<Product> products = requestMessage.getBody().getProducts();
		
		assertNotNull(requestMessage.getBody().getLength(),"Get package length failure");
		assertNotNull(requestMessage.getBody().getHeight(),"Get package height failure");
		assertNotNull(requestMessage.getBody().getWidth(),"Get package width failure");
		
		
		
		RequestManifest manifest = new RequestManifest();
		RequestManifest.Dispatch dispatch = new RequestManifest.Dispatch();
		dispatch.setShippingAgentID(SHIPPING_AGENT_ID());
		dispatch.setReceivingAgentID(RECEIVING_AGENT_ID());
		dispatch.setDispatchID(STRING_BLANK);
//		dispatch.setDispatchDateTime(null);
		dispatch.setTimeZone(STRING_BLANK);
		dispatch.setFileFormatVersion(MANIFEST_FILE_FORMAT_VERSION);

		RequestManifest.Package pkg = new RequestManifest.Package();
		
		pkg.setOrderID(requestMessage.getBody().getDocumentNo());
		pkg.setItemValueCurrencyType(ITEM_VALUE_CURRENCY_TYPE);
		//sender information
		pkg.setSenderName(ContextUtils.getValue("WT_UPS_NAME"));
		String fromAddress2 = ContextUtils.getValue("WT_UPS_ADDRESSLINE2");
		if(StringUtils.isBlank(fromAddress2)){
			fromAddress2 = ContextUtils.getValue("WT_UPS_ADDRESSLINE1");
		}
		pkg.setSenderAddressLine2(fromAddress2);
		pkg.setSenderCity( ContextUtils.getValue("WT_UPS_CITY"));
		pkg.setSenderProvince(ContextUtils.getValue("WT_UPS_PROVINCECODE"));
		pkg.setSenderPostalCode(ContextUtils.getValue("WT_UPS_POSTALCODE"));
		pkg.setSenderCountryCode(SENDER_COUNTRY_CODE());
		pkg.setSenderAddressIsPOBox(SENDER_ADDRESS_IS_PO_BOX());
		String[] phoneNums = ContextUtils.getValue("WT_UPS_PHONENUMBER").split("-");
		String fromPhone = new StringBuffer(phoneNums[0]).
									append(phoneNums[1]).
									append(phoneNums[2]).toString();
		pkg.setSenderPhone(fromPhone);
		
		//recipient information
		pkg.setRecipientName(trim(consignee.getName(),35));
		pkg.setRecipientFirstName(STRING_BLANK);
		pkg.setRecipientMiddleInitial(STRING_BLANK);
		pkg.setRecipientLastName(STRING_BLANK);
		pkg.setRecipientBusinessName(trim(consignee.getName(),35));
		pkg.setRecipientAddressLine1(trim(consignee.getAddress1(),35));
		pkg.setRecipientAddressLine2(trim(consignee.getAddress2(),35));
		pkg.setRecipientAddressLine3(trim(consignee.getAddress3(),35));
		pkg.setRecipientCity(trim(consignee.getCity(),35));
		pkg.setRecipientProvince(trim(consignee.getProvince(),35));
		pkg.setRecipientPostalCode(consignee.getPostcode());
		pkg.setRecipientCountryCode("UK".equals(consignee.getCountry())?"GB":consignee.getCountry());
		pkg.setRecipientAddressIsPOBox(RECIPIENT_ADDRESS_IS_PO_BOX);
		pkg.setRecipientPhone(consignee.getPhone());
		pkg.setRecipientEmail(consignee.getEmail());

		pkg.setPackageType(PACKAGE_TYPE());
		pkg.setTaxpayerID(STRING_BLANK);
		pkg.setShippingandHandling(STRING_BLANK);
		pkg.setShippingCurrencyType(SHIPPING_CURRENCY_TYPE);
		pkg.setPackageID(requestMessage.getBody().getDocumentNo());
		
		pkg.setWeightUnit(WEIGHT_UNIT);
		pkg.setPackageLength(requestMessage.getBody().getLength());
		pkg.setPackageWidth(requestMessage.getBody().getWidth());
		pkg.setPackageHeight(requestMessage.getBody().getHeight());

		pkg.setUnitOfMeasurement(UNIT_OF_MEASUREMENT);
		pkg.setPackageShape(STRING_BLANK);
		pkg.setPaymentAndDeliveryTerms(STRING_BLANK);
		pkg.setServiceType(SERVICE_TYPE());
		pkg.setRateType(DOMESTIC_RATE_TYPE());
		pkg.setPackagePhysicalCount(BigInteger.valueOf(PACKAGE_PHYSICAL_COUNT));
		pkg.setPostagePaid(STRING_BLANK);
		pkg.setPostagePaidCurrencyType(POSTAGE_PAID_CURRENCY_TYPE);
		pkg.setMailingAgentID(MAILING_AGENT_ID());
		pkg.setReturnAgentID(STRING_BLANK);
		pkg.setValueLoaded(VALUE_LOADED);
		pkg.setLicenseNumber(STRING_BLANK);
		pkg.setCertificateNumber(STRING_BLANK);
		pkg.setInvoiceNumber(STRING_BLANK);
		pkg.setPFCorEEL(PF_COR_EEL());
		pkg.setReturnServiceRequested(RETURN_SERVICE_REQUESTED);
		pkg.setAttrPackageID(requestMessage.getBody().getDocumentNo());
		BigDecimal pkgWeight = new BigDecimal(0.0);
		for(Product product:products){
			List<ClassifyProduct> classifyProducts =  product.getClassifyProducts();
			BigDecimal unitValue = new BigDecimal(0.0);
			for(ClassifyProduct classifyProduct : classifyProducts) {
				String countryCode = classifyProduct.getCountry();
				if(countryCode.equalsIgnoreCase("US")) {
					unitValue = unitValue.add(classifyProduct.getPriceImports());
					break;
				}
			}
			
			pkgWeight = pkgWeight.add(product.getQty().multiply(product.getWeight()));
			
			RequestManifest.Package.Item item = new RequestManifest.Package.Item();
			item.setItemID(product.getSku());
			item.setItemDescription(trim(product.getEname(),32));
			item.setCommodityName(STRING_BLANK);
			item.setCustomsDescription(STRING_BLANK);
			item.setUnitValue(unitValue);
			item.setCountryOfOrigin(STRING_BLANK);
			item.setQuantity(product.getQty());
			item.setHTSNumber(STRING_BLANK);
			item.setMultiSourceFlag(STRING_BLANK);
			item.setOriginalImportCost(STRING_BLANK);
			item.setImportCostCurrencyType(STRING_BLANK);

			pkg.getItem().add(item);
		}
		pkg.setPackageWeight(pkgWeight);
		manifest.getDispatchOrPackage().add(dispatch);
		manifest.getDispatchOrPackage().add(pkg);
		return manifest;
	}
	private <T> T assertNotNull(T obj,String msg){
		if(obj==null){
			throw new LabelException(msg);
		}
		return obj;
	}

	@Override
	public boolean isIdempotent()
	{
		return true;
	}
}
