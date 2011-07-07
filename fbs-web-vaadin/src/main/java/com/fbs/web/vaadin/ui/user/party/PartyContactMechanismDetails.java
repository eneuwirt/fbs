package com.fbs.web.vaadin.ui.user.party;

import java.util.Arrays;
import java.util.List;


import com.fbs.dmr.universal.model.contact.ContactMechanism;
import com.fbs.dmr.universal.model.contact.ElectronicAddress;
import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.dmr.universal.model.contact.TelecommunicationNumber;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.items.DetailsAware;
import com.fbs.web.vaadin.ui.user.contact.ContactMechanismHelper;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class PartyContactMechanismDetails extends CustomComponent implements DetailsAware<PartyContactMechanism>,
        ContactMechanismHelper
{
    private static final long serialVersionUID = 1L;

    private static final String ID = "id";
    private static final String COMMENT = "comment";

    private static final String PARTY = "party";

    static final String[] VISIBLE_FIELDS_DETAILS_POSTAL_ADDRESS = new String[]
    { ADDRESS1, ADDRESS2, POSTAL_CODE, CITY, COUNTRY, };
    static final String[] VISIBLE_FIELDS_DETAILS_ELECTRONIC_ADDRESS = new String[]
    { ELECTRONIC_ADDRESS };
    static final String[] VISIBLE_FIELDS_DETAILS_TELEKOM = new String[]
    { COUNTRY_CODE, AREA_CODE, NUMBER };

    private MyVaadinApplication app;

    protected Panel mainPanel;
    protected String[] visibleFields;
    protected PartyContactMechanism bean;
    protected Form form;
    protected Form formDetails;
    private OptionGroup optionGroupPurpose;

    public PartyContactMechanismDetails(MyVaadinApplication app, String[] visibleFields)
    {
        this.app = app;

        // A layout structure used for composition
        this.app = app;
        this.visibleFields = visibleFields;

        mainPanel = new Panel();
        mainPanel.setContent(new VerticalLayout());
        mainPanel.getContent().setSizeFull();
        mainPanel.setSizeFull();
        setSizeFull();

        this.form = new Form();
        this.form.setImmediate(true);
        this.form.setFormFieldFactory(new PartyContactMechanismFormFieldFactory(app));

        this.formDetails = new Form();
        this.formDetails.setImmediate(true);
        this.formDetails.setFormFieldFactory(new ContactDetailsFormFieldFactory(app));

        this.optionGroupPurpose = new OptionGroup();
        this.optionGroupPurpose.setCaption(this.app.getMessage(ApplicationMessages.PartyContactMechanismPurpose));
        this.optionGroupPurpose.setMultiSelect(true);
        this.optionGroupPurpose.setNullSelectionAllowed(true);
        this.optionGroupPurpose.setImmediate(true);

        this.mainPanel.addComponent(form);
        this.mainPanel.addComponent(this.formDetails);
        this.mainPanel.addComponent(this.optionGroupPurpose);

        // The composition root MUST be set
        setCompositionRoot(mainPanel);
    }

    @Override
    public void setBean(PartyContactMechanism bean)
    {
        BeanItem<PartyContactMechanism> beanItem;

        this.bean = bean;
        
        beanItem = new BeanItem<PartyContactMechanism>(bean);

        this.form.setItemDataSource(beanItem, Arrays.asList(this.visibleFields));
    }

    @Override
    public PartyContactMechanism getBean()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createBeanDetails(PartyContactMechanism t)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void readBeanDetails(PartyContactMechanism t)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateBeanDetails(PartyContactMechanism t)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteBeanDetails(PartyContactMechanism t)
    {
        // TODO Auto-generated method stub

    }

    protected String[] determineVisibleFieldDetails(ContactMechanism contactMechanism)
    {
        String[] visible_field_details = null;

        if (contactMechanism instanceof PostalAddress)
        {
            visible_field_details = VISIBLE_FIELDS_DETAILS_POSTAL_ADDRESS;
        }
        else if (contactMechanism instanceof ElectronicAddress)
        {
            visible_field_details = VISIBLE_FIELDS_DETAILS_ELECTRONIC_ADDRESS;
        }
        else if (contactMechanism instanceof TelecommunicationNumber)
        {
            visible_field_details = VISIBLE_FIELDS_DETAILS_TELEKOM;
        }
        else
        {
            visible_field_details = new String[]
            {};
        }

        return visible_field_details;
    }

    private static class PartyContactMechanismFormFieldFactory implements FormFieldFactory
    {
        private static final long serialVersionUID = 1L;
        private MyVaadinApplication app;

        public PartyContactMechanismFormFieldFactory(MyVaadinApplication app)
        {
            this.app = app;
        }

        @Override
        public Field createField(Item item, Object propertyId, Component uiContext)
        {
            // Identify the fields by their Property ID.
            Field result = null;

            String pid = (String) propertyId;

            if (ID.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.PartyContactMechanismId));

                result.setReadOnly(true);
            }
            else if (COMMENT.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.PartyContactMechanismComment));
            }
            else if (PARTY.equals(pid))
            {
                Select select;
                BeanItemContainer<Party> container;
                List<Party> parties;
                String caption;

                caption = this.app.getMessage(ApplicationMessages.PartyContactMechanismParty);

                parties = this.app.getServices().getCrudServiceParty().findAll();
                container = new BeanItemContainer<Party>(Party.class, parties);

                select = new Select(caption, container);

                select.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
                select.setItemCaptionPropertyId("name");

                result = select;
            }

            return result;
        }
    }

    public static class ContactDetailsFormFieldFactory implements FormFieldFactory
    {
        private static final long serialVersionUID = 1L;
        private MyVaadinApplication app;

        public ContactDetailsFormFieldFactory(MyVaadinApplication app)
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
