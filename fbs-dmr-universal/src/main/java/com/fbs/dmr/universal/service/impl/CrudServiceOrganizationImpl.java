package com.fbs.dmr.universal.service.impl;

import org.springframework.stereotype.Repository;

import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.dmr.universal.service.CrudService;

@Repository
public class CrudServiceOrganizationImpl extends CrudServiceGenericImpl<Organization, Integer> implements
        CrudService<Organization, Integer>
{
    private static final long serialVersionUID = 1L;

	public CrudServiceOrganizationImpl()
	{
		super(Organization.class);
	}
}
