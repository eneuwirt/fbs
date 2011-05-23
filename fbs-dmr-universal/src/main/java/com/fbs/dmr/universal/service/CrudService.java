package com.fbs.dmr.universal.service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
public interface CrudService<T, ID extends Serializable>
{
	public T create(T t);
	public void update(T t);
	public T read(ID id);
	public void delete(ID id);
}
