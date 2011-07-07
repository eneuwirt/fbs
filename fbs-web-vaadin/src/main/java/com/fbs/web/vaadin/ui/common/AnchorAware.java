package com.fbs.web.vaadin.ui.common;


public interface AnchorAware<T, A> extends ListAware<T>
{
    public void setAnchor(A anchor);
    public void updateComponents();
}
