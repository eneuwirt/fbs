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
@Table(name="PARTY_POSTAL_ADDRESS")
public class PartyPostalAddress implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    private String comment = "";
    @JoinColumn(name = "party_id")
	@ManyToOne
    private Party party;
    @JoinColumn(name = "postal_address_id")
	@ManyToOne
    private PostalAddress postalAddress;
    @Temporal(TemporalType.DATE)
	@Column(name="date_from")
	private Date dateFrom = new Date();
	@Temporal(TemporalType.DATE)
	@Column(name="date_to")
	private Date dateTo = DateUtils.getEndOfDays();
	
	public PartyPostalAddress()
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

	public Party getParty()
    {
    	return party;
    }

	public void setParty(Party party)
    {
    	this.party = party;
    }

	public void setPostalAddress(PostalAddress postalAddress)
    {
	    this.postalAddress = postalAddress;
    }

	public PostalAddress getPostalAddress()
    {
	    return postalAddress;
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

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof PartyPostalAddress))
		{
			return false;
		}

		PartyPostalAddress other = (PartyPostalAddress) obj;
		
		if (this.getId() == null && other.getId() == null)
		{
			return this.comment.equals(other.comment);
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
		return String.format("PartyPostalAddress [id=%s]", this.id);
	}
}
