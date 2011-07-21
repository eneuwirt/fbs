package com.fbs.web.vaadin.ui.user.workeffort;

import com.fbs.dmr.universal.model.workeffort.TimeSheet;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.ui.common.items.BeanAware;
import com.fbs.web.vaadin.ui.common.items.DetailsAware;
import com.vaadin.ui.TabSheet;

public class TimeSheetDetails extends TabSheet implements DetailsAware<TimeSheet>, BeanAware<TimeSheet>
{
    private static final long serialVersionUID = 1L;
    private MyVaadinApplication app;
    
    public TimeSheetDetails(MyVaadinApplication app)
    {
        super();
        
        this.app = app;
    }

    @Override
    public void setBean(TimeSheet t)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public TimeSheet getBean()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createBeanDetails(TimeSheet t)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void readBeanDetails(TimeSheet t)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBeanDetails(TimeSheet t)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteBeanDetails(TimeSheet t)
    {
        // TODO Auto-generated method stub
        
    }
}
