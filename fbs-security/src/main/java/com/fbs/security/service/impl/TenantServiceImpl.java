package com.fbs.security.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.fbs.security.model.Tenant;
import com.fbs.security.service.TenantService;

public class TenantServiceImpl implements TenantService, Serializable
{
	private static final long serialVersionUID = 1L;
	private DataSource dataSource;
	private static final String CREATE_TENANT = "INSERT INTO tenants (description) VALUES (?)";
	private static final String SELECT_TENANT = "SELECT description FROM tenants WHERE id = ?";
	private static final String SELECT_TENANTS = "SELECT id, description FROM tenants";
	private static final String UPDATE_TENANT = "UPDATE tenants SET description = ? WHERE id = ?";
	private static final String DELETE_TENANT = "DELETE FROM tenants WHERE id = ?";


	@Override
	public Tenant create(Tenant tenant)
	{
		PreparedStatement pstmt;
		Connection conn;

		try
		{
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(CREATE_TENANT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, tenant.getDescription());

			pstmt.executeUpdate();

			ResultSet keys = pstmt.getGeneratedKeys();

			keys.next();
			int key = keys.getInt(1);
			tenant.setId(key);

			keys.close();
			pstmt.close();

		}
		catch (Exception e)
		{
			return null;
		}

		return tenant;
	}


	@Override
	public Tenant read(Integer id)
	{
		Tenant result = null;

		try
		{
			PreparedStatement pstmt;
			Connection conn;
			ResultSet rs;
			String description;

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SELECT_TENANT);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			rs.next();

			description = rs.getString("description");

			result = new Tenant();
			result.setId(id);
			result.setDescription(description);

			pstmt.close();
		}
		catch (Exception e)
		{
			return null;
		}

		return result;
	}


	@Override
	public void update(Tenant tenant)
	{
		try
		{
			PreparedStatement pstmt;
			Connection conn;

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(UPDATE_TENANT);
			pstmt.setString(1, tenant.getDescription());
			pstmt.setInt(2, tenant.getId());

			pstmt.executeUpdate();

			pstmt.close();
		}
		catch (Exception e)
		{
			return;
		}

		return;
	}


	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}


	@Override
	public void delete(Integer id)
	{
		try
		{
			PreparedStatement pstmt;
			Connection conn;

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(DELETE_TENANT);
			pstmt.setInt(1, id);

			pstmt.executeUpdate();

			pstmt.close();
		}
		catch (Exception e)
		{
			return;
		}

		return;
	}


	@Override
	public List<Tenant> findAll()
	{
		List<Tenant> result = new ArrayList<Tenant>();
		try
		{
			PreparedStatement pstmt;
			Connection conn;
			ResultSet rs;

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SELECT_TENANTS);

			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Tenant tenant;
				Integer id;
				String description;
				
				id = rs.getInt("id");
				description = rs.getString("description");

				tenant = new Tenant();
				tenant.setId(id);
				tenant.setDescription(description);
				
				result.add(tenant);
			}

			pstmt.close();
		}
		catch (Exception e)
		{

		}

		return result;
	}
}
