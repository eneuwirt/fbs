package com.fbs.dmr.universal.service.impl.contact;

import com.fbs.dmr.universal.model.contact.ContactMechanismPurposeType;
import com.fbs.dmr.universal.service.impl.CrudServiceTypeGenericImpl;

public class CrudServiceContactMechanismPurposeTypeImpl extends
        CrudServiceTypeGenericImpl<ContactMechanismPurposeType, Integer> 
{

	public CrudServiceContactMechanismPurposeTypeImpl()
    {
	    super(ContactMechanismPurposeType.class);
    }

}
