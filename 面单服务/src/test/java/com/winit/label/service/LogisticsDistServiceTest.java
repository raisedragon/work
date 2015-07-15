package com.winit.label.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.winit.label.model.LogisticsDist;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/conf/spring.test.xml" })
public class LogisticsDistServiceTest
{
	
	@Autowired
	protected LogisticsDistService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Test
	public void testGetByDeliveryWayId()
	{
		
		List<LogisticsDist> dists = service.getByDeliveryWayId(53L, null);
		assertEquals(dists.size(),4);
		
		dists = service.getByDeliveryWayId(1L, null);
		assertEquals(dists.size(),0);
	}

}
