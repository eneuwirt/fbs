package com.fbs.web.vaadin.ui.common;

import java.util.List;

import com.vaadin.ui.FormFieldFactory;

public interface ListScreen<T>
{
	/**
	 * New Instance
	 * @return
	 */
	public T createBeanInstance();

	/**
	 * read the list from source
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<T> getAllBeans() throws Exception;

	/**
	 * Insert bean into data source
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public T createBean(T t) throws Exception;

	/**
	 * Update Datasource
	 * 
	 * @param t
	 * @throws Exception
	 */
	public void updateBean(T t) throws Exception;

	/**
	 * Read from data source
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public T readBean(T t) throws Exception;

	/**
	 * Delete from datasource
	 * @param t
	 * @throws Exception
	 */
	public void deleteBean(T t) throws Exception;

	/**
	 * get appropriate factory for bean form
	 * @return
	 */
	public FormFieldFactory getFormFieldFactory();

	/**
	 * Determine pretty column name for string
	 * 
	 * @param pid
	 * @return
	 */
	public String getColumnName(String pid);
}
