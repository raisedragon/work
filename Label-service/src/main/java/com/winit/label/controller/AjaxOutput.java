package com.winit.label.controller;

import java.util.ArrayList;
import java.util.List;

public class AjaxOutput
{
	protected List<String> successes;
	protected List<String> infos;
	protected List<String> warns;
	protected List<String> errors;
	public List<String> getInfos()
	{
		if(infos==null){
			infos = new ArrayList<String>();
		}
		return infos;
	}
	public void setInfos(List<String> infos)
	{
		this.infos = infos;
	}
	public List<String> getWarns()
	{
		if(warns==null){
			warns = new ArrayList<String>();
		}
		return warns;
	}
	public void setWarns(List<String> warns)
	{
		this.warns = warns;
	}
	public List<String> getErrors()
	{
		if(errors==null){
			errors = new ArrayList<String>();
		}
		return errors;
	}
	public void setErrors(List<String> errors)
	{
		this.errors = errors;
	}
	
	public List<String> getSuccesses()
	{
		if(successes==null){
			successes = new ArrayList<String>();
		}
		return successes;
	}
	public void setSuccesses(List<String> successes)
	{
		this.successes = successes;
	}
	public AjaxOutput addSuccess(String success){
		this.getSuccesses().add(success);
		return this;
	}
	public AjaxOutput addInfo(String info){
		this.getInfos().add(info);
		return this;
	}
	public AjaxOutput addWarn(String warn){
		this.getWarns().add(warn);
		return this;
	}
	public AjaxOutput addError(String error){
		this.getErrors().add(error);
		return this;
	}
}
