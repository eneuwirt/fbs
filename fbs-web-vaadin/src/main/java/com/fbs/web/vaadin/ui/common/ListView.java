package com.fbs.web.vaadin.ui.common;

import java.util.List;

import com.vaadin.ui.FormFieldFactory;

public interface ListView<T, A>
{
    public T createBeanInstance();

    public List<T> getAllBeans() throws Exception;

    public T createBean(T t) throws Exception;

    public void updateBean(T t) throws Exception;

    public T readBean(T t) throws Exception;

    public void deleteBean(T t) throws Exception;
    
    public FormFieldFactory getFormFieldFactory();
    
    public String getColumnName(String pid);
    
    public void setAnchor(A anchor);
    public void updateComponents();
}
