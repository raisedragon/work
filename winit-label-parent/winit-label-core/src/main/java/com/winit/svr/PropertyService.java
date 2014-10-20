package com.winit.svr;

import com.winit.svr.impl.persistence.entity.PropertyEntity;


public interface PropertyService
{
	
	PropertyEntity findPropertyByName(String name);
	
	void saveProperty(PropertyEntity propertyEntity);
	
	void addProperty(String name,String value,String desc);
	
	void saveProperty(String name,String value,String desc);
	
	void deleteProperty(String propertyId);
}
