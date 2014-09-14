package com.winit.svr.label.handler.impl.en.ups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.winit.svr.LabelSystemException;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.label.LabelHandler;
import com.winit.svr.label.RequestMessage;
import com.winit.svr.label.RequestMessage.Product;
import com.winit.svr.label.handler.impl.commoms.report.JasperReportUtils;

/**
 * 生成UPS的面单，返回面单的Base64位编码
 * @author longsheng.wang
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class UPSLable implements LabelHandler
{
	
	private static final String		UPS_LABEL_REPORT			= "ups_uk.jrxml";
	


	@Override
	public Result handle(CommandContext commandContext, RequestMessage requestMessage)
	{
		List<Product> products = requestMessage.getBody().getProducts();
		List<String> skus = new ArrayList<String>();
		for(Product product:products){
			String sku = product.getSku();
			skus.add(sku);
		}
		// 生成面单code 返回
		Map param = new HashMap();
		param.put("documentNo", requestMessage.getBody().getDocumentNo());
		param.put("skus", StringUtils.join(skus.iterator(), "\n"));
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

	@Override
	public boolean isIdempotent()
	{
		// TODO Auto-generated method stub
		return false;
	}
	
}
