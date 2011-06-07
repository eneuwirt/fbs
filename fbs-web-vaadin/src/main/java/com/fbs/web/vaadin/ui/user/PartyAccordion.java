package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.user.party.OrganizationScreen;
import com.fbs.web.vaadin.ui.user.party.PartyRoleScreen;
import com.fbs.web.vaadin.ui.user.party.PartyRoleTypeScreen;
import com.fbs.web.vaadin.ui.user.party.PersonScreen;
import com.fbs.web.vaadin.ui.user.party.PartyTypeScreen;
import com.vaadin.ui.Accordion;

public class PartyAccordion extends Accordion
{
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;
	private OrganizationScreen orgScreen;
	private PersonScreen personScreen;
	private PartyTypeScreen partyTypeScreen;
	private PartyRoleTypeScreen partyRoleTypeScreen;
	private PartyRoleScreen partyRoleScreen;


	public PartyAccordion(MyVaadinApplication app)
	{
		super();
		this.app = app;

		this.orgScreen = new OrganizationScreen(this.app);
		this.personScreen = new PersonScreen(this.app);
		this.partyTypeScreen = new PartyTypeScreen(this.app);
		this.partyRoleTypeScreen = new PartyRoleTypeScreen(this.app);
		this.partyRoleScreen = new PartyRoleScreen(this.app);

		this.addTab(personScreen, this.app.getMessage(ApplicationMessages.PersonTitle), null);
		this.addTab(orgScreen, this.app.getMessage(ApplicationMessages.OrgTitle), null);
		this.addTab(partyRoleScreen, this.app.getMessage(ApplicationMessages.PartyRoleTitle), null);
		this.addTab(partyTypeScreen, this.app.getMessage(ApplicationMessages.PartyTypeTitle), null);
		this.addTab(partyRoleTypeScreen, this.app.getMessage(ApplicationMessages.PartyRoleTypeTitle), null);
		
		this.initLayout();
	}
	
	private void initLayout()
	{
		this.setSizeFull();
	}
}
