package com.winit.test;

import java.io.IOException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import com.winit.label.model.RequestMessage;
import com.winit.label.model.ResponseMessage;
import com.winit.svr.impl.persistence.entity.LogisticTypeEntity;
import com.winit.svr.impl.persistence.entity.VariableInstanceEntity;
import com.winit.svr.label.Label;
import com.winit.svr.label.handler.impl.en.ups.UPSLabelHandler;

public class UPSLabelHandlerTest extends LabelServerBaseTest {
	
	@Test
	public void testStartUp() throws IOException{
		
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
		
		logistics.setCode("ups-uk");
		body.setDocumentNo("123456");
		body.setLogistics(logistics);
		body.setConsignee(consignee);
		
		requestMessage.setBody(body);
		ResponseMessage responseMessage = labelService.generateLabel(requestMessage);
		
		System.out.println(ToStringBuilder.reflectionToString(responseMessage.getBody()));
		
		Label label = labelService.createLabelQuery().documentNo(responseMessage.getBody().getDocumentNo()).singleResult();
		String filename = "E:\\temp\\"+label.getDocumentNo()+".pdf";
		writeFile(filename, label.getFileCode());
	}
	
//	@BeforeClass
	public static void initUPSLogisticType(){
		LogisticTypeEntity entity = new LogisticTypeEntity();
		entity.setCode("ups-uk");
		labelService.saveLogisticType(entity);
		labelService.setLogisticTypeVariable(entity.getId(), VariableInstanceEntity.LOGISTIC_TYPE_LABEL_IMPL_CLASS,
				UPSLabelHandler.class.getName());
		
		
	}
	
}
