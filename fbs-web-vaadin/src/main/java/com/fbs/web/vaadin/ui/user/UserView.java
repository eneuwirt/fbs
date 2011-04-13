package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.MyVaadinApplication;
import com.fbs.web.vaadin.ui.auth.LogoutListener;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class UserView extends VerticalLayout
{
    private static final long serialVersionUID = 1L;
    private MyVaadinApplication app;


    public UserView(MyVaadinApplication app)
    {
        super();
        this.app = app;

        Label demo = new Label("Du hast dich als User eingelogt");

        Button logout = new Button("logout");
        logout.addListener(new LogoutListener(app));

        this.addComponent(logout);
        this.addComponent(demo);
    }
}
