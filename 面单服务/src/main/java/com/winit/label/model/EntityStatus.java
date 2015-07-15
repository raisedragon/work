package com.winit.label.model;

public enum EntityStatus
{
	ACTIVE("active"),
	INACTIVE("inactive"),
	DELETED("delete");
	
	private String text;
	
	private EntityStatus(String text){
		this.text = text;
	}
	public String getText()
	{
		return text;
	}
}
