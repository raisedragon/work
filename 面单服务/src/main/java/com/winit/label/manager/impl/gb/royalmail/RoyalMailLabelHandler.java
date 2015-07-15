package com.winit.label.manager.impl.gb.royalmail;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winit.commons.jasperreports.JasperReportUtils;
import com.winit.exception.LabelBusinessException;
import com.winit.label.db.SequenceGenerator;
import com.winit.label.manager.LabelHandler;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;

/**
 * 生成RoyalMail的面单，返回面单的Base64位编码
 * @author temuser2
 *
 */
@Component
public class RoyalMailLabelHandler implements LabelHandler
{


	private static List<RoyalMailBarcodeConfig>	royalMailBarcodeConfigs			= new ArrayList<RoyalMailBarcodeConfig>();

	@Autowired
	private SequenceGenerator sequenceGenerator;
	
	@Autowired
	private RoyalMailSoftCodeService royalMailSoftCodeService;
	
	static
	{
		/** RoyalMail Tracked 24 w/Signature */
		royalMailBarcodeConfigs.add(new RoyalMailBarcodeConfig("TPN01-Y", "WT_RoyalMail24Sign_seq",
				RoyalMailConfig.PRODUCT_PREFIX_24_SIGN()));

		/** RoyalMail Tracked 24 non-Signature */
		royalMailBarcodeConfigs.add(new RoyalMailBarcodeConfig("TPN01-N", "WT_RoyalMail24NoSign_seq", 
				RoyalMailConfig.PRODUCT_PREFIX_24_NO_SIGN()));

		/** RoyalMail Tracked 48 (Hi-vol.) w/Signature */
		royalMailBarcodeConfigs.add(new RoyalMailBarcodeConfig("TPL01-Y", "WT_RoyalMail48Sign_seq", 
				RoyalMailConfig.PRODUCT_PREFIX_48_SIGN()));

		/** RoyalMail Tracked 48 (Hi-vol.) non-Signature */
		royalMailBarcodeConfigs.add(new RoyalMailBarcodeConfig("TPL01-N", "WT_RoyalMail48NoSign_seq", 
				RoyalMailConfig.PRODUCT_PREFIX_48_NO_SIGN()));
	}
	
	@Override
	public Result handle(RequestMessage requestMessage, DeliveryWay deliveryWay) throws Exception
	{
		
		String serviceCode = deliveryWay.getServiceCode();
		
		if(serviceCode.equals(RoyalMailConfig.ROYALMAIL_48H_NONTRACKED)) {
			return getNonTrackedLabel(requestMessage,deliveryWay);
		} else {
			return getTrackedLabel(requestMessage, deliveryWay);
		}
	}
	
	public Result getNonTrackedLabel(RequestMessage requestMessage,DeliveryWay deliveryWay) throws Exception  {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("documentNo", requestMessage.getDocumentNo());
		
		
		param.put("NAME",ensureNotNull(requestMessage.getConsignee().getName()));
		param.put("ADDRESS1", ensureNotNull(requestMessage.getConsignee().getAddress1()));
		param.put("ADDRESS2", ensureNotNull(requestMessage.getConsignee().getAddress2()));
		param.put("ADDRESS3", ensureNotNull(requestMessage.getConsignee().getAddress3()));
		param.put("CITY", ensureNotNull(requestMessage.getConsignee().getCity()));
		param.put("POSTAL", ensureNotNull(requestMessage.getConsignee().getPostcode()));
		param.put("COUNTRYNAME", ensureNotNull(requestMessage.getConsignee().getCountryCode()));
		param.put("DOCUMENTNO", ensureNotNull(requestMessage.getDocumentNo()));
		param.put("RETURNADDRESS", RoyalMailConfig.RETURN_ADDRESS_ROYALMAIL_NONTRACKED_POBOX());
		
		param.put("logisticsCode", deliveryWay.getCode());
		
		
		String reportFile = RoyalMailConfig.ROYALMAIL_48H_NONTRACKED_REPORT;
		//如果重量小于100使用STL的面单
		if((new BigDecimal(requestMessage.getWeight()*1000)).compareTo(new BigDecimal(100))<1){
			reportFile = RoyalMailConfig.ROYALMAIL_NONTRACKED_STL_SIGNATURE_REPORT;
			param.put("RETURNADDRESS", RoyalMailConfig.RETURN_ADDRESS_UK_FULLADDRESS());
		}
//		Class clazz = Class.forName("org.compiere.report.ReportStarter");
//		Object obj = clazz.newInstance();
//		Method method = obj.getClass().getDeclaredMethod("generatePdfAsBase64", String.class, Map.class);
//		return (String) method.invoke(obj, reportFile, param);
//		
		String code = JasperReportUtils.generatePdfAsBase64(reportFile, param);
		return new Result(code, null);
	}
	
	public Result getTrackedLabel(RequestMessage requestMessage, DeliveryWay deliveryWay) throws Exception {
		RoyalMailBarcodeConfig config = null;
		for(RoyalMailBarcodeConfig rmbc : royalMailBarcodeConfigs) {
			if(rmbc.getServiceCode().equals(deliveryWay.getServiceCode())) {
				config = rmbc;
				break;
			}
		}
		
		String postal = requestMessage.getConsignee().getPostcode();
		String sortCode = royalMailSoftCodeService.getSoftCode(postal);
		boolean isTracked48 = deliveryWay.getServiceCode().split("-")[0].equals(RoyalMailConfig.ROYALMAIL_TRACKED_48H);
//		sortCode="最后一公里";
		if(isTracked48 && sortCode == null) {
			throw new LabelBusinessException(MessageFormat.format("Postal {0} is invalid.", postal));
		}
		// 1，生成物流单号
		String trackingNo = getTrackingNo( config);
		
		// 2，物流单号回写owms出库单 用事物 trxName
//		MOWMSExWarehouse exWarehouse = MOWMSExWarehouse.getByDocumentNo(ctx, documentNo, trxName);
//		exWarehouse.setTrackingNo(trackingNo);
//		exWarehouse.saveEx();
		
		// 3， 生成面单code 返回
		String showNo = trackingNo.substring(0, 2) + " " + trackingNo.substring(2, 6) + " "
				+ trackingNo.substring(6, 10) + " " + trackingNo.substring(10, 13);

		Map<String,Object> param = new HashMap<String,Object>();
		param.put("documentNo", requestMessage.getDocumentNo());
		param.put("serviceCode", deliveryWay.getServiceCode().split("-")[0]);
		param.put("withSignature", deliveryWay.getServiceCode().split("-")[1]);
		param.put("sortCode", sortCode);
		param.put("trackingNo", trackingNo);
		param.put("showNo", showNo);
		
		
		
		param.put("NAME",ensureNotNull(requestMessage.getConsignee().getName()));
		param.put("ADDRESS1", ensureNotNull(requestMessage.getConsignee().getAddress1()));
		param.put("ADDRESS2", ensureNotNull(requestMessage.getConsignee().getAddress2()));
		param.put("ADDRESS3", ensureNotNull(requestMessage.getConsignee().getAddress3()));
		param.put("CITY", ensureNotNull(requestMessage.getConsignee().getCity()));
		param.put("POSTAL", ensureNotNull(requestMessage.getConsignee().getPostcode()));
		param.put("COUNTRYNAME", ensureNotNull(requestMessage.getConsignee().getCountryCode()));
		param.put("DOCUMENTNO", ensureNotNull(requestMessage.getDocumentNo()));
		param.put("RETURNADDRESS", RoyalMailConfig.RETURN_ADDRESS_UK_FULLADDRESS());
		
		param.put("logisticsCode", deliveryWay.getCode());
		
		String reportFile = RoyalMailConfig.ROYALMAIL_TRACKED_24_REPORT;
		
		if(isTracked48) {
			reportFile = RoyalMailConfig.ROYALMAIL_TRACKED_48H_REPORT;
			String postalBarcode = ensureNotNull(requestMessage.getConsignee().getPostcode()).replaceAll("\\s", "");
			param.put("POSTALBARCODE",postalBarcode );
			param.put("POSTALSHOWNO", "*"+postalBarcode+"*");
			
		}
		
//		Class clazz = Class.forName("org.compiere.report.ReportStarter");
//		Object obj = clazz.newInstance();
//		Method method = obj.getClass().getDeclaredMethod("generatePdfAsBase64", String.class, Map.class);
//		return (String) method.invoke(obj, reportFile, param);
		String code = JasperReportUtils.generatePdfAsBase64(reportFile, param);
		return new Result(code, trackingNo);
	}
	
	/**
	 * 生成TrackingNo
	 * @return
	 */
	public String getTrackingNo( RoyalMailBarcodeConfig config) {
		String productPrefix = config.getProductPrefix();
		String itemIdentifier = getItemIdentifier(config.getSequenceName());
		String checkDigit = getCheckDigit(itemIdentifier);
		String originCode = "GB";
		
		return productPrefix + itemIdentifier + checkDigit + originCode;
	}
	
	/**
	 * 获取ItemIdentifier
	 * @return
	 */
	public String getItemIdentifier(String sequenceName) {
		long seq = sequenceGenerator.nextVal(sequenceName);
		return StringUtils.leftPad(String.valueOf(seq), 8, "0");
	}
	
	/**
	 * 根据ItemIdentifier生成CheckDigit
	 * @param itemIdentifier
	 * @return checkDigit
	 */
	public String getCheckDigit(String itemIdentifier)
	{
		int[] weightings = { 8, 6, 4, 2, 3, 5, 9, 7 };
		int total = 0;
		for (int i = 0; i < itemIdentifier.length(); i++)
		{
			int digit = Integer.valueOf(itemIdentifier.substring(i, (i + 1))) * weightings[i];
			total = total + digit;
		}
		
		int digit = 11 - (total % 11);
		if(digit == 10) {
			digit = 0;
		} else if(digit == 11) {
			digit = 5;
		}
		
		return String.valueOf(digit);
	}


	@Override
	public boolean isIdempotent()
	{
		return true;
	}
	
	/**
	 * if str is null,return "";else return str.
	 * @param str
	 * @return
	 */
	public String ensureNotNull(String str){
		if(str == null){
			return "";
		}
		return str;
	}


}

/**
 * RoyalMail Barcode 配置信息
 * @author temuser2
 *
 */
class RoyalMailBarcodeConfig
{
	private String	serviceCode;
	private String	sequenceName;
	private String	productPrefix;

	/**
	 * @param serviceCode
	 * @param sequenceName
	 * @param productPrefix
	 */
	public RoyalMailBarcodeConfig(String serviceCode, String sequenceName, String productPrefix)
	{
		super();
		this.serviceCode = serviceCode;
		this.sequenceName = sequenceName;
		this.productPrefix = productPrefix;
	}

	public String getServiceCode()
	{
		return serviceCode;
	}

	public void setServiceCode(String serviceCode)
	{
		this.serviceCode = serviceCode;
	}

	public String getSequenceName()
	{
		return sequenceName;
	}

	public void setSequenceName(String sequenceName)
	{
		this.sequenceName = sequenceName;
	}
	
	public String getProductPrefix()
	{
		return productPrefix;
	}

	public void setProductPrefix(String productPrefix)
	{
		this.productPrefix = productPrefix;
	}
	

}
