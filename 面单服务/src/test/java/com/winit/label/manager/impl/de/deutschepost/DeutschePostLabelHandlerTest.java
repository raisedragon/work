package com.winit.label.manager.impl.de.deutschepost;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.impl.BaseLabelHandlerTestV2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/conf/spring.test.xml" })
public class DeutschePostLabelHandlerTest   extends BaseLabelHandlerTestV2
{

	@Autowired(required = true)
	private DeutschePostLabelHandler	labelHandler;
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
				"Sheet2"
				};
	}
}
