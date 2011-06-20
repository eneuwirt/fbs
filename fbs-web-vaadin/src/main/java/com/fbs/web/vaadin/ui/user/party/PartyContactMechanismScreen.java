package com.fbs.web.vaadin.ui.user.party;

import java.util.List;

import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyContactMechanism;
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

public class PartyContactMechanismScreen extends ItemsListScreen<PartyContactMechanism>
{
	private static final long serialVersionUID = 1L;

	private static final String ID = "id";
	private static final String COMMENT = "comment";
	private static final String CONTACT_MECHANISM = "contactMechanism";
	private static final String CONTACT_MECHANISM_ADDRESS = "contactMechanism.address";
	private static final String CONTACT_MECHANISM_TYPE_DESCR = "contactMechanism.contactMechanismType.description";
	private static final String PARTY = "party";
	private static final String PARTY_NAME = "party.name";
	private static final String[] VISIBLE_COLUMNS = new String[]
		{ ID, PARTY_NAME, CONTACT_MECHANISM_ADDRESS, CONTACT_MECHANISM_TYPE_DESCR };
	private static final String[] VISIBLE_FIELDS = new String[]
		{ ID, PARTY, CONTACT_MECHANISM, COMMENT };
	private static final String[] NESTED_PROPERTIES = new String[]
		{ PARTY_NAME, CONTACT_MECHANISM_ADDRESS, CONTACT_MECHANISM_TYPE_DESCR };

	public PartyContactMechanismScreen(MyVaadinApplication app)
	{
		super(app, PartyContactMechanism.class, VISIBLE_COLUMNS, VISIBLE_FIELDS, NESTED_PROPERTIES);
	}

	@Override
	protected PartyContactMechanism createBeanInstance()
	{
		return new PartyContactMechanism();
	}

	@Override
	protected List<PartyContactMechanism> getAllBeans() throws Exception
	{
		return this.app.getServices().getCrudServicePartyContactMechanism().findAll();
	}

	@Override
	protected PartyContactMechanism createBean(PartyContactMechanism t) throws Exception
	{
		this.app.getServices().getCrudServicePartyContactMechanism().create(t);

		return t;
	}

	@Override
	protected void updateBean(PartyContactMechanism t) throws Exception
	{
		this.app.getServices().getCrudServicePartyContactMechanism().update(t);
	}

	@Override
	protected PartyContactMechanism readBean(PartyContactMechanism t) throws Exception
	{
		PartyContactMechanism result;

		result = this.app.getServices().getCrudServicePartyContactMechanism().read(t.getId());

		return result;
	}

	@Override
	protected void deleteBean(PartyContactMechanism t) throws Exception
	{
		this.app.getServices().getCrudServicePartyContactMechanism().delete(t.getId());
	}

	@Override
	protected FormFieldFactory getFormFieldFactory()
	{
		return new PartyContactMechanismFormFieldFactory(this.app);
	}

	@Override
	protected String getColumnName(String propertyId)
	{
		if (propertyId.equals(ID))
			return this.app.getMessage(ApplicationMessages.PartyContactMechanismId);

		if (propertyId.equals(COMMENT))
			return this.app.getMessage(ApplicationMessages.PartyContactMechanismComment);

		if (propertyId.equals(PARTY_NAME))
			return this.app.getMessage(ApplicationMessages.PartyContactMechanismParty);

		if (propertyId.equals(CONTACT_MECHANISM_ADDRESS))
			return this.app.getMessage(ApplicationMessages.PartyContactMechanismAddress);

		if (propertyId.equals(CONTACT_MECHANISM_TYPE_DESCR))
			return this.app.getMessage(ApplicationMessages.ContactMechanismTypeTitle);
		
		return propertyId;
	}

	private static class PartyContactMechanismFormFieldFactory implements FormFieldFactory
	{
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;

		public PartyContactMechanismFormFieldFactory(MyVaadinApplication app)
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
				result = new TextField(this.app.getMessage(ApplicationMessages.PartyContactMechanismId));

				result.setReadOnly(true);
			}
			else if (COMMENT.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.PartyContactMechanismComment));
			}
			else if (PARTY.equals(pid))
			{
				Select select;
				BeanItemContainer<Party> container;
				List<Party> parties;
				String caption;

				caption = this.app.getMessage(ApplicationMessages.PartyContactMechanismParty);

				parties = this.app.getServices().getCrudServiceParty().findAll();
				container = new BeanItemContainer<Party>(Party.class, parties);

				select = new Select(caption, container);

				select.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
				select.setItemCaptionPropertyId("name");

				result = select;
			}
			else if (CONTACT_MECHANISM.equals(pid))
			{

			}

			return result;
		}
	}

}
