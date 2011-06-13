package com.fbs.dmr.universal.model.postaladdress;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRIES")
public class Country extends GeographicBoundary
{
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy = "country")
	private List<PostalCode> postalCodes = new ArrayList<PostalCode>();
	@OneToMany(mappedBy = "country")
	private List<Province> provinces = new ArrayList<Province>();
	@OneToMany(mappedBy = "country")
	private List<State> states = new ArrayList<State>();

	public Country()
	{

	}

	public void setPostalCodes(List<PostalCode> postalCodes)
	{
		this.postalCodes = postalCodes;
	}

	public List<PostalCode> getPostalCodes()
	{
		return postalCodes;
	}

	public void setProvinces(List<Province> provinces)
    {
	    this.provinces = provinces;
    }

	public List<Province> getProvinces()
    {
	    return provinces;
    }

	public void setStates(List<State> states)
    {
	    this.states = states;
    }

	public List<State> getStates()
    {
	    return states;
    }
}
