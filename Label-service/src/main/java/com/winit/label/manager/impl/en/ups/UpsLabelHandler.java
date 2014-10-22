package com.winit.label.manager.impl.en.ups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.winit.commons.jasperreports.JasperReportUtils;
import com.winit.exception.LabelSystemException;
import com.winit.label.manager.LabelHandler;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;
import com.winit.label.model.RequestMessage.Consignee;
import com.winit.label.model.RequestMessage.Product;

/**
 * 生成UPS的面单，返回面单的Base64位编码
 * @author longsheng.wang
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Component("enUpsLabelHandler")
public class UpsLabelHandler implements LabelHandler
{
	
	private static final String		UPS_LABEL_REPORT			= "ups_uk.jrxml";
	


	public Result handle(RequestMessage requestMessage,DeliveryWay deliveryWay)
	{
		List<Product> products = requestMessage.getProducts();
		Consignee consignee = requestMessage.getConsignee();
		
		List<String> skus = new ArrayList<String>();
		for(Product product:products){
			String sku = product.getSku();
			skus.add(sku);
		}

		List<String> consigneeAddressList = new ArrayList<String>();
		if(StringUtils.isNotEmpty(consignee.getAddress1())){
			consigneeAddressList.add(consignee.getAddress1());
		}
		if(StringUtils.isNotEmpty(consignee.getAddress2())){
			consigneeAddressList.add(consignee.getAddress2());
		}
		if(StringUtils.isNotEmpty(consignee.getAddress3())){
			consigneeAddressList.add(consignee.getAddress3());
		}
		
		// 生成面单code 返回
		Map param = new HashMap();
		param.put("documentNo", requestMessage.getDocumentNo());
		param.put("consigneeName", ensureNotNull(consignee.getName()));
		param.put("consigneeAddress", ensureNotNull(StringUtils.join(consigneeAddressList.iterator(),"\n")));
		param.put("consigneeCity", ensureNotNull(consignee.getCity()));
		param.put("consigneePostal", ensureNotNull(consignee.getPostcode()));
		param.put("consigneeCountry", ensureNotNull(consignee.getCountryCode()));
		param.put("skus", ensureNotNull(StringUtils.join(skus.iterator(), "\n")));
		String base64Code;
		try
		{
			base64Code = JasperReportUtils.generatePdfAsBase64(UPS_LABEL_REPORT, param);
		}
		catch (Exception e)
		{
			throw new LabelSystemException(e);
		}
		Result result = new Result(base64Code, null);
		return result;
	}

	public boolean isIdempotent()
	{
		return true;
	}
	
	private String ensureNotNull(String str){
		if(str!=null){
			return str;
		}else{
			return "";
		}
	}
	
}
