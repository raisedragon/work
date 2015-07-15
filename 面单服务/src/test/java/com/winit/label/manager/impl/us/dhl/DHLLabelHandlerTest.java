package com.winit.label.manager.impl.us.dhl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.impl.BaseLabelHandlerTest;
import com.winit.label.manager.impl.BaseLabelHandlerTestV2;

public class DHLLabelHandlerTest extends BaseLabelHandlerTestV2
{
	@Autowired
	private DHLLabelHandler labelHandler;
	

	@Override
	public LabelHandler getLabelHandler()
	{
		return labelHandler;
	}

	@Override
	@Test
	public void testIsIdempotent()
	{
		
	}

	@Override
	public String getInputFile()
	{
		return "D:\\work\\MyWork\\面单服务\\test\\US\\dhl\\input-1417771446630.xls";
	}

	@Override
	public String[] getSheets()
	{
		return new String[]{
//				"EU",
//				"AM",
				"AP"
				};
	}


}
