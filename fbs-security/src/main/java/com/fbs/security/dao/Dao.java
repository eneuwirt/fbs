package com.fbs.security.dao;

import java.io.Serializable;

public interface Dao <T extends Serializable, ID extends Serializable> extends Serializable
{
	public T create(T t);
	public T read(ID id);
	public T update(T t);
	public void delete(T t);
	
	//public Integer countAll();
}
