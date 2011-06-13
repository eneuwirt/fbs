package com.fbs.dmr.universal.test.service.party;

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

import com.fbs.dmr.universal.model.party.Person;
import com.fbs.dmr.universal.service.CrudService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-test.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class CrudServicePersonTest
{
	@Resource(name = "crudServicePerson")
	CrudService<Person, Integer> servicePerson;


	@Before
	public void setUp()
	{
		Assert.assertNotNull(servicePerson);
	}


	@Test
	public void testCrud()
	{
		Person person1;
		String fName1 = "Description 1";
		Person person2;
		String fName2 = "Description 2";
		Integer id1;
		List<Person> persons;

		person1 = new Person();
		person1.setFirstName(fName1);

		person2 = new Person();
		person2.setFirstName(fName2);

		this.servicePerson.create(person1);
		this.servicePerson.create(person2);
		Assert.assertNotNull(person1.getId());
		Assert.assertNotNull(person2.getId());
		Assert.assertFalse(person1.getId() == person2.getId());

		id1 = person1.getId();
		person2 = this.servicePerson.read(id1);
		Assert.assertNotNull(person2);
		
		person1.setFirstName(fName2);
		this.servicePerson.update(person1);
		person2 = this.servicePerson.read(id1);
		Assert.assertNotNull(person2);
		Assert.assertEquals(person2.getFirstName(), fName2);

		persons = this.servicePerson.findAll();
		Assert.assertNotNull(persons);
		Assert.assertEquals(persons.size(), 2);
		
		id1 = person1.getId();
		this.servicePerson.delete(id1);
		person2 = this.servicePerson.read(id1);
		Assert.assertNull(person2);
		
		persons = this.servicePerson.findAll();
		Assert.assertNotNull(persons);
		Assert.assertEquals(persons.size(), 1);
	}
}
