package com.fbs.dmr.universal.model.postaladdress;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class GeographicBoundary implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
    private String name = "";
    private String code = "";
    private String abbreviation = "";
	
    public GeographicBoundary()
    {
    	
    }
    
    public Integer getId()
    {
    	return id;
    }

	public void setId(Integer id)
    {
    	this.id = id;
    }

	public String getName()
    {
    	return name;
    }

	public void setName(String name)
    {
    	this.name = name;
    }

	public String getCode()
    {
    	return code;
    }

	public void setCode(String code)
    {
    	this.code = code;
    }

	public String getAbbreviation()
    {
    	return abbreviation;
    }

	public void setAbbreviation(String abbreviation)
    {
    	this.abbreviation = abbreviation;
    }

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof GeographicBoundary))
		{
			return false;
		}

		GeographicBoundary other = (GeographicBoundary) obj;
		
		if (this.getId() == null && other.getId() == null)
		{
			return this.name.equals(other.name);
		}

		if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id))
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
		int descrHashCode = (this.name == null) ? 0 : this.name.hashCode();

		result = prime * result + ((id == null) ? descrHashCode : id.hashCode());

		return result;
	}

    public String toString()
	{
		return String.format("%s [id=%s, name=%s]", this.getClass().getName(), this.id, this.name);
	}
}
