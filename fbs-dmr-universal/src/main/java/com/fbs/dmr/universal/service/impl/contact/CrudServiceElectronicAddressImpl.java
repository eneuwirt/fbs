package com.fbs.dmr.universal.service.impl.contact;

import com.fbs.dmr.universal.model.contact.ElectronicAddress;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServiceElectronicAddressImpl  extends CrudServiceGenericImpl<ElectronicAddress, Integer>
{
	public CrudServiceElectronicAddressImpl(Class<ElectronicAddress> entityClass)
    {
	    super(ElectronicAddress.class);
    }
}
