package com.winit.label.manager.impl.de.bpost;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

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
import com.winit.label.manager.LabelHandler.Result;
import com.winit.label.manager.impl.us.ups.model.accept.response.Response;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;
import com.winit.label.model.ResponseMessage;
import com.winit.label.model.SysConfig;
import com.winit.label.service.DeliveryWayService;
import com.winit.label.service.SysConfigService;
import com.winit.test.RequestMessageUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/conf/spring.test.xml" })

public class BpostLabelHandlerTest 
{
	
	static String filename ;
	
	@Autowired
	private BpostLabelHandler bpostLabelHandler;
	
	Logger logger = LoggerFactory.getLogger(BpostLabelHandlerTest.class);
	private static List<RequestMessage>	requestMessages;

	@Autowired(required = true)
	private DeliveryWayService			deliveryWayService;
	@Autowired(required = true)
	private SysConfigService sysConfigService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		
		
		
		
		String path = BpostLabelHandlerTest.class.getPackage().getName().replace('.', '/');

		filename =  URLDecoder.decode(ClassLoader.getSystemResource(path + "/" + "DATA.xls").getFile() ,"UTF-8");;
		System.out.println(filename);
		requestMessages = RequestMessageUtils.getRequestMessageFromExcel(filename, "DEB0001");
		requestMessages.addAll(RequestMessageUtils.getRequestMessageFromExcel(filename, "DEB0002"));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Test
	@Rollback(value=true)
	public void testHandle() throws IOException
	{
		
		List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
		
		SysConfig config = sysConfigService.selectByName("WT_JASPER_REPORT_DIR");
		config.setValue("D:/work/对接组-Prj/面单服务/src/main/resources/jasperreports");
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
				Result result = bpostLabelHandler.handle(requestMessage, deliveryWay);
				
				responseMessage.setFilePath(result.getBase64Code());
				responseMessage.setStatusCode(0);
				responseMessage.setTrackingNo(result.getTrackNo());
				
//				assertNotNull(result.getBase64Code());
//				logger.info(result.getBase64Code());
			}
			catch (Exception e)
			{
				e.printStackTrace();
				responseMessage.setStatusCode(1);
				responseMessage.setMessage(ExceptionUtils.getFullStackTrace(e));
			}
			responseMessages.add(responseMessage);
		}
		RequestMessageUtils.saveResponseMessages(responseMessages, "D:\\work\\MyWork\\DE\\bpost\\test\\a.xls", null);
	}

	@Test
	public void testIsIdempotent()
	{
		assertTrue(bpostLabelHandler.isIdempotent());
	}
	
}
