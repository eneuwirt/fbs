package com.fbs.web.vaadin.ui.common;

import com.fbs.web.vaadin.MyVaadinApplication;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.VerticalLayout;

public abstract class ScreenTemplate extends VerticalLayout
{
	/**
     * 
     */
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

		this.initLayout();
		
		this.createMainScreen();
		
		this.addComponent(header);
		this.addComponent(mainScreen);
		this.addComponent(footer);
	}

	/**
	 * The inherited class shall create the screen in this method
	 */
	protected abstract void createMainScreen();


	protected void initLayout()
	{
		this.setWidth("100%");

		this.setMargin(true);
		this.setSpacing(true);
	}
}
