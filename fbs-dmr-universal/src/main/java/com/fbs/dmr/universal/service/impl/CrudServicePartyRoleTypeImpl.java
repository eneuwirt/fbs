package com.fbs.dmr.universal.service.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.service.ServicePartyRoleType;

@Repository
public class CrudServicePartyRoleTypeImpl extends CrudServiceGenericImpl<PartyRoleType, Integer> implements
        ServicePartyRoleType
{
	public CrudServicePartyRoleTypeImpl()
	{
		super(PartyRoleType.class);
	}

	@Override
    public PartyRoleType findForDescription(String description)
    {
		PartyRoleType result = null;
		Query query;

		query = em.createQuery("SELECT p FROM PartyRoleType p WHERE p.description = ?1");
		query.setParameter(1, description);

		result = (PartyRoleType) query.getSingleResult();

		return result;
    }
}
