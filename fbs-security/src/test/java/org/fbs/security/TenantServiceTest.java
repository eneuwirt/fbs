package org.fbs.security;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fbs.security.model.Tenant;
import com.fbs.security.service.TenantService;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-test.xml")
public class TenantServiceTest
{
	@Resource
	TenantService tenantService;


	@Before
	public void setUp()
	{
		Assert.assertNotNull(this.tenantService);
	}


	@Test
	public void testDataAccess()
	{
		Tenant tenant;
		String description =  "Description test";
		
		tenant = new Tenant();
		tenant.setDescription(description);
		
		tenant = this.tenantService.create(tenant);
		Assert.assertNotNull(tenant.getId());
		
		int id = tenant.getId();
		tenant = this.tenantService.read(id);
		Assert.assertNotNull(tenant);
		Assert.assertEquals(tenant.getId(), new Integer(id));
		Assert.assertEquals(description, tenant.getDescription());
		
		description = "New description";
		tenant.setDescription(description);
		this.tenantService.update(tenant);
		tenant = this.tenantService.read(id);
		Assert.assertNotNull(tenant);
		Assert.assertEquals(tenant.getId(), new Integer(id));
		Assert.assertEquals(description, tenant.getDescription());	
		
		this.tenantService.delete(id);
		tenant = this.tenantService.read(id);
		Assert.assertNull(tenant);
	}


	@After
	public void tearDown()
	{
		
	}

}
