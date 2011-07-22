package com.fbs.dmr.universal.service.crud.impl.party;

import com.fbs.dmr.universal.model.party.Person;
import com.fbs.dmr.universal.service.crud.impl.CrudServiceGenericImpl;

public class CrudServicePersonImpl extends CrudServiceGenericImpl<Person, Integer>
{
	public CrudServicePersonImpl()
	{
		super(Person.class);
	}
}
