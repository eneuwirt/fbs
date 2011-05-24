package com.fbs.dmr.universal.model.party;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "PARTIES")
@Inheritance(strategy=InheritanceType.JOINED)
public class Party implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@OneToMany(mappedBy="party", cascade=CascadeType.ALL)
	private List<PartyClassification> partyClassifications = new ArrayList<PartyClassification>();

	public Party()
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

	public void setPartyClassifications(List<PartyClassification> partyClassifications)
    {
	    this.partyClassifications = partyClassifications;
    }


	public List<PartyClassification> getPartyClassifications()
    {
	    return partyClassifications;
    }

	public String getName()
	{
		return (this.id == 0) ? "0" : this.id.toString();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof Party))
		{
			return false;
		}

		Party other = (Party) obj;

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
		return String.format("Party [id=%s]", this.id);
	}
}
