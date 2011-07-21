package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.user.workeffort.TimeSheetScreen;
import com.fbs.web.vaadin.ui.user.workeffort.WorkEffortScreen;
import com.vaadin.ui.Accordion;

public class WorkEffortAccordion extends Accordion
{
    private static final long serialVersionUID = 1L;
    private MyVaadinApplication app;
    private WorkEffortScreen workEffortScreen;
    private TimeSheetScreen timeSheetScreen;

    public WorkEffortAccordion(MyVaadinApplication app)
    {
        super();
        this.app = app;
        
        this.workEffortScreen = new WorkEffortScreen(app);
        
        this.timeSheetScreen = new TimeSheetScreen(app);
        
        this.addTab(this.workEffortScreen, this.app.getMessage(ApplicationMessages.WorkEffortTitle), null);
        this.addTab(this.timeSheetScreen, this.app.getMessage(ApplicationMessages.TimeSheetTitle), null);
        
        this.initLayout();
    }
    
    
    private void initLayout()
	{
		this.setSizeFull();
	}
}
