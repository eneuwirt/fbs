package com.fbs.web.vaadin.ui.user.party;

import java.util.List;

import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListView;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class PostalAddressView extends  ItemsListView<PostalAddress>
{
    private static final long serialVersionUID = 1L;
    protected static final String ADDRESS1 = "address1";
    protected static final String ADDRESS2 = "address2";
    protected static final String CITY = "city";
    protected static final String POSTAL_CODE = "postalCode";
    protected static final String COUNTRY = "country";
    private static final String[] VISIBLE_COLUMNS =
    { ADDRESS1, ADDRESS2, POSTAL_CODE, CITY, COUNTRY };
    private static final String[] VISIBLE_FIELDS =
    { ADDRESS1, ADDRESS2, POSTAL_CODE, CITY, COUNTRY };

    public PostalAddressView(MyVaadinApplication app)
    {
       super(app, PostalAddress.class, VISIBLE_COLUMNS, VISIBLE_FIELDS);
    }

    @Override
    public void updateComponents(Object obj)
    {
        Party party = (Party) obj;
        List<PartyContactMechanism> partyContactMechanisms;
        
        // TODO Temporal
        partyContactMechanisms = this.app.getServices().getCrudServicePartyContactMechanism().findAll();

        this.beanItemContainer.removeAllItems();
        for (PartyContactMechanism pcm : partyContactMechanisms)
        {
            if (pcm.getContactMechanism() instanceof PostalAddress && pcm.getParty().equals(party))
            {
                PostalAddress pa;

                pa = (PostalAddress) pcm.getContactMechanism();

                this.beanItemContainer.addBean(pa);
            }
        }
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
    public PostalAddress createBean(PostalAddress t) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void updateBean(PostalAddress t) throws Exception
    {
        // TODO Auto-generated method stub
        
    }


    @Override
    public PostalAddress readBean(PostalAddress t) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void deleteBean(PostalAddress t) throws Exception
    {
        // TODO Auto-generated method stub
        
    }


    @Override
    public FormFieldFactory getFormFieldFactory()
    {
        return new PostalCodeFormFieldFactory(this.app);
    }


    @Override
    public String getColumnName(String propertyId)
    {
        if (propertyId.equals(ADDRESS1))
            return this.app.getMessage(ApplicationMessages.PostalAddressAddress1);

        if (propertyId.equals(ADDRESS2))
            return this.app.getMessage(ApplicationMessages.PostalAddressAddress2);

        if (propertyId.equals(CITY))
            return this.app.getMessage(ApplicationMessages.PostalAddressCity);

        if (propertyId.equals(POSTAL_CODE))
            return this.app.getMessage(ApplicationMessages.PostalAddressPostalCode);

        if (propertyId.equals(COUNTRY))
            return this.app.getMessage(ApplicationMessages.PostalAddressCountry);

        return propertyId;
    }
    
    
    private static class PostalCodeFormFieldFactory implements FormFieldFactory
    {
        private static final long serialVersionUID = 1L;
        private MyVaadinApplication app;

        public PostalCodeFormFieldFactory(MyVaadinApplication app)
        {
            this.app = app;
        }

        @Override
        public Field createField(Item item, Object propertyId, Component uiContext)
        {
            Field result = null;
            String pid = (String) propertyId;

            if (ADDRESS1.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.PostalAddressAddress1));
            }
            else if (ADDRESS2.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.PostalAddressAddress2));
            }
            else if (CITY.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.PostalAddressCity));
            }
            else if (POSTAL_CODE.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.PostalAddressPostalCode));
            }
            else if (COUNTRY.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.PostalAddressCountry));
            }

            return result;
        }
    }
}
