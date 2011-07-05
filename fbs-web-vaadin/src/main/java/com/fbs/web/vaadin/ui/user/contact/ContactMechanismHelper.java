package com.fbs.web.vaadin.ui.user.contact;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fbs.dmr.universal.model.contact.ContactMechanism;
import com.fbs.dmr.universal.model.contact.ElectronicAddress;
import com.fbs.dmr.universal.model.contact.EntityContactMechanism;
import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.dmr.universal.model.contact.TelecommunicationNumber;
import com.fbs.web.dto.ContactMechanismDto;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public abstract class ContactMechanismHelper<T extends EntityContactMechanism> extends ItemsListScreen<T>
{
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ContactMechanismHelper.class.getName());

	protected static final String CONTACT_MECHANISM_ADDRESS = "contactMechanism.address";
	protected static final String CONTACT_MECHANISM_TYPE_DESCR = "contactMechanism.contactMechanismType.description";

	protected static final String ADDRESS1 = "address1";
	protected static final String ADDRESS2 = "address2";
	protected static final String CITY = "city";
	protected static final String POSTAL_CODE = "postalCode";
	protected static final String COUNTRY = "country";
	protected static final String ELECTRONIC_ADDRESS = "electronicAddress";
	protected static final String COUNTRY_CODE = "countryCode";
	protected static final String AREA_CODE = "areaCode";
	protected static final String NUMBER = "number";

	protected static final String[] VISIBLE_FIELDS_DETAILS_POSTAL_ADDRESS = new String[]
		{ ADDRESS1, ADDRESS2, POSTAL_CODE, CITY, COUNTRY, };
	protected static final String[] VISIBLE_FIELDS_DETAILS_ELECTRONIC_ADDRESS = new String[]
		{ ELECTRONIC_ADDRESS };
	protected static final String[] VISIBLE_FIELDS_DETAILS_TELEKOM = new String[]
		{ COUNTRY_CODE, AREA_CODE, NUMBER };

	protected Form formDetails;

	public ContactMechanismHelper(MyVaadinApplication app, Class<T> clazz, String[] visibleColumns,
	        String[] visibleFields, String[] nestedContainerProperties)
	{
		super(app, clazz, visibleColumns, visibleFields, nestedContainerProperties);
	}

	@Override
	protected void layoutItemButtons()
	{
		String caption = this.app.getMessage(ApplicationMessages.ContactMechanismCreatePostalAddress);
		String caption1 = this.app.getMessage(ApplicationMessages.ContactMechanismCreateEAddress);
		String caption2 = this.app.getMessage(ApplicationMessages.ContactMechanismCreatePhone);

		this.buttonItemAdd.setCaption(caption);
		this.buttonItemAdd1.setCaption(caption1);
		this.buttonItemAdd2.setCaption(caption2);

		this.buttonItemDelete.setCaption("-");
	}

	protected String[] determineVisibleFieldDetails(ContactMechanism contactMechanism)
	{
		String[] visible_field_details = null;

		if (contactMechanism instanceof PostalAddress)
		{
			visible_field_details = VISIBLE_FIELDS_DETAILS_POSTAL_ADDRESS;
		}
		else if (contactMechanism instanceof ElectronicAddress)
		{
			visible_field_details = VISIBLE_FIELDS_DETAILS_ELECTRONIC_ADDRESS;
		}
		else if (contactMechanism instanceof TelecommunicationNumber)
		{
			visible_field_details = VISIBLE_FIELDS_DETAILS_TELEKOM;
		}
		else
		{
			visible_field_details = new String[] {};
		}

		return visible_field_details;
	}

	protected ContactMechanism createContactMechanism()
	{
		ContactMechanism contactMechanism;

		switch (this.actionCurrent)
		{
			case CREATE:
				contactMechanism = new PostalAddress();
				break;

			case CREATE_1:
				contactMechanism = new ElectronicAddress();
				break;

			case CREATE_2:
				contactMechanism = new TelecommunicationNumber();
				break;

			default:
				contactMechanism = new ContactMechanism();
				break;
		}

		return contactMechanism;
	}

	@SuppressWarnings("unchecked")
	protected void updateDetails(T bean)
	{
		BeanItem<ContactMechanismDto> beanItem;
		ContactMechanism contactMechanism = null;
		ContactMechanismDto dummy = null;
		String[] visible_field_details = null;

		super.updateDetails(bean);

		if (bean != null)
		{
			contactMechanism = bean.getContactMechanism();
		}
		else if (bean == null)
		{
			contactMechanism = this.createContactMechanism();

			BeanItem<T> bI;

			bI = (BeanItem<T>) this.form.getItemDataSource();

			bean = bI.getBean();

			bean.setContactMechanism(contactMechanism);
		}

		dummy = new ContactMechanismDto(contactMechanism);

		beanItem = new BeanItem<ContactMechanismDto>(dummy);

		this.setAdditionalElements(bean);

		visible_field_details = this.determineVisibleFieldDetails(contactMechanism);

		this.formDetails.setItemDataSource(beanItem, Arrays.asList(visible_field_details));
	}

	protected void setAdditionalElements(T bean)
	{
		return;
	}

	protected void createContactMechanism(ContactMechanismDto bean, EntityContactMechanism t)
	{
		if (t.getContactMechanism() instanceof PostalAddress)
		{
			PostalAddress postalAddress;

			postalAddress = (PostalAddress) t.getContactMechanism();

			bean.fillPostalAddress(postalAddress);

			this.app.getServices().getCrudServicePostalAddress().create(postalAddress);
		}
		else if (t.getContactMechanism() instanceof TelecommunicationNumber)
		{
			TelecommunicationNumber tn;

			tn = (TelecommunicationNumber) t.getContactMechanism();

			bean.fillTelecommunicationNumber(tn);

			this.app.getServices().getCrudServiceTelecommunicationNumber().create(tn);
		}
		else if (t.getContactMechanism() instanceof ElectronicAddress)
		{
			ElectronicAddress ea;

			ea = (ElectronicAddress) t.getContactMechanism();

			bean.fillElectronicAddress(ea);

			this.app.getServices().getCrudServiceElectronicAddress().create(ea);
		}
		else
		{
			String msg = "Unknown class: " + t.getContactMechanism().getClass().getName();

			logger.log(Level.SEVERE, msg);

			throw new IllegalArgumentException(msg);
		}
	}

	protected void updateContactMechanism(ContactMechanismDto bean, EntityContactMechanism t)
	{
		if (t.getContactMechanism() instanceof PostalAddress)
		{
			PostalAddress postalAddress;

			postalAddress = (PostalAddress) t.getContactMechanism();

			bean.fillPostalAddress(postalAddress);

			this.app.getServices().getCrudServicePostalAddress().update(postalAddress);
		}
		else if (t.getContactMechanism() instanceof TelecommunicationNumber)
		{
			TelecommunicationNumber tn;

			tn = (TelecommunicationNumber) t.getContactMechanism();

			bean.fillTelecommunicationNumber(tn);

			this.app.getServices().getCrudServiceTelecommunicationNumber().update(tn);
		}
		else if (t.getContactMechanism() instanceof ElectronicAddress)
		{
			ElectronicAddress ea;

			ea = (ElectronicAddress) t.getContactMechanism();

			bean.fillElectronicAddress(ea);

			this.app.getServices().getCrudServiceElectronicAddress().update(ea);
		}
		else
		{
			String msg = "Unknown class: " + t.getContactMechanism().getClass().getName();

			logger.log(Level.SEVERE, msg);

			throw new IllegalArgumentException(msg);
		}
	}

	public static class ContactDetailsFormFieldFactory implements FormFieldFactory
	{
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;

		public ContactDetailsFormFieldFactory(MyVaadinApplication app)
		{
			this.app = app;
		}

		@Override
		public Field createField(Item item, Object propertyId, Component uiContext)
		{
			Field result = null;
			String pid = (String) propertyId;

			if (ADDRESS1.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.PostalAddressAddress1));
			}
			else if (ADDRESS2.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.PostalAddressAddress2));
			}
			else if (CITY.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.PostalAddressCity));
			}
			else if (POSTAL_CODE.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.PostalAddressPostalCode));
			}
			else if (COUNTRY.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.PostalAddressCountry));
			}
			else if (ELECTRONIC_ADDRESS.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.ElectronicAddress));
			}
			else if (COUNTRY_CODE.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.TelekommunikationCountryCode));
			}
			else if (AREA_CODE.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.TelekommunikationAreaCode));
			}
			else if (NUMBER.equals(pid))
			{
				result = new TextField(ApplicationMessages.TelekommunikationNumber);
			}

			return result;
		}
	}
}
