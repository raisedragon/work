package com.winit.label.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.winit.label.manager.impl.LabelHandlerInvoker;
import com.winit.label.model.RequestMessage;
import com.winit.label.model.ResponseMessage;




@Controller
@RequestMapping(value="/")
public class LabelController {

	/**
	 * 日志
	 */
	protected static final Logger logger = Logger.getLogger(LabelController.class); 
	
	@Autowired
	private LabelHandlerInvoker labelHandlerInvoker;
	/**
	 * 
	 * <p>获取面单</p>
	 * @param json
	 * @return
	 * @author {庄坚发}
	 */
	@RequestMapping(value="/getLabel",method=RequestMethod.POST)
	public String getLabel(HttpServletRequest request){
		ResponseMessage responseMessage = new ResponseMessage();
		
		String requestStr;
		try
		{
			requestStr = IOUtils.toString(request.getInputStream(),"UTF-8");
			
			logger.info("Get Lable request: "+ requestStr);
		
			if(StringUtils.isEmpty(requestStr)){
				responseMessage.setMessage("paraments is null");
				responseMessage.setStatusCode(100);
			}else{
				RequestMessage requestMessage =  com.alibaba.fastjson.JSONObject.parseObject(requestStr,RequestMessage.class);
				
				responseMessage =  labelHandlerInvoker.invoke(requestMessage);
			}
		}
		catch (IOException e)
		{
			logger.error(ExceptionUtils.getFullStackTrace(e));
			responseMessage.setMessage(e.getMessage());
			responseMessage.setStatusCode(100);
		}
		
		String responseStr =  JSONObject.fromObject(responseMessage).toString();
		logger.info("Get Label response: "+responseStr);
		return responseStr;
	}

	
}
