package com.fbs.datasource;

/*
 * ClientId is Integers represented by string. 
 */
public class ClientContextHolder
{
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();


	public static void setCustomerType(String clientId)
	{
		contextHolder.set(clientId);
	}


	public static String getCustomerType()
	{
		String result = contextHolder.get();
		
		return result;
	}


	public static void clearCustomerType()
	{
		contextHolder.remove();
	}
}
