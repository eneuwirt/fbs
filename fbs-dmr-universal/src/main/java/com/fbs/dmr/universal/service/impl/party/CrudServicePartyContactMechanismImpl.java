package com.fbs.dmr.universal.service.impl.party;

import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServicePartyContactMechanismImpl extends CrudServiceGenericImpl<PartyContactMechanism, Integer> 
{
	public CrudServicePartyContactMechanismImpl()
    {
	    super(PartyContactMechanism.class);
    }
}
