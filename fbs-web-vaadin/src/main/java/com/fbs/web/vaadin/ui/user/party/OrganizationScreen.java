package com.fbs.web.vaadin.ui.user.party;

import java.util.List;
import java.util.logging.Logger;

import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.fbs.web.vaadin.ui.user.party.details.OrganizationDetails;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;


public class OrganizationScreen extends ItemsListScreen<Organization>
{
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(OrganizationScreen.class.getName());
	private static final String ID = "id";
	private static final String NAME = "name";

	private static final String[] VISIBLE_COLUMNS = new String[] { ID, NAME };
	private static final String[] VISIBLE_FIELDS = new String[] { ID, NAME };


	public OrganizationScreen(MyVaadinApplication app)
	{
		super(app, Organization.class, new OrganizationDetails(app, new OrganizationFormFieldFactory(app), VISIBLE_FIELDS), VISIBLE_COLUMNS);
	}


	@Override
	public Organization createBeanInstance()
	{
		return new Organization();
	}


	@Override
	public List<Organization> getAllBeans() throws Exception
	{
		List<Organization> organizations;

		organizations = this.services.getCrudServiceOrganization().findAll();

		return organizations;
	}


	@Override
	public Organization createBean(Organization t) throws Exception
	{		
		this.services.getCrudServiceOrganization().create(t);
		
		this.component.createBeanDetails(t);

		return t;
	}


	@Override
	public void updateBean(Organization t) throws Exception
	{		
		logger.info(">updateBean");
		
		this.services.getCrudServiceOrganization().update(t);
		
		this.component.updateBeanDetails(t);		
		
		logger.info("<updateBean");
	}


	@Override
	public Organization readBean(Organization t) throws Exception
	{
		Organization org;

		org = this.services.getCrudServiceOrganization().read(t.getId());
		
		this.component.readBeanDetails(org);

		return org;
	}


	@Override
	public void deleteBean(Organization t) throws Exception
	{
	    this.component.deleteBeanDetails(t);
	    
		this.services.getCrudServiceOrganization().delete(t.getId());
	}


	@Override
	public String getColumnName(String propertyId)
	{
		if (propertyId.equals(ID))
			return this.app.getMessage(ApplicationMessages.OrgId);

		if (propertyId.equals(NAME))
			return this.app.getMessage(ApplicationMessages.OrgName);

		return propertyId;
	}


	private static class OrganizationFormFieldFactory implements FormFieldFactory
    {
        private static final long serialVersionUID = 1L;
        private MyVaadinApplication app;


        public OrganizationFormFieldFactory(MyVaadinApplication app)
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
                result = new TextField(this.app.getMessage(ApplicationMessages.OrgId));

                result.setReadOnly(true);
            }
            else if (NAME.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.OrgName));
            }
            

            return result;
        }
    }
}
