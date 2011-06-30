package com.fbs.web.vaadin.ui.common;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public abstract class ItemsListView<T> extends Panel implements ListView<T>
{
    private static final long serialVersionUID = 1L;

    protected MyVaadinApplication app;
    private Class<T> clazz;

    protected Table tableBeans;
    protected BeanItemContainer<T> beanItemContainer;

    protected Button buttonAdd;
    protected Button buttonDelete;
    //
    protected Window windowAdd;
    //
    protected String[] visibleColumns;
    protected String[] visibleFields;

    public ItemsListView(MyVaadinApplication app, Class<T> clazz, String[] visibleColumns, String[] visibleFields)
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
        this.tableBeans.addListener(new TableSelectListener<T>(this));

        this.buttonAdd = new Button("Add", new AddListener<T>(this));
        
        this.buttonDelete = new Button("-", new DeleteListener<T>(this));
        this.buttonDelete.setEnabled(false);

        this.windowAdd = new AddDialog<T>(this);
        this.windowAdd.setModal(true);
        
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
        buttonRow.addComponent(this.buttonDelete);

        this.addComponent(this.tableBeans);
        this.addComponent(buttonRow);
    }
    
    private static class TableSelectListener<T> implements Property.ValueChangeListener
    {
        private static final long serialVersionUID = 1L;
        private ItemsListView<T> view;

        public TableSelectListener(ItemsListView<T> view)
        {
            this.view = view;
        }

        @Override
        public void valueChange(ValueChangeEvent event)
        {
            this.view.buttonDelete.setEnabled(true);
        }
    }
    
    private static class DeleteListener<T> implements Button.ClickListener
    {
        private static final long serialVersionUID = 1L;
        private ItemsListView<T> view;

        public DeleteListener(ItemsListView<T> view)
        {
            this.view = view;
        }

        @Override
        public void buttonClick(ClickEvent event)
        {
          
        }
    }

    private static class AddListener<T> implements Button.ClickListener
    {
        private static final long serialVersionUID = 1L;
        private ItemsListView<T> view;

        public AddListener(ItemsListView<T> view)
        {
            this.view = view;
        }

        @Override
        public void buttonClick(ClickEvent event)
        {
            if (view.windowAdd.getParent() != null)
            {
                // window is already showing
                view.getWindow().showNotification("Window is already open");
            }
            else
            {
                // Open the subwindow by adding it to the parent window
                view.getWindow().addWindow(view.windowAdd);
            }
        }
    }

    private static class AddDialog<T> extends Window
    {
        private static final long serialVersionUID = 1L;

        private ItemsListView<T> view;

        private Form form;
        private BeanItem<T> beanItem;
        private T bean;
        private Button buttonSave;
        private Button buttonClose;

        public AddDialog(ItemsListView<T> view)
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
            this.form.setFormFieldFactory(this.view.getFormFieldFactory());
            this.form.setItemDataSource(beanItem);

            this.buttonSave = new Button("Save", new DialogListener<T>(this));
            this.buttonClose = new Button("Close", new DialogListener<T>(this));

            HorizontalLayout formLayout = (HorizontalLayout) this.form.getFooter();
            formLayout.setMargin(true);
            formLayout.setSpacing(true);
            formLayout.addComponent(buttonSave);
            formLayout.addComponent(buttonClose);

            layout.addComponent(this.form);
        }

        private void reset()
        {
            this.bean = this.view.createBeanInstance();
            this.beanItem = new BeanItem<T>(bean);
            this.form.setItemDataSource(beanItem);

        }

        private static class DialogListener<T> implements Button.ClickListener
        {
            private static final long serialVersionUID = 1L;
            private AddDialog<T> dialog;

            public DialogListener(AddDialog<T> dialog)
            {
                this.dialog = dialog;
            }

            @Override
            public void buttonClick(ClickEvent event)
            {
                if (event.getButton().equals(dialog.buttonSave))
                {

                }

                this.dialog.reset();

                // close the window by removing it from the parent window
                this.dialog.getParent().removeWindow(dialog);
            }

        }
    }
}
