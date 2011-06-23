package com.fbs.dmr.universal.service.impl.contact;

import com.fbs.dmr.universal.model.contact.ContactMechanism;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServiceContactMechanismImpl extends CrudServiceGenericImpl<ContactMechanism, Integer>
{
	public CrudServiceContactMechanismImpl()
	{
		super(ContactMechanism.class);
	}
}
