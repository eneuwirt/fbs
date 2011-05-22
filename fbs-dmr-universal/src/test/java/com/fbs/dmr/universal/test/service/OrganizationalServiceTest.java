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

import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.dmr.universal.service.impl.CrudServiceOrganizationImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-test.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
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
		Organization org;
		Organization org2;
		String name = "Test";
		String name2 = "ABCDEF";
		Integer id;
		Integer id2;

		org = new Organization();
		org.setName(name);
		org = this.crudServiceOrganizationImpl.create(org);
		Assert.assertNotNull(org.getId());
		id = org.getId();

		org2 = new Organization();
		org2.setName(name2);
		org2 = this.crudServiceOrganizationImpl.create(org2);
		Assert.assertNotNull(org2.getId());
		id2 = org2.getId();

		org = this.crudServiceOrganizationImpl.read(1);
		Assert.assertNotNull(org);
		Assert.assertEquals(org.getName(), name);

		org.setName(name2);
		this.crudServiceOrganizationImpl.update(org);
		org = this.crudServiceOrganizationImpl.read(id);
		Assert.assertNotNull(org);
		Assert.assertNotNull(org.getId());
		Assert.assertEquals(org.getId(), id);
		Assert.assertEquals(org.getName(), name2);

		this.crudServiceOrganizationImpl.delete(id);
		org = this.crudServiceOrganizationImpl.read(id);
		Assert.assertNull(org);

		org2 = this.crudServiceOrganizationImpl.read(id2);
		Assert.assertNotNull(org2);
		Assert.assertEquals(org2.getName(), name2);
	}
}
