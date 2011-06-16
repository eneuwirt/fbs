package com.fbs.dmr.universal.service.impl;

import java.util.List;
import java.util.logging.Logger;

import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.dmr.universal.model.party.PartyRelationshipStatusType;
import com.fbs.dmr.universal.model.party.PartyRole;
import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.dmr.universal.model.party.Person;
import com.fbs.dmr.universal.model.party.PriorityType;
import com.fbs.dmr.universal.service.CrudService;
import com.fbs.dmr.universal.service.SeedService;
import com.fbs.dmr.universal.service.ServicePartyRole;
import com.fbs.dmr.universal.service.ServicePartyRoleType;

public class SeedServiceImpl implements SeedService
{
	private static Logger logger = Logger.getLogger(SeedServiceImpl.class.getName());
	private static final String ADVOCATE = "Anwalt";
	private static final String ASSISTANCE = "Assistenz"; 
	private static final String CLIENT_INVESTMENT = "Mandant Investition";
	private static final String CLIENT_OUTSTANDING = "Mandant Forderung";
	private static final String CLIENT_FISCAL_LAW = "Mandant Steuerrecht";
	private static final String FEMALE = "Frau";
	private static final String MALE = "Herr";
	private static final String PRIVATE = "Private";
	private static final String SUPPLIER = "Supplier"; 
	private CrudService<PartyRelationshipStatusType, Integer> servicePartyRelationshipStatusType;
	private ServicePartyRoleType servicePartyRoleType;
	private CrudService<PartyType, Integer> servicePartyType;
	private CrudService<PriorityType, Integer> servicePriorityType;
	private CrudService<Organization, Integer> crudServiceOrganization;
	private CrudService<Person, Integer> crudServicePerson;
	private ServicePartyRole servicePartyRole;

	@Override
	public void defaultFill()
	{
		logger.info(">defaultFill");
		// Party part
		this.createPartyType();
		this.createPartyRoleType();
		this.createPriorityType();
		this.createPartyRelationshipType();
		logger.info("<defaultFill");
	}

	private void createPartyRelationshipType()
	{
		List<PartyRelationshipStatusType> types;

		types = this.servicePartyRelationshipStatusType.findAll();

		if (types.size() == 0)
		{
			PartyRelationshipStatusType type;

			type = new PartyRelationshipStatusType();
			type.setDescription("Aktiv");
			this.servicePartyRelationshipStatusType.create(type);

			type = new PartyRelationshipStatusType();
			type.setDescription("Inaktiv");
			this.servicePartyRelationshipStatusType.create(type);
		}
	}

	private void createPriorityType()
	{
		List<PriorityType> priorityTypes;

		priorityTypes = this.servicePriorityType.findAll();

		if (priorityTypes.size() == 0)
		{
			PriorityType priorityType;

			priorityType = new PriorityType();
			priorityType.setDescription("Hoch");
			this.servicePriorityType.create(priorityType);

			priorityType = new PriorityType();
			priorityType.setDescription("Mittel");
			this.servicePriorityType.create(priorityType);

			priorityType = new PriorityType();
			priorityType.setDescription("Niedrig");
			this.servicePriorityType.create(priorityType);
		}
	}

	private void createPartyRoleType()
	{
		List<PartyRoleType> partyRoleTypes;

		partyRoleTypes = servicePartyRoleType.findAll();

		if (partyRoleTypes.size() == 0)
		{
			PartyRoleType partyRoleType;

			partyRoleType = new PartyRoleType();
			partyRoleType.setDescription(ADVOCATE);
			this.servicePartyRoleType.create(partyRoleType);
			
			partyRoleType = new PartyRoleType();
			partyRoleType.setDescription(CLIENT_FISCAL_LAW);
			this.servicePartyRoleType.create(partyRoleType);

			partyRoleType = new PartyRoleType();
			partyRoleType.setDescription(CLIENT_OUTSTANDING);
			this.servicePartyRoleType.create(partyRoleType);
			
			partyRoleType = new PartyRoleType();
			partyRoleType.setDescription(CLIENT_INVESTMENT);
			this.servicePartyRoleType.create(partyRoleType);

			partyRoleType = new PartyRoleType();
			partyRoleType.setDescription(ASSISTANCE);
			this.servicePartyRoleType.create(partyRoleType);
			
			partyRoleType = new PartyRoleType();
			partyRoleType.setDescription(SUPPLIER);
			this.servicePartyRoleType.create(partyRoleType);
			
			partyRoleType = new PartyRoleType();
			partyRoleType.setDescription(PRIVATE);
			this.servicePartyRoleType.create(partyRoleType);
		}
	}

	private void createPartyType()
	{
		List<PartyType> partyTypes;

		partyTypes = this.servicePartyType.findAll();

		if (partyTypes.size() == 0)
		{
			PartyType partyType;

			partyType = new PartyType();
			partyType.setDescription("Person");
			this.servicePartyType.create(partyType);

			partyType = new PartyType();
			partyType.setDescription("Organization");
			this.servicePartyType.create(partyType);
		}

	}

	public void setServicePartyType(CrudService<PartyType, Integer> servicePartyType)
	{
		this.servicePartyType = servicePartyType;
	}

	public void setServicePartyRoleType(ServicePartyRoleType servicePartyRoleType)
	{
		this.servicePartyRoleType = servicePartyRoleType;
	}

	public void setServicePriorityType(CrudService<PriorityType, Integer> servicePriorityType)
	{
		this.servicePriorityType = servicePriorityType;
	}

	public void setServicePartyRelationshipStatusType(
	        CrudService<PartyRelationshipStatusType, Integer> servicePartyRelationshipStatusType)
	{
		this.servicePartyRelationshipStatusType = servicePartyRelationshipStatusType;
	}

	@Override
	public void demoFill()
	{
		logger.info(">demoFill");

		for (Organization o : this.crudServiceOrganization.findAll())
		{
			if (o.getName().equals("Querformat GmbH"))
				;
			{
				return;
			}
		}

		this.createDemoPersons();

		this.createDemoOrgs();

		logger.info("<demoFill");
	}

	private void createDemoOrgs()
    {
		Organization org;
		PartyRoleType partyRoleType;
		PartyRole partyRole;
		
		org = new Organization();
		org.setName("Bürogemeinschaft Klaglos & Ratlos");
		this.crudServiceOrganization.create(org);

		//
		org = new Organization();
		org.setName("Querformat GmbH");
		this.crudServiceOrganization.create(org);
		partyRoleType = this.servicePartyRoleType.findForDescription(SUPPLIER);
		partyRole = new PartyRole();
		partyRole.setParty(org);
		partyRole.setPartyRoleType(partyRoleType);
		this.servicePartyRole.create(partyRole);
		
		partyRoleType = this.servicePartyRoleType.findForDescription(CLIENT_OUTSTANDING);
		partyRole = new PartyRole();
		partyRole.setParty(org);
		partyRole.setPartyRoleType(partyRoleType);
		this.servicePartyRole.create(partyRole);
    }

	private void createDemoPersons()
	{
		Person person;
		PartyRoleType partyRoleType;
		PartyRole partyRole;

		person = new Person();
		person.setGender(MALE);
		person.setLastName("Ratlos");
		this.crudServicePerson.create(person);
		partyRoleType = this.servicePartyRoleType.findForDescription(ADVOCATE);
		partyRole = new PartyRole();
		partyRole.setParty(person);
		partyRole.setPartyRoleType(partyRoleType);
		this.servicePartyRole.create(partyRole);

		person = new Person();
		person.setLastName("Klaglos");
		person.setGender(MALE);
		this.crudServicePerson.create(person);
		partyRoleType = this.servicePartyRoleType.findForDescription(ADVOCATE);
		partyRole = new PartyRole();
		partyRole.setParty(person);
		partyRole.setPartyRoleType(partyRoleType);
		this.servicePartyRole.create(partyRole);

		person = new Person();
		person.setGender(FEMALE);
		person.setLastName("Walküre");
		this.crudServicePerson.create(person);
		partyRoleType = this.servicePartyRoleType.findForDescription(ASSISTANCE);
		partyRole = new PartyRole();
		partyRole.setParty(person);
		partyRole.setPartyRoleType(partyRoleType);
		this.servicePartyRole.create(partyRole);
		
		person = new Person();
		person.setGender(MALE);
		person.setLastName("Reinweiß");
		this.crudServicePerson.create(person);
		partyRoleType = this.servicePartyRoleType.findForDescription(CLIENT_INVESTMENT);
		partyRole = new PartyRole();
		partyRole.setParty(person);
		partyRole.setPartyRoleType(partyRoleType);
		this.servicePartyRole.create(partyRole);
		
		person = new Person();
		person.setGender(MALE);
		person.setLastName("Lahm");
		this.crudServicePerson.create(person);
		partyRoleType = this.servicePartyRoleType.findForDescription(PRIVATE);
		partyRole = new PartyRole();
		partyRole.setParty(person);
		partyRole.setPartyRoleType(partyRoleType);
		this.servicePartyRole.create(partyRole);
		
	}

	public void setCrudServiceOrganization(CrudService<Organization, Integer> crudServiceOrganization)
	{
		this.crudServiceOrganization = crudServiceOrganization;
	}

	public void setCrudServicePerson(CrudService<Person, Integer> crudServicePerson)
	{
		this.crudServicePerson = crudServicePerson;
	}

	public void setServicePartyRole(ServicePartyRole servicePartyRole)
	{
		this.servicePartyRole = servicePartyRole;
	}
}
