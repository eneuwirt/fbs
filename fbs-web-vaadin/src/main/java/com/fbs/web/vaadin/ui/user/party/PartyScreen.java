package com.fbs.web.vaadin.ui.user.party;

import java.util.List;

import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyClassification;
import com.fbs.dmr.universal.model.party.PartyRole;
import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;

public abstract class PartyScreen<T extends Party> extends ItemsListScreen<T>
{
	private static final long serialVersionUID = 1L;
	protected OptionGroup rolesGroup;
	protected OptionGroup classificationsGroup;


	public PartyScreen(MyVaadinApplication app, Class<T> clazz, String[] visibleColumns, String[] visibleFields)
	{
		super(app, clazz, visibleColumns, visibleFields);
	}


	public PartyScreen(MyVaadinApplication app, Class<T> clazz, String[] visibleColumns, String[] visibleFields,
	        String[] nestedContainerProperties)
	{
		super(app, clazz, visibleColumns, visibleFields, nestedContainerProperties);
	}
	
	@Override
	protected void resetComponent()
	{
		this.rolesGroup.setValue(null);
		this.classificationsGroup.setValue(null);
	}


	private void setOptionGroupRoles(Party party)
	{
		List<PartyRoleType> partyRoleTypes;
		String caption;

		caption = this.app.getMessage(ApplicationMessages.PartyRoleTitle);
		this.rolesGroup.setCaption(caption);
		this.rolesGroup.setMultiSelect(true);
		this.rolesGroup.setNullSelectionAllowed(true);
		this.rolesGroup.setImmediate(true);
		
		partyRoleTypes = this.app.getServices().getCrudServicePartyRoleType().findAll();
		for (PartyRoleType partyRoleType : partyRoleTypes)
		{
			this.rolesGroup.addItem(partyRoleType.getDescription());
		}
		
		// set selection
		if (party != null)
		{
			for (PartyRole p : party.getPartyRoles())
			{
				this.rolesGroup.select(p.getPartyRoleType().getDescription());
			}
		}
	}
	
	private void setOptionGroupClassification(Party party)
    {
		List<PartyType> partyTypes;
		String caption;
		
		caption = this.app.getMessage(ApplicationMessages.PartyTypeTitle);
		this.classificationsGroup.setCaption(caption);
		this.classificationsGroup.setMultiSelect(true);
		this.classificationsGroup.setNullSelectionAllowed(true); 
		this.classificationsGroup.setImmediate(true); 
		
		partyTypes = this.app.getServices().getCrudServicePartyType().findAll();
		for (PartyType partyType : partyTypes)
		{
			this.classificationsGroup.addItem(partyType.getDescription());
		}
		
		// set selection
		if (party != null)
		{
			for (PartyClassification p : party.getPartyClassifications())
			{
				this.classificationsGroup.select(p.getPartyType().getDescription());
			}
		}
    }



	@SuppressWarnings("unchecked")
    @Override
	protected Component getComponent()
	{
		HorizontalLayout layout;
		BeanItem<T> beanItem;
		Party party = null;

		layout = new HorizontalLayout();
		layout.setSizeFull();
		layout.setMargin(true);
		layout.setSpacing(true);

		beanItem = (BeanItem<T>) this.form.getItemDataSource();

		if (beanItem != null)
		{
			party = beanItem.getBean();
		}

		this.rolesGroup = new OptionGroup();
		this.setOptionGroupRoles(party);
		
		this.classificationsGroup = new OptionGroup();
		this.setOptionGroupClassification(party);
		
		
		layout.addComponent(rolesGroup);
		layout.addComponent(classificationsGroup);

		return layout;
	}
}
