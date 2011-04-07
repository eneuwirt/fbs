package com.fbs.rest.domain;

import javax.xml.bind.annotation.XmlRootElement;


//JAX-RS supports an automatic mapping from JAXB annotated class to XML and
//JSON
//Isn't that cool?
@XmlRootElement
public class Person
{
	private String firstName;
	private String lastName;


	public String getFirstName()
	{
		return firstName;
	}


	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}


	public String getLastName()
	{
		return lastName;
	}


	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

}
