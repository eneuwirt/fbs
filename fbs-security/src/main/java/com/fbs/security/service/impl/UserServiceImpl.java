package com.fbs.security.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.fbs.security.model.Tenant;
import com.fbs.security.model.User;
import com.fbs.security.service.UserService;
import com.fbs.security.util.JDBCFinalizer;

public class UserServiceImpl implements UserService
{
	private static final long serialVersionUID = 1L;
	private static final String CREATE_USER = "INSERT INTO users (userName, password, salt, tenant_id) VALUES (?, ?, ?, ?)";
	private static final String SELECT_TENANTS = "SELECT username, password, salt, tenant_id  FROM users";
	private DataSource dataSource;


	@Override
	public User create(User user)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;

		if (user == null)
		{
			throw new IllegalArgumentException("user null");
		}

		try
		{
			conn = dataSource.getConnection();

			pstmt = conn.prepareStatement(CREATE_USER);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getSalt());
			pstmt.setInt(4, user.getTenantId());

			pstmt.executeUpdate();

			pstmt.close();

		}
		catch (SQLException e)
		{
			// ignore
		}
		finally
		{
			JDBCFinalizer.close(pstmt);
			JDBCFinalizer.close(conn);
		}

		return user;
	}


	@Override
	public User read(String id)
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void update(User tenant)
	{
		// TODO Auto-generated method stub

	}


	@Override
	public void delete(String id)
	{
		// TODO Auto-generated method stub

	}


	@Override
	public List<User> findAll()
	{
		List<User> result = new ArrayList<User>();

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try
		{
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SELECT_TENANTS);

			rs = pstmt.executeQuery();
			while (rs.next())
			{
				User user;
				String userName;
				String password;
				String salt;
				Integer tenantId;

				userName = rs.getString("username");
				password = rs.getString("password");
				salt = rs.getString("salt");
				tenantId = rs.getInt("tenant_id");

				user = new User();
				user.setPassword(password);
				user.setSalt(salt);
				user.setUserName(userName);
				user.setTenantId(tenantId);

				result.add(user);
			}
		}
		catch (SQLException e)
		{
			// ignore
		}
		finally
		{
			JDBCFinalizer.close(rs);
			JDBCFinalizer.close(pstmt);
			JDBCFinalizer.close(conn);
		}

		return result;
	}


	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
}
