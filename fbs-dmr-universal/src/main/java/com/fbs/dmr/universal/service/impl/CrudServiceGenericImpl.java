package com.fbs.dmr.universal.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.fbs.dmr.universal.service.CrudService;

@Transactional
public class CrudServiceGenericImpl<T, ID extends Serializable> implements CrudService<T, ID>
{
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CrudServiceGenericImpl.class.getName());
	protected EntityManager em;
	private Class<T> entityClass;


	public CrudServiceGenericImpl(Class<T> entityClass)
	{
		this.entityClass = entityClass;
	}


	@Override
	public void create(T t)
	{
		logger.info(">create");
		em.persist(t);
		logger.info("<create");
	}


	@Override
	public T read(ID id)
	{
		T result;
		
		logger.info(">read");
		result = em.find(entityClass, id);
		logger.info("<read");

		return result;
	}


	@Override
	public void update(T t)
	{
		logger.info(">update");
		em.merge(t);
		logger.info("<update");
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
		
		logger.info(">delete");
		em.remove(entity);
		logger.info("<delete");
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

		logger.info(">findAll");
		query = em.createQuery("FROM " + entityClass.getName());

		result = query.getResultList();

		logger.info("<findAll");
		return result;
	}
}
