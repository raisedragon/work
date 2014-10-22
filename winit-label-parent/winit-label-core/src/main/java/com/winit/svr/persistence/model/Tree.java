package com.winit.svr.persistence.model;

import com.winit.svr.impl.db.PersistentObject;

public interface Tree
{

	public abstract String getId();

	public abstract void setId(String id);

	public abstract String getReferenceId();

	public abstract void setReferenceId(String referenceId);

	public abstract String getLable();

	public abstract void setLable(String lable);

	public abstract String getParentId();

	public abstract void setParentId(String parentId);

	public abstract boolean isLeaf();

	public abstract void setLeaf(boolean isLeaf);

	public abstract String getPath();

	public abstract void setPath(String path);

}