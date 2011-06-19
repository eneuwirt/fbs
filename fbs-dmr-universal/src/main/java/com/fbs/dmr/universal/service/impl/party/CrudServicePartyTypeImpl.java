package com.fbs.dmr.universal.service.impl.party;

import org.springframework.stereotype.Repository;

import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.dmr.universal.service.CrudServiceType;
import com.fbs.dmr.universal.service.impl.CrudServiceTypeGenericImpl;

@Repository
public class CrudServicePartyTypeImpl extends CrudServiceTypeGenericImpl<PartyType, Integer>
implements CrudServiceType<PartyType, Integer>
{
	public CrudServicePartyTypeImpl()
    {
	    super(PartyType.class);
    }
}
