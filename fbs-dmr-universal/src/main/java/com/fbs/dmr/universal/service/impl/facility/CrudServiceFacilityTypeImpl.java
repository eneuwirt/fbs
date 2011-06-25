package com.fbs.dmr.universal.service.impl.facility;

import com.fbs.dmr.universal.model.facility.FacilityType;
import com.fbs.dmr.universal.service.impl.CrudServiceTypeGenericImpl;

public class CrudServiceFacilityTypeImpl  extends CrudServiceTypeGenericImpl<FacilityType, Integer>
{
	public CrudServiceFacilityTypeImpl()
    {
	    super(FacilityType.class);
    }
}
