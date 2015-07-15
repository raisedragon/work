package com.winit.label.model;

import java.io.Serializable;

public class ResponseMessage implements Serializable
{
	private static final long	serialVersionUID	= -4028389572368811168L;

	private String				documentNo;
	private String				trackingNo;
	private String				filePath;
	private String				message;
	private int					statusCode;

	public ResponseMessage()
	{
	}

	public ResponseMessage(String documentNo, int statusCode, String message)
	{
		this.documentNo = documentNo;
		this.statusCode = statusCode;
		this.message = message;
	}

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

	public String getTrackingNo()
	{
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo)
	{
		this.trackingNo = trackingNo;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

}
