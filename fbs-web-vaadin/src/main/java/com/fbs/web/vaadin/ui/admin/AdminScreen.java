package com.fbs.web.vaadin.ui.admin;

import com.fbs.web.vaadin.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ScreenTemplate;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;

public class AdminScreen extends ScreenTemplate
{
	private static final long serialVersionUID = 1L;

	private TabSheet tabsheet;

	public AdminScreen(MyVaadinApplication app)
	{
		super(app);
	}


	@Override
	protected void initMainScreen()
	{
		TenantsScreen clientScreen;
		
		this.tabsheet = new TabSheet();
		this.mainScreen = tabsheet;
		
		tabsheet.setSizeFull();
		
		clientScreen = new TenantsScreen(this.app);
		
		tabsheet.addTab(clientScreen, this.app.getMessage(ApplicationMessages.AdminTabTitleTenant), null);
		tabsheet.addTab(new Label("Contents of the second tab"), this.app.getMessage(ApplicationMessages.AdminTabTitleUser), null);
		tabsheet.addTab(new Label("Contents of the third tab"), "Third tab", null);
	}

}
