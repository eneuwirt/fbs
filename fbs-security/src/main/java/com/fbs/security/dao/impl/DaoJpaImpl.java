package com.fbs.security.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fbs.security.dao.Dao;

public class DaoJpaImpl<T extends Serializable, ID extends Serializable> implements Dao<T, ID>
{
	private static final long serialVersionUID = 1L;
	private EntityManager em;
	private final Class<T> clazz;


	public DaoJpaImpl(final Class<T> persistentClass)
	{
		super();

		this.clazz = persistentClass;
	}


	@PersistenceContext
	public void setEntityManager(EntityManager em)
	{
		this.em = em;
	}


	@Override
	public T create(T t)
	{
		this.em.persist(t);

		return t;
	}


	@Override
	public T read(ID id)
	{
		final T result = em.find(clazz, id);

		return result;
	}


	@Override
	public T update(T t)
	{
		T result;

		result = this.em.merge(t);

		return result;
	}


	@Override
	public void delete(T t)
	{
		this.em.remove(t);
	}
}
