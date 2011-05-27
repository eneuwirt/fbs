package com.fbs.web.vaadin.application;

import javax.annotation.Resource;

import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.dmr.universal.model.party.Person;
import com.fbs.dmr.universal.service.CrudService;
import com.fbs.dmr.universal.service.SeedService;
import com.fbs.security.service.SecurityService;
import com.fbs.security.service.TenantService;
import com.fbs.security.service.UserService;

// Service Container
public class ApplicationServicesImpl implements ApplicationServices
{
	@Resource
	private SecurityService securityService;
	@Resource
	private TenantService tenantService;
	@Resource
	private UserService userService;
	@Resource
	private SeedService seedService;
	@Resource(name = "crudServiceOrganization")
	private CrudService<Organization, Integer> crudServiceOrganization;
	@Resource(name = "crudServiceParty")
	private CrudService<Party, Integer> crudPartyService;
	@Resource(name = "crudServicePartyType")
	private CrudService<PartyType, Integer> crudPartyTypeService;
	@Resource (name="crudServicePerson")
	private CrudService<Person, Integer> crudPersonService;

	@Override
	public SecurityService getSecurityService()
	{
		return securityService;
	}

	public void setSecurityService(SecurityService securityService)
	{
		this.securityService = securityService;
	}

	@Override
	public TenantService getTenantService()
	{
		return tenantService;
	}

	public void setTenantService(TenantService tenantService)
	{
		this.tenantService = tenantService;
	}

	@Override
	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	@Override
	public SeedService getSeedService()
	{
		return seedService;
	}

	public void setSeedService(SeedService seedService)
	{
		this.seedService = seedService;
	}

	@Override
	public CrudService<Party, Integer> getCrudPartyService()
	{
		return crudPartyService;
	}

	public void setCrudPartyService(CrudService<Party, Integer> crudPartyService)
	{
		this.crudPartyService = crudPartyService;
	}

	@Override
	public CrudService<PartyType, Integer> getCrudPartyTypeService()
	{
		return crudPartyTypeService;
	}

	public void setCrudPartyTypeService(CrudService<PartyType, Integer> crudPartyTypeService)
	{
		this.crudPartyTypeService = crudPartyTypeService;
	}

	@Override
	public CrudService<Person, Integer> getCrudPersonService()
    {
	    return crudPersonService;
    }
	
	public void setCrudPersonService(CrudService<Person, Integer> crudPersonService)
    {
	    this.crudPersonService = crudPersonService;
    }

	public void setCrudServiceOrganization(CrudService<Organization, Integer> crudServiceOrganization)
    {
	    this.crudServiceOrganization = crudServiceOrganization;
    }

	@Override
	public CrudService<Organization, Integer> getCrudServiceOrganization()
    {
	    return crudServiceOrganization;
    }
}
