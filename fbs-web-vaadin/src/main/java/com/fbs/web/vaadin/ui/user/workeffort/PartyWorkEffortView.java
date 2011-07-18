package com.fbs.web.vaadin.ui.user.workeffort;

import java.util.List;

import com.fbs.dmr.universal.model.workeffort.PartyWorkEffort;
import com.fbs.dmr.universal.model.workeffort.WorkEffort;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.ui.common.AnchorAware;
import com.fbs.web.vaadin.ui.common.CrudAware;
import com.fbs.web.vaadin.ui.common.ListAware;
import com.fbs.web.vaadin.ui.common.items.BeanAware;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class PartyWorkEffortView extends VerticalLayout implements AnchorAware<PartyWorkEffort, WorkEffort>, ListAware<PartyWorkEffort>, CrudAware<PartyWorkEffort>
{
    private static final long serialVersionUID = 1L;

    public static final String ID = "id";

    private static final String[] VISIBLE_COLUMNS =
    { ID };

    protected MyVaadinApplication app;

    private WorkEffort anchor;
    private PartyWorkEffort selectedBean;

    protected Table tableBeans;
    protected BeanItemContainer<PartyWorkEffort> beanItemContainer;

    protected Button buttonAdd;
    protected Button buttonDelete;

    //
    protected String[] visibleColumns = new String[]
    {};
    protected String[] nestedContainerProperties = new String[]
    {};

    public PartyWorkEffortView(MyVaadinApplication app)
    {
        super();

        this.app = app;
        this.visibleColumns = VISIBLE_COLUMNS;

        this.beanItemContainer = new BeanItemContainer<PartyWorkEffort>(PartyWorkEffort.class);

        this.tableBeans = new Table();
        this.tableBeans.setSelectable(true);
        this.tableBeans.setMultiSelect(false);
        this.tableBeans.setImmediate(true);
        this.tableBeans.setContainerDataSource(beanItemContainer);
        this.tableBeans.addListener(new TableSelectListener(this));

        this.buttonAdd = new Button("Add", new CreateListener(this));

        this.buttonDelete = new Button("Delete", new DeleteListener(this));
        this.buttonDelete.setEnabled(false);
        this.initLayout();
    }

    private void initLayout()
    {
        HorizontalLayout buttonRow;

        this.setSizeFull();

        this.tableBeans.setSizeFull();
        this.tableBeans.setVisibleColumns(this.visibleColumns);
        // Set nicer header names
        for (String propertyId : this.visibleColumns)
        {
            String columnName = this.getColumnName(propertyId);

            this.tableBeans.setColumnHeader(propertyId, columnName);
        }

        buttonRow = new HorizontalLayout();
        buttonRow.setMargin(true);

        buttonRow.addComponent(this.buttonAdd);
        buttonRow.addComponent(this.buttonDelete);

        this.addComponent(this.tableBeans);
        this.addComponent(buttonRow);

        this.setExpandRatio(this.tableBeans, 1.0f);
    }

    @Override
    public void setAnchor(WorkEffort anchor)
    {
        this.anchor = anchor;
    }

    @Override
    public void updateComponents()
    {

    }

    @Override
    public List<PartyWorkEffort> getAllBeans() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getColumnName(String pid)
    {

        return pid;
    }
    
    @Override
    public PartyWorkEffort createBeanInstance()
    {
        PartyWorkEffort result = null;
        
        result = new PartyWorkEffort();
        
        return result;
    }

    @Override
    public PartyWorkEffort createBean(PartyWorkEffort t) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateBean(PartyWorkEffort t) throws Exception
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public PartyWorkEffort readBean(PartyWorkEffort t) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteBean(PartyWorkEffort t) throws Exception
    {
        // TODO Auto-generated method stub
        
    }

    private static class TableSelectListener implements Property.ValueChangeListener
    {
        private static final long serialVersionUID = 1L;
        private PartyWorkEffortView view;

        public TableSelectListener(PartyWorkEffortView view)
        {
            this.view = view;
        }

        @Override
        public void valueChange(ValueChangeEvent event)
        {
            this.view.selectedBean = (PartyWorkEffort) this.view.tableBeans.getValue();

            if (this.view.selectedBean != null)
            {
                this.view.buttonDelete.setEnabled(true);
            }
            else
            {
                this.view.buttonDelete.setEnabled(false);
            }
        }
    }

    private static class CreateListener implements Button.ClickListener
    {
        private static final long serialVersionUID = 1L;
        private PartyWorkEffortView view;

        public CreateListener(PartyWorkEffortView view)
        {
            this.view = view;
        }

        @Override
        public void buttonClick(ClickEvent event)
        {

        }
    }

    private static class DeleteListener implements Button.ClickListener
    {
        private static final long serialVersionUID = 1L;
        private PartyWorkEffortView view;

        public DeleteListener(PartyWorkEffortView view)
        {
            this.view = view;
        }

        @Override
        public void buttonClick(ClickEvent event)
        {

        }
    }

    private static class CrudDialog extends Window implements BeanAware<PartyWorkEffort>
    {
        public CrudDialog()
        {

        }

        @Override
        public void setBean(PartyWorkEffort t)
        {
            // TODO Auto-generated method stub

        }

        @Override
        public PartyWorkEffort getBean()
        {
            // TODO Auto-generated method stub
            return null;
        }

    }
}
