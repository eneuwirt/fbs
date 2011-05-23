package com.fbs.dmr.universal.service.impl;

import org.springframework.stereotype.Repository;

import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.dmr.universal.service.CrudService;

@Repository
public class CrudServicePartyTypeImpl extends CrudServiceGenericImpl<PartyType, Integer> implements CrudService<PartyType, Integer>
{
	public CrudServicePartyTypeImpl()
    {
	    super(PartyType.class);
    }
}
