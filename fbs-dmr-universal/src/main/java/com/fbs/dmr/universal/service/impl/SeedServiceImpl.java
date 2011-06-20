package com.fbs.dmr.universal.service.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import com.fbs.dmr.universal.model.contact.ContactMechanismPurposeType;
import com.fbs.dmr.universal.model.contact.ContactMechanismType;
import com.fbs.dmr.universal.model.contact.ElectronicAddress;
import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.dmr.universal.model.party.PartyRelationshipStatusType;
import com.fbs.dmr.universal.model.party.PartyRole;
import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.dmr.universal.model.party.Person;
import com.fbs.dmr.universal.model.party.PriorityType;
import com.fbs.dmr.universal.service.CrudService;
import com.fbs.dmr.universal.service.CrudServiceType;
import com.fbs.dmr.universal.service.SeedService;
import com.fbs.dmr.universal.service.ServicePartyRole;

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
	private static final String CONTACT_TYPE_EMAIL = "E-Mail";
	private static final String CONTACT_TYPE_FAX = "Fax";
	private static final String CONTACT_TYPE_MOBILE = "Mobil";
	private static final String CONTACT_TYPE_PHONE = "Telefon";
	private static final String CONTACT_TYPE_POSTAL = "Post Anschrift";
	private static final String CONTACT_TYPE_WEB = "Web-Adresse";
	private static final String DEMO_STREET = "A4";
	private static final String DEMO_CITY = "Mannheim";
	private static final String DEMO_ZIP = "69011";
	@Resource(name="crudServicePartyContactMechanism")
	private CrudService<PartyContactMechanism, Integer> crudServicePartyContactMechanism;
	@Resource(name="crudServiceElectronicAddress")
	private CrudService<ElectronicAddress, Integer> crudServiceElectronicAddress;
	@Resource(name="crudServicePostalAddress")
	private CrudService<PostalAddress, Integer> crudServicePostalAddress;
	@Resource(name="crudServiceContactMechanismPurposeType")
	private CrudServiceType<ContactMechanismPurposeType, Integer> crudServiceContactMechanismPurposeType;
	@Resource(name="crudServiceContactMechanismType")
	private CrudServiceType<ContactMechanismType, Integer> crudServiceContactMechanismType;
	@Resource(name="crudServicePartyRelationshipStatusType")
	private CrudServiceType<PartyRelationshipStatusType, Integer> crudServicePartyRelationshipStatusType;
	@Resource(name="crudServicePartyRoleType")
	private CrudServiceType<PartyRoleType, Integer> crudServicePartyRoleType;
	@Resource(name="crudServicePartyType")
	private CrudService<PartyType, Integer> crudServicePartyType;
	@Resource(name="crudServicePriorityType")
	private CrudServiceType<PriorityType, Integer> crudServicePriorityType;
	@Resource(name="crudServiceOrganization")
	private CrudService<Organization, Integer> crudServiceOrganization;
	@Resource(name="crudServicePerson")
	private CrudService<Person, Integer> crudServicePerson;
	@Resource(name="crudServicePartyRole")
	private ServicePartyRole servicePartyRole;
	
	@Override
	public void defaultFill()
	{
		logger.info(">defaultFill");
		// Contact part
		this.createContactMechanismPurposeType();
		this.createContactMechanismType();
		// Party part
		this.createPartyType();
		this.createPartyRoleType();
		this.createPriorityType();
		this.createPartyRelationshipType();
		logger.info("<defaultFill");
	}

	private void createContactMechanismPurposeType()
	{
		List<ContactMechanismPurposeType> types;

		types = this.crudServiceContactMechanismPurposeType.findAll();

		if (types.size() == 0)
		{
			ContactMechanismPurposeType type;
			String HEAD_QUARTERS = "Die Zentrale";
			String GENERAL_PHONE = "Haupttelefonkontakt";
			String GENERAL_FAX = "Fax";
			String SECONDARY_FAX = "Zweite Faxnummer";

			type = new ContactMechanismPurposeType();
			type.setDescription(HEAD_QUARTERS);
			this.crudServiceContactMechanismPurposeType.create(type);

			type = new ContactMechanismPurposeType();
			type.setDescription(GENERAL_PHONE);
			this.crudServiceContactMechanismPurposeType.create(type);

			type = new ContactMechanismPurposeType();
			type.setDescription(GENERAL_FAX);
			this.crudServiceContactMechanismPurposeType.create(type);

			type = new ContactMechanismPurposeType();
			type.setDescription(SECONDARY_FAX);
			this.crudServiceContactMechanismPurposeType.create(type);
		}
	}

	private void createContactMechanismType()
	{
		List<ContactMechanismType> types;

		types = this.crudServiceContactMechanismType.findAll();

		if (types.size() == 0)
		{
			ContactMechanismType type;

			type = new ContactMechanismType();
			type.setDescription(CONTACT_TYPE_PHONE);
			this.crudServiceContactMechanismType.create(type);

			type = new ContactMechanismType();
			type.setDescription(CONTACT_TYPE_FAX);
			this.crudServiceContactMechanismType.create(type);

			type = new ContactMechanismType();
			type.setDescription(CONTACT_TYPE_POSTAL);
			this.crudServiceContactMechanismType.create(type);

			type = new ContactMechanismType();
			type.setDescription(CONTACT_TYPE_EMAIL);
			this.crudServiceContactMechanismType.create(type);

			type = new ContactMechanismType();
			type.setDescription(CONTACT_TYPE_MOBILE);
			this.crudServiceContactMechanismType.create(type);

			type = new ContactMechanismType();
			type.setDescription(CONTACT_TYPE_WEB);
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

	private void createPartyRoleType()
	{
		List<PartyRoleType> partyRoleTypes;

		partyRoleTypes = crudServicePartyRoleType.findAll();

		if (partyRoleTypes.size() == 0)
		{
			PartyRoleType partyRoleType;

			partyRoleType = new PartyRoleType();
			partyRoleType.setDescription(ADVOCATE);
			this.crudServicePartyRoleType.create(partyRoleType);

			partyRoleType = new PartyRoleType();
			partyRoleType.setDescription(CLIENT_FISCAL_LAW);
			this.crudServicePartyRoleType.create(partyRoleType);

			partyRoleType = new PartyRoleType();
			partyRoleType.setDescription(CLIENT_OUTSTANDING);
			this.crudServicePartyRoleType.create(partyRoleType);

			partyRoleType = new PartyRoleType();
			partyRoleType.setDescription(CLIENT_INVESTMENT);
			this.crudServicePartyRoleType.create(partyRoleType);

			partyRoleType = new PartyRoleType();
			partyRoleType.setDescription(ASSISTANCE);
			this.crudServicePartyRoleType.create(partyRoleType);

			partyRoleType = new PartyRoleType();
			partyRoleType.setDescription(SUPPLIER);
			this.crudServicePartyRoleType.create(partyRoleType);

			partyRoleType = new PartyRoleType();
			partyRoleType.setDescription(PRIVATE);
			this.crudServicePartyRoleType.create(partyRoleType);
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

	@Override
	public void demoFill()
	{
		logger.info(">demoFill");

		for (Organization o : this.crudServiceOrganization.findAll())
		{
			if (o.getName().equals("Querformat GmbH"))
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
		PostalAddress postalAddress;
		ElectronicAddress electronicAddress;
		PartyContactMechanism pcm;
		ContactMechanismType contactMechanismType;
	
		// ************************************************************************************************
		org = new Organization();
		org.setName("Bürogemeinschaft Klaglos & Ratlos");
		this.crudServiceOrganization.create(org);
		//
		contactMechanismType = this.crudServiceContactMechanismType.findForDescription(CONTACT_TYPE_POSTAL);
		postalAddress = new PostalAddress();
		postalAddress.setAddress1("Gerechtigkeitstraße 7");
		postalAddress.setCity(DEMO_CITY);
		postalAddress.setPostalCode(DEMO_ZIP);
		postalAddress.setContactMechanismType(contactMechanismType);
		this.crudServicePostalAddress.create(postalAddress);
		pcm = new PartyContactMechanism();
		pcm.setContactMechanism(postalAddress);
		pcm.setParty(org);
		this.crudServicePartyContactMechanism.create(pcm);
		//
		contactMechanismType = this.crudServiceContactMechanismType.findForDescription(CONTACT_TYPE_EMAIL);
		electronicAddress = new ElectronicAddress();
		electronicAddress.setContactMechanismType(contactMechanismType);
		electronicAddress.setElectronicAddress("contact@klaglos-ratlos.de");
		this.crudServiceElectronicAddress.create(electronicAddress);
		pcm = new PartyContactMechanism();
		pcm.setContactMechanism(electronicAddress);
		pcm.setParty(org);
		this.crudServicePartyContactMechanism.create(pcm);	

		// ************************************************************************************************
		org = new Organization();
		org.setName("Querformat GmbH");
		this.crudServiceOrganization.create(org);
		partyRoleType = this.crudServicePartyRoleType.findForDescription(SUPPLIER);
		partyRole = new PartyRole();
		partyRole.setParty(org);
		partyRole.setPartyRoleType(partyRoleType);
		this.servicePartyRole.create(partyRole);
		//
		contactMechanismType = this.crudServiceContactMechanismType.findForDescription(CONTACT_TYPE_POSTAL);
		postalAddress = new PostalAddress();
		postalAddress.setAddress1(DEMO_STREET);
		postalAddress.setCity(DEMO_CITY);
		postalAddress.setPostalCode(DEMO_ZIP);
		postalAddress.setContactMechanismType(contactMechanismType);
		this.crudServicePostalAddress.create(postalAddress);
		pcm = new PartyContactMechanism();
		pcm.setContactMechanism(postalAddress);
		pcm.setParty(org);
		this.crudServicePartyContactMechanism.create(pcm);	
		//
		contactMechanismType = this.crudServiceContactMechanismType.findForDescription(CONTACT_TYPE_EMAIL);
		electronicAddress = new ElectronicAddress();
		electronicAddress.setContactMechanismType(contactMechanismType);
		electronicAddress.setElectronicAddress("info@querformat.com");
		this.crudServiceElectronicAddress.create(electronicAddress);
		pcm = new PartyContactMechanism();
		pcm.setContactMechanism(electronicAddress);
		pcm.setParty(org);
		this.crudServicePartyContactMechanism.create(pcm);	
		

		partyRoleType = this.crudServicePartyRoleType.findForDescription(CLIENT_OUTSTANDING);
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
		partyRoleType = this.crudServicePartyRoleType.findForDescription(ADVOCATE);
		partyRole = new PartyRole();
		partyRole.setParty(person);
		partyRole.setPartyRoleType(partyRoleType);
		this.servicePartyRole.create(partyRole);

		person = new Person();
		person.setLastName("Klaglos");
		person.setGender(MALE);
		this.crudServicePerson.create(person);
		partyRoleType = this.crudServicePartyRoleType.findForDescription(ADVOCATE);
		partyRole = new PartyRole();
		partyRole.setParty(person);
		partyRole.setPartyRoleType(partyRoleType);
		this.servicePartyRole.create(partyRole);

		person = new Person();
		person.setGender(FEMALE);
		person.setLastName("Walküre");
		this.crudServicePerson.create(person);
		partyRoleType = this.crudServicePartyRoleType.findForDescription(ASSISTANCE);
		partyRole = new PartyRole();
		partyRole.setParty(person);
		partyRole.setPartyRoleType(partyRoleType);
		this.servicePartyRole.create(partyRole);

		person = new Person();
		person.setGender(MALE);
		person.setLastName("Reinweiß");
		this.crudServicePerson.create(person);
		partyRoleType = this.crudServicePartyRoleType.findForDescription(CLIENT_INVESTMENT);
		partyRole = new PartyRole();
		partyRole.setParty(person);
		partyRole.setPartyRoleType(partyRoleType);
		this.servicePartyRole.create(partyRole);

		person = new Person();
		person.setGender(MALE);
		person.setLastName("Lahm");
		this.crudServicePerson.create(person);
		partyRoleType = this.crudServicePartyRoleType.findForDescription(PRIVATE);
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

	public void setServicePartyRole(ServicePartyRole servicePartyRole)
	{
		this.servicePartyRole = servicePartyRole;
	}

	public void setCrudServicePostalAddress(CrudService<PostalAddress, Integer> crudServicePostalAddress)
    {
	    this.crudServicePostalAddress = crudServicePostalAddress;
    }

	public CrudService<PostalAddress, Integer> getCrudServicePostalAddress()
    {
	    return crudServicePostalAddress;
    }

	public void setCrudServicePartyContactMechanism(CrudService<PartyContactMechanism, Integer> crudServicePartyContactMechanism)
    {
	    this.crudServicePartyContactMechanism = crudServicePartyContactMechanism;
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

	public void setCrudServiceElectronicAddress(CrudService<ElectronicAddress, Integer> crudServiceElectronicAddress)
    {
	    this.crudServiceElectronicAddress = crudServiceElectronicAddress;
    }
}
