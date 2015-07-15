package com.winit.label.manager.impl.de.bpost;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.winit.commons.jasperreports.JasperReportUtils;
import com.winit.label.manager.LabelHandler;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;




/**
 * 抓取bpost面单
 * @author jianfa.zhuang
 *
 */

@Component
public class BpostLabelHandler implements LabelHandler
{


	@Override
	public Result handle(RequestMessage requestMessage, DeliveryWay deliveryWay) throws Exception
	{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//放入出库单号，用于查询发货地址和邮编
		resultMap.put("documentNo", requestMessage.getDocumentNo());
		//BPOST指定德国仓地址信息
		resultMap.put("senderName",BpostConfig.BPOST_DE_SENDER());
		resultMap.put("senderAddressLine1",BpostConfig.BPOST_DE_ADDRESS1());
		resultMap.put("senderTown", BpostConfig.BPOST_DE_TOWN());
		resultMap.put("senderPostcode",BpostConfig.BPOST_DE_POSTCODE());
		
		
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
		
		resultMap.put("NAME", requestMessage.getConsignee().getName());
		resultMap.put("ADDRESS", StringUtils.join(address.iterator()," "));
		resultMap.put("CITY", requestMessage.getConsignee().getCity());
		resultMap.put("POSTAL", requestMessage.getConsignee().getPostcode());
		
		
//		Class<?> clazz = Class.forName("org.compiere.report.ReportStarter");
//		Object obj = clazz.newInstance();
//		Method method = obj.getClass().getDeclaredMethod("generatePdfAsBase64", String.class, Map.class);
		
		String base64Code = null;
		//BPOST_DE_LARGE与BPOST_DE_SMALL都是下面打印
		if(null != deliveryWay.getServiceCode() && ("BPOST_DE_SMALL".equalsIgnoreCase(deliveryWay.getServiceCode()) 
				|| "BPOST_DE_LARGE".equalsIgnoreCase(deliveryWay.getServiceCode()))){ 
//			base64Code =  (String) method.invoke(obj, BpostConfig.BPOST_DE_LABEL_FILENAME(), resultMap);
			base64Code = JasperReportUtils.generatePdfAsBase64(BpostConfig.BPOST_DE_LABEL_FILENAME(), resultMap);
		} else if(null!=deliveryWay.getServiceCode()&&("BPOST_DE_02_SMALL".equalsIgnoreCase(deliveryWay.getServiceCode())
				||"BPOST_DE_02_LARGE".equalsIgnoreCase(deliveryWay.getServiceCode()))){
//			base64Code =  (String) method.invoke(obj, BpostConfig.BPOST_DE_02_LABEL_FILENAME, resultMap);
			base64Code = JasperReportUtils.generatePdfAsBase64(BpostConfig.BPOST_DE_02_LABEL_FILENAME, resultMap);
		}
		return new Result(base64Code, null);
	}

	@Override
	public boolean isIdempotent()
	{
		return true;
	}
}
