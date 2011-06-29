package com.fbs.web.vaadin.ui.user.party;

import java.util.Arrays;
import java.util.List;

import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;

public class PostalAddressView extends Panel
{
    private static final long serialVersionUID = 1L;
    protected static final String ADDRESS1 = "address1";
    protected static final String ADDRESS2 = "address2";
    protected static final String CITY = "city";
    protected static final String POSTAL_CODE = "postalCode";
    protected static final String COUNTRY = "country";
    private String[] VISIBLE_COLUMNS =
    { ADDRESS1, ADDRESS2, POSTAL_CODE, CITY, COUNTRY };

    protected MyVaadinApplication app;
    
    protected Table tablePostalAddress;
    protected BeanItemContainer<PostalAddress> beanItemContainerPostalAddress;

    public PostalAddressView(MyVaadinApplication app)
    {
        this.app = app;

        this.tablePostalAddress = new Table();

        this.beanItemContainerPostalAddress = new BeanItemContainer<PostalAddress>(PostalAddress.class);

        this.tablePostalAddress.setContainerDataSource(beanItemContainerPostalAddress);

        this.initLayout();
    }

    private void initLayout()
    {
        this.setSizeFull();
        this.setScrollable(true);

        this.tablePostalAddress.setSizeFull();
        this.tablePostalAddress.setVisibleColumns(VISIBLE_COLUMNS);
        // Set nicer header names
        for (String propertyId : VISIBLE_COLUMNS)
        {
            String columnName = this.getColumnName(propertyId);

            this.tablePostalAddress.setColumnHeader(propertyId, columnName);
        }

        this.addComponent(this.tablePostalAddress);
    }

    private String getColumnName(String propertyId)
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

    public void updateComponents(Party party)
    {
        List<PartyContactMechanism> partyContactMechanisms;
        // TODO Temporal
        partyContactMechanisms = this.app.getServices().getCrudServicePartyContactMechanism().findAll();

        this.beanItemContainerPostalAddress.removeAllItems();
        for (PartyContactMechanism pcm : partyContactMechanisms)
        {
            if (pcm.getContactMechanism() instanceof PostalAddress && pcm.getParty().equals(party))
            {
                PostalAddress pa;

                pa = (PostalAddress) pcm.getContactMechanism();

                this.beanItemContainerPostalAddress.addBean(pa);
            }
        }
    }
}
