package com.fbs.web.vaadin.application;


import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyType;
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

	public CrudService<Party, Integer> getCrudPartyService();

	public CrudService<PartyType, Integer> getCrudPartyTypeService();
}
