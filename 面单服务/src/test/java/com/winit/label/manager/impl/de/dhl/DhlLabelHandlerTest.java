package com.winit.label.manager.impl.de.dhl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.impl.BaseLabelHandlerTestV2;

public class DhlLabelHandlerTest extends BaseLabelHandlerTestV2
{
	@Autowired
	private DhlLabelHandler labelHandler;
	

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
		return "D:/work/MyWork/面单服务/test/DE/dhl/dhl-input.xls";
	}

	@Override
	public String[] getSheets()
	{
		return new String[]{
//				"EU",
//				"AM",
//				"Sheet2",
				"DHL001"
				};
	}


}
