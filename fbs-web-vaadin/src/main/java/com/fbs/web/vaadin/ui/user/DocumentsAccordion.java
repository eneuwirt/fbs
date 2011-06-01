package com.fbs.web.vaadin.ui.user;

import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.ui.document.DocumentTree;
import com.vaadin.ui.Accordion;

public class DocumentsAccordion  extends Accordion
{
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;
	private DocumentTree documentTree;

	public DocumentsAccordion(MyVaadinApplication app)
	{
		this.app = app;
		this.documentTree = new DocumentTree();
		
		this.addTab(documentTree, "Dokumente", null);
		
		this.setSizeFull();
	}
}
