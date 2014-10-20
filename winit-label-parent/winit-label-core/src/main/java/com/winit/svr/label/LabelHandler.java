package com.winit.svr.label;

import com.winit.label.model.RequestMessage;
import com.winit.svr.impl.interceptor.CommandContext;

public interface LabelHandler
{
	Result handle(CommandContext commandContext,RequestMessage requestMessage);
	
	boolean isIdempotent();
	
	
	public static class Result{
		
		protected String base64Code;
		protected String trackNo;
		
		
		
		public Result(String base64Code, String trackNo)
		{
			super();
			this.base64Code = base64Code;
			this.trackNo = trackNo;
		}
		
		public String getBase64Code()
		{
			return base64Code;
		}
		public void setBase64Code(String base64Code)
		{
			this.base64Code = base64Code;
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
