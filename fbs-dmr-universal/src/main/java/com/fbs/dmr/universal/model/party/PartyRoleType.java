package com.fbs.dmr.universal.model.party;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fbs.dmr.universal.model.type.Type;

@Entity
@Table(name = "PARTY_ROLE_TYPES")
public class PartyRoleType extends Type
{
    private static final long serialVersionUID = 1L;

    public PartyRoleType()
    {
    	
    }
    
    public String toString()
	{
		return String.format("PartyRoleType [id=%s, descr=%s]", this.getId(), this.getDescription());
	}
}
