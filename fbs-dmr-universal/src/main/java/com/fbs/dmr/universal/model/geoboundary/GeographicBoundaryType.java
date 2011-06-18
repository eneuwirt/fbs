package com.fbs.dmr.universal.model.geoboundary;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fbs.dmr.universal.model.type.Type;

@Entity
@Table(name = "GEO_BOUNDARY_TYPE")
public class GeographicBoundaryType extends Type
{
    private static final long serialVersionUID = 1L;
  
    public GeographicBoundaryType()
    {
    	
    } 
 

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof GeographicBoundaryType))
		{
			return false;
		}

		GeographicBoundaryType other = (GeographicBoundaryType) obj;
		
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
