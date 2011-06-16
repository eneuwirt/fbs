package com.fbs.dmr.universal.service;

import java.util.List;

import com.fbs.dmr.universal.model.party.PartyRole;

public interface ServicePartyRole extends CrudService<PartyRole, Integer>
{
	public List<PartyRole> findByParty(Integer partyId);

	public PartyRole findByPartyRoleType(Integer partyId, String partyRoleTypeDescription);
}