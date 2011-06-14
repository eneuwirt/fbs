package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.user.party.PartyRelationshipTypeScreen;
import com.fbs.web.vaadin.ui.user.party.PartyRoleTypeScreen;
import com.fbs.web.vaadin.ui.user.party.PartyTypeScreen;
import com.vaadin.ui.Accordion;

public class TypesAccordion extends Accordion
{
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;
	private PartyTypeScreen partyTypeScreen;
	private PartyRoleTypeScreen partyRoleTypeScreen;
	private PartyRelationshipTypeScreen relTypeScreen;
	
	public TypesAccordion(MyVaadinApplication app)
	{
		super();
		this.app = app;
		
		this.partyTypeScreen = new PartyTypeScreen(this.app);
		this.partyRoleTypeScreen = new PartyRoleTypeScreen(this.app);
		this.relTypeScreen = new PartyRelationshipTypeScreen(app);
		
		this.addTab(partyTypeScreen, this.app.getMessage(ApplicationMessages.PartyTypeTitle), null);
		this.addTab(partyRoleTypeScreen, this.app.getMessage(ApplicationMessages.PartyRoleTypeTitle), null);
		this.addTab(this.relTypeScreen, this.app.getMessage(ApplicationMessages.PartyRelationshipTypeTitle), null);
		
		this.initLayout();
	}

	private void initLayout()
	{
		this.setSizeFull();
	}
}
