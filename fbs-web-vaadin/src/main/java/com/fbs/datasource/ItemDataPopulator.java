package com.fbs.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

public class ItemDataPopulator implements InitializingBean
{
	private static final String CREATE_TABLES = "create table item (\n" + "    name varchar(255) primary key,\n"
	        + "    price double not null\n" + ");";

	protected DataSource dataSource = null;
	protected String tenantId;


	public void setClient(String tenantId)
	{
		this.tenantId = tenantId;
	}


	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}


	@Override
	public void afterPropertiesSet() throws Exception
	{
		// because we're using an in-memory hsqldb for the sample app, a new one
		// will be created each time the
		// app starts, so create the tables and insert the 2 sample users on
		// bootstrap:

		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		jdbcTemplate.execute(CREATE_TABLES);

		String name = "Item for tenant " + this.tenantId;
		String query = "insert into item values ('" + name + "', '1' )";

		jdbcTemplate.execute(query);
	}

}
