package com.fbs.dmr.universal.party;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fbs.dmr.universal.model.party.Organization;

public class OrganizationTest
{
	/*
	@Test
	public void testCompareTo()
	{
		Organization p1 = null;
		Organization p2 = null;

		p1 = new Organization();
		assertEquals(p1.compareTo(null), -1);

		p2 = new Organization();
		assertEquals(p1.compareTo(p2), 0);
		
		p1.setName("Name1");
		assertEquals(p1.compareTo(p2), 1);

		p1.setId(1);
		assertEquals(p1.compareTo(p2), -1);

		p2.setId(2);
		assertEquals(p1.compareTo(p2), -1);

		p2.setId(1);
		assertEquals(p1.compareTo(p2), 0);
	}
	*/
	
	@Test
	public void testEqualsAndHashCode()
	{
		Organization obj1 = null;
		Organization obj2 = null;

		obj1 = new Organization();
		assertTrue(obj1.equals(obj1));
		assertFalse(obj1.equals(obj2));
		
		obj2 = new Organization();

		assertTrue(obj1.equals(obj1));
		assertTrue(obj1.equals(obj2));
		assertTrue(obj2.equals(obj1));
		assertEquals(obj1.hashCode(), obj2.hashCode());
		
		obj1.setName("Name1");
		assertTrue(obj1.equals(obj1));
		assertFalse(obj1.equals(obj2));
		assertFalse(obj2.equals(obj1));
		assertFalse(obj1.hashCode() == obj2.hashCode());
		
		obj2.setName("Name1");
		assertTrue(obj1.equals(obj1));
		assertTrue(obj1.equals(obj2));
		assertTrue(obj2.equals(obj1));
		assertEquals(obj1.hashCode(), obj2.hashCode());
		
		obj1.setId(1);
		assertTrue(obj1.equals(obj1));
		assertFalse(obj1.equals(obj2));
		assertFalse(obj2.equals(obj1));
		assertFalse(obj1.hashCode() == obj2.hashCode());

		
		obj1 = new Organization();
		obj2 = new Organization();

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
		
		obj2.setId(2);
		assertTrue(obj2.equals(obj2));
		assertFalse(obj2.equals(obj1));
		assertFalse(obj1.equals(obj2));
		assertFalse(obj1.hashCode() == obj2.hashCode());
		
		obj2.setName("Name1");
		assertTrue(obj1.equals(obj1));
		assertFalse(obj2.equals(obj1));
		assertFalse(obj1.equals(obj2));
		assertFalse(obj1.hashCode() == obj2.hashCode());
	}
}
