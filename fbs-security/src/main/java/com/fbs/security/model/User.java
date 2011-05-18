package com.fbs.security.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Comparable<User>, Serializable
{
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private String salt;
	private Integer tenantId;
	private Date validFrom = new Date();
	private Date validTo = new Date();
	
	public User()
	{
		
	}
	
	public String getUserName()
    {
    	return userName;
    }

	public void setUserName(String userName)
    {
    	this.userName = userName;
    }

	public String getPassword()
    {
    	return password;
    }

	public void setPassword(String password)
    {
    	this.password = password;
    }

	public String getSalt()
    {
    	return salt;
    }

	public void setSalt(String salt)
    {
    	this.salt = salt;
    }

	public Integer getTenantId()
    {
    	return tenantId;
    }

	public void setTenantId(Integer tenantId)
    {
    	this.tenantId = tenantId;
    }

	public Date getValidFrom()
    {
    	return validFrom;
    }

	public void setValidFrom(Date validFrom)
    {
    	this.validFrom = validFrom;
    }

	public Date getValidTo()
    {
    	return validTo;
    }

	public void setValidTo(Date validTo)
    {
    	this.validTo = validTo;
    }

	@Override
    public int compareTo(User o)
    {
		// see documentation
		if (o == null)
		{
			throw new NullPointerException();
		}

		if (this.userName != null && o.userName != null)
		{
			return (this.userName.compareTo(o.userName));
		}
		else if (this.userName == null && o.userName == null)
		{
			return password.compareTo(o.password);
		}
		else if (this.userName == null)
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

		User other = (User) obj;
		if (userName == null)
		{
			if (other.userName != null)
				return false;
		}
		else if (!userName.equals(other.userName))
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

		result = prime * result + ((userName == null) ? super.hashCode() : userName.hashCode());

		return result;
	}

}
