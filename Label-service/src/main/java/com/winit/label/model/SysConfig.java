package com.winit.label.model;

import java.io.Serializable;

/**
 * 
 * <P></P>
 * @author {庄坚发}
 */
public class SysConfig implements Serializable{
	protected String id;
	protected String name;
	protected String value;
	/**
	 * TODO
	 */
	private static final long serialVersionUID = -7271603172869849480L;
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	
	
}
