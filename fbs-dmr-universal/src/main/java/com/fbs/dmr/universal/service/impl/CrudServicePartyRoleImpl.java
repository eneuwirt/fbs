package com.fbs.dmr.universal.service.impl;

import com.fbs.dmr.universal.model.party.PartyRole;
import com.fbs.dmr.universal.service.CrudService;

public class CrudServicePartyRoleImpl extends CrudServiceGenericImpl<PartyRole, Integer> implements
        CrudService<PartyRole, Integer>
{
	public CrudServicePartyRoleImpl()
	{
		super(PartyRole.class);
	}
}
