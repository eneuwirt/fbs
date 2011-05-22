package com.fbs.dmr.universal.service.impl;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fbs.dmr.universal.model.party.Organization;

public class CrudServiceOrganizationImpl extends CrudServiceImpl<Organization, Integer>
{
	public CrudServiceOrganizationImpl()
    {
	    super(Organization.class);
    } 
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
    public Organization create(Organization t)
    {
		EntityManager em = this.emf.createEntityManager();
		
		em.persist(t);
	       
	    return t;
    }
}
