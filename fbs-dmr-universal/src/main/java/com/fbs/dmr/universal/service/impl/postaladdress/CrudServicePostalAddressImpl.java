package com.fbs.dmr.universal.service.impl.postaladdress;

import com.fbs.dmr.universal.model.postaladdress.PostalAddress;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServicePostalAddressImpl extends CrudServiceGenericImpl<PostalAddress, Integer>
{
	public CrudServicePostalAddressImpl()
	{
		super(PostalAddress.class);
	}
}
