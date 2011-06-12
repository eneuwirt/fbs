package com.fbs.web.vaadin.application;

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

public interface ApplicationServices
{
	public SecurityService getSecurityService();

	public TenantService getTenantService();

	public UserService getUserService();

	public SeedService getSeedService();

	public CrudService<Party, Integer> getCrudServiceParty();

	public CrudService<PartyRelationship, Integer> getCrudServicePartyRelationship();
	
	public CrudService<PartyRelationshipStatusType, Integer> getCrudServicePartyRelationshipStatusType();
	
	public CrudService<PartyRelationshipType, Integer> getCrudServicePartyRelationshipType();

	public CrudService<Person, Integer> getCrudServicePerson();
	
	public CrudService<PriorityType, Integer> getCrudServicePriorityType();

	public CrudService<Organization, Integer> getCrudServiceOrganization();
	
	public ServicePartyClassification getCrudServicePartyClassification();
	
	public ServicePartyRole getCrudServicePartyRole();
	
	public ServicePartyRoleType getCrudServicePartyRoleType();
	
	public ServicePartyType getCrudServicePartyType();
}
