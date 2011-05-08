package com.fbs.security.model;

import java.io.Serializable;
import java.util.Date;

public class Tenant implements Serializable
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

}
