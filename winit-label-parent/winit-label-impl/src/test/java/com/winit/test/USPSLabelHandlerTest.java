package com.winit.test;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import com.winit.label.model.RequestMessage;
import com.winit.label.model.ResponseMessage;
import com.winit.label.model.RequestMessage.ClassifyProduct;
import com.winit.label.model.RequestMessage.Product;
import com.winit.svr.impl.persistence.entity.LogisticTypeEntity;
import com.winit.svr.impl.persistence.entity.VariableInstanceEntity;
import com.winit.svr.label.Label;
import com.winit.svr.label.handler.impl.en.ups.UPSLabelHandler;
import com.winit.svr.label.handler.impl.usa.usps.USPSIntlLabelHandler;

public class USPSLabelHandlerTest extends LabelServerBaseTest {
	
	@Test
	public void testStartUp() throws IOException{
		
		RequestMessage requestMessage = new RequestMessage();
		RequestMessage.SBODY body = new RequestMessage.SBODY();
		RequestMessage.Logistics logistics = new RequestMessage.Logistics();
		RequestMessage.Consignee consignee = new RequestMessage.Consignee();
		
		consignee.setName("Peter Perring");
		consignee.setAddress1("2 Whittle Street");
		consignee.setAddress2("2 Whittle Street");
		consignee.setAddress3("2 Whittle Street");
		consignee.setCity("Teneriffe");
		consignee.setCountry("GB");
		consignee.setPostcode("AB01");
		consignee.setProvince("IN");
		consignee.setPhone("765 284 2848");
		
		Product p1 = new Product();
		p1.setSku("M010000000000015673");
		p1.setEname("Cellphone Case");
		p1.setQty(new BigDecimal("1"));
		p1.setWeight(new BigDecimal("1"));
		ClassifyProduct cp1 = new ClassifyProduct();
		cp1.setCountry("US");
		cp1.setDeclareName("Cellphone Case");
		cp1.setPriceExports(new BigDecimal("1"));
		cp1.setPriceImports(new BigDecimal("1"));
		p1.getClassifyProducts().add(cp1);
		
		body.getProducts().add(p1);
		
		logistics.setCode("usps-us-intl");
		body.setDocumentNo("123456");
		body.setLogistics(logistics);
		body.setConsignee(consignee);
		
		requestMessage.setBody(body);
		ResponseMessage responseMessage = labelService.generateLabel(requestMessage);
		
		System.out.println(ToStringBuilder.reflectionToString(responseMessage.getBody()));
		
		if(responseMessage.getBody().getStatusCode()!=0){
			return ;
		}
		
		Label label = labelService.createLabelQuery().documentNo(responseMessage.getBody().getDocumentNo()).singleResult();
		
		
		String filename = "E:\\temp\\"+label.getDocumentNo()+".png";
		writeFile(filename, label.getFileCode());
	}
	
	@BeforeClass
	public static void initUPSLogisticType(){
		LogisticTypeEntity entity = new LogisticTypeEntity();
		entity.setCode("usps-us-intl");
		labelService.saveLogisticType(entity);
		labelService.setLogisticTypeVariable(entity.getId(), VariableInstanceEntity.LOGISTIC_TYPE_LABEL_IMPL_CLASS,
				USPSIntlLabelHandler.class.getName());
		labelService.setLogisticTypeVariable(entity.getId(), "serviceCode","EMS");
			
			
		propertyService.saveProperty("USPS_INTL_GSS_URL","http://gss.usps.com/usps-cpas/TestGSSAPI/GSSMailerWebService.asmx",null);
		propertyService.saveProperty("USPS_INTL_GSS_WORKSTATION_ID","WINITAMTDUSM",null);
		propertyService.saveProperty("USPS_INTL_GSS_LABELING_USER_ID","WinITforLabel",null);
		propertyService.saveProperty("USPS_INTL_GSS_LABELING_PASSWORD","WIT3207gss",null);
		propertyService.saveProperty("USPS_INTL_GSS_LABELING_LOCATION_ID","LABELERLWINITAMTCUSL",null);
		propertyService.saveProperty("USPS_INTL_GSS_USER_ID","WATCtest",null);
		propertyService.saveProperty("USPS_INTL_GSS_PASSWORD","WATC2504gss",null);
		propertyService.saveProperty("USPS_INTL_GSS_LOCATION_ID","TESTSITEWINITAMTDUSM",null);
		propertyService.saveProperty("USPS_INTL_LABEL_MAILING_AGENT_ID","WINITAMTCUSL",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SHIPPING_AGENT_ID","WINITAMTCUSL",null);
		propertyService.saveProperty("USPS_INTL_LABEL_RECEIVING_AGENT_ID","USPSERVICUSD",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SENDER_BUSINESS_NAME","WinIt America Trade Company LTD",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SENDER_ADDRESS_LINE1","1250 Graves Ave Unit B",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SENDER_ADDRESS_LINE2","XXXXXX",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SENDER_ADDRESS_LINE3","XXXXXX",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SENDER_CITY","Oxnard",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SENDER_PROVINCE","CA",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SENDER_POSTAL_CODE","93030",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SENDER_COUNTRY_CODE","US",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SENDER_ADDRESS_IS_PO_BOX","N",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SERVICE_TYPE","LBL",null);
		propertyService.saveProperty("USPS_INTL_LABEL_PACKAGE_TYPE","M",null);
		propertyService.saveProperty("USPS_INTL_LABEL_PF_COR_EEL","NOEEI 30.37(a)",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SENDER_NAME","XXXXXX",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SENDER_FIRST_NAME","XXXXXX",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SENDER_MIDDLE_INITIAL","XXXXXX",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SENDER_LAST_NAME","XXXXXX",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SENDER_PHONE","XXXXXX",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SENDER_EMAIL","XXXXXX",null);
		propertyService.saveProperty("USPS_INTL_LABEL_SENDER_TAXPAYER_ID","XXXXXX",null);
		propertyService.saveProperty("USPS_INTL_LABEL_ALREADY_LOAD_ERR_MSG","Package : %s : Package %s  has already been loaded into the database and SUCCESSFULLY LABELED.",null);
		propertyService.saveProperty("USPS_INTL_LABEL_FORMAT","PNG",null);
		propertyService.saveProperty("USPS_INTL_GSS_REQUEST_TIME_OUT","12000",null);
		propertyService.saveProperty("USPS_INTL_SOAP_LOG_LEVEL","FINE",null);
		propertyService.saveProperty("USPS_INTL_NETWORK_EXCEPTION_MESSAGE","404,UnknownHostException,Timeout,timed out XXXXXX",null);
	}
	
}
