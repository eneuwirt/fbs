package com.fbs.web.vaadin.ui.admin;

import java.util.List;

import com.fbs.security.model.Tenant;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.fbs.web.vaadin.ui.common.items.DetailsComponentSimple;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class TenantsScreen extends ItemsListScreen<Tenant>
{
    private static final long serialVersionUID = 1L;
    private static final String COL_ID = "id";
    private static final String COL_DESCR = "description";
    private static final String[] VISIBLE_COLUMNS = new String[]
    { COL_ID, COL_DESCR };
    private static final String[] VISIBLE_FIELDS = new String[]
    { COL_ID, COL_DESCR };

    public TenantsScreen(MyVaadinApplication app)
    {
        super(app, Tenant.class, new DetailsComponentSimple<Tenant>(app, new TenantFormFieldFactory(app), VISIBLE_FIELDS), VISIBLE_COLUMNS);
    }

    @Override
    public Tenant createBeanInstance()
    {
        Tenant result;

        result = new Tenant();
        result.setDescription("");

        return result;
    }

    @Override
    public List<Tenant> getAllBeans() throws Exception
    {
        List<Tenant> result;

        result = this.services.getTenantService().findAll();

        return result;
    }

    @Override
    public String getColumnName(String propertyId)
    {
        if (propertyId.equals(COL_ID))
            return this.app.getMessage(ApplicationMessages.TenantId);

        if (propertyId.equals(COL_DESCR))
            return this.app.getMessage(ApplicationMessages.TenantDescription);

        return propertyId;
    }

    @Override
    public void updateBean(Tenant t) throws Exception
    {
        this.services.getTenantService().update(t);
    }

    @Override
    public void deleteBean(Tenant t) throws Exception
    {
        this.services.getTenantService().delete(t);
    }

    @Override
    public Tenant createBean(Tenant tenant) throws Exception
    {
        Tenant result;

        result = this.services.getTenantService().create(tenant);

        return result;
    }

    @Override
    public Tenant readBean(Tenant t) throws Exception
    {
        Tenant result;

        result = this.services.getTenantService().read(t.getId());

        return result;
    }

    private static class TenantFormFieldFactory implements FormFieldFactory
    {
        private static final long serialVersionUID = 1L;
        private MyVaadinApplication app;

        public TenantFormFieldFactory(MyVaadinApplication app)
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
            else if (COL_DESCR.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.TenantDescription));
            }

            return result;
        }

    }
}
