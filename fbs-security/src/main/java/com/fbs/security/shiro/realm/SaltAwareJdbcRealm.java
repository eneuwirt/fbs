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

import com.fbs.security.util.JDBCFinalizer;

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
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			conn = this.dataSource.getConnection();

			pstmt = conn.prepareStatement(saltQuery);
			pstmt.setString(1, username);

			rs = pstmt.executeQuery();

			boolean hasEntry = rs.next();

			if (!hasEntry)
			{
				throw new AuthenticationException("Could not retrieve salt for [" + username + "].");
			}

			String salt = rs.getString(1);

			if (rs.next())
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
		finally
		{
			JDBCFinalizer.close(rs);
			JDBCFinalizer.close(pstmt);
			JDBCFinalizer.close(conn);
		}
	}
}
