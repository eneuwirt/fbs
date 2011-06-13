package com.fbs.dmr.universal.postaladdress;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fbs.dmr.universal.model.postaladdress.PostalAddressBoundary;

public class PostalAddressBoundaryType
{
	@Test
	public void testEqualsAndHashCode()
	{
		PostalAddressBoundary obj1 = null;
		PostalAddressBoundary obj2 = null;
		//String address1 = "address1";

		obj1 = new PostalAddressBoundary();
		assertTrue(obj1.equals(obj1));
		assertFalse(obj1.equals(obj2));
		
		obj2 = new PostalAddressBoundary();

		assertTrue(obj1.equals(obj1));
		assertTrue(obj1.equals(obj2));
		assertTrue(obj2.equals(obj1));
		assertEquals(obj1.hashCode(), obj2.hashCode());

		obj1.setId(1);
		assertTrue(obj1.equals(obj1));
		assertFalse(obj1.equals(obj2));
		assertFalse(obj2.equals(obj1));
		assertFalse(obj1.hashCode() == obj2.hashCode());

		obj2.setId(1);
		assertTrue(obj2.equals(obj2));
		assertTrue(obj1.equals(obj2));
		assertTrue(obj2.equals(obj1));
		assertEquals(obj1.hashCode(), obj2.hashCode());

		obj2.setId(3);
		assertTrue(obj2.equals(obj2));
		assertFalse(obj2.equals(obj1));
		assertFalse(obj1.equals(obj2));
		assertFalse(obj1.hashCode() == obj2.hashCode());

		obj1 = new PostalAddressBoundary();
		obj2 = new PostalAddressBoundary();
		/*
		obj1.setAddress1(address1);
		assertTrue(obj1.equals(obj1));
		assertFalse(obj2.equals(obj1));
		assertFalse(obj1.equals(obj2));
		assertFalse(obj1.hashCode() == obj2.hashCode());
		
		obj1.setId(1);
		obj2.setId(1);
		assertTrue(obj1.equals(obj1));
		assertTrue(obj1.equals(obj2));
		assertTrue(obj2.equals(obj1));
		assertEquals(obj1.hashCode(), obj2.hashCode());
		*/
	}
}
