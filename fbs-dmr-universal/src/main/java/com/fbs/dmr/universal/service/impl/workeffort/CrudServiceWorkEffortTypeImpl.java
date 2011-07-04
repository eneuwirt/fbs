package com.fbs.dmr.universal.service.impl.workeffort;

import com.fbs.dmr.universal.model.workeffort.WorkEffortType;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServiceWorkEffortTypeImpl extends CrudServiceGenericImpl<WorkEffortType, Integer>
{
    public CrudServiceWorkEffortTypeImpl()
    {
        super(WorkEffortType.class);
    }
}