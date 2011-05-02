package com.fbs.datasource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private double price;

	public Item()
	{
		
	}

	public Item(String name, double price)
	{
		this.name = name;
		this.price = price;
	}


	public String getName()
	{
		return name;
	}


	public double getPrice()
	{
		return price;
	}


	public String toString()
	{
		return name + " (" + price + ")";
	}


	public void setId(Integer id)
    {
	    this.id = id;
    }


	public Integer getId()
    {
	    return id;
    }

}
