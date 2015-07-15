package com.winit.label.manager.impl.au.ubi;

import static org.junit.Assert.*;

import java.net.URLDecoder;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.winit.label.manager.LabelHandler;
import com.winit.label.manager.LabelHandler.Result;
import com.winit.label.manager.impl.BaseLabelHandlerTest;
import com.winit.label.manager.impl.BaseLabelHandlerTestV2;
import com.winit.label.manager.impl.de.dpd.DpdLabelHandler;
import com.winit.label.model.DeliveryWay;
import com.winit.label.model.RequestMessage;
import com.winit.label.service.DeliveryWayService;
import com.winit.test.RequestMessageUtils;

public class UbiLabelHandlerTest extends BaseLabelHandlerTestV2
{
	@Autowired
	private UbiLabelHandler labelHandler;
	

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
		return "D:\\work\\MyWork\\面单服务\\test\\AU\\ubi\\input.xls";
	}

	@Override
	public String[] getSheets()
	{
		return new String[]{
				"AU0012",
//				"AU0014",
//				"AU0024",
//				"AU0016",
//				"AU0020",
//				"AU0026",
//				"AU0018",
//				"AU0022"
				};
	}

}
