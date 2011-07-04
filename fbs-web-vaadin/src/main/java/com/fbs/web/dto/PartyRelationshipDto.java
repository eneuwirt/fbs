package com.fbs.web.dto;

import java.io.Serializable;

import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyRelationship;
import com.fbs.dmr.universal.model.party.PartyRelationshipStatusType;
import com.fbs.dmr.universal.model.party.PartyRelationshipType;
import com.fbs.dmr.universal.model.party.PriorityType;

public class PartyRelationshipDto implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String comment = "";
    private PartyRelationshipType partyRelationshipType;
    private PartyRelationshipStatusType partyRelationshipStatusType;
    private PriorityType priorityType;

    private Party partyFrom;
    private Party partyTo;

    public PartyRelationshipDto()
    {

    }

    public PartyRelationshipDto(PartyRelationship partyRelationship)
    {
       this.setPartyRelationship(partyRelationship);
    }

    public void setPartyRelationship(PartyRelationship partyRelationship)
    {
        this.id = partyRelationship.getId();
        this.comment = partyRelationship.getComment();

        this.partyRelationshipType = partyRelationship.getPartyRelationshipType();

        this.partyRelationshipStatusType = partyRelationship.getPartyRelationshipStatusType();
        this.priorityType = partyRelationship.getPriorityType();

        this.partyFrom = partyRelationship.getPartyRoleFrom().getParty();

        this.partyTo = partyRelationship.getPartyRoleTo().getParty();
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

    public PartyRelationshipType getPartyRelationshipType()
    {
        return partyRelationshipType;
    }

    public void setPartyRelationshipType(PartyRelationshipType partyRelationshipType)
    {
        this.partyRelationshipType = partyRelationshipType;
    }

    public PartyRelationshipStatusType getPartyRelationshipStatusType()
    {
        return partyRelationshipStatusType;
    }

    public void setPartyRelationshipStatusType(PartyRelationshipStatusType partyRelationshipStatusType)
    {
        this.partyRelationshipStatusType = partyRelationshipStatusType;
    }

    public PriorityType getPriorityType()
    {
        return priorityType;
    }

    public void setPriorityType(PriorityType priorityType)
    {
        this.priorityType = priorityType;
    }

    public Party getPartyFrom()
    {
        return partyFrom;
    }

    public void setPartyFrom(Party partyFrom)
    {
        this.partyFrom = partyFrom;
    }

    public void setPartyTo(Party partyTo)
    {
        this.partyTo = partyTo;
    }

    public Party getPartyTo()
    {
        return partyTo;
    }

}
