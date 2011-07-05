package com.fbs.web.vaadin.ui.common;

public interface ItemCrud<T>
{
    /**
     * New Instance
     * @return
     */
    public T createBeanInstance();

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
}
