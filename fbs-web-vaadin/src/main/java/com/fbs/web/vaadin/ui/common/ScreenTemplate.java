package com.fbs.web.vaadin.ui.common;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public abstract class ScreenTemplate extends VerticalLayout
{
	private static final long serialVersionUID = 1L;

	private Header header;
	protected AbstractComponentContainer mainScreen;
	private Footer footer;

	protected MyVaadinApplication app;


	public ScreenTemplate(MyVaadinApplication app)
	{
		super();

		this.app = app;
		this.header = new Header(app);
		this.footer = new Footer(app);

		this.initComponents();

		this.initLayout();
	}


	/**
	 * The inherited class shall instantiate the screen object in this method
	 */
	protected abstract void initMainScreen();


	private void initLayout()
	{
		this.setSizeFull();

		this.setMargin(true);
		this.setSpacing(true);

		//this.setExpandRatio(header, 1.0f);
		this.setExpandRatio(this.mainScreen, 1.0f);
		//this.setExpandRatio(this.footer, 1.0f);
	}


	private void initComponents()
	{
		this.initMainScreen();

		this.addComponent(this.header);
		this.addComponent(new Label("---------------------------------------"));
		this.addComponent(this.mainScreen);
		this.addComponent(new Label("---------------------------------------"));
		this.addComponent(this.footer);
	}
}
