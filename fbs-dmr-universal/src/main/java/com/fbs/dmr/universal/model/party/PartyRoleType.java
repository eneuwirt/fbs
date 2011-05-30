package com.fbs.dmr.universal.model.party;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fbs.dmr.universal.model.type.RoleType;

@Entity
@Table(name = "PARTY_ROLE_TYPES")
public class PartyRoleType extends RoleType
{
    private static final long serialVersionUID = 1L;

    public PartyRoleType()
    {
    	
    }
    
    public String toString()
	{
		return String.format("PartyRoleType [id=%s]", this.getId());
	}
}
