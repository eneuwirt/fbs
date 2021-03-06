package com.fbs.web.vaadin.ui.common.items;

import com.vaadin.ui.ComponentContainer;

public interface DetailsAware<T> extends ComponentContainer, BeanAware<T>
{    
    /**
     * Shall be implemented only, if bean has details
     * @param t
     */
    public void createBeanDetails(T t);
    public void readBeanDetails(T t);
    public void updateBeanDetails(T t);
    public void deleteBeanDetails(T t);
}
