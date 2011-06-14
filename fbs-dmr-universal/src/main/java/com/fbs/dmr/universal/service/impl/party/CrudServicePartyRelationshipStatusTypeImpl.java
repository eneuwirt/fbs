package com.fbs.dmr.universal.service.impl.party;

import com.fbs.dmr.universal.model.party.PartyRelationshipStatusType;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServicePartyRelationshipStatusTypeImpl extends CrudServiceGenericImpl<PartyRelationshipStatusType, Integer>
{

	public CrudServicePartyRelationshipStatusTypeImpl()
    {
	    super(PartyRelationshipStatusType.class);
    }
}
