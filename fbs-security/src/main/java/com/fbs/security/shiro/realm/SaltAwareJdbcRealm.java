package com.fbs.security.shiro.realm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.util.SimpleByteSource;

public class SaltAwareJdbcRealm extends JdbcRealm
{
	protected String saltQuery = "select salt from users where username = ?";


	public SaltAwareJdbcRealm()
	{
		super();
	}


	@Override
	protected AuthenticationInfo buildAuthenticationInfo(String username, char[] password)
	{
		SimpleByteSource salt;

		// use a creative salt
		salt = this.getPasswordSaltForUser(username);

		return new SimpleAuthenticationInfo(username, password, salt, getName());
	}


	private SimpleByteSource getPasswordSaltForUser(String username)
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection conn = null;

		try
		{
			conn = this.dataSource.getConnection();

			statement = conn.prepareStatement(saltQuery);
			statement.setString(1, username);

			resultSet = statement.executeQuery();

			boolean hasEntry = resultSet.next();
			
			if (!hasEntry)
			{
				throw new AuthenticationException("Could not retrieve salt for [" + username + "].");
			}
			
			String salt = resultSet.getString(1);

			if (resultSet.next())
			{
				throw new AuthenticationException("More than one user row found for user [" + username
				        + "]. Usernames must be unique.");
			}

			return new SimpleByteSource(salt);

		}
		catch (SQLException e)
		{
			final String message = "There was a SQL error while authenticating user [" + username + "]";

			throw new AuthenticationException(message, e);
		}
	}
}
