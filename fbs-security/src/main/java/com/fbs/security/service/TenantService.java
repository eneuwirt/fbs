package com.fbs.security.service;

import java.util.List;

import com.fbs.security.model.Tenant;

public interface TenantService
{
	public Tenant create(Tenant tenant);


	public Tenant read(Integer id);


	public void update(Tenant tenant);


	public void delete(Tenant tenant);


	public List<Tenant> findAll();
}
