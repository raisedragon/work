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
		protected String consignmentRef1;
		protected String consignmentRef2;
		
		
		public Result(String base64Code, String trackNo)
		{
			super();
			this.base64Code = base64Code;
			this.trackNo = trackNo;
		}
		

		public Result(String base64Code, String trackNo,String consignmentRef1,String consignmentRef2)
		{
			super();
			this.base64Code = base64Code;
			this.trackNo = trackNo;
			this.consignmentRef1 = consignmentRef1;
			this.consignmentRef2 = consignmentRef2;
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


		public String getConsignmentRef1()
		{
			return consignmentRef1;
		}


		public void setConsignmentRef1(String consignmentRef1)
		{
			this.consignmentRef1 = consignmentRef1;
		}


		public String getConsignmentRef2()
		{
			return consignmentRef2;
		}


		public void setConsignmentRef2(String consignmentRef2)
		{
			this.consignmentRef2 = consignmentRef2;
		}
		
		
		
	}
}
