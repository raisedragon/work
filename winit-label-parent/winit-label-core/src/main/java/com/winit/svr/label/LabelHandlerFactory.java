package com.winit.svr.label;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.persistence.entity.LogisticTypeEntity;
import com.winit.svr.impl.persistence.entity.VariableInstanceEntity;

public class LabelHandlerFactory
{
	
	List<LabelHandler> handlers = null;
	Map<String,LabelHandler> handlersMap = new HashMap<String, LabelHandler>();
	
	public LabelHandler getHandler(String code){
		LogisticTypeEntity logisticType = Context.getCommandContext()
		.getLogisticTypeManager()
		.findByLogisticTypeCode(code);
		
		if(logisticType==null){
			return null;
		}
		
		String className = (String) logisticType.getVariable(VariableInstanceEntity.LOGISTIC_TYPE_LABEL_IMPL_CLASS);
		if(StringUtils.isEmpty(className)){
			return null;
		}
		return getHandlerLocal(className);
		
	}
	
	private LabelHandler getHandlerLocal(String className){
		LabelHandler handler = handlersMap.get(className);
		if(handler==null){
			for(LabelHandler h:handlers){
				try
				{
					Class<?> clazz = Class.forName(className);
					if(clazz.isInstance(h)){
						handler = h;
						handlersMap.put(className, handler);
					}
				}
				catch (ClassNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return handler;
	}
	
	public void setHandlers(List<LabelHandler> handlers)
	{
		this.handlers = handlers;
	}
}
