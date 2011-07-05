package com.fbs.web.vaadin.ui.user.contact;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.fbs.dmr.universal.model.contact.ContactMechanism;
import com.fbs.dmr.universal.model.facility.Facility;
import com.fbs.dmr.universal.model.facility.FacilityContactMechanism;
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
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class FacilityContactMechanismScreen extends ContactMechanismHelper<FacilityContactMechanism>
{
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(FacilityContactMechanismScreen.class.getName());

	private static final String ID = "id";

	private static final String FACILITY = "facility";
	private static final String FACILITY_DESCRIPTION = "facility.description";

	private static final String[] VISIBLE_COLUMNS = new String[]
		{ ID, FACILITY_DESCRIPTION, CONTACT_MECHANISM_ADDRESS };

	private static final String[] VISIBLE_FIELDS = new String[]
		{ ID, FACILITY };

	private static final String[] NESTED_PROPERTIES = new String[]
		{ FACILITY_DESCRIPTION, CONTACT_MECHANISM_ADDRESS };

	public FacilityContactMechanismScreen(MyVaadinApplication app)
	{
		super(app, FacilityContactMechanism.class, VISIBLE_COLUMNS, VISIBLE_FIELDS, NESTED_PROPERTIES);
	}

	@Override
	protected AbstractComponentContainer getComponent()
	{
		VerticalLayout verticalLayout;

		verticalLayout = new VerticalLayout();

		this.formDetails = new Form();
		this.formDetails.setCaption(this.app.getMessage(ApplicationMessages.PartyContactMechanismDetails));
		this.formDetails.setFormFieldFactory(new ContactDetailsFormFieldFactory(this.app));

		verticalLayout.addComponent(this.form);
		verticalLayout.addComponent(this.formDetails);

		return verticalLayout;
	}

	@Override
	protected void layoutComponent()
	{
		this.formDetails.setSizeFull();
	}

	@SuppressWarnings("unchecked")
	protected void updateDetails(FacilityContactMechanism bean)
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

			BeanItem<FacilityContactMechanism> bI;

			bI = (BeanItem<FacilityContactMechanism>) this.form.getItemDataSource();

			bean = bI.getBean();

			bean.setContactMechanism(contactMechanism);
		}

		dummy = new ContactMechanismDto(contactMechanism);

		beanItem = new BeanItem<ContactMechanismDto>(dummy);

		// this.setOptiongroup(bean);

		visible_field_details = this.determineVisibleFieldDetails(contactMechanism);

		this.formDetails.setItemDataSource(beanItem, Arrays.asList(visible_field_details));
	}

	@Override
	public FacilityContactMechanism createBeanInstance()
	{
		return new FacilityContactMechanism();
	}

	@Override
	public List<FacilityContactMechanism> getAllBeans() throws Exception
	{
		List<FacilityContactMechanism> result;

		result = this.app.getServices().getCrudServiceFacilityContactMechanism().findAll();

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FacilityContactMechanism createBean(FacilityContactMechanism t) throws Exception
	{
		BeanItem<ContactMechanismDto> beanItem;
		ContactMechanismDto bean;

		logger.info(">createBean");

		beanItem = (BeanItem<ContactMechanismDto>) this.formDetails.getItemDataSource();
		bean = beanItem.getBean();

		this.createContactMechanism(bean, t);

		this.app.getServices().getCrudServiceFacilityContactMechanism().create(t);

		logger.info("<createBean");

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateBean(FacilityContactMechanism t) throws Exception
	{
		BeanItem<ContactMechanismDto> beanItem;
		ContactMechanismDto bean;

		beanItem = (BeanItem<ContactMechanismDto>) this.formDetails.getItemDataSource();

		bean = beanItem.getBean();

		this.app.getServices().getCrudServiceFacilityContactMechanism().update(t);

		this.updateContactMechanism(bean, t);
	}

	@Override
	public FacilityContactMechanism readBean(FacilityContactMechanism t) throws Exception
	{
		FacilityContactMechanism result;

		result = this.app.getServices().getCrudServiceFacilityContactMechanism().read(t.getId());

		return result;
	}

	@Override
	public void deleteBean(FacilityContactMechanism t) throws Exception
	{
		this.app.getServices().getCrudServiceFacilityContactMechanism().delete(t.getId());
	}

	@Override
	public FormFieldFactory getFormFieldFactory()
	{
		return new FacilityContactMechanismFormFieldFactory(this.app);
	}

	@Override
	public String getColumnName(String propertyId)
	{
		if (propertyId.equals(ID))
			return this.app.getMessage(ApplicationMessages.FacilityContactMechanismId);

		return propertyId;
	}

	private static class FacilityContactMechanismFormFieldFactory implements FormFieldFactory
	{
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;

		public FacilityContactMechanismFormFieldFactory(MyVaadinApplication app)
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
				result = new TextField(this.app.getMessage(ApplicationMessages.FacilityContactMechanismId));

				result.setReadOnly(true);
			}
			else if (FACILITY.equals(pid))
			{
				Select select;
				BeanItemContainer<Facility> container;
				List<Facility> facilities;
				String caption;

				caption = this.app.getMessage(ApplicationMessages.FacilityContactMechanismFacility);

				facilities = this.app.getServices().getCrudServiceFacility().findAll();
				container = new BeanItemContainer<Facility>(Facility.class, facilities);

				select = new Select(caption, container);

				select.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
				select.setItemCaptionPropertyId("description");

				result = select;
			}

			return result;
		}
	}
}
