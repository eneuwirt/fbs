package com.fbs.web.vaadin.ui.user.workeffort;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.workeffort.WorkEffortPartyAssignment;
import com.fbs.dmr.universal.model.workeffort.WorkEffort;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.AnchorAware;
import com.fbs.web.vaadin.ui.common.CrudAware;
import com.fbs.web.vaadin.ui.common.ListAware;
import com.fbs.web.vaadin.ui.common.items.BeanAware;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Select;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class WorkEffortPartyAssignmentView extends VerticalLayout implements AnchorAware<WorkEffortPartyAssignment, WorkEffort>,
        ListAware<WorkEffortPartyAssignment>, CrudAware<WorkEffortPartyAssignment>
{
    private static final long serialVersionUID = 1L;

    static final String MINUTES = "minutes";
    static final String COMMENT = "comment";
    static final String FROM_DATE = "fromDate";
    static final String PARTY = "party";
    static final String PARTY_NAME = "party.name";
    static final String TO_DATE = "toDate";
    public static final String ID = "id";

    private static final String[] VISIBLE_COLUMNS =
    { ID, PARTY_NAME };

    private static final String[] NESTED_PROPERTIES =
    { PARTY_NAME };

    public enum Action
    {
        CREATE, DELETE, UNDEFINED, UPDATE;
    };

    protected MyVaadinApplication app;

    private WorkEffort anchor;
    private WorkEffortPartyAssignment selectedBean;
    private Action action = Action.UNDEFINED;

    protected String createDialogCaption = "";
    protected String updateDialogCaption = "";
    protected CrudDialog dialog;

    protected Table tableBeans;
    protected BeanItemContainer<WorkEffortPartyAssignment> beanItemContainer;

    protected Button buttonAdd;
    protected Button buttonEdit;
    protected Button buttonDelete;

    //
    protected String[] visibleColumns = new String[]
    {};
    protected String[] nestedContainerProperties = new String[]
    {};

    public WorkEffortPartyAssignmentView(MyVaadinApplication app)
    {
        super();

        this.app = app;
        this.visibleColumns = VISIBLE_COLUMNS;
        this.nestedContainerProperties = NESTED_PROPERTIES;

        this.createDialogCaption = this.app.getMessage(ApplicationMessages.CommonCreate);
        this.updateDialogCaption = this.app.getMessage(ApplicationMessages.CommonUpdate);
        this.dialog = new CrudDialog(this);

        this.beanItemContainer = new BeanItemContainer<WorkEffortPartyAssignment>(WorkEffortPartyAssignment.class);
        for (String p : this.nestedContainerProperties)
        {
            this.beanItemContainer.addNestedContainerProperty(p);
        }

        this.tableBeans = new Table();
        this.tableBeans.setSelectable(true);
        this.tableBeans.setMultiSelect(false);
        this.tableBeans.setImmediate(true);
        this.tableBeans.setContainerDataSource(beanItemContainer);
        this.tableBeans.addListener(new TableSelectListener(this));

        this.buttonAdd = new Button("Add", new CreateListener(this));

        this.buttonEdit = new Button("Edit", new EditListener(this));
        this.buttonEdit.setEnabled(false);

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
        buttonRow.addComponent(this.buttonEdit);
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
        List<WorkEffortPartyAssignment> assignments;

        assignments = this.app.getServices().getCrudServiceWorkEffortPartyAssignment().findByWorkEffort(this.anchor.getId());

        this.beanItemContainer.removeAllItems();
        for (WorkEffortPartyAssignment a : assignments)
        {
            this.beanItemContainer.addBean(a);
        }
    }

    @Override
    public List<WorkEffortPartyAssignment> getAllBeans() throws Exception
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
    public WorkEffortPartyAssignment createBeanInstance()
    {
        WorkEffortPartyAssignment result = null;

        result = new WorkEffortPartyAssignment();
        result.setWorkEffort(this.anchor);

        return result;
    }

    @Override
    public WorkEffortPartyAssignment createBean(WorkEffortPartyAssignment t) throws Exception
    {

        return null;
    }

    @Override
    public void updateBean(WorkEffortPartyAssignment t) throws Exception
    {
        // TODO Auto-generated method stub

    }

    @Override
    public WorkEffortPartyAssignment readBean(WorkEffortPartyAssignment t) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteBean(WorkEffortPartyAssignment t) throws Exception
    {
        // TODO Auto-generated method stub

    }

    private static class TableSelectListener implements Property.ValueChangeListener
    {
        private static final long serialVersionUID = 1L;
        private WorkEffortPartyAssignmentView view;

        public TableSelectListener(WorkEffortPartyAssignmentView view)
        {
            this.view = view;
        }

        @Override
        public void valueChange(ValueChangeEvent event)
        {
            this.view.selectedBean = (WorkEffortPartyAssignment) this.view.tableBeans.getValue();

            if (this.view.selectedBean != null)
            {
                this.view.buttonDelete.setEnabled(true);
                this.view.buttonEdit.setEnabled(true);
            }
            else
            {
                this.view.buttonDelete.setEnabled(false);
                this.view.buttonEdit.setEnabled(false);
            }
        }
    }

    private static class CreateListener implements Button.ClickListener
    {
        private static final long serialVersionUID = 1L;
        private WorkEffortPartyAssignmentView view;

        public CreateListener(WorkEffortPartyAssignmentView view)
        {
            this.view = view;
        }

        @Override
        public void buttonClick(ClickEvent event)
        {
            WorkEffortPartyAssignment bean;
            
            this.view.dialog.setCaption(this.view.createDialogCaption);

            this.view.action = Action.CREATE;

            bean = this.view.createBeanInstance();

            this.view.dialog.setBean(bean);

            if (view.dialog.getParent() != null)
            {
                // window is already showing
                view.getWindow().showNotification("Window is already open");
            }
            else
            {
                // Open the subwindow by adding it to the parent window
                view.getWindow().addWindow(view.dialog);
            }
        }
    }

    private static class EditListener implements Button.ClickListener
    {
        private static final long serialVersionUID = 1L;
        private WorkEffortPartyAssignmentView view;

        public EditListener(WorkEffortPartyAssignmentView view)
        {
            this.view = view;
        }

        @Override
        public void buttonClick(ClickEvent event)
        {
            WorkEffortPartyAssignment bean;
            
            this.view.dialog.setCaption(this.view.updateDialogCaption);

            this.view.action = Action.UPDATE;

            bean = this.view.selectedBean;

            this.view.dialog.setBean(bean);

            if (view.dialog.getParent() != null)
            {
                // window is already showing
                view.getWindow().showNotification("Window is already open");
            }
            else
            {
                // Open the subwindow by adding it to the parent window
                view.getWindow().addWindow(view.dialog);
            }
        }
    }

    private static class DeleteListener implements Button.ClickListener
    {
        private static final long serialVersionUID = 1L;
        private WorkEffortPartyAssignmentView view;

        public DeleteListener(WorkEffortPartyAssignmentView view)
        {
            this.view = view;
        }

        @Override
        public void buttonClick(ClickEvent event)
        {

        }
    }

    private static class CrudDialog extends Window implements BeanAware<WorkEffortPartyAssignment>
    {
        private static final long serialVersionUID = 1L;
        static final String[] VISIBLE_FIELDS = new String[]
        { PARTY, FROM_DATE, TO_DATE, MINUTES, COMMENT };

        private MyVaadinApplication app;
        private WorkEffortPartyAssignmentView view;

        private WorkEffortPartyAssignment bean;

        private Form form;
        private Button buttonSave;
        private Button buttonClose;

        public CrudDialog(WorkEffortPartyAssignmentView view)
        {
            this.app = view.app;
            this.view = view;

            this.setModal(true);
            this.setClosable(true);
            // this.setHeight("80%");
            this.setWidth("50%");

            VerticalLayout layout = (VerticalLayout) this.getContent();
            layout.setMargin(true);
            layout.setSpacing(true);

            this.form = new Form();
            this.form.setImmediate(true);
            this.form.setSizeFull();
            this.form.setFormFieldFactory(new WorkEffortPartyAssignmentFormFieldFactory(app));

            DialogListener dl = new DialogListener(this);
            this.buttonSave = new Button(this.app.getMessage(ApplicationMessages.CommonSave), dl);
            this.buttonClose = new Button(this.app.getMessage(ApplicationMessages.CommonCancel), dl);

            HorizontalLayout buttonRow = new HorizontalLayout();
            buttonRow.setMargin(true);
            buttonRow.setSpacing(true);
            buttonRow.setSizeFull();

            buttonRow.addComponent(buttonSave);
            buttonRow.addComponent(buttonClose);

            layout.addComponent(this.form);

            layout.addComponent(buttonRow);
        }

        @Override
        public void setBean(WorkEffortPartyAssignment bean)
        {
            BeanItem<WorkEffortPartyAssignment> beanItem;

            this.bean = bean;

            beanItem = new BeanItem<WorkEffortPartyAssignment>(bean);

            this.form.setItemDataSource(beanItem, Arrays.asList(VISIBLE_FIELDS));
        }

        @Override
        public WorkEffortPartyAssignment getBean()
        {
            return this.bean;
        }

        private static class DialogListener implements Button.ClickListener
        {
            private static final long serialVersionUID = 1L;
            Logger logger = Logger.getLogger(DialogListener.class.getName());

            private CrudDialog dialog;

            public DialogListener(CrudDialog dialog)
            {
                this.dialog = dialog;
            }

            @Override
            public void buttonClick(ClickEvent event)
            {
                if (event.getButton().equals(dialog.buttonSave))
                {
                    try
                    {
                        switch (this.dialog.view.action)
                        {
                            case CREATE:
                                this.dialog.createPartyWorkEffort(this.dialog.bean);

                            case UPDATE:
                                this.dialog.updatePartyWorkEffort(this.dialog.bean);

                        }
                    }
                    catch (Exception e)
                    {
                        logger.log(Level.SEVERE, "Exception occured: " + e.getMessage());
                    }
                }

                this.dialog.view.updateComponents();

                // close the window by removing it from the parent window
                this.dialog.getParent().removeWindow(dialog);
            }
        }

        public void createPartyWorkEffort(WorkEffortPartyAssignment bean)
        {
            this.app.getServices().getCrudServiceWorkEffortPartyAssignment().create(bean);
        }

        public void updatePartyWorkEffort(WorkEffortPartyAssignment bean2)
        {
            // TODO Auto-generated method stub

        }
    }

    private static class WorkEffortPartyAssignmentFormFieldFactory implements FormFieldFactory
    {
        private static final long serialVersionUID = 1L;
        private MyVaadinApplication app;

        public WorkEffortPartyAssignmentFormFieldFactory(MyVaadinApplication app)
        {
            this.app = app;
        }

        @Override
        public Field createField(Item item, Object propertyId, Component uiContext)
        {
            // Identify the fields by their Property ID.
            Field result = null;

            String pid = (String) propertyId;

            if (MINUTES.equals(pid))
            {
                result = new TextField(this.app.getMessage(ApplicationMessages.WorkEffortPartyAssignmentMinutes));
            }
            if (COMMENT.equals(pid))
            {
                result = new TextArea(this.app.getMessage(ApplicationMessages.WorkEffortPartyAssignmentComment));
            }
            else if (FROM_DATE.equals(pid))
            {
                DateField date;

                date = new DateField(this.app.getMessage(ApplicationMessages.WorkEffortPartyAssignmentFromDate));
                date.setResolution(DateField.RESOLUTION_DAY);

                result = date;
            }
            else if (TO_DATE.equals(pid))
            {
                DateField date;

                date = new DateField(this.app.getMessage(ApplicationMessages.WorkEffortPartyAssignmentToDate));
                date.setResolution(DateField.RESOLUTION_DAY);

                result = date;
            }
            else if (PARTY.equals(pid))
            {
                List<Party> parties;
                BeanItemContainer<Party> container;
                Select select;

                parties = this.app.getServices().getCrudServiceParty().findAll();

                container = new BeanItemContainer<Party>(Party.class, parties);

                select = new Select(this.app.getMessage(ApplicationMessages.WorkEffortPartyAssignmentParty), container);
                select.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
                select.setItemCaptionPropertyId("name");

                result = select;
            }

            return result;
        }
    }
}
