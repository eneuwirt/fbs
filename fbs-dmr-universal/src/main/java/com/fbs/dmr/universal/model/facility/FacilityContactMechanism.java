package com.fbs.dmr.universal.model.facility;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fbs.dmr.universal.model.contact.ContactMechanism;

@Entity
@Table(name = "FACILITY_CONTACTS")
public class FacilityContactMechanism implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "facility_id")
	private Facility facility;
	@ManyToOne
	@JoinColumn(name = "contact_mechanism_id")
	private ContactMechanism contactMechanism;

	public FacilityContactMechanism()
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

	public Facility getFacility()
    {
    	return facility;
    }

	public void setFacility(Facility facility)
    {
    	this.facility = facility;
    }

	public ContactMechanism getContactMechanism()
    {
    	return contactMechanism;
    }

	public void setContactMechanism(ContactMechanism contactMechanism)
    {
    	this.contactMechanism = contactMechanism;
    }

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof FacilityContactMechanism))
		{
			return false;
		}

		FacilityContactMechanism other = (FacilityContactMechanism) obj;

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

		result = ((id == null) ? 0 : id.hashCode());

		result = prime * result;

		return result;
	}

	public String toString()
	{
		return String.format("PartyContactMechanism [id=%s]", this.id);
	}
}
