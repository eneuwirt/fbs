package org.fbs.security;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fbs.security.model.User;
import com.fbs.security.service.UserService;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-test.xml")
public class UserServiceTest
{
	@Resource
	UserService userService;
	
	@Before
	public void setUp()
	{
		Assert.assertNotNull(this.userService);
	}
	
	@Test
	public void testDataAccess()
	{
		User user1;
		String userName1 = "user 1";
		String password1 = "passwd 1";
		String salt1 = "salt 1";
		Integer tenantId1 = 1;
		User user2;
		String userName2 = "user 2";
		String password2 = "passwd 2";
		String password3 = "passwd 3";
		String salt2 = "salt 2";
		Integer tenantId2 = 2;
		List<User> users;
		
		user1 = new User();
		user1.setUserName(userName1);
		user1.setPassword(password1);
		user1.setSalt(salt1);
		user1.setTenantId(tenantId1);
		
		user1 = this.userService.create(user1);
		Assert.assertNotNull(user1);
		
		user2 = new User();
		user2.setUserName(userName2);
		user2.setPassword(password2);
		user2.setSalt(salt2);
		user2.setTenantId(tenantId2);
		
		user2 = this.userService.create(user2);
		Assert.assertNotNull(user2);
		
		users = this.userService.findAll();
		Assert.assertNotNull(users);
		Assert.assertEquals(users.size(), 2);
		
		user1 = this.userService.read("HDSH");
		Assert.assertNull(user1);
		
		user1 = this.userService.read(userName1);
		Assert.assertNotNull(user1);
		Assert.assertEquals(password1, user1.getPassword());
		
		user1.setPassword(password3);
		this.userService.update(user1);
		user2 = this.userService.read(userName1);
		Assert.assertNotNull(user2);
		Assert.assertEquals(password3, user2.getPassword());
		Assert.assertEquals(userName1, user2.getUserName());
		Assert.assertEquals(salt1, user2.getSalt());
		
		this.userService.delete(userName1);
		user1 = this.userService.read(userName1);
		Assert.assertNull(user1);
	}
}
