package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.MyVaadinApplication;
import com.fbs.web.vaadin.ui.common.Header;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class UserScreen extends VerticalLayout
{
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;


	public UserScreen(final MyVaadinApplication app)
	{
		super();
		this.app = app;

		Header header = new Header(app);


		Button create = new Button("Create items", new CreateListener(app, this));
		Button show = new Button("Show items", new ShowListener(app, this));

		
		this.addComponent(header);
		this.addComponent(show);
		this.addComponent(create);
		
	}

	private static class CreateListener implements Button.ClickListener
	{
		private static final long serialVersionUID = 1L;
		MyVaadinApplication app;
		UserScreen userScreen;


		public CreateListener(MyVaadinApplication app, UserScreen userScreen)
		{
			this.app = app;
			this.userScreen = userScreen;
		}


		@Override
		public void buttonClick(ClickEvent event)
		{
			app.doCreate();
		}

	}
	
	private static class ShowListener implements Button.ClickListener
	{
		private static final long serialVersionUID = 1L;
		MyVaadinApplication app;
		UserScreen userScreen;


		public ShowListener(MyVaadinApplication app, UserScreen userScreen)
		{
			this.app = app;
			this.userScreen = userScreen;
		}


		@Override
		public void buttonClick(ClickEvent event)
		{
			app.doShow();
		}

	}
}
