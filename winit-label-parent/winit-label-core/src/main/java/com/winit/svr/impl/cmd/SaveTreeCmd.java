package com.winit.svr.impl.cmd;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.persistence.entity.TreeEntity;
import com.winit.svr.impl.persistence.manager.TreeEntityManager;
import com.winit.svr.persistence.model.Tree;

public class SaveTreeCmd  implements Command<Void>, Serializable 
{
	protected TreeEntity<?> tree;
	
	public SaveTreeCmd(Tree tree)
	{
		this.tree = (TreeEntity<?>) tree;
	}

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Override
	public Void execute(CommandContext commandContext)
	{
		tree.save();
		return null;
	}

}
