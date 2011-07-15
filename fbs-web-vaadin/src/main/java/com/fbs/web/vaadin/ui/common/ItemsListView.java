package com.fbs.web.vaadin.ui.common;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.items.BeanAware;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public abstract class ItemsListView<T, A> extends VerticalLayout implements AnchorAware<T, A>, ListAware<T>, CrudAware<T>
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
    protected String createDialogCaption = "";
    protected String updateDialogCaption = "";
    protected CRUDDialog<T, A> dialog;
    //
    protected String[] visibleColumns;
    protected String[] nestedContainerProperties;

    public ItemsListView(MyVaadinApplication app, Class<T> clazz, FormFieldFactory formFieldFactory, String[] visibleColumns,
            String[] visibleFields, String[] nestedContainerProperties)
    {
        this.app = app;
        this.clazz = clazz;
        this.visibleColumns = visibleColumns;
        this.nestedContainerProperties = nestedContainerProperties;

        this.createDialogCaption = this.app.getMessage(ApplicationMessages.CommonCreate);
        
        this.dialog = new CRUDDialog<T, A>(this, formFieldFactory, visibleFields);
        this.dialog.setModal(true);

        this.beanItemContainer = new BeanItemContainer<T>(this.clazz);
        this.addNestedContainerProperties();

        this.tableBeans = new Table();
        this.tableBeans.setSelectable(true);
        this.tableBeans.setMultiSelect(false);
        this.tableBeans.setImmediate(true);
        this.tableBeans.setContainerDataSource(beanItemContainer);
        this.tableBeans.addListener(new TableSelectListener<T, A>(this));

        this.buttonAdd = new Button("Add", new CreateListener<T, A>(this));

        this.buttonEdit = new Button("Ändern", new EditListener<T, A>(this));
        this.buttonEdit.setEnabled(false);

        this.buttonDelete = new Button("-", new DeleteListener<T, A>(this));
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
    public void setAnchor(A anchor)
    {
        this.anchor = anchor;

        if (anchor != null)
        {
            this.updateComponents();
        }
    }

    private void addNestedContainerProperties()
    {
        if (this.nestedContainerProperties == null)
        {
            return;
        }

        for (String p :  this.nestedContainerProperties)
        {
            this.beanItemContainer.addNestedContainerProperty(p);
        }
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
            this.view.selectedBean = (T) this.view.tableBeans.getValue();

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

                // we have to repopulate table
                this.view.updateComponents();

                this.view.tableBeans.setValue(null);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Exception: " + e.getMessage());
            }
        }
    }

    private static class EditListener<T, A> implements Button.ClickListener
    {
        private static final long serialVersionUID = 1L;
        private ItemsListView<T, A> view;

        public EditListener(ItemsListView<T, A> view)
        {
            this.view = view;
        }

        @Override
        public void buttonClick(ClickEvent event)
        {
            T bean;

            bean = this.view.selectedBean;

            this.view.dialog.setCaption(this.view.updateDialogCaption);
            this.view.dialog.setAction(CRUDDialog.Action.UPDATE);
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

    private static class CreateListener<T, A> implements Button.ClickListener
    {
        private static final long serialVersionUID = 1L;
        private ItemsListView<T, A> view;

        public CreateListener(ItemsListView<T, A> view)
        {
            this.view = view;
        }

        @Override
        public void buttonClick(ClickEvent event)
        {
            T bean;

            bean = this.view.createBeanInstance();

            this.view.dialog.setCaption(this.view.createDialogCaption);

            this.view.dialog.setAction(CRUDDialog.Action.CREATE);
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

    private static class CRUDDialog<T, A> extends Window implements BeanAware<T>
    {
        private static final long serialVersionUID = 1L;

        public enum Action
        {
            CREATE, UPDATE, UNDEFINED;
        }

        private ItemsListView<T, A> view;
        
        private String[] visibleFields;

        private Form form;
        private BeanItem<T> beanItem;
        private T bean;
        private Button buttonSave;
        private Button buttonClose;

        private Action action = Action.UNDEFINED;

        public CRUDDialog(ItemsListView<T, A> view, FormFieldFactory formFieldFactory, String[] visibleFields)
        {
            super();

            this.view = view;
            
            this.visibleFields = visibleFields;

            this.setClosable(true);
            this.setHeight("50%");
            this.setWidth("50%");

            VerticalLayout layout = (VerticalLayout) this.getContent();
            layout.setMargin(true);
            layout.setSpacing(true);

            this.bean = this.view.createBeanInstance();
            this.beanItem = new BeanItem<T>(bean);

            this.form = new Form();
            this.form.setImmediate(true);
            this.form.setSizeFull();
            this.form.setFormFieldFactory(formFieldFactory);
            this.form.setItemDataSource(beanItem, Arrays.asList(visibleFields));

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
            this.form.setItemDataSource(beanItem, Arrays.asList(visibleFields));
        }
        
        @Override
        public T getBean()
        {
            return this.bean;
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
                        }
                        else if (this.dialog.action == Action.UPDATE)
                        {
                            this.dialog.view.updateBean(this.dialog.bean);
                        }

                        this.dialog.view.updateComponents();
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
