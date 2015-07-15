package com.winit.label.manager.impl.us.usps.intl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.winit.exception.LabelBusinessException;
import com.winit.exception.LabelException;
import com.winit.exception.LabelSystemException;
import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.impl.us.usps.intl.UspsIntlProcessPackageSoapWrapper.LoadAndRecordLabeledPackageResultWrapper;
import com.winit.label.manager.impl.us.usps.intl.model.ArrayOfString;
import com.winit.label.manager.impl.us.usps.intl.model.LabelResult;
import com.winit.label.manager.impl.us.usps.intl.model.LoadAndRecordLabeledPackage;
import com.winit.label.manager.impl.us.usps.intl.model.TrackResult;
import com.winit.label.manager.impl.us.usps.intl.model.ext.request.RequestManifest;
import com.winit.label.manager.impl.us.usps.intl.model.ext.response.DispatchConfirmation;
import com.winit.label.manager.impl.us.usps.intl.model.ext.response.PackageError;
import com.winit.label.manager.impl.us.usps.intl.model.ext.response.PackageIdentifier;
import com.winit.label.manager.impl.us.usps.intl.model.ext.response.ResponseManifest;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;
import com.winit.label.model.RequestMessage.ClassifyProduct;
import com.winit.label.model.RequestMessage.Consignee;
import com.winit.label.model.RequestMessage.Product;
import com.winit.label.support.ConfigUtil;

@Component
public class UspsIntlLabelHandler implements LabelHandler
{

	Logger logger = LoggerFactory.getLogger(UspsIntlLabelHandler.class);
	

	private static final String	STRING_BLANK				= "";
	

	public Result handle( RequestMessage requestMessage,DeliveryWay deliveryWay)
	{
		return this.handle( requestMessage,deliveryWay,true);
	}
	
	public Result handle( RequestMessage requestMessage,DeliveryWay deliveryWay,boolean isIntl)
	{
		String documentNo = requestMessage.getDocumentNo();
		
		String lableCode = null;
		String trackingNo = null;
		boolean err_flag = false;
		try{
			
			
			RequestManifest manifest = null;
			if(isIntl){
				manifest = getRequestManifest( requestMessage,deliveryWay);
			}else{
				manifest = getRequestManifestForDomestic(requestMessage);
			}
			UspsIntlProcessPackageSoapWrapper port = UspsIntlProcessPackageSoapWrapper.getInstance();
			
			LoadAndRecordLabeledPackage.XmlDoc xmlDoc = new LoadAndRecordLabeledPackage.XmlDoc();
			
			xmlDoc.getContent().add(manifest);
			
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
				
				if(String.format(UspsIntlConfig.ALREADY_LOAD_ERR_MSG(), documentNo,documentNo, documentNo,documentNo,documentNo).equalsIgnoreCase(errMsg.toString().trim())){
					//TODO
//					if(StringUtils.isEmpty(exWarehouse.getTrackingNo())){
						TrackResult trackResult = port.trackPackage(documentNo, UspsIntlConfig.MAILING_AGENT_ID(), 1);
						if(0!=trackResult.getResponseCode()){
							err_flag = true;
							throw new LabelException(trackResult.getMessage());
						}
						ArrayOfString trackingIds = trackResult.getTrackingId();
						if(trackingIds!=null && trackingIds.getString()!=null && trackingIds.getString().size()>=1){
							String[] trackingIdAry = StringUtils.split(trackingIds.getString().get(0), UspsIntlConfig.TRACKING_ID_SEPARATOR());
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
			LabelResult labelResult= port.getImageLabelsForPackage(documentNo, UspsIntlConfig.MAILING_AGENT_ID(), 1, UspsIntlConfig.LABLE_FORMAT());
			if(labelResult.getResponseCode()!=0){
				err_flag = true;
				throw new LabelException(labelResult.getMessage());
			}
			List<byte[]> labels = labelResult.getLabel().getBase64Binary();
			for(byte[] l:labels){
				byte[] data = Base64.encodeBase64(l);
				lableCode  = new String(data, "utf-8");
				break;
			}
		}catch(Exception e){
			logger.error(ExceptionUtils.getFullStackTrace(e));
			if(!err_flag){
				throw new LabelBusinessException(e);
			}else{
				throw new LabelSystemException(e);
			}
		}
		return new Result(lableCode, trackingNo);
	}
	
	
	
	private RequestManifest getRequestManifest(RequestMessage requestMessage,DeliveryWay deliveryWay){
		Consignee consignee = requestMessage.getConsignee();
		List<Product> products = requestMessage.getProducts();
		//Map<String,Object> parMap = owmsExWarehouse.checkLogisticsType();
		
		RequestManifest manifest = new RequestManifest();
		RequestManifest.Dispatch dispatch = new RequestManifest.Dispatch();
		dispatch.setShippingAgentID(UspsIntlConfig.SHIPPING_AGENT_ID());
		dispatch.setReceivingAgentID(UspsIntlConfig.RECEIVING_AGENT_ID());
		dispatch.setDispatchID(STRING_BLANK);
//		dispatch.setDispatchDateTime(null);
		dispatch.setTimeZone(STRING_BLANK);
		dispatch.setFileFormatVersion(UspsIntlConfig.MANIFEST_FILE_FORMAT_VERSION);

		RequestManifest.Package pkg = new RequestManifest.Package();
		
		pkg.setOrderID(requestMessage.getDocumentNo());
		pkg.setItemValueCurrencyType(UspsIntlConfig.ITEM_VALUE_CURRENCY_TYPE);
		//sender information
		pkg.setSenderName(UspsIntlConfig.SENDER_NAME());
		pkg.setSenderFirstName(UspsIntlConfig.SENDER_FIRST_NAME());
		pkg.setSenderMiddleInitial(UspsIntlConfig.SENDER_MIDDLE_INITIAL());
		pkg.setSenderLastName(UspsIntlConfig.SENDER_LAST_NAME());
		pkg.setSenderBusinessName(UspsIntlConfig.SENDER_BUSINESS_NAME());
		pkg.setSenderAddressLine1(UspsIntlConfig.SENDER_ADDRESS_LINE1());
		pkg.setSenderAddressLine2(UspsIntlConfig.SENDER_ADDRESS_LINE2());
		pkg.setSenderAddressLine3(UspsIntlConfig.SENDER_ADDRESS_LINE3());
		pkg.setSenderCity(UspsIntlConfig.SENDER_CITY());
		pkg.setSenderProvince(UspsIntlConfig.SENDER_PROVINCE());
		pkg.setSenderPostalCode(UspsIntlConfig.SENDER_POSTAL_CODE());
		pkg.setSenderCountryCode(UspsIntlConfig.SENDER_COUNTRY_CODE());
		pkg.setSenderAddressIsPOBox(UspsIntlConfig.SENDER_ADDRESS_IS_PO_BOX());
		pkg.setSenderPhone(UspsIntlConfig.SENDER_PHONE());
		pkg.setSenderEmail(UspsIntlConfig.SENDER_EMAIL());
		pkg.setSenderTaxpayerID(UspsIntlConfig.SENDER_TAXPAYER_ID());
		
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
		pkg.setRecipientProvince(trim(consignee.getState(),35));
		pkg.setRecipientPostalCode(consignee.getPostcode());
		pkg.setRecipientCountryCode("UK".equals(consignee.getCountryCode())?"GB":consignee.getCountryCode());
		pkg.setRecipientAddressIsPOBox(UspsIntlConfig.RECIPIENT_ADDRESS_IS_PO_BOX);
		pkg.setRecipientPhone(consignee.getPhone());
		pkg.setRecipientEmail(consignee.getEmail());

		pkg.setPackageType(UspsIntlConfig.PACKAGE_TYPE());
		pkg.setTaxpayerID(STRING_BLANK);
		pkg.setShippingandHandling(STRING_BLANK);
		pkg.setShippingCurrencyType(UspsIntlConfig.SHIPPING_CURRENCY_TYPE);
		pkg.setPackageID(requestMessage.getDocumentNo());
		
		pkg.setWeightUnit(UspsIntlConfig.WEIGHT_UNIT);
		//pkg.setPackageLength(parMap.get("lenght"));
		//pkg.setPackageWidth(parMap.get("width"));
		//pkg.setPackageHeight(parMap.get("height"));

		pkg.setUnitOfMeasurement(UspsIntlConfig.UNIT_OF_MEASUREMENT);
		pkg.setPackageShape(STRING_BLANK);
		pkg.setPaymentAndDeliveryTerms(STRING_BLANK);
		pkg.setServiceType(UspsIntlConfig.SERVICE_TYPE());
		pkg.setRateType(deliveryWay.getServiceCode());
		pkg.setPackagePhysicalCount(BigInteger.valueOf(UspsIntlConfig.PACKAGE_PHYSICAL_COUNT));
		pkg.setPostagePaid(STRING_BLANK);
		pkg.setPostagePaidCurrencyType(UspsIntlConfig.POSTAGE_PAID_CURRENCY_TYPE);
		pkg.setMailingAgentID(UspsIntlConfig.MAILING_AGENT_ID());
		pkg.setReturnAgentID(STRING_BLANK);
		pkg.setValueLoaded(UspsIntlConfig.VALUE_LOADED);
		pkg.setLicenseNumber(STRING_BLANK);
		pkg.setCertificateNumber(STRING_BLANK);
		pkg.setInvoiceNumber(STRING_BLANK);
		pkg.setPFCorEEL(UspsIntlConfig.PF_COR_EEL());
		pkg.setReturnServiceRequested(UspsIntlConfig.RETURN_SERVICE_REQUESTED);
		pkg.setAttrPackageID(requestMessage.getDocumentNo());
		double pkgWeight = 0;
		for(Product product:products){
			double unitValue = 0;
			
			for(ClassifyProduct classifyProduct : product.getClassifyProducts()) {
				String countryCode = classifyProduct.getCountryCode();
				if(countryCode.equalsIgnoreCase("US")) {
					unitValue = unitValue + classifyProduct.getPriceImports();
					break;
				}
			}
			
			pkgWeight = pkgWeight + product.getQty() * product.getWeight();
			
			RequestManifest.Package.Item item = new RequestManifest.Package.Item();
			item.setItemID(product.getSku());
			item.setItemDescription(trim(product.getName(),32));
			item.setCommodityName(STRING_BLANK);
			item.setCustomsDescription(STRING_BLANK);
			item.setUnitValue(new BigDecimal(unitValue));
			item.setCountryOfOrigin(STRING_BLANK);
			item.setQuantity(new BigDecimal(product.getQty()));
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
		Consignee consignee = requestMessage.getConsignee();
		List<Product> products = requestMessage.getProducts();
		
		assertNotNull(requestMessage.getLength(),"Get package length failure");
		assertNotNull(requestMessage.getHeight(),"Get package height failure");
		assertNotNull(requestMessage.getWidth(),"Get package width failure");
		
		
		
		RequestManifest manifest = new RequestManifest();
		RequestManifest.Dispatch dispatch = new RequestManifest.Dispatch();
		dispatch.setShippingAgentID(UspsIntlConfig.SHIPPING_AGENT_ID());
		dispatch.setReceivingAgentID(UspsIntlConfig.RECEIVING_AGENT_ID());
		dispatch.setDispatchID(STRING_BLANK);
//		dispatch.setDispatchDateTime(null);
		dispatch.setTimeZone(STRING_BLANK);
		dispatch.setFileFormatVersion(UspsIntlConfig.MANIFEST_FILE_FORMAT_VERSION);

		RequestManifest.Package pkg = new RequestManifest.Package();
		
		pkg.setOrderID(requestMessage.getDocumentNo());
		pkg.setItemValueCurrencyType(UspsIntlConfig.ITEM_VALUE_CURRENCY_TYPE);
		//sender information
		pkg.setSenderName(ConfigUtil.getValue("WT_UPS_NAME"));
		String fromAddress2 = ConfigUtil.getValue("WT_UPS_ADDRESSLINE2");
		if(StringUtils.isBlank(fromAddress2)){
			fromAddress2 = ConfigUtil.getValue("WT_UPS_ADDRESSLINE1");
		}
		pkg.setSenderAddressLine2(fromAddress2);
		pkg.setSenderCity( ConfigUtil.getValue("WT_UPS_CITY"));
		pkg.setSenderProvince(ConfigUtil.getValue("WT_UPS_PROVINCECODE"));
		pkg.setSenderPostalCode(ConfigUtil.getValue("WT_UPS_POSTALCODE"));
		pkg.setSenderCountryCode(UspsIntlConfig.SENDER_COUNTRY_CODE());
		pkg.setSenderAddressIsPOBox(UspsIntlConfig.SENDER_ADDRESS_IS_PO_BOX());
		String[] phoneNums = ConfigUtil.getValue("WT_UPS_PHONENUMBER").split("-");
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
		pkg.setRecipientProvince(trim(consignee.getState(),35));
		pkg.setRecipientPostalCode(consignee.getPostcode());
		pkg.setRecipientCountryCode("UK".equals(consignee.getCountryCode())?"GB":consignee.getCountryCode());
		pkg.setRecipientAddressIsPOBox(UspsIntlConfig.RECIPIENT_ADDRESS_IS_PO_BOX);
		pkg.setRecipientPhone(consignee.getPhone());
		pkg.setRecipientEmail(consignee.getEmail());

		pkg.setPackageType(UspsIntlConfig.PACKAGE_TYPE());
		pkg.setTaxpayerID(STRING_BLANK);
		pkg.setShippingandHandling(STRING_BLANK);
		pkg.setShippingCurrencyType(UspsIntlConfig.SHIPPING_CURRENCY_TYPE);
		pkg.setPackageID(requestMessage.getDocumentNo());
		
		pkg.setWeightUnit(UspsIntlConfig.WEIGHT_UNIT);
		pkg.setPackageLength(requestMessage.getLength());
		pkg.setPackageWidth(requestMessage.getWidth());
		pkg.setPackageHeight(requestMessage.getHeight());

		pkg.setUnitOfMeasurement(UspsIntlConfig.UNIT_OF_MEASUREMENT);
		pkg.setPackageShape(STRING_BLANK);
		pkg.setPaymentAndDeliveryTerms(STRING_BLANK);
		pkg.setServiceType(UspsIntlConfig.SERVICE_TYPE());
		pkg.setRateType(UspsIntlConfig.DOMESTIC_RATE_TYPE());
		pkg.setPackagePhysicalCount(BigInteger.valueOf(UspsIntlConfig.PACKAGE_PHYSICAL_COUNT));
		pkg.setPostagePaid(STRING_BLANK);
		pkg.setPostagePaidCurrencyType(UspsIntlConfig.POSTAGE_PAID_CURRENCY_TYPE);
		pkg.setMailingAgentID(UspsIntlConfig.MAILING_AGENT_ID());
		pkg.setReturnAgentID(STRING_BLANK);
		pkg.setValueLoaded(UspsIntlConfig.VALUE_LOADED);
		pkg.setLicenseNumber(STRING_BLANK);
		pkg.setCertificateNumber(STRING_BLANK);
		pkg.setInvoiceNumber(STRING_BLANK);
		pkg.setPFCorEEL(UspsIntlConfig.PF_COR_EEL());
		pkg.setReturnServiceRequested(UspsIntlConfig.RETURN_SERVICE_REQUESTED);
		pkg.setAttrPackageID(requestMessage.getDocumentNo());
		double pkgWeight = 0;
		for(Product product:products){
			List<ClassifyProduct> classifyProducts =  product.getClassifyProducts();
			double unitValue = 0;
			for(ClassifyProduct classifyProduct : classifyProducts) {
				String countryCode = classifyProduct.getCountryCode();
				if(countryCode.equalsIgnoreCase("US")) {
					unitValue = unitValue + classifyProduct.getPriceImports();
					break;
				}
			}
			
			pkgWeight = pkgWeight + product.getQty() * product.getWeight();
			
			RequestManifest.Package.Item item = new RequestManifest.Package.Item();
			item.setItemID(product.getSku());
			item.setItemDescription(trim(product.getName(),32));
			item.setCommodityName(STRING_BLANK);
			item.setCustomsDescription(STRING_BLANK);
			item.setUnitValue(new BigDecimal(unitValue));
			item.setCountryOfOrigin(STRING_BLANK);
			item.setQuantity(new BigDecimal(product.getQty()));
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
		return false;
	}

}
