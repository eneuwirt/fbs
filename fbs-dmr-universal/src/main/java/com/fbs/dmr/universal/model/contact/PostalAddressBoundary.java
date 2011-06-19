package com.fbs.dmr.universal.model.contact;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fbs.dmr.universal.model.geoboundary.GeographicBoundary;

/**
 * Relationship between Address and GeoBoundaries
 * 
 * @author neuwirt
 * 
 */
@Entity
@Table(name = "POSTAL_ADDRESS_BOUNDARIES")
public class PostalAddressBoundary implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "postall_address_id")
	private PostalAddress postallAddress;
	@ManyToOne
	@JoinColumn(name = "geo_boundary_id")
	private GeographicBoundary geoBoundary;

	public PostalAddressBoundary()
	{

	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public PostalAddress getPostallAddress()
	{
		return postallAddress;
	}

	public void setPostallAddress(PostalAddress postallAddress)
	{
		this.postallAddress = postallAddress;
	}

	public GeographicBoundary getGeoBoundary()
	{
		return geoBoundary;
	}

	public void setGeoBoundary(GeographicBoundary geoBoundary)
	{
		this.geoBoundary = geoBoundary;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof PostalAddressBoundary))
		{
			return false;
		}

		PostalAddressBoundary other = (PostalAddressBoundary) obj;

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

		result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());

		return result;
	}

	public String toString()
	{
		return String.format("PostalAddressBoundary [id=%s]", this.getId());
	}
}
