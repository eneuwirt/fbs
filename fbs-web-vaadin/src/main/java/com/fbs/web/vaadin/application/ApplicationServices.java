package com.fbs.web.vaadin.application;

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
import com.fbs.dmr.universal.service.demo.DemoDataService;
import com.fbs.dmr.universal.service.seed.SeedService;
import com.fbs.security.service.SecurityService;
import com.fbs.security.service.TenantService;
import com.fbs.security.service.UserService;

public interface ApplicationServices
{
	public SecurityService getSecurityService();

	public TenantService getTenantService();

	public UserService getUserService();

	public SeedService getSeedService();
	
	public DemoDataService getDemoDataService();

	public CrudServiceType<ContactMechanismPurposeType, Integer> getCrudServiceContactMechanismPurposeType();

	public CrudService<ContactMechanismType, Integer> getCrudServiceContactMechanismType();
	
	public CrudService<Facility, Integer> getCrudServiceFacility();
	
	public CrudService<FacilityContactMechanism, Integer> getCrudServiceFacilityContactMechanism();
	
	public CrudService<ElectronicAddress, Integer> getCrudServiceElectronicAddress();
	
	public CrudService<Organization, Integer> getCrudServiceOrganization();

	public CrudService<Party, Integer> getCrudServiceParty();

	public CrudServicePartyContactMechanism getCrudServicePartyContactMechanism();
	
	public CrudServicePartyContactMechanismPurpose getCrudServicePartyContactMechanismPurpose();
	
	public CrudService<PartyFacility, Integer> getCrudServicePartyFacility();

	public CrudService<PartyRelationship, Integer> getCrudServicePartyRelationship();

	public CrudService<PartyRelationshipStatusType, Integer> getCrudServicePartyRelationshipStatusType();

	public CrudService<PartyRelationshipType, Integer> getCrudServicePartyRelationshipType();
	
	public CrudServiceType<PartyRoleType, Integer> getCrudServicePartyRoleType();
	
	public CrudServiceType<PartyType, Integer> getCrudServicePartyType();

	public CrudService<Person, Integer> getCrudServicePerson();
	
	public CrudService<PostalAddress, Integer> getCrudServicePostalAddress();

	public CrudService<PriorityType, Integer> getCrudServicePriorityType();
	
	public CrudService<TelecommunicationNumber, Integer> getCrudServiceTelecommunicationNumber();
	
	public CrudService<WorkEffort, Integer> getCrudServiceWorkEffort();
	
	public CrudServiceWorkEffortPartyAssignment getCrudServiceWorkEffortPartyAssignment();
	
	public CrudService<WorkEffortType, Integer> getCrudServiceWorkEffortType();

	public CrudServicePartyClassification getCrudServicePartyClassification();

	public CrudServicePartyRole getCrudServicePartyRole();	
}
