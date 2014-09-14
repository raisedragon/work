/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package com.winit.svr.impl.cmd;

import java.io.Serializable;
import java.util.Date;

import com.winit.svr.LabelBusinessException;
import com.winit.svr.LabelSystemException;
import com.winit.svr.impl.db.PersistentObject;
import com.winit.svr.impl.interceptor.Command;
import com.winit.svr.impl.interceptor.CommandContext;
import com.winit.svr.impl.persistence.entity.LabelEntity;
import com.winit.svr.label.Label;
import com.winit.svr.label.LabelHandler;
import com.winit.svr.label.LabelHandler.Result;
import com.winit.svr.label.RequestMessage;
import com.winit.svr.label.RequestMessage.Logistics;
import com.winit.svr.label.ResponseMessage;

/**
 * @author longsheng.wang
 */
public class GenerateLabelCmd implements Command<ResponseMessage>, Serializable
{

	private static final long	serialVersionUID	= 1L;

	private RequestMessage		requestMessage;

	public GenerateLabelCmd(RequestMessage request)
	{
		checkRequestMessage(request);
		this.requestMessage = request;
	}

	public ResponseMessage execute(CommandContext commandContext)
	{
		ResponseMessage response = new ResponseMessage();
		ResponseMessage.SBODY responseBody = new ResponseMessage.SBODY();
		response.setBody(responseBody);
		String documentNo = requestMessage.getBody().getDocumentNo();
		Logistics logistics = requestMessage.getBody().getLogistics();
		
		
		responseBody.setDocumentNo(documentNo);
		
		
		try
		{
			
			
			LabelHandler labelHandler = commandContext
					.getLabelServerConfiguration()
					.getLabelHandlerFactory()
					.getHandler(logistics.getCode());

			if (labelHandler == null)
			{
				responseBody.setStatusCode(100);
				responseBody.setMessage("Label generate service specify by logistic's code " + logistics.getCode()
						+ " not found");
				return response;
			}
			Label label = commandContext.getLabelManager().findLabelByDocumentNo(documentNo);
			if (label != null)
			{
				if (requestMessage.getBody().isRequiredNew())
				{

					return response;
				}
				Result result = labelHandler.handle(commandContext, requestMessage);
				label.setFileCode(result.getBase64Code());
				label.setTrackNo(result.getTrackNo());
				label.setUpdated(new Date());
				commandContext.getLabelManager().updateLabel(label);
			}
			else
			{
				Result result = labelHandler.handle(commandContext, requestMessage);
				label = new LabelEntity();
				label.setCreated(new Date());
				label.setDocumentNo(documentNo);
				label.setFileCode(result.getBase64Code());
				label.setTrackNo(result.getTrackNo());
				label.setUpdated(new Date());
				commandContext.getLabelManager().insertLabel(label);
			}
			
			
			responseBody.setStatusCode(0);
			responseBody.setTrackNo(label.getTrackNo());
		}
		catch (LabelBusinessException exception)
		{
			responseBody.setStatusCode(200);
			responseBody.setMessage(exception.getMessage());
		}
		catch (LabelSystemException exception)
		{
			responseBody.setStatusCode(100);
			responseBody.setMessage(exception.getMessage());
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			responseBody.setStatusCode(300);
			responseBody.setMessage(exception.getMessage());
		}
		
		return response;
	}

	/**
	 * 校验消息有效性
	 * 
	 * @param request
	 */
	private void checkRequestMessage(RequestMessage request)
	{
		// TODO
	}

}
