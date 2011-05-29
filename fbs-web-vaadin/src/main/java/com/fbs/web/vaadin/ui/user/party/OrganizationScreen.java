package com.fbs.web.vaadin.ui.user.party;

import java.util.List;

import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;

public class OrganizationScreen extends ItemsListScreen<Organization>
{
	private static final long serialVersionUID = 1L;
	private static final String COL_ID = "id";
	private static final String COL_NAME = "name";
	private static final String COL_CLASS = "partyClassifications";
	private static final String[] VISIBLE_COLUMNS = new String[] { COL_ID, COL_NAME };
	private static final String[] VISIBLE_ITEM_PROPERTIES = new String[] { COL_ID, COL_NAME, COL_CLASS };


	public OrganizationScreen(MyVaadinApplication app)
	{
		super(app, Organization.class, null, VISIBLE_COLUMNS, VISIBLE_ITEM_PROPERTIES);
	}


	@Override
	protected Organization createBeanInstance()
	{
		return new Organization();
	}


	@Override
	protected List<Organization> getAllBeans() throws Exception
	{
		List<Organization> organizations;

		organizations = this.services.getCrudServiceOrganization().findAll();

		return organizations;
	}


	@Override
	protected Organization createBean(Organization t) throws Exception
	{
		this.services.getCrudServiceOrganization().create(t);

		return t;
	}


	@Override
	protected void updateBean(Organization t) throws Exception
	{
		this.services.getCrudServiceOrganization().update(t);
	}


	@Override
	protected Organization readBean(Organization t) throws Exception
	{
		Organization org;

		org = this.services.getCrudServiceOrganization().read(t.getId());

		return org;
	}


	@Override
	protected void deleteBean(Organization t) throws Exception
	{
		this.services.getCrudServiceOrganization().delete(t.getId());
	}


	@Override
	protected String getColumnName(String propertyId)
	{
		if (propertyId.equals(COL_ID))
			return this.app.getMessage(ApplicationMessages.OrgId);

		return propertyId;
	}


	@Override
	protected FormFieldFactory getFormFieldFactory()
	{
		return new OrganizationFormFieldFactory(this.app);
	}

	private static class OrganizationFormFieldFactory implements FormFieldFactory
	{
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;


		public OrganizationFormFieldFactory(MyVaadinApplication app)
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
				result = new TextField(this.app.getMessage(ApplicationMessages.OrgId));

				result.setReadOnly(true);
			}
			else if (COL_NAME.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.OrgName));
			}
			else if (COL_CLASS.equals(pid))
			{
				OptionGroup optionGroup;
				// Have a bean container to put the beans in
				BeanItemContainer<PartyType> container = new BeanItemContainer<PartyType>(PartyType.class);
				List<PartyType> partyTypes;

				partyTypes = this.app.getServices().getCrudPartyTypeService().findAll();
				for (PartyType partyType : partyTypes)
				{
					container.addBean(partyType);
				}

				optionGroup = new OptionGroup(this.app.getMessage(ApplicationMessages.PartyTypeClassificationTitle),
				        container);

				// Set the caption mode to read the caption directly
				// from the 'name' property of the bean
				optionGroup.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
				optionGroup.setItemCaptionPropertyId("description");
				optionGroup.setMultiSelect(true);
				optionGroup.setNullSelectionAllowed(false);
				optionGroup.setImmediate(true);

				result = optionGroup;
			}

			return result;
		}
	}
}
