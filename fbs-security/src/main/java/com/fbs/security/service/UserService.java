package com.fbs.security.service;

import java.util.List;

import com.fbs.security.model.User;

public interface UserService
{
	public User create(User tenant);


	public User read(String id);


	public void update(User tenant);


	public void delete(String id);


	public List<User> findAll();
}
