package com.fbs.web.vaadin.ui.user.party.relship;

import java.util.List;

import com.fbs.dmr.universal.model.party.PartyRelationshipType;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.fbs.web.vaadin.ui.common.items.DetailsComponentSimple;

public class PartyRelationshipTypeScreen extends ItemsListScreen<PartyRelationshipType> implements RelationshipConstants
{
    private static final long serialVersionUID = 1L;
  
    private static final String[] VISIBLE_COLUMNS = new String[]
    { ID, NAME };
    private static final String[] VISIBLE_FIELDS = new String[]
    { ID, NAME, DESC, ROLETYPE_FROM, ROLETYPE_TO };

    public PartyRelationshipTypeScreen(MyVaadinApplication app)
    {
        super(app, PartyRelationshipType.class, new DetailsComponentSimple<PartyRelationshipType>(app,
                new PartyRelationshipTypeFormFieldFactory(app), VISIBLE_FIELDS), VISIBLE_COLUMNS);
    }

    @Override
    public PartyRelationshipType createBeanInstance()
    {
        return new PartyRelationshipType();
    }

    @Override
    public List<PartyRelationshipType> getAllBeans() throws Exception
    {
        return this.services.getCrudServicePartyRelationshipType().findAll();
    }

    @Override
    public PartyRelationshipType createBean(PartyRelationshipType t) throws Exception
    {
        this.services.getCrudServicePartyRelationshipType().create(t);

        return t;
    }

    @Override
    public void updateBean(PartyRelationshipType t) throws Exception
    {
        this.services.getCrudServicePartyRelationshipType().update(t);
    }

    @Override
    public PartyRelationshipType readBean(PartyRelationshipType t) throws Exception
    {
        return this.services.getCrudServicePartyRelationshipType().read(t.getId());
    }

    @Override
    public void deleteBean(PartyRelationshipType t) throws Exception
    {
        this.services.getCrudServicePartyRelationshipType().delete(t.getId());
    }

    @Override
    public String getColumnName(String propertyId)
    {
        if (propertyId.equals(ID))
            return this.app.getMessage(ApplicationMessages.PartyRelationshipTypeId);

        if (propertyId.equals(NAME))
            return this.app.getMessage(ApplicationMessages.PartyRelationshipTypeName);

        return propertyId;
    }

}
