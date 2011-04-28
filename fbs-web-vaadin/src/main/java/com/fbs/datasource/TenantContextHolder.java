package com.fbs.datasource;

/*
 * ClientId is Integers represented by string. 
 */
public class TenantContextHolder
{
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();


	public static void setTenant(String tenantId)
	{
		contextHolder.set(tenantId);
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
