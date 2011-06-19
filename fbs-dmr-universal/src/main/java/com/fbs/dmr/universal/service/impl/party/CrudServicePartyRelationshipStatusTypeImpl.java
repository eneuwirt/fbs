package com.fbs.dmr.universal.service.impl.party;

import com.fbs.dmr.universal.model.party.PartyRelationshipStatusType;
import com.fbs.dmr.universal.service.impl.CrudServiceTypeGenericImpl;

public class CrudServicePartyRelationshipStatusTypeImpl extends CrudServiceTypeGenericImpl<PartyRelationshipStatusType, Integer>
{

	public CrudServicePartyRelationshipStatusTypeImpl()
    {
	    super(PartyRelationshipStatusType.class);
    }
}
