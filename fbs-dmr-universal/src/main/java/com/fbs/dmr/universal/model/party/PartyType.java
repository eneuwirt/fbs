package com.fbs.dmr.universal.model.party;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PARTY_TYPES")
public class PartyType implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
    @Column(unique=true)
    private String description = "";
    
    

	public PartyType()
	{

	}


	public void setId(Integer id)
	{
		this.id = id;
	}


	public Integer getId()
	{
		return id;
	}
	
	public void setDescription(String description)
    {
	    this.description = description;
    }


	public String getDescription()
    {
	    return description;
    }


	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof PartyType))
		{
			return false;
		}		

		PartyType other = (PartyType) obj;
		
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
		int result = 1;
		final int prime = 31;

		result = prime * result + ((id == null) ? 0 : id.hashCode());
		
		result = result + ((this.description == null) ? 0 : this.description.hashCode());

		return result;
	}

	
	@Override
	public String toString()
	{
		return String.format("PartyType [id=%s, description=%s]", this.id, this.description);
	}
}
