package com.fbs.web.vaadin.ui.user.party;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.fbs.dmr.universal.model.party.Organization;
import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyClassification;
import com.fbs.dmr.universal.model.party.PartyRole;
import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.dmr.universal.model.party.PartyType;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
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
	protected void updateBean(Party party) throws Exception
	{
		List<PartyRole> partyRoles;
		List<PartyRole> newPartyRoles = new LinkedList<PartyRole>();

		partyRoles = this.app.getServices().getCrudServicePartyRole().findAll();

		party.getPartyClassifications().clear();
		@SuppressWarnings("unchecked")
		List<String> selectedRoles = new LinkedList<String>((Set<String>) this.rolesGroup.getValue());
		// Select all remained roles
		for (PartyRole p : party.getPartyRoles())
		{
			
		}

	}


	@SuppressWarnings("unchecked")
	@Override
	protected void resetComponent()
	{
		BeanItem<T> beanItem;
		Party party = null;

		beanItem = (BeanItem<T>) this.form.getItemDataSource();

		if (beanItem != null)
		{
			party = beanItem.getBean();
		}

		this.resetOptionGroupRoles(party);

		this.resetOptionGroupClassification(party);
	}


	private void resetOptionGroupRoles(Party party)
	{
		List<PartyRoleType> partyRoleTypes;

		partyRoleTypes = this.app.getServices().getCrudServicePartyRoleType().findAll();
		for (PartyRoleType partyRoleType : partyRoleTypes)
		{
			this.rolesGroup.addItem(partyRoleType.getDescription());
		}

		// set selection
		this.rolesGroup.setValue(null);
		if (party != null)
		{
			for (PartyRole p : party.getPartyRoles())
			{
				this.rolesGroup.select(p.getPartyRoleType().getDescription());
			}
		}
	}


	private void resetOptionGroupClassification(Party party)
	{
		List<PartyType> partyTypes;
		BeanItemContainer<String> container;

		partyTypes = this.app.getServices().getCrudServicePartyType().findAll();
		container = new BeanItemContainer<String>(String.class);
		for (PartyType t : partyTypes)
		{
			container.addBean(t.getDescription());
		}
		this.classificationsGroup.setContainerDataSource(container);

		// set selection
		this.classificationsGroup.setValue(null);
		if (party != null)
		{
			for (PartyClassification p : party.getPartyClassifications())
			{
				this.classificationsGroup.select(p.getPartyType().getDescription());
			}
		}
	}


	@Override
	protected Component getComponent()
	{
		HorizontalLayout layout;
		String caption;

		layout = new HorizontalLayout();
		layout.setSizeFull();
		layout.setMargin(true);
		layout.setSpacing(true);

		this.classificationsGroup = new OptionGroup();
		caption = this.app.getMessage(ApplicationMessages.PartyTypeClassificationTitle);
		this.classificationsGroup.setCaption(caption);
		this.classificationsGroup.setMultiSelect(true);
		this.classificationsGroup.setNullSelectionAllowed(true);
		this.classificationsGroup.setImmediate(true);

		this.rolesGroup = new OptionGroup();
		caption = this.app.getMessage(ApplicationMessages.PartyRoleTitle);
		this.rolesGroup.setCaption(caption);
		this.rolesGroup.setMultiSelect(true);
		this.rolesGroup.setNullSelectionAllowed(true);
		this.rolesGroup.setImmediate(true);

		layout.addComponent(rolesGroup);
		layout.addComponent(classificationsGroup);

		return layout;
	}
}
