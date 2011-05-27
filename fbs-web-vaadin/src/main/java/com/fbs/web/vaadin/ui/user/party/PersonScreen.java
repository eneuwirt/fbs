package com.fbs.web.vaadin.ui.user.party;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.fbs.dmr.universal.model.party.Person;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class PersonScreen extends ItemsListScreen<Person>
{
	private static final long serialVersionUID = 1L;
	private static final String COL_ID = "id";
	private static final String COL_FIRST_NAME = "firstName";
	private static final String COL_LAST_NAME = "lastName";
	private static String[] visibleColumns = new String[] { COL_ID, COL_FIRST_NAME, COL_LAST_NAME };
	private static String[] visibleItemProperties = new String[] { COL_ID, COL_FIRST_NAME, COL_LAST_NAME };


	public PersonScreen(MyVaadinApplication app)
	{
		super(app, Person.class);
	}


	@Override
	protected Person createBeanInstance()
	{
		return new Person();
	}


	@Override
	protected List<Person> getAllBeans() throws Exception
	{
		List<Person> result = this.services.getCrudPersonService().findAll();

		return result;
	}


	@Override
	protected Person createBean(Person t) throws Exception
	{
		this.services.getCrudPersonService().create(t);

		return t;
	}


	@Override
	protected void updateBean(Person t) throws Exception
	{
		this.services.getCrudPersonService().update(t);
	}


	@Override
	protected Person readBean(Person t) throws Exception
	{
		Person result;

		result = this.services.getCrudPersonService().read(t.getId());
		
		return result;
	}


	@Override
	protected void deleteBean(Person t) throws Exception
	{
		this.services.getCrudPersonService().delete(t.getId());
	}


	@Override
	protected String[] getVisibleColumns()
	{
		return visibleColumns;
	}


	@Override
	protected String getColumnName(String propertyId)
	{
		if (propertyId.equals(COL_ID))
			return this.app.getMessage(ApplicationMessages.PersonId);

		if (propertyId.equals(COL_FIRST_NAME))
			return this.app.getMessage(ApplicationMessages.PersonFirstName);

		if (propertyId.equals(COL_LAST_NAME))
			return this.app.getMessage(ApplicationMessages.PersonLastName);

		return propertyId;
	}


	@Override
	protected Collection<String> getVisibleItemProperties()
	{
		return Arrays.asList(visibleItemProperties);
	}


	@Override
	protected FormFieldFactory getFormFieldFactory()
	{
		return new PartyFormFieldFactory(this.app);
	}

	private static class PartyFormFieldFactory implements FormFieldFactory
	{
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;


		public PartyFormFieldFactory(MyVaadinApplication app)
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
				result = new TextField(this.app.getMessage(ApplicationMessages.PersonId));

				result.setReadOnly(true);
			}
			else if (COL_FIRST_NAME.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.PersonFirstName));
			}
			else if (COL_LAST_NAME.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.PersonLastName));
			}

			return result;
		}
	}
}
