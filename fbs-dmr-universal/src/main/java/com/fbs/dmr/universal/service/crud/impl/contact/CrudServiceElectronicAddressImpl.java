package com.fbs.dmr.universal.service.crud.impl.contact;

import com.fbs.dmr.universal.model.contact.ElectronicAddress;
import com.fbs.dmr.universal.service.crud.CrudService;
import com.fbs.dmr.universal.service.crud.impl.CrudServiceGenericImpl;

public class CrudServiceElectronicAddressImpl extends CrudServiceGenericImpl<ElectronicAddress, Integer> implements
        CrudService<ElectronicAddress, Integer>
{
	public CrudServiceElectronicAddressImpl()
	{
		super(ElectronicAddress.class);
	}
}
