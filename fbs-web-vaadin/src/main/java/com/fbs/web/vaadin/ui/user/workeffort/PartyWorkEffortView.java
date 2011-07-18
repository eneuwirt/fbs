package com.fbs.web.vaadin.ui.user.workeffort;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.workeffort.PartyWorkEffort;
import com.fbs.dmr.universal.model.workeffort.WorkEffort;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.AnchorAware;
import com.fbs.web.vaadin.ui.common.CrudAware;
import com.fbs.web.vaadin.ui.common.ListAware;
import com.fbs.web.vaadin.ui.common.items.BeanAware;
import com.fbs.web.vaadin.ui.user.party.details.PartyContactMechanismView.Action;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
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

public class PartyWorkEffortView extends VerticalLayout implements AnchorAware<PartyWorkEffort, WorkEffort>, ListAware<PartyWorkEffort>,
        CrudAware<PartyWorkEffort>
{
    private static final long serialVersionUID = 1L;

    public enum Action
    {
        CREATE, DELETE, UNDEFINED, UPDATE;
    };

    static final String COMMENT = "comment";
    public static final String ID = "id";

    private static final String[] VISIBLE_COLUMNS =
    { ID };

    protected MyVaadinApplication app;

    private WorkEffort anchor;
    private PartyWorkEffort selectedBean;
    private Action action = Action.UNDEFINED;

    protected CrudDialog dialog;

    protected Table tableBeans;
    protected BeanItemContainer<PartyWorkEffort> beanItemContainer;

    protected Button buttonAdd;
    protected Button buttonEdit;
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

        this.dialog = new CrudDialog(this);

        this.beanItemContainer = new BeanItemContainer<PartyWorkEffort>(PartyWorkEffort.class);

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
        private PartyWorkEffortView view;

        public CreateListener(PartyWorkEffortView view)
        {
            this.view = view;
        }

        @Override
        public void buttonClick(ClickEvent event)
        {
            PartyWorkEffort bean;
            
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
        private PartyWorkEffortView view;

        public EditListener(PartyWorkEffortView view)
        {
            this.view = view;
        }

        @Override
        public void buttonClick(ClickEvent event)
        {
            PartyWorkEffort bean;
            
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
        private static final long serialVersionUID = 1L;
        static final String[] VISIBLE_FIELDS = new String[] {COMMENT};

        private MyVaadinApplication app;
        private PartyWorkEffortView view;

        private PartyWorkEffort bean;

        private Form form;
        private Button buttonSave;
        private Button buttonClose;

        public CrudDialog(PartyWorkEffortView view)
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
            this.form.setFormFieldFactory(new PartyWorkEffortFormFieldFactory(app));

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
        public void setBean(PartyWorkEffort bean)
        {
            BeanItem<PartyWorkEffort> beanItem;
            
            this.bean = bean;
            
            beanItem = new BeanItem<PartyWorkEffort>(bean);
            
            this.form.setItemDataSource(beanItem, Arrays.asList(VISIBLE_FIELDS));
        }

        @Override
        public PartyWorkEffort getBean()
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

        public void createPartyWorkEffort(PartyWorkEffort bean2)
        {
            // TODO Auto-generated method stub
            
        }

        public void updatePartyWorkEffort(PartyWorkEffort bean2)
        {
            // TODO Auto-generated method stub
            
        }
    }

    private static class PartyWorkEffortFormFieldFactory implements FormFieldFactory
    {
        private static final long serialVersionUID = 1L;
        private MyVaadinApplication app;

        public PartyWorkEffortFormFieldFactory(MyVaadinApplication app)
        {
            this.app = app;
        }

        @Override
        public Field createField(Item item, Object propertyId, Component uiContext)
        {
            // Identify the fields by their Property ID.
            Field result = null;

            String pid = (String) propertyId;

            if (COMMENT.equals(pid))
            {
                result = new TextField("Kommmenneejrh");
            }

            return result;
        }
    }
}
