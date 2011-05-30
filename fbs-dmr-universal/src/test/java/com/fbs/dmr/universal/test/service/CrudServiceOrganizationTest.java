package com.fbs.dmr.universal.test.service;

import java.util.List;

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
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyClassification;
import com.fbs.dmr.universal.model.party.PartyRole;
import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.dmr.universal.service.CrudService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-test.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class CrudServiceOrganizationTest
{
	@Resource(name="crudServiceOrganization")
	CrudService<Organization, Integer> crudServiceOrganization;
	@Resource(name="crudServicePartyType")
	CrudService<PartyType, Integer> crudServicePartyType;
	@Resource(name="crudServicePartyRoleType")
	CrudService<PartyRoleType, Integer> crudServicePartyRoleType;
	


	@Before
	public void setUp()
	{
		Assert.assertNotNull(crudServiceOrganization);
		Assert.assertNotNull(crudServicePartyType);
	}
	
	private void createClassifications(Party party, PartyType partyType)
	{
		PartyClassification pC = new PartyClassification();
		
		pC.setParty(party);
		pC.setPartyType(partyType);
	
		
		party.getPartyClassifications().add(pC);
	}
	
	private void createRoles(Party party, PartyRoleType prt)
	{
		PartyRole pr = new PartyRole();
		
		pr.setParty(party);
		pr.setPartyRoleType(prt);
		
		party.getPartyRoles().add(pr);
	}

	@Test
	public void testCrud()
	{
		PartyType pT;
		PartyType pT2;
		Organization org;
		Organization org2;
		String name = "Test";
		String name2 = "ABCDEF";
		Integer id;
		Integer id2;
		List<Organization> organizations = null;
		PartyRoleType prt1, prt2;
		
		prt1 = new PartyRoleType();
		prt1.setDescription("Party Role Type 1");
		this.crudServicePartyRoleType.create(prt1);

		prt2 = new PartyRoleType();
		prt2.setDescription("Party Role Type 2");
		this.crudServicePartyRoleType.create(prt2);
		
		
		pT = new PartyType();
		pT.setDescription("Party Type lol");
		this.crudServicePartyType.create(pT);

		pT2 = new PartyType();
		pT2.setDescription("Party Type 2 lol");
		this.crudServicePartyType.create(pT2);
		
		
		org = new Organization();
		org.setName(name);
		this.createClassifications(org, pT);	
		this.createClassifications(org, pT2);
		this.createRoles(org, prt1);
		this.createRoles(org, prt2);
		this.crudServiceOrganization.create(org);
		Assert.assertNotNull(org.getId());
		Assert.assertEquals(org.getPartyClassifications().size(), 2);
		Assert.assertEquals(org.getPartyRoles().size(), 2);
		for (PartyClassification pc : org.getPartyClassifications())
		{
			Assert.assertNotNull(pc.getId());
		}
		for (PartyRole pr : org.getPartyRoles())
		{
			Assert.assertNotNull(pr.getId());
		}
		id = org.getId();

		org2 = new Organization();
		org2.setName(name2);
		this.crudServiceOrganization.create(org2);
		Assert.assertNotNull(org2.getId());
		id2 = org2.getId();
		

		org = this.crudServiceOrganization.read(id);
		Assert.assertNotNull(org);
		Assert.assertEquals(org.getName(), name);
		Assert.assertNotNull(org.getPartyClassifications());
		Assert.assertEquals(org.getPartyClassifications().size(), 2);
		Assert.assertEquals(org.getPartyRoles().size(), 2);
		Assert.assertEquals(org.getPartyClassifications().size(), 2);
		Assert.assertEquals(org.getPartyRoles().size(), 2);
		for (PartyClassification pc : org.getPartyClassifications())
		{
			Assert.assertNotNull(pc.getId());
		}
		for (PartyRole pr : org.getPartyRoles())
		{
			Assert.assertNotNull(pr.getId());
		}

		org.setName(name2);
		this.crudServiceOrganization.update(org);
		org = this.crudServiceOrganization.read(id);
		Assert.assertNotNull(org);
		Assert.assertNotNull(org.getId());
		Assert.assertEquals(org.getId(), id);
		Assert.assertEquals(org.getName(), name2);
		
		organizations = this.crudServiceOrganization.findAll();
		Assert.assertNotNull(organizations);
		Assert.assertEquals(organizations.size(), 2);
		
		this.crudServiceOrganization.delete(id);
		org = this.crudServiceOrganization.read(id);
		Assert.assertNull(org);

		org2 = this.crudServiceOrganization.read(id2);
		Assert.assertNotNull(org2);
		Assert.assertEquals(org2.getName(), name2);
	}
}
