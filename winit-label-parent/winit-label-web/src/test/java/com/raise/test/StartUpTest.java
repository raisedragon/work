package com.raise.test;

import org.activiti.engine.impl.test.TestHelper;
import org.junit.Test;

import com.winit.svr.IdentityService;
import com.winit.svr.LabelServer;

import static org.junit.Assert.*;

public class StartUpTest {
	@Test
	public void testStartUp(){
		String configurationResource = "server.cfg.xml";
		LabelServer labelServer = TestHelper.getLabelServer(configurationResource);
		assertNotNull(labelServer);
		IdentityService identityService = labelServer.getIdentityService();
		assertNotNull(identityService);
	}
}
