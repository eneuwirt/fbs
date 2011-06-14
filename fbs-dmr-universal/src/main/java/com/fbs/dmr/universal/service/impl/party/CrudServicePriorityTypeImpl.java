package com.fbs.dmr.universal.service.impl.party;

import com.fbs.dmr.universal.model.party.PriorityType;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServicePriorityTypeImpl extends CrudServiceGenericImpl<PriorityType, Integer>
{
	public CrudServicePriorityTypeImpl()
    {
	    super(PriorityType.class);
    }
}
