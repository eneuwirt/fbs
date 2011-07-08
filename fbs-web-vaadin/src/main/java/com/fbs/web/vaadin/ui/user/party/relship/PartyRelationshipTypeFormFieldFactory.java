package com.fbs.web.vaadin.ui.user.party.relship;

import java.util.List;

import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

class PartyRelationshipTypeFormFieldFactory implements FormFieldFactory, RelationshipConstants
{
    private static final long serialVersionUID = 1L;
    private MyVaadinApplication app;

    public PartyRelationshipTypeFormFieldFactory(MyVaadinApplication app)
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
            result = new TextField(this.app.getMessage(ApplicationMessages.PartyRelationshipTypeId));

            result.setReadOnly(true);
        }
        else if (NAME.equals(pid))
        {
            result = new TextField(this.app.getMessage(ApplicationMessages.PartyRelationshipTypeName));
        }
        else if (DESC.equals(pid))
        {
            TextArea textArea;

            textArea = new TextArea(this.app.getMessage(ApplicationMessages.PartyRelationshipTypeDescription));
            textArea.setRows(5);
            textArea.setColumns(20);
            textArea.setImmediate(true);

            result = textArea;
        }
        else if (ROLETYPE_TO.equals(pid) || ROLETYPE_FROM.equals(pid))
        {
            BeanItemContainer<PartyRoleType> container;
            Select select;
            List<PartyRoleType> partyRoleTypes;
            String caption;

            if (ROLETYPE_TO.equals(pid))
            {
                caption = this.app.getMessage(ApplicationMessages.PartyRelationshipTypeRoleTypeTo);
            }
            else
            {
                caption = this.app.getMessage(ApplicationMessages.PartyRelationshipTypeRoleTypeFrom);
            }

            partyRoleTypes = this.app.getServices().getCrudServicePartyRoleType().findAll();

            container = new BeanItemContainer<PartyRoleType>(PartyRoleType.class, partyRoleTypes);

            select = new Select(caption, container);
            select.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
            select.setItemCaptionPropertyId("description");

            result = select;
        }

        return result;
    }
}