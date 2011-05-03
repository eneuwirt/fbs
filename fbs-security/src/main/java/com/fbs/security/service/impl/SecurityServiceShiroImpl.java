package com.fbs.security.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.SimpleByteSource;

import com.fbs.security.service.Authentication;
import com.fbs.security.service.AuthenticationSimple;
import com.fbs.security.service.SecurityService;
import com.fbs.security.service.UserRole;

public class SecurityServiceShiroImpl implements SecurityService, Serializable
{
	private static final long serialVersionUID = 1L;
	protected String tenantQuery = "select tenant_id from users where username = ?";
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

		result = new AuthenticationSimple(userName, this.getTenantId(userName), null, this.getUserRole(userName));

		return result;
	}


	private UserRole getUserRole(String username)
	{
		UserRole result = UserRole.ROLE_USER;

		if (username.equals("admin"))
		{
			result = UserRole.ROLE_ADMIN;
		}
		

		return result;
	}


	private Integer getTenantId(String username)
	{
		Integer result = 0;

		try
		{
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			Connection conn = null;
			
			conn = this.dataSource.getConnection();

			statement = conn.prepareStatement(tenantQuery);
			statement.setString(1, username);

			resultSet = statement.executeQuery();

			boolean hasEntry = resultSet.next();
			
			if (!hasEntry)
			{
				throw new AuthenticationException("Could not retrieve tenant for [" + username + "].");
			}
			
			result = resultSet.getInt(1);

			if (resultSet.next())
			{
				throw new AuthenticationException("More than one user row found for user [" + username
				        + "]. Usernames must be unique.");
			}
		}
		catch (SQLException e)
		{
			final String message = "There was a SQL error while authenticating user [" + username + "]";

			throw new AuthenticationException(message, e);
		}

		return result;
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
