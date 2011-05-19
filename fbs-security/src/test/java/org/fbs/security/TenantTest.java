package org.fbs.security;

import org.junit.Test;
import org.junit.Assert;

import com.fbs.security.model.Tenant;

public class TenantTest
{
	@Test
	public void test()
	{
		Tenant t1 = null;
		Tenant t2 = null;
		Integer id1 = 1;
		String desc1 = "descr 1";
		Integer id2 = 2;
		String desc2 = "descr 2";
		
		t1 = new Tenant();
		Assert.assertFalse(t1.equals(t2));
		t2 = new Tenant();
		Assert.assertFalse(t1.equals(t2));
		Assert.assertFalse(t1.hashCode() == t2.hashCode());
		
		t1.setDescription(desc1);
		Assert.assertEquals(t1, t1);
		Assert.assertFalse(t1.equals(t2));
		Assert.assertFalse(t1.hashCode() == t2.hashCode());
		
		// set same description and id null
		t2.setDescription(desc1);
		Assert.assertFalse(t1.equals(t2));
		Assert.assertFalse(t1.hashCode() == t2.hashCode());
		
		t2.setDescription(desc2);
		Assert.assertFalse(t1.equals(t2));
		Assert.assertFalse(t1.hashCode() == t2.hashCode());
		Assert.assertFalse(t1.hashCode() == t2.hashCode());
		
		t1.setId(id1);
		Assert.assertEquals(t1, t1);
		Assert.assertFalse(t1.equals(t2));
		Assert.assertFalse(t1.hashCode() == t2.hashCode());
		
		t2.setId(id2);
		Assert.assertFalse(t1.equals(t2));
		Assert.assertFalse(t1.hashCode() == t2.hashCode());
		
		t2.setId(id1);
		Assert.assertTrue(t1.equals(t2));
		Assert.assertTrue(t1.hashCode() == t2.hashCode());
	}
}
