package com.fbs.dmr.universal.service.impl.workeffort;

import com.fbs.dmr.universal.model.workeffort.WorkEffortPartyAssignment;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServiceWorkEffortPartyAssignmentImpl extends CrudServiceGenericImpl<WorkEffortPartyAssignment, Integer>
{
	public CrudServiceWorkEffortPartyAssignmentImpl()
    {
	    super(WorkEffortPartyAssignment.class);
    }
}
