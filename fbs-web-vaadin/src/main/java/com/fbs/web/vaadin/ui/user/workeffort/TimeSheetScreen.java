package com.fbs.web.vaadin.ui.user.workeffort;

import java.util.List;

import com.fbs.dmr.universal.model.workeffort.TimeSheet;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.fbs.web.vaadin.ui.common.items.DetailsAware;

public class TimeSheetScreen  extends ItemsListScreen<TimeSheet>
{
    private static final long serialVersionUID = 1L;

    public TimeSheetScreen(MyVaadinApplication app,DetailsAware<TimeSheet> component, String[] visibleColumns)
    {
        super(app, TimeSheet.class, component, visibleColumns);
    }

    @Override
    public List<TimeSheet> getAllBeans() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TimeSheet createBeanInstance()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TimeSheet createBean(TimeSheet t) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateBean(TimeSheet t) throws Exception
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public TimeSheet readBean(TimeSheet t) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteBean(TimeSheet t) throws Exception
    {
        // TODO Auto-generated method stub
        
    }

}
