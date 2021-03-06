package com.fbs.dmr.universal.service.crud.impl.party;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.fbs.dmr.universal.model.contact.ContactMechanism;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.dmr.universal.service.crud.CrudServicePartyContactMechanism;
import com.fbs.dmr.universal.service.crud.impl.CrudServiceGenericImpl;

public class CrudServicePartyContactMechanismImpl extends CrudServiceGenericImpl<PartyContactMechanism, Integer> implements
        CrudServicePartyContactMechanism
{
    public CrudServicePartyContactMechanismImpl()
    {
        super(PartyContactMechanism.class);
    }

    @Override
    public PartyContactMechanism findByPartyAndContactMechanism(Party party, ContactMechanism contactMechanism)
    {
        PartyContactMechanism result = null;
        Query query;

        query = em.createQuery("SELECT p FROM PartyContactMechanism p WHERE p.party.id = ?1 AND p.contactMechanism.id = ?2");
        query.setParameter(1, party.getId());
        query.setParameter(2, contactMechanism.getId());

        try
        {
            result = (PartyContactMechanism) query.getSingleResult();
        }
        catch (NoResultException nex)
        {
            result = null;
        }
        
        return result;
    }

	@SuppressWarnings("unchecked")
    @Override
    public List<PartyContactMechanism> findByParty(Party party)
    {
		List<PartyContactMechanism> result = null;
        Query query;

        query = em.createQuery("SELECT p FROM PartyContactMechanism p WHERE p.party.id = ?1");
        
        query.setParameter(1, party.getId());

        try
        {
            result = query.getResultList();
        }
        catch (NoResultException nex)
        {
            result = null;
        }
        
        return result;
    }
}
