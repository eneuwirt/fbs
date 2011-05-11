package com.fbs.web.vaadin.ui.admin;

import com.fbs.web.vaadin.MyVaadinApplication;
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
		tabsheet = new TabSheet();
		this.mainScreen = tabsheet;
		
		tabsheet.setSizeFull();
		
		initClientTab();
		
		//tabsheet.addTab(new Label("Contents of the first tab"), "First Tab", null);
		tabsheet.addTab(new Label("Contents of the second tab"), "Second Tab", null);
		tabsheet.addTab(new Label("Contents of the third tab"), "Third tab", null);
	}
	
	private  void initClientTab()
	{
		ClientScreen clientScreen = new ClientScreen(this.app);
		
		tabsheet.addTab(clientScreen, "First Tab", null);
	}
	
}
