package com.winit.label.manager.impl;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.LabelHandler.Result;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;
import com.winit.label.model.ResponseMessage;
import com.winit.label.model.SysConfig;
import com.winit.label.service.DeliveryWayService;
import com.winit.label.service.SysConfigService;
import com.winit.test.RequestMessageUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/conf/spring.test.xml" })
public abstract class BaseLabelHandlerTest
{
	

	static String						filename;


	Logger								logger	= LoggerFactory.getLogger(BaseLabelHandlerTest.class);


	@Autowired(required = true)
	public DeliveryWayService			deliveryWayService;
	@Autowired(required = true)
	private SysConfigService			sysConfigService;

	
	public abstract LabelHandler getLabelHandler();
	
	public abstract String getInputFile();
	public abstract String[] getSheets();
	public abstract String getOutputFile();
	
	
	public String jasperReportDir(){
		return "D:/work/对接组-Prj/面单服务/src/main/resources/jasperreports";
	}

	@Test
	public void testHandle() throws IOException
	{
		String inputfile = getInputFile();
		String[] sheets =getSheets();
		String outputfile = getOutputFile();
		List<RequestMessage>	requestMessages = new ArrayList<RequestMessage>();
		
		for(String sheet:sheets){
			requestMessages.addAll(RequestMessageUtils.getRequestMessageFromExcel(inputfile, sheet));
		}
		
		List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();

		SysConfig config = sysConfigService.selectByName("WT_JASPER_REPORT_DIR");
		config.setValue(jasperReportDir());
		sysConfigService.update(config);

		for (RequestMessage requestMessage : requestMessages)
		{
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setDocumentNo(requestMessage.getDocumentNo());
			try
			{
				System.out.println(JSONObject.toJSONString(requestMessage));
				String logisticsCode = requestMessage.getLogisticsCode();
				DeliveryWay deliveryWay = deliveryWayService.findByCode(logisticsCode);
				Result result = getLabelHandler().handle(requestMessage, deliveryWay);

				responseMessage.setFilePath(result.getBase64Code());
				responseMessage.setStatusCode(0);
				responseMessage.setTrackingNo(result.getTrackNo());
			}
			catch (Exception e)
			{
				e.printStackTrace();
				responseMessage.setStatusCode(1);
				if(StringUtils.isNotEmpty(e.getMessage())){
					responseMessage.setMessage(e.getMessage());
				}else{
					responseMessage.setMessage(ExceptionUtils.getFullStackTrace(e));
				}
			}
			responseMessages.add(responseMessage);
		}
		RequestMessageUtils.saveResponseMessages(responseMessages,outputfile, null);
	}

	@Test
	public abstract void testIsIdempotent();

}
