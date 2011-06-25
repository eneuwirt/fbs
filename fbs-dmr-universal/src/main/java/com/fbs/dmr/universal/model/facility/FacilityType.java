package com.fbs.dmr.universal.model.facility;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fbs.dmr.universal.model.type.Type;

@Entity
@Table(name = "FACILITY_TYPES")
public class FacilityType extends Type
{
    private static final long serialVersionUID = 1L;
    
    public FacilityType()
    {
    	
    }

    @Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof FacilityType))
		{
			return false;
		}

		FacilityType other = (FacilityType) obj;
		
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
