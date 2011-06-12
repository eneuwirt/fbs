package com.fbs.dmr.universal.model.party;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fbs.dmr.util.DateUtils;

@Entity
@Table(name = "PARTY_ROLES")
@Inheritance(strategy=InheritanceType.JOINED)
public class PartyRole implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	private Party party;
	@ManyToOne
	private PartyRoleType partyRoleType;
	@Temporal(TemporalType.DATE)
	@Column(name="date_from")
	private Date dateFrom = new Date();
	@Column(name="date_to")
	@Temporal(TemporalType.DATE)
	private Date dateTo = new Date(DateUtils.getEndOfDays().getTime());
	
	public PartyRole()
	{
		
	}
	
	public void setParty(Party party)
    {
	    this.party = party;
    }

	public Party getParty()
    {
	    return party;
    }

	public Date getDateFrom()
    {
    	return dateFrom;
    }

	public void setDateFrom(Date dateFrom)
    {
    	this.dateFrom = dateFrom;
    }

	public Date getDateTo()
    {
    	return dateTo;
    }

	public void setDateTo(Date dateTo)
    {
    	this.dateTo = dateTo;
    }

	public void setId(Integer id)
    {
	    this.id = id;
    }

	public Integer getId()
    {
	    return id;
    }

	public void setPartyRoleType(PartyRoleType partyRoleType)
    {
	    this.partyRoleType = partyRoleType;
    }

	public PartyRoleType getPartyRoleType()
    {
	    return partyRoleType;
    }

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof PartyRole))
		{
			return false;
		}

		PartyRole other = (PartyRole) obj;
		
		if (this.id == null || other.id == null)
		{
			return (this.partyRoleType.equals(other.partyRoleType) && this.party.equals(other.party));
		}

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
		return String.format("PartyRole [id=%s, roleType=%s]", this.id, this.partyRoleType.getDescription());
	}
}
