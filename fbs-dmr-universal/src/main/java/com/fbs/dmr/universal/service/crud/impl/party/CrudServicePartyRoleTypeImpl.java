package com.fbs.dmr.universal.service.crud.impl.party;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.service.crud.CrudServiceType;
import com.fbs.dmr.universal.service.crud.impl.CrudServiceGenericImpl;

@Repository
public class CrudServicePartyRoleTypeImpl extends CrudServiceGenericImpl<PartyRoleType, Integer> implements
        CrudServiceType<PartyRoleType, Integer>
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
