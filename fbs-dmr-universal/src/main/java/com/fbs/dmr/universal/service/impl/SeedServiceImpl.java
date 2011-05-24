package com.fbs.dmr.universal.service.impl;

import java.util.List;

import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.dmr.universal.service.CrudService;
import com.fbs.dmr.universal.service.SeedService;

public class SeedServiceImpl implements SeedService
{
	private CrudService<PartyType, Integer> servicePartyType;


	@Override
	public void defaultFill()
	{
		this.createPartyPart();
	}


	private void createPartyPart()
	{
		List<PartyType> partyTypes;

		partyTypes = this.servicePartyType.findAll();
		if (partyTypes.size() > 0)
		{
			PartyType pType1;
			String pTypeDescription1 = "Default";
			pType1 = new PartyType();
			pType1.setDescription(pTypeDescription1);
			
			this.servicePartyType.save(pType1);
		}
	}


	public void setServicePartyType(CrudService<PartyType, Integer> servicePartyType)
	{
		this.servicePartyType = servicePartyType;
	}
}
