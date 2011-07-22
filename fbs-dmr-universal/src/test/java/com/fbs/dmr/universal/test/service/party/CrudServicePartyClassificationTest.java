package com.fbs.dmr.universal.test.service.party;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.fbs.dmr.universal.model.party.PartyClassification;
import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.dmr.universal.service.crud.CrudService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-test.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class CrudServicePartyClassificationTest
{
	@Resource(name="crudServicePartyClassification")
	CrudService<PartyClassification, Integer> serviceClassification;
	@Resource(name="crudServicePartyType")
	CrudService<PartyType, Integer> serviceType;
	
	@Before
	public void setUp()
	{
		Assert.assertNotNull(serviceClassification);
		Assert.assertNotNull(serviceType);
	}
	
	@Test
	public void testCrud()
	{
		PartyType pType1;
		String pTypeDescription1 = "Description 1";
		PartyType pType2;
		String pTypeDescription2 = "Description 2";
		PartyClassification pC1;
		PartyClassification pC2;
		Integer id1;
		Date now = new Date();
		Date further = new Date(now.getTime() + 3*1000*1024);
		
		
		pType1 = new PartyType();
		pType1.setDescription(pTypeDescription1);
		
		pType2 = new PartyType();
		pType2.setDescription(pTypeDescription2);
		
		this.serviceType.create(pType1);
		this.serviceType.create(pType2);
		Assert.assertNotNull(pType1.getId());
		Assert.assertNotNull(pType2.getId());
		Assert.assertFalse(pType1.getId() == pType2.getId());
		
		pC1 = new PartyClassification();
		pC1.setPartyType(pType1);
		this.serviceClassification.create(pC1);
		Assert.assertNotNull(pC1.getId());
		id1 = pC1.getId();
		
		pC1.setDateTo(further);
		this.serviceClassification.create(pC1);
		
		pC2 = this.serviceClassification.read(id1);
		Assert.assertNotNull(pC2);
		Assert.assertEquals(pC2.getDateTo(), further);
		
		this.serviceClassification.delete(id1);
		pC2 = this.serviceClassification.read(id1);
		Assert.assertNull(pC2);
		
		// Check accidentally deleted PTYPE ?
		id1 = pType1.getId();
		pType2 = this.serviceType.read(id1);
		Assert.assertNotNull(pType2);
	}
	
}
