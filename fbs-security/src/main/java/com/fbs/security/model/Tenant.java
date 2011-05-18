package com.fbs.security.model;

import java.io.Serializable;
import java.util.Date;

public class Tenant implements Comparable<Tenant>, Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String description;
	private Date validFrom = new Date();
	private Date validTo = new Date();


	public Tenant()
	{

	}


	public void setDescription(String description)
	{
		this.description = description;
	}


	public String getDescription()
	{
		return description;
	}


	public void setId(Integer id)
	{
		this.id = id;
	}


	public Integer getId()
	{
		return id;
	}


	public void setValidFrom(Date validFrom)
	{
		this.validFrom = validFrom;
	}


	public Date getValidFrom()
	{
		return validFrom;
	}


	public void setValidTo(Date validTo)
	{
		this.validTo = validTo;
	}


	public Date getValidTo()
	{
		return validTo;
	}


	@Override
	public int compareTo(Tenant o)
	{
		// see documentation
		if (o == null)
		{
			throw new NullPointerException();
		}

		if (this.getId() != null && o.getId() != null)
		{
			return (this.getId() - o.getId());
		}
		else if (this.getId() == null && o.getId() == null)
		{
			return this.description.compareTo(o.description);
		}
		else if (this.id == null)
		{
			return 1;
		}
		
		return -1;
	}


	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (this == obj)
		{
			return true;
		}

		Tenant other = (Tenant) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
		{
			return false;
		}

		return true;
	}


	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;

		result = prime * result + ((id == null) ? super.hashCode() : id.hashCode());

		return result;
	}
}
