package com.fbs.web.vaadin.ui.common;

import com.fbs.web.vaadin.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.auth.LogoutListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class Header extends HorizontalLayout
{
    private static final long serialVersionUID = 1L;

	public Header(MyVaadinApplication app)
	{
		String greetMessage = app.getMessage(ApplicationMessages.HeaderWelcome) + " " + app.getUser().getPrincipal();
		Label  labelUser = new Label(greetMessage);
		
		Button logoutButton = new Button(app.getMessage(ApplicationMessages.LogoutButton));
		logoutButton.addListener(new LogoutListener(app));
		
		this.setWidth("100%");

		this.addComponent(labelUser);
		this.addComponent(logoutButton);
		
		setComponentAlignment(labelUser, Alignment.MIDDLE_LEFT);
		setComponentAlignment(logoutButton, Alignment.MIDDLE_RIGHT);
	}
}
