package com.winit.label.manager.impl.de.dpd;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.impl.BaseLabelHandlerTest;
import com.winit.label.manager.impl.BaseLabelHandlerTestV2;

public class DpdLabelHandlerTest extends BaseLabelHandlerTestV2 {
	@Autowired
	private DpdLabelHandler labelHandler;

	@Override
	public LabelHandler getLabelHandler() {
		return labelHandler;
	}

	@Override
	@Test
	public void testIsIdempotent() {

	}

	@Override
	public String getInputFile() {
		return "D:/work/MyWork/面单服务/test/DE/dpd/input.xls";
	}

	@Override
	public String[] getSheets() {
		return new String[] { 
//				"DPD0003",
//				"DPD0001",
//				"DPD0005",
				"DPD0004",
//				"DPD0006"
				};
	}

}
