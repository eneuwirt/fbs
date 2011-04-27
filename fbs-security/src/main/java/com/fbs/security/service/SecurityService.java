package com.fbs.security.service;

public interface SecurityService
{
	public void login(String userName, String userPassword) throws Exception;


	public void logout();


	public String getTenant(String userName) throws Exception;
}
