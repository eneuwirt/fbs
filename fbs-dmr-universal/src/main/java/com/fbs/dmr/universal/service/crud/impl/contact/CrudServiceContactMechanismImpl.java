package com.fbs.dmr.universal.service.crud.impl.contact;

import com.fbs.dmr.universal.model.contact.ContactMechanism;
import com.fbs.dmr.universal.service.crud.impl.CrudServiceGenericImpl;

public class CrudServiceContactMechanismImpl extends CrudServiceGenericImpl<ContactMechanism, Integer>
{
	public CrudServiceContactMechanismImpl()
	{
		super(ContactMechanism.class);
	}
}
