package com.fbs.web.vaadin.ui.user.contact;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.fbs.dmr.universal.model.contact.ContactMechanismPurposeType;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.dmr.universal.model.party.PartyContactMechanismPurpose;
import com.fbs.web.dto.ContactMechanismDto;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;

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

public class PartyContactMechanismScreen extends ContactMechanismHelper<PartyContactMechanism>
{
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PartyContactMechanismScreen.class.getName());
	private static final String ID = "id";
	private static final String COMMENT = "comment";

	private static final String PARTY = "party";
	private static final String PARTY_NAME = "party.name";

	private static final String[] VISIBLE_COLUMNS = new String[]
		{ ID, PARTY_NAME, CONTACT_MECHANISM_ADDRESS };
	
	private static final String[] VISIBLE_FIELDS = new String[]
		{ ID, PARTY, COMMENT };
	private static final String[] NESTED_PROPERTIES = new String[]
		{ PARTY_NAME, CONTACT_MECHANISM_ADDRESS };

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
		this.formDetails.setFormFieldFactory(new ContactDetailsFormFieldFactory(this.app));

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

	@Override
	protected void setAdditionalElements(PartyContactMechanism bean)
	{
		this.setOptiongroup(bean);
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
		
		logger.info(">createBean");

		beanItem = (BeanItem<ContactMechanismDto>) this.formDetails.getItemDataSource();
		bean = beanItem.getBean();
		
		this.createContactMechanism(bean, t);
		
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
		
		logger.info("<createBean");

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

		this.updateContactMechanism(bean, t);
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
}
