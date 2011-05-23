package com.fbs.dmr.universal.service.impl;

import org.springframework.stereotype.Repository;

import com.fbs.dmr.universal.model.party.PartyType;

@Repository
public class CrudServicePartyTypeImpl extends CrudServiceImpl<PartyType, Integer>
{
	public CrudServicePartyTypeImpl()
    {
	    super(PartyType.class);
    }
}
