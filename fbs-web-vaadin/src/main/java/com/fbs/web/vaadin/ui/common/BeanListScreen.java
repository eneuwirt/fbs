package com.fbs.web.vaadin.ui.common;

import com.fbs.web.vaadin.application.ApplicationServices;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.vaadin.ui.HorizontalSplitPanel;

/**
 * Used as template for horizontal split screens. left is a list view with
 * filter, right is a details view for the selected list element.
 * 
 * Assumptions: only a subset of the entire list is shown. Filter defines the
 * maximal number of fetched entries and additional filtering criteria.
 * 
 * @author neuwirt
 * 
 */
public class BeanListScreen<T> extends HorizontalSplitPanel
{
	private static final long serialVersionUID = 1L;

	protected Class<T> clazz;
	protected MyVaadinApplication app;
	protected ApplicationServices services;
	// state list
	protected int maxDisplay = 10;
	

	public BeanListScreen(Class<T> clazz, MyVaadinApplication app)
	{
		super();
		this.clazz = clazz;
		this.app = app;
		this.services = this.app.getServices();
		
		this.initLayout();
	}


	private void initLayout()
    {
	    this.setSizeFull();
    }
}
