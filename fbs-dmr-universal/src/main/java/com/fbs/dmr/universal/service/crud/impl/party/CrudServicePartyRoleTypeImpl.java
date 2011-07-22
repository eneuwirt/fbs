package com.fbs.dmr.universal.service.crud.impl.party;

import org.springframework.stereotype.Repository;

import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.service.crud.impl.CrudServiceTypeGenericImpl;

@Repository
public class CrudServicePartyRoleTypeImpl extends CrudServiceTypeGenericImpl<PartyRoleType, Integer> 
{
	public CrudServicePartyRoleTypeImpl()
	{
		super(PartyRoleType.class);
	}
}
