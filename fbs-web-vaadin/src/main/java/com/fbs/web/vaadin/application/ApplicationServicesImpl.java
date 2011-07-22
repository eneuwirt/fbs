package com.fbs.web.vaadin.application;

import javax.annotation.Resource;

import com.fbs.dmr.universal.model.contact.ContactMechanismPurposeType;
import com.fbs.dmr.universal.model.contact.ContactMechanismType;
import com.fbs.dmr.universal.model.contact.ElectronicAddress;
import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.dmr.universal.model.contact.TelecommunicationNumber;
import com.fbs.dmr.universal.model.facility.Facility;
import com.fbs.dmr.universal.model.facility.FacilityContactMechanism;
import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyFacility;
import com.fbs.dmr.universal.model.party.PartyRelationship;
import com.fbs.dmr.universal.model.party.PartyRelationshipStatusType;
import com.fbs.dmr.universal.model.party.PartyRelationshipType;
import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.dmr.universal.model.party.Person;
import com.fbs.dmr.universal.model.party.PriorityType;
import com.fbs.dmr.universal.model.workeffort.WorkEffort;
import com.fbs.dmr.universal.model.workeffort.WorkEffortType;
import com.fbs.dmr.universal.service.crud.CrudService;
import com.fbs.dmr.universal.service.crud.CrudServicePartyClassification;
import com.fbs.dmr.universal.service.crud.CrudServicePartyContactMechanism;
import com.fbs.dmr.universal.service.crud.CrudServicePartyContactMechanismPurpose;
import com.fbs.dmr.universal.service.crud.CrudServicePartyRole;
import com.fbs.dmr.universal.service.crud.CrudServiceType;
import com.fbs.dmr.universal.service.crud.CrudServiceWorkEffortPartyAssignment;
import com.fbs.dmr.universal.service.seed.SeedService;
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
	@Resource(name = "crudServiceContactMechanismPurposeType")
	private CrudServiceType<ContactMechanismPurposeType, Integer> crudServiceContactMechanismPurposeType;

	@Resource(name = "crudServiceContactMechanismType")
	private CrudService<ContactMechanismType, Integer> crudServiceContactMechanismType;

	@Resource(name = "crudServiceFacility")
	private CrudService<Facility, Integer> crudServiceFacility;

	@Resource(name = "crudServiceFacilityContactMechanism")
	private CrudService<FacilityContactMechanism, Integer> crudServiceFacilityContactMechanism;

	@Resource(name = "crudServiceElectronicAddress")
	private CrudService<ElectronicAddress, Integer> crudServiceElectronicAddress;

	@Resource(name = "crudServiceOrganization")
	private CrudService<Organization, Integer> crudServiceOrganization;

	@Resource(name = "crudServiceParty")
	private CrudService<Party, Integer> crudServiceParty;

	@Resource(name = "crudServicePartyContactMechanism")
	private CrudServicePartyContactMechanism crudServicePartyContactMechanism;

	@Resource(name = "crudServicePartyContactMechanismPurpose")
	private CrudServicePartyContactMechanismPurpose crudServicePartyContactMechanismPurpose;

	@Resource(name = "crudServicePartyFacility")
	private CrudService<PartyFacility, Integer> crudServicePartyFacility;

	@Resource(name = "crudServicePartyType")
	private CrudServiceType<PartyType, Integer> crudServicePartyType;

	@Resource(name = "crudServicePartyRelationship")
	private CrudService<PartyRelationship, Integer> crudServicePartyRelationship;

	@Resource(name = "crudServicePartyRelationshipStatusType")
	private CrudService<PartyRelationshipStatusType, Integer> crudServicePartyRelationshipStatusType;

	@Resource(name = "crudServicePartyRelationshipType")
	private CrudService<PartyRelationshipType, Integer> crudServicePartyRelationshipType;

	@Resource(name = "crudServicePartyRoleType")
	private CrudServiceType<PartyRoleType, Integer> crudServicePartyRoleType;

	@Resource(name = "crudServicePartyRole")
	private CrudServicePartyRole crudServicePartyRole;

	@Resource(name = "crudServicePerson")
	private CrudService<Person, Integer> crudServicePerson;

	@Resource(name = "crudServicePriorityType")
	private CrudService<PriorityType, Integer> crudServicePriorityType;

	@Resource(name = "crudServiceTelecommunicationNumber")
	private CrudService<TelecommunicationNumber, Integer> crudServiceTelecommunicationNumber;

	@Resource
	private CrudServicePartyClassification crudServicePartyClassification;

	@Resource(name = "crudServicePostalAddress")
	private CrudService<PostalAddress, Integer> crudServicePostalAddress;

	@Resource(name = "crudServiceWorkEffort")
	private CrudService<WorkEffort, Integer> crudServiceWorkEffort;
	
	@Resource(name = "crudServiceWorkEffortPartyAssignment")
    private CrudServiceWorkEffortPartyAssignment crudServiceWorkEffortPartyAssignment;

	@Resource(name = "crudServiceWorkEffortType")
	private CrudService<WorkEffortType, Integer> crudServiceWorkEffortType;

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
	public CrudServiceType<PartyType, Integer> getCrudServicePartyType()
	{
		return crudServicePartyType;
	}

	public void setCrudPartyTypeService(CrudServiceType<PartyType, Integer> crudPartyTypeService)
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

	public void setCrudServicePartyRoleType(CrudServiceType<PartyRoleType, Integer> crudServicePartyRole)
	{
		this.crudServicePartyRoleType = crudServicePartyRole;
	}

	@Override
	public CrudServiceType<PartyRoleType, Integer> getCrudServicePartyRoleType()
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
	public CrudServicePartyRole getCrudServicePartyRole()
	{
		return this.crudServicePartyRole;
	}

	public void setCrudServicePartyRole(CrudServicePartyRole crudServicePartyRole)
	{
		this.crudServicePartyRole = crudServicePartyRole;
	}

	@Override
	public CrudServicePartyClassification getCrudServicePartyClassification()
	{

		return this.crudServicePartyClassification;
	}

	public void setCrudServicePartyClassification(CrudServicePartyClassification crudServicePartyClassification)
	{

		this.crudServicePartyClassification = crudServicePartyClassification;
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
	public CrudServiceType<ContactMechanismPurposeType, Integer> getCrudServiceContactMechanismPurposeType()
	{
		return this.crudServiceContactMechanismPurposeType;
	}

	@Override
	public CrudServicePartyContactMechanism getCrudServicePartyContactMechanism()
	{
		return this.crudServicePartyContactMechanism;
	}

	public void setCrudServicePartyContactMechanism(CrudServicePartyContactMechanism crudServicePartyContactMechanism)
	{
		this.crudServicePartyContactMechanism = crudServicePartyContactMechanism;
	}

	@Override
	public CrudService<PostalAddress, Integer> getCrudServicePostalAddress()
	{
		return this.crudServicePostalAddress;
	}

	public void setCrudServicePostalAddress(CrudService<PostalAddress, Integer> crudServicePostalAddress)
	{
		this.crudServicePostalAddress = crudServicePostalAddress;
	}

	@Override
	public CrudService<TelecommunicationNumber, Integer> getCrudServiceTelecommunicationNumber()
	{
		return this.crudServiceTelecommunicationNumber;
	}

	public void setCrudServiceTelecommunicationNumber(
	        CrudService<TelecommunicationNumber, Integer> crudServiceTelecommunicationNumber)
	{
		this.crudServiceTelecommunicationNumber = crudServiceTelecommunicationNumber;
	}

	@Override
	public CrudService<ElectronicAddress, Integer> getCrudServiceElectronicAddress()
	{
		return this.crudServiceElectronicAddress;
	}

	public void setCrudServiceElectronicAddress(CrudService<ElectronicAddress, Integer> crudServiceElectronicAddress)
	{
		this.crudServiceElectronicAddress = crudServiceElectronicAddress;
	}

	@Override
	public CrudServicePartyContactMechanismPurpose getCrudServicePartyContactMechanismPurpose()
	{
		return this.crudServicePartyContactMechanismPurpose;
	}

	public void setCrudServicePartyContactMechanismPurpose(
	        CrudServicePartyContactMechanismPurpose crudServicePartyContactMechanismPurpose)
	{
		this.crudServicePartyContactMechanismPurpose = crudServicePartyContactMechanismPurpose;
	}

	@Override
	public CrudService<PartyFacility, Integer> getCrudServicePartyFacility()
	{
		return this.crudServicePartyFacility;
	}

	public void setCrudServicePartyFacility(CrudService<PartyFacility, Integer> crudServicePartyFacility)
	{
		this.crudServicePartyFacility = crudServicePartyFacility;
	}

	@Override
	public CrudService<FacilityContactMechanism, Integer> getCrudServiceFacilityContactMechanism()
	{
		return crudServiceFacilityContactMechanism;
	}

	public void setCrudServiceFacilityContactMechanism(
	        CrudService<FacilityContactMechanism, Integer> crudServiceFacilityContactMechanism)
	{
		this.crudServiceFacilityContactMechanism = crudServiceFacilityContactMechanism;
	}

	@Override
	public CrudService<Facility, Integer> getCrudServiceFacility()
	{
		return crudServiceFacility;
	}

	public void setCrudServiceFacility(CrudService<Facility, Integer> crudServiceFacility)
	{
		this.crudServiceFacility = crudServiceFacility;
	}

	public void setCrudServiceWorkEffort(CrudService<WorkEffort, Integer> crudServiceWorkEffort)
	{
		this.crudServiceWorkEffort = crudServiceWorkEffort;
	}

	@Override
	public CrudService<WorkEffort, Integer> getCrudServiceWorkEffort()
	{
		return crudServiceWorkEffort;
	}

	@Override
	public CrudService<WorkEffortType, Integer> getCrudServiceWorkEffortType()
	{
		return crudServiceWorkEffortType;
	}

	public void setCrudServiceWorkEffortType(CrudService<WorkEffortType, Integer> crudServiceWorkEffortType)
	{
		this.crudServiceWorkEffortType = crudServiceWorkEffortType;
	}

	@Override
    public CrudServiceWorkEffortPartyAssignment getCrudServiceWorkEffortPartyAssignment()
    {
        return crudServiceWorkEffortPartyAssignment;
    }

    public void setCrudServiceWorkEffortPartyAssignment(CrudServiceWorkEffortPartyAssignment crudServiceWorkEffortPartyAssignment)
    {
        this.crudServiceWorkEffortPartyAssignment = crudServiceWorkEffortPartyAssignment;
    }
}
