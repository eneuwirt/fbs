package com.fbs.dmr.universal.model.party;

public class Organization extends Party
{
	private static final long serialVersionUID = 1L;
	private String name = "";


	public Organization()
	{

	}


	public void setName(String name)
	{
		this.name = name;
	}


	public String getName()
	{
		return name;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof Organization))
		{
			return false;
		}

		Organization other = (Organization) obj;
		
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


	@Override
	public int hashCode()
	{
		int result = 1;

		result = super.hashCode() + ((name == null) ? 0 : name.hashCode());

		return result;
	}


	@Override
	public String toString()
	{
		return String.format("Organization [id=%s], [name=%s]", this.getId(), this.getName());
	}
}
