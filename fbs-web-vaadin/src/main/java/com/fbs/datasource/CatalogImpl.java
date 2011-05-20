package com.fbs.datasource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CatalogImpl implements Catalog, Serializable
{
    private static final long serialVersionUID = 1L;
    private EntityManager em;

	@SuppressWarnings("unchecked")
    @Override
	public List<Item> getItems()
	{
		List<Item> result = new ArrayList<Item>();
		
		Query query = em.createQuery("select i from Item i");
		result = query.getResultList();
		
		return result;
	}

	@PersistenceContext
	public void setEm(EntityManager em)
    {
	    this.em = em;
    }

	@Override
    public void createItem()
    {
	    Item item = new Item("Item HALLO", 20.00);
	    
	    em.persist(item);
    }

}
