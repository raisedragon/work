package com.winit.label.manager.impl.us.dhl;


public class BussinessAdempiereException extends RuntimeException
{
	public BussinessAdempiereException(){
		super();
	}
	public BussinessAdempiereException(String message){
		super(message);
	}
	public BussinessAdempiereException(Throwable cause){
		super(cause);
	}
	public BussinessAdempiereException(String message, Throwable cause){
		super(message, cause);
	}
}
