package com.fbs.dmr.universal.service.impl.contact;

import com.fbs.dmr.universal.model.contact.ContactMechanismType;
import com.fbs.dmr.universal.service.impl.CrudServiceTypeGenericImpl;

public class CrudServiceContactMechanismTypeImpl  extends CrudServiceTypeGenericImpl<ContactMechanismType, Integer>
{
	public CrudServiceContactMechanismTypeImpl()
    {
	    super(ContactMechanismType.class);
    }
}
