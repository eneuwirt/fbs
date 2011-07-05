package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.user.contact.PartyContactMechanismScreen;
import com.vaadin.ui.Accordion;

public class ContactAccordion extends Accordion
{
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;
	private PartyContactMechanismScreen partyContactMechanismScreen;

	public ContactAccordion(MyVaadinApplication app)
	{
		super();

		this.app = app;

		this.partyContactMechanismScreen = new PartyContactMechanismScreen(this.app);

		this.addTab(this.partyContactMechanismScreen,
		        this.app.getMessage(ApplicationMessages.PartyContactMechanismTitle), null);
		

		this.initLayout();
	}

	private void initLayout()
	{
		this.setSizeFull();
	}
}
