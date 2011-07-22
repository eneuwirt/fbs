package com.fbs.dmr.universal.test.service.contact;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.fbs.dmr.universal.model.contact.ElectronicAddress;
import com.fbs.dmr.universal.service.crud.CrudService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-test.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class CrudServiceElectronicAddressTest
{
	@Resource(name = "crudServiceElectronicAddress")
	CrudService<ElectronicAddress, Integer> crudServiceElectronicAddress;

	@Before
	public void setUp()
	{
		Assert.assertNotNull(crudServiceElectronicAddress);
	}

	@Test
	public void testCrud()
	{
		ElectronicAddress ea; 
		String email = "aaa@ooo.com";
		
		ea = new ElectronicAddress();
		ea.setElectronicAddress(email);
		
		this.crudServiceElectronicAddress.create(ea);
		Assert.assertNotNull(ea.getId());
	}
}
