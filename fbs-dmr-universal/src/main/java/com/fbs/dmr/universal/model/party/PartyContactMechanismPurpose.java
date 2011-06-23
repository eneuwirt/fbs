package com.fbs.dmr.universal.model.party;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fbs.dmr.universal.model.contact.ContactMechanismPurposeType;

@Entity
@Table(name = "PARTY_CONTACT_PURPOSES")
public class PartyContactMechanismPurpose implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
    @ManyToOne
	@JoinColumn(name = "party_contact_mechanism_id")
    private PartyContactMechanism partyContactMechanism;
    @ManyToOne
	@JoinColumn(name = "party_contact_mechanism_purpsoe_type_id")
    private ContactMechanismPurposeType contactMechanismPurposeType;

    public PartyContactMechanismPurpose()
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

	public PartyContactMechanism getPartyContactMechanism()
    {
    	return partyContactMechanism;
    }

	public void setPartyContactMechanism(PartyContactMechanism partyContactMechanism)
    {
    	this.partyContactMechanism = partyContactMechanism;
    }

	public ContactMechanismPurposeType getContactMechanismPurposeType()
    {
    	return contactMechanismPurposeType;
    }

	public void setContactMechanismPurposeType(ContactMechanismPurposeType contactMechanismPurposeType)
    {
    	this.contactMechanismPurposeType = contactMechanismPurposeType;
    }

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof PartyContactMechanismPurpose))
		{
			return false;
		}

		PartyContactMechanismPurpose other = (PartyContactMechanismPurpose) obj;

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

		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}


	public String toString()
	{
		return String.format("PartyContactMechanismPurpose [id=%s]", this.id);
	}
}
