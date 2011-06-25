package com.fbs.web.vaadin.ui.user.party;

import java.util.List;

import com.fbs.dmr.universal.model.party.PartyRelationship;
import com.fbs.dmr.universal.model.party.PartyRelationshipStatusType;
import com.fbs.dmr.universal.model.party.PartyRelationshipType;
import com.fbs.dmr.universal.model.party.PartyRole;
import com.fbs.dmr.universal.model.party.PriorityType;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class PartyRelationshipScreen extends ItemsListScreen<PartyRelationship>
{
	private static final long serialVersionUID = 1L;
	
	private static final String COMMENT = "comment";
	private static final String DATE_FROM = "dateFrom";
	private static final String DATE_TO = "dateTo";
	private static final String ID = "id";

	private static final String PRIORITY_TYPE = "priorityType";
	private static final String PRIORITY_TYPE_DESCRIPTION = "priorityType.description";
	
	private static final String RELTYPE = "partyRelationshipType";
	private static final String RELTYPE_NAME = "partyRelationshipType.name";


	private static final String STATUS = "partyRelationshipStatusType";
	private static final String STATUS_DESCRIPTION = "partyRelationshipStatusType.description";

	private static final String[] NESTED_PROPERTIES = new String[]
		{ RELTYPE_NAME, STATUS_DESCRIPTION, PRIORITY_TYPE_DESCRIPTION };

	private static final String[] VISIBLE_COLUMNS = new String[]
		{ ID, RELTYPE_NAME, STATUS_DESCRIPTION };

	private static final String[] VISIBLE_FIELDS = new String[]
		{ ID, COMMENT, RELTYPE, STATUS, PRIORITY_TYPE };

	public PartyRelationshipScreen(MyVaadinApplication app)
	{
		super(app, PartyRelationship.class, VISIBLE_COLUMNS, VISIBLE_FIELDS, NESTED_PROPERTIES);
	}

	@Override
	protected PartyRelationship createBeanInstance()
	{
		PartyRelationship result = new PartyRelationship();

		return result;
	}

	@Override
	protected List<PartyRelationship> getAllBeans() throws Exception
	{
		return this.app.getServices().getCrudServicePartyRelationship().findAll();
	}

	@Override
	protected PartyRelationship createBean(PartyRelationship t) throws Exception
	{
		this.app.getServices().getCrudServicePartyRelationship().create(t);

		return t;
	}

	@Override
	protected void updateBean(PartyRelationship t) throws Exception
	{
		//this.app.getServices().getCrudServicePartyRelationship().update(t);
	}

	@Override
	protected PartyRelationship readBean(PartyRelationship t) throws Exception
	{
		return this.app.getServices().getCrudServicePartyRelationship().read(t.getId());
	}

	@Override
	protected void deleteBean(PartyRelationship t) throws Exception
	{
		this.app.getServices().getCrudServicePartyRelationship().delete(t.getId());
	}

	@Override
	protected String getColumnName(String propertyId)
	{
		if (propertyId.equals(ID))
			return this.app.getMessage(ApplicationMessages.PartyRelationshipId);

		if (propertyId.equals(RELTYPE_NAME))
			return this.app.getMessage(ApplicationMessages.PartyRelationshipTypeTitle);
/*
		if (propertyId.equals(PARTY_FROM_NAME))
			return this.app.getMessage(ApplicationMessages.PartyRelationshipPartyFrom);

		if (propertyId.equals(ROLE_FROM_DESCRIPTION))
			return this.app.getMessage(ApplicationMessages.PartyRelationshipRoleFrom);

		if (propertyId.equals(PARTY_TO_NAME))
			return this.app.getMessage(ApplicationMessages.PartyRelationshipPartyTo);

		if (propertyId.equals(ROLE_TO_DESCRIPTION))
			return this.app.getMessage(ApplicationMessages.PartyRelationshipRoleTo);
*/
		if (propertyId.equals(STATUS_DESCRIPTION))
			return this.app.getMessage(ApplicationMessages.PartyRelationshipStatus);

		if (propertyId.equals(PRIORITY_TYPE_DESCRIPTION))
			return this.app.getMessage(ApplicationMessages.PartyRelationshipPriority);

		return propertyId;
	}

	@Override
	protected FormFieldFactory getFormFieldFactory()
	{
		return new PartyRelationshipFormFieldFactory(this.app);
	}

	private static class PartyRelationshipFormFieldFactory implements FormFieldFactory
	{
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;

		public PartyRelationshipFormFieldFactory(MyVaadinApplication app)
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
				result = new TextField(this.app.getMessage(ApplicationMessages.PartyRelationshipId));

				result.setReadOnly(true);
			}
			else if (COMMENT.equals(pid))
			{
				TextArea textArea;

				textArea = new TextArea(this.app.getMessage(ApplicationMessages.PartyRelationshipComment));
				textArea.setRows(5);
				textArea.setColumns(20);
				textArea.setImmediate(true);

				result = textArea;
			}
			else if (RELTYPE.equals(pid))
			{
				BeanItemContainer<PartyRelationshipType> container;
				List<PartyRelationshipType> partyRelationshipTypes;
				Select select;
				String caption;

				partyRelationshipTypes = this.app.getServices().getCrudServicePartyRelationshipType().findAll();
				container = new BeanItemContainer<PartyRelationshipType>(PartyRelationshipType.class,
				        partyRelationshipTypes);

				caption = this.app.getMessage(ApplicationMessages.PartyRelationshipTypeTitle);

				select = new Select(caption, container);
				select.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
				select.setItemCaptionPropertyId("name");

				result = select;
			}
			/*
			else if (ROLE_FROM.equals(pid) || ROLE_TO.equals(pid))
			{
				Select select;
				String caption;
				BeanItemContainer<PartyRole> container;
				List<PartyRole> partyRoles;

				partyRoles = this.app.getServices().getCrudServicePartyRole().findAll();
				container = new BeanItemContainer<PartyRole>(PartyRole.class, partyRoles);
				container.addNestedContainerProperty("partyRoleType.description");

				if (ROLE_FROM.equals(pid))
				{
					caption = this.app.getMessage(ApplicationMessages.PartyRelationshipRoleFrom);
				}
				else
				{
					caption = this.app.getMessage(ApplicationMessages.PartyRelationshipRoleTo);
				}

				select = new Select(caption, container);
				select.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
				select.setItemCaptionPropertyId("partyRoleType.description");

				result = select;
			}
			else if (PARTY_FROM.equals(pid) || PARTY_TO.equals(pid))
			{
				Select select;
				String caption;
				BeanItemContainer<PartyRole> container;
				List<PartyRole> partyRoles;

				partyRoles = this.app.getServices().getCrudServicePartyRole().findAll();
				container = new BeanItemContainer<PartyRole>(PartyRole.class, partyRoles);
				container.addNestedContainerProperty("party.name");

				if (PARTY_FROM.equals(pid))
				{
					caption = this.app.getMessage(ApplicationMessages.PartyRelationshipPartyFrom);

				}
				else
				{
					caption = this.app.getMessage(ApplicationMessages.PartyRelationshipPartyTo);
				}

				select = new Select(caption, container);
				select.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
				select.setItemCaptionPropertyId("party.name");

				result = select;
			}
			*/
			else if (DATE_FROM.equals(pid) || DATE_TO.equals(pid))
			{
				DateField date;
				String caption;

				if (DATE_FROM.equals(pid))
				{
					caption = this.app.getMessage(ApplicationMessages.PartyRelationshipDateFrom);
				}
				else
				{
					caption = this.app.getMessage(ApplicationMessages.PartyRelationshipDateTo);
				}

				date = new DateField(caption);
				date.setResolution(DateField.RESOLUTION_DAY);

				result = date;
			}
			else if (STATUS.equals(pid))
			{
				Select select;
				String caption;
				BeanItemContainer<PartyRelationshipStatusType> container;
				List<PartyRelationshipStatusType> stati;

				caption = this.app.getMessage(ApplicationMessages.PartyRelationshipStatus);

				stati = this.app.getServices().getCrudServicePartyRelationshipStatusType().findAll();
				container = new BeanItemContainer<PartyRelationshipStatusType>(PartyRelationshipStatusType.class, stati);

				select = new Select(caption, container);
				select.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
				select.setItemCaptionPropertyId("description");

				result = select;
			}
			else if (PRIORITY_TYPE.equals(pid))
			{
				Select select;
				String caption;
				BeanItemContainer<PriorityType> container;
				List<PriorityType> priorityTypes;

				caption = this.app.getMessage(ApplicationMessages.PartyRelationshipPriority);

				priorityTypes = this.app.getServices().getCrudServicePriorityType().findAll();
				container = new BeanItemContainer<PriorityType>(PriorityType.class, priorityTypes);

				select = new Select(caption, container);
				select.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
				select.setItemCaptionPropertyId("description");

				result = select;
			}

			return result;
		}
	}
}
