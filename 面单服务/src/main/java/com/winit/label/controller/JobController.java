package com.winit.label.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.winit.commons.mybatis.Page;
import com.winit.label.model.EntityStatus;
import com.winit.label.model.Job;
import com.winit.label.service.impl.JobServiceImpl;



@Controller
@RequestMapping(value="Job")
public class JobController extends BaseController
{
	
	JobServiceImpl jobService;
	
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		List<Job> jobs = new ArrayList<Job>();
		Job job = new Job();
		job.setName("name");
		job.setClazz("clazz");
		job.setDescription("description");
		job.setGroup("group");
		job.setId(123456L);
		job.setStatus(EntityStatus.ACTIVE);
		jobs.add(job);
		String tableId = "tableId";
		int pageNum = Integer.parseInt(request.getParameter((new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_PAGE))));
		Page page = new Page(pageNum, 10);
		jobService.selectByQueryCriteria(null, page);
		
		
		ModelAndView mv =  getDefaultModelAndView(request);
		mv.addObject("jobs", jobs);
		
		
		
		return mv;
	}
}
