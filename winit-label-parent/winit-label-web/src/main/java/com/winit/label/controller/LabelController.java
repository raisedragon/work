package com.winit.label.controller;


import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.winit.label.model.RequestMessage;
import com.winit.label.model.ResponseMessage;
import com.winit.svr.LabelService;





@Controller
@RequestMapping(value="/")
public class LabelController {

	protected static final Logger logger = LoggerFactory.getLogger(LabelController.class); 
	
	@Autowired
	private LabelService labelService;

	
	/**
	 * 
	 * <p>获取面单</p>
	 * @param json
	 * @return
	 * @author {庄坚发}
	 */
	@RequestMapping(value="/getLabel")
	@ResponseBody
	public String getLabel(HttpServletRequest request){
		String data = request.getParameter("data");
		RequestMessage requestMessage = JSONObject.parseObject(data,RequestMessage.class);
		ResponseMessage responseMessage = labelService.generateLabel(requestMessage);
		String js = JSONObject.toJSONString(responseMessage);
		System.out.println(js);
		
		return js;
	}
	

	/**
	 * 
	 * <p>获取面单</p>
	 * @param json
	 * @return
	 * @author {庄坚发}
	 */
	@RequestMapping(value="/index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
}
