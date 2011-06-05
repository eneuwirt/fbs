package com.fbs.web.vaadin.application;

import javax.annotation.Resource;

import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyRelationshipType;
import com.fbs.dmr.universal.model.party.PartyRoleType;
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
	private CrudService<Party, Integer> crudServiceParty;
	@Resource(name = "crudServicePartyType")
	private CrudService<PartyType, Integer> crudServicePartyType;
	@Resource (name="crudServicePerson")
	private CrudService<Person, Integer> crudServicePerson;
	@Resource(name = "crudServicePartyRelationshipType")
	private CrudService<PartyRelationshipType, Integer>crudServicePartyRelationshipType;
	@Resource(name = "crudServicePartyRoleType")
	private CrudService<PartyRoleType, Integer>crudServicePartyRoleType;

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
	public CrudService<Party, Integer> getCrudServiceParty()
	{
		return crudServiceParty;
	}

	public void setCrudPartyService(CrudService<Party, Integer> crudPartyService)
	{
		this.crudServiceParty = crudPartyService;
	}

	@Override
	public CrudService<PartyType, Integer> getCrudServicePartyType()
	{
		return crudServicePartyType;
	}

	public void setCrudPartyTypeService(CrudService<PartyType, Integer> crudPartyTypeService)
	{
		this.crudServicePartyType = crudPartyTypeService;
	}

	@Override
	public CrudService<Person, Integer> getCrudServicePerson()
    {
	    return crudServicePerson;
    }
	
	public void setCrudPersonService(CrudService<Person, Integer> crudPersonService)
    {
	    this.crudServicePerson = crudPersonService;
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

	@Override
    public CrudService<PartyRoleType, Integer> getCrudServicePartyRoleType()
    {
	    return crudServicePartyRoleType;
    }

	public void setCrudServicePartyRelationshipType(CrudService<PartyRelationshipType, Integer> crudServicePartyRelationshipType)
    {
	    this.crudServicePartyRelationshipType = crudServicePartyRelationshipType;
    }

	@Override
	public CrudService<PartyRelationshipType, Integer> getCrudServicePartyRelationshipType()
    {
	    return crudServicePartyRelationshipType;
    }
}
