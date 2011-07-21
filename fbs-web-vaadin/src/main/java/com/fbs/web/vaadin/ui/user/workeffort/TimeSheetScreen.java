package com.fbs.web.vaadin.ui.user.workeffort;

import java.util.List;

import com.fbs.dmr.universal.model.workeffort.TimeSheet;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;

public class TimeSheetScreen extends ItemsListScreen<TimeSheet>
{
    private static final long serialVersionUID = 1L;
    private static final String ID = "id";

    private static String[] VISIBLE_COLUMNS = new String[]
    { ID };

    public TimeSheetScreen(MyVaadinApplication app)
    {
        super(app, TimeSheet.class, new TimeSheetDetails(app), VISIBLE_COLUMNS);
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
        TimeSheet result;
        
        result = new TimeSheet();
        
        return result;
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
