package com.fbs.dmr.universal.model.party;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fbs.dmr.util.DateUtils;

@Entity
@Table(name = "PARTY_RELATIONSHIP")
public class PartyRelationship implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String comment = "";
	@Temporal(TemporalType.DATE)
	@Column(name="date_from")
	private Date dateFrom = new Date();
	@Temporal(TemporalType.DATE)
	@Column(name="date_to")
	private Date dateTo = DateUtils.getEndOfDays();
	@JoinColumn(name = "party_relshiptype_id")
	@ManyToOne(optional=false)
	private PartyRelationshipType partyRelationshipType;
	@JoinColumn(name = "party_roleto_id")
	@ManyToOne(optional=false)
	private PartyRole partyRoleTo;
	@JoinColumn(name = "party_rolefrom_id")
	@ManyToOne(optional=false)
	private PartyRole partyRoleFrom;
	@JoinColumn(name = "party_relship_statustype_id")
	@ManyToOne
	private PartyRelationshipStatusType partyRelationshipStatusType;
	@JoinColumn(name = "party_relship_prioritytype_id")
	@ManyToOne
	private PriorityType priorityType;

	public PartyRelationship()
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

	public String getComment()
    {
    	return comment;
    }

	public void setComment(String comment)
    {
    	this.comment = comment;
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

	public PartyRelationshipType getPartyRelationshipType()
    {
    	return partyRelationshipType;
    }

	public void setPartyRelationshipType(PartyRelationshipType partyRelationshipType)
    {
    	this.partyRelationshipType = partyRelationshipType;
    }

	public PartyRole getPartyRoleTo()
    {
    	return partyRoleTo;
    }

	public void setPartyRoleTo(PartyRole partyRoleTo)
    {
    	this.partyRoleTo = partyRoleTo;
    }

	public PartyRole getPartyRoleFrom()
    {
    	return partyRoleFrom;
    }

	public void setPartyRoleFrom(PartyRole partyRoleFrom)
    {
    	this.partyRoleFrom = partyRoleFrom;
    }
	
	public void setPartyRelationshipStatusType(PartyRelationshipStatusType partyRelationshipStatusType)
    {
	    this.partyRelationshipStatusType = partyRelationshipStatusType;
    }

	public PartyRelationshipStatusType getPartyRelationshipStatusType()
    {
	    return partyRelationshipStatusType;
    }

	public void setPriorityType(PriorityType priorityType)
    {
	    this.priorityType = priorityType;
    }

	public PriorityType getPriorityType()
    {
	    return priorityType;
    }

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof PartyRelationship))
		{
			return false;
		}

		PartyRelationship other = (PartyRelationship) obj;
		
		if (this.getId() == null && other.getId() == null)
		{
			return this.getComment().equals(other.getComment());
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
		int commentHashCode = (this.comment == null) ? 0 : this.comment.hashCode();

		result = prime * result + ((id == null) ? commentHashCode : id.hashCode());

		return result;
	}


	public String toString()
	{
		return String.format("PartyRelationShip [id=%s, comment=%s]", this.id, this.comment);
	}
}
