package com.fbs.web.vaadin.ui.user.party.contact;

import java.util.List;

import com.fbs.dmr.universal.model.contact.ElectronicAddress;
import com.fbs.web.vaadin.application.MyVaadinApplication;

public class ElectronicAddressView extends ContactMechanismView<ElectronicAddress>
{
    private static final long serialVersionUID = 1L;

    private static final String[] VISIBLE_COLUMNS =
    { ELECTRONIC_ADDRESS };
    private static final String[] VISIBLE_FIELDS =
    { ELECTRONIC_ADDRESS };

    public ElectronicAddressView(MyVaadinApplication app)
    {
        super(app, ElectronicAddress.class, VISIBLE_COLUMNS, VISIBLE_FIELDS);
    }

    @Override
    public ElectronicAddress createBeanInstance()
    {
        return new ElectronicAddress();
    }

    @Override
    public List<ElectronicAddress> getAllBeans() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ElectronicAddress createBean(ElectronicAddress t) throws Exception
    {
        // create Instance
        this.app.getServices().getCrudServiceElectronicAddress().create(t);

        // create party connection
        this.createPartyContactMechanism(t);

        return t;
    }

    @Override
    public void updateBean(ElectronicAddress t) throws Exception
    {
        this.app.getServices().getCrudServiceElectronicAddress().update(t);
    }

    @Override
    public ElectronicAddress readBean(ElectronicAddress t) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteBean(ElectronicAddress t) throws Exception
    {
        this.deletePartyContactMechanism(t);
        
        this.app.getServices().getCrudServiceElectronicAddress().delete(t.getId());
    }

}
