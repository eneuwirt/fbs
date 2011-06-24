package com.fbs.web.vaadin.ui.user.party;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fbs.dmr.universal.model.contact.ContactMechanism;
import com.fbs.dmr.universal.model.contact.ContactMechanismPurposeType;
import com.fbs.dmr.universal.model.contact.ElectronicAddress;
import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.dmr.universal.model.contact.TelecommunicationNumber;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.dmr.universal.model.party.PartyContactMechanismPurpose;
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
import com.vaadin.ui.OptionGroup;
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
		{ ID, PARTY_NAME, CONTACT_MECHANISM_ADDRESS };
	private static final String[] VISIBLE_FIELDS = new String[]
		{ ID, PARTY, COMMENT };
	private static final String[] NESTED_PROPERTIES = new String[]
		{ PARTY_NAME, CONTACT_MECHANISM_ADDRESS };
	private static final String ADDRESS1 = "address1";
	private static final String ADDRESS2 = "address2";
	private static final String CITY = "city";
	private static final String POSTAL_CODE = "postalCode";
	private static final String COUNTRY = "country";
	private static final String ELECTRONIC_ADDRESS = "electronicAddress";
	private static final String COUNTRY_CODE = "countryCode";
	private static final String AREA_CODE = "areaCode";
	private static final String NUMBER = "number";
	private static final String[] VISIBLE_FIELDS_DETAILS_POSTAL_ADDRESS = new String[]
		{ ADDRESS1, ADDRESS2, POSTAL_CODE, CITY, COUNTRY, };
	private static final String[] VISIBLE_FIELDS_DETAILS_ELECTRONIC_ADDRESS = new String[]
		{ ELECTRONIC_ADDRESS };
	private static final String[] VISIBLE_FIELDS_DETAILS_TELEKOM = new String[]
		{ COUNTRY_CODE, AREA_CODE, NUMBER };

	private Form formDetails;
	private OptionGroup optionGroupPurpose;

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
		this.formDetails.setCaption(this.app.getMessage(ApplicationMessages.PartyContactMechanismDetails));
		this.formDetails.setFormFieldFactory(new PartyContactDetailsFormFieldFactory(this.app));

		this.optionGroupPurpose = new OptionGroup();
		this.optionGroupPurpose.setCaption(this.app.getMessage(ApplicationMessages.PartyContactMechanismPurpose));
		this.optionGroupPurpose.setMultiSelect(true);
		this.optionGroupPurpose.setNullSelectionAllowed(true);
		this.optionGroupPurpose.setImmediate(true);

		verticalLayout.addComponent(this.form);
		verticalLayout.addComponent(this.formDetails);
		verticalLayout.addComponent(this.optionGroupPurpose);

		return verticalLayout;
	}

	@SuppressWarnings("unchecked")
	protected void updateComponent(PartyContactMechanism bean)
	{
		BeanItem<ContactMechanismDto> beanItem;
		ContactMechanism contactMechanism = null;
		ContactMechanismDto dummy = null;
		String[] visible_field_details = null;

		super.updateComponent(bean);

		if (bean != null)
		{
			contactMechanism = bean.getContactMechanism();
		}
		else if (bean == null)
		{
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

			BeanItem<PartyContactMechanism> bI;

			bI = (BeanItem<PartyContactMechanism>) this.form.getItemDataSource();

			bean = bI.getBean();

			bean.setContactMechanism(contactMechanism);
		}

		dummy = new ContactMechanismDto(contactMechanism);

		beanItem = new BeanItem<ContactMechanismDto>(dummy);

		this.setOptiongroup(bean);

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
			visible_field_details = new String[]{};
		}

		this.formDetails.setItemDataSource(beanItem, Arrays.asList(visible_field_details));
	}

	private void setOptiongroup(PartyContactMechanism pcm)
	{
		List<ContactMechanismPurposeType> contactMechanismPurposes;
		BeanItemContainer<String> container;

		container = new BeanItemContainer<String>(String.class);
		contactMechanismPurposes = this.app.getServices().getCrudServiceContactMechanismPurposeType().findAll();
		for (ContactMechanismPurposeType cmp : contactMechanismPurposes)
		{
			container.addBean(cmp.getDescription());
		}

		this.optionGroupPurpose.setContainerDataSource(container);

		// set selection
		this.optionGroupPurpose.setValue(null);
		if (pcm != null)
		{
			List<PartyContactMechanismPurpose> partyContactMechanismPurposes;

			partyContactMechanismPurposes = this.app.getServices().getCrudServicePartyContactMechanismPurpose()
			        .findByPartyContactMechanism(pcm);

			for (PartyContactMechanismPurpose p : partyContactMechanismPurposes)
			{
				this.optionGroupPurpose.select(p.getContactMechanismPurposeType().getDescription());
			}
		}
	}

	@Override
	protected void layoutComponent()
	{
		this.formDetails.setSizeFull();

		this.optionGroupPurpose.setSizeFull();
	}

	@Override
	protected void layoutItemButtons()
	{
		String caption = this.app.getMessage(ApplicationMessages.PartyContactMechanismCreatePostalAddress);
		String caption1 = this.app.getMessage(ApplicationMessages.PartyContactMechanismCreateEAddress);
		String caption2 = this.app.getMessage(ApplicationMessages.PartyContactMechanismCreatePhone);

		this.buttonItemAdd.setCaption(caption);
		this.buttonItemAdd1.setCaption(caption1);
		this.buttonItemAdd2.setCaption(caption2);

		this.buttonItemDelete.setCaption("-");
	}

	@Override
	protected PartyContactMechanism createBeanInstance()
	{
		return new PartyContactMechanism();
	}

	@Override
	protected List<PartyContactMechanism> getAllBeans() throws Exception
	{
		List<PartyContactMechanism> result;

		result = this.app.getServices().getCrudServicePartyContactMechanism().findAll();

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected PartyContactMechanism createBean(PartyContactMechanism t) throws Exception
	{
		Set<String> selectedPurposes;
		BeanItem<ContactMechanismDto> beanItem;
		ContactMechanismDto bean;

		beanItem = (BeanItem<ContactMechanismDto>) this.formDetails.getItemDataSource();
		bean = beanItem.getBean();

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

		this.app.getServices().getCrudServicePartyContactMechanism().create(t);

		selectedPurposes = (Set<String>) this.optionGroupPurpose.getValue();
		for (String s : selectedPurposes)
		{
			ContactMechanismPurposeType contactMechanismPurposeType;
			PartyContactMechanismPurpose cmp;

			contactMechanismPurposeType = this.app.getServices().getCrudServiceContactMechanismPurposeType()
			        .findForDescription(s);

			cmp = new PartyContactMechanismPurpose();
			cmp.setPartyContactMechanism(t);
			cmp.setContactMechanismPurposeType(contactMechanismPurposeType);
			this.app.getServices().getCrudServicePartyContactMechanismPurpose().create(cmp);

		}

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
