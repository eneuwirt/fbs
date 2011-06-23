package com.fbs.dmr.universal.service.impl.party;

import java.util.List;

import javax.persistence.Query;

import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.dmr.universal.model.party.PartyContactMechanismPurpose;
import com.fbs.dmr.universal.service.CrudServicePartyContactMechanismPurpose;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServicePartyContactMechanismPurposeImpl extends
        CrudServiceGenericImpl<PartyContactMechanismPurpose, Integer> implements
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
}
