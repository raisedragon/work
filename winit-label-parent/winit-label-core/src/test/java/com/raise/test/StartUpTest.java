package com.raise.test;

import java.util.List;

import org.activiti.engine.impl.test.TestHelper;
import org.junit.Test;

import com.winit.svr.IdentityService;
import com.winit.svr.LabelServer;
import com.winit.svr.LabelService;
import com.winit.svr.impl.persistence.entity.LabelEntity;
import com.winit.svr.impl.persistence.entity.LogisticTypeEntity;
import com.winit.svr.label.Label;
import com.winit.svr.label.LogisticType;

import static org.junit.Assert.*;

public class StartUpTest {
	@Test
	public void testStartUp(){
		String configurationResource = "server.cfg.xml";
		LabelServer labelServer = TestHelper.getLabelServer(configurationResource);
		assertNotNull(labelServer);
		IdentityService identityService = labelServer.getIdentityService();
		assertNotNull(identityService);
		LabelService labelService = labelServer.getLabelService();
		assertNotNull(labelService);
		
		Label label = new LabelEntity();
		labelService.saveLabel(label);
		
		LogisticType logisticType = new LogisticTypeEntity();
		labelService.saveLogisticType(logisticType);
		
		List<LogisticType> logisticTypes = labelService.createLogisticTypeQuery().list();
		assertTrue(logisticTypes.size()==1);
		
		System.out.println(logisticTypes.get(0).getId());
	}
}
