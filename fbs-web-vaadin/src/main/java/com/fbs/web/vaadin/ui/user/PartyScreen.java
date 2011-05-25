package com.fbs.web.vaadin.ui.user;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.fbs.dmr.universal.model.party.Party;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class PartyScreen extends ItemsListScreen<Party>
{
    private static final long serialVersionUID = 1L;
    private static final String COL_ID = "id";
	private static final String COL_NAME = "name";
	private static String[] visibleColumns = new String[] { COL_ID, COL_NAME };
	private static String[] visibleItemProperties = new String[] { COL_ID, COL_NAME };


	public PartyScreen(MyVaadinApplication app)
    {
	    super(app, Party.class);
    }

	@Override
    protected Party createBeanInstance()
    {	
		return new Party();
    }

	@Override
    protected List<Party> getAllBeans() throws Exception
    {
		List<Party> result = this.app.getCrudPartyService().findAll();
		
	    return result;
    }

	@Override
    protected Party createBean(Party t) throws Exception
    {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    protected void updateBean(Party t) throws Exception
    {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    protected Party readBean(Party t) throws Exception
    {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    protected void deleteBean(Party t) throws Exception
    {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    protected String[] getVisibleColumns()
    {
		return visibleColumns;
    }

	@Override
    protected Collection<String> getVisibleItemProperties()
    {
		return Arrays.asList(visibleItemProperties);
    }

	@Override
    protected FormFieldFactory getFormFieldFactory()
    {
	    return new PartyFormFieldFactory(this.app);
    }

	private static class PartyFormFieldFactory implements FormFieldFactory
	{
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;
		
		public PartyFormFieldFactory(MyVaadinApplication app)
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
				result = new TextField(this.app.getMessage(ApplicationMessages.TenantId));
				
				result.setReadOnly(true);
			}
			else if (COL_NAME.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.TenantDescription));
			}
			
			
			return result;
		}

	}
}
