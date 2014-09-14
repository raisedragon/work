package com.winit.svr.label.context;

import com.winit.svr.LabelException;
import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.persistence.entity.PropertyEntity;

public class ContextUtils
{
	
	
	public static PropertyEntity getPropertyEntity(String key){
		CommandContext ctx = Context.getCommandContext();
		if(ctx==null){
			throw new LabelException("Execute not in a valid context");
		}
		PropertyEntity propertyEntity = ctx.getPropertyEntityManager()
		.findPropertyById(key);
		return propertyEntity;
	}
	
	public static String getValue(String key){
		PropertyEntity propertyEntity = getPropertyEntity(key);
		if(propertyEntity!=null){
			return propertyEntity.getValue();
		}
		return null;
	}
	
	public static String getValue(String key,String defaultValue){
		PropertyEntity propertyEntity = getPropertyEntity(key);
		if(propertyEntity!=null){
			return propertyEntity.getValue();
		}
		return defaultValue;
	}
}
