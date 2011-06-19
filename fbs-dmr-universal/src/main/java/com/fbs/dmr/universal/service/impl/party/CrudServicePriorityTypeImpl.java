package com.fbs.dmr.universal.service.impl.party;

import com.fbs.dmr.universal.model.party.PriorityType;
import com.fbs.dmr.universal.service.impl.CrudServiceTypeGenericImpl;

public class CrudServicePriorityTypeImpl extends CrudServiceTypeGenericImpl<PriorityType, Integer>
{
	public CrudServicePriorityTypeImpl()
    {
	    super(PriorityType.class);
    }
}
