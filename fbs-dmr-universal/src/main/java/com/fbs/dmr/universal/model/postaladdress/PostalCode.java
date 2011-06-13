package com.fbs.dmr.universal.model.postaladdress;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POSTAL_CODES")
public class PostalCode extends GeographicBoundary
{
    private static final long serialVersionUID = 1L;
    @JoinColumn(name = "country_id")
    @ManyToOne(optional=false)
    private Country country;

    public PostalCode()
    {
    	
    }

	public void setCountry(Country country)
    {
	    this.country = country;
    }

	public Country getCountry()
    {
	    return country;
    }
}
