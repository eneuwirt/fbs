package com.fbs.security.service;

import java.io.Serializable;


public class AuthenticationSimple implements Authentication, Serializable
{
	private static final long serialVersionUID = 1L;

	private String userName;
	private Integer tenantId;
	private String tenantName;
	
	public AuthenticationSimple(String userName, Integer tenantId, String tenantName)
	{
		this.userName = userName;
		this.tenantId = tenantId;
		this.tenantName = tenantName;
	}

	@Override
	public String getPrincipal()
	{
		return userName;
	}


	@Override
	public Integer getTenantId()
	{
		return tenantId;
	}


	@Override
	public String getTenantName()
	{
		return tenantName;
	}

}
