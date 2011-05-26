package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.user.party.PartyScreen;
import com.fbs.web.vaadin.ui.user.party.PartyTypeScreen;
import com.vaadin.ui.Accordion;

public class PartyAccordion extends Accordion
{
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;
	private PartyScreen partyScreen;
	private PartyTypeScreen partyTypeScreen;


	public PartyAccordion(MyVaadinApplication app)
	{
		super();
		this.app = app;

		partyScreen = new PartyScreen(this.app);
		partyTypeScreen = new PartyTypeScreen(this.app);

		this.addTab(partyScreen, this.app.getMessage(ApplicationMessages.PartyTitle), null);
		this.addTab(partyTypeScreen, this.app.getMessage(ApplicationMessages.PartyTypeTitle), null);
		
		this.initLayout();
	}
	
	private void initLayout()
	{
		this.setSizeFull();
	}
}
