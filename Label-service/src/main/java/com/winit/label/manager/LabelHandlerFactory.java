package com.winit.label.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winit.label.model.DeliveryWay;
import com.winit.label.service.DeliveryWayService;

@Component
public class LabelHandlerFactory
{
	
	@Autowired
	private List<LabelHandler> handlers;
	private Map<String,LabelHandler> handlersMap = new HashMap<String, LabelHandler>();
	
	@Autowired
	protected DeliveryWayService deliveryWayService;
	
	public LabelHandler getHandler(String code){
		DeliveryWay deliveryWay =  deliveryWayService.findByCode(code);
		return this.getHandler(deliveryWay);
	}
	public LabelHandler getHandler(DeliveryWay deliveryWay){
		
		if(deliveryWay==null){
			return null;
		}
		
		String className = deliveryWay.getImplClass();
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
