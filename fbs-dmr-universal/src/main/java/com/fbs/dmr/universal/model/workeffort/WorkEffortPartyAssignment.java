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

import com.fbs.dmr.universal.model.party.Party;

@Entity
@Table(name = "PARTY_WORK_EFFORT")
public class WorkEffortPartyAssignment implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Temporal(TemporalType.DATE)
	@Column(name = "from_date")
	private Date fromDate = new Date();
	@Temporal(TemporalType.DATE)
	@Column(name = "to_date")
	private Date toDate = new Date();
	private Double minutes = 0.0;
	private String comment = "";
	@ManyToOne
    @JoinColumn(name = "party_id")
    private Party party;
	@ManyToOne
    @JoinColumn(name = "work_effort_id")
    private WorkEffort workEffort;

	public WorkEffortPartyAssignment()
	{

	}

	public Party getParty()
    {
        return party;
    }

    public void setParty(Party party)
    {
        this.party = party;
    }

    public WorkEffort getWorkEffort()
    {
        return workEffort;
    }

    public void setWorkEffort(WorkEffort workEffort)
    {
        this.workEffort = workEffort;
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

	public Double getMinutes()
    {
    	return minutes;
    }

	public void setMinutes(Double minutes)
    {
    	this.minutes = minutes;
    }

	public String getComment()
    {
    	return comment;
    }

	public void setComment(String comment)
    {
    	this.comment = comment;
    }

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof WorkEffortPartyAssignment))
		{
			return false;
		}

		WorkEffortPartyAssignment other = (WorkEffortPartyAssignment) obj;

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
		return String.format("WorkEffortPartyAssignment [id=%s]", this.id);
	}
}
