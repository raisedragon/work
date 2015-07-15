package com.winit.commons.spring;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

public class WtAccessDecisionManager implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException
	{
		// TODO Auto-generated method stub
		System.out.println(123);
		
	}

	@Override
	public boolean supports(ConfigAttribute attribute)
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz)
	{
		// TODO Auto-generated method stub
		return true;
	}  
  
      
}  