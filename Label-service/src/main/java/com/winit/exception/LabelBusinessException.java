
package com.winit.exception;

/**
 * @author longsheng.wang
 *
 */
public class LabelBusinessException extends RuntimeException
{

	private static final long	serialVersionUID	= 1L;

	public LabelBusinessException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public LabelBusinessException(Throwable cause)
	{
		super(cause);
	}

	public LabelBusinessException(String message)
	{
		super(message);
	}
}
