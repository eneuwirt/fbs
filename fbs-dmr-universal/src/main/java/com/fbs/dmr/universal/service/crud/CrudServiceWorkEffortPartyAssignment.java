package com.fbs.dmr.universal.service.crud;

import java.util.List;

import com.fbs.dmr.universal.model.workeffort.WorkEffortPartyAssignment;

public interface CrudServiceWorkEffortPartyAssignment extends CrudService<WorkEffortPartyAssignment, Integer>
{
    public List<WorkEffortPartyAssignment> findByWorkEffort(Integer workEffortId);
}
