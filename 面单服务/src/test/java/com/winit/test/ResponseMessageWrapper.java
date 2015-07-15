package com.winit.test;

import com.winit.label.model.RequestMessage;
import com.winit.label.model.ResponseMessage;

public class ResponseMessageWrapper {
	private String sheetname;
	private int rownum;
	private ResponseMessage responseMessage;
	
	
	
	public ResponseMessageWrapper(String sheetname, int rownum, 
			ResponseMessage responseMessage) {
		super();
		this.sheetname = sheetname;
		this.rownum = rownum;
		this.responseMessage = responseMessage;
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
	public ResponseMessage getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(ResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}

	
	
}
