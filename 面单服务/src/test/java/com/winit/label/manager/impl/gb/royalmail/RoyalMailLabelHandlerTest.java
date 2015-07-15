package com.winit.label.manager.impl.gb.royalmail;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.impl.BaseLabelHandlerTest;
import com.winit.label.manager.impl.BaseLabelHandlerTestV2;
import com.winit.label.manager.impl.gb.royalmail.RoyalMailLabelHandler;
import com.winit.label.manager.impl.gb.ups.UpsLabelHandler;

public class RoyalMailLabelHandlerTest extends BaseLabelHandlerTestV2
{
	@Autowired
	private RoyalMailLabelHandler labelHandler;
	

	@Override
	public LabelHandler getLabelHandler()
	{
		return labelHandler;
	}

	@Override
	@Test
	public void testIsIdempotent()
	{
		ClassPathResource rs =new ClassPathResource("");
	}

	@Override
	public String getInputFile()
	{
		return "D:\\work\\MyWork\\面单服务\\test\\GB\\royalmail\\input.xls";
	}

	@Override
	public String[] getSheets()
	{
		return new String[]{
//				"RM0002",
//				"RM0004",
//				"RM0006",
				"RM0008",
//				"RM0010",
//				"RM0020"
				};
	}

//	@Override
//	public String getOutputFile()
//	{
//		return "D:\\work\\MyWork\\面单服务\\test\\GB\\royalmail\\output.xls";
//	}

}
