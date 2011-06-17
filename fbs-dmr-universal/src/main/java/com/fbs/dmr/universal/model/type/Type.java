package com.fbs.dmr.universal.model.type;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Type  implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
    private String description ="";

	public Type()
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

	public String getDescription()
    {
    	return description;
    }

	public void setDescription(String description)
    {
    	this.description = description;
    }

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof Type))
		{
			return false;
		}

		Type other = (Type) obj;
		
		if (this.getId() == null && other.getId() == null)
		{
			return this.getDescription().equals(other.getDescription());
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
		int descrHashCode = (this.description == null) ? 0 : this.description.hashCode();

		result = prime * result + ((id == null) ? descrHashCode : id.hashCode());

		return result;
	}


	public String toString()
	{
		return String.format("Type [id=%s, description=%s]", this.id, this.description);
	}
}

