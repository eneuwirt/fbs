package com.fbs.dmr.universal.party;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fbs.dmr.universal.model.type.RoleType;

public class RoleTypeTest
{
	@Test
	public void testEqualsAndHashCode()
	{
		RoleType obj1 = null;
		RoleType obj2 = null;
		String description1 = "Descr 1";

		obj1 = new RoleType();
		obj2 = new RoleType();

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

		obj1 = new RoleType();
		obj2 = new RoleType();
		obj1.setDescription(description1);
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
	}
}
