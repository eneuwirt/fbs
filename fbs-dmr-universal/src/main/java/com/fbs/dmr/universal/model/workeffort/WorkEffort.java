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

@Entity
@Table(name = "WORK_EFFORTS")
public class WorkEffort implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "work_effort_type_id")
	private WorkEffortType workEffortType;
	private String name = "";
	private String description = "";
	@Temporal(TemporalType.DATE)
	@Column(name="scheduled_start_date")
	private Date scheduledStartDate = new Date();
	@Temporal(TemporalType.DATE)
	@Column(name="scheduled_completion_date")
	private Date scheduledCompletionDate = new Date();
	@Column (name="total_money_allowed")
	private Double totalMoneyAllowed = 0.0;
	@Column (name="total_minutes_allowed")
	private Integer totalMinutesAllowed = 0;
	@Column (name="estimated_minutes")
	private Integer estimatedMinutes = 0;
	@Temporal(TemporalType.DATE)
	@Column(name="actual_start_date")
	private Date actualStartDate = new Date();
	@Temporal(TemporalType.DATE)
	@Column(name="actual_completion_date")
	private Date actualCompletionDate = new Date();
	@Column(name="actual_minutes")
	private Integer actualMinutes = 0;

	public WorkEffort()
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

	public WorkEffortType getWorkEffortType()
	{
		return workEffortType;
	}

	public void setWorkEffortType(WorkEffortType workEffortType)
	{
		this.workEffortType = workEffortType;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Date getScheduledStartDate()
	{
		return scheduledStartDate;
	}

	public void setScheduledStartDate(Date scheduledStartDate)
	{
		this.scheduledStartDate = scheduledStartDate;
	}

	public Date getScheduledCompletionDate()
	{
		return scheduledCompletionDate;
	}

	public void setScheduledCompletionDate(Date scheduledCompletionDate)
	{
		this.scheduledCompletionDate = scheduledCompletionDate;
	}

	public Double getTotalMoneyAllowed()
	{
		return totalMoneyAllowed;
	}

	public void setTotalMoneyAllowed(Double totalMoneyAllowed)
	{
		this.totalMoneyAllowed = totalMoneyAllowed;
	}

	public Integer getTotalMinutesAllowed()
	{
		return totalMinutesAllowed;
	}

	public void setTotalMinutesAllowed(Integer totalMinutesAllowed)
	{
		this.totalMinutesAllowed = totalMinutesAllowed;
	}

	public Integer getEstimatedMinutes()
	{
		return estimatedMinutes;
	}

	public void setEstimatedMinutes(Integer estimatedMinutes)
	{
		this.estimatedMinutes = estimatedMinutes;
	}

	public Date getActualStartDate()
	{
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate)
	{
		this.actualStartDate = actualStartDate;
	}

	public Date getActualCompletionDate()
	{
		return actualCompletionDate;
	}

	public void setActualCompletionDate(Date actualCompletionDate)
	{
		this.actualCompletionDate = actualCompletionDate;
	}

	public Integer getActualMinutes()
	{
		return actualMinutes;
	}

	public void setActualMinutes(Integer actualMinutes)
	{
		this.actualMinutes = actualMinutes;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof WorkEffort))
		{
			return false;
		}

		WorkEffort other = (WorkEffort) obj;

		if (this.id == null && other.id == null)
		{
			return this.name.equals(other.name) && this.description.equals(other.description);
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
		int attrHash = this.name.hashCode() + this.description.hashCode();

		result = prime * ((id == null) ? attrHash : id.hashCode());

		return result;
	}

	public String toString()
	{
		return String.format("WorkEffort [id=%s]", this.id);
	}
}
