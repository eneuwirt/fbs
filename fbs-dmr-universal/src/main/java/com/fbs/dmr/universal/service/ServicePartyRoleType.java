package com.fbs.dmr.universal.service;

import com.fbs.dmr.universal.model.party.PartyRoleType;

public interface ServicePartyRoleType extends CrudService<PartyRoleType, Integer>
{
	public PartyRoleType findForDescription (String description);
}
