package com.fbs.dmr.universal.model.party;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fbs.dmr.universal.model.facility.Facility;

@Entity
@Table(name = "PARTY_FACILITIES")
public class PartyFacility  implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "party_id")
	private Party party;
	@ManyToOne
	@JoinColumn(name = "facility_id")
	private Facility facility;
	
	public PartyFacility()
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

	public Party getParty()
    {
    	return party;
    }

	public void setParty(Party party)
    {
    	this.party = party;
    }

	public Facility getFacility()
    {
    	return facility;
    }

	public void setFacility(Facility facility)
    {
    	this.facility = facility;
    }

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof PartyFacility))
		{
			return false;
		}

		PartyFacility other = (PartyFacility) obj;

		if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id))
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

		result = prime * ((id == null) ? 0 : id.hashCode());

		return result;
	}


	public String toString()
	{
		return String.format("PartyFacility [id=%s]", this.id);
	}
}
