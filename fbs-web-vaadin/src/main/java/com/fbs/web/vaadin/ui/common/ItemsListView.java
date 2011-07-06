package com.fbs.web.vaadin.ui.common;

import java.util.logging.Logger;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public abstract class ItemsListView<T, A> extends Panel implements ListView<T, A>
{
    private static final long serialVersionUID = 1L;

    protected MyVaadinApplication app;
    protected Class<T> clazz;

    protected A anchor;

    protected T selectedBean;

    protected Table tableBeans;
    protected BeanItemContainer<T> beanItemContainer;

    protected Button buttonAdd;
    protected Button buttonEdit;
    protected Button buttonDelete;
    //
    protected CRUDDialog<T, A> dialog;
    //
    protected String[] visibleColumns;
    protected String[] visibleFields;

    public ItemsListView(MyVaadinApplication app, Class<T> clazz, FormFieldFactory formFieldFactory, String[] visibleColumns,
            String[] visibleFields)
    {
        this.app = app;
        this.clazz = clazz;
        this.visibleColumns = visibleColumns;
        this.visibleFields = visibleFields;

        this.beanItemContainer = new BeanItemContainer<T>(this.clazz);

        this.tableBeans = new Table();
        this.tableBeans.setSelectable(true);
        this.tableBeans.setImmediate(true);
        this.tableBeans.setContainerDataSource(beanItemContainer);
        this.tableBeans.addListener(new TableSelectListener<T, A>(this));

        this.buttonAdd = new Button("Add", new AddListener<T, A>(this));

        this.buttonEdit = new Button("Ändern", new AddListener<T, A>(this));
        this.buttonEdit.setEnabled(false);

        this.buttonDelete = new Button("-", new DeleteListener<T, A>(this));
        this.buttonDelete.setEnabled(false);

        this.dialog = new CRUDDialog<T, A>(this, formFieldFactory);
        this.dialog.setModal(true);

        this.initLayout();
    }

    private void initLayout()
    {
        HorizontalLayout buttonRow;

        this.setSizeFull();
        this.setScrollable(true);

        this.tableBeans.setSizeFull();
        this.tableBeans.setVisibleColumns(this.visibleColumns);
        // Set nicer header names
        for (String propertyId : this.visibleColumns)
        {
            String columnName = this.getColumnName(propertyId);

            this.tableBeans.setColumnHeader(propertyId, columnName);
        }

        buttonRow = new HorizontalLayout();
        buttonRow.setWidth("100%");

        buttonRow.addComponent(this.buttonAdd);
        buttonRow.addComponent(this.buttonEdit);
        buttonRow.addComponent(this.buttonDelete);

        this.addComponent(this.tableBeans);
        this.addComponent(buttonRow);
    }

    @Override
    public void setAnchor(A anchor)
    {
        this.anchor = anchor;
        this.updateComponents();
    }

    private static class TableSelectListener<T, A> implements Property.ValueChangeListener
    {
        private static final long serialVersionUID = 1L;
        private ItemsListView<T, A> view;

        public TableSelectListener(ItemsListView<T, A> view)
        {
            this.view = view;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void valueChange(ValueChangeEvent event)
        {
            this.view.buttonDelete.setEnabled(true);

            this.view.buttonEdit.setEnabled(true);

            this.view.selectedBean = (T) this.view.tableBeans.getValue();
        }
    }

    private static class DeleteListener<T, A> implements Button.ClickListener
    {
        private static final long serialVersionUID = 1L;
        private static final Logger logger = Logger.getLogger(DeleteListener.class.getName());
        private ItemsListView<T, A> view;

        public DeleteListener(ItemsListView<T, A> view)
        {
            this.view = view;
        }

        @Override
        public void buttonClick(ClickEvent event)
        {
            try
            {
                this.view.deleteBean(this.view.selectedBean);

                this.view.tableBeans.removeItem(this.view.selectedBean);
                
                this.view.tableBeans.select(null);
                
                // we have to repopulate table
                this.view.updateComponents();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private static class AddListener<T, A> implements Button.ClickListener
    {
        private static final long serialVersionUID = 1L;
        private ItemsListView<T, A> view;

        public AddListener(ItemsListView<T, A> view)
        {
            this.view = view;
        }

        @Override
        public void buttonClick(ClickEvent event)
        {
            this.view.dialog.setAction(CRUDDialog.Action.CREATE);
            this.view.dialog.setBean(this.view.createBeanInstance());

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

    private static class CRUDDialog<T, A> extends Window
    {
        private static final long serialVersionUID = 1L;

        public enum Action
        {
            CREATE, UPDATE, UNDEFINED;
        }

        private ItemsListView<T, A> view;

        private Form form;
        private BeanItem<T> beanItem;
        private T bean;
        private Button buttonSave;
        private Button buttonClose;

        private Action action = Action.UNDEFINED;

        public CRUDDialog(ItemsListView<T, A> view, FormFieldFactory formFieldFactory)
        {
            super();

            this.view = view;

            this.setClosable(true);
            this.setHeight("50%");
            this.setWidth("50%");

            VerticalLayout layout = (VerticalLayout) this.getContent();
            layout.setMargin(true);
            layout.setSpacing(true);

            bean = this.view.createBeanInstance();
            beanItem = new BeanItem<T>(bean);

            this.form = new Form();
            this.form.setSizeFull();
            this.form.setVisibleItemProperties(this.view.visibleFields);
            this.form.setFormFieldFactory(formFieldFactory);
            this.form.setItemDataSource(beanItem);

            this.buttonSave = new Button("Save", new DialogListener<T, A>(this));
            this.buttonClose = new Button("Close", new DialogListener<T, A>(this));

            HorizontalLayout formLayout = (HorizontalLayout) this.form.getFooter();
            formLayout.setMargin(true);
            formLayout.setSpacing(true);
            formLayout.addComponent(buttonSave);
            formLayout.addComponent(buttonClose);

            layout.addComponent(this.form);
        }

        public void setBean(T bean)
        {
            this.bean = bean;
            this.beanItem = new BeanItem<T>(bean);
            this.form.setItemDataSource(beanItem);
        }

        public void setAction(Action action)
        {
            this.action = action;
        }

        private static class DialogListener<T, A> implements Button.ClickListener
        {
            private static final long serialVersionUID = 1L;
            private CRUDDialog<T, A> dialog;

            public DialogListener(CRUDDialog<T, A> dialog)
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
                        if (this.dialog.action == Action.CREATE)
                        {
                            this.dialog.view.createBean(this.dialog.bean);

                            this.dialog.view.updateComponents();
                        }
                    }
                    catch (Exception e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                // close the window by removing it from the parent window
                this.dialog.getParent().removeWindow(dialog);
            }
        }
    }
}
