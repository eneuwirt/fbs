package com.fbs.security.service;

import java.io.Serializable;


public class AuthenticationSimple implements Authentication, Serializable
{
	private static final long serialVersionUID = 1L;

	private String userName;
	private Integer tenantId;
	private String tenantName;
	private UserRole userRole;
	
	public AuthenticationSimple(String userName, Integer tenantId, String tenantName, UserRole userRole)
	{
		this.userName = userName;
		this.tenantId = tenantId;
		this.tenantName = tenantName;
		this.userRole = userRole;
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

	@Override
    public UserRole getUserRole()
    {
	    return this.userRole;
    }
}
