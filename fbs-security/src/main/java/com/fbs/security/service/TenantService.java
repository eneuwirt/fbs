package com.fbs.security.service;

import java.util.List;

import com.fbs.security.model.Tenant;

public interface TenantService
{
	public Tenant create(Tenant tenant) throws Exception;


	public Tenant read(Integer id)  throws Exception;


	public void update(Tenant tenant)  throws Exception;


	public void delete(Tenant tenant)  throws Exception;


	public List<Tenant> findAll()  throws Exception;
}
