package com.fbs.dmr.universal.service.impl;

import org.springframework.stereotype.Service;

import com.fbs.dmr.universal.model.party.Organization;

@Service
public class CrudServiceOrganizationImpl extends CrudServiceImpl<Organization, Integer>
{
	public CrudServiceOrganizationImpl()
    {
	    super(Organization.class);
    } 
}
