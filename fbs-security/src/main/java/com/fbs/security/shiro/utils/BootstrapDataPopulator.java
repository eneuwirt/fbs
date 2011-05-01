package com.fbs.security.shiro.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * A data populator that creates a set of security tables and test data that can
 * be used by the Shiro Spring sample application to demonstrate the use of the
 * {@link org.apache.shiro.realm.jdbc.JdbcRealm} The tables created by this
 * class follow the default table and column names that
 * {@link org.apache.shiro.realm.jdbc.JdbcRealm} uses.
 * 
 */
public class BootstrapDataPopulator implements InitializingBean
{

	private static final String CREATE_TABLES = 
		"create table users (\n" + 
		"    username varchar(255) primary key,\n" + 
		"    password varchar(255) not null,\n" + 
		"    salt varchar(1500) not null,\n" + 
		"    tenant integer not null\n" + 
		");\n" + 
		
		"\n" + "create table roles (\n" + "    role_name varchar(255) primary key\n" + ");\n" + "\n"
	        + "create table user_roles (\n" + "    username varchar(255) not null,\n"
	        + "    role_name varchar(255) not null,\n"
	        + "    constraint user_roles_uq unique ( username, role_name )\n" + ");\n" + "\n"
	        + "create table roles_permissions (\n" + "    role_name varchar(255) not null,\n"
	        + "    permission varchar(255) not null,\n" + "    primary key (role_name, permission)\n" + ");";

	private static final Logger log = Logger.getLogger(BootstrapDataPopulator.class.getName());

	protected DataSource dataSource = null;


	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}


	public void afterPropertiesSet() throws Exception
	{
		// because we're using an in-memory hsqldb for the sample app, a new one
		// will be created each time the
		// app starts, so create the tables and insert the 2 sample users on
		// bootstrap:

		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		jdbcTemplate.execute(CREATE_TABLES);

		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		String salt = rng.nextBytes().toBase64();

		// password is 'user1' SHA hashed and base64 encoded:
		// The first argument to the hash constructor is the actual value to be
		// hashed. The 2nd is the
		// salt. In this simple demo scenario, the username and the password are
		// the same, but to clarify the
		// distinction, you would see this in practice:
		// new Sha256Hash( <password>, <cryptographically strong randomly
		// generated salt> (not the username!) )
		String adminHash = new Sha256Hash("admin", salt, 1024).toBase64();
		String query = "insert into users values ('admin', '" + adminHash + "', '" + salt + "', '0' )";
		jdbcTemplate.execute(query);
		log.log(Level.INFO, "Created admin. ");

		// password is 'demo' SHA hashed, salted with String 'demo' and base64
		// encoded:
		String demoHash = new Sha256Hash("demo", salt, 1024).toBase64();
		query = "insert into users values ( 'demo', '" + demoHash + "', '" + salt + "', '1' )";
		jdbcTemplate.execute(query);
		log.log(Level.INFO, "Created demo.");

		// Admin role
		query = "insert into roles values ( 'ROLE_ADMIN' )";
		jdbcTemplate.execute(query);
		log.log(Level.INFO, "Created ROLE_ADMIN");

		query = "insert into roles values ( 'ROLE_USER' )";
		jdbcTemplate.execute(query);
		log.log(Level.INFO, "Created ROLE_USER");

		query = "insert into roles_permissions values ( 'ROLE_ADMIN', 'PERMISSIONS_ADMIN')";
		jdbcTemplate.execute(query);
		log.log(Level.INFO, "Created permission admin for ROLE_ADMIN");

		query = "insert into roles_permissions values ( 'ROLE_ADMIN', 'permission2')";
		jdbcTemplate.execute(query);
		log.log(Level.INFO, "Created permission 2 for ROLE_ADMIN");

		query = "insert into roles_permissions values ( 'ROLE_USER', 'PERMISSIONS_USER')";
		jdbcTemplate.execute(query);
		log.log(Level.INFO, "Created permission user for ROLE_USER");

		query = "insert into user_roles values ( 'admin', 'ROLE_ADMIN' )";
		jdbcTemplate.execute(query);

		query = "insert into user_roles values ( 'demo', 'ROLE_USER' )";
		jdbcTemplate.execute(query);
		log.log(Level.INFO, "Assigned demo roles ROLE_USER");
	}
}
