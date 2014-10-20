package com.winit.svr.impl.persistence.entity;

import java.util.List;

import com.winit.svr.persistence.model.Tree;

public class TreeEntity<T> implements Tree
{
	private String	id;
	private String	reference;
	private String	lable;
	private String	parentId;
	private String	path;
	private boolean	isLeaf;
	private List<TreeEntity<T>> children;
	private T referenceEntity;
	
	public void save(TreeEntity<T> tree){
		//TODO
	}
	
	public boolean delete(String id){
		//TODO
		return false;
	}
	
	public TreeEntity<T> update(TreeEntity<T> tree){
		//TODO
		return tree;
	}
	
	public TreeEntity<T> get(String id){
		//TODO
		return null;
	}
	
	///Getter and Setter

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.Tree#getId()
	 */
	@Override
	public String getId()
	{
		return id;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.Tree#setId(java.lang.String)
	 */
	@Override
	public void setId(String id)
	{
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.Tree#getReference()
	 */
	@Override
	public String getReference()
	{
		return reference;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.Tree#setReference(java.lang.String)
	 */
	@Override
	public void setReference(String reference)
	{
		this.reference = reference;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.Tree#getLable()
	 */
	@Override
	public String getLable()
	{
		return lable;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.Tree#setLable(java.lang.String)
	 */
	@Override
	public void setLable(String lable)
	{
		this.lable = lable;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.Tree#getParentId()
	 */
	@Override
	public String getParentId()
	{
		return parentId;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.Tree#setParentId(java.lang.String)
	 */
	@Override
	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.Tree#isLeaf()
	 */
	@Override
	public boolean isLeaf()
	{
		return isLeaf;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.Tree#setLeaf(boolean)
	 */
	@Override
	public void setLeaf(boolean isLeaf)
	{
		this.isLeaf = isLeaf;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.Tree#getPath()
	 */
	@Override
	public String getPath()
	{
		return path;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.Tree#setPath(java.lang.String)
	 */
	@Override
	public void setPath(String path)
	{
		this.path = path;
	}

	public List<TreeEntity<T>> getChildren()
	{
		return children;
	}

	public void setChildren(List<TreeEntity<T>> children)
	{
		this.children = children;
	}

	public T getReferenceEntity()
	{
		return referenceEntity;
	}

	public void setReferenceEntity(T referenceEntity)
	{
		this.referenceEntity = referenceEntity;
	}
	
	

}
