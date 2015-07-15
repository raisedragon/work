package com.winit.label.manager.impl.de.deutschepost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winit.commons.jasperreports.JasperReportUtils;
import com.winit.label.db.SequenceGenerator;
import com.winit.label.manager.LabelHandler;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;

@SuppressWarnings({"unchecked", "rawtypes"})
@Component
public class DeutschePostLabelHandler implements LabelHandler
{

	@Autowired
	private SequenceGenerator sequenceGenerator;
	
	
	public String getOriginalNumber() {
//		int seq = DB.getSQLValue(trxName, "select wt_DeutschePostLable_seq.nextval from dual");
//		String original = StringUtils.leftPad(String.valueOf(seq), 8, "0");
		String seq=""+sequenceGenerator.nextVal(DeutschePostConfig.SEQ_GETORIGINAL_NUMBER);
		String original = StringUtils.leftPad(seq, 8, "0");
		return original;
	}
	
	public String getCheckDigit(String original) {
		int[] factor = {8, 6, 4, 2, 3, 5, 9, 7};
		int total = 0;
		for(int i = 0; i < original.length(); i++) {
			int number = Integer.parseInt(String.valueOf(original.charAt(i)));
			total = total + (number * factor[i]);
		}
		int checkDigit = 11 - (total - (total / 11) * 11);
		if(checkDigit == 10) {
			checkDigit = 0;
		}
		if(checkDigit == 11) {
			checkDigit = 5;
		}
		return String.valueOf(checkDigit);
	}
	
	public String getTrackingNo(String original, String checkDigit) {
		String trackingNo = DeutschePostConfig.BARCODE_PREFIX()
				+ original 
				+ checkDigit 
				+ DeutschePostConfig.BARCODE_SUFFIX().trim();
		return trackingNo;
	}
	
	public String getShowNo(String trackingNo) {
		StringBuffer showNo = new StringBuffer();
		showNo.append(trackingNo.substring(0, 2)).append(" ");
		showNo.append(trackingNo.substring(2, 4)).append(" ");
		showNo.append(trackingNo.substring(4, 7)).append(" ");
		showNo.append(trackingNo.substring(7, 10));
		if(trackingNo.length() > 10) {
			showNo.append(" ");
			showNo.append(trackingNo.substring(10, trackingNo.length()));
		}
		return showNo.toString();
	}

	@Override
	public Result handle(RequestMessage requestMessage, DeliveryWay deliveryWay) throws Exception
	{
		String original = getOriginalNumber();
		String checkDigit = getCheckDigit(original);
		String trackingNo = getTrackingNo(original, checkDigit);
		String showNo = getShowNo(trackingNo);
		
		List<String> address = new ArrayList<String>();
		if(StringUtils.isNotEmpty(requestMessage.getConsignee().getAddress1())){
			address.add(requestMessage.getConsignee().getAddress1());
		}
		if(StringUtils.isNotEmpty(requestMessage.getConsignee().getAddress2())){
			address.add(requestMessage.getConsignee().getAddress2());
		}
		if(StringUtils.isNotEmpty(requestMessage.getConsignee().getAddress3())){
			address.add(requestMessage.getConsignee().getAddress3());
		}
		
		Map param = new HashMap();
		param.put("documentNo", requestMessage.getDocumentNo());
		param.put("trackingNo", trackingNo);
		param.put("showNo", showNo);
		param.put("senderCompanyname",DeutschePostConfig.SENDER_COMPANYNAME());
		param.put("senderAddress",DeutschePostConfig.SENDER_ADDRESS());
		param.put("senderCity", DeutschePostConfig.SENDER_CITY());
		param.put("senderPostcode",DeutschePostConfig.SENDER_POSTCODE());
		
		param.put("NAME",limitStr( requestMessage.getConsignee().getName(), 35));
		param.put("ADDRESS", limitStr( StringUtils.join(address.iterator()," "), 35));
		param.put("CITY", limitStr(requestMessage.getConsignee().getCity(),35));
		param.put("POSTAL", requestMessage.getConsignee().getPostcode());
		param.put("COUNTRYNAME", getDisplayCountry(requestMessage.getConsignee().getCountryCode()));
		
		String base64 = JasperReportUtils.generatePdfAsBase64(DeutschePostConfig.REPORT_FILE, param);
		
		return new Result(base64, trackingNo);		
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
	private String limitStr(String str,int len){
		if(str==null){
			return "";
		}
		if(str.length()<=len){
			return str;
		}
		return str.substring(0,len);
	}
	@Override
	public boolean isIdempotent()
	{
		return true;
	}

}
