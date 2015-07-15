package com.winit.label.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.winit.label.model.PostcodeGroup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/conf/spring.test.xml" })
public class PostcodeGroupServiceTest
{
	@Autowired
	private PostcodeGroupService	service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Test
	public void testGetByLogisticsDistId()
	{
		List<PostcodeGroup> dists = service.getByLogisticsDistId(1l, null);
		assertEquals(dists.size(), 1);

		dists = service.getByLogisticsDistId(5l, null);
		assertEquals(dists.size(), 0);
	}

}
