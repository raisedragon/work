package com.winit.label.manager.impl.us.ups;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.impl.BaseLabelHandlerTestV2;
import com.winit.label.manager.impl.us.dhl.DHLLabelHandler;


@RunWith(SpringJUnit4ClassRunner.class)
public class UpsLabelHandlerTest extends BaseLabelHandlerTestV2
{
	
	@Autowired
	private UpsLabelHandler labelHandler;
	

	@Override
	public LabelHandler getLabelHandler()
	{
		return labelHandler;
	}

	@Test
	public void testIsIdempotent()
	{
		
	}

	@Override
	public String getInputFile()
	{
		return "D:/work/MyWork/面单服务/test/US/ups/input.xls";
	}

	@Override
	public String[] getSheets()
	{
		return new String[]{
				"AM"
				};
	}


}
