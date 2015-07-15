package com.winit.label.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.winit.commons.mybatis.Page;
import com.winit.label.model.SysConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/conf/spring.test.xml"})
public class SysConfigServiceTest
{
	@Autowired
	SysConfigService configService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Test
	@Rollback(true)
	public void testGetValue()
	{
		fail("Not yet implemented");
	}

	@Test
	@Rollback(true)
	public void testSave()
	{
		fail("Not yet implemented");
	}

	@Test
	@Rollback(true)
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	@Test
	@Rollback(true)
	public void testRemoveString()
	{
		fail("Not yet implemented");
	}

	@Test
	@Rollback(true)
	public void testRemoveStringObject()
	{
		fail("Not yet implemented");
	}

	@Test
	@Rollback(true)
	public void testSingle()
	{
		fail("Not yet implemented");
	}

	@Test
	@Rollback(true)
	public void testList()
	{
		fail("Not yet implemented");
	}

	@Test
	@Rollback(true)
	public void testSelectCount()
	{
		fail("Not yet implemented");
	}

	@Test
	@Rollback(true)
	public void testSelectMap()
	{
		fail("Not yet implemented");
	}

	@Test
	@Rollback(true)
	public void testListByPage()
	{
		Page page = new Page(3, 5);
		List<SysConfig> configs = configService.getAllPaging(page);
		assertEquals(5, configs.size());
	}

}
