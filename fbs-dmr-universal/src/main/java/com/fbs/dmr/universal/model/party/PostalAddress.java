package com.fbs.dmr.universal.model.party;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="POSTAL_ADDRESS")
public class PostalAddress implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    @Column(name="address_1")
    private String address1 = "";
    @Column(name="address_2")
    private String address2;
    private String directions;
    
    public PostalAddress()
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

	public String getAddress1()
    {
    	return address1;
    }

	public void setAddress1(String address1)
    {
    	this.address1 = address1;
    }

	public String getAddress2()
    {
    	return address2;
    }

	public void setAddress2(String address2)
    {
    	this.address2 = address2;
    }

	public String getDirections()
    {
    	return directions;
    }

	public void setDirections(String directions)
    {
    	this.directions = directions;
    }

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof PostalAddress))
		{
			return false;
		}

		PostalAddress other = (PostalAddress) obj;
		
		if (this.getId() == null && other.getId() == null)
		{
			return this.address1.equals(other.address1);
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
		int descrHashCode = (this.address1 == null) ? 0 : this.address1.hashCode();

		result = prime * result + ((id == null) ? descrHashCode : id.hashCode());

		return result;
	}

    public String toString()
	{
		return String.format("PostalAddress [id=%s, address1=%s]", this.id, this.address1);
	}
}
