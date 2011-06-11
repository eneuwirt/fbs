package com.fbs.web.vaadin.ui.user.party;

import java.util.List;
import java.util.Set;

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

	@SuppressWarnings("unchecked")
    @Override
	protected T createBean(T party) throws Exception
	{
		Set<String> selectedRoles;
        Set<String> selectedClassiffcations;

		selectedRoles = (Set<String>) this.rolesGroup.getValue();
		
		for (String selectedRole : selectedRoles)
		{
			PartyRoleType partyRoleType;
			PartyRole partyRole;

			partyRoleType = this.app.getServices().getCrudServicePartyRoleType().findForDescription(selectedRole);

			partyRole = new PartyRole();
			partyRole.setParty(party);
			partyRole.setPartyRoleType(partyRoleType);

			party.getPartyRoles().add(partyRole);
		}

        selectedClassiffcations = (Set<String>) this.classificationsGroup.getValue();
		
        for (String selectedClassiffcation : selectedClassiffcations)
		{
			PartyType partyType;
			PartyClassification partyClassification;

			partyType = this.app.getServices().getCrudServicePartyType().findForDescription(selectedClassiffcation);
			
			partyClassification = new PartyClassification();
			partyClassification.setParty(party);
			partyClassification.setPartyType(partyType);
			
			party.getPartyClassifications().add(partyClassification);
		}

		return party;
	}

	@Override
	protected void updateBean(Party party) throws Exception
	{
		@SuppressWarnings("unchecked")
		Set<String> selectedRoles = (Set<String>) this.rolesGroup.getValue();

	}

	@SuppressWarnings("unchecked")
	@Override
	protected void resetComponent()
	{
		BeanItem<T> beanItem;
		Party party = null;
		T bean;

		beanItem = (BeanItem<T>) this.form.getItemDataSource();

		if (beanItem != null)
		{
			try
			{
				bean = beanItem.getBean();
				if (bean.getId() != null)
				{
					party = this.readBean(bean);
				}

				this.resetOptionGroupRoles(party);

				this.resetOptionGroupClassification(party);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
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
