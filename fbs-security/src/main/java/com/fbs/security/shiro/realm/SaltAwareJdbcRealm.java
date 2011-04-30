package com.fbs.security.shiro.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.util.SimpleByteSource;

public class SaltAwareJdbcRealm extends JdbcRealm
{
	@Override
	protected AuthenticationInfo buildAuthenticationInfo(String username, char[] password)
	{
		SimpleByteSource salt;
		
		//use a creative salt 
		salt = new SimpleByteSource(username);
		
		return new SimpleAuthenticationInfo(username, password, salt, getName());
	}
}
