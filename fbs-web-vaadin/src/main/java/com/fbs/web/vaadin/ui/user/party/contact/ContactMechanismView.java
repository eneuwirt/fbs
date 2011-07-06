package com.fbs.web.vaadin.ui.user.party.contact;

import java.util.List;

import com.fbs.dmr.universal.model.contact.ContactMechanism;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListView;
import com.fbs.web.vaadin.ui.user.contact.ContactMechanismHelper;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public abstract class ContactMechanismView<T extends ContactMechanism> extends ItemsListView<T, Party> implements ContactMechanismHelper
{
    private static final long serialVersionUID = 1L;

    public ContactMechanismView(MyVaadinApplication app, Class<T> clazz, String[] visibleColumns, String[] visibleFields)
    {
        super(app, clazz, new ContactMechanismFormFieldFactory(app), visibleColumns, visibleFields);
    }

    protected PartyContactMechanism createPartyContactMechanism(ContactMechanism t) throws Exception
    {
        PartyContactMechanism partyContactMechanism;

        partyContactMechanism = new PartyContactMechanism();

        partyContactMechanism.setContactMechanism(t);
        partyContactMechanism.setParty(this.anchor);

        this.app.getServices().getCrudServicePartyContactMechanism().create(partyContactMechanism);

        return partyContactMechanism;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void updateComponents()
    {
        List<PartyContactMechanism> partyContactMechanisms;

        // TODO Temporal
        partyContactMechanisms = this.app.getServices().getCrudServicePartyContactMechanism().findAll();

        this.beanItemContainer.removeAllItems();
        for (PartyContactMechanism pcm : partyContactMechanisms)
        {
            if (pcm.getContactMechanism().getClass().equals(this.clazz) && pcm.getParty().equals(anchor))
            {
                T bean;

                bean = (T) pcm.getContactMechanism();

                this.beanItemContainer.addBean(bean);
            }
        }
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

        if (propertyId.equals(COUNTRY_CODE))
            return this.app.getMessage(ApplicationMessages.TelekommunikationCountryCode);

        if (propertyId.equals(AREA_CODE))
            return this.app.getMessage(ApplicationMessages.TelekommunikationAreaCode);

        if (propertyId.equals(NUMBER))
            return this.app.getMessage(ApplicationMessages.TelekommunikationNumber);

        if (propertyId.equals(ELECTRONIC_ADDRESS))
            return this.app.getMessage(ApplicationMessages.ElectronicAddress);

        return propertyId;
    }

    public FormFieldFactory getFormFieldFactory()
    {
        return new ContactMechanismFormFieldFactory(this.app);
    }

    private static class ContactMechanismFormFieldFactory implements FormFieldFactory
    {
        private static final long serialVersionUID = 1L;
        private MyVaadinApplication app;

        public ContactMechanismFormFieldFactory(MyVaadinApplication app)
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
            else if (ELECTRONIC_ADDRESS.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.ElectronicAddress));
            }
            else if (COUNTRY_CODE.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.TelekommunikationCountryCode));
            }
            else if (AREA_CODE.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.TelekommunikationAreaCode));
            }
            else if (NUMBER.equals(pid))
            {
                result = new TextField(ApplicationMessages.TelekommunikationNumber);
            }

            return result;
        }
    }

}
