package com.winit.commons.spring;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

//1 加载资源与权限的对应关系  
public class WtSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {  
  //返回所请求资源所需要的权限  
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException
	{

		// String requestUrl = ((FilterInvocation) object).getRequestUrl();
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean supports(Class<?> arg0)
	{
		// TODO Auto-generated method stub
		return true;
	}

}  