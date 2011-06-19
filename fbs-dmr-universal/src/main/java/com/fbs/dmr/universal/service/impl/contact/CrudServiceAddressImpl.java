package com.fbs.dmr.universal.service.impl.contact;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.fbs.dmr.universal.model.contact.Address;
import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.dmr.universal.model.contact.PostalAddressBoundary;
import com.fbs.dmr.universal.model.geoboundary.City;
import com.fbs.dmr.universal.model.geoboundary.PostalCode;
import com.fbs.dmr.universal.service.CrudService;

/**
 * Address is not an entity but an dto.
 * 
 * @author neuwirt
 * 
 */
public class CrudServiceAddressImpl implements CrudService<Address, Integer>
{
	private static Logger logger = Logger.getLogger(CrudServiceAddressImpl.class.getName());
	@PersistenceContext
	private EntityManager em;

	public void setEm(EntityManager em)
	{
		this.em = em;
	}

	@Override
	public void create(Address t)
	{
		PostalAddress pa;
		PostalCode zip;
		City city;
		PostalAddressBoundary pab;

		logger.info(">create");

		pa = this.findByAddress(t.getAddr1(), t.getAddr2());
		if (pa == null)
		{
			pa = new PostalAddress();
			pa.setAddress1(t.getAddr1());
			pa.setAddress2(t.getAddr2());
			pa.setContactMechanismType(t.getCmt());
			this.em.persist(pa);
		}

		zip = this.findByCode(t.getZip());
		if (zip == null)
		{
			zip = new PostalCode();
			zip.setCode(t.getZip());
			this.em.persist(zip);
		}
		pab = new PostalAddressBoundary();
		pab.setPostallAddress(pa);
		pab.setGeoBoundary(zip);
		this.em.persist(pab);

		city = this.findByName(t.getCity());
		if (city == null)
		{
			city = new City();
			city.setName(t.getCity());
			this.em.persist(city);
		}
		pab = new PostalAddressBoundary();
		pab.setPostallAddress(pa);
		pab.setGeoBoundary(zip);
		this.em.persist(pab);
	}

	private City findByName(String city)
	{
		City result = null;
		Query query;
		String queryString;

		try
		{
			queryString = String.format("SELECT c FROM City c WHERE c.name = ?1");
			query = em.createQuery(queryString);
			query.setParameter(1, city);
			result = (City) query.getSingleResult();
		}
		catch (PersistenceException pe)
		{
			logger.log(Level.SEVERE, "Exception: " + pe.getMessage());

			result = null;
		}

		return result;
	}

	private PostalCode findByCode(String code)
	{
		PostalCode result = null;
		Query query;
		String queryString;

		try
		{
			queryString = String.format("SELECT c FROM PostalCode c WHERE c.code = ?1");
			query = em.createQuery(queryString);
			query.setParameter(1, code);
			result = (PostalCode) query.getSingleResult();
		}
		catch (PersistenceException pe)
		{
			logger.log(Level.SEVERE, "Exception: " + pe.getMessage());

			result = null;
		}

		return result;
	}

	private PostalAddress findByAddress(String addr1, String addr2)
	{
		PostalAddress result = null;
		Query query;
		String queryString;

		try
		{
			queryString = String.format("SELECT a FROM PostalAddress a WHERE a.address1 = ?1 AND a.address1 = ?2");
			query = em.createQuery(queryString);
			query.setParameter(1, addr1);
			query.setParameter(1, addr2);
			result = (PostalAddress) query.getSingleResult();
		}
		catch (PersistenceException pe)
		{
			logger.log(Level.SEVERE, "Exception: " + pe.getMessage());

			result = null;
		}

		return result;
	}

	@Override
	public Address read(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Address t)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public List<Address> findAll()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
