package com.fbs.web.vaadin.ui.user.party;

import java.util.List;

import com.fbs.dmr.universal.model.party.PartyRelationshipType;
import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class PartyRelationshipTypeScreen extends ItemsListScreen<PartyRelationshipType>
{
	private static final long serialVersionUID = 1L;
	private static final String COL_ID = "id";
	private static final String COL_DESC = "description";
	private static final String COL_NAME = "name";
	private static final String COL_ROLETYPE_FROM = "partyRoleTypeFrom";
	private static final String COL_ROLETYPE_TO = "partyRoleTypeTo";
	private static final String[] VISIBLE_COLUMNS = new String[]
		{ COL_ID, COL_NAME };
	private static final String[] VISIBLE_ITEM_PROPERTIES = new String[]
		{ COL_ID, COL_NAME, COL_DESC, COL_ROLETYPE_FROM, COL_ROLETYPE_TO };

	public PartyRelationshipTypeScreen(MyVaadinApplication app)
	{
		super(app, PartyRelationshipType.class, null, VISIBLE_COLUMNS, VISIBLE_ITEM_PROPERTIES);
	}

	@Override
	protected PartyRelationshipType createBeanInstance()
	{
		return new PartyRelationshipType();
	}

	@Override
	protected List<PartyRelationshipType> getAllBeans() throws Exception
	{
		return this.services.getCrudServicePartyRelationshipType().findAll();
	}

	@Override
	protected PartyRelationshipType createBean(PartyRelationshipType t) throws Exception
	{
		this.services.getCrudServicePartyRelationshipType().create(t);

		return t;
	}

	@Override
	protected void updateBean(PartyRelationshipType t) throws Exception
	{
		this.services.getCrudServicePartyRelationshipType().update(t);
	}

	@Override
	protected PartyRelationshipType readBean(PartyRelationshipType t) throws Exception
	{
		return this.services.getCrudServicePartyRelationshipType().read(t.getId());
	}

	@Override
	protected void deleteBean(PartyRelationshipType t) throws Exception
	{
		this.services.getCrudServicePartyRelationshipType().delete(t.getId());
	}

	@Override
	protected FormFieldFactory getFormFieldFactory()
	{
		return new PartyRelationshipTypeFormFieldFactory(this.app);
	}

	private static class PartyRelationshipTypeFormFieldFactory implements FormFieldFactory
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

			if (COL_ID.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.PartyRelationshipTypeId));

				result.setReadOnly(true);
			}
			else if (COL_NAME.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.PartyRelationshipTypeName));
			}
			else if (COL_DESC.equals(pid))
			{
				TextArea textArea;
				
				textArea = new TextArea(this.app.getMessage(ApplicationMessages.PartyRelationshipTypeDescription));
				textArea.setRows(5);
				textArea.setColumns(20);
				textArea.setImmediate(true);
				
				result = textArea;
			}
			else if (COL_ROLETYPE_TO.equals(pid) || COL_ROLETYPE_FROM.equals(pid))
			{
				BeanItemContainer<PartyRoleType> container;
				Select select;
				List<PartyRoleType> partyRoleTypes;
				String caption;

				if (COL_ROLETYPE_TO.equals(pid))
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
}
