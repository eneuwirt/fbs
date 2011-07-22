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
import com.fbs.dmr.universal.service.customizing.ContactTypeValues;
import com.fbs.dmr.universal.service.customizing.PartyRelationshipStatusValues;
import com.fbs.dmr.universal.service.customizing.PriorityTypeValues;
import com.fbs.dmr.universal.service.seed.SeedService;

/**
 * Core customizing values.
 * 
 * @author neuwirt
 *
 */
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
			type.setDescription(ContactTypeValues.PHONE);
			this.crudServiceContactMechanismType.create(type);

			type = new ContactMechanismType();
			type.setDescription(ContactTypeValues.FAX);
			this.crudServiceContactMechanismType.create(type);

			type = new ContactMechanismType();
			type.setDescription(ContactTypeValues.POSTAL);
			this.crudServiceContactMechanismType.create(type);

			type = new ContactMechanismType();
			type.setDescription(ContactTypeValues.EMAIL);
			this.crudServiceContactMechanismType.create(type);

			type = new ContactMechanismType();
			type.setDescription(ContactTypeValues.MOBILE);
			this.crudServiceContactMechanismType.create(type);

			type = new ContactMechanismType();
			type.setDescription(ContactTypeValues.WEB);
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
			type.setDescription(PartyRelationshipStatusValues.ACTIVE);
			this.crudServicePartyRelationshipStatusType.create(type);

			type = new PartyRelationshipStatusType();
			type.setDescription(PartyRelationshipStatusValues.INACTIVE);
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
			priorityType.setDescription(PriorityTypeValues.HIGH);
			this.crudServicePriorityType.create(priorityType);

			priorityType = new PriorityType();
			priorityType.setDescription(PriorityTypeValues.MIDDLE);
			this.crudServicePriorityType.create(priorityType);

			priorityType = new PriorityType();
			priorityType.setDescription(PriorityTypeValues.LOW);
			this.crudServicePriorityType.create(priorityType);
		}
	}
}
