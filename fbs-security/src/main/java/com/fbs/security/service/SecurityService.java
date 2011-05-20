package com.fbs.security.service;

public interface SecurityService
{
	/**
	 * Login the user
	 * 
	 * @param userName
	 * @param userPassword
	 * @return object represented the current user
	 * @throws Exception
	 */
	public Authentication login(String userName, String userPassword) throws Exception;
	
	/**
	 * Generates random password
	 * 
	 * @return generated password
	 */
	public String generatePassword();


	public void logout();


	/**
	 * Spring security uses Thread local so I have to initialize it in the case
	 * of thread start
	 * 
	 * @param object
	 */
	public void rollContextIn(Object object);


	/**
	 * Spring security uses Thread local so I have to deinitialize it in the
	 * case of thread end
	 */
	public void rollContextOut();
}
