package com.fbs.dmr.universal.model.postaladdress;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "POSTAL_ADDRESS_BOUNDARIES")
public class PostalAddressBoundary  implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
    @ManyToOne
    @JoinColumn(name = "postal_address_id")
    private PostalAddress postalAddress;
    @ManyToOne
    @JoinColumn(name = "geo_boundary_id")
    private GeographicBoundary geoBoundary ;

	public PostalAddressBoundary()
    {
    	
    }

    public Integer getId()
    {
    	return id;
    }

	public void setId(Integer id)
    {
    	this.id = id;
    }

	public PostalAddress getPostalAddress()
    {
    	return postalAddress;
    }

	public void setPostalAddress(PostalAddress postalAddress)
    {
    	this.postalAddress = postalAddress;
    }
    
    public void setGeoBoundary(GeographicBoundary geoBoundary)
    {
	    this.geoBoundary = geoBoundary;
    }

	public GeographicBoundary getGeoBoundary()
    {
	    return geoBoundary;
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

		if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id))
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

		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}


	public String toString()
	{
		return String.format("PostalAddressBoundary [id=%s]", this.id);
	}
}
