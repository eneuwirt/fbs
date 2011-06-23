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

import com.fbs.dmr.universal.model.contact.ContactMechanism;
import com.fbs.dmr.util.DateUtils;

@Entity
@Table(name = "PARTY_CONTACTS")
public class PartyContactMechanism implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "party_id")
	private Party party;
	@ManyToOne
	@JoinColumn(name = "contact_mechanism_id")
	private ContactMechanism contactMechanism;
	@Temporal(TemporalType.DATE)
	@Column(name = "date_from")
	private Date dateFrom = new Date();
	@Temporal(TemporalType.DATE)
	@Column(name = "date_to")
	private Date dateTo = DateUtils.getEndOfDays();
	private Boolean solicitation = false;
	private String extension = "";
	private String comment = "";

	public PartyContactMechanism()
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

	public Party getParty()
	{
		return party;
	}

	public void setParty(Party party)
	{
		this.party = party;
	}

	public void setContactMechanism(ContactMechanism contactMechanism)
    {
	    this.contactMechanism = contactMechanism;
    }

	public ContactMechanism getContactMechanism()
    {
	    return contactMechanism;
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

	public Boolean isSolicitation()
	{
		return solicitation;
	}

	public void setSolicitation(Boolean solicitation)
	{
		this.solicitation = solicitation;
	}

	public String getExtension()
	{
		return extension;
	}

	public void setExtension(String extension)
	{
		this.extension = extension;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}


	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof PartyContactMechanism))
		{
			return false;
		}

		PartyContactMechanism other = (PartyContactMechanism) obj;

		if (this.id == null && other.id == null)
		{
			String thisSeq = this.comment + this.extension;
			String otherSeq = other.comment + other.extension;
			boolean soli = this.solicitation == other.solicitation;

			return (soli && thisSeq.equals(otherSeq));
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
		int attrHash = this.comment.hashCode() + this.extension.hashCode() + this.solicitation.hashCode();

		result = prime * result + ((id == null) ? attrHash : id.hashCode());

		return result;
	}

	public String toString()
	{
		return String.format("PartyContactMechanism [id=%s]", this.id);
	}
}
