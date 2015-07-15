package com.winit.exception;

public class LabelException extends RuntimeException {

	private static final long serialVersionUID = 8840698565383508722L;

	private Object[] params = null;
	
	private int errorCode = 0;
	
	public LabelException(String message) {
		super(message);
	}
	
	public LabelException(Exception e) {
		super(e);
	}


	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
