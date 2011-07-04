package com.fbs.web.vaadin.ui.user.party;

import java.util.List;

import com.fbs.web.dto.PartyRelationshipDto;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.ui.common.ItemsListView;
import com.vaadin.ui.FormFieldFactory;

public class PartyRelationshipView extends ItemsListView<PartyRelationshipDto>
{
    private static final long serialVersionUID = 1L;
    private static final String ID = "id";

    private static final String[] VISIBLE_COLUMNS =
    { ID };
    private static final String[] VISIBLE_FIELDS =
    { ID };

    public PartyRelationshipView(MyVaadinApplication app)
    {
        super(app, PartyRelationshipDto.class, VISIBLE_COLUMNS, VISIBLE_FIELDS);
    }

    @Override
    public PartyRelationshipDto createBeanInstance()
    {
        return new PartyRelationshipDto();
    }

    @Override
    public List<PartyRelationshipDto> getAllBeans() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PartyRelationshipDto createBean(PartyRelationshipDto t) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateBean(PartyRelationshipDto t) throws Exception
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public PartyRelationshipDto readBean(PartyRelationshipDto t) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteBean(PartyRelationshipDto t) throws Exception
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public FormFieldFactory getFormFieldFactory()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getColumnName(String pid)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateComponents(Object obj)
    {
        // TODO Auto-generated method stub
        
    }  
}