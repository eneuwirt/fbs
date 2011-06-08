package com.fbs.web.vaadin.ui.user.party;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fbs.dmr.universal.model.party.Party;
import com.fbs.dmr.universal.model.party.PartyRole;
import com.fbs.dmr.universal.model.party.PartyRoleType;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Select;

public abstract class PartyScreen<T extends Party> extends ItemsListScreen<T>
{
	private static final long serialVersionUID = 1L;


	public PartyScreen(MyVaadinApplication app, Class<T> clazz, String[] visibleColumns, String[] visibleFields)
	{
		super(app, clazz, visibleColumns, visibleFields);
	}


	public PartyScreen(MyVaadinApplication app, Class<T> clazz, String[] visibleColumns, String[] visibleFields,
	        String[] nestedContainerProperties)
	{
		super(app, clazz, visibleColumns, visibleFields, nestedContainerProperties);
	}


	private OptionGroup getOptionGroupRoles(Party party)
	{
		OptionGroup rolesGroup;
		List<String> partyRoles = new ArrayList<String>();
		List<PartyRoleType> partyRoleTypes;
		String caption;

		partyRoleTypes = this.app.getServices().getCrudServicePartyRoleType().findAll();
		for (PartyRoleType partyRoleType : partyRoleTypes)
		{
			partyRoles.add(partyRoleType.getDescription());
		}

		caption = this.app.getMessage(ApplicationMessages.PartyRoleTitle);

		rolesGroup = new OptionGroup(caption, partyRoles);
		rolesGroup.setMultiSelect(true);
		rolesGroup.setNullSelectionAllowed(true);
		rolesGroup.setImmediate(true);
		rolesGroup.addListener(new PartyRolesValueChangeListener());
		rolesGroup.addListener(new PartyRolesValueChangeListener());

		// set selection
		if (party != null)
		{
			for (PartyRole p : party.getPartyRoles())
			{
				rolesGroup.select(p.getPartyRoleType().getDescription());
			}
		}

		return rolesGroup;
	}


	@Override
	protected Component getComponent()
	{
		HorizontalLayout layout;
		OptionGroup rolesGroup;
		OptionGroup classificationsGroup;
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

		rolesGroup = this.getOptionGroupRoles(party);

		/*
		 * classificationsGroup = new OptionGroup("Rollllllleeen", null);
		 * classificationsGroup.setMultiSelect(true);
		 * classificationsGroup.setNullSelectionAllowed(false); // user can not
		 * // 'unselect' classificationsGroup.select("Berlin"); // select this
		 * by default classificationsGroup.setImmediate(true); // send the
		 * change to the // server at once
		 */
		layout.addComponent(rolesGroup);
		// layout.addComponent(classificationsGroup);

		return layout;
	}

	private static class PartyRolesValueChangeListener implements Property.ValueChangeListener
	{
		private static final long serialVersionUID = 1L;


		@Override
		public void valueChange(ValueChangeEvent event)
		{
			OptionGroup optionGroup;
			Collection<String >selList;

			optionGroup = (OptionGroup) event.getProperty();
			selList = (Collection<String>) optionGroup.getValue();
		}
	}

}
