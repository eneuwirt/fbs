package com.fbs.dmr.universal.service.crud;

import java.util.List;

import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyRole;

public interface CrudServicePartyRole extends CrudService<PartyRole, Integer>
{
	public List<PartyRole> findByParty(Integer partyId);

	public PartyRole findByPartyAndPartyRoleType(Integer partyId, String partyRoleTypeDescription);
	
	public List<Party> findByPartyRoleType(String partyRoleTypeDescription);
}
