package com.fbs.dmr.universal.service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface CrudServiceType<T, ID extends Serializable> extends CrudService<T, ID>
{
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public T findForDescription(String description);
}
