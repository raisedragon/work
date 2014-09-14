package com.winit.svr.label;

import java.io.Serializable;

public interface LogisticType extends Serializable 
{
	String getId();
	void setId(String id);
	
	String getName();
	void setName(String name);
	
	String getCode();
	void setCode(String code);
}
