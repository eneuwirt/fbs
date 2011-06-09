package com.fbs.web.vaadin.ui.user.party;

import java.util.List;

import com.fbs.dmr.universal.model.party.Person;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class PersonScreen extends PartyScreen<Person>
{
	private static final long serialVersionUID = 1L;
	private static final String COL_ID = "id";
	private static final String COL_FIRST_NAME = "firstName";
	private static final String COL_LAST_NAME = "lastName";
	private static final String[] VISIBLE_COLUMNS = new String[] { COL_ID, COL_FIRST_NAME, COL_LAST_NAME };
	private static final String[] VISIBLE_FIELDS =  new String[] { COL_ID, COL_FIRST_NAME, COL_LAST_NAME };


	public PersonScreen(MyVaadinApplication app)
	{
		super(app, Person.class, VISIBLE_COLUMNS, VISIBLE_FIELDS);
	}


	@Override
	protected Person createBeanInstance()
	{
		return new Person();
	}


	@Override
	protected List<Person> getAllBeans() throws Exception
	{
		List<Person> result = this.services.getCrudServicePerson().findAll();

		return result;
	}


	@Override
	protected Person createBean(Person t) throws Exception
	{
		this.services.getCrudServicePerson().create(t);

		return t;
	}


	@Override
	protected void updateBean(Person t) throws Exception
	{
		this.services.getCrudServicePerson().update(t);
	}


	@Override
	protected Person readBean(Person t) throws Exception
	{
		Person result;

		result = this.services.getCrudServicePerson().read(t.getId());

		return result;
	}


	@Override
	protected void deleteBean(Person t) throws Exception
	{
		this.services.getCrudServicePerson().delete(t.getId());
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
	protected FormFieldFactory getFormFieldFactory()
	{
		return new PersonFormFieldFactory(this.app);
	}

	private static class PersonFormFieldFactory implements FormFieldFactory
	{
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;


		public PersonFormFieldFactory(MyVaadinApplication app)
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
