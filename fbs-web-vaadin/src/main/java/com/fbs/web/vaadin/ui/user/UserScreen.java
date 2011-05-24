package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ScreenTemplate;

import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;

public class UserScreen extends ScreenTemplate
{
	private static final long serialVersionUID = 1L;

	private TabSheet tabsheet;
	public UserScreen(MyVaadinApplication app)
	{
		super(app);		
	}
	
	@Override
    protected void initMainScreen()
    {
		PartyScreen partyScreen;
		
		this.tabsheet = new TabSheet();
		this.mainScreen = tabsheet;
		
		tabsheet.setSizeFull();
		
		partyScreen = new PartyScreen(this.app);
		
		tabsheet.addTab(partyScreen, this.app.getMessage(ApplicationMessages.UserTabTitleParty), null);
		tabsheet.addTab(new Label("Contents of the third tab"), "Second tab", null);
		tabsheet.addTab(new Label("Contents of the third tab"), "Third tab", null);
    }
}
