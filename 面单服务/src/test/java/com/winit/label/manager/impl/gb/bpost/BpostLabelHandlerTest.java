package com.winit.label.manager.impl.gb.bpost;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.impl.BaseLabelHandlerTest;
import com.winit.label.manager.impl.gb.ups.UpsLabelHandler;

public class BpostLabelHandlerTest extends BaseLabelHandlerTest
{
	@Autowired
	private BpostLabelHandler labelHandler;
	

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
		return "D:\\work\\MyWork\\面单服务\\test\\GB\\bpost\\input.xls";
	}

	@Override
	public String[] getSheets()
	{
		return new String[]{
				"BP0002",
				"BP0004",
				"BP0006",
				"BP00018",
				"BP0008",
				"BP0010",
				"BP0012",
				"BP0014"
				};
	}

	@Override
	public String getOutputFile()
	{
		return "D:\\work\\MyWork\\面单服务\\test\\GB\\bpost\\output.xls";
	}

}
