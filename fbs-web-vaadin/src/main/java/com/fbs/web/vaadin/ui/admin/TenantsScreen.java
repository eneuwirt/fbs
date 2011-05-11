package com.fbs.web.vaadin.ui.admin;

import java.util.List;

import com.fbs.security.model.Tenant;
import com.fbs.web.vaadin.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;

public class TenantsScreen extends ItemsListScreen<Tenant>
{
	private static final long serialVersionUID = 1L;
	private static final String COL_ID = "id";
	private static final String COL_DESCR = "description";
	private static String[] visibleColumns = new String[] { COL_ID, COL_DESCR };


	public TenantsScreen(MyVaadinApplication app)
	{
		super(app, Tenant.class);
	}


	@Override
	protected Tenant createBeanInstance()
	{
		Tenant result;

		result = new Tenant();
		result.setDescription("Bidde pflegen");

		return result;
	}


	@Override
	protected List<Tenant> getAllBeans()
	{
		List<Tenant> result;

		result = this.app.getTenantService().findAll();

		return result;
	}


	@Override
	protected String[] getVisibleColumns()
	{
		return visibleColumns;
	}


	@Override
	protected String getColumnName(String propertyId)
	{
		if (propertyId.equals(COL_ID))
			return this.app.getMessage(ApplicationMessages.TenantId);

		if (propertyId.equals(COL_DESCR))
			return this.app.getMessage(ApplicationMessages.TenantDescription);
		
		return propertyId;
	}

}
