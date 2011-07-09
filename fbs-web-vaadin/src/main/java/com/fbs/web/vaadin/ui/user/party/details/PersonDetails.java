package com.fbs.web.vaadin.ui.user.party.details;

import com.fbs.dmr.universal.model.party.Person;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.vaadin.ui.FormFieldFactory;

public class PersonDetails  extends PartyDetails<Person>
{
    private static final long serialVersionUID = 1L;

    public PersonDetails(MyVaadinApplication app, FormFieldFactory formFieldFactory, String[] visibleFields)
    {
        super(app, formFieldFactory, visibleFields);
    }

}
