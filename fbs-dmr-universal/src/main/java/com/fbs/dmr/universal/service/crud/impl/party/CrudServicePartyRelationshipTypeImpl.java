package com.fbs.dmr.universal.service.crud.impl.party;

import com.fbs.dmr.universal.model.party.PartyRelationshipType;
import com.fbs.dmr.universal.service.crud.impl.CrudServiceGenericImpl;

public class CrudServicePartyRelationshipTypeImpl extends CrudServiceGenericImpl<PartyRelationshipType, Integer>
{
	public CrudServicePartyRelationshipTypeImpl()
    {
	    super(PartyRelationshipType.class);
    }
}
