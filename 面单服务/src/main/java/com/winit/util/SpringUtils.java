package com.winit.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * <P>spring获取注册bean辅助类</P>
 * @author {庄坚发}
 */
public class SpringUtils {
	
	/**
	 * 
	 * <p>根据reqeust与springId获取bean</p>
	 * @param request
	 * @param springId
	 * @return
	 * @author {庄坚发}
	 */
	public static Object getBean(HttpServletRequest request,String springId){
		ServletContext  servletContext=request.getSession().getServletContext();
		WebApplicationContext webApplicationContext = (WebApplicationContext)servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		return webApplicationContext.getBean(springId);
	}
}
