package com.fbs.security.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.fbs.security.exception.AlreadyExists;
import com.fbs.security.model.User;
import com.fbs.security.service.UserService;
import com.fbs.security.util.JDBCFinalizer;

public class UserServiceImpl implements UserService
{
	private static final long serialVersionUID = 1L;
	private static final String CREATE_USER = "INSERT INTO users (userName, password, salt, tenant_id) VALUES (?, ?, ?, ?)";
	private static final String SELECT_USERS = "SELECT username, password, salt, tenant_id  FROM users";
	private static final String SELECT_USER = "SELECT username, password, salt, tenant_id  FROM users WHERE username = ?";
	private static final String UPDATE_USER = "UPDATE users SET password = ?, salt = ?, tenant_id = ? WHERE username = ?";
	private static final String DELETE_USER = "DELETE FROM users WHERE username = ?";
	private DataSource dataSource;


	@Override
	public User create(User user) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;

		if (user == null)
		{
			throw new IllegalArgumentException("user null");
		}
		
		if (this.read(user.getUserName()) != null)
		{
			throw new AlreadyExists();
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
		}
		finally
		{
			JDBCFinalizer.close(pstmt);
			JDBCFinalizer.close(conn);
		}

		return user;
	}


	@Override
	public User read(String id) throws Exception
	{
		User result = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;

		if (id == null)
		{
			throw new IllegalArgumentException("id null");
		}

		try
		{
			conn = dataSource.getConnection();
			
			pstmt = conn.prepareStatement(SELECT_USER);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();			
			
			if (rs.next())
			{
				String userName;
				String password;
				String salt;
				Integer tenantId;

				userName = rs.getString("username");
				password = rs.getString("password");
				salt = rs.getString("salt");
				tenantId = rs.getInt("tenant_id");

				result = new User();
				result.setPassword(password);
				result.setSalt(salt);
				result.setUserName(userName);
				result.setTenantId(tenantId);
			}
		}
		finally
		{
			JDBCFinalizer.close(rs);
			JDBCFinalizer.close(pstmt);
			JDBCFinalizer.close(conn);
		}

		return result;
	}


	@Override
	public void update(User user) throws Exception
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

			pstmt = conn.prepareStatement(UPDATE_USER);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getSalt());
			pstmt.setInt(3, user.getTenantId());
			pstmt.setString(4, user.getUserName());

			pstmt.executeUpdate();
		}
		finally
		{
			JDBCFinalizer.close(pstmt);
			JDBCFinalizer.close(conn);
		}

		return;
	}


	@Override
	public void delete(User user) throws Exception
	{
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		if (user == null)
		{
			throw new IllegalArgumentException("user id null");
		}
		
		try
		{
			conn = dataSource.getConnection();

			pstmt = conn.prepareStatement(DELETE_USER);
			pstmt.setString(1, user.getUserName());

			pstmt.executeUpdate();
		}
		finally
		{
			JDBCFinalizer.close(pstmt);
			JDBCFinalizer.close(conn);
		}
	}


	@Override
	public List<User> findAll() throws Exception
	{
		List<User> result = new ArrayList<User>();

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try
		{
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SELECT_USERS);

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
