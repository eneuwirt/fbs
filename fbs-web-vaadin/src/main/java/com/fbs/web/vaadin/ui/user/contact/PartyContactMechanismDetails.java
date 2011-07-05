package com.fbs.web.vaadin.ui.user.contact;

import java.util.List;

import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.items.DetailsComponent;
import com.vaadin.data.Item;
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

public class PartyContactMechanismDetails extends CustomComponent implements DetailsComponent<PartyContactMechanism>
{
    private static final long serialVersionUID = 1L;
    
    private static final String ID = "id";
    private static final String COMMENT = "comment";
    
    private static final String PARTY = "party";
    private static final String PARTY_NAME = "party.name";
    
    protected static final String ADDRESS1 = "address1";
    protected static final String ADDRESS2 = "address2";
    protected static final String CITY = "city";
    protected static final String POSTAL_CODE = "postalCode";
    protected static final String COUNTRY = "country";
    protected static final String ELECTRONIC_ADDRESS = "electronicAddress";
    protected static final String COUNTRY_CODE = "countryCode";
    protected static final String AREA_CODE = "areaCode";
    protected static final String NUMBER = "number";
    
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
        
    }

    @Override
    public void setBean(PartyContactMechanism t)
    {
        // TODO Auto-generated method stub

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
