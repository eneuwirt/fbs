package com.fbs.web.vaadin.application;

import com.fbs.dmr.universal.model.contact.ContactMechanismPurposeType;
import com.fbs.dmr.universal.model.contact.ContactMechanismType;
import com.fbs.dmr.universal.model.contact.ElectronicAddress;
import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.dmr.universal.model.contact.TelecommunicationNumber;
import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.dmr.universal.model.party.PartyRelationship;
import com.fbs.dmr.universal.model.party.PartyRelationshipStatusType;
import com.fbs.dmr.universal.model.party.PartyRelationshipType;
import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.dmr.universal.model.party.Person;
import com.fbs.dmr.universal.model.party.PriorityType;
import com.fbs.dmr.universal.service.CrudService;
import com.fbs.dmr.universal.service.CrudServicePartyContactMechanismPurpose;
import com.fbs.dmr.universal.service.CrudServiceType;
import com.fbs.dmr.universal.service.SeedService;
import com.fbs.dmr.universal.service.ServicePartyClassification;
import com.fbs.dmr.universal.service.ServicePartyRole;
import com.fbs.security.service.SecurityService;
import com.fbs.security.service.TenantService;
import com.fbs.security.service.UserService;

public interface ApplicationServices
{
	public SecurityService getSecurityService();

	public TenantService getTenantService();

	public UserService getUserService();

	public SeedService getSeedService();

	public CrudServiceType<ContactMechanismPurposeType, Integer> getCrudServiceContactMechanismPurposeType();

	public CrudService<ContactMechanismType, Integer> getCrudServiceContactMechanismType();
	
	public CrudService<ElectronicAddress, Integer> getCrudServiceElectronicAddress();
	
	public CrudService<Organization, Integer> getCrudServiceOrganization();

	public CrudService<Party, Integer> getCrudServiceParty();

	public CrudService<PartyContactMechanism, Integer> getCrudServicePartyContactMechanism();
	
	public CrudServicePartyContactMechanismPurpose getCrudServicePartyContactMechanismPurpose();

	public CrudService<PartyRelationship, Integer> getCrudServicePartyRelationship();

	public CrudService<PartyRelationshipStatusType, Integer> getCrudServicePartyRelationshipStatusType();

	public CrudService<PartyRelationshipType, Integer> getCrudServicePartyRelationshipType();
	
	public CrudServiceType<PartyRoleType, Integer> getCrudServicePartyRoleType();
	
	public CrudServiceType<PartyType, Integer> getCrudServicePartyType();

	public CrudService<Person, Integer> getCrudServicePerson();
	
	public CrudService<PostalAddress, Integer> getCrudServicePostalAddress();

	public CrudService<PriorityType, Integer> getCrudServicePriorityType();
	
	public CrudService<TelecommunicationNumber, Integer> getCrudServiceTelecommunicationNumber();

	public ServicePartyClassification getCrudServicePartyClassification();

	public ServicePartyRole getCrudServicePartyRole();	
}
