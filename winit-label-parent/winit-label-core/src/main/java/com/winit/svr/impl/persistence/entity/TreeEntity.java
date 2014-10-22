package com.winit.svr.impl.persistence.entity;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.db.HasRevision;
import com.winit.svr.impl.db.PersistentObject;
import com.winit.svr.impl.persistence.manager.TreeEntityManager;
import com.winit.svr.persistence.model.Tree;

public class TreeEntity<T> implements Tree , Serializable, PersistentObject, HasRevision
{
	static enum ReferenceType{
		GROUP,
		MENU
	}
	
	private String	id;
	private String	referenceId;
	private ReferenceType 	referenceType;
	private String	lable;
	private String	parentId;
	private String	path;
	private boolean	isLeaf;
	private List<TreeEntity<T>> children;
	private T referenceEntity;
	
	public void save(){
		TreeEntityManager entityManager = Context.getCommandContext().getSession(TreeEntityManager.class);
		if(StringUtils.isNotEmpty(this.getId())){
			entityManager.insert(this);
		}else{
			entityManager.updateTree(this);
		}
	}
	
	public void delete(){
		TreeEntityManager entityManager = Context.getCommandContext().getSession(TreeEntityManager.class);
		entityManager.delete(this);
	}
	
	public static <E extends PersistentObject> TreeEntity<E> get(String id,Class<E> clazz){
		TreeEntityManager entityManager = Context.getCommandContext().getSession(TreeEntityManager.class);
		return entityManager.select(TreeEntity.class, id);
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
	 * @see com.winit.svr.impl.persistence.entity.Tree#getReferenceId()
	 */
	@Override
	public String getReferenceId()
	{
		return referenceId;
	}

	/* (non-Javadoc)
	 * @see com.winit.svr.impl.persistence.entity.Tree#setReference(java.lang.String)
	 */
	@Override
	public void setReferenceId(String referenceId)
	{
		this.referenceId = referenceId;
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

	@Override
	public void setRevision(int revision)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getRevision()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRevisionNext()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getPersistentState()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public ReferenceType getReferenceType()
	{
		return referenceType;
	}

	public void setReferenceType(ReferenceType referenceType)
	{
		this.referenceType = referenceType;
	}
	
	

}
