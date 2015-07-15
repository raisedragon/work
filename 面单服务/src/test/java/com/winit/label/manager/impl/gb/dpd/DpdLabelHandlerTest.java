package com.winit.label.manager.impl.gb.dpd;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.impl.BaseLabelHandlerTestV2;

public class DpdLabelHandlerTest extends BaseLabelHandlerTestV2
{
	@Autowired
	private DpdLabelHandler labelHandler;
	

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
		return "D:/work/MyWork/面单服务/test/GB/dpd/input.xls";
	}

	@Override
	public String[] getSheets()
	{
		return new String[]{
//				"DPD_UK_001",
//				"DPD_UK_002",
//				"DPD_UK_003",
//				"DPD_UK_004",
//				"GR",
//				"ES",
				"DPD International Small Parcels"
				};
	}


}
