package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.MyVaadinApplication;
import com.fbs.web.vaadin.ui.auth.LogoutListener;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class UserView extends VerticalLayout
{
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;
	Label demo;


	public UserView(final MyVaadinApplication app)
	{
		super();
		this.app = app;

		this.demo = new Label("Du hast dich als User eingelogt");

		Button b = new Button("What is the time?", new TestListener(app, this));

		Button logout = new Button("logout");
		logout.addListener(new LogoutListener(app));

		
		this.addComponent(demo);
		this.addComponent(b);
		this.addComponent(logout);
	}

	private static class TestListener implements Button.ClickListener
	{
		private static final long serialVersionUID = 1L;
		MyVaadinApplication app;
		UserView userView;


		public TestListener(MyVaadinApplication app, UserView userView)
		{
			this.app = app;
			this.userView = userView;
		}


		@Override
		public void buttonClick(ClickEvent event)
		{
			app.doSomething();
		}

	}
}
