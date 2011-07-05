package com.fbs.web.vaadin.ui.common;


public interface ListView<T, A> extends ListScreen<T>
{
    public void setAnchor(A anchor);
    public void updateComponents();
}
