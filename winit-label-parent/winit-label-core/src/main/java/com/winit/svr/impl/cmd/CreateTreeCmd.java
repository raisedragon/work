package com.winit.svr.impl.cmd;

import java.io.Serializable;

import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.persistence.model.Tree;

public class CreateTreeCmd  implements Command<Tree>, Serializable 
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Override
	public Tree execute(CommandContext commandContext)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
