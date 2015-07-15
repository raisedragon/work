package com.winit.label.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.winit.commons.mybatis.Page;
import com.winit.label.model.SysConfig;
import com.winit.label.query.SysConfigQuery;
import com.winit.label.service.SysConfigService;


@Controller
@RequestMapping(value="SysConfig")
public class SysConfigController
{
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv =  getDefaultModelAndView(request);
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("listAjax")
	@ResponseBody
	public Map<String,Object> listAjax(HttpServletRequest request,HttpServletResponse response){
		int start = Integer.valueOf(request.getParameter("start"));
		int length = Integer.valueOf(request.getParameter("length"));
		int draw = Integer.valueOf(request.getParameter("draw"));
		String nameLike = request.getParameter("columns[0][search][value]");
		String valueLike = request.getParameter("columns[1][search][value]");
		int pageNum =  start/length+1;
		Page page = new Page(pageNum, length);
		
		
		
		SysConfigQuery query = new SysConfigQuery();
		if(StringUtils.isNotEmpty(nameLike))
			query.nameLike(nameLike);
		if(StringUtils.isNotEmpty(valueLike))
			query.valueLike(valueLike);
		
		List<SysConfig> configs = sysConfigService.selectByQueryCriteria(query, page);
		Map<String,Object> map = new HashMap<String, Object>();
		
		List<List<String>> confs = (List<List<String>>) CollectionUtils.collect(configs, new Transformer() {
			@Override
			public Object transform(Object config)
			{
				SysConfig conf = (SysConfig)config;
				List<String> list =new  ArrayList<String>();
				list.add(conf.getName());
				list.add(conf.getValue());
				list.add("<a href='#' name='edit' configId='"+conf.getId()+"'>Edit</a> <a href='#' name='delete' configId='"+conf.getId()+"'>Delete</a>");
				return list;
			}
		});
		
		map.put("draw", draw);
		map.put("data", confs);
		map.put("recordsTotal",page.getTotal());
		map.put("recordsFiltered", page.getTotal());
		
		return map;
	}
	
	/**
	 * 进入系统配置项编辑页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("edit")
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("configId");
		 ModelAndView mv =getDefaultModelAndView(request);
		 SysConfig sysConfig = null;
		 if(StringUtils.isEmpty(id)){
			 sysConfig = new SysConfig();
		 }else{
			 sysConfig =sysConfigService.select(id);
		 }
		 mv.addObject("sysConfig", sysConfig);
		 return mv;
	}
	
	
	/**
	 * 删除系统配置项
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public AjaxOutput delete(HttpServletRequest request,HttpServletResponse response){
		AjaxOutput output = new AjaxOutput();
		try{
			String id = request.getParameter("id");
			if(StringUtils.isNotEmpty(id)){
				sysConfigService.delete(id);
				output.addSuccess("Delete success");
			}else{
				output.addError("Params miss!");
			}
		}catch(Exception e){
			output.addError(e.getMessage());
		}
		return output;
	}
	
	/**
	 * 保存系统配置项
	 * @param request
	 * @param response
	 * @param sysConfig
	 * @return
	 */
	@RequestMapping(value={"save"},method={RequestMethod.POST})
	@ResponseBody
	public AjaxOutput save(HttpServletRequest request,HttpServletResponse response,@ModelAttribute SysConfig sysConfig){
		AjaxOutput output = new AjaxOutput();
		
		if(null!=sysConfigService.selectByName(sysConfig.getName())){
			output.addError(String.format("System config with name '%s' has already exists",sysConfig.getName()));
		}else{
			if(StringUtils.isNotEmpty(sysConfig.getId())){
				sysConfigService.update(sysConfig);
			}else{
				sysConfigService.insert(sysConfig);
			}
			output.addSuccess("save System Config success");
		}
		return output;
	}
	
	
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
