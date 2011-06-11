package com.fbs.dmr.universal.service;

import java.util.List;

import com.fbs.dmr.universal.model.party.PartyClassification;

public interface ServicePartyClassification extends CrudService<PartyClassification, Integer>
{
	List<PartyClassification> findByParty(Integer partyId);

	PartyClassification findByPartyType(Integer id, String selectedClass);
}
