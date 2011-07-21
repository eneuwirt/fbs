package com.fbs.dmr.universal.service.impl.workeffort;

import com.fbs.dmr.universal.model.workeffort.TimeSheet;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServiceTimeSheetImpl extends CrudServiceGenericImpl<TimeSheet, Integer>
{

    public CrudServiceTimeSheetImpl()
    {
        super(TimeSheet.class);
    }

}
