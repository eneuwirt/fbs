package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.vaadin.ui.Accordion;

public class ContactAccordion extends Accordion
{
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;

	public ContactAccordion(MyVaadinApplication app)
	{
		super();
		
		this.app = app;
		
		this.initLayout();
	}

	private void initLayout()
	{
		this.setSizeFull();
	}
}
