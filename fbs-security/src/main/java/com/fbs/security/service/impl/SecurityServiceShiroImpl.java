package com.fbs.security.service.impl;

import java.io.Serializable;

import javax.sql.DataSource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.fbs.security.service.Authentication;
import com.fbs.security.service.AuthenticationSimple;
import com.fbs.security.service.SecurityService;

public class SecurityServiceShiroImpl implements SecurityService, Serializable
{
	private static final long serialVersionUID = 1L;
	protected DataSource dataSource = null;

	@Override
	public Authentication login(String userName, String password) throws Exception
	{
		Authentication result;

		Subject subject = SecurityUtils.getSubject();

		// Authenticate the subject by passing the user name and password token
		// into the login method
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

		// ”Remember Me” built-in, just do this:
		token.setRememberMe(true);

		subject.login(token);

		// security reason, clear token if someone uses memory-dumps
		token.clear();

		result = new AuthenticationSimple(userName, this.getTenantId(userName), null, null);

		return result;
	}


	private Integer getTenantId(String username)
	{
		Integer tenantId = 0;

		if (username.equals("demo"))
		{
			tenantId = 1;
		}
		else if (username.equals("view"))
		{
			tenantId = 2;
		}

		return tenantId;
	}


	@Override
	public void logout()
	{
		Subject subject = SecurityUtils.getSubject();

		if (subject.isAuthenticated())
		{
			subject.logout();
		}
	}


	@Override
	public void rollContextIn(Object object)
	{
		return;
	}


	@Override
	public void rollContextOut()
	{
		return;
	}

	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
}
