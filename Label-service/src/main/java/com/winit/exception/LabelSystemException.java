package com.winit.exception;

/**
 * @author longsheng.wang
 *
 */
public class LabelSystemException extends RuntimeException
{

	private static final long	serialVersionUID	= 1L;

	public LabelSystemException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public LabelSystemException(Throwable cause)
	{
		super(cause);
	}

	public LabelSystemException(String message)
	{
		super(message);
	}
}
