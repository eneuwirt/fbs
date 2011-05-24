package com.fbs.web.vaadin.ui.auth;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class RegistrationScreen extends VerticalLayout
{
    private static final long serialVersionUID = 1L;
    private MyVaadinApplication app;


    public RegistrationScreen(MyVaadinApplication app)
    {
        this.app = app;

        // The application caption is shown in the caption bar of the
        // browser window. We set it here and not in the application
        // init() to make switching language easier.
        this.app.getMainWindow().setCaption(app.getMessage(ApplicationMessages.RegisterTitle));

        setSizeFull();

        // Put the editor inside a centered panel
        Panel registrationPanel = new Panel(this.app.getMessage(ApplicationMessages.RegisterNewUser));
        registrationPanel.setWidth("400px");
        
        addComponent(registrationPanel);
        setComponentAlignment(registrationPanel, Alignment.MIDDLE_CENTER);
    }
}
