package com.fbs.dmr.universal.service.crud.impl.party;

import com.fbs.dmr.universal.model.party.PartyFacility;
import com.fbs.dmr.universal.service.crud.impl.CrudServiceGenericImpl;

public class CrudServicePartyFacilityImpl extends CrudServiceGenericImpl<PartyFacility, Integer>
{
	public CrudServicePartyFacilityImpl()
    {
	    super(PartyFacility.class);
    }
}
