package com.fbs.dmr.universal.service.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fbs.dmr.universal.service.Crud;

public abstract class CrudServiceImpl<T, ID extends Serializable> implements Crud<T, ID>
{
	protected EntityManagerFactory emf;
	private Class<T> entityClass;
	
	public CrudServiceImpl(Class<T> entityClass)
	{
		this.entityClass = entityClass;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
    public T create(T t)
    {
		EntityManager em = this.emf.createEntityManager();
		
		em.persist(t);
	       
	    return t;
    }

	@Override
    public void update(T t)
    {
		EntityManager em = this.emf.createEntityManager();
		
		em.persist(t);
    }

	@Override
    public T read(ID id)
    {
	    T result;
	    EntityManager em = this.emf.createEntityManager();
	    
	    result = em.find(entityClass, id);
	    
	    return result;
    }

	@Override
    public void delete(ID id)
    {
		T entity;
		EntityManager em = this.emf.createEntityManager();
		
		entity = this.read(id);
		
		if (entity == null)
		{
			return;
		}
		
		em.remove(entity);
    }

	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory emf)
    {
	    this.emf = emf;
    }
}
