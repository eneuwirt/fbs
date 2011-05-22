package com.fbs.dmr.universal.test.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fbs.dmr.universal.service.impl.CrudServiceOrganizationImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-test.xml")
public class OrganizationalServiceTest
{
	@Resource
	CrudServiceOrganizationImpl crudServiceOrganizationImpl;
	
	@Before
	public void setUp()
	{
		Assert.assertNotNull(crudServiceOrganizationImpl);
	}
	
	@Test
	public void testCrud()
	{
		
	}
}
