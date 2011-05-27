package com.fbs.web.vaadin.ui.user.party;

import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;

public class OrganizationForm extends Form
{
	private static final long serialVersionUID = 1L;
	private GridLayout layout;


	public OrganizationForm()
	{
		// Use top-left margin and spacing
		this.layout.setMargin(true, false, false, true);
		this.layout.setSpacing(true);

		setLayout(this.layout);
	}


	
}
