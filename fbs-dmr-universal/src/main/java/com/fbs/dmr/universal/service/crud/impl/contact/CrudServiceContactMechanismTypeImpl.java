package com.fbs.dmr.universal.service.crud.impl.contact;

import com.fbs.dmr.universal.model.contact.ContactMechanismType;
import com.fbs.dmr.universal.service.crud.impl.CrudServiceTypeGenericImpl;

public class CrudServiceContactMechanismTypeImpl  extends CrudServiceTypeGenericImpl<ContactMechanismType, Integer>
{
	public CrudServiceContactMechanismTypeImpl()
    {
	    super(ContactMechanismType.class);
    }
}
