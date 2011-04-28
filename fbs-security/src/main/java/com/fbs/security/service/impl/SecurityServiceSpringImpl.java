package com.fbs.security.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fbs.security.service.SecurityService;

public class SecurityServiceSpringImpl implements SecurityService
{
	private static Logger logger = Logger.getLogger(SecurityServiceSpringImpl.class.getName());
	private AuthenticationManager authenticationManager;


	@Override
	public Object login(String userName, String userPassword) throws Exception
	{
		Authentication input;
		Authentication result;
		
		logger.log(Level.INFO, "login");

		input = new UsernamePasswordAuthenticationToken(userName, userPassword);

		result = authenticationManager.authenticate(input);

		SecurityContextHolder.getContext().setAuthentication(result);

		return result;
	}


	@Override
	public void logout()
	{
		logger.log(Level.INFO, "logout");
		
		// Clear the authentication property to log the user out
		SecurityContextHolder.clearContext();
	}


	@Override
	public String getTenant(String userName) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rollContextIn(Object user)
	{
		logger.log(Level.INFO, "roll security context in");

		/*
		 * The security context holder uses the thread local pattern to store
		 * its authentication credentials. As requests may be handled by
		 * different threads, we have to update the security context holder in
		 * the beginning of each transaction.
		 */
		SecurityContextHolder.getContext().setAuthentication((Authentication) user);
	}


	@Override
	public void rollContextOut()
	{
		logger.log(Level.INFO, "roll security context out");
		
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
	
	public void setAuthenticationManager(AuthenticationManager authenticationManager)
	{
		this.authenticationManager = authenticationManager;
	}
}
