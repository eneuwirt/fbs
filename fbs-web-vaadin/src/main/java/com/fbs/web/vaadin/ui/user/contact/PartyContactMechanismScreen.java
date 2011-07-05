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
		super(app, PartyContactMechanism.class, new PartyContactMechanismDetails(app, VISIBLE_FIELDS), VISIBLE_COLUMNS, NESTED_PROPERTIES);
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
	public PartyContactMechanism createBeanInstance()
	{
		return new PartyContactMechanism();
	}

	@Override
	public List<PartyContactMechanism> getAllBeans() throws Exception
	{
		List<PartyContactMechanism> result;

		result = this.app.getServices().getCrudServicePartyContactMechanism().findAll();

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PartyContactMechanism createBean(PartyContactMechanism t) throws Exception
	{
		/*Set<String> selectedPurposes;
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
		*/

		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateBean(PartyContactMechanism t) throws Exception
	{
		/*BeanItem<ContactMechanismDto> beanItem;
		ContactMechanismDto bean;

		beanItem = (BeanItem<ContactMechanismDto>) this.formDetails.getItemDataSource();

		bean = beanItem.getBean();

		this.app.getServices().getCrudServicePartyContactMechanism().update(t);

		this.updateContactMechanism(bean, t);
		*/
	}

	@Override
	public PartyContactMechanism readBean(PartyContactMechanism t) throws Exception
	{
		PartyContactMechanism result;

		result = this.app.getServices().getCrudServicePartyContactMechanism().read(t.getId());

		return result;
	}

	@Override
	public void deleteBean(PartyContactMechanism t) throws Exception
	{
		this.app.getServices().getCrudServicePartyContactMechanism().delete(t.getId());
	}


	@Override
	public String getColumnName(String propertyId)
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

}
