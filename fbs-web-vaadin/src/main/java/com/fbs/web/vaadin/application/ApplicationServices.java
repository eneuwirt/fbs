package com.fbs.web.vaadin.application;


import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyRelationshipType;
import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.dmr.universal.model.party.Person;
import com.fbs.dmr.universal.service.CrudService;
import com.fbs.dmr.universal.service.SeedService;
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

	public CrudService<PartyType, Integer> getCrudServicePartyType();
	
	public CrudService<PartyRoleType, Integer> getCrudServicePartyRoleType();
	
	public CrudService<Person, Integer> getCrudServicePerson();
	
	public CrudService<Organization, Integer> getCrudServiceOrganization();
	
	public CrudService<PartyRelationshipType, Integer> getCrudServicePartyRelationshipType();
}
