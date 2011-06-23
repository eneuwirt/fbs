package com.fbs.dmr.universal.service;

import java.util.List;

import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.dmr.universal.model.party.PartyContactMechanismPurpose;

public interface CrudServicePartyContactMechanismPurpose extends CrudService<PartyContactMechanismPurpose, Integer>
{
	public List<PartyContactMechanismPurpose> findByPartyContactMechanism(PartyContactMechanism pcm);
}
