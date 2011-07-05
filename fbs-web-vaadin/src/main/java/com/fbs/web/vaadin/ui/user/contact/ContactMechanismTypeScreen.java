package com.fbs.web.vaadin.ui.user.contact;

import java.util.List;

import com.fbs.dmr.universal.model.contact.ContactMechanismType;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class ContactMechanismTypeScreen extends ItemsListScreen<ContactMechanismType>
{
	private static final long serialVersionUID = 1L;
	private static final String ID = "id";
	private static final String DESC = "description";
	private static final String[] VISIBLE_COLUMNS = new String[]
		{ ID, DESC };
	private static final String[] VISIBLE_FIELDS = new String[]
		{ ID, DESC };

	public ContactMechanismTypeScreen(MyVaadinApplication app)
	{
		super(app, ContactMechanismType.class, VISIBLE_COLUMNS, VISIBLE_FIELDS);
	}

	@Override
	public ContactMechanismType createBeanInstance()
	{
		return new ContactMechanismType();
	}

	@Override
	public List<ContactMechanismType> getAllBeans() throws Exception
	{
		return this.app.getServices().getCrudServiceContactMechanismType().findAll();
	}

	@Override
	public ContactMechanismType createBean(ContactMechanismType t) throws Exception
	{
		this.services.getCrudServiceContactMechanismType().create(t);

		return t;
	}

	@Override
	public void updateBean(ContactMechanismType t) throws Exception
	{
		this.services.getCrudServiceContactMechanismType().update(t);
	}

	@Override
	public ContactMechanismType readBean(ContactMechanismType t) throws Exception
	{
		ContactMechanismType result;

		result = this.services.getCrudServiceContactMechanismType().read(t.getId());

		return result;
	}

	@Override
	public void deleteBean(ContactMechanismType t) throws Exception
	{
		this.services.getCrudServiceContactMechanismType().delete(t.getId());
	}

	@Override
	public String getColumnName(String propertyId)
	{
		if (propertyId.equals(ID))
			return this.app.getMessage(ApplicationMessages.ContactMechanismTypeId);

		if (propertyId.equals(DESC))
			return this.app.getMessage(ApplicationMessages.ContactMechanismTypeDescription);

		return propertyId;
	}

	@Override
	public FormFieldFactory getFormFieldFactory()
	{
		return new ContactMechanismTypeFormFieldFactory(this.app);
	}

	private static class ContactMechanismTypeFormFieldFactory implements FormFieldFactory
	{
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;

		public ContactMechanismTypeFormFieldFactory(MyVaadinApplication app)
		{
			this.app = app;
		}

		@Override
		public Field createField(Item item, Object propertyId, Component uiContext)
		{
			// Identify the fields by their Property ID.
			TextField result = null;

			String pid = (String) propertyId;

			if (ID.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.ContactMechanismTypeId));

				result.setReadOnly(true);
			}
			else if (DESC.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.ContactMechanismTypeDescription));
			}

			return result;
		}
	}
}
