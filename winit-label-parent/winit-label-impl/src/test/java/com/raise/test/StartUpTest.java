package com.raise.test;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

import org.activiti.engine.impl.test.TestHelper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import com.winit.svr.IdentityService;
import com.winit.svr.LabelServer;
import com.winit.svr.LabelService;
import com.winit.svr.impl.persistence.entity.LabelEntity;
import com.winit.svr.impl.persistence.entity.LogisticTypeEntity;
import com.winit.svr.impl.persistence.entity.VariableInstanceEntity;
import com.winit.svr.label.Label;
import com.winit.svr.label.LogisticType;
import com.winit.svr.label.RequestMessage;
import com.winit.svr.label.ResponseMessage;
import com.winit.svr.label.handler.impl.commoms.report.JasperReportUtils;
import com.winit.svr.label.handler.impl.en.ups.UPSLable;

import static org.junit.Assert.*;

public class StartUpTest {
	static LabelServer labelServer;
	static LabelService labelService ;
	
	@BeforeClass
	public static void setUp(){
		String configurationResource = "server.cfg.xml";
		labelServer = TestHelper.getLabelServer(configurationResource);
		labelService = labelServer.getLabelService();
		initLogisticType();
	}
	
//	@Test
	public void testStartUp(){
		
		RequestMessage requestMessage = new RequestMessage();
		RequestMessage.SBODY body = new RequestMessage.SBODY();
		RequestMessage.Logistics logistics = new RequestMessage.Logistics();
		RequestMessage.Consignee consignee = new RequestMessage.Consignee();
		
		consignee.setName("wang");
		consignee.setAddress1("address1");
		consignee.setAddress2("address2");
		consignee.setAddress3("address3");
		consignee.setCity("city");
		consignee.setCountry("country");
		consignee.setPostcode("postcode");
		
		logistics.setCode("1");
		body.setDocumentNo("123456");
		body.setLogistics(logistics);
		body.setConsignee(consignee);
		
		requestMessage.setBody(body);
		ResponseMessage responseMessage = labelService.generateLabel(requestMessage);
		
		System.out.println(ToStringBuilder.reflectionToString(responseMessage.getBody()));
		
		Label label = labelService.createLabelQuery().documentNo(responseMessage.getBody().getDocumentNo()).singleResult();
		System.out.println(label.getFileCode());
		
	}
	
	
	protected static  void initLogisticType(){
		LogisticTypeEntity entity = new LogisticTypeEntity();
		entity.setCode("1");
		labelService.saveLogisticType(entity);
		labelService.setLogisticTypeVariable(entity.getId(),VariableInstanceEntity.LOGISTIC_TYPE_LABEL_IMPL_CLASS, UPSLable.class.getName());
	}
	
	
	@Test
	public  void gen() throws Exception{
		String code = JasperReportUtils.generatePdfAsBase64("report1.jrxml", new HashMap<String, Object>());
		System.out.println(code);
		String filename = "F:\\go-src"+System.currentTimeMillis()+".pdf";
		FileOutputStream fos = new FileOutputStream(filename);
		byte[] bs = Base64.decodeBase64(code);
		fos.write(bs);
		fos.close();
		 Desktop.getDesktop().open(new File(filename));;
	}
}
