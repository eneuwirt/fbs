package com.fbs.dmr.universal.service.impl;

import com.fbs.dmr.universal.model.party.PartyRole;
import com.fbs.dmr.universal.service.CrudService;

public class CrudServicePartyRole extends CrudServiceGenericImpl<PartyRole, Integer> implements
        CrudService<PartyRole, Integer>
{
	public CrudServicePartyRole()
	{
		super(PartyRole.class);
	}
}
