package com.fbs.dmr.universal.service.impl.contact;

import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServicePostalAddressImpl extends CrudServiceGenericImpl<PostalAddress, Integer>
{
	public CrudServicePostalAddressImpl()
	{
		super(PostalAddress.class);
	}
}
