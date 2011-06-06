package com.fbs.dmr.universal.model.party;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PARTY_RELATIONSHIP_TYPE")
public class PartyRelationshipType implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String description = "";
	private String name = "";
	@ManyToOne(optional=true)
	private PartyRoleType partyRoleTypeTo;
	@ManyToOne(optional=true)
	private PartyRoleType partyRoleTypeFrom;

	public PartyRelationshipType()
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public PartyRoleType getPartyRoleTypeTo()
    {
    	return partyRoleTypeTo;
    }

	public void setPartyRoleTypeTo(PartyRoleType partyRoleTypeTo)
    {
    	this.partyRoleTypeTo = partyRoleTypeTo;
    }

	public PartyRoleType getPartyRoleTypeFrom()
    {
    	return partyRoleTypeFrom;
    }

	public void setPartyRoleTypeFrom(PartyRoleType partyRoleTypeFrom)
    {
    	this.partyRoleTypeFrom = partyRoleTypeFrom;
    }

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof PartyRelationshipType))
		{
			return false;
		}

		PartyRelationshipType other = (PartyRelationshipType) obj;

		if (this.getId() == null && other.getId() == null)
		{
			return this.getName().equals(other.getName());
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
		int nameHashCode = (this.name == null) ? 0 : this.name.hashCode();

		result = prime * result + ((id == null) ? nameHashCode : id.hashCode());

		return result;
	}

	public String toString()
	{
		return String.format("PartyRelationshipType [id=%s, name=%s]", this.id, this.name);
	}
}
