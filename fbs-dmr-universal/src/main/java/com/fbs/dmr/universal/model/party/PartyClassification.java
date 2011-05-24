package com.fbs.dmr.universal.model.party;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fbs.dmr.util.DateUtils;

@Entity
@Table(name = "PARTY_CLASSIFICATIONS")
public class PartyClassification implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	private PartyType partyType;
	private Date dateFrom = new Date();
	private Date dateTo = DateUtils.getEndOfDays();


	public PartyClassification()
	{

	}


	public void setId(Integer id)
	{
		this.id = id;
	}


	public Integer getId()
	{
		return id;
	}


	public void setPartyType(PartyType partyType)
	{
		this.partyType = partyType;
	}


	public PartyType getPartyType()
	{
		return partyType;
	}


	public void setDateTo(Date dateTo)
	{
		this.dateTo = dateTo;
	}


	public Date getDateTo()
	{
		return dateTo;
	}


	public void setDateFrom(Date dateFrom)
	{
		this.dateFrom = dateFrom;
	}


	public Date getDateFrom()
	{
		return dateFrom;
	}


	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof PartyClassification))
		{
			return false;
		}

		PartyClassification other = (PartyClassification) obj;

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
		return String.format("PartyClassification [id=%s]", this.id);
	}
}
