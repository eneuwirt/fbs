package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.vaadin.ui.Accordion;

public class RelationshipAccordion extends Accordion
{
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;

	public RelationshipAccordion(MyVaadinApplication app)
	{
		this.app = app;
	}
}
