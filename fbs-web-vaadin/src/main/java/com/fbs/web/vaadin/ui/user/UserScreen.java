package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.MyVaadinApplication;
import com.fbs.web.vaadin.ui.common.ScreenTemplate;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

public class UserScreen extends ScreenTemplate
{
	private static final long serialVersionUID = 1L;

	private HorizontalLayout tmp;
	public UserScreen(MyVaadinApplication app)
	{
		super(app);		
	}
	
	@Override
    protected void initMainScreen()
    {
	    this.tmp = new HorizontalLayout();
	    
	    this.mainScreen = this.tmp;
	    
	    Button create = new Button("Create items", new CreateListener(app, this));
		Button show = new Button("Show items", new ShowListener(app, this));

		
		this.tmp.addComponent(show);
		this.tmp.addComponent(create);
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
