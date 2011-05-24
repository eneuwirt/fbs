package com.fbs.dmr.universal.model.party;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PERSONS")
public class Person extends Party
{
    private static final long serialVersionUID = 1L;

    public Person()
    {
    	
    }
    
    @Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof Person))
		{
			return false;
		}

		Person other = (Person) obj;

		if ((this.getId() == null) ? (other.getId() != null) : !this.getId().equals(other.getId()))
		{
			return false;
		}
		
		return true;
	}


	@Override
	public int hashCode()
	{
		int result;

		result = super.hashCode();

		return result;
	}


	public String toString()
	{
		return String.format("Person [id=%s]", this.getId());
	}
}