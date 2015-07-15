package com.winit.label.manager.impl.gb.bpost;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winit.commons.jasperreports.JasperReportUtils;
import com.winit.label.db.SequenceGenerator;
import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.impl.gb.royalmail.RoyalMailConfig;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;

/**
 * 生成bPost的面单，返回面单的Base64位编码
 * @author temuser2
 *
 */
@Component("uk_bpostLabelHandler")
public class BpostLabelHandler implements LabelHandler
{
	@Autowired
	private SequenceGenerator sequenceGenerator;
	private static DecimalFormat	df					= new DecimalFormat("#######.##");
	@Override
	public Result handle(RequestMessage requestMessage, DeliveryWay deliveryWay) throws Exception
	{
		String serviceCode = deliveryWay.getServiceCode();
		
		// DSA和MiniPak没有快递单号
		boolean tracking = true;
		String reportFile = BpostConfig.BPOST_LABEL;
		if(serviceCode.equals(BpostConfig.DSA)) {
			reportFile = BpostConfig.BPOST_DSA_LABEL;
			tracking = false;
		} else if(serviceCode.equals(BpostConfig.MINIPAK)) {
			reportFile = BpostConfig.BPOST_MINIPAK_LABEL;
			tracking = false;
		}
		
		// 生成快递单号
		String trackingNo = null;
		if(tracking == true) {
			trackingNo = getTrackingNo();
			
//			MOWMSExWarehouse exWarehouse = MOWMSExWarehouse.getByDocumentNo(ctx, documentNo, trxName);
//			exWarehouse.setTrackingNo(trackingNo);
//			exWarehouse.saveEx();
		}
		
		// 生成面单code 返回
		String weight = df.format(requestMessage.getWeight());
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("documentNo", requestMessage.getDocumentNo());
		param.put("serviceCode", serviceCode);
		param.put("weight", weight + "kg");
		
		param.put("NAME", ensureNotNull(requestMessage.getConsignee().getName()));
		param.put("COUNTRYNAME", ensureNotNull(requestMessage.getConsignee().getCountryCode()));
		param.put("CITY", ensureNotNull(requestMessage.getConsignee().getCity()));
		param.put("ADDRESS1", ensureNotNull(requestMessage.getConsignee().getAddress1()));
		param.put("ADDRESS2", ensureNotNull(requestMessage.getConsignee().getAddress2()));
		param.put("ADDRESS3", ensureNotNull(requestMessage.getConsignee().getAddress3()));
		param.put("POSTAL", ensureNotNull(requestMessage.getConsignee().getPostcode()));
		param.put("PHONE", ensureNotNull(requestMessage.getConsignee().getPhone()));
		
//		String WEGHT = "0";
//		if(requestMessage.getWeight()!=0){
//		DecimalFormat decimalFormat = new DecimalFormat("#.####");
//			WEGHT = decimalFormat.format(requestMessage.getWeight());
//		}
//		param.put("WEIGHT", WEGHT);
		param.put("TODAY", ensureNotNull(DateFormatUtils.format(new Date(), "dd/MM/yyyy")));
		param.put("DOCUMENTNO", ensureNotNull(requestMessage.getDocumentNo()));
		param.put("RETURNADDRESS", RoyalMailConfig.RETURN_ADDRESS_ROYALMAIL_NONTRACKED_POBOX());
		
		if(tracking == true) {
			param.put("trackingNo", trackingNo);
		}
		
//		Class clazz = Class.forName("org.compiere.report.ReportStarter");
//		Object obj = clazz.newInstance();
//		Method method = obj.getClass().getDeclaredMethod("generatePdfAsBase64", String.class, Map.class);
//		return (String) method.invoke(obj, reportFile, param);
		String code = JasperReportUtils.generatePdfAsBase64(reportFile, param);
		return new Result(code, trackingNo);
	}
	
	/**
	 * 生成快递单号
	 * @param siteCode
	 * @param originCountry
	 * @return
	 */
	public String getTrackingNo() {
		String siteCode =BpostConfig.SITE_CODE();
		int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		String originCountry = Locale.UK.getCountry();
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(siteCode);
		buffer.append(dayOfWeek);
		buffer.append(originCountry);
		buffer.append(getRange()); // Range 000000001-999999999
		buffer.append("01");
		
		return buffer.toString();
	}
	
	/**
	 * 获取range
	 * @param trxName
	 * @return
	 */
	public String getRange() {
//		int seq = DB.getSQLValue(trxName, "select wt_bPostBarcode_seq.nextval from dual");
		long seq = sequenceGenerator.nextVal(BpostConfig.SEQ_RANGE_KEY);
		return StringUtils.leftPad(String.valueOf(seq), 9, "0");
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
