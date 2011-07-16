package com.fbs.dmr.universal.service.impl.workeffort;

import com.fbs.dmr.universal.model.workeffort.WorkEffort;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServiceWorkEffortImpl extends CrudServiceGenericImpl<WorkEffort, Integer>
{
	public CrudServiceWorkEffortImpl()
	{
		super(WorkEffort.class);
	}

}
