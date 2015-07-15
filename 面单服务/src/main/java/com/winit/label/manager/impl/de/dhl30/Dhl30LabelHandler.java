package com.winit.label.manager.impl.de.dhl30;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.winit.label.db.SequenceGenerator;
import com.winit.label.manager.LabelHandler;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;
import com.winit.label.model.RequestMessage.Consignee;

/**
 * dhl 获取面单
 * 
 * @author wu.jiaohua
 */
public class Dhl30LabelHandler implements LabelHandler
{
	@Autowired
	private SequenceGenerator sequenceGenerator ;
	
	
	@Autowired 
	private Dhl30ConsignmentService consignmentService;
	
	@Override
	public Result handle(RequestMessage requestMessage, DeliveryWay deliveryWay) throws Exception
	{
		String trackingNo = getTrackingNo();
		String serviceCode = deliveryWay.getServiceCode();
		
		Map<String, Object> param = getParam(requestMessage, deliveryWay, trackingNo);
		Class<?> clazz = Class.forName("org.compiere.report.ReportStarter");
		Object obj = clazz.newInstance();
		Method method = obj.getClass().getDeclaredMethod("generatePdfAsBase64", String.class, Map.class);
		String lableCode =  (String) method.invoke(obj, Dhl30Config.DHL30_LABEL_FILENAME, param);
		
		return new Result(lableCode, trackingNo);
	}

	public Map<String, Object> getParam(RequestMessage requestMessage, DeliveryWay deliveryWay, String trackingNo) throws Exception
	{
		
		Consignee consignee = requestMessage.getConsignee();
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		// 重量
		String weight = Double.valueOf(requestMessage.getWeight()).intValue()+"";

		// Shuttle
		Dhl30Consignment cons = getShuttle(
				consignee.getCountryCode(), 
				consignee.getPostcode(), 
				Dhl30Config.WINIT_BRANCH_CODE(), 
				Dhl30Config.DEFAULT_BRANCH_CODE());
		
		// 街道
		StringBuffer street = new StringBuffer();
		String consigneeAddressLine1 = consignee.getAddress1();
		String consigneeAddressLine2 = consignee.getAddress2();
		String consigneeAddressLine3 = consignee.getAddress3();
		if (StringUtils.isNotEmpty(consigneeAddressLine1))
		{
			street.append(consigneeAddressLine1);
		}
		if (StringUtils.isNotEmpty(consigneeAddressLine2))
		{
			street.append(" ");
			street.append(consigneeAddressLine2);
		}
		if (StringUtils.isNotEmpty(consigneeAddressLine3))
		{
			street.append(" ");
			street.append(consigneeAddressLine3);
		}
		
		// 国家/邮编/城市，德国国内不需要国家名称，其他国家需要英文全称
		String countryCode = consignee.getCountryCode().toUpperCase();
		countryCode = "UK".equals(countryCode) ? "GB" : countryCode;
		String countryName = "DE".equals(countryCode) ? "" : new Locale("", countryCode).getDisplayCountry(Locale.US).toUpperCase() + " ";
		String countryLine = countryName 
				+ (consignee.getPostcode() == null ? "" : consignee.getPostcode()) 
				+ " " + subString(consignee.getCity());
		
		param.put("SystemDate", "");
		param.put("serviceCode", deliveryWay.getServiceCode());
		param.put("sendCompany", Dhl30Config.SEND_COMPANY());
		param.put("sendAddress", Dhl30Config.SEND_ADDRESS());
		param.put("countrynamePostal",Dhl30Config.COUNTRY_NAME_POSTAL());
		
		param.put("Barcode", trackingNo);
		param.put("documentNo", requestMessage.getDocumentNo());
		param.put("exwarehouseWeight", weight + "kg");
		param.put("consigneeName1", subString(consignee.getName()).toUpperCase());
		param.put("consigneeAddress",subString(street.toString()).toUpperCase());
		param.put("Country", countryLine.toUpperCase());
		
		param.put("Shuttle", cons.getShuttlesonder());
		param.put("Leitgebiet", cons.getLeitgebiet());
		param.put("Ausnahme", "DE".equals(countryCode) ? "" : cons.getTerminal());
		param.put("Tour", cons.getTour());
		
		return param;
	}
	
	/**
	 * 根据国家编码，邮编，branchCode来查询Consignment
	 * @param countryCode
	 * @param postal
	 * @param winitBranchCode
	 * @param defaultBranchCode
	 * @return
	 * @throws Exception
	 */
	public Dhl30Consignment getShuttle(String countryCode, String postal, String winitBranchCode, String defaultBranchCode) throws Exception {
		Dhl30Consignment result = null;
		
		countryCode = countryCode.toUpperCase();
		if(countryCode.equals("UK")) {
			countryCode = "GB";
		}
		if(postal == null || postal.trim().equals("")) {
			// 没有邮编
			result = consignmentService.getConsignmentByCountry(countryCode, winitBranchCode, defaultBranchCode);
		} else {
			postal = postal.toUpperCase();
			// 有邮编，先查询起始邮编
			String startPostal = consignmentService.getStartPostcode(countryCode, postal);
			if(startPostal == null) {
				throw new Exception("Get routing area failed!");
			} else {
				startPostal = startPostal.toUpperCase();
			}
			
			// 再根据起始邮编查询
			result = consignmentService.getConsignmentByCountryAndPostcode(countryCode, startPostal, winitBranchCode, defaultBranchCode);
		}
		
		if(result == null) 
		{
			throw new Exception("Get routing area failed!");
		}
		
		return result;
	}

	/**
	 * 截取35位
	 * 
	 * @return
	 */
	public String subString(String str)
	{
		if (str.length() > 35)
			return str.substring(0, 35);
		return str;
	}

	/**
	 * 得到TrackingNo
	 * @return
	 */
	public String getTrackingNo()
	{
		String prefix = Dhl30Config.BARCODE_NO();
		String range = getRange();
		int len = range.length();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 9 - len; i++)
		{
			sb.append("0");
		}

		return prefix + sb.toString() + range;
	}

	/**
	 * 得到序列值
	 * @return
	 */
	public String getRange()
	{
//		int seq = DB.getSQLValue(null, "select WT_dhlDeBarcode_seq.nextval from dual");
		String seq = ""+sequenceGenerator.nextVal(Dhl30Config.BARCODE_SEQ_KEY);
		return StringUtils.leftPad(seq, 9, "0");
	}


	@Override
	public boolean isIdempotent()
	{
		return true;
	}

}
