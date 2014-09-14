package com.raise.commons.id;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raise.commons.id.impl.DefaultIdGeneratorImpl;

public class IdGeneratorTest  {
	Logger LOG = LoggerFactory.getLogger(getClass());
	int workerId = 1;
	int datacenterId = 1;
	IdGenerator idGenerator = null;
	@Before
	public void setUp() throws Exception {
		idGenerator = new DefaultIdGeneratorImpl(workerId, datacenterId);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNextId() {
		Set<Long> ids = new HashSet<Long>();
		for(int i=0;i<1000;i++){
			ids.add(idGenerator.nextId());
		}
		assertTrue(ids.size()==1000);
		for(long id:ids){
			LOG.info(""+id);
		}
	}

}
