package com.winit.label.query;

import com.winit.exception.LabelException;

public class SysConfigQuery
{
	private String idEqual;
	private String nameEqual;
	private String nameLike;
	private String valueEqual;
	private String valueLike;

	public SysConfigQuery idEqual(String id){
		if(id==null){
			throw new LabelException("Provided id is null");
		}
		this.idEqual= id;
		return this;
	}
	
	public SysConfigQuery nameEqual(String name){
		if(name==null){
			throw new LabelException("Provided name is null");
		}
		this.nameEqual = name;
		return this;
	}
	
	
	public SysConfigQuery nameLike(String name){
		if(name==null){
			throw new LabelException("Provided name is null");
		}
		this.nameLike = name;
		return this;
	}
	
	
	public SysConfigQuery valueEqual(String value){
		if(value==null){
			throw new LabelException("Provided value is null");
		}
		this.valueEqual = value;
		return this;
	}
	public SysConfigQuery valueLike(String value){
		if(value==null){
			throw new LabelException("Provided value is null");
		}
		this.valueLike = value;
		return this;
	}

	public String getIdEqual()
	{
		return idEqual;
	}

	public String getNameEqual()
	{
		return nameEqual;
	}

	public String getNameLike()
	{
		return nameLike;
	}

	public String getValueEqual()
	{
		return valueEqual;
	}

	public String getValueLike()
	{
		return valueLike;
	}

	
	
	
	
//	public List<String> getConditions(){
//		List<String> conditions = new ArrayList<String>();
//		
//	}
}
