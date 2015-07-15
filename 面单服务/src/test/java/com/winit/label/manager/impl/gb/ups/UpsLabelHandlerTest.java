package com.winit.label.manager.impl.gb.ups;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.impl.BaseLabelHandlerTest;
import com.winit.label.manager.impl.gb.ups.UpsLabelHandler;

public class UpsLabelHandlerTest extends BaseLabelHandlerTest
{
	@Autowired
	private UpsLabelHandler labelHandler;
	

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
		return "D:\\work\\MyWork\\面单服务\\test\\GB\\ups\\input.xls";
	}

	@Override
	public String[] getSheets()
	{
		return new String[]{
				"UPSFS"
				};
	}

	@Override
	public String getOutputFile()
	{
		return "D:\\work\\MyWork\\面单服务\\test\\GB\\ups\\output.xls";
	}

}
