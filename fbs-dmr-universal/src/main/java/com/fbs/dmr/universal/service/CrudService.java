package com.fbs.dmr.universal.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
public interface CrudService<T, ID extends Serializable>
{
	public void save(T t);
	public T read(ID id);
	public void delete(ID id);
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<T> findAll();
}
