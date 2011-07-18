package com.fbs.web.vaadin.ui.user.workeffort;

import java.util.List;

import com.fbs.dmr.universal.model.workeffort.WorkEffort;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;

public class WorkEffortScreen extends ItemsListScreen<WorkEffort>
{
	private static final long serialVersionUID = 1L;
	private static final String ID = "id";
	private static final String DESC = "description";
	private static final String NAME = "name";

	private static final String[] VISIBLE_COLUMNS = new String[]
		{ ID, NAME };

	public WorkEffortScreen(MyVaadinApplication app)
	{
		super(app, WorkEffort.class, new WorkEffortDetails(app), VISIBLE_COLUMNS);
	}

	@Override
	public List<WorkEffort> getAllBeans() throws Exception
	{
		List<WorkEffort> result;
		
		result = this.app.getServices().getCrudServiceWorkEffort().findAll();
		
		return result;
	}

	@Override
	public WorkEffort createBeanInstance()
	{
		return new WorkEffort();
	}

	@Override
	public WorkEffort createBean(WorkEffort t) throws Exception
	{
	    this.app.getServices().getCrudServiceWorkEffort().create(t);
	    
		return t;
	}

	@Override
	public void updateBean(WorkEffort t) throws Exception
	{
	    this.app.getServices().getCrudServiceWorkEffort().update(t);
	}

	@Override
	public WorkEffort readBean(WorkEffort t) throws Exception
	{
		WorkEffort result;
		
		result = this.app.getServices().getCrudServiceWorkEffort().read(t.getId());
		
		return result;
	}

	@Override
	public void deleteBean(WorkEffort t) throws Exception
	{
	    this.app.getServices().getCrudServiceWorkEffort().delete(t.getId());
	}

	@Override
	public String getColumnName(String propertyId)
	{
		if (propertyId.equals(ID))
			return this.app.getMessage(ApplicationMessages.WorkEffortId);

		if (propertyId.equals(DESC))
			return this.app.getMessage(ApplicationMessages.WorkEffortDescription);

		if (propertyId.equals(NAME))
			return this.app.getMessage(ApplicationMessages.WorkEffortName);

		return propertyId;
	}
}
