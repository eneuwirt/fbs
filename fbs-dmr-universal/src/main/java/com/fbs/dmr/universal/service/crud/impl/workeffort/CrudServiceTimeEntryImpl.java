package com.fbs.dmr.universal.service.crud.impl.workeffort;

import com.fbs.dmr.universal.model.workeffort.TimeEntry;
import com.fbs.dmr.universal.service.crud.impl.CrudServiceGenericImpl;

public class CrudServiceTimeEntryImpl extends CrudServiceGenericImpl<TimeEntry, Integer>
{
    public CrudServiceTimeEntryImpl()
    {
        super(TimeEntry.class);
    }
}
