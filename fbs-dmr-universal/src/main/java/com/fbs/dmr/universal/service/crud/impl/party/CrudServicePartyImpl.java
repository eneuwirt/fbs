package com.fbs.dmr.universal.service.crud.impl.party;

import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.service.crud.impl.CrudServiceGenericImpl;

public class CrudServicePartyImpl extends CrudServiceGenericImpl<Party, Integer>
{
    public CrudServicePartyImpl()
    {
        super(Party.class);
    }
    
    public CrudServicePartyImpl(Class<Party> entityClass)
    {
        super(entityClass);
    }
}
