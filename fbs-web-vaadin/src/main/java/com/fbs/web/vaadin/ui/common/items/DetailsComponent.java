package com.fbs.web.vaadin.ui.common.items;

import com.vaadin.ui.ComponentContainer;

public interface DetailsComponent<T> extends ComponentContainer
{
    public void setBean(T t);
    public T getBean();
    
    /**
     * Shall be implemented only, if bean has details
     * @param t
     */
    public void createBeanDetails(T t);
    public void readBeanDetails(T t);
    public void updateBeanDetails(T t);
    public void deleteBeanDetails(T t);
}
