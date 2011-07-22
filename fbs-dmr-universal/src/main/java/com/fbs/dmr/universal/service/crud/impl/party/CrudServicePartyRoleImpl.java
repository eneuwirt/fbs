package com.fbs.dmr.universal.service.crud.impl.party;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyRole;
import com.fbs.dmr.universal.service.crud.CrudServicePartyRole;
import com.fbs.dmr.universal.service.crud.impl.CrudServiceGenericImpl;

public class CrudServicePartyRoleImpl extends CrudServiceGenericImpl<PartyRole, Integer> implements CrudServicePartyRole
{
	public CrudServicePartyRoleImpl()
	{
		super(PartyRole.class);
	}

	@Override
	public PartyRole findByPartyAndPartyRoleType(Integer partyId, String partyRoleTypeDescription)
	{
		PartyRole result;
		Query query;

		query = em.createQuery("SELECT p FROM PartyRole p WHERE p.partyRoleType.description = ?1 AND p.party.id = ?2");
		query.setParameter(1, partyRoleTypeDescription);
		query.setParameter(2, partyId);

		try
		{
			result = (PartyRole) query.getSingleResult();
		}
		catch (NoResultException nex)
		{
			result = null;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartyRole> findByParty(Integer partyId)
	{
		List<PartyRole> result;
		Query query;

		query = em.createQuery("SELECT p FROM PartyRole p WHERE p.party.id = ?");
		query.setParameter(1, partyId);

		result = query.getResultList();

		return result;
	}

	@SuppressWarnings("unchecked")
    @Override
    public List<Party> findByPartyRoleType(String partyRoleTypeDescription)
    {
		List<Party> result;
		Query query;

		query = em.createQuery("SELECT p.party FROM PartyRole p WHERE p.partyRoleType.description = ?");
		query.setParameter(1, partyRoleTypeDescription);

		result = query.getResultList();

		return result;
    }
}
