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

import java.util.List;
import java.util.Map;

import com.winit.svr.delegate.event.ActivitiEventType;
import com.winit.svr.delegate.event.impl.ActivitiEventBuilder;
import com.winit.svr.identity.User;
import com.winit.svr.impl.LabelQueryImpl;
import com.winit.svr.impl.Page;
import com.winit.svr.impl.context.Context;
import com.winit.svr.impl.db.DbSqlSession;
import com.winit.svr.impl.db.PersistentObject;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.persistence.AbstractManager;
import com.winit.svr.label.Label;
import com.winit.svr.label.LabelQuery;

/**
 * @author longsheng.wang
 */
public class LabelEnitityManager extends AbstractManager
{

	public void insertLabel(Label label)
	{
		getDbSqlSession().insert((PersistentObject) label);

		if (getProcessEngineConfiguration().getEventDispatcher().isEnabled())
		{
			getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
					ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_CREATED, label));
			getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
					ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_INITIALIZED, label));
		}
	}

	public void updateLabel(Label updatedLabel)
	{
		CommandContext commandContext = Context.getCommandContext();
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.update((LabelEntity) updatedLabel);

		if (getProcessEngineConfiguration().getEventDispatcher().isEnabled())
		{
			getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
					ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_UPDATED, updatedLabel));
		}
	}

	public void deleteLabel(String labelId)
	{
		LabelEntity label = getDbSqlSession().selectById(LabelEntity.class, labelId);

		if (label != null)
		{
			if (getProcessEngineConfiguration().getEventDispatcher().isEnabled())
			{
				getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
						ActivitiEventBuilder
								.createMembershipEvent(ActivitiEventType.MEMBERSHIPS_DELETED, labelId, null));
			}

			getDbSqlSession().delete("deleteMembershipsByLabelId", labelId);
			getDbSqlSession().delete(label);

			if (getProcessEngineConfiguration().getEventDispatcher().isEnabled())
			{
				getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(
						ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_DELETED, label));
			}
		}
	}

	public LabelQuery createNewLabelQuery()
	{
		return new LabelQueryImpl(Context.getLabelServerConfiguration().getCommandExecutor());
	}

	@SuppressWarnings("unchecked")
	public List<Label> findLabelByQueryCriteria(LabelQueryImpl query, Page page)
	{
		return getDbSqlSession().selectList("selectLabelByQueryCriteria", query, page);
	}

	public long findLabelCountByQueryCriteria(LabelQueryImpl query)
	{
		return (Long) getDbSqlSession().selectOne("selectLabelCountByQueryCriteria", query);
	}

	@SuppressWarnings("unchecked")
	public List<Label> findLabelsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults)
	{
		return getDbSqlSession().selectListWithRawParameter("selectLabelByNativeQuery", parameterMap, firstResult,
				maxResults);
	}

	public long findLabelCountByNativeQuery(Map<String, Object> parameterMap)
	{
		return (Long) getDbSqlSession().selectOne("selectLabelCountByNativeQuery", parameterMap);
	}

	public Label findLabelById(String labelId)
	{
		return (LabelEntity) getDbSqlSession().selectOne("selectLabelById", labelId);
	}

	public Label findLabelByDocumentNo(String documentNo)
	{
		return (LabelEntity) getDbSqlSession().selectOne("selectLabelByDocumentNo", documentNo);
	}

	public boolean isNewLabel(Label label)
	{
		return ((LabelEntity) label).getRevision() == 0;
	}

}
