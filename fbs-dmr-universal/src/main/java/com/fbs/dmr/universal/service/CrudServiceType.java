package com.fbs.dmr.universal.service;

import java.io.Serializable;

public interface CrudServiceType<T, ID extends Serializable> extends CrudService<T, ID>
{
	public T findForDescription(String description);
}
