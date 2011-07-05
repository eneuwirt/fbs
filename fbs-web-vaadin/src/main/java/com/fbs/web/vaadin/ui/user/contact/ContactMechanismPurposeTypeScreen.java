package com.fbs.web.vaadin.ui.user.contact;

import java.util.List;

import com.fbs.dmr.universal.model.contact.ContactMechanismPurposeType;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class ContactMechanismPurposeTypeScreen extends ItemsListScreen<ContactMechanismPurposeType>
{
	private static final long serialVersionUID = 1L;
	private static final String ID = "id";
	private static final String DESC = "description";
	private static final String[] VISIBLE_COLUMNS = new String[]
		{ ID, DESC };
	private static final String[] VISIBLE_FIELDS = new String[]
		{ ID, DESC };

	public ContactMechanismPurposeTypeScreen(MyVaadinApplication app)
	{
		super(app, ContactMechanismPurposeType.class, VISIBLE_COLUMNS, VISIBLE_FIELDS);
	}

	@Override
	public ContactMechanismPurposeType createBeanInstance()
	{
		return new ContactMechanismPurposeType();
	}

	@Override
	public List<ContactMechanismPurposeType> getAllBeans() throws Exception
	{
		return this.app.getServices().getCrudServiceContactMechanismPurposeType().findAll();
	}

	@Override
	public ContactMechanismPurposeType createBean(ContactMechanismPurposeType t) throws Exception
	{
		this.app.getServices().getCrudServiceContactMechanismPurposeType().create(t);

		return t;
	}

	@Override
	public void updateBean(ContactMechanismPurposeType t) throws Exception
	{
		this.app.getServices().getCrudServiceContactMechanismPurposeType().update(t);
	}

	@Override
	public ContactMechanismPurposeType readBean(ContactMechanismPurposeType t) throws Exception
	{
		ContactMechanismPurposeType result;

		result = this.app.getServices().getCrudServiceContactMechanismPurposeType().read(t.getId());

		return result;
	}

	@Override
	public void deleteBean(ContactMechanismPurposeType t) throws Exception
	{
		this.app.getServices().getCrudServiceContactMechanismPurposeType().delete(t.getId());
	}

	@Override
	public String getColumnName(String propertyId)
	{
		if (propertyId.equals(ID))
			return this.app.getMessage(ApplicationMessages.ContactMechanismPurposeTypeId);

		if (propertyId.equals(DESC))
			return this.app.getMessage(ApplicationMessages.ContactMechanismPurposeTypeDescription);

		return propertyId;
	}

	@Override
	public FormFieldFactory getFormFieldFactory()
	{
		return new ContactMechanismPurposeTypeFormFieldFactory(this.app);
	}

	private static class ContactMechanismPurposeTypeFormFieldFactory implements FormFieldFactory
	{
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;

		public ContactMechanismPurposeTypeFormFieldFactory(MyVaadinApplication app)
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
				result = new TextField(this.app.getMessage(ApplicationMessages.ContactMechanismPurposeTypeId));

				result.setReadOnly(true);
			}
			else if (DESC.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.ContactMechanismPurposeTypeDescription));
			}

			return result;
		}
	}
}
