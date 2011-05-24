package com.fbs.dmr.universal.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.fbs.dmr.universal.service.CrudService;

public class CrudServiceGenericImpl<T, ID extends Serializable> implements CrudService<T, ID>
{
    private static final long serialVersionUID = 1L;
	protected EntityManager em;
	private Class<T> entityClass;


	public CrudServiceGenericImpl(Class<T> entityClass)
	{
		this.entityClass = entityClass;
	}


	@Override
	public void save(T t)
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


	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll()
	{
		List<T> result = null;
		Query query;		

		query = em.createQuery("FROM " + entityClass.getName());

		result = query.getResultList();

		return result;
	}
}
