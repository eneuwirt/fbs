package com.fbs.dmr.universal.service.impl;

import com.fbs.dmr.universal.model.party.PartyClassification;
import com.fbs.dmr.universal.service.CrudService;

public class CrudServicePartyClassificationImpl extends CrudServiceGenericImpl<PartyClassification, Integer> implements
        CrudService<PartyClassification, Integer>
{
    private static final long serialVersionUID = 1L;

	public CrudServicePartyClassificationImpl()
	{
		super(PartyClassification.class);
	}

}
