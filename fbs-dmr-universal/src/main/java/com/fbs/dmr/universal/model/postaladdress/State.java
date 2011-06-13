package com.fbs.dmr.universal.model.postaladdress;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STATES")
public class State extends GeographicBoundary
{
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	public State()
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
