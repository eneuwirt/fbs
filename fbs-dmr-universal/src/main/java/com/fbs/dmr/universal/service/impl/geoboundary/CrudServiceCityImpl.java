package com.fbs.dmr.universal.service.impl.geoboundary;

import com.fbs.dmr.universal.model.geoboundary.City;
import com.fbs.dmr.universal.service.CrudService;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServiceCityImpl extends CrudServiceGenericImpl<City, Integer> implements CrudService<City, Integer>
{

	public CrudServiceCityImpl()
    {
	    super(City.class);
    }

}
