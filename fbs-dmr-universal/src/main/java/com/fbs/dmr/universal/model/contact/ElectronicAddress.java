package com.fbs.dmr.universal.model.contact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ELECTRONIC_ADDRESSES")
public class ElectronicAddress extends ContactMechanism
{
	private static final long serialVersionUID = 1L;
	@Column(name = "electronic_address")
	private String electronicAddress = "";

	public ElectronicAddress()
	{

	}

	@Override
	public String getAddress()
	{
		return this.electronicAddress;
	}

	public void setElectronicAddress(String electronicAddress)
	{
		this.electronicAddress = electronicAddress;
	}

	public String getElectronicAddress()
	{
		return electronicAddress;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof ElectronicAddress))
		{
			return false;
		}

		ElectronicAddress other = (ElectronicAddress) obj;

		if (this.getId() == null && other.getId() == null)
		{
			return this.electronicAddress.equals(other.electronicAddress);
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
		int descrHashCode = this.electronicAddress.hashCode();

		result = prime * result + ((this.getId() == null) ? descrHashCode : this.getId().hashCode());

		return result;
	}

	public String toString()
	{
		return String.format("ElectronicAddress [id=%s, elAddress=%s]", this.getId(), this.electronicAddress);
	}
}
