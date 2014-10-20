package com.winit.svr.label.handler.impl.en.ups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.winit.label.model.RequestMessage;
import com.winit.label.model.RequestMessage.Consignee;
import com.winit.label.model.RequestMessage.Product;
import com.winit.svr.LabelSystemException;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.label.LabelHandler;
import com.winit.svr.label.handler.impl.commons.report.JasperReportUtils;

/**
 * 生成UPS的面单，返回面单的Base64位编码
 * @author longsheng.wang
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class UPSLabelHandler implements LabelHandler
{
	
	private static final String		UPS_LABEL_REPORT			= "ups_uk.jrxml";
	


	public Result handle(CommandContext commandContext, RequestMessage requestMessage)
	{
		List<Product> products = requestMessage.getBody().getProducts();
		Consignee consignee = requestMessage.getBody().getConsignee();
		
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
		param.put("documentNo", requestMessage.getBody().getDocumentNo());
		param.put("consigneeName", ensureNotNull(consignee.getName()));
		param.put("consigneeAddress", ensureNotNull(StringUtils.join(consigneeAddressList.iterator(),"\n")));
		param.put("consigneeCity", ensureNotNull(consignee.getCity()));
		param.put("consigneePostal", ensureNotNull(consignee.getPostcode()));
		param.put("consigneeCountry", ensureNotNull(consignee.getCountry()));
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
		// TODO Auto-generated method stub
		return false;
	}
	
	private String ensureNotNull(String str){
		if(str!=null){
			return str;
		}else{
			return "";
		}
	}
	
}
