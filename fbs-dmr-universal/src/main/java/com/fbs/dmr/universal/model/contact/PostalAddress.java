package com.fbs.dmr.universal.model.contact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "POSTAL_ADDRESSES")
public class PostalAddress extends ContactMechanism
{
	private static final long serialVersionUID = 1L;
	@Column(name = "address_1")
	private String address1 = "";
	@Column(name = "address_2")
	private String address2 = "";
	private String directions = "";
	private String city = "";
	@Column(name = "postal_code")
	private String postalCode = "";
	private String country = "";

	public String getAddress()
	{
		return String.format("%s %s %s", this.address1, this.postalCode, this.city);
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

	public PostalAddress()
	{

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

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof PostalAddress))
		{
			return false;
		}

		PostalAddress other = (PostalAddress) obj;

		if (this.getId() == null && other.getId() == null)
		{
			return this.address1.equals(other.address1);
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
		int result = 1;
		int attrHashCode = this.address1.hashCode() + this.address2.hashCode() + this.directions.hashCode();

		result = prime * result + ((this.getId() == null) ? attrHashCode : this.getId().hashCode());

		return result;
	}

	public String toString()
	{
		return String.format("PostalAddress [id=%s, address1=%s]", this.getId(), this.address1);
	}
}
