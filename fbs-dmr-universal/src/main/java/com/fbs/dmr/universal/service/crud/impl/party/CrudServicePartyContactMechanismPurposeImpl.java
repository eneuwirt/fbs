package com.fbs.dmr.universal.service.crud.impl.party;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.dmr.universal.model.party.PartyContactMechanismPurpose;
import com.fbs.dmr.universal.service.crud.CrudServicePartyContactMechanismPurpose;
import com.fbs.dmr.universal.service.crud.impl.CrudServiceGenericImpl;

public class CrudServicePartyContactMechanismPurposeImpl extends CrudServiceGenericImpl<PartyContactMechanismPurpose, Integer> implements
        CrudServicePartyContactMechanismPurpose
{
    public CrudServicePartyContactMechanismPurposeImpl()
    {
        super(PartyContactMechanismPurpose.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PartyContactMechanismPurpose> findByPartyContactMechanism(PartyContactMechanism pcm)
    {
        List<PartyContactMechanismPurpose> result;

        Query query;

        query = em.createQuery("SELECT p FROM PartyContactMechanismPurpose p WHERE p.partyContactMechanism.id = ?1");
        query.setParameter(1, pcm.getId());

        result = (List<PartyContactMechanismPurpose>) query.getResultList();

        return result;
    }

    @Override
    public PartyContactMechanismPurpose findByPartyContactMechanismAndContactMechanismPurposeType(PartyContactMechanism pcm,
            String ContactMechanismPurposeTypeDescription)
    {
        PartyContactMechanismPurpose result = null;
        Query query;

        query = em.createQuery("SELECT p FROM PartyContactMechanismPurpose p "
                + "WHERE p.partyContactMechanism.id = ?1 AND p.contactMechanismPurposeType.description = ?2");
        query.setParameter(1, pcm.getId());
        query.setParameter(2, ContactMechanismPurposeTypeDescription);

        try
        {
            result = (PartyContactMechanismPurpose) query.getSingleResult();
        }
        catch (NoResultException nex)
        {
            result = null;
        }

        return result;
    }
}
