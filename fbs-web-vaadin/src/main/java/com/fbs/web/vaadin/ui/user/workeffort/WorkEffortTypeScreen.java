package com.fbs.web.vaadin.ui.user.workeffort;

import java.util.List;

import com.fbs.dmr.universal.model.workeffort.WorkEffortType;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.fbs.web.vaadin.ui.common.items.DetailsComponentSimple;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class WorkEffortTypeScreen extends ItemsListScreen<WorkEffortType>
{
    private static final long serialVersionUID = 1L;
    private static final String ID = "id";
    private static final String DESC = "description";
    private static final String STANDARD_WORK_MINUTES = "standardWorkMinutes";
    private static final String[] VISIBLE_COLUMNS = new String[]
    { ID, DESC };
    private static final String[] VISIBLE_FIELDS = new String[]
    { ID, DESC, STANDARD_WORK_MINUTES };

    public WorkEffortTypeScreen(MyVaadinApplication app)
    {
        super(app, WorkEffortType.class, new DetailsComponentSimple<WorkEffortType>(app, new WorkEffortTypeFormFieldFactory(app),
                VISIBLE_FIELDS), VISIBLE_COLUMNS);
    }

    @Override
    public WorkEffortType createBeanInstance()
    {
        return new WorkEffortType();
    }

    @Override
    public List<WorkEffortType> getAllBeans() throws Exception
    {
        return this.app.getServices().getCrudServiceWorkEffortType().findAll();
    }

    @Override
    public WorkEffortType createBean(WorkEffortType t) throws Exception
    {
        this.app.getServices().getCrudServiceWorkEffortType().create(t);

        return t;
    }

    @Override
    public void updateBean(WorkEffortType t) throws Exception
    {
        this.app.getServices().getCrudServiceWorkEffortType().update(t);
    }

    @Override
    public WorkEffortType readBean(WorkEffortType t) throws Exception
    {
        WorkEffortType result;

        result = this.app.getServices().getCrudServiceWorkEffortType().read(t.getId());

        return result;
    }

    @Override
    public void deleteBean(WorkEffortType t) throws Exception
    {
        this.app.getServices().getCrudServiceWorkEffortType().delete(t.getId());
    }

    @Override
    public String getColumnName(String propertyId)
    {
        if (propertyId.equals(ID))
            return this.app.getMessage(ApplicationMessages.WorkEffortTypeId);

        if (propertyId.equals(DESC))
            return this.app.getMessage(ApplicationMessages.WorkEffortTypeDescription);

        if (propertyId.equals(STANDARD_WORK_MINUTES))
            return this.app.getMessage(ApplicationMessages.WorkEffortTypeStandardWorkMunutes);

        return propertyId;
    }

    private static class WorkEffortTypeFormFieldFactory implements FormFieldFactory
    {
        private static final long serialVersionUID = 1L;
        private MyVaadinApplication app;

        public WorkEffortTypeFormFieldFactory(MyVaadinApplication app)
        {
            this.app = app;
        }

        @Override
        public Field createField(Item item, Object propertyId, Component uiContext)
        {
            // Identify the fields by their Property ID.
            TextField result = null;

            String pid = (String) propertyId;

            if (ID.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.WorkEffortTypeId));

                result.setReadOnly(true);
            }
            else if (DESC.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.WorkEffortTypeDescription));
            }
            else if (STANDARD_WORK_MINUTES.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.WorkEffortTypeStandardWorkMunutes));
            }

            return result;
        }
    }
}
