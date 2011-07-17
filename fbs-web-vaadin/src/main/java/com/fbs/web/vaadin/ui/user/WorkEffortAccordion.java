package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.ui.user.workeffort.WorkEffortScreen;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Label;

public class WorkEffortAccordion extends Accordion
{
    private static final long serialVersionUID = 1L;
    private MyVaadinApplication app;
    private WorkEffortScreen workEffortScreen;

    public WorkEffortAccordion(MyVaadinApplication app)
    {
        super();
        this.app = app;
        
        this.workEffortScreen = new WorkEffortScreen(app);
        
        this.addTab(this.workEffortScreen, "In Arbeit", null);
        
        this.initLayout();
    }
    
    
    private void initLayout()
	{
		this.setSizeFull();
	}
}
