package com.fbs.web.vaadin.application;

import javax.annotation.Resource;

import com.fbs.dmr.universal.model.contact.ContactMechanismPurposeType;
import com.fbs.dmr.universal.model.contact.ContactMechanismType;
import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyRelationship;
import com.fbs.dmr.universal.model.party.PartyRelationshipStatusType;
import com.fbs.dmr.universal.model.party.PartyRelationshipType;
import com.fbs.dmr.universal.model.party.Person;
import com.fbs.dmr.universal.model.party.PriorityType;
import com.fbs.dmr.universal.service.CrudService;
import com.fbs.dmr.universal.service.SeedService;
import com.fbs.dmr.universal.service.ServicePartyClassification;
import com.fbs.dmr.universal.service.ServicePartyRole;
import com.fbs.dmr.universal.service.ServicePartyRoleType;
import com.fbs.dmr.universal.service.ServicePartyType;
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
	@Resource(name="crudServiceContactMechanismPurposeType")
	CrudService<ContactMechanismPurposeType, Integer> crudServiceContactMechanismPurposeType;
	@Resource(name="crudServiceContactMechanismType")
	CrudService<ContactMechanismType, Integer> crudServiceContactMechanismType;
	@Resource(name = "crudServiceOrganization")
	private CrudService<Organization, Integer> crudServiceOrganization;
	@Resource(name = "crudServiceParty")
	private CrudService<Party, Integer> crudServiceParty;
	@Resource(name = "crudServicePartyType")
	private ServicePartyType crudServicePartyType;
	@Resource(name = "crudServicePartyRelationship")
	private CrudService<PartyRelationship, Integer> crudServicePartyRelationship;
	@Resource(name = "crudServicePartyRelationshipStatusType")
	private CrudService<PartyRelationshipStatusType, Integer> crudServicePartyRelationshipStatusType;
	@Resource(name = "crudServicePartyRelationshipType")
	private CrudService<PartyRelationshipType, Integer> crudServicePartyRelationshipType;
	@Resource(name = "crudServicePartyRoleType")
	private ServicePartyRoleType crudServicePartyRoleType;
	@Resource(name = "crudServicePartyRole")
	private ServicePartyRole crudServicePartyRole;
	@Resource(name = "crudServicePerson")
	private CrudService<Person, Integer> crudServicePerson;
	@Resource(name="crudServicePriorityType")
	private CrudService<PriorityType, Integer> crudServicePriorityType;
	@Resource
	ServicePartyClassification servicePartyClassification;

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
	public ServicePartyType getCrudServicePartyType()
	{
		return crudServicePartyType;
	}

	public void setCrudPartyTypeService(ServicePartyType crudPartyTypeService)
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

	public void setCrudServicePartyRoleType(ServicePartyRoleType crudServicePartyRole)
	{
		this.crudServicePartyRoleType = crudServicePartyRole;
	}

	@Override
	public ServicePartyRoleType getCrudServicePartyRoleType()
	{
		return crudServicePartyRoleType;
	}

	public void setCrudServicePartyRelationshipType(
	        CrudService<PartyRelationshipType, Integer> crudServicePartyRelationshipType)
	{
		this.crudServicePartyRelationshipType = crudServicePartyRelationshipType;
	}

	@Override
	public CrudService<PartyRelationshipType, Integer> getCrudServicePartyRelationshipType()
	{
		return crudServicePartyRelationshipType;
	}

	public void setCrudServicePartyRelationship(CrudService<PartyRelationship, Integer> crudServicePartyRelationship)
	{
		this.crudServicePartyRelationship = crudServicePartyRelationship;
	}

	@Override
	public CrudService<PartyRelationship, Integer> getCrudServicePartyRelationship()
	{
		return crudServicePartyRelationship;
	}

	@Override
	public ServicePartyRole getCrudServicePartyRole()
	{
		return this.crudServicePartyRole;
	}

	public void setCrudServicePartyRole(ServicePartyRole crudServicePartyRole)
	{
		this.crudServicePartyRole = crudServicePartyRole;
	}

	@Override
	public ServicePartyClassification getCrudServicePartyClassification()
	{

		return this.servicePartyClassification;
	}

	public void setCrudServicePartyClassification(ServicePartyClassification servicePartyClassification)
	{

		this.servicePartyClassification = servicePartyClassification;
	}

	public void setCrudServicePartyRelationshipStatusType(
	        CrudService<PartyRelationshipStatusType, Integer> crudServicePartyRelationshipStatusType)
	{
		this.crudServicePartyRelationshipStatusType = crudServicePartyRelationshipStatusType;
	}

	@Override
	public CrudService<PartyRelationshipStatusType, Integer> getCrudServicePartyRelationshipStatusType()
	{
		return crudServicePartyRelationshipStatusType;
	}

	public void setCrudServicePriorityType(CrudService<PriorityType, Integer> crudServicePriorityType)
	{
		this.crudServicePriorityType = crudServicePriorityType;
	}

	@Override
	public CrudService<PriorityType, Integer> getCrudServicePriorityType()
	{
		return this.crudServicePriorityType;
	}

	@Override
    public CrudService<ContactMechanismType, Integer> getCrudServiceContactMechanismType()
    {
	    return this.crudServiceContactMechanismType;
    }

	@Override
    public CrudService<ContactMechanismPurposeType, Integer> getCrudServiceContactMechanismPurposeType()
    {
	    return this.crudServiceContactMechanismPurposeType;
    }
}
