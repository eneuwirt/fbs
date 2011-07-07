package com.fbs.web.vaadin.ui.user.party.contact;

import java.util.List;

import com.fbs.dmr.universal.model.contact.TelecommunicationNumber;
import com.fbs.web.vaadin.application.MyVaadinApplication;

public class TelekomView extends ContactMechanismView<TelecommunicationNumber>
{
    private static final long serialVersionUID = 1L;

    private static final String[] VISIBLE_COLUMNS =
    { COUNTRY_CODE, AREA_CODE, NUMBER };
    private static final String[] VISIBLE_FIELDS =
    { COUNTRY_CODE, AREA_CODE, NUMBER };

    public TelekomView(MyVaadinApplication app)
    {
        super(app, TelecommunicationNumber.class, VISIBLE_COLUMNS, VISIBLE_FIELDS, NESTED_PROPERTIES);
    }

    @Override
    public TelecommunicationNumber createBeanInstance()
    {
        return new TelecommunicationNumber();
    }

    @Override
    public List<TelecommunicationNumber> getAllBeans() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TelecommunicationNumber createBean(TelecommunicationNumber t) throws Exception
    {
        // create Instance
        this.app.getServices().getCrudServiceTelecommunicationNumber().create(t);

        // create party connection
        this.createPartyContactMechanism(t);

        return t;
    }

    @Override
    public void updateBean(TelecommunicationNumber t) throws Exception
    {
        this.app.getServices().getCrudServiceTelecommunicationNumber().update(t);
    }

    @Override
    public TelecommunicationNumber readBean(TelecommunicationNumber t) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteBean(TelecommunicationNumber t) throws Exception
    {
        this.deletePartyContactMechanism(t);

        this.app.getServices().getCrudServiceTelecommunicationNumber().delete(t.getId());
    }
}
