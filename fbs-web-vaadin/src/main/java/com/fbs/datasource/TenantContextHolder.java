package com.fbs.datasource;

/*
 * ClientId is an Integer value stored as String. 
 */
public class TenantContextHolder
{
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();


	public static void setTenant(Integer tenantId)
	{
		contextHolder.set(tenantId.toString());
	}


	public static String getTenant()
	{
		String result = contextHolder.get();
		
		return result;
	}


	public static void clearTenant()
	{
		contextHolder.remove();
	}
}
