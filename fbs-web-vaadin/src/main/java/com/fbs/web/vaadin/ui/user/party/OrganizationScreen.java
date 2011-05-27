package com.fbs.web.vaadin.ui.user.party;

import java.util.List;

import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class OrganizationScreen  extends ItemsListScreen<Organization>
{
    private static final long serialVersionUID = 1L;
    private static final String COL_ID = "id";
    private static final String[] VISIBLE_COLUMNS = new String[] { COL_ID};
	private static final String[] VISIBLE_ITEM_PROPERTIES =  new String[] { COL_ID};

	public OrganizationScreen(MyVaadinApplication app)
    {
	    super(app, Organization.class, new PartyForm(), VISIBLE_COLUMNS, VISIBLE_ITEM_PROPERTIES);
    }

	@Override
    protected Organization createBeanInstance()
    {
	    return new Organization();
    }

	@Override
    protected List<Organization> getAllBeans() throws Exception
    {
	    List<Organization> organizations;
	    
	    organizations = this.services.getCrudServiceOrganization().findAll();
	    
	    return organizations;
    }

	@Override
    protected Organization createBean(Organization t) throws Exception
    {
		this.services.getCrudServiceOrganization().create(t);
		
	    return t;
    }

	@Override
    protected void updateBean(Organization t) throws Exception
    {
		this.services.getCrudServiceOrganization().update(t);
    }

	@Override
    protected Organization readBean(Organization t) throws Exception   
    {
	    Organization org;
	    
	    org = this.services.getCrudServiceOrganization().read(t.getId());
	    
		return org;
    }

	@Override
    protected void deleteBean(Organization t) throws Exception
    {
		this.services.getCrudServiceOrganization().delete(t.getId());
    }

	@Override
	protected String getColumnName(String propertyId)
	{
		if (propertyId.equals(COL_ID))
			return this.app.getMessage(ApplicationMessages.OrgId);


		return propertyId;
	}

	@Override
    protected FormFieldFactory getFormFieldFactory()
    {
	    return new OrganizationFormFieldFactory(this.app);
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
			TextField result = null;

			String pid = (String) propertyId;

			if (COL_ID.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.PersonId));

				result.setReadOnly(true);
			}

			return result;
		}
	}
}
