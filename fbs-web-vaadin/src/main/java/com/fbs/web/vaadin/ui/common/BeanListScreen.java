package com.fbs.web.vaadin.ui.common;

import com.fbs.web.vaadin.application.ApplicationServices;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.items.DetailsAware;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

/**
 * Used as template for horizontal split screens. 
 * left is a list view with 
 *  - filter 
 *      - filtered fields 
 *      - maximal number of displayed entries 
 *      - total entries in list
 *  - entries 
 *      - entry list 
 *      - filtering on the table content 
 *      - list 
 *      - create and delete buttons 
 * right is a details view for the selected list element. Depends
 * on the entity
 * 
 * Assumptions: only a subset of the entire list is shown. Filter defines the
 * maximal number of fetched entries and additional filtering criteria.
 * 
 * @author neuwirt
 * 
 */
public abstract class BeanListScreen<T> extends HorizontalSplitPanel implements CrudAware<T>
{
    private static final long serialVersionUID = 1L;
    // Visible columns for table
    protected String[] visibleColumns;
    protected String[] nestedContainerProperties = new String[]
    {};
    //
    protected Class<T> clazz;
    protected MyVaadinApplication app;
    protected ApplicationServices services;
    // state
    protected int displayMax = 10;
    // ** Elements left
    protected AbstractComponentContainer formSearch;
    protected HorizontalLayout tableFilterRow;
    protected Table table;
    protected BeanItemContainer<T> beanItemContainer;

    // ** Elements right ******************************************
    protected DetailsAware<T> detailsComponent;

    public BeanListScreen(Class<T> clazz, MyVaadinApplication app, DetailsAware<T> component, String[] visibleColumns,
            String[] nestedContainerProperties)
    {
        super();
        this.clazz = clazz;
        this.app = app;
        this.services = this.app.getServices();
        this.visibleColumns = visibleColumns;
        this.nestedContainerProperties = nestedContainerProperties;
        
        this.beanItemContainer = new BeanItemContainer<T>(this.clazz);
        this.addNestedContainerProperties();

        this.tableFilterRow = new HorizontalLayout();
        this.initTableFilterRow();
        
        this.table = new Table();
        this.table.setSelectable(true);
        this.table.setMultiSelect(false);
        this.table.setImmediate(true);
        this.table.setContainerDataSource(beanItemContainer);
        //this.table.addListener(new TableSelectChangeListener<T>(this));
        
        this.detailsComponent = component;
        this.detailsComponent.setEnabled(false);
        this.detailsComponent.setBean(this.createBeanInstance());

        this.initLayout();
    }

    private void initLayout()
    {
        this.setSizeFull();
        
    }
    
    protected void initTableFilterRow()
    {       
        this.tableFilterRow.setWidth("100%");
        this.tableFilterRow.setCaption(this.app.getMessage(ApplicationMessages.CommonSearch));

        // Add search fields
        for (final String visibleColumn : this.visibleColumns)
        {
            final TextField searchField = new TextField();

            searchField.setWidth("100%");
            searchField.setInputPrompt(visibleColumn);
            searchField.setImmediate(true);

            searchField.addListener(new Property.ValueChangeListener()
            {
                private static final long serialVersionUID = 1L;

                public void valueChange(ValueChangeEvent event)
                {
                    beanItemContainer.removeContainerFilters(visibleColumn);

                    if (searchField.toString().length() > 0 && !visibleColumn.equals(searchField.toString()))
                    {
                        beanItemContainer.addContainerFilter(visibleColumn, searchField.toString(), true, false);
                    }
                }
            });

            this.tableFilterRow.addComponent(searchField);
            this.tableFilterRow.setExpandRatio(searchField, 2);
        }
    }

    /**
     * Returns a nice name of column.
     * 
     * @param propertyId
     * @return niceName of it
     */
    public String getColumnName(String propertyId)
    {
        return propertyId;
    }

    private void addNestedContainerProperties()
    {
        if (this.nestedContainerProperties == null)
        {
            return;
        }

        for (int i = 0; i < this.nestedContainerProperties.length; i++)
        {
            this.beanItemContainer.addNestedContainerProperty(nestedContainerProperties[i]);
        }
    }
}
