package com.fbs.web.vaadin.ui.user.party;

import java.util.List;

import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class PartyTypeScreen extends ItemsListScreen<PartyType>
{
	private static final long serialVersionUID = 1L;
	private static final String COL_ID = "id";
	private static final String COL_DESC = "description";
	private static final String[] VISIBLE_COLUMNS = new String[] { COL_ID, COL_DESC };
	private static final String[] VISIBLE_ITEM_PROPERTIES = new String[] { COL_ID, COL_DESC };


	public PartyTypeScreen(MyVaadinApplication app)
	{
		super(app, PartyType.class, null, VISIBLE_COLUMNS, VISIBLE_ITEM_PROPERTIES);
	}


	@Override
	protected PartyType createBeanInstance()
	{
		PartyType partyType;

		partyType = new PartyType();

		return partyType;
	}


	@Override
	protected List<PartyType> getAllBeans() throws Exception
	{
		List<PartyType> partyTypes = this.services.getCrudPartyTypeService().findAll();

		return partyTypes;
	}


	@Override
	protected PartyType createBean(PartyType t) throws Exception
	{
		this.services.getCrudPartyTypeService().create(t);

		return t;
	}


	@Override
	protected void updateBean(PartyType t) throws Exception
	{
		this.services.getCrudPartyTypeService().update(t);
	}


	@Override
	protected PartyType readBean(PartyType t) throws Exception
	{
		PartyType result;

		result = this.services.getCrudPartyTypeService().read(t.getId());

		return result;
	}


	@Override
	protected void deleteBean(PartyType t) throws Exception
	{
		this.services.getCrudPartyTypeService().delete(t.getId());
	}


	@Override
	protected FormFieldFactory getFormFieldFactory()
	{
		return new PartyTypeFormFieldFactory(this.app);
	}

	private static class PartyTypeFormFieldFactory implements FormFieldFactory
	{
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;


		public PartyTypeFormFieldFactory(MyVaadinApplication app)
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
				result = new TextField(this.app.getMessage(ApplicationMessages.PartyTypeId));

				result.setReadOnly(true);
			}
			else if (COL_DESC.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.PartyTypeDescription));
			}

			return result;
		}
	}
}
