package com.fbs.dmr.universal.service.impl.party;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.dmr.universal.service.CrudServiceType;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

@Repository
public class CrudServicePartyTypeImpl extends CrudServiceGenericImpl<PartyType, Integer>
implements CrudServiceType<PartyType, Integer>
{
	public CrudServicePartyTypeImpl()
    {
	    super(PartyType.class);
    }

	@Override
    public PartyType findForDescription(String description)
    {
		PartyType result;
		Query query;

		query = em.createQuery("SELECT p FROM PartyType p WHERE p.description = ?1");
		query.setParameter(1, description);

		result = (PartyType) query.getSingleResult();

		return result;
    }
}
