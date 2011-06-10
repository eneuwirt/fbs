package com.fbs.dmr.universal.service.impl;

import com.fbs.dmr.universal.model.party.Person;

public class CrudServicePersonImpl extends CrudServiceGenericImpl<Person, Integer>
{

	public CrudServicePersonImpl()
    {
	    super(Person.class);
    }
	
	@Override
	public Person read(Integer id)
	{
		Person result;
		
		result = super.read(id);
		
		result.getPartyClassifications().size();
		result.getPartyRoles().size();
		
		return result;
	}
}
