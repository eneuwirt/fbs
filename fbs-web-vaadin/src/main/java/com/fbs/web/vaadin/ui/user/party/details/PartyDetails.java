package com.fbs.web.vaadin.ui.user.party.details;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyClassification;
import com.fbs.dmr.universal.model.party.PartyRole;
import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.items.BeanAware;
import com.fbs.web.vaadin.ui.common.items.DetailsAware;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TabSheet;

public class PartyDetails<T extends Party> extends TabSheet implements DetailsAware<T>, BeanAware<T>
{
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(PartyDetails.class.getName());
    protected MyVaadinApplication app;
    
    protected String[] visibleFields;

    protected T bean;
    protected Form form;

    protected OptionGroup rolesGroup;
    protected OptionGroup classificationsGroup;
    //
    private PartyContactMechanismView partyContactMechanismView;

    public PartyDetails(MyVaadinApplication app, FormFieldFactory formFieldFactory, String[] visibleFields)
    {
        // A layout structure used for composition
        this.app = app;
        this.visibleFields = visibleFields;
        
        this.form = new Form();
        this.form.setImmediate(true);
        this.form.setFormFieldFactory(formFieldFactory);
        
        this.createComponents();

        this.setSizeFull();
        
        this.form.setSizeFull();
        this.classificationsGroup.setSizeFull();
        this.rolesGroup.setSizeFull();       
    }

    @Override
    public void setBean(T party)
    {
        BeanItem<T> beanItem;

        if (party == null)
        {
            return;
        }

        this.bean = party;
        beanItem = new BeanItem<T>(party);

        this.form.setItemDataSource(beanItem, Arrays.asList(this.visibleFields));

        this.resetOptionGroupClassification(party);
        this.resetOptionGroupRoles(party);

        this.partyContactMechanismView.setAnchor(party);
    }

    @Override
    public T getBean()
    {
        return this.bean;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createBeanDetails(T party)
    {
        Set<String> selectedRoles;
        Set<String> selectedClassiffcations;

        logger.info(">createBean");

        selectedRoles = (Set<String>) this.rolesGroup.getValue();

        for (String selectedRole : selectedRoles)
        {
            PartyRoleType partyRoleType;
            PartyRole partyRole;

            partyRoleType = this.app.getServices().getCrudServicePartyRoleType().findForDescription(selectedRole);

            partyRole = new PartyRole();
            partyRole.setParty(party);
            partyRole.setPartyRoleType(partyRoleType);

            this.app.getServices().getCrudServicePartyRole().create(partyRole);
        }

        selectedClassiffcations = (Set<String>) this.classificationsGroup.getValue();

        for (String selectedClassiffcation : selectedClassiffcations)
        {
            PartyType partyType;
            PartyClassification partyClassification;

            partyType = this.app.getServices().getCrudServicePartyType().findForDescription(selectedClassiffcation);

            partyClassification = new PartyClassification();
            partyClassification.setParty(party);
            partyClassification.setPartyType(partyType);

            this.app.getServices().getCrudServicePartyClassification().create(partyClassification);
        }

        logger.info("<createBean");
    }

    @Override
    public void readBeanDetails(T t)
    {

    }

    @SuppressWarnings("unchecked")
    @Override
    public void updateBeanDetails(T party)
    {
        List<PartyRole> partyRoles;
        List<PartyClassification> classifications;
        Set<String> selectedRoles;
        Set<String> selectedClassifications;

        logger.info(">updateBean");

        selectedRoles = (Set<String>) this.rolesGroup.getValue();

        // remove all unselected roles
        partyRoles = this.app.getServices().getCrudServicePartyRole().findByParty(party.getId());
        for (PartyRole partyRole : partyRoles)
        {
            if (!selectedRoles.contains(partyRole.getPartyRoleType().getDescription()))
            {
                this.app.getServices().getCrudServicePartyRole().delete(partyRole.getId());
            }
        }

        // add all selected items to the party
        for (String selectedRole : selectedRoles)
        {
            PartyRole partyRole;

            partyRole = this.app.getServices().getCrudServicePartyRole().findByPartyAndPartyRoleType(party.getId(), selectedRole);
            if (partyRole == null)
            {
                PartyRoleType partyRoleType;

                partyRoleType = this.app.getServices().getCrudServicePartyRoleType().findForDescription(selectedRole);

                partyRole = new PartyRole();

                partyRole.setParty(party);
                partyRole.setPartyRoleType(partyRoleType);

                this.app.getServices().getCrudServicePartyRole().create(partyRole);
            }
            else
            {
                this.app.getServices().getCrudServicePartyRole().update(partyRole);
            }
        }

        selectedClassifications = (Set<String>) this.classificationsGroup.getValue();
        // remove all unselected classifications
        classifications = this.app.getServices().getCrudServicePartyClassification().findByParty(party.getId());
        for (PartyClassification classification : classifications)
        {
            if (!selectedClassifications.contains(classification.getPartyType().getDescription()))
            {
                this.app.getServices().getCrudServicePartyClassification().delete(classification.getId());
            }
        }
        // add selected items
        for (String selectedClass : selectedClassifications)
        {
            PartyClassification partyClass;

            partyClass = this.app.getServices().getCrudServicePartyClassification().findByPartyType(party.getId(), selectedClass);

            if (partyClass == null)
            {
                PartyType partyType;

                partyType = this.app.getServices().getCrudServicePartyType().findForDescription(selectedClass);

                partyClass = new PartyClassification();
                partyClass.setParty(party);
                partyClass.setPartyType(partyType);

                this.app.getServices().getCrudServicePartyClassification().create(partyClass);
            }
            else
            {
                this.app.getServices().getCrudServicePartyClassification().update(partyClass);
            }
        }
    }

    @Override
    public void deleteBeanDetails(T t)
    {

    }

    private void resetOptionGroupRoles(Party party)
    {
        List<PartyRoleType> partyRoleTypes;

        partyRoleTypes = this.app.getServices().getCrudServicePartyRoleType().findAll();
        for (PartyRoleType partyRoleType : partyRoleTypes)
        {
            this.rolesGroup.addItem(partyRoleType.getDescription());
        }

        // set selection
        this.rolesGroup.setValue(null);
        if (party != null)
        {
            List<PartyRole> partyRoles;
            partyRoles = this.app.getServices().getCrudServicePartyRole().findByParty(party.getId());

            for (PartyRole p : partyRoles)
            {
                this.rolesGroup.select(p.getPartyRoleType().getDescription());
            }
        }
    }

    private void resetOptionGroupClassification(Party party)
    {
        List<PartyType> partyTypes;
        BeanItemContainer<String> container;

        partyTypes = this.app.getServices().getCrudServicePartyType().findAll();
        container = new BeanItemContainer<String>(String.class);
        for (PartyType t : partyTypes)
        {
            container.addBean(t.getDescription());
        }
        this.classificationsGroup.setContainerDataSource(container);

        // set selection
        this.classificationsGroup.setValue(null);
        if (party != null)
        {
            List<PartyClassification> classifications;

            classifications = this.app.getServices().getCrudServicePartyClassification().findByParty(party.getId());
            for (PartyClassification p : classifications)
            {
                this.classificationsGroup.select(p.getPartyType().getDescription());
            }
        }
    }

    private void createComponents()
    {
        String captionMasterData;
        String captionClass;
        String captionRoles;
        String captionRelations;
        String captionPartyContacts;

        logger.info(">getComponent");

        captionMasterData = this.app.getMessage(ApplicationMessages.PartyMasterData);
        captionClass = this.app.getMessage(ApplicationMessages.PartyTypeClassificationTitle);
        captionRoles = this.app.getMessage(ApplicationMessages.PartyRoleTitle);
        captionRelations = this.app.getMessage(ApplicationMessages.PartyRelationships);
        captionPartyContacts = "Kontakte";
        
        this.partyContactMechanismView = new PartyContactMechanismView(this.app);

        this.classificationsGroup = new OptionGroup();
        this.classificationsGroup.setMultiSelect(true);
        this.classificationsGroup.setNullSelectionAllowed(true);
        this.classificationsGroup.setImmediate(true);
        
        this.rolesGroup = new OptionGroup();
        this.rolesGroup.setMultiSelect(true);
        this.rolesGroup.setNullSelectionAllowed(true);
        this.rolesGroup.setImmediate(true);

        this.addTab(this.form, captionMasterData, null);
        
        this.addTab(partyContactMechanismView, captionPartyContacts, null);
        this.addTab(new Label("Beziehungen zwischen mir und den andren"), captionRelations, null);
        this.addTab(this.rolesGroup, captionRoles, null);
        this.addTab(this.classificationsGroup, captionClass, null);
    }
}
