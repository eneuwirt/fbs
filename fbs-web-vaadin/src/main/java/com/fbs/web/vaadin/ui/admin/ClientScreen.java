package com.fbs.web.vaadin.ui.admin;

import java.util.List;

import com.fbs.security.model.Tenant;
import com.fbs.web.vaadin.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;

public class ClientScreen extends HorizontalSplitPanel
{
	private static final long serialVersionUID = 1L;
	private static final String COL_ID = "id";
	private static final String COL_DESCR = "description";
	private static String[] visibleColumns = new String[] { COL_ID, COL_DESCR };
	private MyVaadinApplication app;
	private Table table;
	private Form form;
	private BeanItemContainer<Tenant> beanItemContainer;
	private Button itemAddButton;
	private Button itemRemovalButton;
	private Button itemSaveButton;
	private Button cancelButton;
	private Button deleteButton;


	public ClientScreen(MyVaadinApplication app)
	{
		super();

		this.app = app;
		
		this.table = new Table();
		this.table.addListener(new Property.ValueChangeListener()
		{
			private static final long serialVersionUID = 1L;


			public void valueChange(ValueChangeEvent event)
			{
				Object id = table.getValue();

				form.setItemDataSource(id == null ? new BeanItem<Tenant>(new Tenant()) : table.getItem(id));

				itemRemovalButton.setEnabled(id != null);
				itemSaveButton.setEnabled(id != null);
				cancelButton.setEnabled(id != null);
				deleteButton.setEnabled(id != null);
			}
		});
		
		this.form = new Form();
		
		this.beanItemContainer = new BeanItemContainer<Tenant>(Tenant.class);
		this.getItems();

		this.itemAddButton = new Button("+");
		this.itemAddButton.addListener(new AddItemListener(this.table, this.form, this.beanItemContainer));
		
		this.itemRemovalButton = new Button("-");
		this.itemRemovalButton.addListener(new RemoveItemListener(this.table, this.beanItemContainer));
		
		this.itemSaveButton = new Button(this.app.getMessage(ApplicationMessages.CommonSave));
		this.itemSaveButton.addListener(new SaveItemListener());
		
		this.cancelButton = new Button(this.app.getMessage(ApplicationMessages.CommonCancel));
		this.cancelButton.addListener(new CancelButtonListener());
		
		this.deleteButton = new Button(this.app.getMessage(ApplicationMessages.CommonDelete));
		this.deleteButton.addListener(new RemoveItemListener(this.table, this.beanItemContainer));

		this.initLayout();
	}


	private void initLayout()
	{
		VerticalLayout left = new VerticalLayout();
		VerticalLayout right = new VerticalLayout();

		this.layoutLeft(left);
		this.layoutRight(right);

		this.addComponent(left);
		this.addComponent(right);
	}


	private void layoutLeft(VerticalLayout left)
	{
		HorizontalLayout bottomLeftCorner = new HorizontalLayout();

		left.setSizeFull();
		left.setSpacing(true);
		left.setMargin(true);

		this.layoutTable();
		this.layoutButtomLeftCorner(bottomLeftCorner);

		left.addComponent(this.table);
		left.addComponent(bottomLeftCorner);

		left.setExpandRatio(table, 1.0f);
	}


	private void layoutTable()
	{
		this.table.setSizeFull();
		
		//this.table.setNullSelectionAllowed(false);
		this.table.setSelectable(true);
		this.table.setMultiSelect(false);
		
		this.table.setImmediate(true);
		this.table.setContainerDataSource(beanItemContainer);
		this.table.setVisibleColumns(visibleColumns);

		// Set nicer header names
		this.table.setColumnHeader(COL_ID, this.app.getMessage(ApplicationMessages.TenantId));
		this.table.setColumnHeader(COL_DESCR, this.app.getMessage(ApplicationMessages.TenantDescription));

		this.table.setVisibleColumns(visibleColumns);
	}


	private void layoutButtomLeftCorner(HorizontalLayout bottomLeftCorner)
	{
		bottomLeftCorner.setWidth("100%");

		this.itemRemovalButton.setEnabled(false);
		
		bottomLeftCorner.addComponent(this.itemAddButton);
		bottomLeftCorner.addComponent(this.itemRemovalButton);
		//Add search fields
		for (final String visibleColumn : visibleColumns)
		{
			final TextField searchField = new TextField();

			searchField.setWidth("100%");
			searchField.setInputPrompt(visibleColumn);
			searchField.setImmediate(true);

			searchField.addListener(new Property.ValueChangeListener()
			{
				private static final long serialVersionUID = 1L;


				public void valueChange(ValueChangeEvent event)
				{
					beanItemContainer.removeContainerFilters(visibleColumn);

					if (searchField.toString().length() > 0 && !visibleColumn.equals(searchField.toString()))
					{
						beanItemContainer.addContainerFilter(visibleColumn, searchField.toString(), true, false);
					}
				}
			});

			bottomLeftCorner.addComponent(searchField);

			bottomLeftCorner.setExpandRatio(searchField, 1);
		}
	}


	private void getItems()
	{
		List<Tenant> tenants;

		tenants = this.app.getTenantService().findAll();

		for (Tenant tenant : tenants)
		{
			this.beanItemContainer.addBean(tenant);
		}
	}


	private void layoutRight(VerticalLayout right)
	{
		this.form.setSizeFull();
		this.form.setImmediate(true);
		this.form.setCaption(this.app.getMessage(ApplicationMessages.CommonDetails));
		this.form.setDescription(this.app.getMessage(ApplicationMessages.CommonDetailsDescription));

		BeanItem<Tenant> dummy = new BeanItem<Tenant>(new Tenant());
		this.form.setItemDataSource(dummy);

		this.itemSaveButton.setEnabled(false);
		this.cancelButton.setEnabled(false);
		this.deleteButton.setEnabled(false);

		HorizontalLayout buttonRow = new HorizontalLayout();
		buttonRow.addComponent(this.itemSaveButton);
		buttonRow.addComponent(this.cancelButton);
		buttonRow.addComponent(this.deleteButton);

		// right.setSizeFull();
		right.addComponent(this.form);
		right.addComponent(buttonRow);

		right.setExpandRatio(this.form, 1.0f);
	}

	private static class AddItemListener implements Button.ClickListener
	{
		private static final long serialVersionUID = 1L;
		private Table table;
		private BeanItemContainer<Tenant> beanItemContainer = null;
		private Form form;


		public AddItemListener(Table table, Form form, BeanItemContainer<Tenant> beanItemContainer)
		{
			this.beanItemContainer = beanItemContainer;
			this.form = form;
			this.table = table;
		}


		@Override
		public void buttonClick(ClickEvent event)
		{
			Tenant t = new Tenant();
			t.setDescription("Bitte pflegen");

			this.beanItemContainer.addBean(t);
			this.table.select(null);
			this.table.select(t);

			BeanItem<Tenant> beanItem = new BeanItem<Tenant>(t);
			this.form.setItemDataSource(beanItem);
		}
	}

	private static class RemoveItemListener implements Button.ClickListener
	{
		private static final long serialVersionUID = 1L;
		private BeanItemContainer<Tenant> beanItemContainer = null;
		private Table table;


		public RemoveItemListener(Table table, BeanItemContainer<Tenant> beanItemContainer)
		{
			this.beanItemContainer = beanItemContainer;

			this.table = table;
		}


		@Override
		public void buttonClick(ClickEvent event)
		{
			this.beanItemContainer.removeItem(table.getValue());

			table.select(null);
		}
	}

	private static class SaveItemListener implements Button.ClickListener
	{
		private static final long serialVersionUID = 1L;


		@Override
		public void buttonClick(ClickEvent event)
		{
			// TODO Auto-generated method stub

		}
	}
	
	private static class CancelButtonListener implements Button.ClickListener
	{
		private static final long serialVersionUID = 1L;


		@Override
		public void buttonClick(ClickEvent event)
		{
			// TODO Auto-generated method stub

		}
	}
}
