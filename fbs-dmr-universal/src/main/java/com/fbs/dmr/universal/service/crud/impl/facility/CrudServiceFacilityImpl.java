package com.fbs.dmr.universal.service.crud.impl.facility;

import com.fbs.dmr.universal.model.facility.Facility;
import com.fbs.dmr.universal.service.crud.impl.CrudServiceGenericImpl;

public class CrudServiceFacilityImpl extends CrudServiceGenericImpl<Facility, Integer>
{
	public CrudServiceFacilityImpl()
	{
		super(Facility.class);
	}

}
