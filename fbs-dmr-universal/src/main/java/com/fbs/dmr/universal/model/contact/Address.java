package com.fbs.dmr.universal.model.contact;

import java.io.Serializable;

/**
 * Transient Address DTO
 * 
 * @author neuwirt
 * 
 */
public class Address implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String addr1 = "";
	private String addr2 = "";
	private String city = "";
	private String zip = "";
	private String country = "";
	private ContactMechanismType cmt;

	public Address(String addr1, String addr2, String zip, String city, String country, ContactMechanismType cmt)
	{
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.city = city;
		this.zip = zip;
		this.country = country;
		
		this.setCmt(cmt);
	}
	

	public String getAddr1()
	{
		return addr1;
	}

	public void setAddr1(String addr1)
	{
		this.addr1 = addr1;
	}

	public String getAddr2()
	{
		return addr2;
	}

	public void setAddr2(String addr2)
	{
		this.addr2 = addr2;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}


	public void setCmt(ContactMechanismType cmt)
    {
	    this.cmt = cmt;
    }


	public ContactMechanismType getCmt()
    {
	    return cmt;
    }
}
