package com.fbs.dmr.universal.service.impl.contact;

import com.fbs.dmr.universal.model.contact.ContactMechanismPurposeType;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServiceContactMechanismPurposeTypeImpl extends
        CrudServiceGenericImpl<ContactMechanismPurposeType, Integer>
{

	public CrudServiceContactMechanismPurposeTypeImpl()
    {
	    super(ContactMechanismPurposeType.class);
    }

}
