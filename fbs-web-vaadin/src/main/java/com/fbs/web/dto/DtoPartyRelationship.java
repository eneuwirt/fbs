package com.fbs.web.dto;

import java.io.Serializable;
import java.util.Date;

import com.fbs.dmr.universal.model.party.PartyRelationship;
import com.fbs.dmr.universal.model.party.PartyRelationshipType;
import com.fbs.dmr.util.DateUtils;

public class DtoPartyRelationship implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String comment = "";
	private Date dateFrom = new Date();
	private Date dateTo = DateUtils.getEndOfDays();
	private PartyRelationshipType partyRelationshipType;
	
	public DtoPartyRelationship()
	{
		
	}

	public DtoPartyRelationship(PartyRelationship rel)
    {
	    this.id = rel.getId();
	    this.comment = rel.getComment();
	    this.dateFrom = rel.getDateFrom();
	    this.dateTo = rel.getDateTo();
	    this.partyRelationshipType = rel.getPartyRelationshipType();
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
	
	public String toString()
	{
		return String.format("DtoPartyRelationship [id=%s, comment=%s]", this.id, this.comment);
	}
}
