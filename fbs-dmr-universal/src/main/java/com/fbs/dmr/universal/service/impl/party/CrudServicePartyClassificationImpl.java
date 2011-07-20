package com.fbs.dmr.universal.service.impl.party;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.fbs.dmr.universal.model.party.PartyClassification;
import com.fbs.dmr.universal.service.CrudServicePartyClassification;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServicePartyClassificationImpl extends CrudServiceGenericImpl<PartyClassification, Integer> implements
        CrudServicePartyClassification
{
	public CrudServicePartyClassificationImpl()
	{
		super(PartyClassification.class);
	}

	@SuppressWarnings("unchecked")
    @Override
    public List<PartyClassification> findByParty(Integer partyId)
    {
		List<PartyClassification> result;
		
		Query query;

		query = em.createQuery("SELECT p FROM PartyClassification p WHERE p.party.id = ?");
		query.setParameter(1, partyId);

		result = query.getResultList();
		
	    return result;
    }

	@Override
    public PartyClassification findByPartyType(Integer partyId, String partyRoleTypeDescription)
    {
		PartyClassification result;
		Query query;

		query = em.createQuery("SELECT p FROM PartyClassification p WHERE p.partyType.description = ?1 AND p.party.id = ?2");
		query.setParameter(1, partyRoleTypeDescription);
		query.setParameter(2, partyId);

		try
		{
			result = (PartyClassification) query.getSingleResult();
		}
		catch (NoResultException nex)
		{
			result = null;
		}
		
	    return result;
    }

}
