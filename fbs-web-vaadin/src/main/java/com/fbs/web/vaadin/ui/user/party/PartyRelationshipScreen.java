package com.fbs.web.vaadin.ui.user.party;

import java.util.ArrayList;
import java.util.List;

import com.fbs.dmr.universal.model.party.PartyRelationship;
import com.fbs.dmr.universal.model.party.PartyRelationshipType;
import com.fbs.web.dto.DtoPartyRelationship;
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

public class PartyRelationshipScreen extends ItemsListScreen<DtoPartyRelationship>
{
	private static final long serialVersionUID = 1L;
	private static final String COL_ID = "id";
	private static final String COL_COMMENT = "comment";
	private static final String COL_REL_TYPE = "partyRelationshipType";
	private static final String COL_REL_TYPE_NAME = "partyRelationshipTypeName";
	private static final String COL_DATE_FROM = "dateFrom";
	private static final String COL_DATE_TO = "dateTo";
	private static final String[] VISIBLE_COLUMNS = new String[] { COL_ID, COL_REL_TYPE_NAME };
	private static final String[] VISIBLE_ITEM_PROPERTIES = new String[] { COL_ID, COL_COMMENT, COL_REL_TYPE,
	        COL_DATE_FROM, COL_DATE_TO };


	public PartyRelationshipScreen(MyVaadinApplication app)
	{
		super(app, DtoPartyRelationship.class, VISIBLE_COLUMNS, VISIBLE_ITEM_PROPERTIES);
	}


	@Override
	protected DtoPartyRelationship createBeanInstance()
	{
		DtoPartyRelationship result = new DtoPartyRelationship();

		return result;
	}


	@Override
	protected List<DtoPartyRelationship> getAllBeans() throws Exception
	{
		List<DtoPartyRelationship> result = new ArrayList<DtoPartyRelationship>();
		List<PartyRelationship> partyRelationships;

		partyRelationships = this.app.getServices().getCrudServicePartyRelationship().findAll();
		for (PartyRelationship partyRelationship : partyRelationships)
		{
			DtoPartyRelationship dto;

			dto = new DtoPartyRelationship(partyRelationship);

			result.add(dto);
		}

		return result;
	}


	@Override
	protected DtoPartyRelationship createBean(DtoPartyRelationship t) throws Exception
	{
		PartyRelationship partyRelationship;
		
		partyRelationship = new PartyRelationship();
		partyRelationship.setComment(t.getComment());
		partyRelationship.setDateFrom(t.getDateFrom());
		partyRelationship.setDateTo(t.getDateTo());
		partyRelationship.setPartyRelationshipType(t.getPartyRelationshipType());
		
		this.app.getServices().getCrudServicePartyRelationship().create(partyRelationship);
		
		return t;
	}


	@Override
	protected void updateBean(DtoPartyRelationship t) throws Exception
	{
		throw new IllegalArgumentException("Not implemnted");
	}


	@Override
	protected DtoPartyRelationship readBean(DtoPartyRelationship t) throws Exception
	{
		DtoPartyRelationship result;
		PartyRelationship partyRelationship;

		partyRelationship = this.app.getServices().getCrudServicePartyRelationship().read(t.getId());

		result = new DtoPartyRelationship(partyRelationship);

		return result;
	}


	@Override
	protected void deleteBean(DtoPartyRelationship t) throws Exception
	{
		this.app.getServices().getCrudServicePartyRelationship().delete(t.getId());
	}


	@Override
	protected String getColumnName(String propertyId)
	{
		if (propertyId.equals(COL_ID))
			return this.app.getMessage(ApplicationMessages.PartyRelationshipId);

		if (propertyId.equals(COL_COMMENT))
			return this.app.getMessage(ApplicationMessages.PartyRelationshipComment);

		if (propertyId.equals(COL_REL_TYPE))
			return this.app.getMessage(ApplicationMessages.PartyRelationshipTypeTitle);

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

			if (COL_ID.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.PartyRelationshipId));

				result.setReadOnly(true);
			}
			else if (COL_COMMENT.equals(pid))
			{
				TextArea textArea;

				textArea = new TextArea(this.app.getMessage(ApplicationMessages.PartyRelationshipComment));
				textArea.setRows(5);
				textArea.setColumns(20);
				textArea.setImmediate(true);

				result = textArea;
			}
			else if (COL_REL_TYPE.equals(pid))
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
			else if (COL_DATE_FROM.equals(pid) || COL_DATE_TO.equals(pid))
			{
				DateField date;
				String caption;
				
				if (COL_DATE_FROM.equals(pid))
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

			return result;
		}
	}
}
