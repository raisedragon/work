/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.winit.svr.impl.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.db.HasRevision;
import com.winit.svr.impl.db.PersistentObject;
import com.winit.svr.label.Label;
import com.winit.svr.label.LogisticType;

/**
 * @author longsheng.wang
 */
public class LabelEntity implements Label, Serializable, PersistentObject, HasRevision
{
	private static final long	serialVersionUID	= 3867682296218865840L;

	protected String	id;
	protected int		revision;
	protected String documentNo;
	protected String trackNo;
	protected String fileCode;
	protected StatusType status;
	protected Date created;
	protected Date updated;

	@Override
	public void setRevision(int revision)
	{
		this.revision = revision;
	}

	@Override
	public int getRevision()
	{
		return this.revision;
	}

	@Override
	public int getRevisionNext()
	{
		return revision + 1;
	}

	@Override
	public Object getPersistentState()
	{
		Map<String, Object> persistentState = new HashMap<String, Object>();
		persistentState.put("documentNo", documentNo);
		persistentState.put("status", status);
		return persistentState;
	}

	@Override
	public String getId()
	{
		return this.id;
	}

	@Override
	public void setId(String id)
	{
		this.id = id;
	}

	@Override
	public String getDocumentNo()
	{
		return this.documentNo;
	}

	@Override
	public void setDocumentNo(String documentNo)
	{
		this.documentNo = documentNo;

	}

	@Override
	public String getTrackNo()
	{
		return this.trackNo;
	}

	@Override
	public void setTrackNo(String trackNo)
	{
		this.trackNo =  trackNo;
	}

	@Override
	public String getFileCode()
	{
		return fileCode;
	}

	@Override
	public void setFileCode(String fileCode)
	{
		this.fileCode = fileCode;

	}

	@Override
	public Date getCreated()
	{
		return this.created;
	}

	@Override
	public void setCreated(Date created)
	{
		this.created = created;

	}

	@Override
	public Date getUpdated()
	{
		return updated;
	}

	@Override
	public void setUpdated(Date updated)
	{
		this.updated = updated;

	}

	@Override
	public StatusType getStatus()
	{
		return this.status;
	}

	@Override
	public void setStatus(StatusType status)
	{
		this.status = status;
	}

}
