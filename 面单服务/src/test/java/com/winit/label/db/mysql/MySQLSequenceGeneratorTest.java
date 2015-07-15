package com.winit.label.db.mysql;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/conf/spring.test.xml" })
public class MySQLSequenceGeneratorTest
{
	@Autowired
	MySQLSequenceGenerator generator;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Test
	public void testNextVal()
	{
		long val1 = generator.nextVal("wt_DeutschePostLable_seq");
		long val2 = generator.nextVal("wt_DeutschePostLable_seq");
		assertEquals(val1, val2-1);
	}

	@Test
	public void testCurrentVal()
	{
		long val1 = generator.currentVal("wt_DeutschePostLable_seq");
		long val2 = generator.currentVal("wt_DeutschePostLable_seq");
		assertEquals(val1, val2);
	}

	@Test
	public void testSetVal()
	{
		long val1 = generator.currentVal("wt_DeutschePostLable_seq");
		long val2 = generator.setVal("wt_DeutschePostLable_seq",val1+234);
		assertEquals(val1+234, val2);
		long val3 = generator.currentVal("wt_DeutschePostLable_seq");
		assertEquals(val2, val3);
	}

}
