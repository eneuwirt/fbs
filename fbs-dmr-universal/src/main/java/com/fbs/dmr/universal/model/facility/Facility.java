package com.fbs.dmr.universal.model.facility;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FACILITIES")
public class Facility implements Serializable
{
    private static final long serialVersionUID = 1L; 
    
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
    private String description = "";
    @ManyToOne
    @JoinColumn(name = "facility_type_id")
    private FacilityType facilityType;
    
    public Facility()
    {
    	
    }
    
    public Integer getId()
    {
    	return id;
    }

	public void setId(Integer id)
    {
    	this.id = id;
    }

	public String getDescription()
    {
    	return description;
    }

	public void setDescription(String description)
    {
    	this.description = description;
    }

	public FacilityType getFacilityType()
    {
    	return facilityType;
    }

	public void setFacilityType(FacilityType facilityType)
    {
    	this.facilityType = facilityType;
    }

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof Facility))
		{
			return false;
		}

		Facility other = (Facility) obj;

		if (this.getId() == null && other.getId() == null)
		{
			return this.description.equals(other.description);
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
		final int prime = 31;
		int result = 1;
		int descrHashCode = this.description.hashCode();

		result = prime * ((this.getId() == null) ? descrHashCode : this.getId().hashCode());

		return result;
	}

	public String toString()
	{
		return String.format("FacilityTest [id=%s, description=%s]", this.getId(), this.description);
	}
}
