package com.fbs.web.vaadin.ui.user.party.details;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fbs.dmr.universal.model.contact.ContactMechanism;
import com.fbs.dmr.universal.model.contact.ContactMechanismPurposeType;
import com.fbs.dmr.universal.model.contact.ElectronicAddress;
import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.dmr.universal.model.contact.TelecommunicationNumber;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.dmr.universal.model.party.PartyContactMechanismPurpose;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.AnchorAware;
import com.fbs.web.vaadin.ui.common.ListAware;
import com.fbs.web.vaadin.ui.common.items.BeanAware;
import com.fbs.web.vaadin.ui.user.contact.ContactMechanismConstants;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Select;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class PartyContactMechanismView extends VerticalLayout implements AnchorAware<PartyContactMechanism, Party>,
        ListAware<PartyContactMechanism>,  ContactMechanismConstants
{
    private static final long serialVersionUID = 1L;

    private static final String COMMENT = "comment";

    private static final String[] VISIBLE_COLUMNS =
    { ID, CONTACT_MECHANISM_ADDRESS };

    protected static final String[] NESTED_PROPERTIES = new String[]
    { CONTACT_MECHANISM_ADDRESS };

    public enum Action
    {
        CREATE_POSTAL, CREATE_ELECTRONIC, CREATE_TELECOM, DELETE, UNDEFINED, UPDATE;
    };

    protected MyVaadinApplication app;

    private Party anchor;

    private PartyContactMechanism selectedBean;

    private Action action = Action.UNDEFINED;

    protected Table tableBeans;
    protected BeanItemContainer<PartyContactMechanism> beanItemContainer;

    protected String createDialogCaption = "";
    protected String updateDialogCaption = "";
    protected CrudDialog dialog;
    //
    protected Button addPostal;
    protected Button addElectronic;
    protected Button addTelecomm;
    protected Button buttonEdit;
    protected Button buttonDelete;
    //
    protected String[] visibleColumns = new String[]
    {};
    protected String[] nestedContainerProperties = new String[]
    {};

    public PartyContactMechanismView(MyVaadinApplication app)
    {
        super();

        this.app = app;
        this.visibleColumns = VISIBLE_COLUMNS;
        this.nestedContainerProperties = NESTED_PROPERTIES;

        this.createDialogCaption = this.app.getMessage(ApplicationMessages.CommonCreate);
        this.updateDialogCaption = this.app.getMessage(ApplicationMessages.CommonUpdate);

        this.dialog = new CrudDialog(this);

        this.beanItemContainer = new BeanItemContainer<PartyContactMechanism>(PartyContactMechanism.class);
        for (String p : this.nestedContainerProperties)
        {
            this.beanItemContainer.addNestedContainerProperty(p);
        }

        this.tableBeans = new Table();
        this.tableBeans.setSelectable(true);
        this.tableBeans.setMultiSelect(false);
        this.tableBeans.setImmediate(true);
        this.tableBeans.setContainerDataSource(beanItemContainer);
        this.tableBeans.addListener(new TableSelectListener(this));

        this.addPostal = new Button(this.app.getMessage(ApplicationMessages.PartyPostalAddress), new CreateListener(this));
        this.addElectronic = new Button(this.app.getMessage(ApplicationMessages.PartyElectronicAddress), new CreateListener(this));
        this.addTelecomm = new Button(this.app.getMessage(ApplicationMessages.PartyTelecommunicationNumber), new CreateListener(this));

        this.buttonEdit = new Button(this.app.getMessage(ApplicationMessages.CommonUpdate), new EditListener(this));
        this.buttonEdit.setEnabled(false);

        this.buttonDelete = new Button(this.app.getMessage(ApplicationMessages.CommonDelete), new DeleteListener(this));
        this.buttonDelete.setEnabled(false);

        this.initLayout();
    }

    private void initLayout()
    {
        HorizontalLayout buttonRow;

        this.setSizeFull();

        this.tableBeans.setSizeFull();
        this.tableBeans.setVisibleColumns(this.visibleColumns);
        // Set nicer header names
        for (String propertyId : this.visibleColumns)
        {
            String columnName = this.getColumnName(propertyId);

            this.tableBeans.setColumnHeader(propertyId, columnName);
        }

        buttonRow = new HorizontalLayout();
        buttonRow.setMargin(true);

        buttonRow.addComponent(this.addPostal);
        buttonRow.addComponent(this.addPostal);
        buttonRow.addComponent(this.addTelecomm);
        buttonRow.addComponent(this.addElectronic);
        buttonRow.addComponent(this.buttonEdit);
        buttonRow.addComponent(this.buttonDelete);

        this.addComponent(this.tableBeans);
        this.addComponent(buttonRow);

        this.setExpandRatio(this.tableBeans, 1.0f);
    }

    @Override
    public String getColumnName(String pid)
    {
        if (pid.equals(ID))
            return this.app.getMessage(ApplicationMessages.PartyContactMechanismId);

        if (pid.equals(COMMENT))
            return this.app.getMessage(ApplicationMessages.PartyContactMechanismComment);

        if (pid.equals(PARTY_NAME))
            return this.app.getMessage(ApplicationMessages.PartyContactMechanismParty);

        if (pid.equals(CONTACT_MECHANISM_ADDRESS))
            return this.app.getMessage(ApplicationMessages.PartyContactMechanismAddress);

        if (pid.equals(CONTACT_MECHANISM_TYPE_DESCR))
            return this.app.getMessage(ApplicationMessages.ContactMechanismTypeTitle);

        return pid;
    }

    public PartyContactMechanism createBeanInstance()
    {
        PartyContactMechanism result;
        ContactMechanism contactMechanism = null;

        switch (this.action)
        {
            case CREATE_ELECTRONIC:
                contactMechanism = new ElectronicAddress();
                break;

            case CREATE_POSTAL:
                contactMechanism = new PostalAddress();
                break;

            case CREATE_TELECOM:
                contactMechanism = new TelecommunicationNumber();
                break;
        }

        result = new PartyContactMechanism();
        result.setParty(anchor);

        result.setContactMechanism(contactMechanism);

        return result;
    }

    public void deleteBean(PartyContactMechanism t) throws Exception
    {
        this.app.getServices().getCrudServicePartyContactMechanism().delete(t.getId());

        if (t.getContactMechanism() instanceof PostalAddress)
        {
            PostalAddress postalAddress;

            postalAddress = (PostalAddress) t.getContactMechanism();

            this.app.getServices().getCrudServicePostalAddress().delete(postalAddress.getId());
        }
        else if (t.getContactMechanism() instanceof TelecommunicationNumber)
        {
            TelecommunicationNumber tn;

            tn = (TelecommunicationNumber) t.getContactMechanism();

            this.app.getServices().getCrudServiceTelecommunicationNumber().delete(tn.getId());
        }
        else if (t.getContactMechanism() instanceof ElectronicAddress)
        {
            ElectronicAddress ea;

            ea = (ElectronicAddress) t.getContactMechanism();

            this.app.getServices().getCrudServiceElectronicAddress().delete(ea.getId());
        }
        else
        {
            String msg = "Unknown class: " + t.getContactMechanism().getClass().getName();

            throw new IllegalArgumentException(msg);
        }
    }

    @Override
    public void setAnchor(Party anchor)
    {
        this.anchor = anchor;

        if (this.anchor != null)
        {
            this.updateComponents();
        }
    }

    @Override
    public void updateComponents()
    {
        List<PartyContactMechanism> partyContactMechanisms;

        partyContactMechanisms = this.app.getServices().getCrudServicePartyContactMechanism().findByParty(anchor);

        this.beanItemContainer.removeAllItems();
        for (PartyContactMechanism pcm : partyContactMechanisms)
        {
            this.beanItemContainer.addBean(pcm);
        }
    }

    private static class TableSelectListener implements Property.ValueChangeListener
    {
        private static final long serialVersionUID = 1L;
        private PartyContactMechanismView view;

        public TableSelectListener(PartyContactMechanismView view)
        {
            this.view = view;
        }

        @Override
        public void valueChange(ValueChangeEvent event)
        {
            this.view.selectedBean = (PartyContactMechanism) this.view.tableBeans.getValue();

            if (this.view.selectedBean != null)
            {
                this.view.buttonDelete.setEnabled(true);

                this.view.buttonEdit.setEnabled(true);
            }
            else
            {
                this.view.buttonDelete.setEnabled(false);

                this.view.buttonEdit.setEnabled(false);
            }
        }
    }

    private static class CreateListener implements Button.ClickListener
    {
        private static final long serialVersionUID = 1L;
        private PartyContactMechanismView view;

        public CreateListener(PartyContactMechanismView view)
        {
            this.view = view;
        }

        @Override
        public void buttonClick(ClickEvent event)
        {
            PartyContactMechanism bean;

            this.view.dialog.setCaption(this.view.createDialogCaption);

            if (event.getButton().equals(view.addElectronic))
            {
                this.view.action = Action.CREATE_ELECTRONIC;
            }
            else if (event.getButton().equals(view.addPostal))
            {
                this.view.action = Action.CREATE_POSTAL;
            }
            else if (event.getButton().equals(view.addTelecomm))
            {
                this.view.action = Action.CREATE_TELECOM;
            }

            bean = this.view.createBeanInstance();

            this.view.dialog.setBean(bean);

            if (view.dialog.getParent() != null)
            {
                // window is already showing
                view.getWindow().showNotification("Window is already open");
            }
            else
            {
                // Open the subwindow by adding it to the parent window
                view.getWindow().addWindow(view.dialog);
            }
        }
    }

    private static class DeleteListener implements Button.ClickListener
    {
        private static final long serialVersionUID = 1L;
        private static Logger logger = Logger.getLogger(DeleteListener.class.getName());
        private PartyContactMechanismView view;

        public DeleteListener(PartyContactMechanismView view)
        {
            this.view = view;
        }

        @Override
        public void buttonClick(ClickEvent event)
        {
            PartyContactMechanism bean;

            try
            {
                this.view.action = Action.DELETE;

                bean = this.view.selectedBean;

                this.view.deleteBean(bean);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Exception occured: " + e.getMessage());
            }

            this.view.updateComponents();
        }
    }

    private static class EditListener implements Button.ClickListener
    {
        private static final long serialVersionUID = 1L;
        private PartyContactMechanismView view;

        public EditListener(PartyContactMechanismView view)
        {
            this.view = view;
        }

        @Override
        public void buttonClick(ClickEvent event)
        {
            PartyContactMechanism bean;

            bean = this.view.selectedBean;

            this.view.dialog.setCaption(this.view.updateDialogCaption);

            this.view.action = Action.UPDATE;

            this.view.dialog.setBean(bean);

            if (view.dialog.getParent() != null)
            {
                // window is already showing
                view.getWindow().showNotification("Window is already open");
            }
            else
            {
                // Open the subwindow by adding it to the parent window
                view.getWindow().addWindow(view.dialog);
            }
        }
    }

    private static class CrudDialog extends Window implements BeanAware<PartyContactMechanism>
    {
        private static final long serialVersionUID = 1L;
        static final String[] VISIBLE_FIELDS = new String[] {COMMENT};
        static final String[] VISIBLE_FIELDS_DETAILS_POSTAL_ADDRESS = new String[]
        { ADDRESS1, ADDRESS2, POSTAL_CODE, CITY, COUNTRY, };
        static final String[] VISIBLE_FIELDS_DETAILS_ELECTRONIC_ADDRESS = new String[]
        { ELECTRONIC_ADDRESS };
        static final String[] VISIBLE_FIELDS_DETAILS_TELECOM = new String[]
        { COUNTRY_CODE, AREA_CODE, NUMBER };

        private MyVaadinApplication app;
        private PartyContactMechanismView view;

        private PartyContactMechanism bean;
        private Form formPartyContactMechanism;
        private ContactMechanism contactMechanism;
        private Form contactMechanismForm;
        private OptionGroup optionGroupPurpose;

        private Button buttonSave;
        private Button buttonClose;

        public CrudDialog(PartyContactMechanismView view)
        {
            super();

            this.app = view.app;
            this.view = view;

            this.setModal(true);
            this.setClosable(true);
            // this.setHeight("80%");
            this.setWidth("50%");

            VerticalLayout layout = (VerticalLayout) this.getContent();
            layout.setMargin(true);
            layout.setSpacing(true);

            this.formPartyContactMechanism = new Form();
            this.formPartyContactMechanism.setImmediate(true);
            this.formPartyContactMechanism.setSizeFull();
            this.formPartyContactMechanism.setFormFieldFactory(new PartyContactMechanismFormFieldFactory(this.app));

            this.contactMechanismForm = new Form();
            this.contactMechanismForm.setImmediate(true);
            this.contactMechanismForm.setSizeFull();
            this.contactMechanismForm.setFormFieldFactory(new PartyContactMechanismFormFieldFactory(this.app));

            DialogListener dl = new DialogListener(this);
            this.buttonSave = new Button(this.app.getMessage(ApplicationMessages.CommonSave), dl);
            this.buttonClose = new Button(this.app.getMessage(ApplicationMessages.CommonCancel), dl);

            this.optionGroupPurpose = new OptionGroup();
            this.optionGroupPurpose.setCaption(this.app.getMessage(ApplicationMessages.PartyContactMechanismPurpose));
            this.optionGroupPurpose.setMultiSelect(true);
            this.optionGroupPurpose.setNullSelectionAllowed(true);
            this.optionGroupPurpose.setImmediate(true);

            HorizontalLayout buttonRow = new HorizontalLayout();
            buttonRow.setMargin(true);
            buttonRow.setSpacing(true);
            buttonRow.setSizeFull();

            buttonRow.addComponent(buttonSave);
            buttonRow.addComponent(buttonClose);

            layout.addComponent(this.contactMechanismForm);
            layout.addComponent(this.optionGroupPurpose);
            layout.addComponent(this.formPartyContactMechanism);

            layout.addComponent(buttonRow);
        }

        @Override
        public void setBean(PartyContactMechanism bean)
        {
            BeanItem<PartyContactMechanism> beanItemPcm;

            this.bean = bean;
            this.contactMechanism = bean.getContactMechanism();

            beanItemPcm = new BeanItem<PartyContactMechanism>(bean);
            this.formPartyContactMechanism.setItemDataSource(beanItemPcm, Arrays.asList(VISIBLE_FIELDS));

            if (this.contactMechanism instanceof PostalAddress)
            {
                BeanItem<PostalAddress> beanItem;
                PostalAddress pa = (PostalAddress) this.contactMechanism;

                beanItem = new BeanItem<PostalAddress>(pa);
                this.contactMechanismForm.setItemDataSource(beanItem, Arrays.asList(VISIBLE_FIELDS_DETAILS_POSTAL_ADDRESS));
            }
            else if (this.contactMechanism instanceof TelecommunicationNumber)
            {
                BeanItem<TelecommunicationNumber> beanItem;
                TelecommunicationNumber ta = (TelecommunicationNumber) this.contactMechanism;

                beanItem = new BeanItem<TelecommunicationNumber>(ta);
                this.contactMechanismForm.setItemDataSource(beanItem, Arrays.asList(VISIBLE_FIELDS_DETAILS_TELECOM));
            }
            else if (this.contactMechanism instanceof ElectronicAddress)
            {
                BeanItem<ElectronicAddress> beanItem;
                ElectronicAddress ea = (ElectronicAddress) this.contactMechanism;

                beanItem = new BeanItem<ElectronicAddress>(ea);
                this.contactMechanismForm.setItemDataSource(beanItem, Arrays.asList(VISIBLE_FIELDS_DETAILS_ELECTRONIC_ADDRESS));
            }
            else
            {
                throw new IllegalArgumentException("instance of unknown class " + this.contactMechanism.getClass().getName());
            }

            this.setOptiongroupPartyContactMechanism(bean);
        }

        private void setOptiongroupPartyContactMechanism(PartyContactMechanism pcm)
        {
            List<ContactMechanismPurposeType> types;

            types = this.app.getServices().getCrudServiceContactMechanismPurposeType().findAll();

            for (ContactMechanismPurposeType t : types)
            {
                this.optionGroupPurpose.addItem(t.getDescription());
            }

            // set selection
            this.optionGroupPurpose.setValue(null);
            if (pcm != null)
            {
                List<PartyContactMechanismPurpose> purposes;

                purposes = this.app.getServices().getCrudServicePartyContactMechanismPurpose().findByPartyContactMechanism(pcm);

                for (PartyContactMechanismPurpose p : purposes)
                {
                    this.optionGroupPurpose.select(p.getContactMechanismPurposeType().getDescription());
                }
            }
        }

        @Override
        public PartyContactMechanism getBean()
        {
            return this.bean;
        }
        
        @SuppressWarnings("unchecked")
        public void updatePartyContactMechanism(PartyContactMechanism t) throws Exception
        {
            Set<String> selectedPurposes;
            List<PartyContactMechanismPurpose> purposes;
            
            this.app.getServices().getCrudServicePartyContactMechanism().update(t);

            if (t.getContactMechanism() instanceof PostalAddress)
            {
                PostalAddress postalAddress;

                postalAddress = (PostalAddress) t.getContactMechanism();

                this.app.getServices().getCrudServicePostalAddress().update(postalAddress);
            }
            else if (t.getContactMechanism() instanceof TelecommunicationNumber)
            {
                TelecommunicationNumber tn;

                tn = (TelecommunicationNumber) t.getContactMechanism();

                this.app.getServices().getCrudServiceTelecommunicationNumber().update(tn);
            }
            else if (t.getContactMechanism() instanceof ElectronicAddress)
            {
                ElectronicAddress ea;

                ea = (ElectronicAddress) t.getContactMechanism();

                this.app.getServices().getCrudServiceElectronicAddress().update(ea);
            }
            else
            {
                String msg = "Unknown class: " + t.getContactMechanism().getClass().getName();

                throw new IllegalArgumentException(msg);
            }
            
            selectedPurposes = (Set<String>) this.optionGroupPurpose.getValue();
            // remove all unselected roles
            purposes = this.app.getServices().getCrudServicePartyContactMechanismPurpose().findByPartyContactMechanism(t);
            for (PartyContactMechanismPurpose p : purposes)
            {
                if (!selectedPurposes.contains(p.getContactMechanismPurposeType().getDescription()))
                {
                    this.app.getServices().getCrudServicePartyContactMechanismPurpose().delete(p.getId());
                }
            }

            // add all selected items to the party contact mechanism
            for (String s : selectedPurposes)
            {
                PartyContactMechanismPurpose purpose;

                purpose = this.app.getServices().getCrudServicePartyContactMechanismPurpose()
                        .findByPartyContactMechanismAndContactMechanismPurposeType(t, s);
                
                if (purpose == null)
                {
                    ContactMechanismPurposeType type;
                    
                    type = this.app.getServices().getCrudServiceContactMechanismPurposeType().findForDescription(s);
                    
                    purpose = new PartyContactMechanismPurpose();
                    
                    purpose.setContactMechanismPurposeType(type);
                    purpose.setPartyContactMechanism(t);
                    
                    this.app.getServices().getCrudServicePartyContactMechanismPurpose().create(purpose);
                }
                else
                {
                    this.app.getServices().getCrudServicePartyContactMechanismPurpose().update(purpose);
                }
            }
        }

        @SuppressWarnings("unchecked")
        private void createPartyContactMechanism(PartyContactMechanism pcm) throws Exception
        {
            Set<String> selectedPurposes;
            
            if (pcm.getContactMechanism() instanceof PostalAddress)
            {
                PostalAddress postalAddress;

                postalAddress = (PostalAddress) pcm.getContactMechanism();

                this.app.getServices().getCrudServicePostalAddress().create(postalAddress);
            }
            else if (pcm.getContactMechanism() instanceof TelecommunicationNumber)
            {
                TelecommunicationNumber tn;

                tn = (TelecommunicationNumber) pcm.getContactMechanism();

                this.app.getServices().getCrudServiceTelecommunicationNumber().create(tn);
            }
            else if (pcm.getContactMechanism() instanceof ElectronicAddress)
            {
                ElectronicAddress ea;

                ea = (ElectronicAddress) pcm.getContactMechanism();

                this.app.getServices().getCrudServiceElectronicAddress().create(ea);
            }
            else
            {
                String msg = "Unknown class: " + pcm.getContactMechanism().getClass().getName();

                throw new IllegalArgumentException(msg);
            }

            this.app.getServices().getCrudServicePartyContactMechanism().create(pcm);

            // create party contact mechanism purpsoe
            selectedPurposes = (Set<String>) this.optionGroupPurpose.getValue();
            for (String selectedPurpose : selectedPurposes)
            {
                PartyContactMechanismPurpose purpose;                
                ContactMechanismPurposeType type;
                
                type = this.app.getServices().getCrudServiceContactMechanismPurposeType().findForDescription(selectedPurpose);
                
                purpose = new PartyContactMechanismPurpose();
                purpose.setContactMechanismPurposeType(type);
                purpose.setPartyContactMechanism(pcm);
                
                this.app.getServices().getCrudServicePartyContactMechanismPurpose().create(purpose);
            }
            
        }

        private static class DialogListener implements Button.ClickListener
        {
            private static final long serialVersionUID = 1L;
            Logger logger = Logger.getLogger(DialogListener.class.getName());

            private CrudDialog dialog;

            public DialogListener(CrudDialog dialog)
            {
                this.dialog = dialog;
            }

            @Override
            public void buttonClick(ClickEvent event)
            {
                if (event.getButton().equals(dialog.buttonSave))
                {
                    try
                    {
                        switch (this.dialog.view.action)
                        {
                            case CREATE_POSTAL:
                            case CREATE_ELECTRONIC:
                            case CREATE_TELECOM:
                                this.dialog.createPartyContactMechanism(this.dialog.bean);

                            case UPDATE:
                                this.dialog.updatePartyContactMechanism(this.dialog.bean);

                        }
                    }
                    catch (Exception e)
                    {
                        logger.log(Level.SEVERE, "Exception occured: " + e.getMessage());
                    }
                }

                this.dialog.view.updateComponents();

                // close the window by removing it from the parent window
                this.dialog.getParent().removeWindow(dialog);
            }
        }
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
                result = new TextArea(this.app.getMessage(ApplicationMessages.PartyContactMechanismComment));
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
                select.setReadOnly(true);

                result = select;
            }
            else if (ADDRESS1.equals(pid))
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
                result = new TextField(this.app.getMessage(ApplicationMessages.TelecommunicationCountryCode));
            }
            else if (AREA_CODE.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.TelecommunicationAreaCode));
            }
            else if (NUMBER.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.TelecommunicationNumber));
            }

            return result;
        }
    }

    @Override
    public List<PartyContactMechanism> getAllBeans() throws Exception
    {
        return null;
    }
}
