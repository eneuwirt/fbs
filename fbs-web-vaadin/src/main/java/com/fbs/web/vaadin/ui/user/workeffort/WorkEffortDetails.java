package com.fbs.web.vaadin.ui.user.workeffort;

import java.util.Arrays;
import java.util.logging.Logger;

import com.fbs.dmr.universal.model.workeffort.WorkEffort;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.items.BeanAware;
import com.fbs.web.vaadin.ui.common.items.DetailsAware;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;

public class WorkEffortDetails extends TabSheet implements DetailsAware<WorkEffort>, BeanAware<WorkEffort>
{
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(WorkEffortDetails.class.getName());
    private static final String ID = "id";
    private static final String DESC = "description";
    private static final String NAME = "name";
    private static final String SCHEDULED_START_DATE = "scheduledStartDate";
    private static final String[] VISIBLE_FIELDS = new String[]
    { ID, NAME, DESC, SCHEDULED_START_DATE };

    protected MyVaadinApplication app;

    protected String[] visibleFields;

    protected WorkEffort bean;

    protected Form form;

    protected WorkEffortPartyAssignmentView workEffortPartyAssignmentView;

    public WorkEffortDetails(MyVaadinApplication app)
    {
        super();

        // A layout structure used for composition
        this.app = app;
        this.visibleFields = VISIBLE_FIELDS;

        this.form = new Form();
        this.form.setImmediate(true);
        this.form.setFormFieldFactory(new WorkEffortFormFieldFactory(this.app));

        this.workEffortPartyAssignmentView = new WorkEffortPartyAssignmentView(this.app);

        this.createComponents();

        this.setSizeFull();
    }

    private void createComponents()
    {
        String captionMasterData;

        captionMasterData = this.app.getMessage(ApplicationMessages.WorkEffortMasterData);

        this.addTab(this.form, captionMasterData, null);
        this.addTab(this.workEffortPartyAssignmentView, "Hoho", null);
    }

    @Override
    public void setBean(WorkEffort workEffort)
    {
        BeanItem<WorkEffort> beanItem;

        if (workEffort == null)
        {
            return;
        }

        this.bean = workEffort;
        beanItem = new BeanItem<WorkEffort>(this.bean);

        this.form.setItemDataSource(beanItem, Arrays.asList(this.visibleFields));

        this.workEffortPartyAssignmentView.setAnchor(workEffort);
    }

    @Override
    public WorkEffort getBean()
    {
        return this.bean;
    }

    @Override
    public void createBeanDetails(WorkEffort t)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void readBeanDetails(WorkEffort t)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateBeanDetails(WorkEffort t)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteBeanDetails(WorkEffort t)
    {
        // TODO Auto-generated method stub

    }

    private static class WorkEffortFormFieldFactory implements FormFieldFactory
    {
        private static final long serialVersionUID = 1L;
        private MyVaadinApplication app;

        public WorkEffortFormFieldFactory(MyVaadinApplication app)
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
                result = new TextField(this.app.getMessage(ApplicationMessages.WorkEffortId));

                result.setReadOnly(true);
            }
            else if (DESC.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.WorkEffortDescription));
            }
            else if (NAME.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.WorkEffortName));
            }
            else if (SCHEDULED_START_DATE.equals(pid))
            {
                DateField date;

                date = new DateField(this.app.getMessage(ApplicationMessages.WorkEffortScheduledStartDate));
                date.setResolution(DateField.RESOLUTION_DAY);

                result = date;
            }

            return result;
        }
    }
}
