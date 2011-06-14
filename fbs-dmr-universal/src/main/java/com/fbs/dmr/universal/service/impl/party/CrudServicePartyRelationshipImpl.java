package com.fbs.dmr.universal.service.impl.party;

import com.fbs.dmr.universal.model.party.PartyRelationship;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServicePartyRelationshipImpl extends CrudServiceGenericImpl<PartyRelationship, Integer>
{
	public CrudServicePartyRelationshipImpl()
    {
	    super(PartyRelationship.class);
    }
}
