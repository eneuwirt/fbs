package com.fbs.dmr.universal.model.party;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fbs.dmr.universal.model.type.Type;

@Entity
@Table(name = "PARTY_RELATIONSHIP_STATUS_TYPES")
public class PartyRelationshipStatusType extends Type
{
    private static final long serialVersionUID = 1L;
    
    public PartyRelationshipStatusType()
    {
    	
    }
    @Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof PartyRelationshipStatusType))
		{
			return false;
		}

		PartyRelationshipStatusType other = (PartyRelationshipStatusType) obj;

		if (this.getId() == null && other.getId() == null)
		{
			return this.getDescription().equals(other.getDescription());
		}

		if ((this.getId() == null) ? (other.getId() != null) : !this.getId().equals(other.getId()))
		{
			return false;
		}

		return true;
	}
}
