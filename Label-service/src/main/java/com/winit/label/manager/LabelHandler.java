package com.winit.label.manager;

import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;

public interface LabelHandler
{
	/**
	 * 处理面单生成
	 * @param requestMessage
	 * @param deliveryWay
	 * @return
	 * @throws Exception
	 */
	Result handle(RequestMessage requestMessage,DeliveryWay deliveryWay) throws Exception;
	
	/**
	 * 对已处理的面单，是否可再次处理
	 * @return
	 */
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
