package com.fbs.web.vaadin.ui.admin;

import com.fbs.web.vaadin.MyVaadinApplication;
import com.fbs.web.vaadin.ui.auth.LogoutListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class AdminView extends VerticalLayout
{
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;


	public AdminView(MyVaadinApplication app)
	{
		this.app = app;

		// Create an empty tab sheet.
		TabSheet tabsheet = new TabSheet();
		
		Button logout = new Button("logout");
		logout.addListener(new LogoutListener(app));

		// Make the tabsheet shrink to fit the contents.
		tabsheet.setSizeUndefined();

		tabsheet.addTab(new Label("Contents of the first tab"), "First Tab", null);
		tabsheet.addTab(new Label("Contents of the second tab"), "Second Tab", null);
		tabsheet.addTab(new Label("Contents of the third tab"), "Third tab", null);

		this.addComponent(logout);
		this.addComponent(tabsheet);

	}
}
