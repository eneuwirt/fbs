package com.fbs.dmr.universal.service.impl;

import com.fbs.dmr.universal.model.party.Person;

public class CrudServicePersonImpl extends CrudServiceGenericImpl<Person, Integer>
{

	public CrudServicePersonImpl()
    {
	    super(Person.class);
    }
}
