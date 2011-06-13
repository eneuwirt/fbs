package com.fbs.dmr.universal.model.postaladdress;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRIES")
public class Country extends GeographicBoundary
{
	private static final long serialVersionUID = 1L;

	public Country()
	{

	}
}
