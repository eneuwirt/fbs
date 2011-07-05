package com.fbs.web.vaadin.ui.user.party;

import java.util.List;

import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.fbs.web.vaadin.ui.common.items.DetailsComponentSimple;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class PartyTypeScreen extends ItemsListScreen<PartyType>
{
    private static final long serialVersionUID = 1L;
    private static final String ID = "id";
    private static final String DESC = "description";
    private static final String[] VISIBLE_COLUMNS = new String[]
    { ID, DESC };
    private static final String[] VISIBLE_FIELDS = new String[]
    { ID, DESC };

    public PartyTypeScreen(MyVaadinApplication app)
    {
        super(app, PartyType.class, new DetailsComponentSimple<PartyType>(app, new PartyTypeFormFieldFactory(app), VISIBLE_FIELDS),
                VISIBLE_COLUMNS);
    }

    @Override
    public PartyType createBeanInstance()
    {
        PartyType partyType;

        partyType = new PartyType();

        return partyType;
    }

    @Override
    public List<PartyType> getAllBeans() throws Exception
    {
        List<PartyType> partyTypes = this.services.getCrudServicePartyType().findAll();

        return partyTypes;
    }

    @Override
    public PartyType createBean(PartyType t) throws Exception
    {
        this.services.getCrudServicePartyType().create(t);

        return t;
    }

    @Override
    public void updateBean(PartyType t) throws Exception
    {
        this.services.getCrudServicePartyType().update(t);
    }

    @Override
    public PartyType readBean(PartyType t) throws Exception
    {
        PartyType result;

        result = this.services.getCrudServicePartyType().read(t.getId());

        return result;
    }

    @Override
    public void deleteBean(PartyType t) throws Exception
    {
        this.services.getCrudServicePartyType().delete(t.getId());
    }

    @Override
    public String getColumnName(String propertyId)
    {
        if (propertyId.equals(ID))
            return this.app.getMessage(ApplicationMessages.PartyTypeId);

        if (propertyId.equals(DESC))
            return this.app.getMessage(ApplicationMessages.PartyTypeDescription);

        return propertyId;
    }

    private static class PartyTypeFormFieldFactory implements FormFieldFactory
    {
        private static final long serialVersionUID = 1L;
        private MyVaadinApplication app;

        public PartyTypeFormFieldFactory(MyVaadinApplication app)
        {
            this.app = app;
        }

        @Override
        public Field createField(Item item, Object propertyId, Component uiContext)
        {
            // Identify the fields by their Property ID.
            TextField result = null;

            String pid = (String) propertyId;

            if (ID.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.PartyTypeId));

                result.setReadOnly(true);
            }
            else if (DESC.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.PartyTypeDescription));
            }

            return result;
        }
    }
}
