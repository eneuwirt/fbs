package com.fbs.dmr.universal.service;

import com.fbs.dmr.universal.model.party.PartyType;

public interface ServicePartyType extends CrudService<PartyType, Integer>
{
	public PartyType findForDescription(String description);
}
