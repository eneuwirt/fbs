package com.fbs.web.vaadin.ui.user.party.contact;

import java.util.List;

import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.web.vaadin.application.MyVaadinApplication;

public class PostalAddressView extends ContactMechanismView<PostalAddress>
{
    private static final long serialVersionUID = 1L;
    private static final String[] VISIBLE_COLUMNS =
    { ADDRESS1, ADDRESS2, POSTAL_CODE, CITY, COUNTRY };
    private static final String[] VISIBLE_FIELDS =
    { ADDRESS1, ADDRESS2, POSTAL_CODE, CITY, COUNTRY };

    public PostalAddressView(MyVaadinApplication app)
    {
        super(app, PostalAddress.class, VISIBLE_COLUMNS, VISIBLE_FIELDS);
    }

    @Override
    public PostalAddress createBeanInstance()
    {
        return new PostalAddress();
    }

    @Override
    public List<PostalAddress> getAllBeans() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PostalAddress createBean(PostalAddress postalAddress) throws Exception
    {
        // create Instance
        this.app.getServices().getCrudServicePostalAddress().create(postalAddress);
        
        // create party connection
        this.createPartyContactMechanism(postalAddress);
        
        return postalAddress;
    }

    @Override
    public void updateBean(PostalAddress t) throws Exception
    {
        this.app.getServices().getCrudServicePostalAddress().update(t);
    }

    @Override
    public PostalAddress readBean(PostalAddress t) throws Exception
    {
        PostalAddress result;
        
        result = this.app.getServices().getCrudServicePostalAddress().read(t.getId());
        
        return result;
    }

    @Override
    public void deleteBean(PostalAddress t) throws Exception
    {
        this.deletePartyContactMechanism(t);
        
        this.app.getServices().getCrudServicePostalAddress().delete(t.getId());
    }
}
