package com.fbs.dmr.universal.model.party;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fbs.dmr.universal.model.type.Type;

@Entity
@Table(name = "PARTY_RELATIONSHIP_TYPE")
public class PartyRelationshipType extends Type
{
	private static final long serialVersionUID = 1L;
	
	private String name = "";
	@JoinColumn(name = "party_roletype_to_id")
	@ManyToOne(optional=true)
	private PartyRoleType partyRoleTypeTo;
	@JoinColumn(name = "party_roletype_from_id")
	@ManyToOne(optional=true)
	private PartyRoleType partyRoleTypeFrom;

	public PartyRelationshipType()
	{

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

		if ((this.getId() == null) ? (other.getId() != null) : !this.getId().equals(other.getId()))
		{
			return false;
		}

		return true;
	}
}
