package com.winit.svr.label.handler.impl;

import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.label.Label;
import com.winit.svr.label.LabelHandler;
import com.winit.svr.label.RequestMessage;

public class DefaultLableHanler implements LabelHandler
{

	@Override
	public Result handle(CommandContext commandContext, RequestMessage requestMessage)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isIdempotent()
	{
		// TODO Auto-generated method stub
		return true;
	}

}
