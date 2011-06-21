package com.fbs.web.dto;

import java.io.Serializable;

import com.fbs.dmr.universal.model.contact.ContactMechanism;

/**
 * Mega object with all field from all contact Mechanism
 * 
 * @author neuwirt
 * 
 */
public class ContactMechanismDto implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ContactMechanism contactMechanism;
	// Electronic part
	private String electronicAddress = "";
	// Postal part
	private String address1 = "";
	private String address2 = "";
	private String directions = "";
	private String city = "";
	private String postalCode = "";
	private String country = "";
	// Phone part
	private String countryCode = "";
	private String areaCode = "";
	private String number = "";

	public ContactMechanismDto(ContactMechanism cm)
	{
		this.contactMechanism = cm;
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

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getElectronicAddress()
	{
		return electronicAddress;
	}

	public void setElectronicAddress(String electronicAddress)
	{
		this.electronicAddress = electronicAddress;
	}

	public String getAddress1()
	{
		return address1;
	}

	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}

	public String getAddress2()
	{
		return address2;
	}

	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}

	public String getDirections()
	{
		return directions;
	}

	public void setDirections(String directions)
	{
		this.directions = directions;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getPostalCode()
	{
		return postalCode;
	}

	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public void setContactMechanism(ContactMechanism contactMechanism)
	{
		this.contactMechanism = contactMechanism;
	}

	public ContactMechanism getContactMechanism()
	{
		return contactMechanism;
	}

}
