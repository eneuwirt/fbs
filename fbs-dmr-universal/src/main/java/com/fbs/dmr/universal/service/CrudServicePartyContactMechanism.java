package com.fbs.dmr.universal.service;

import java.util.List;

import com.fbs.dmr.universal.model.contact.ContactMechanism;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyContactMechanism;

public interface CrudServicePartyContactMechanism extends CrudService<PartyContactMechanism, Integer>
{
	public List<PartyContactMechanism> findByParty(Party party);
	public PartyContactMechanism findByPartyAndContactMechanism(Party party, ContactMechanism contactMechanism);
}
