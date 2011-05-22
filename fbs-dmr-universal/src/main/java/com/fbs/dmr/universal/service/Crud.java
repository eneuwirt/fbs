package com.fbs.dmr.universal.service;

import java.io.Serializable;

public interface Crud<T, ID extends Serializable>
{
	public T create(T t);
	public void update(T t);
	public T read(ID id);
	public void delete(ID id);
}
