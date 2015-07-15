package com.winit.commons.spring;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.winit.label.model.User;
import com.winit.label.support.ConfigUtil;

public class WtUserDetailServiceImpl implements UserDetailsService {
	private static final String KEY_USER_NAME="WT_LOGIN_USER";
	private static final String KEY_USER_PWD="WT_LOGIN_PWD";
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		String account = ConfigUtil.getValue(KEY_USER_NAME);
		
		if(StringUtils.isEmpty(account)){
			return null;
		}
		String password = ConfigUtil.getValue(KEY_USER_PWD);
		User user = new User();
		user.setPassword(password);
		return user;
	}  
    
}  