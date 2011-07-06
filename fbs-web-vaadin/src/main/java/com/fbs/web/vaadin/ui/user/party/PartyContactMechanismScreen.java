package com.fbs.web.vaadin.ui.user.party;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fbs.dmr.universal.model.contact.ContactMechanism;
import com.fbs.dmr.universal.model.contact.ContactMechanismPurposeType;
import com.fbs.dmr.universal.model.contact.ElectronicAddress;
import com.fbs.dmr.universal.model.contact.EntityContactMechanism;
import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.dmr.universal.model.contact.TelecommunicationNumber;
import com.fbs.dmr.universal.model.party.PartyContactMechanism;
import com.fbs.dmr.universal.model.party.PartyContactMechanismPurpose;
import com.fbs.web.dto.ContactMechanismDto;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.fbs.web.vaadin.ui.user.contact.ContactMechanismHelper;


import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class PartyContactMechanismScreen extends ItemsListScreen<PartyContactMechanism> implements ContactMechanismHelper
{
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PartyContactMechanismScreen.class.getName());
	private static final String ID = "id";
	private static final String COMMENT = "comment";
	
	protected static final String CONTACT_MECHANISM_ADDRESS = "contactMechanism.address";
    protected static final String CONTACT_MECHANISM_TYPE_DESCR = "contactMechanism.contactMechanismType.description";


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
