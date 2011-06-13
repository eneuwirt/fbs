package com.fbs.dmr.universal.test.service.postaladdress;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.fbs.dmr.universal.model.postaladdress.City;
import com.fbs.dmr.universal.model.postaladdress.Country;
import com.fbs.dmr.universal.model.postaladdress.PostalAddress;
import com.fbs.dmr.universal.model.postaladdress.PostalAddressBoundary;
import com.fbs.dmr.universal.model.postaladdress.PostalCode;
import com.fbs.dmr.universal.model.postaladdress.State;
import com.fbs.dmr.universal.service.CrudService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-test.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class CrudServicePostalAddressTest
{
	@Resource(name="crudServicePostalAddress")
	CrudService<PostalAddress, Integer> crudServicePostalAddress;
	
	@Before
	public void setUp()
	{
		Assert.assertNotNull(crudServicePostalAddress);
	}
	
	@Test
	public void testCrud()
	{
		PostalAddress postalAddress;
		PostalAddressBoundary postalAddressBoundary;
		String address1 = "address1";
		// Details
		Country country;
		State state;
		City city;
		PostalCode postalCode;
		
		postalAddress = new PostalAddress();
		postalAddress.setAddress1(address1);
		
		// Add country to postal address
		country = new Country();
		country.setName("Big Country");
		postalAddressBoundary = new PostalAddressBoundary();
		postalAddressBoundary.setPostalAddress(postalAddress);
		postalAddressBoundary.setGeoBoundary(country);
				
		state = new State();
		state.setName("Small State");
		state.setCountry(country);
		postalAddressBoundary = new PostalAddressBoundary();
		postalAddressBoundary.setPostalAddress(postalAddress);
		postalAddressBoundary.setGeoBoundary(state);
		
		city = new City();
		city.setName("Big City");
		city.setState(state);
		postalAddressBoundary = new PostalAddressBoundary();
		postalAddressBoundary.setPostalAddress(postalAddress);
		postalAddressBoundary.setGeoBoundary(city);
		
		postalCode = new PostalCode();
		postalCode.setCode("1234567");
		postalCode.setCountry(country);
		postalAddressBoundary = new PostalAddressBoundary();
		postalAddressBoundary.setPostalAddress(postalAddress);
		postalAddressBoundary.setGeoBoundary(postalCode);
		
		this.crudServicePostalAddress.create(postalAddress);
		Assert.assertNotNull(postalAddress.getId());
	}
}
