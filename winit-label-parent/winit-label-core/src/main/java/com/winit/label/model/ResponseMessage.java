package com.winit.label.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResponseMessage implements Serializable
{
	private static final long	serialVersionUID	= -4028389572368811168L;
	
	//消息头
	private SHEAD head;
	//消息体
	private SBODY body;
	//变量
	private Map<String,Object> variables= new HashMap<String, Object>();

	public SHEAD getHead()
	{
		return head;
	}

	public void setHead(SHEAD head)
	{
		this.head = head;
	}

	public SBODY getBody()
	{
		return body;
	}

	public void setBody(SBODY body)
	{
		this.body = body;
	}
	
	public Map<String, Object> getVariables()
	{
		return variables;
	}

	public void setVariables(Map<String, Object> variables)
	{
		this.variables = variables;
	}




	
	
	
	public static class SHEAD{
		
	}
	
	public static class SBODY{
		
		private String documentNo;
		private int statusCode;
		private String message;
		private String trackNo;
		
		public String getDocumentNo()
		{
			return documentNo;
		}
		public void setDocumentNo(String documentNo)
		{
			this.documentNo = documentNo;
		}
		public int getStatusCode()
		{
			return statusCode;
		}
		public void setStatusCode(int statusCode)
		{
			this.statusCode = statusCode;
		}
		public String getMessage()
		{
			return message;
		}
		public void setMessage(String message)
		{
			this.message = message;
		}
		public String getTrackNo()
		{
			return trackNo;
		}
		public void setTrackNo(String trackNo)
		{
			this.trackNo = trackNo;
		}
	}
}
