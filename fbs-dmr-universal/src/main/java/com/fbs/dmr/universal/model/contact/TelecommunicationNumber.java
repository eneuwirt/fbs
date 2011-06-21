package com.fbs.dmr.universal.model.contact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TELECOM_NUMBERS")
public class TelecommunicationNumber extends ContactMechanism
{
    private static final long serialVersionUID = 1L;
    @Column(name="country_code")
    private String countryCode = "";
    @Column(name="area_code")
    private String areaCode = "";
    private String number = "";
    
    public TelecommunicationNumber()
    {
    	
    }
    
    @Override
    public String getAddress()
    {
    	return String.format("%s %s %s", this.countryCode, this.areaCode, this.number);
    }

    public String getCountryCode()
    {
    	return countryCode;
    }

	public void setCountryCode(String countryCode)
    {
    	this.countryCode = countryCode;
    }

	public String getAreaCode()
    {
    	return areaCode;
    }

	public void setAreaCode(String areaCode)
    {
    	this.areaCode = areaCode;
    }

	public String getNumber()
    {
    	return number;
    }

	public void setNumber(String contactNumber)
    {
    	this.number = contactNumber;
    }
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof TelecommunicationNumber))
		{
			return false;
		}

		TelecommunicationNumber other = (TelecommunicationNumber) obj;
		
		if (this.getId() == null && other.getId() == null)
		{
			String thisSeq = this.areaCode + this.countryCode + this.number;
			String otherSeq = other.areaCode + other.countryCode + other.number;
			
			return thisSeq.equals(otherSeq);
		}


		if ((this.getId() == null) ? (other.getId() != null) : !this.getId().equals(other.getId()))
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		String attrHash = this.areaCode + this.countryCode + this.number;
		int result = 1;

		result = prime * result + ((this.getId() == null) ? attrHash.hashCode() : this.getId().hashCode());

		return result;
	}

	public String toString()
	{
		return String.format("TelecommunicationNumber [id=%s]", this.getId());
	}
}
