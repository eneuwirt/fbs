package com.fbs.web.vaadin.ui.common;

import com.fbs.web.vaadin.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class Footer extends HorizontalLayout
{
    private static final long serialVersionUID = 1L;

	public Footer(MyVaadinApplication app)
	{
		String greetMessage = app.getMessage(ApplicationMessages.FooterCopyRight);
		Label  labelUser = new Label(greetMessage);
			
		
		this.setWidth("100%");
		this.addComponent(labelUser);
		
		setComponentAlignment(labelUser, Alignment.MIDDLE_CENTER);
	}
}

