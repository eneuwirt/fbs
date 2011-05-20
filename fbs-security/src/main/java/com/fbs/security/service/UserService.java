package com.fbs.security.service;

import java.util.List;

import com.fbs.security.model.User;

public interface UserService
{
	public User create(User tenant) throws Exception;


	public User read(String id) throws Exception;


	public void update(User tenant)  throws Exception;


	public void delete(User user)  throws Exception;


	public List<User> findAll()  throws Exception;
}
