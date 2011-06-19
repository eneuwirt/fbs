package com.fbs.dmr.universal.test.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.fbs.dmr.universal.service.SeedService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-test.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class SeedServiceTest
{
	@Resource(name = "seedService")
	SeedService seedService;
	
	@Before
	public void setUp()
	{
		Assert.assertNotNull(seedService);
	}
	
	@Test
	public void testCrud()
	{
		this.seedService.defaultFill();
		
		this.seedService.demoFill();
	}
}
