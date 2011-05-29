package com.fbs.dmr.universal.service.impl;

import org.springframework.stereotype.Repository;

import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.service.CrudService;

@Repository
public class CrudServicePartyRoleTypeImpl extends CrudServiceGenericImpl<PartyRoleType, Integer> implements
        CrudService<PartyRoleType, Integer>
{
	public CrudServicePartyRoleTypeImpl()
	{
		super(PartyRoleType.class);
	}
}
