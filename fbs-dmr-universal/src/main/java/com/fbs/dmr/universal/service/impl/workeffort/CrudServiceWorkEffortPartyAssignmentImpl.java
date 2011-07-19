package com.fbs.dmr.universal.service.impl.workeffort;

import java.util.List;

import javax.persistence.Query;

import com.fbs.dmr.universal.model.workeffort.WorkEffortPartyAssignment;
import com.fbs.dmr.universal.service.CrudServiceWorkEffortPartyAssignment;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServiceWorkEffortPartyAssignmentImpl extends CrudServiceGenericImpl<WorkEffortPartyAssignment, Integer> implements
        CrudServiceWorkEffortPartyAssignment
{
    public CrudServiceWorkEffortPartyAssignmentImpl()
    {
        super(WorkEffortPartyAssignment.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<WorkEffortPartyAssignment> findByWorkEffort(Integer workEffortId)
    {
        List<WorkEffortPartyAssignment> result = null;        
        Query query;

        query = em.createQuery("SELECT w FROM WorkEffortPartyAssignment w WHERE w.workEffort.id = ?");
        query.setParameter(1, workEffortId);

        result = query.getResultList();
        
        return result;
    }
}
