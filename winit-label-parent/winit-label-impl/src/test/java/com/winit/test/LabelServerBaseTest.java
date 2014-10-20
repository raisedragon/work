package com.winit.test;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.junit.BeforeClass;

import com.winit.svr.LabelServer;
import com.winit.svr.LabelService;
import com.winit.svr.PropertyService;

public class LabelServerBaseTest
{
	protected static LabelServer	labelServer;
	protected static LabelService	labelService;
	
	protected static PropertyService propertyService;

	@BeforeClass
	public static void setUp()
	{
		String configurationResource = "server.cfg.xml";
		labelServer = TestHelper.getLabelServer(configurationResource);
		labelService = labelServer.getLabelService();
		propertyService = labelServer.getPropertyService();
//		initLogisticType();
	}


	protected void writeFile(String filename,String code) throws IOException{
		FileOutputStream fos = new FileOutputStream(filename);
		byte[] bs = Base64.decodeBase64(code);
		fos.write(bs);
		fos.close();
		Desktop.getDesktop().open(new File(filename));
	}
}
