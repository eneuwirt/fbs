package com.fbs.dmr.universal.contact;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fbs.dmr.universal.model.contact.ContactMechanism;

public class ContactMechanismTest
{
	@Test
	public void testEqualsAndHashCode()
	{
		ContactMechanism obj1 = null;
		ContactMechanism obj2 = null;

		obj1 = new ContactMechanism();
		assertTrue(obj1.equals(obj1));
		assertFalse(obj1.equals(obj2));
		
		obj2 = new ContactMechanism();

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
	}
}
