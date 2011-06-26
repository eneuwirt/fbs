package com.fbs.web.vaadin.ui.user.facility;

import java.util.List;

import com.fbs.dmr.universal.model.facility.Facility;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class FacilityScreen extends ItemsListScreen<Facility>
{
    private static final long serialVersionUID = 1L;
    
	private static final String ID = "id";
	private static final String DESC = "description";

	private static final String[] VISIBLE_COLUMNS = new String[]
		{ ID, DESC };
	private static final String[] VISIBLE_FIELDS = new String[]
		{ ID, DESC };

	public FacilityScreen(MyVaadinApplication app)
	{
		super(app, Facility.class, VISIBLE_COLUMNS, VISIBLE_FIELDS);
	}

	@Override
    protected Facility createBeanInstance()
    {
	    return new Facility();
    }

	@Override
    protected List<Facility> getAllBeans() throws Exception
    {
		List<Facility> result;
		
		result = this.app.getServices().getCrudServiceFacility().findAll();
		
	    return result;
    }

	@Override
    protected Facility createBean(Facility t) throws Exception
    {
	    this.app.getServices().getCrudServiceFacility().create(t);
	    
	    return t;
    }

	@Override
    protected void updateBean(Facility t) throws Exception
    {
		this.app.getServices().getCrudServiceFacility().update(t);
    }

	@Override
    protected Facility readBean(Facility t) throws Exception
    {
		Facility result;
		
		result =  this.app.getServices().getCrudServiceFacility().read(t.getId());
		
	    return result;
    }

	@Override
    protected void deleteBean(Facility t) throws Exception
    {
		this.app.getServices().getCrudServiceFacility().delete(t.getId());
    }

	@Override
    protected FormFieldFactory getFormFieldFactory()
    {
	    return new FacilityFormFieldFactory(this.app);
    }

	private static class FacilityFormFieldFactory  implements FormFieldFactory
	{
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;

		public FacilityFormFieldFactory(MyVaadinApplication app)
		{
			this.app = app;
		}

		@Override
        public Field createField(Item item, Object propertyId, Component uiContext)
        {
			Field result = null;

			String pid = (String) propertyId;

			if (ID.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.FacilityId));

				result.setReadOnly(true);
			}
			else if (DESC.equals(pid))
			{
				result = new TextField(this.app.getMessage(ApplicationMessages.FacilityDescription));
			}
			
	        return result;
        }
	}
	
}
