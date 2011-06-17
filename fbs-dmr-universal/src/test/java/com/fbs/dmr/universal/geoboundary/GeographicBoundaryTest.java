package com.fbs.dmr.universal.geoboundary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fbs.dmr.universal.model.geoboundary.GeographicBoundary;

public class GeographicBoundaryTest
{
	@Test
	public void testEqualsAndHashCode()
	{
		GeographicBoundary obj1 = null;
		GeographicBoundary obj2 = null;

		obj1 = new GeographicBoundary();
		assertTrue(obj1.equals(obj1));
		assertFalse(obj1.equals(obj2));
		obj2 = new GeographicBoundary();

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

		
		obj1 = new GeographicBoundary();
		obj2 = new GeographicBoundary();

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
