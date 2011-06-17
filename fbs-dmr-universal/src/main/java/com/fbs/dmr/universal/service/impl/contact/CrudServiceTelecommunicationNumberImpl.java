package com.fbs.dmr.universal.service.impl.contact;

import com.fbs.dmr.universal.model.contact.TelecommunicationNumber;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServiceTelecommunicationNumberImpl extends CrudServiceGenericImpl<TelecommunicationNumber, Integer>
{
	public CrudServiceTelecommunicationNumberImpl()
    {
	    super(TelecommunicationNumber.class);
    }
}
