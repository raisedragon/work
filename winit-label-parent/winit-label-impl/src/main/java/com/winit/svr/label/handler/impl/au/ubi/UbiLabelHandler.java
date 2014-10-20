package com.winit.svr.label.handler.impl.au.ubi;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.winit.label.model.RequestMessage;
import com.winit.label.model.RequestMessage.ClassifyProduct;
import com.winit.label.model.RequestMessage.Consignee;
import com.winit.label.model.RequestMessage.Product;
import com.winit.svr.LabelException;
import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.persistence.entity.LogisticTypeEntity;
import com.winit.svr.label.LabelHandler;
import com.winit.svr.label.context.ContextUtils;

/**
 * 抓取UBI的面单，返回面单的Base64位编码
 * @author temuser2
 *
 */
@SuppressWarnings("rawtypes")
public class UbiLabelHandler  implements LabelHandler
{
	/** Winit 澳洲配置信息 */
	private static final String	BUSINESS_NAME	= "WT_AU_BUSINESSNAME";
	private static final String	CONTACT_NAME	= "WT_AU_CONTACKNAME";
	private static final String	TELEPHONE		= "WT_AU_TELEPHONE";
	private static final String	FULL_ADDRESS	= "WT_AU_FULLADDRESS";

	/** eParcel JSON Key */
	public static final String	ORDERS_ID		= "ordersId";
	public static final String	SOLD_ITEM_ID	= "soldItemId";
	public static final String	TRACKING_NUMBER	= "trackIngNumber";
	public static final String	LABEL_URL		= "URL";

	/** SmartParcel JSON Key */
	public static final String	ORDER_ID		= "orderId";

	/** eParcel 默认值 */
	private static int			shipmentId		= 138;
	private static int			countryId		= 934;
	private static String		printType		= "ubiAuPrintModel15";
	private static String		printStyle		= "@page{ size: 400px 590px; margin: 10px; }";
	private static String		fontPath		= "Zurich.ttf";

	/** eParcel ServiceCode */
	public static final String	EPARCEL			= "eParcel";
	
	private String errorMsg = "No message body writer has been found for response class ApplicationExceptionEntity.";

	@Override
	public Result handle(CommandContext commandContext, RequestMessage requestMessage)
	{
//		try {
			try
			{
				return getSmartParcelLabel( requestMessage);
			}
			catch (Exception e)
			{
				throw new LabelException(e);
			}
//		} catch (LabelException e) {
//			if(e.getMessage().contains("errorCode")) { // 业务异常，不需要重试
//				throw e;
//			} else {
//				throw new Exception("error"); // 系统异常，需要重试
//				
//			}
//		} catch (Exception e) {
//			throw new Exception("error"); // 系统异常，需要重试
//		}
	}
	
	
	
	
	
	
	/**
	 * Winit澳洲联系方式
	 * @return
	 */
	public Map<String, String> getWinitAU()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put(BUSINESS_NAME, ContextUtils.getValue(BUSINESS_NAME)); // WINIT AU
		map.put(CONTACT_NAME, ContextUtils.getValue(CONTACT_NAME)); // Winit
		map.put(TELEPHONE, ContextUtils.getValue(TELEPHONE)); // 12345678
		map.put(FULL_ADDRESS, ContextUtils.getValue(FULL_ADDRESS)); // PO Box 168, Regents Park NSW 2143
		return map;
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
	public Result getSmartParcelLabel(RequestMessage requestMessage) throws Exception {
		Map<String, String> winitAU = getWinitAU();
		Map<String, Object> orderMap = getSmartParcelOrder(requestMessage, winitAU);
		String orderJson = null;
		InputStream in = null;
		String response = null;
		
		try {
			// 1. 新增订单
			orderJson = UbiJsonUtil.getJsonArray(orderMap);
			in = UbiSoapUtil.postToSmartParcel(UbiSoapUtil.UBI_SMARTPARCEL_ORDER_PATH(), orderJson);
			response = new String(UbiSoapUtil.readInputStream(in), "utf-8");
			UbiJsonUtil.parseSmartParcelOrderResult(response, UbiLabelHandler.ORDER_ID);
		} catch (Exception e) {
			// 对于调用过“新增订单”的出库单，再次抓取面单时，忽略掉110003【Order Reference Number already exists】这个异常
			if(! e.getMessage().contains("110003")) {
				throw new LabelException(e);
			}
		}
		
		// 2. 获取面单
		String labelJson = UbiJsonUtil.getJsonArray(requestMessage.getBody().getDocumentNo());
		in = UbiSoapUtil.postToSmartParcel(UbiSoapUtil.UBI_SMARTPARCEL_LABELS_PATH(), labelJson);
		byte[] data = Base64.encodeBase64(UbiSoapUtil.readInputStream(in));
        String base64 = new String(data, "utf-8");
        
        // 如果没有成功调用“新增订单”接口，在调用“获取面单”时会得到这个异常信息
        String msg = new String(Base64.decodeBase64(base64.getBytes("utf-8")));
        if(msg.contains(errorMsg)) {
        	throw new LabelException(msg);
        }
        
        // 3. 获取快递单号
		String trackingNumberJson = UbiJsonUtil.getJsonArray(requestMessage.getBody().getDocumentNo());
		in = UbiSoapUtil.postToSmartParcel(UbiSoapUtil.UBI_SMARTPARCEL_TRACKING_NUMBER_PATH(), trackingNumberJson);
		response = new String(UbiSoapUtil.readInputStream(in), "utf-8");
		String trackingNo = UbiJsonUtil.parseSmartParcelTrackingNumberResult(response);
		
        
		// 保存快递单号 (Non-Tracking的不保存快递单号)
		//TODO
//        String SERVICECODE = (STRING) MAP.GET("SERVICECODE");
//        IF(! SERVICECODE.CONTAINS("NON-TRACKING")) {
//			MOWMSEXWAREHOUSE MEXWAREHOUSE = MOWMSEXWAREHOUSE.GETBYDOCUMENTNO(CTX, DOCUMENTNO, TRXNAME);
//			MEXWAREHOUSE.SETTRACKINGNO(TRACKINGNO);
//			MEXWAREHOUSE.SAVEEX();
//        }
		return new Result(base64, trackingNo);
//		return base64;
	}
	
	public Map<String, Object> getSmartParcelOrder(RequestMessage requestMessage, Map<String, String> winitAU) {
//		MOWMSExWarehouse mExWarehouse = MOWMSExWarehouse.getByDocumentNo(ctx, documentNo, trxName);
//		List<MOWMSExWareHouseProduct> prods = mExWarehouse.getChildren(MOWMSExWareHouseProduct.Table_Name, "", "");
		List<Product> products = requestMessage.getBody().getProducts();
		Consignee consignee = requestMessage.getBody().getConsignee();
		String description="";
		double invoiceValue = 0;
		DecimalFormat df = new DecimalFormat("0.00");
		for (Product product : products)
		{
			// 产品描述不超过50个字符
			String temp = description + " " + product.getEname();
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
				String countryCode = classifyProduct.getCountry();
				if(countryCode.equals("AU")) {
					invoiceValue = invoiceValue + classifyProduct.getPriceImports().doubleValue() * product.getQty().intValue();
					break;
				}
			}
		}
		
//		Map<String, Object> size = mExWarehouse.checkLogisticsType();
		if(requestMessage.getBody().getLength()==null||
				requestMessage.getBody().getWidth()==null||
				 requestMessage.getBody().getHeight()==null
				) {
			throw new LabelException("Product volume is null");
		}
		double length = requestMessage.getBody().getLength().doubleValue();
		double width = requestMessage.getBody().getWidth().doubleValue();
		double height = requestMessage.getBody().getHeight().doubleValue();
		double volume = (length / 100) * (width / 100) * (height / 100);
		String volumeStr = df.format(volume);
		String invoiceValueStr = df.format(invoiceValue);
		
		
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
		}
		
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("referenceNo", requestMessage.getBody().getDocumentNo());
		orderMap.put("recipientName", consignee.getName());
		orderMap.put("addressline1", consignee.getAddress1());
		if(consignee.getAddress2() != null) {
			orderMap.put("addressline2",consignee.getAddress2());
		}
		if(consignee.getAddress3() != null) {
			orderMap.put("addressline3", consignee.getAddress3() );
		}
		orderMap.put("city",consignee.getCity());
		orderMap.put("state", consignee.getProvince());
		orderMap.put("postcode", consignee.getPostcode());
		orderMap.put("country", consignee.getCountry());
		//TODO
		orderMap.put("weight", pkgWeight.doubleValue());
		orderMap.put("volume", Double.valueOf(volumeStr));
		orderMap.put("invoiceValue", Double.valueOf(invoiceValueStr)); // 价值
		orderMap.put("invoiceCurrency", "AUD"); // 货币
		orderMap.put("description", description);
		orderMap.put("phone", consignee.getPhone() == null ? "" :consignee.getPhone()); 
		orderMap.put("recipientCompany", ""); 
		
		LogisticTypeEntity logisticType = Context.getCommandContext().getLogisticTypeManager().findByLogisticTypeCode(requestMessage.getBody().getLogistics().getCode());
		
		
		String serviceCode = (String) logisticType.getVariable("serviceCode");
		
//		String serviceCode = (String) map.get("serviceCode");
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
