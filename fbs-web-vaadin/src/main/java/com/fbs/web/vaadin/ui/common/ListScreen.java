package com.fbs.web.vaadin.ui.common;

import java.util.List;

public interface ListScreen<T> extends ItemCrud<T>
{
	/**
	 * read the list from source
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<T> getAllBeans() throws Exception;


	/**
	 * Determine pretty column name for string
	 * 
	 * @param pid
	 * @return
	 */
	public String getColumnName(String pid);
}
