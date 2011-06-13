package com.fbs.dmr.universal.model.postaladdress;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "STATES")
public class State extends GeographicBoundary
{
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
	@OneToMany(mappedBy="state")
	private List<City> cities = new ArrayList<City>();
	@OneToMany(mappedBy="state")
	private List<County> counties = new ArrayList<County>();

	public State()
	{

	}

	public List<City> getCities()
	{
		return cities;
	}

	public void setCities(List<City> cities)
	{
		this.cities = cities;
	}

	public List<County> getCounties()
	{
		return counties;
	}

	public void setCounties(List<County> counties)
	{
		this.counties = counties;
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
