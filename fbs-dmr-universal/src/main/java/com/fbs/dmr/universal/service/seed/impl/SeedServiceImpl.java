package com.fbs.dmr.universal.service.seed.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import com.fbs.dmr.universal.model.contact.ContactMechanismPurposeType;
import com.fbs.dmr.universal.model.contact.ContactMechanismType;
import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.dmr.universal.model.party.PartyContactMechanismPurpose;
import com.fbs.dmr.universal.model.party.PartyRelationshipStatusType;
import com.fbs.dmr.universal.model.party.PartyRelationshipType;
import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.dmr.universal.model.party.Person;
import com.fbs.dmr.universal.model.party.PriorityType;
import com.fbs.dmr.universal.service.crud.CrudService;
import com.fbs.dmr.universal.service.crud.CrudServicePartyRole;
import com.fbs.dmr.universal.service.crud.CrudServiceType;
import com.fbs.dmr.universal.service.customizing.ContactType;
import com.fbs.dmr.universal.service.seed.SeedService;

public class SeedServiceImpl implements SeedService
{
	private static Logger logger = Logger.getLogger(SeedServiceImpl.class.getName());
	// *******************************************************************************
	@Resource(name = "crudServicePostalAddress")
	CrudService<PostalAddress, Integer> crudServicePostalAddress;
	@Resource(name = "crudServiceContactMechanismPurposeType")
	CrudServiceType<ContactMechanismPurposeType, Integer> crudServiceContactMechanismPurposeType;
	@Resource(name = "crudServiceContactMechanismType")
	CrudServiceType<ContactMechanismType, Integer> crudServiceContactMechanismType;	
	@Resource(name = "crudServicePartyContactMechanismPurpose")
	CrudService<PartyContactMechanismPurpose, Integer> crudServicePartyContactMechanismPurpose;
	@Resource(name = "crudServicePartyRelationshipStatusType")
	CrudServiceType<PartyRelationshipType, Integer> crudServicePartyRelationshipType;
	@Resource(name = "crudServicePartyRelationshipStatusType")
	CrudServiceType<PartyRelationshipStatusType, Integer> crudServicePartyRelationshipStatusType;
	@Resource(name = "crudServicePartyRoleType")
	CrudServiceType<PartyRoleType, Integer> crudServicePartyRoleType;
	@Resource(name = "crudServicePartyType")
	CrudService<PartyType, Integer> crudServicePartyType;
	@Resource(name = "crudServicePriorityType")
	CrudServiceType<PriorityType, Integer> crudServicePriorityType;
	@Resource(name = "crudServiceOrganization")
	CrudService<Organization, Integer> crudServiceOrganization;
	@Resource(name = "crudServicePerson")
	CrudService<Person, Integer> crudServicePerson;
	@Resource(name = "crudServicePartyRole")
	CrudServicePartyRole crudServicePartyRole;

	@Override
	public void defaultFill()
	{
		logger.info(">defaultFill");
		// Contact part
		this.createContactMechanismType();
		// Party part
		this.createPartyType();
		this.createPriorityType();
		this.createPartyRelationshipType();
		logger.info("<defaultFill");
	}

	private void createContactMechanismType()
	{
		List<ContactMechanismType> types;

		types = this.crudServiceContactMechanismType.findAll();

		if (types.size() == 0)
		{
			ContactMechanismType type;

			type = new ContactMechanismType();
			type.setDescription(ContactType.PHONE);
			this.crudServiceContactMechanismType.create(type);

			type = new ContactMechanismType();
			type.setDescription(ContactType.FAX);
			this.crudServiceContactMechanismType.create(type);

			type = new ContactMechanismType();
			type.setDescription(ContactType.POSTAL);
			this.crudServiceContactMechanismType.create(type);

			type = new ContactMechanismType();
			type.setDescription(ContactType.EMAIL);
			this.crudServiceContactMechanismType.create(type);

			type = new ContactMechanismType();
			type.setDescription(ContactType.MOBILE);
			this.crudServiceContactMechanismType.create(type);

			type = new ContactMechanismType();
			type.setDescription(ContactType.WEB);
			this.crudServiceContactMechanismType.create(type);
		}
	}

	private void createPartyRelationshipType()
	{
		List<PartyRelationshipStatusType> types;

		types = this.crudServicePartyRelationshipStatusType.findAll();

		if (types.size() == 0)
		{
			PartyRelationshipStatusType type;

			type = new PartyRelationshipStatusType();
			type.setDescription("Aktiv");
			this.crudServicePartyRelationshipStatusType.create(type);

			type = new PartyRelationshipStatusType();
			type.setDescription("Inaktiv");
			this.crudServicePartyRelationshipStatusType.create(type);
		}
	}

	private void createPriorityType()
	{
		List<PriorityType> priorityTypes;

		priorityTypes = this.crudServicePriorityType.findAll();

		if (priorityTypes.size() == 0)
		{
			PriorityType priorityType;

			priorityType = new PriorityType();
			priorityType.setDescription("Hoch");
			this.crudServicePriorityType.create(priorityType);

			priorityType = new PriorityType();
			priorityType.setDescription("Mittel");
			this.crudServicePriorityType.create(priorityType);

			priorityType = new PriorityType();
			priorityType.setDescription("Niedrig");
			this.crudServicePriorityType.create(priorityType);
		}
	}

	

	private void createPartyType()
	{
		List<PartyType> partyTypes;

		partyTypes = this.crudServicePartyType.findAll();

		if (partyTypes.size() == 0)
		{
			PartyType partyType;

			partyType = new PartyType();
			partyType.setDescription("Person");
			this.crudServicePartyType.create(partyType);

			partyType = new PartyType();
			partyType.setDescription("Organization");
			this.crudServicePartyType.create(partyType);
		}

	}	

	public void setCrudServiceOrganization(CrudService<Organization, Integer> crudServiceOrganization)
	{
		this.crudServiceOrganization = crudServiceOrganization;
	}

	public void setCrudServicePerson(CrudService<Person, Integer> crudServicePerson)
	{
		this.crudServicePerson = crudServicePerson;
	}

	public void setCrudServiceContactMechanismType(
	        CrudServiceType<ContactMechanismType, Integer> crudServiceContactMechanismType)
	{
		this.crudServiceContactMechanismType = crudServiceContactMechanismType;
	}

	public void setCrudServiceContactMechanismPurposeType(
	        CrudServiceType<ContactMechanismPurposeType, Integer> crudServiceContactMechanismPurposeType)
	{
		this.crudServiceContactMechanismPurposeType = crudServiceContactMechanismPurposeType;
	}

	public void setServicePartyRole(CrudServicePartyRole crudServicePartyRole)
	{
		this.crudServicePartyRole = crudServicePartyRole;
	}

	public void setCrudServicePostalAddress(CrudService<PostalAddress, Integer> crudServicePostalAddress)
	{
		this.crudServicePostalAddress = crudServicePostalAddress;
	}

	public CrudService<PostalAddress, Integer> getCrudServicePostalAddress()
	{
		return crudServicePostalAddress;
	}

	public void setServicePartyType(CrudService<PartyType, Integer> servicePartyType)
	{
		this.crudServicePartyType = servicePartyType;
	}

	public void setServicePartyRoleType(CrudServiceType<PartyRoleType, Integer> servicePartyRoleType)
	{
		this.crudServicePartyRoleType = servicePartyRoleType;
	}

	public void setServicePriorityType(CrudServiceType<PriorityType, Integer> servicePriorityType)
	{
		this.crudServicePriorityType = servicePriorityType;
	}

	public void setServicePartyRelationshipStatusType(
	        CrudServiceType<PartyRelationshipStatusType, Integer> servicePartyRelationshipStatusType)
	{
		this.crudServicePartyRelationshipStatusType = servicePartyRelationshipStatusType;
	}

	public void setCrudServicePartyContactMechanismPurpose(CrudServiceType<PartyContactMechanismPurpose, Integer> crudServicePartyContactMechanismPurpose)
    {
	    this.crudServicePartyContactMechanismPurpose = crudServicePartyContactMechanismPurpose;
    }

	public void setCrudServicePartyRelationshipType(CrudServiceType<PartyRelationshipType, Integer> crudServicePartyRelationshipType)
    {
	    this.crudServicePartyRelationshipType = crudServicePartyRelationshipType;
    }
}
