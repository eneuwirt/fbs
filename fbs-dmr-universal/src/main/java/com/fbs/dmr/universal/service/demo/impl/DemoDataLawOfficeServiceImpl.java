package com.fbs.dmr.universal.service.demo.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import com.fbs.dmr.universal.model.contact.ContactMechanismPurposeType;
import com.fbs.dmr.universal.model.contact.ContactMechanismType;
import com.fbs.dmr.universal.model.contact.ElectronicAddress;
import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.dmr.universal.model.contact.TelecommunicationNumber;
import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.dmr.universal.model.party.PartyContactMechanismPurpose;
import com.fbs.dmr.universal.model.party.PartyRelationshipStatusType;
import com.fbs.dmr.universal.model.party.PartyRelationshipType;
import com.fbs.dmr.universal.model.party.PartyRole;
import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.dmr.universal.model.party.Person;
import com.fbs.dmr.universal.model.party.PriorityType;
import com.fbs.dmr.universal.service.crud.CrudService;
import com.fbs.dmr.universal.service.crud.CrudServicePartyRole;
import com.fbs.dmr.universal.service.crud.CrudServiceType;
import com.fbs.dmr.universal.service.customizing.ContactType;
import com.fbs.dmr.universal.service.demo.DemoDataService;

public class DemoDataLawOfficeServiceImpl implements DemoDataService
{
    private static Logger logger = Logger.getLogger(DemoDataLawOfficeServiceImpl.class.getName());
    private static final String SUPPLIER = "Supplier";
    private static final String DEMO_STREET = "A4";
    private static final String DEMO_CITY = "Mannheim";
    private static final String DEMO_ZIP = "69011";
    private static final String PARTY_ROLE_TYPE_REPRESENT_INVESTMENT = "Beratung in Investitionen";
    private static final String ADVOCATE = "Anwalt";
    private static final String ASSISTANCE = "Assistenz";
    private static final String CLIENT_INVESTMENT = "Mandant Investition";
    private static final String CLIENT_OUTSTANDING = "Mandant Forderung";
    private static final String CLIENT_FISCAL_LAW = "Mandant Steuerrecht";
    private static final String FEMALE = "Frau";
    private static final String MALE = "Herr";
    private static final String PRIVATE = "Private";
    private static final String PURPOSE_HEAD_QUARTERS = "Die Zentrale";
    private static final String PURPOSE_GENERAL_PHONE = "Haupttelefonkontakt";
    private static final String PURPOSE_GENERAL_FAX = "Fax";
    private static final String PURPOSE_SECONDARY_FAX = "Zweite Faxnummer";
    // *******************************************************************************
    @Resource(name = "crudServicePartyContactMechanism")
    CrudService<PartyContactMechanism, Integer> crudServicePartyContactMechanism;
    @Resource(name = "crudServiceElectronicAddress")
    CrudService<ElectronicAddress, Integer> crudServiceElectronicAddress;
    @Resource(name = "crudServiceTelecommunicationNumber")
    CrudService<TelecommunicationNumber, Integer> crudServiceTelecommunicationNumber;
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
        
        this.createPartyRoleType();
        
        this.createContactMechanismPurposeType();

        this.createDemoPersons();

        this.createDemoOrgs();

        this.createDemoRelationshipTypes();

        logger.info("<demoFill");
    }
    
    private void createContactMechanismPurposeType()
    {
        List<ContactMechanismPurposeType> types;

        types = this.crudServiceContactMechanismPurposeType.findAll();

        if (types.size() == 0)
        {
            ContactMechanismPurposeType type;

            type = new ContactMechanismPurposeType();
            type.setDescription(PURPOSE_HEAD_QUARTERS);
            this.crudServiceContactMechanismPurposeType.create(type);

            type = new ContactMechanismPurposeType();
            type.setDescription(PURPOSE_GENERAL_PHONE);
            this.crudServiceContactMechanismPurposeType.create(type);

            type = new ContactMechanismPurposeType();
            type.setDescription(PURPOSE_GENERAL_FAX);
            this.crudServiceContactMechanismPurposeType.create(type);

            type = new ContactMechanismPurposeType();
            type.setDescription(PURPOSE_SECONDARY_FAX);
            this.crudServiceContactMechanismPurposeType.create(type);
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

    private void createDemoOrgs()
    {
        Organization org;
        PartyRoleType partyRoleType;
        PartyRole partyRole;
        PostalAddress postalAddress;
        ElectronicAddress electronicAddress;
        PartyContactMechanism pcm;
        PartyContactMechanismPurpose pcmp;
        ContactMechanismType contactMechanismType;
        ContactMechanismPurposeType contactMechanismPurposeType;

        // ************************************************************************************************
        org = new Organization();
        org.setName("Bürogemeinschaft Klaglos & Ratlos");
        this.crudServiceOrganization.create(org);
        //
        contactMechanismType = this.crudServiceContactMechanismType.findForDescription(ContactType.POSTAL);
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
        contactMechanismType = this.crudServiceContactMechanismType.findForDescription(ContactType.EMAIL);
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
        this.crudServicePartyRole.create(partyRole);
        //
        contactMechanismType = this.crudServiceContactMechanismType.findForDescription(ContactType.POSTAL);
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
        contactMechanismPurposeType = this.crudServiceContactMechanismPurposeType.findForDescription(PURPOSE_HEAD_QUARTERS);
        pcmp = new PartyContactMechanismPurpose();
        pcmp.setPartyContactMechanism(pcm);
        pcmp.setContactMechanismPurposeType(contactMechanismPurposeType);
        this.crudServicePartyContactMechanismPurpose.create(pcmp);

        //
        contactMechanismType = this.crudServiceContactMechanismType.findForDescription(ContactType.EMAIL);
        electronicAddress = new ElectronicAddress();
        electronicAddress.setContactMechanismType(contactMechanismType);
        electronicAddress.setElectronicAddress("info@querformat.com");
        this.crudServiceElectronicAddress.create(electronicAddress);
        pcm = new PartyContactMechanism();
        pcm.setContactMechanism(electronicAddress);
        pcm.setParty(org);
        this.crudServicePartyContactMechanism.create(pcm);
        //
        partyRoleType = this.crudServicePartyRoleType.findForDescription(CLIENT_OUTSTANDING);
        partyRole = new PartyRole();
        partyRole.setParty(org);
        partyRole.setPartyRoleType(partyRoleType);
        this.crudServicePartyRole.create(partyRole);
    }

    private void createDemoPersons()
    {
        Person person;
        PartyRoleType partyRoleType;
        PartyRole partyRole;
        PostalAddress postalAddress;
        TelecommunicationNumber phone;
        PartyContactMechanism pcm;
        ContactMechanismType contactMechanismType;

        person = new Person();
        person.setGender(MALE);
        person.setLastName("Ratlos");
        this.crudServicePerson.create(person);
        partyRoleType = this.crudServicePartyRoleType.findForDescription(ADVOCATE);
        partyRole = new PartyRole();
        partyRole.setParty(person);
        partyRole.setPartyRoleType(partyRoleType);
        this.crudServicePartyRole.create(partyRole);

        person = new Person();
        person.setLastName("Klaglos");
        person.setGender(MALE);
        this.crudServicePerson.create(person);
        partyRoleType = this.crudServicePartyRoleType.findForDescription(ADVOCATE);
        partyRole = new PartyRole();
        partyRole.setParty(person);
        partyRole.setPartyRoleType(partyRoleType);
        this.crudServicePartyRole.create(partyRole);

        person = new Person();
        person.setGender(FEMALE);
        person.setLastName("Walküre");
        this.crudServicePerson.create(person);
        partyRoleType = this.crudServicePartyRoleType.findForDescription(ASSISTANCE);
        partyRole = new PartyRole();
        partyRole.setParty(person);
        partyRole.setPartyRoleType(partyRoleType);
        this.crudServicePartyRole.create(partyRole);

        person = new Person();
        person.setGender(MALE);
        person.setLastName("Reinweiß");
        this.crudServicePerson.create(person);
        partyRoleType = this.crudServicePartyRoleType.findForDescription(CLIENT_INVESTMENT);
        partyRole = new PartyRole();
        partyRole.setParty(person);
        partyRole.setPartyRoleType(partyRoleType);
        this.crudServicePartyRole.create(partyRole);
        //
        contactMechanismType = this.crudServiceContactMechanismType.findForDescription(ContactType.POSTAL);
        postalAddress = new PostalAddress();
        postalAddress.setAddress1(DEMO_STREET);
        postalAddress.setCity(DEMO_CITY);
        postalAddress.setPostalCode(DEMO_ZIP);
        postalAddress.setContactMechanismType(contactMechanismType);
        this.crudServicePostalAddress.create(postalAddress);
        pcm = new PartyContactMechanism();
        pcm.setContactMechanism(postalAddress);
        pcm.setParty(person);
        this.crudServicePartyContactMechanism.create(pcm);
        //
        contactMechanismType = this.crudServiceContactMechanismType.findForDescription(ContactType.PHONE);
        phone = new TelecommunicationNumber();
        phone.setNumber("0815 – 10");
        phone.setContactMechanismType(contactMechanismType);
        this.crudServiceTelecommunicationNumber.create(phone);
        pcm = new PartyContactMechanism();
        pcm.setContactMechanism(phone);
        pcm.setParty(person);
        this.crudServicePartyContactMechanism.create(pcm);

        person = new Person();
        person.setGender(MALE);
        person.setLastName("Lahm");
        this.crudServicePerson.create(person);
        partyRoleType = this.crudServicePartyRoleType.findForDescription(PRIVATE);
        partyRole = new PartyRole();
        partyRole.setParty(person);
        partyRole.setPartyRoleType(partyRoleType);
        this.crudServicePartyRole.create(partyRole);
    }
    
    private void createDemoRelationshipTypes()
    {
        PartyRelationshipType partyRelationshipType;
        PartyRoleType partyRoleTypeFrom;
        PartyRoleType partyRoleTypeTo;
        
        partyRoleTypeFrom = this.crudServicePartyRoleType.findForDescription(ADVOCATE);
        partyRoleTypeTo =  this.crudServicePartyRoleType.findForDescription(CLIENT_INVESTMENT);
        
        
        partyRelationshipType = new PartyRelationshipType();
        partyRelationshipType.setName(PARTY_ROLE_TYPE_REPRESENT_INVESTMENT);
        partyRelationshipType.setPartyRoleTypeFrom(partyRoleTypeFrom);
        partyRelationshipType.setPartyRoleTypeTo(partyRoleTypeTo);
        
        this.crudServicePartyRelationshipType.create(partyRelationshipType);
    }
}
