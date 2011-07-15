package com.fbs.web.vaadin.ui.common;


public interface AnchorAware<T, A>
{
    public void setAnchor(A anchor);
    public void updateComponents();
}
