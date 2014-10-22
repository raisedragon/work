//package com.winit.test;
//
//
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//
//import java.math.BigDecimal;
//
//import net.sf.json.JSONObject;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.ContextHierarchy;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.winit.commons.Constants;
//import com.winit.label.model.DeliveryWay;
//
//
//
//
////XML风格  
//@RunWith(SpringJUnit4ClassRunner.class)  
//@WebAppConfiguration
//@ContextHierarchy({@ContextConfiguration(name="parent",locations="classpath:conf/spring.xml"),@ContextConfiguration(name="children",locations="classpath:conf/spring-mvc.xml")})  
//public class LogisticsTest {
//
//	@Autowired  
//    private WebApplicationContext wac;  
//    private MockMvc mockMvc; 
//    
//    @Autowired  
//    private Cache cache;
//        
//	@Before  
//    public void setUp() {  
//		this.mockMvc = webAppContextSetup(this.wac).build();  
//    } 
//
//	@Test  
//	public void testUsps() throws Exception {
//		RequestMsg request = new RequestMsg();
//		request.setDocumentNo("1659560");
//		request.setHeight(new BigDecimal(0.01));
//		request.setLength(new BigDecimal(0.01));
//		request.setWidth(new BigDecimal(0.01));
//		request.setWeight(new BigDecimal(0.3));
//		request.setLogisctCode("USPS0001");
//		request.setToAddress1("43647 21ST ST W");
//		request.setToCity("Mount Pleasant");
//		request.setToFirm("Mount Pleasant");
//		request.setToName("HECTOR YBARRA");
//		request.setToState("CA");
//		request.setToZip("93536");
//		String json = JSONObject.fromObject(request).toString();
//	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/label/getLabel.do","json").contentType(MediaType.APPLICATION_JSON)
//	    		.content(json.getBytes()))
//	            .andDo(MockMvcResultHandlers.print())  
//	            .andReturn();
//	    ModelAndView model = result.getModelAndView();
//	    JSONObject js = JSONObject.fromObject(model.getViewName());
//	    ResponseMsg rp = (ResponseMsg) JSONObject.toBean(js, ResponseMsg.class);
//	    System.out.println(rp.getFileUrl());
//	    System.out.println(rp.getTrackNo());
//	} 
//	
//	@Test  
//	public void testUps() throws Exception {
//		RequestMsg request = new RequestMsg();
//		request.setDocumentNo("1659560");
//		request.setHeight(new BigDecimal(0.01));
//		request.setLength(new BigDecimal(0.01));
//		request.setWidth(new BigDecimal(0.01));
//		request.setWeight(new BigDecimal(0.3));
//		request.setLogisctCode("UDSR");
//		request.setToAddress1("43647 21ST ST W");
//		request.setToCity("Mount Pleasant");
//		request.setToFirm("Mount Pleasant");
//		request.setToName("HECTOR YBARRA");
//		request.setToState("CA");
//		request.setToZip("93536");
//		request.setProductCount(5);
//		request.setToCountryCode("US");
//		request.setDeclarationTotalPrice(new BigDecimal(12.5));
//		String json = JSONObject.fromObject(request).toString();
//	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/label/getLabel.do","json").contentType(MediaType.APPLICATION_JSON)
//	    		.content(json.getBytes()))
//	            .andDo(MockMvcResultHandlers.print())  
//	            .andReturn();
//	    ModelAndView model = result.getModelAndView();
//	    JSONObject js = JSONObject.fromObject(model.getViewName());
//	    ResponseMsg rp = (ResponseMsg) JSONObject.toBean(js, ResponseMsg.class);
//	    System.out.println(rp.getFileUrl());
//	    System.out.println(rp.getTrackNo());
//	} 
//	
//	@Test
//	public void testCache(){
//		DeliveryWay deliveryWay = (DeliveryWay) cache.get(Constants.DELVERYWAY,"USPS0001");
//		System.out.println(deliveryWay.getCode());
//		System.out.println(cache.get(Constants.SYSCONFIG,"WT_UPS_PHONENUMBER"));
//	}
// 
//}
