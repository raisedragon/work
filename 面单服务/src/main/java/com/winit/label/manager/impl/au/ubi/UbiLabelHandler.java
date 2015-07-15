package com.winit.label.manager.impl.au.ubi;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winit.exception.LabelBusinessException;
import com.winit.exception.LabelException;
import com.winit.exception.LabelSystemException;
import com.winit.label.manager.LabelHandler;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;
import com.winit.label.model.RequestMessage.ClassifyProduct;
import com.winit.label.model.RequestMessage.Consignee;
import com.winit.label.model.RequestMessage.Product;
import com.winit.label.service.DeliveryWayService;

/**
 * 抓取UBI面单
 * @author kaizhou.chen
 *
 */
@Service
public class UbiLabelHandler  implements LabelHandler
{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String	orderId	= "orderId";

	private String errorMsg = "No message body writer has been found for response class ApplicationExceptionEntity.";
	
	
	@Autowired
	protected DeliveryWayService deliveryWayService;

	@Override
	public Result handle(RequestMessage requestMessage, DeliveryWay deliveryWay)
	{
		try
		{
			return getLabel(requestMessage, deliveryWay);
		}
		catch (Exception e)
		{
			throw new LabelSystemException(e);
		}
	}
	
	/**
	 * SmartParcel Label
	 * @param map
	 * @param ctx
	 * @param documentNo
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public Result getLabel(RequestMessage requestMessage,DeliveryWay deliveryWay) throws Exception {
		Map<String, Object> orderMap = getSmartParcelOrder(requestMessage, deliveryWay);
		String orderJson = null;
		InputStream in = null;
		String response = null;
		
		try {
			// 1. 新增订单
			orderJson = UbiJsonUtil.getJsonArray(orderMap);
			logger.info("REQUEST: "+ orderJson);
			in = UbiSoapUtil.postToSmartParcel(UbiConfig.UBI_SMARTPARCEL_ORDER_PATH(), orderJson);
			response = new String(UbiSoapUtil.readInputStream(in), "utf-8");
			logger.info("RESPONSE: "+ response);
			UbiJsonUtil.parseOrderResult(response, orderId);
		} catch (Exception e) {
			// 对于调用过“新增订单”的出库单，再次抓取面单时，忽略掉110003【Order Reference Number already exists】这个异常
			if( e.getMessage()!=null && ! e.getMessage().contains("110003")) {
				throw new LabelBusinessException(e);
			}
		}
		
		// 2. 获取面单
		String labelJson = UbiJsonUtil.getJsonArray(requestMessage.getDocumentNo());
		in = UbiSoapUtil.postToSmartParcel(UbiConfig.UBI_SMARTPARCEL_LABELS_PATH(), labelJson);
		byte[] data = Base64.encodeBase64(UbiSoapUtil.readInputStream(in));
        String base64 = new String(data, "utf-8");
        
        // 如果没有成功调用“新增订单”接口，在调用“获取面单”时会得到这个异常信息
        String msg = new String(Base64.decodeBase64(base64.getBytes("utf-8")));
        if(msg.contains(errorMsg)) {
        	throw new LabelBusinessException(msg);
        }
        
        // 3. 获取快递单号
		String trackingNumberJson = UbiJsonUtil.getJsonArray(requestMessage.getDocumentNo());
		in = UbiSoapUtil.postToSmartParcel(UbiConfig.UBI_SMARTPARCEL_TRACKING_NUMBER_PATH(), trackingNumberJson);
		response = new String(UbiSoapUtil.readInputStream(in), "utf-8");
		String trackingNo = UbiJsonUtil.parseTrackingNumberResult(response);
		
		// Non-Tracking的没有快递单号
		if(deliveryWay.getServiceCode().contains("Non-Tracking")) {
			trackingNo = null;
		}

		return new Result(base64, trackingNo);
	}
	
	public Map<String, Object> getSmartParcelOrder(RequestMessage requestMessage, DeliveryWay deliveryWay) {
		List<Product> products = requestMessage.getProducts();
		Consignee consignee = requestMessage.getConsignee();
		String description="";
		double invoiceValue = 0;
		DecimalFormat df = new DecimalFormat("0.00");
		for (Product product : products)
		{
			// 产品描述不超过50个字符
			String temp = description + " " + product.getName();
			if(temp.length() <= 50) {
				description = temp;
			} else {
				// 超过50个字符，则截取字符串
				description = temp.substring(0, 50);
			}
			
			// (产品的澳洲进口申报价值 * 产品数量) = invoiceValue
			// 如果查不到澳洲进口申报价值，则invoiceValue为0
			List<ClassifyProduct> classifyProducts =product.getClassifyProducts();
			for(ClassifyProduct classifyProduct : classifyProducts) {
				String countryCode = classifyProduct.getCountryCode();
				if(countryCode.equals("AU")) {
					invoiceValue = invoiceValue + classifyProduct.getPriceImports() * product.getQty();
					break;
				}
			}
		}
		
		if(requestMessage.getLength() == 0 ||
				requestMessage.getWidth() == 0 ||
				requestMessage.getHeight() == 0) {
			throw new LabelBusinessException("Package volume over limit.");
		}
		double length = requestMessage.getLength();
		double width = requestMessage.getWidth();
		double height = requestMessage.getHeight();
		double volume = (length / 100) * (width / 100) * (height / 100);
		String volumeStr = df.format(volume);
		String invoiceValueStr = df.format(invoiceValue);
		
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("referenceNo", requestMessage.getDocumentNo());
		orderMap.put("recipientName", consignee.getName());
		orderMap.put("addressline1", consignee.getAddress1());
		if(consignee.getAddress2() != null) {
			orderMap.put("addressline2",consignee.getAddress2());
		}
		if(consignee.getAddress3() != null) {
			orderMap.put("addressline3", consignee.getAddress3() );
		}
		orderMap.put("city",consignee.getCity());
		orderMap.put("state", consignee.getState());
		orderMap.put("postcode", consignee.getPostcode());
		orderMap.put("country", consignee.getCountryCode());
		orderMap.put("weight", requestMessage.getWeight()); // 重量：KG
		orderMap.put("volume", Double.valueOf(volumeStr));
		orderMap.put("invoiceValue", Double.valueOf(invoiceValueStr)); // 价值
		orderMap.put("invoiceCurrency", "AUD"); // 货币
		orderMap.put("description", description);
		orderMap.put("phone", consignee.getPhone() == null ? "" :consignee.getPhone()); 
		orderMap.put("recipientCompany", ""); 
		
		String serviceCode = deliveryWay.getServiceCode();
		
		if(serviceCode.contains("Non-Tracking")) {
			serviceCode = "Non-Tracking";
		}
		orderMap.put("serviceOption", serviceCode); 
		
		return orderMap;
	}


	@Override
	public boolean isIdempotent()
	{
		return true;
	}

}
