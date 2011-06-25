package com.fbs.web.vaadin.ui.user.party;

import java.util.ArrayList;
import java.util.List;

import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyRelationship;
import com.fbs.dmr.universal.model.party.PartyRelationshipStatusType;
import com.fbs.dmr.universal.model.party.PartyRelationshipType;
import com.fbs.dmr.universal.model.party.PartyRole;
import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.model.party.PriorityType;
import com.fbs.web.dto.PartyRelationshipDto;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class PartyRelationshipScreen extends ItemsListScreen<PartyRelationshipDto>
{
	private static final long serialVersionUID = 1L;
	private static final String ID = "id";
	private static final String COMMENT = "comment";

	private static final String PRIORITY_TYPE = "priorityType";
	private static final String PRIORITY_TYPE_DESCRIPTION = "priorityType.description";

	private static final String RELTYPE = "partyRelationshipType";
	private static final String RELTYPE_NAME = "partyRelationshipType.name";

	private static final String STATUS = "partyRelationshipStatusType";
	private static final String STATUS_DESCRIPTION = "partyRelationshipStatusType.description";

	private static final String PARTY_FROM = "partyFrom";
	private static final String PARTY_FROM_NAME = "partyFrom.name";

	private static final String PARTY_TO = "partyTo";
	private static final String PARTY_TO_NAME = "partyTo.name";

	private static final String[] VISIBLE_COLUMNS = new String[]
		{ ID, PARTY_FROM_NAME, RELTYPE_NAME, PARTY_TO_NAME };

	// RELTYPE muss hinter den PARTY_FROM und PARTY_TO stehen. 
	private static final String[] VISIBLE_FIELDS = new String[]
		{ ID, PARTY_FROM, PARTY_TO, RELTYPE, STATUS, COMMENT, PRIORITY_TYPE };

	private static final String[] NESTED_PROPERTIES = new String[]
		{ PRIORITY_TYPE_DESCRIPTION, RELTYPE_NAME, STATUS_DESCRIPTION, PARTY_FROM_NAME, PARTY_TO_NAME };

	public PartyRelationshipScreen(MyVaadinApplication app)
	{
		super(app, PartyRelationshipDto.class, VISIBLE_COLUMNS, VISIBLE_FIELDS, NESTED_PROPERTIES);
	}

	@Override
	protected PartyRelationshipDto createBeanInstance()
	{
		return new PartyRelationshipDto();
	}

	@Override
	protected List<PartyRelationshipDto> getAllBeans() throws Exception
	{
		List<PartyRelationshipDto> result = new ArrayList<PartyRelationshipDto>();
		List<PartyRelationship> partyRelationships;

		partyRelationships = this.app.getServices().getCrudServicePartyRelationship().findAll();

		for (PartyRelationship p : partyRelationships)
		{
			PartyRelationshipDto dto;

			dto = new PartyRelationshipDto(p);

			result.add(dto);
		}

		return result;
	}

	@Override
	protected PartyRelationshipDto createBean(PartyRelationshipDto t) throws Exception
	{
		PartyRelationship p;
		Party partyFrom;
		Party partyTo;
		PartyRole partyRoleFrom;
		PartyRole partyRoleTo;
		String partyRoleTypeDescription;

		p = new PartyRelationship();

		p.setPartyRelationshipStatusType(t.getPartyRelationshipStatusType());
		p.setPartyRelationshipType(t.getPartyRelationshipType());
		p.setComment(t.getComment());

		partyFrom = t.getPartyFrom();
		partyRoleTypeDescription = p.getPartyRelationshipType().getPartyRoleTypeFrom().getDescription();
		partyRoleFrom = this.app.getServices().getCrudServicePartyRole()
		        .findByPartyRoleType(partyFrom.getId(), partyRoleTypeDescription);
		p.setPartyRoleFrom(partyRoleFrom);

		partyTo = t.getPartyTo();
		partyRoleTypeDescription = p.getPartyRelationshipType().getPartyRoleTypeTo().getDescription();
		partyRoleTo = this.app.getServices().getCrudServicePartyRole()
		        .findByPartyRoleType(partyTo.getId(), partyRoleTypeDescription);
		p.setPartyRoleTo(partyRoleTo);

		this.app.getServices().getCrudServicePartyRelationship().create(p);

		return t;
	}

	@Override
	protected void updateBean(PartyRelationshipDto t) throws Exception
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected PartyRelationshipDto readBean(PartyRelationshipDto t) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void deleteBean(PartyRelationshipDto t) throws Exception
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String getColumnName(String pid)
	{
		if (pid.equals(ID))
			return this.app.getMessage(ApplicationMessages.PartyRelationshipId);
		
		if (pid.equals(PARTY_FROM_NAME))
		{
			return this.app.getMessage(ApplicationMessages.PartyRelationshipPartyFrom);
		}
		
		if (pid.equals(PARTY_TO_NAME))
		{
			return this.app.getMessage(ApplicationMessages.PartyRelationshipPartyTo);
		}
		
		if (pid.equals(RELTYPE_NAME))
		{
			return this.app.getMessage(ApplicationMessages.PartyRelationshipTitle);
		}

		return pid;
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
				final Select select;
				String caption;
				final Form form = (Form) uiContext;

				partyRelationshipTypes = this.app.getServices().getCrudServicePartyRelationshipType().findAll();
				container = new BeanItemContainer<PartyRelationshipType>(PartyRelationshipType.class,
				        partyRelationshipTypes);

				caption = this.app.getMessage(ApplicationMessages.PartyRelationshipTypeTitle);

				select = new Select(caption, container);
				select.setNullSelectionAllowed(false);
				select.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
				select.setItemCaptionPropertyId("name");

				select.addListener(new ValueChangeListener()
				{
					private static final long serialVersionUID = 1L;

					public void valueChange(ValueChangeEvent event)
					{
						PartyRelationshipType bean = null;
						PartyRoleType partyRoleType;
						List<Party> parties;
						BeanItemContainer<Party> container;
						Select partySelect;
						Party party;

						bean = (PartyRelationshipType) select.getValue();
						if (bean == null)
							return;

						partyRoleType = bean.getPartyRoleTypeFrom();
						parties = app.getServices().getCrudServicePartyRole()
						        .findByPartyRoleType(partyRoleType.getDescription());
						container = new BeanItemContainer<Party>(Party.class, parties);

						partySelect = (Select) form.getField(PARTY_FROM);
						// Notify the selected element
						party = (Party) partySelect.getValue();
						partySelect.removeAllItems();
						partySelect.setContainerDataSource(container);
						partySelect.setEnabled(true);
						// restore the selection
						partySelect.setValue(party);

						partyRoleType = bean.getPartyRoleTypeTo();
						parties = app.getServices().getCrudServicePartyRole()
						        .findByPartyRoleType(partyRoleType.getDescription());
						container = new BeanItemContainer<Party>(Party.class, parties);

						partySelect = (Select) form.getField(PARTY_TO);
						// Notify the selected element
						party = (Party) partySelect.getValue();						
						partySelect.removeAllItems();
						partySelect.setContainerDataSource(container);
						partySelect.setEnabled(true);
						// restore the selection
						partySelect.setValue(party);
					}
				});

				result = select;
			}
			else if (PARTY_FROM.equals(pid) || PARTY_TO.equals(pid))
			{
				Select select;
				String caption;

				if (PARTY_FROM.equals(pid))
				{
					caption = this.app.getMessage(ApplicationMessages.PartyRelationshipPartyFrom);

				}
				else
				{
					caption = this.app.getMessage(ApplicationMessages.PartyRelationshipPartyTo);
				}

				select = new Select(caption);
				select.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
				select.setItemCaptionPropertyId("name");
				// Select a relationshiptype first
				select.setEnabled(false);

				result = select;
			}
			/*
			 * else if (DATE_FROM.equals(pid) || DATE_TO.equals(pid)) {
			 * DateField date; String caption;
			 * 
			 * if (DATE_FROM.equals(pid)) { caption =
			 * this.app.getMessage(ApplicationMessages
			 * .PartyRelationshipDateFrom); } else { caption =
			 * this.app.getMessage(ApplicationMessages.PartyRelationshipDateTo);
			 * }
			 * 
			 * date = new DateField(caption);
			 * date.setResolution(DateField.RESOLUTION_DAY);
			 * 
			 * result = date; }
			 */
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
