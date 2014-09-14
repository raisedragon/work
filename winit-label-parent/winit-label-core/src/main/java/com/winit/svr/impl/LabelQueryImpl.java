/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package com.winit.svr.impl;

import java.util.List;

import com.winit.svr.LabelIllegalArgumentException;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.interceptor.CommandExecutor;
import com.winit.svr.label.Label;
import com.winit.svr.label.Label.StatusType;
import com.winit.svr.label.LabelQuery;

/**
 * @author longsheng.wang
 *
 */
public class LabelQueryImpl extends AbstractQuery<LabelQuery, Label> implements LabelQuery
{

	private static final long	serialVersionUID	= 1L;
	protected String			id;
	protected String			documentNo;
	protected String			trackNo;
	protected StatusType		status;

	public LabelQueryImpl()
	{
	}

	public LabelQueryImpl(CommandContext commandContext)
	{
		super(commandContext);
	}

	public LabelQueryImpl(CommandExecutor commandExecutor)
	{
		super(commandExecutor);
	}

	public LabelQuery labelId(String id)
	{
		if (id == null)
		{
			throw new LabelIllegalArgumentException("Provided id is null");
		}
		this.id = id;
		return this;
	}
	
	public LabelQuery documentNo(String documentNo)
	{
		if (documentNo == null)
		{
			throw new LabelIllegalArgumentException("Provided documentNo is null");
		}
		this.documentNo = documentNo;
		return this;
	}

	
	public LabelQuery trackNo(String trackNo)
	{
		if (trackNo == null)
		{
			throw new LabelIllegalArgumentException("Provided trackNo is null");
		}
		this.trackNo = trackNo;
		return this;
	}

	public LabelQuery status(StatusType status)
	{
		if (status == null)
		{
			throw new LabelIllegalArgumentException("Provided status is null");
		}
		this.status = status;
		return this;
	}
	

	// sorting ////////////////////////////////////////////////////////

	public LabelQuery orderByLabelId()
	{
		return orderBy(LabelQueryProperty.LABEL_ID);
	}

	public LabelQuery orderByLabelQueryDocumentNo()
	{
		return orderBy(LabelQueryProperty.DOCUMENTNO);
	}
	
	public LabelQuery orderByLabelQueryTrackNo()
	{
		return orderBy(LabelQueryProperty.TRACKNO);
	}

	// results ////////////////////////////////////////////////////////

	public long executeCount(CommandContext commandContext)
	{
		checkQueryOk();
		return commandContext.getLabelManager().findLabelCountByQueryCriteria(this);
	}

	public List<Label> executeList(CommandContext commandContext, Page page)
	{
		checkQueryOk();
		return commandContext.getLabelManager().findLabelByQueryCriteria(this, page);
	}


	// getters ////////////////////////////////////////////////////////
	public String getId()
	{
		return id;
	}

	public String getDocumentNo()
	{
		return documentNo;
	}

	public String getTrackNo()
	{
		return trackNo;
	}

	public StatusType getStatus()
	{
		return status;
	}




}
