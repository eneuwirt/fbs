package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Label;

public class WorkEffortAccordion extends Accordion
{
    private static final long serialVersionUID = 1L;
    private MyVaadinApplication app;
    

    public WorkEffortAccordion(MyVaadinApplication app)
    {
        super();
        this.app = app;
        
        this.addTab(new Label("In Arbeit"), "In Arbeit", null);
    }
    
}
