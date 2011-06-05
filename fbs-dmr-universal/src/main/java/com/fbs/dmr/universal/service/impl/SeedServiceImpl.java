package com.fbs.dmr.universal.service.impl;

import java.util.List;

import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.dmr.universal.service.CrudService;
import com.fbs.dmr.universal.service.SeedService;

public class SeedServiceImpl implements SeedService
{
	private CrudService<PartyType, Integer> servicePartyType;
	private CrudService<PartyRoleType, Integer> servicePartyRoleType;


	@Override
	public void defaultFill()
	{
		// Party part
		this.createPartyType();
		this.createPartyRoleType();
	}


	private void createPartyRoleType()
    {
	   List<PartyRoleType> partyRoleTypes;
	   
	   partyRoleTypes = servicePartyRoleType.findAll();
	   
	   if (partyRoleTypes.size() == 0)
	   {
		   PartyRoleType partyRoleType;
		   
		   partyRoleType = new PartyRoleType();
		   partyRoleType.setDescription("Anwalt");
		   this.servicePartyRoleType.create(partyRoleType);
		   
		   partyRoleType = new PartyRoleType();
		   partyRoleType.setDescription("Mandant");
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


	public void setServicePartyRoleType(CrudService<PartyRoleType, Integer> servicePartyRoleType)
    {
	    this.servicePartyRoleType = servicePartyRoleType;
    }
}
