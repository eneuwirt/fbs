package com.fbs.dmr.universal.model.workeffort;

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

import com.fbs.dmr.universal.model.party.PartyRole;

@Entity
@Table(name = "TIME_SHEETS")
public class TimeSheet implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String comment = "";
	@Temporal(TemporalType.DATE)
	@Column(name = "from_date")
	private Date fromDate = new Date();
	@Temporal(TemporalType.DATE)
	@Column(name = "to_date")
	private Date toDate = new Date();
	@ManyToOne
	@JoinColumn(name = "party_role_id")
	private PartyRole partyRole;

	public TimeSheet()
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

	public Date getFromDate()
	{
		return fromDate;
	}

	public void setFromDate(Date fromDate)
	{
		this.fromDate = fromDate;
	}

	public Date getToDate()
	{
		return toDate;
	}

	public void setToDate(Date toDate)
	{
		this.toDate = toDate;
	}

	public PartyRole getPartyRole()
	{
		return partyRole;
	}

	public void setPartyRole(PartyRole partyRole)
	{
		this.partyRole = partyRole;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof TimeSheet))
		{
			return false;
		}

		TimeSheet other = (TimeSheet) obj;

		if (this.id == null && other.id == null)
		{
			return this.comment.equals(other.comment) && this.fromDate.equals(other.fromDate);
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
		int attrHash = comment.hashCode() + this.fromDate.hashCode();

		result = prime * ((id == null) ? attrHash : id.hashCode());

		return result;
	}

	public String toString()
	{
		return String.format("TimeSheet [id=%s]", this.id);
	}
}
