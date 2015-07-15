package com.winit.label.manager.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
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
import com.winit.test.RequestMessageWrapper;
import com.winit.test.ResponseMessageWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/conf/spring.test.xml" })
public abstract class BaseLabelHandlerTestV2
{
	

	static String						filename;


	Logger								logger	= LoggerFactory.getLogger(BaseLabelHandlerTestV2.class);


	@Autowired(required = true)
	public DeliveryWayService			deliveryWayService;
	@Autowired(required = true)
	private SysConfigService			sysConfigService;

	
	public abstract LabelHandler getLabelHandler();
	
	public abstract String getInputFile();
	public abstract String[] getSheets();
	public String getOutputFile() throws IOException{
		String inputfile = getInputFile();
		int idx = inputfile.lastIndexOf('-');
		String ext = FilenameUtils.getExtension(inputfile);
		String outputfile ;
		if(idx!=-1){
			outputfile = inputfile.substring(0,idx);
		}else{
			outputfile = inputfile.substring(0,inputfile.length()-ext.length()-1);
		}
		outputfile =outputfile+"-"+System.currentTimeMillis()+"."+ext;
		FileUtils.copyFile(new File(inputfile), new File(outputfile));
		return outputfile;
	}
	
	
	public String jasperReportDir(){
		return "D:/work/对接组-Prj/面单服务/src/main/resources/jasperreports";
	}

	@Test
	public void testHandle() throws IOException
	{
		String inputfile = getInputFile();
		String[] sheets =getSheets();
		String outputfile = getOutputFile();
		List<RequestMessageWrapper>	requestMessages = new ArrayList<RequestMessageWrapper>();
		
		for(String sheet:sheets){
			requestMessages.addAll(RequestMessageUtils.getRequestMessageWrapperFromExcel(inputfile, sheet));
		}
		
		List<ResponseMessageWrapper> responseMessages = new ArrayList<ResponseMessageWrapper>();

		SysConfig config = sysConfigService.selectByName("WT_JASPER_REPORT_DIR");
		config.setValue(jasperReportDir());
		sysConfigService.update(config);

		for (RequestMessageWrapper requestMessageWrapper : requestMessages)
		{
			RequestMessage requestMessage=requestMessageWrapper.getRequestMessage();
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
			responseMessages.add(new ResponseMessageWrapper(requestMessageWrapper.getSheetname(), requestMessageWrapper.getRownum(), responseMessage));
		}
		RequestMessageUtils.saveResponseMessages(responseMessages,outputfile);
	}

	@Test
	public abstract void testIsIdempotent();

}
