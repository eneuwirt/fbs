package com.fbs.security.service;

public interface SecurityService
{
	/**
	 * 
	 * @param userName
	 * @param userPassword
	 * @return object represented the current user
	 * @throws Exception
	 */
	public Object login(String userName, String userPassword) throws Exception;


	public void logout();


	public String getTenant(String userName) throws Exception;
	
	/**
	 * Spring security uses Thread local so I have to initialize it in the case of thread start
	 * @param object
	 */
	public void rollContextIn(Object object);
	
	/**
	 * End of thread
	 */
	public void rollContextOut();
}
