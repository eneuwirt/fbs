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

    public String toString()
	{
		return String.format("PartyRelationshipStatusType [id=%s descr=%s]", this.getId(), this.getDescription());
	}
}
