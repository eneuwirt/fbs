package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.user.contact.ContactMechanismPurposeTypeScreen;
import com.fbs.web.vaadin.ui.user.contact.ContactMechanismTypeScreen;
import com.fbs.web.vaadin.ui.user.party.PartyRoleTypeScreen;
import com.fbs.web.vaadin.ui.user.party.PartyTypeScreen;
import com.fbs.web.vaadin.ui.user.party.relship.PartyRelationshipTypeScreen;
import com.fbs.web.vaadin.ui.user.workeffort.WorkEffortTypeScreen;
import com.vaadin.ui.Accordion;

public class TypesAccordion extends Accordion
{
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;
	private ContactMechanismPurposeTypeScreen contactMechanismPurposeTypeScreen;
	private ContactMechanismTypeScreen contactMechanismTypeScreen;
	private PartyTypeScreen partyTypeScreen;
	private PartyRoleTypeScreen partyRoleTypeScreen;
	private PartyRelationshipTypeScreen relTypeScreen;
	private WorkEffortTypeScreen workEffortTypeScreen;

	public TypesAccordion(MyVaadinApplication app)
	{
		super();
		this.app = app;

		this.contactMechanismPurposeTypeScreen = new ContactMechanismPurposeTypeScreen(this.app);
		this.contactMechanismTypeScreen = new ContactMechanismTypeScreen(this.app);
		this.partyTypeScreen = new PartyTypeScreen(this.app);
		this.partyRoleTypeScreen = new PartyRoleTypeScreen(this.app);
		this.relTypeScreen = new PartyRelationshipTypeScreen(this.app);
		this.workEffortTypeScreen = new WorkEffortTypeScreen(this.app);

		this.addTab(partyTypeScreen, this.app.getMessage(ApplicationMessages.PartyTypeTitle), null);
		this.addTab(partyRoleTypeScreen, this.app.getMessage(ApplicationMessages.PartyRoleTypeTitle), null);
		this.addTab(this.relTypeScreen, this.app.getMessage(ApplicationMessages.PartyRelationshipTypeTitle), null);
		this.addTab(this.contactMechanismPurposeTypeScreen,
		        this.app.getMessage(ApplicationMessages.ContactMechanismPurposeTypeTitle), null);
		this.addTab(this.contactMechanismTypeScreen,
		        this.app.getMessage(ApplicationMessages.ContactMechanismTypeTitle), null);
		this.addTab(this.workEffortTypeScreen,
                this.app.getMessage(ApplicationMessages.WorkEffortTypeTitle), null);

		this.initLayout();
	}

	private void initLayout()
	{
		this.setSizeFull();
	}
}
