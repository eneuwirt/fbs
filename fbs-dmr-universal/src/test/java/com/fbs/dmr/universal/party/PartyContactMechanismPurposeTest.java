package com.fbs.dmr.universal.party;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fbs.dmr.universal.model.party.PartyContactMechanismPurpose;

public class PartyContactMechanismPurposeTest
{
	@Test
	public void testEqualsAndHashCode()
	{
		PartyContactMechanismPurpose obj1 = null;
		PartyContactMechanismPurpose obj2 = null;
		Float another = new Float(2.0);

		obj1 = new PartyContactMechanismPurpose();
		assertTrue(obj1.equals(obj1));
		assertFalse(obj1.equals(obj2));
		assertFalse(obj1.equals(another));
		
		obj2 = new PartyContactMechanismPurpose();
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
