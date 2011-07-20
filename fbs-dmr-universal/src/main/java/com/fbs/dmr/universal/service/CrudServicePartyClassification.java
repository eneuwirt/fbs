package com.fbs.dmr.universal.service;

import java.util.List;

import com.fbs.dmr.universal.model.party.PartyClassification;

public interface CrudServicePartyClassification extends CrudService<PartyClassification, Integer>
{
	public List<PartyClassification> findByParty(Integer partyId);

	public PartyClassification findByPartyType(Integer id, String selectedClass);
}
