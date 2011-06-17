package com.fbs.dmr.universal.service.impl.contact;

import com.fbs.dmr.universal.model.contact.ContactMechanismType;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServiceContactMechanismType  extends CrudServiceGenericImpl<ContactMechanismType, Integer>
{
	public CrudServiceContactMechanismType()
    {
	    super(ContactMechanismType.class);
    }
}
