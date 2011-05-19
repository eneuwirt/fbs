package org.fbs.security;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
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
		Tenant tenant1;
		Tenant tenant2;
		String description = "Description tenant 1";
		String description2 = "Description tenant 2";
		List<Tenant> tenants;

		tenant1 = new Tenant();
		tenant1.setDescription(description);
		tenant1 = this.tenantService.create(tenant1);
		Assert.assertNotNull(tenant1.getId());

		tenant2 = new Tenant();
		tenant2.setDescription(description2);
		tenant2 = this.tenantService.create(tenant2);
		Assert.assertNotNull(tenant2.getId());

		tenants = this.tenantService.findAll();
		Assert.assertEquals(tenants.size(), 2);

		int id = tenant1.getId();
		tenant1 = this.tenantService.read(id);
		Assert.assertNotNull(tenant1);
		Assert.assertEquals(tenant1.getId(), new Integer(id));
		Assert.assertEquals(description, tenant1.getDescription());

		description = "New description";
		tenant1.setDescription(description);
		this.tenantService.update(tenant1);
		tenant1 = this.tenantService.read(id);
		Assert.assertNotNull(tenant1);
		Assert.assertEquals(tenant1.getId(), new Integer(id));
		Assert.assertEquals(description, tenant1.getDescription());

		this.tenantService.delete(tenant1);
		tenant1 = this.tenantService.read(id);
		Assert.assertNull(tenant1);
	}


	@After
	public void tearDown()
	{

	}

}
