package com.winit.test;

import com.winit.label.model.RequestMessage;

public class RequestMessageWrapper {
	private String sheetname;
	private int rownum;
	private RequestMessage requestMessage;
	
	
	
	public RequestMessageWrapper(String sheetname, int rownum, 
			RequestMessage requestMessage) {
		super();
		this.sheetname = sheetname;
		this.rownum = rownum;
		this.requestMessage = requestMessage;
	}
	public String getSheetname() {
		return sheetname;
	}
	public void setSheetname(String sheetname) {
		this.sheetname = sheetname;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public RequestMessage getRequestMessage() {
		return requestMessage;
	}
	public void setRequestMessage(RequestMessage requestMessage) {
		this.requestMessage = requestMessage;
	}
	
	
}
