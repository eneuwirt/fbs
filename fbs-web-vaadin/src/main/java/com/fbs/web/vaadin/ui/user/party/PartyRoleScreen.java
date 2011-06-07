package com.fbs.web.vaadin.ui.user.party;

import java.util.List;

import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyRole;
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
import com.vaadin.ui.TextField;

public class PartyRoleScreen extends ItemsListScreen<PartyRole>
{
	private static final long serialVersionUID = 1L;
	private static final String ID = "id";
	private static final String PARTY = "party";
	private static final String PARTY_NAME = "party.name";
	private static final String PARTY_ROLETYPE = "partyRoleType";
	private static final String PARTY_ROLETYPE_DESCR = "partyRoleType.description";
	private static final String[] NESTED_PROPERTIES = new String[] { PARTY_NAME, PARTY_ROLETYPE_DESCR };
	private static final String[] VISIBLE_COLUMNS = new String[] { ID, PARTY_NAME, PARTY_ROLETYPE_DESCR };
	private static final String[] VISIBLE_FIELDS = new String[] { ID, PARTY, PARTY_ROLETYPE };


	public PartyRoleScreen(MyVaadinApplication app)
	{
		super(app, PartyRole.class, VISIBLE_COLUMNS, VISIBLE_FIELDS, NESTED_PROPERTIES);
	}


	@Override
	protected PartyRole createBeanInstance()
	{
		return new PartyRole();
	}


	@Override
	protected List<PartyRole> getAllBeans() throws Exception
	{
		return this.app.getServices().getCrudServicePartyRole().findAll();
	}


	@Override
	protected PartyRole createBean(PartyRole t) throws Exception
	{
		this.app.getServices().getCrudServicePartyRole().create(t);

		return t;
	}


	@Override
	protected void updateBean(PartyRole t) throws Exception
	{
		this.app.getServices().getCrudServicePartyRole().update(t);
	}


	@Override
	protected PartyRole readBean(PartyRole t) throws Exception
	{
		return this.app.getServices().getCrudServicePartyRole().read(t.getId());
	}


	@Override
	protected void deleteBean(PartyRole t) throws Exception
	{
		this.app.getServices().getCrudServicePartyRole().delete(t.getId());
	}


	@Override
	protected String getColumnName(String propertyId)
	{
		if (propertyId.equals(ID))
			return this.app.getMessage(ApplicationMessages.PartyRoleId);

		if (propertyId.equals(PARTY_NAME))
			return this.app.getMessage(ApplicationMessages.PartyName);

		if (propertyId.equals(PARTY_ROLETYPE_DESCR))
			return this.app.getMessage(ApplicationMessages.PartyRoleTypeDescription);

		return propertyId;
	}


	@Override
	protected FormFieldFactory getFormFieldFactory()
	{
		return new PartyRoleFormFieldFactory(this.app);
	}

	private static class PartyRoleFormFieldFactory implements FormFieldFactory
	{
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;


		public PartyRoleFormFieldFactory(MyVaadinApplication app)
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
				result = new TextField(this.app.getMessage(ApplicationMessages.PartyRoleId));

				result.setReadOnly(true);
			}
			else if (PARTY.equals(pid))
			{
				Select select;
				BeanItemContainer<Party> container;
				List<Party> parties;
				String caption;

				caption = this.app.getMessage(ApplicationMessages.PartyRoleParty);

				parties = this.app.getServices().getCrudServiceParty().findAll();
				container = new BeanItemContainer<Party>(Party.class, parties);

				select = new Select(caption, container);

				select.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
				select.setItemCaptionPropertyId("name");
				result = select;
			}
			else if (PARTY_ROLETYPE.equals(pid))
			{
				Select select;
				BeanItemContainer<PartyRoleType> container;
				List<PartyRoleType> partyRoleTypes;
				String caption;

				caption = this.app.getMessage(ApplicationMessages.PartyRoleTypeTitle);
				
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
