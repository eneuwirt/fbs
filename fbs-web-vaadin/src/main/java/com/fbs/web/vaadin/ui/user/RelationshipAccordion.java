package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.user.party.relship.PartyRelationshipScreen;
import com.vaadin.ui.Accordion;

public class RelationshipAccordion extends Accordion
{
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;
	private PartyRelationshipScreen relScreen;

	public RelationshipAccordion(MyVaadinApplication app)
	{
		super();
		
		this.app = app;
		this.relScreen = new PartyRelationshipScreen(app);
		
		this.addTab(this.relScreen, this.app.getMessage(ApplicationMessages.PartyRelationshipTitle), null);
		
		this.setSizeFull();
	}
}
