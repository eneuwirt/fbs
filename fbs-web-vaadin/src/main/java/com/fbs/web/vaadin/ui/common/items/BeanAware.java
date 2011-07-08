package com.fbs.web.vaadin.ui.common.items;

public interface BeanAware<T>
{
    public void setBean(T t);
    public T getBean();
}
