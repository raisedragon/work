package com.winit.label.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController
{
	protected ModelAndView getDefaultModelAndView(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String servletPath= request.getServletPath();
//		String uri = request.getRequestURI();
//		String lastUri = uri.substring(servletPath.length());
		String lastUri = servletPath;
		String viewName = lastUri.replaceAll("\\.\\S+$", "");
		mv.setViewName(viewName);
		return mv;
	}
}
