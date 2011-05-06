package com.fbs.web.vaadin.ui.admin;

import com.fbs.web.vaadin.MyVaadinApplication;
import com.fbs.web.vaadin.ui.common.Header;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class AdminScreen extends VerticalLayout
{
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;


	public AdminScreen(MyVaadinApplication app)
	{
		this.app = app;
		
		this.initLayout();

		// Create an empty tab sheet.
		Header header = new Header(app);
		TabSheet tabsheet = new TabSheet();

		// Make the tabsheet shrink to fit the contents.
		

		tabsheet.addTab(new Label("Contents of the first tab"), "First Tab", null);
		tabsheet.addTab(new Label("Contents of the second tab"), "Second Tab", null);
		tabsheet.addTab(new Label("Contents of the third tab"), "Third tab", null);

		this.addComponent(header);
		this.addComponent(tabsheet);
	}
	
	private void initLayout()
	{
		this.setWidth("100%");
		this.setMargin(true);
		this.setSpacing(true);
	}
}
