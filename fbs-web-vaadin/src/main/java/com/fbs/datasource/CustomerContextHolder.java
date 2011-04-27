package com.fbs.datasource;

public class CustomerContextHolder
{
	private static final ThreadLocal<CustomerType> contextHolder = new ThreadLocal<CustomerType>();


	public static void setCustomerType(CustomerType customerType)
	{
		contextHolder.set(customerType);
	}


	public static CustomerType getCustomerType()
	{
		CustomerType result = contextHolder.get();
		
		return result;
	}


	public static void clearCustomerType()
	{
		contextHolder.remove();
	}
}
