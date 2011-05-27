package com.fbs.web.vaadin.ui.user.party;

import java.util.Collection;
import java.util.List;

import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.ui.FormFieldFactory;

public class OrganizationScreen  extends ItemsListScreen<Organization>
{
    private static final long serialVersionUID = 1L;

	public OrganizationScreen(MyVaadinApplication app)
    {
	    super(app, Organization.class);
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
    protected String[] getVisibleColumns()
    {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    protected Collection<String> getVisibleItemProperties()
    {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    protected FormFieldFactory getFormFieldFactory()
    {
	    // TODO Auto-generated method stub
	    return null;
    }

}
