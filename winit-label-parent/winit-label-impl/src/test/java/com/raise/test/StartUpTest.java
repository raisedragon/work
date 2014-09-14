package com.raise.test;

import java.util.List;

import org.activiti.engine.impl.test.TestHelper;
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
	
	@Test
	public void testStartUp(){
		LabelService labelService = labelServer.getLabelService();
		
		RequestMessage requestMessage = new RequestMessage();
		RequestMessage.SBODY body = new RequestMessage.SBODY();
		RequestMessage.Logistics logistics = new RequestMessage.Logistics();
		logistics.setCode("1");
		body.setDocumentNo("123456");
		body.setLogistics(logistics);
		
		requestMessage.setBody(body);
		ResponseMessage responseMessage = labelService.generateLabel(requestMessage);
		
		System.out.println(ToStringBuilder.reflectionToString(responseMessage.getBody()));
		
		
	}
	
	
	protected static  void initLogisticType(){
		LogisticTypeEntity entity = new LogisticTypeEntity();
		entity.setCode("1");
		labelService.saveLogisticType(entity);
		labelService.setLogisticTypeVariable(entity.getId(),VariableInstanceEntity.LOGISTIC_TYPE_LABEL_IMPL_CLASS, UPSLable.class.getName());
	}
}
