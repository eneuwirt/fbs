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

import com.fbs.dmr.universal.model.party.PartyRelationship;
import com.fbs.dmr.universal.model.party.PartyRelationshipType;
import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.service.CrudService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-test.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class CrudServicePartyRelationshipTest
{
	@Resource(name="crudServicePartyRelationship")
	CrudService<PartyRelationship, Integer> crudServicePartyRelationship;
	@Resource(name="crudServicePartyRelationshipType")
	CrudService<PartyRelationshipType, Integer> crudServicePartyRelationshipType;
	@Resource(name="crudServicePartyRoleType")
	CrudService<PartyRoleType, Integer> crudServicePartyRoleType;
	
	@Before
	public void setUp()
	{
		Assert.assertNotNull(crudServicePartyRelationship);
		Assert.assertNotNull(crudServicePartyRelationshipType);
	}
	
	@Test
	public void testCrud()
	{
		PartyRelationshipType partyRelationshipType, partyRelationshipType2;
		String prtName1 = "Prt 1 Name";
		String prtName2 = "Prt 2 Name";
		
		PartyRelationship partyRelationship1;
		PartyRoleType partyRoleType1, partyRoleType2;
		
		partyRoleType1 = new PartyRoleType();
		partyRoleType1.setDescription("Party Role Type 1");
		this.crudServicePartyRoleType.create(partyRoleType1);

		partyRoleType2 = new PartyRoleType();
		partyRoleType2.setDescription("Party Role Type 2");
		this.crudServicePartyRoleType.create(partyRoleType2);
		
		
		partyRelationshipType = new PartyRelationshipType();	
		partyRelationshipType.setName(prtName1);
		partyRelationshipType.setPartyRoleTypeFrom(partyRoleType1);
		partyRelationshipType.setPartyRoleTypeTo(partyRoleType2);
		this.crudServicePartyRelationshipType.create(partyRelationshipType);
		Assert.assertNotNull(partyRelationshipType.getId());
		
		partyRelationshipType2 = new PartyRelationshipType();
		partyRelationshipType2.setName(prtName2);
		this.crudServicePartyRelationshipType.create(partyRelationshipType2);
		
		
		partyRelationship1 = new PartyRelationship();
		partyRelationship1.setPartyRelationshipType(partyRelationshipType);
		//this.crudServicePartyRelationship.create(partyRelationship1);
		//Assert.assertNotNull(partyRelationship1.getId());
	}
}
