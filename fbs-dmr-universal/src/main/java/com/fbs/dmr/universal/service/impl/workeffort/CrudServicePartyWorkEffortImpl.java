package com.fbs.dmr.universal.service.impl.workeffort;

import com.fbs.dmr.universal.model.workeffort.WorkEffortPartyAssignment;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServicePartyWorkEffortImpl extends CrudServiceGenericImpl<WorkEffortPartyAssignment, Integer>
{
	public CrudServicePartyWorkEffortImpl()
    {
	    super(WorkEffortPartyAssignment.class);
    }
}
