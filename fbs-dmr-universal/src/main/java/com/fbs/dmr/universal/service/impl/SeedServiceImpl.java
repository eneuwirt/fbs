package com.fbs.dmr.universal.service.impl;

import java.util.List;
import java.util.logging.Logger;

import com.fbs.dmr.universal.model.party.PartyRelationshipStatusType;
import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.dmr.universal.model.party.PriorityType;
import com.fbs.dmr.universal.service.CrudService;
import com.fbs.dmr.universal.service.SeedService;

public class SeedServiceImpl implements SeedService
{
	private static Logger logger = Logger.getLogger(SeedServiceImpl.class.getName());
	private CrudService<PartyRelationshipStatusType, Integer> servicePartyRelationshipStatusType;
	private CrudService<PartyRoleType, Integer> servicePartyRoleType;
	private CrudService<PartyType, Integer> servicePartyType;
	private CrudService<PriorityType, Integer> servicePriorityType;
	

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

	public void setServicePriorityType(CrudService<PriorityType, Integer> servicePriorityType)
	{
		this.servicePriorityType = servicePriorityType;
	}

	public void setServicePartyRelationshipStatusType(CrudService<PartyRelationshipStatusType, Integer> servicePartyRelationshipStatusType)
    {
	    this.servicePartyRelationshipStatusType = servicePartyRelationshipStatusType;
    }
}
