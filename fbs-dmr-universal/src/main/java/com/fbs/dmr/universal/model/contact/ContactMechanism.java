package com.fbs.dmr.universal.model.contact;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONTACTS")
@Inheritance(strategy=InheritanceType.JOINED)
public class ContactMechanism implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
    @ManyToOne
    @JoinColumn(name = "contact_type_id")
    private ContactMechanismType contactMechanismType;
    
    public ContactMechanism()
    {
    	
    }
    
    public String getAddress()
    {
    	return "";
    }
    
    public Integer getId()
    {
    	return id;
    }


	public void setId(Integer id)
    {
    	this.id = id;
    }


	public void setContactMechanismType(ContactMechanismType contactMechanismType)
    {
	    this.contactMechanismType = contactMechanismType;
    }

	public ContactMechanismType getContactMechanismType()
    {
	    return contactMechanismType;
    }

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof ContactMechanism))
		{
			return false;
		}

		ContactMechanism other = (ContactMechanism) obj;


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
		return String.format("ContactMechanism [id=%s]", this.id);
	}

}
