package com.fbs.dmr.universal.service.impl.party;

import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.service.impl.CrudServiceGenericImpl;

public class CrudServicePartyImpl extends CrudServiceGenericImpl<Party, Integer>
{
	public CrudServicePartyImpl()
	{
		super(Party.class);
	}

	@Override
	public Party read(Integer id)
	{
		Party result;

		result = super.read(id);

		if (result != null)
		{
			result.getPartyClassifications().size();
			result.getPartyRoles().size();
		}

		return result;
	}
}
