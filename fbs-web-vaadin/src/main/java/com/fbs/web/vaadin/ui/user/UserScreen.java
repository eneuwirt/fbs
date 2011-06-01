package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ScreenTemplate;

import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;

public class UserScreen extends ScreenTemplate
{
	private static final long serialVersionUID = 1L;

	private TabSheet tabsheet = new TabSheet();

	private PartyAccordion partyAccordion;
	private RelationshipAccordion relAccordion;
	private DocumentsAccordion docAccordion;


	public UserScreen(MyVaadinApplication app)
	{
		super(app);
	}


	@Override
	protected void initMainScreen()
	{
		tabsheet = new TabSheet();
		this.mainScreen = tabsheet;
		
		this.partyAccordion = new PartyAccordion(this.app);
		this.relAccordion = new RelationshipAccordion(this.app);
		this.docAccordion = new  DocumentsAccordion(this.app);

		tabsheet.setSizeFull();

		tabsheet.addTab(partyAccordion, this.app.getMessage(ApplicationMessages.UserTabTitleParty), null);
		tabsheet.addTab(relAccordion, this.app.getMessage(ApplicationMessages.UserTabTitleRelationships), null);
		tabsheet.addTab(docAccordion, this.app.getMessage(ApplicationMessages.UserTabTitleDocuments), null);
	}
}
