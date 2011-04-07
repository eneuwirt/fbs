package com.fbs.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fbs.rest.domain.Person;

@Path("/person")
public class PersonResource
{
	// This method is called if XMLis request
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Person getXML()
	{
		Person todo = new Person();
		todo.setFirstName("Eduard");
		todo.setLastName("Neuwirt");
		
		return todo;
	}


	// This can be used to test the integration with the browser
	@GET
	@Produces({ MediaType.TEXT_XML})
	public Person getHTML()
	{
		Person todo = new Person();
		todo.setFirstName("Eduard");
		todo.setLastName("Neuwirt");
		return todo;
	}
}
