package com.fbs.dmr.universal.model.contact;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ContactMechanism implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
    
    public ContactMechanism()
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


	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof ContactMechanism))
		{
			return false;
		}

		ContactMechanism other = (ContactMechanism) obj;


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

		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}


	public String toString()
	{
		return String.format("ContactMechanism [id=%s]", this.id);
	}

}
