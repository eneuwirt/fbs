package com.fbs.dmr.universal.model.contact;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fbs.dmr.universal.model.type.Type;

@Entity
@Table(name = "CONTACT_PURPOSE_TYPES")
public class ContactMechanismPurposeType extends Type
{
    private static final long serialVersionUID = 1L;
    
    @Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof ContactMechanismPurposeType))
		{
			return false;
		}

		ContactMechanismPurposeType other = (ContactMechanismPurposeType) obj;

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
