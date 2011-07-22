package com.fbs.dmr.universal.service.crud.impl.party;

import org.springframework.stereotype.Repository;

import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.dmr.universal.service.crud.CrudService;
import com.fbs.dmr.universal.service.crud.impl.CrudServiceGenericImpl;

@Repository
public class CrudServiceOrganizationImpl extends CrudServiceGenericImpl<Organization, Integer> implements
        CrudService<Organization, Integer>
{
	public CrudServiceOrganizationImpl()
	{
		super(Organization.class);
	}
}
