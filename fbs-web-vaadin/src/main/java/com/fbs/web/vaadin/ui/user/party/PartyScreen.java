package com.fbs.web.vaadin.ui.user.party;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TabSheet;

public abstract class PartyScreen<T extends Party> extends ItemsListScreen<T>
{
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PartyScreen.class.getName());
	//
	protected OptionGroup rolesGroup;
	protected OptionGroup classificationsGroup;
	//
	private PostalAddressView postalAddressView;

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

		logger.info(">createBean");

		selectedRoles = (Set<String>) this.rolesGroup.getValue();

		for (String selectedRole : selectedRoles)
		{
			PartyRoleType partyRoleType;
			PartyRole partyRole;

			partyRoleType = this.app.getServices().getCrudServicePartyRoleType().findForDescription(selectedRole);

			partyRole = new PartyRole();
			partyRole.setParty(party);
			partyRole.setPartyRoleType(partyRoleType);

			this.app.getServices().getCrudServicePartyRole().create(partyRole);
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

			this.app.getServices().getCrudServicePartyClassification().create(partyClassification);
		}

		logger.info("<createBean");

		return party;
	}

	@Override
	protected void updateBean(Party party) throws Exception
	{
		List<PartyRole> partyRoles;
		List<PartyClassification> classifications;

		logger.info(">updateBean");

		@SuppressWarnings("unchecked")
		Set<String> selectedRoles = (Set<String>) this.rolesGroup.getValue();

		// remove all unselected roles
		partyRoles = this.app.getServices().getCrudServicePartyRole().findByParty(party.getId());
		for (PartyRole partyRole : partyRoles)
		{
			if (!selectedRoles.contains(partyRole.getPartyRoleType().getDescription()))
			{
				this.app.getServices().getCrudServicePartyRole().delete(partyRole.getId());
			}
		}

		// add all selected items to the party
		partyRoles = new ArrayList<PartyRole>();
		for (String selectedRole : selectedRoles)
		{
			PartyRole partyRole;

			partyRole = this.app.getServices().getCrudServicePartyRole()
			        .findByPartyAndPartyRoleType(party.getId(), selectedRole);
			if (partyRole == null)
			{
				PartyRoleType partyRoleType;

				partyRoleType = this.app.getServices().getCrudServicePartyRoleType().findForDescription(selectedRole);

				partyRole = new PartyRole();

				partyRole.setParty(party);
				partyRole.setPartyRoleType(partyRoleType);

				this.app.getServices().getCrudServicePartyRole().create(partyRole);
			}
			else
			{
				this.app.getServices().getCrudServicePartyRole().update(partyRole);
			}
		}

		@SuppressWarnings("unchecked")
		Set<String> selectedClassifications = (Set<String>) this.classificationsGroup.getValue();
		// remove all unselected classifications
		classifications = this.app.getServices().getCrudServicePartyClassification().findByParty(party.getId());
		for (PartyClassification classification : classifications)
		{
			if (!selectedClassifications.contains(classification.getPartyType().getDescription()))
			{
				this.app.getServices().getCrudServicePartyClassification().delete(classification.getId());
			}
		}
		// add selected items
		classifications = new ArrayList<PartyClassification>();
		for (String selectedClass : selectedClassifications)
		{
			PartyClassification partyClass;

			partyClass = this.app.getServices().getCrudServicePartyClassification()
			        .findByPartyType(party.getId(), selectedClass);

			if (partyClass == null)
			{
				PartyType partyType;

				partyType = this.app.getServices().getCrudServicePartyType().findForDescription(selectedClass);

				partyClass = new PartyClassification();
				partyClass.setParty(party);
				partyClass.setPartyType(partyType);

				this.app.getServices().getCrudServicePartyClassification().create(partyClass);
			}
			else
			{
				this.app.getServices().getCrudServicePartyClassification().update(partyClass);
			}

			classifications.add(partyClass);
		}

		logger.info("<updateBean");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void updateComponent(T party)
	{
		BeanItem<T> beanItem;
		T bean;

		logger.info(">resetComponent");

		super.updateComponent(party);

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

				this.postalAddressView.updateComponents(party);
			}
			catch (Exception e)
			{
				logger.log(Level.SEVERE, "Exception: " + e.getMessage());
			}
		}

		logger.info("<resetComponent");
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
			List<PartyRole> partyRoles;
			partyRoles = this.app.getServices().getCrudServicePartyRole().findByParty(party.getId());

			for (PartyRole p : partyRoles)
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
			List<PartyClassification> classifications;

			classifications = this.app.getServices().getCrudServicePartyClassification().findByParty(party.getId());
			for (PartyClassification p : classifications)
			{
				this.classificationsGroup.select(p.getPartyType().getDescription());
			}
		}
	}

	@Override
	protected AbstractComponentContainer getComponent()
	{
		TabSheet tabsheet = new TabSheet();
		String captionMasterData;
		String captionClass;
		String captionRoles;
		String captionAddr;
		String captionRelations;

		logger.info(">getComponent");

		captionMasterData = this.app.getMessage(ApplicationMessages.PartyMasterData);
		captionClass = this.app.getMessage(ApplicationMessages.PartyTypeClassificationTitle);
		captionRoles = this.app.getMessage(ApplicationMessages.PartyRoleTitle);
		captionAddr = this.app.getMessage(ApplicationMessages.PartyPostalAddress);
		captionRelations = this.app.getMessage(ApplicationMessages.PartyRelationships);

		this.createClassificationGroup();
		this.createRolesGroup();
		this.createContacts();

		tabsheet.addTab(this.form, captionMasterData, null);
		tabsheet.addTab(this.postalAddressView, captionAddr, null);
		tabsheet.addTab(new Label("Bezihungen zwischen mir und den andren"), captionRelations, null);
		tabsheet.addTab(this.rolesGroup, captionRoles, null);
		tabsheet.addTab(this.classificationsGroup, captionClass, null);

		logger.info("<getComponent");

		return tabsheet;
	}

	private void createContacts()
	{
		this.postalAddressView = new PostalAddressView(this.app);
	}

	@Override
	protected void layoutComponent()
	{
		this.classificationsGroup.setSizeFull();
		this.rolesGroup.setSizeFull();

		this.component.setSizeFull();
	}

	private void createClassificationGroup()
	{
		this.classificationsGroup = new OptionGroup();
		this.classificationsGroup.setMultiSelect(true);
		this.classificationsGroup.setNullSelectionAllowed(true);
		this.classificationsGroup.setImmediate(true);
	}

	private void createRolesGroup()
	{
		this.rolesGroup = new OptionGroup();
		this.rolesGroup.setMultiSelect(true);
		this.rolesGroup.setNullSelectionAllowed(true);
		this.rolesGroup.setImmediate(true);
	}
}
