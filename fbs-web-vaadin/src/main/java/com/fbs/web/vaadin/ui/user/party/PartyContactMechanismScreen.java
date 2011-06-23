package com.fbs.web.vaadin.ui.user.party;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fbs.dmr.universal.model.contact.ElectronicAddress;
import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.dmr.universal.model.contact.TelecommunicationNumber;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.web.dto.ContactMechanismDto;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class PartyContactMechanismScreen extends ItemsListScreen<PartyContactMechanism>
{
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PartyContactMechanismScreen.class.getName());
	private static final String ID = "id";
	private static final String COMMENT = "comment";
	private static final String CONTACT_MECHANISM_ADDRESS = "contactMechanism.address";
	private static final String CONTACT_MECHANISM_TYPE_DESCR = "contactMechanism.contactMechanismType.description";
	private static final String PARTY = "party";
	private static final String PARTY_NAME = "party.name";
	private static final String[] VISIBLE_COLUMNS = new String[]
		{ ID, PARTY_NAME, CONTACT_MECHANISM_ADDRESS, CONTACT_MECHANISM_TYPE_DESCR };
	private static final String[] VISIBLE_FIELDS = new String[]
		{ ID, PARTY, COMMENT };
	private static final String[] NESTED_PROPERTIES = new String[]
		{ PARTY_NAME, CONTACT_MECHANISM_ADDRESS, CONTACT_MECHANISM_TYPE_DESCR };
	private static final String ADDRESS1 = "address1";
	private static final String ADDRESS2 = "address2";
	private static final String CITY = "city";
	private static final String POSTAL_CODE = "postalCode";
	private static final String COUNTRY = "country";
	private static final String ELECTRONIC_ADDRESS = "electronicAddress";
	private static final String COUNTRY_CODE = "countryCode";
	private static final String AREA_CODE = "areaCode";
	private static final String NUMBER = "number";

	private Form formDetails;

	public PartyContactMechanismScreen(MyVaadinApplication app)
	{
		super(app, PartyContactMechanism.class, VISIBLE_COLUMNS, VISIBLE_FIELDS, NESTED_PROPERTIES);
	}

	@Override
	protected AbstractComponentContainer getComponent()
	{
		VerticalLayout verticalLayout;

		verticalLayout = new VerticalLayout();

		this.formDetails = new Form();
		this.formDetails.setFormFieldFactory(new PartyContactDetailsFormFieldFactory(this.app));

		verticalLayout.addComponent(this.form);
		verticalLayout.addComponent(this.formDetails);

		return verticalLayout;
	}

	protected void updateComponent(PartyContactMechanism bean)
	{
		super.updateComponent(bean);

		if (bean != null)
		{
			BeanItem<ContactMechanismDto> beanItem;
			ContactMechanismDto dummy;

			dummy = new ContactMechanismDto(bean.getContactMechanism());

			beanItem = new BeanItem<ContactMechanismDto>(dummy);

			this.formDetails.setItemDataSource(beanItem);
		}
	}

	@Override
	protected void layoutComponent()
	{
		this.formDetails.setSizeFull();

		// this.component.setSizeFull();
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

	@SuppressWarnings("unchecked")
    @Override
	protected void updateBean(PartyContactMechanism t) throws Exception
	{
		BeanItem<ContactMechanismDto> beanItem;
		ContactMechanismDto bean;
		
		
		beanItem = (BeanItem<ContactMechanismDto>) this.formDetails.getItemDataSource();
		
		bean = beanItem.getBean();
		
		this.app.getServices().getCrudServicePartyContactMechanism().update(t);
		
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

			return result;
		}
	}

	private static class PartyContactDetailsFormFieldFactory implements FormFieldFactory
	{
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;

		public PartyContactDetailsFormFieldFactory(MyVaadinApplication app)
		{
			this.app = app;
		}

		/**
		 * Sehr tricky. Also ich bekomme eine Instanz Dto Klasse Anhand der
		 * zugrunde liegenden Klasse bestimme ich, welche Felder aktiviert
		 * werden.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public Field createField(Item item, Object propertyId, Component uiContext)
		{
			Field result = null;
			BeanItem<ContactMechanismDto> beanItem;
			ContactMechanismDto contactMechanismDto;

			String pid = (String) propertyId;
			beanItem = (BeanItem<ContactMechanismDto>) item;
			contactMechanismDto = beanItem.getBean();

			boolean isPA = contactMechanismDto.getContactMechanism() instanceof PostalAddress;
			boolean isPhone = contactMechanismDto.getContactMechanism() instanceof TelecommunicationNumber;
			boolean isElectronic = contactMechanismDto.getContactMechanism() instanceof ElectronicAddress;

			if (ADDRESS1.equals(pid) && isPA)
			{
				result = new TextField("Addresse");
			}
			else if (ADDRESS2.equals(pid) && isPA)
			{
				result = new TextField("Addresszusatz");
			}
			else if (CITY.equals(pid) && isPA)
			{
				result = new TextField("Stadt");
			}
			else if (POSTAL_CODE.equals(pid) && isPA)
			{
				result = new TextField("Postleitzahl");
			}
			else if (COUNTRY.equals(pid) && isPA)
			{
				result = new TextField("Staat");
			}
			else if (ELECTRONIC_ADDRESS.equals(pid) && isElectronic)
			{
				result = new TextField("Elektronische Adresse");
			}
			else if (COUNTRY_CODE.equals(pid) && isPhone)
			{
				result = new TextField("Länder Code");
			}
			else if (AREA_CODE.equals(pid) && isPhone)
			{
				result = new TextField("Vorwahl");
			}
			else if (NUMBER.equals(pid) && isPhone)
			{
				result = new TextField("Telefonnummer");
			}

			return result;
		}
	}
}
