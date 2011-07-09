package com.fbs.web.vaadin.ui.user.party.details;

import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.vaadin.ui.FormFieldFactory;

public class OrganizationDetails extends PartyDetails<Organization>
{
    private static final long serialVersionUID = 1L;

    public OrganizationDetails(MyVaadinApplication app, FormFieldFactory formFieldFactory, String[] visibleFields)
    {
        super(app, formFieldFactory, visibleFields);
    }

}
