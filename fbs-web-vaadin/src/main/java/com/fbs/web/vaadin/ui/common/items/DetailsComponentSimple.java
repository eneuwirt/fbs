package com.fbs.web.vaadin.ui.common.items;

import java.io.Serializable;
import java.util.Arrays;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Shows CRUD screen for given bean. No implemementation for crud details
 * 
 * @author neuwirt
 * 
 * @param <T>
 */
public class DetailsComponentSimple<T extends Serializable> extends CustomComponent implements DetailsComponent<T>
{
    private static final long serialVersionUID = 1L;
    protected MyVaadinApplication app;

    protected Panel mainPanel;
    protected String[] visibleFields;
    protected T bean;
    protected Form form;

    public DetailsComponentSimple(MyVaadinApplication app, FormFieldFactory formFieldFactory, String[] visibleFields)
    {
        // A layout structure used for composition
        this.app = app;
        this.visibleFields = visibleFields;

        this.mainPanel = new Panel();
        this.mainPanel.setContent(new VerticalLayout());

        this.form = new Form();
        this.form.setImmediate(true);
        this.form.setFormFieldFactory(formFieldFactory);

        this.mainPanel.addComponent(this.form);
        
        this.form.setSizeFull();
        this.mainPanel.getContent().setSizeFull();
        this.mainPanel.setSizeFull();
        this.setSizeFull();

        // The composition root MUST be set
        setCompositionRoot(mainPanel);
    }

    public void setBean(T bean)
    {
        BeanItem<T> beanItem;

        this.bean = bean;
        beanItem = new BeanItem<T>(bean);

        this.form.setItemDataSource(beanItem, Arrays.asList(this.visibleFields));
    }

    public T getBean()
    {
        return this.bean;
    }

    @Override
    public void createBeanDetails(T t)
    {
       
    }

    @Override
    public void readBeanDetails(T t)
    {
        
    }

    @Override
    public void updateBeanDetails(T t)
    {
        
    }

    @Override
    public void deleteBeanDetails(T t)
    {
       
    }
}
