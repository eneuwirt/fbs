package com.fbs.dmr.universal.service.impl;


import org.springframework.stereotype.Repository;

import com.fbs.dmr.universal.model.party.Organization;

@Repository
public class CrudServiceOrganizationImpl extends CrudServiceImpl<Organization, Integer>
{
	public CrudServiceOrganizationImpl()
    {
	    super(Organization.class);
    } 
}
