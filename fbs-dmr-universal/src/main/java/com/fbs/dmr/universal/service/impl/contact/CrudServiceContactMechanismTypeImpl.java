package com.fbs.dmr.universal.service.impl.contact;

import com.fbs.dmr.universal.model.contact.ContactMechanismType;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServiceContactMechanismTypeImpl  extends CrudServiceGenericImpl<ContactMechanismType, Integer>
{
	public CrudServiceContactMechanismTypeImpl()
    {
	    super(ContactMechanismType.class);
    }
}
