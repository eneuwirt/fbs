package com.fbs.web.vaadin.ui.auth;

import com.fbs.web.vaadin.MyVaadinApplication;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

public class LogoutListener implements Button.ClickListener
{
    private static final long serialVersionUID = 1L;
    private MyVaadinApplication app;
    
    public LogoutListener(MyVaadinApplication app)
    {
        this.app = app;
    }

    @Override
    public void buttonClick(ClickEvent event)
    {
        this.app.logout();
    }
}
