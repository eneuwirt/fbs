package com.fbs.dmr.universal.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fbs.dmr.universal.model.party.Party;

@Transactional(propagation=Propagation.REQUIRED)
public class CrudParty
{
	private EntityManager em;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em)
    {
    	this.em = em;
    }

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void create(Party t)
    {
		em.persist(t);
		
		em.flush();
    }
}
