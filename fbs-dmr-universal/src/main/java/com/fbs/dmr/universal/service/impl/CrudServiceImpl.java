package com.fbs.dmr.universal.service.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fbs.dmr.universal.service.CrudService;

public class CrudServiceImpl<T, ID extends Serializable> implements CrudService<T, ID>
{
	protected EntityManager em;
	private Class<T> entityClass;
	
	public CrudServiceImpl(Class<T> entityClass)
	{
		this.entityClass = entityClass;
	}
	
	
	@Override
    public T create(T t)
    {
		em.persist(t);
		
		em.flush();
	       
	    return t;
    }

	@Override
    public void update(T t)
    {		
		em.persist(t);
    }

	@Override
    public T read(ID id)
    {
	    T result;
	   	    
	    result = em.find(entityClass, id);
	    
	    return result;
    }

	@Override
    public void delete(ID id)
    {
		T entity;
		
		entity = this.read(id);
		
		if (entity == null)
		{
			return;
		}
		
		em.remove(entity);
    }

	@PersistenceContext
	public void setEntityManager(EntityManager em)
    {
	    this.em = em;
    }
}
