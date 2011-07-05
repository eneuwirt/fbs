package com.fbs.web.vaadin.ui.user.party;

import java.util.List;

import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.fbs.web.vaadin.ui.common.items.DetailsComponentSimple;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class PartyRoleTypeScreen extends ItemsListScreen<PartyRoleType>
{
    private static final long serialVersionUID = 1L;
    private static final String COL_ID = "id";
    private static final String COL_DESC = "description";
    private static final String[] VISIBLE_COLUMNS = new String[]
    { COL_ID, COL_DESC };
    private static final String[] VISIBLE_FIELDS = new String[]
    { COL_ID, COL_DESC };

    public PartyRoleTypeScreen(MyVaadinApplication app)
    {
        super(app, PartyRoleType.class, new DetailsComponentSimple<PartyRoleType>(app, new PartyRoleTypeFormFieldFactory(app),
                VISIBLE_FIELDS), VISIBLE_COLUMNS);
    }

    @Override
    public PartyRoleType createBeanInstance()
    {
        return new PartyRoleType();
    }

    @Override
    public List<PartyRoleType> getAllBeans() throws Exception
    {
        List<PartyRoleType> partyRoleTypes = this.services.getCrudServicePartyRoleType().findAll();

        return partyRoleTypes;
    }

    @Override
    public PartyRoleType createBean(PartyRoleType t) throws Exception
    {
        this.services.getCrudServicePartyRoleType().create(t);

        return t;
    }

    @Override
    public void updateBean(PartyRoleType t) throws Exception
    {
        this.services.getCrudServicePartyRoleType().update(t);
    }

    @Override
    public PartyRoleType readBean(PartyRoleType t) throws Exception
    {
        PartyRoleType result;

        result = this.services.getCrudServicePartyRoleType().read(t.getId());

        return result;
    }

    @Override
    public void deleteBean(PartyRoleType t) throws Exception
    {
        this.services.getCrudServicePartyRoleType().delete(t.getId());
    }

    @Override
    public String getColumnName(String propertyId)
    {
        if (propertyId.equals(COL_ID))
            return this.app.getMessage(ApplicationMessages.PartyRoleTypeId);

        if (propertyId.equals(COL_DESC))
            return this.app.getMessage(ApplicationMessages.PartyRoleTypeDescription);

        return propertyId;
    }

    private static class PartyRoleTypeFormFieldFactory implements FormFieldFactory
    {
        private static final long serialVersionUID = 1L;
        private MyVaadinApplication app;

        public PartyRoleTypeFormFieldFactory(MyVaadinApplication app)
        {
            this.app = app;
        }

        @Override
        public Field createField(Item item, Object propertyId, Component uiContext)
        {
            // Identify the fields by their Property ID.
            TextField result = null;

            String pid = (String) propertyId;

            if (COL_ID.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.PartyRoleTypeId));

                result.setReadOnly(true);
            }
            else if (COL_DESC.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.PartyRoleTypeDescription));
            }

            return result;
        }
    }
}
