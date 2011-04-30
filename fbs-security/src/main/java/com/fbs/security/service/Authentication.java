package com.fbs.security.service;

public interface Authentication
{
	public String getPrincipal();
	
	public Integer getTenantId();
	
	public String getTenantName();
}
