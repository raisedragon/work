

package com.winit.svr.impl.persistence.manager;

import org.apache.commons.lang.StringUtils;

import com.winit.svr.delegate.event.ActivitiEventType;
import com.winit.svr.delegate.event.impl.ActivitiEventBuilder;
import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.db.DbSqlSession;
import com.winit.svr.impl.db.PersistentObject;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.persistence.AbstractManager;
import com.winit.svr.impl.persistence.entity.TreeEntity;
import com.winit.svr.persistence.model.Tree;


public class TreeEntityManager extends AbstractManager {


  public void insertTree(Tree tree) {
    getDbSqlSession().insert((PersistentObject) tree);
    
    if(getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
    	getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
    			ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_CREATED, tree));
    	getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
    			ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_INITIALIZED, tree));
    }
  }

  public void updateTree(Tree updatedTree) {
    CommandContext commandContext = Context.getCommandContext();
    DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
    dbSqlSession.update((TreeEntity) updatedTree);
    
    if(getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
    	getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
    			ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_UPDATED, updatedTree));
    }
  }

  public void deleteTree(String treeId) {
    TreeEntity tree = getDbSqlSession().selectById(TreeEntity.class, treeId);
    
    if(tree != null) {
    	if(getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
      	getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
      			ActivitiEventBuilder.createMembershipEvent(ActivitiEventType.MEMBERSHIPS_DELETED, treeId, null));
      }
    	
    	getDbSqlSession().delete("deleteMembershipsByTreeId", treeId);
    	getDbSqlSession().delete(tree);
    	
    	if(getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
    		getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
    				ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_DELETED, tree));
    	}
    }
  }
  
  
/**
 * 修正Tree对象数据
 * @param tree
 */
protected void adjust(TreeEntity<?> tree){
	  String path = "/"+tree.getId();
	  if(StringUtils.isNotEmpty(tree.getParentId())){
		  TreeEntity<?> entity = this.select(tree.getClass(), tree.getParentId());
		  if(entity!=null){
			  path = entity.getPath()+path;
		  }
	  }
	  tree.setPath(path);
  }
  
}
