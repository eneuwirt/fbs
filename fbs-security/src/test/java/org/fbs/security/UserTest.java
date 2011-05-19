package org.fbs.security;

import org.junit.Test;
import org.junit.Assert;

import com.fbs.security.model.User;

public class UserTest
{
	@Test
	public void test()
	{
		User u1 = null;
		User u2 = null;
		String uName1 = "uname 1";
		String uName2 = "uname 2";
		String p1 = "password 1";
		
		u1 = new User();
		Assert.assertEquals(u1, u1);
		Assert.assertFalse(u1.equals(u2));
		
		u2 = new User();
		Assert.assertFalse(u1.equals(u2));
		Assert.assertFalse(u1.hashCode() == u2.hashCode());
		
		u1.setPassword(p1);
		u2.setPassword(p1);
		Assert.assertEquals(u1, u1);
		Assert.assertFalse(u1.equals(u2));
		Assert.assertFalse(u1.hashCode() == u2.hashCode());
		
		u1.setUserName(uName1);
		Assert.assertEquals(u1, u1);
		Assert.assertFalse(u1.equals(u2));
		Assert.assertFalse(u1.hashCode() == u2.hashCode());
		
		u2.setUserName(uName2);
		Assert.assertEquals(u1, u1);
		Assert.assertFalse(u1.equals(u2));
		Assert.assertFalse(u1.hashCode() == u2.hashCode());
		
		u2.setUserName(uName1);
		Assert.assertEquals(u1, u1);
		Assert.assertTrue(u1.equals(u2));
		Assert.assertTrue(u1.hashCode() == u2.hashCode());
	}
}
