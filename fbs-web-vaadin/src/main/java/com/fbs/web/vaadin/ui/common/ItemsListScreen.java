package com.fbs.web.vaadin.ui.common;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.fbs.web.vaadin.application.ApplicationServices;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

/**
 * Class represents a template to build a List of entries and a possibility to
 * edit.
 * 
 * You have to implement the protected methods.
 * 
 * @author neuwirt
 * 
 * @param <T>
 */
public abstract class ItemsListScreen<T> extends HorizontalSplitPanel implements ListScreen<T>
{
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ItemsListScreen.class.getName());

	public enum Action
	{
		CREATE, CREATE_1, CREATE_2, SAVE, SAVE_FAILURE, DELETE, CANCEL, SELECT, SELECT_NULL, UNDEFINED;
	}

	protected Class<T> clazz;
	protected MyVaadinApplication app;
	protected ApplicationServices services;
	// Visible columns for table and visible fields for form
	protected String[] visibleColumns;
	protected String[] visibleFields;
	protected String[] nestedContainerProperties;
	// ** Elements left *******************************************
	protected Table table;
	protected BeanItemContainer<T> beanItemContainer;
	protected Button buttonItemAdd;
	protected Button buttonItemAdd1;
	protected Button buttonItemAdd2;
	protected Button buttonItemDelete;
	// ** Elements right ******************************************
	protected Form form;
	protected AbstractComponentContainer component;
	protected Button buttonSave;
	protected Button buttonCancel;
	protected Button buttonDelete;
	// remember the clicked button
	protected Action actionCurrent = Action.UNDEFINED;
	protected Action actionPrevious = Action.UNDEFINED; // Previously clicked
														// button

	public Action getActionCurrent()
	{
		return this.actionCurrent;
	}


	protected Collection<String> getVisibleFields()
	{
		return Arrays.asList(visibleFields);
	}

	/**
	 * Returns a nice name of column.
	 * 
	 * @param propertyId
	 * @return niceName of it
	 */
	public String getColumnName(String propertyId)
	{
		return propertyId;
	}

	protected Form getForm()
	{
		return new Form();

	}

	public ItemsListScreen(MyVaadinApplication app, Class<T> clazz, String[] visibleColumns, String[] visibleFields)
	{
		this(app, clazz, visibleColumns, visibleFields, null);
	}

	public ItemsListScreen(MyVaadinApplication app, Class<T> clazz, String[] visibleColumns, String[] visibleFields,
	        String[] nestedContainerProperties)
	{
		super();

		this.app = app;
		this.clazz = clazz;
		this.services = this.app.getServices();
		this.visibleColumns = visibleColumns;
		this.visibleFields = visibleFields;
		this.nestedContainerProperties = nestedContainerProperties;

		this.form = this.getForm();
		this.form.setImmediate(true);
		this.form.setFormFieldFactory(this.getFormFieldFactory());

		this.component = this.getComponent();
		this.component.setEnabled(false);
		this.updateComponent(null);

		this.beanItemContainer = new BeanItemContainer<T>(this.clazz);
		this.addNestedContainerProperties();

		this.table = new Table();
		this.table.setSelectable(true);
		this.table.setMultiSelect(false);
		this.table.setImmediate(true);
		this.table.setContainerDataSource(beanItemContainer);
		this.table.addListener(new LocalValueChangeListener<T>(this));

		this.buttonItemAdd = new Button();
		this.buttonItemAdd.addListener(new CreateItemListener<T>(this));

		this.buttonItemAdd1 = new Button();
		this.buttonItemAdd1.addListener(new CreateItemListener<T>(this));

		this.buttonItemAdd2 = new Button();
		this.buttonItemAdd2.addListener(new CreateItemListener<T>(this));

		this.buttonItemDelete = new Button();
		this.buttonItemDelete.addListener(new DeleteItemListener<T>(this));
		this.buttonItemDelete.setEnabled(false);

		this.buttonSave = new Button(this.app.getMessage(ApplicationMessages.CommonSave));
		this.buttonSave.addListener(new SaveItemListener<T>(this));
		this.buttonSave.setEnabled(false);

		this.buttonCancel = new Button(this.app.getMessage(ApplicationMessages.CommonCancel));
		this.buttonCancel.addListener(new CancelButtonListener<T>(this));
		this.buttonCancel.setEnabled(false);

		this.buttonDelete = new Button(this.app.getMessage(ApplicationMessages.CommonDelete));
		this.buttonDelete.addListener(new DeleteItemListener<T>(this));
		this.buttonDelete.setEnabled(false);

		this.initLayout();

		// fill up with data
		try
		{
			for (T t : this.getAllBeans())
			{
				this.beanItemContainer.addBean(t);
			}
		}
		catch (Exception e)
		{
			logger.log(Level.SEVERE, "Exception: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	protected void updateComponent(T bean)
	{
		if (bean != null)
		{
			BeanItem<T> beanItem;

			try
			{
				this.notifyClick(Action.SELECT);

				beanItem = (BeanItem<T>) this.table.getItem(bean);

				this.form.setItemDataSource(beanItem, this.getVisibleFields());

				this.component.setEnabled(true);

				this.buttonItemAdd.setEnabled(true);
				this.buttonItemAdd1.setEnabled(true);
				this.buttonItemAdd2.setEnabled(true);
				this.buttonItemDelete.setEnabled(true);

				this.buttonSave.setEnabled(true);
				this.buttonCancel.setEnabled(true);
				this.buttonDelete.setEnabled(true);
			}
			catch (Exception e)
			{
				logger.log(Level.SEVERE, "Exception: " + e.getMessage());
			}
		}
		else
		{
			// fill with dummy
			T t = this.createBeanInstance();
			BeanItem<T> dummy = new BeanItem<T>(t);

			this.form.setItemDataSource(dummy, this.getVisibleFields());
		}
	}

	protected AbstractComponentContainer getComponent()
	{
		Panel p = new Panel();

		return p;
	}

	protected void layoutComponent()
	{
		this.layoutForm();

		this.component.setSizeFull();

		this.component.addComponent(this.form);
	}

	private void addNestedContainerProperties()
	{
		if (this.nestedContainerProperties == null)
		{
			return;
		}

		for (int i = 0; i < this.nestedContainerProperties.length; i++)
		{
			this.beanItemContainer.addNestedContainerProperty(nestedContainerProperties[i]);
		}
	}

	protected void initLayout()
	{
		VerticalLayout left = new VerticalLayout();
		VerticalLayout right = new VerticalLayout();

		this.layoutLeft(left);
		this.layoutRight(right);

		this.addComponent(left);
		this.addComponent(right);
	}

	protected void layoutLeft(VerticalLayout left)
	{
		HorizontalLayout searchRow = new HorizontalLayout();
		HorizontalLayout buttonRow = new HorizontalLayout();

		left.setSizeFull();
		left.setSpacing(true);
		left.setMargin(true);

		this.layoutTable();
		this.layoutSearchField(searchRow);
		this.layoutItemButtons();

		buttonRow.setSpacing(true);
		buttonRow.addComponent(this.buttonItemAdd);
		buttonRow.addComponent(this.buttonItemAdd1);
		buttonRow.addComponent(this.buttonItemAdd2);
		buttonRow.addComponent(this.buttonItemDelete);

		left.addComponent(searchRow);
		left.addComponent(this.table);
		left.addComponent(buttonRow);

		left.setExpandRatio(table, 1.0f);
	}

	protected void layoutTable()
	{
		this.table.setSizeFull();

		this.table.setVisibleColumns(this.visibleColumns);

		// Set nicer header names
		for (String propertyId : this.visibleColumns)
		{
			String columnName = this.getColumnName(propertyId);

			this.table.setColumnHeader(propertyId, columnName);
		}
	}

	protected void layoutItemButtons()
	{
		this.buttonItemAdd.setCaption("+");
		this.buttonItemDelete.setCaption("-");

		this.buttonItemAdd1.setVisible(false);
		this.buttonItemAdd2.setVisible(false);
	}

	protected void layoutSearchField(HorizontalLayout searchRow)
	{
		searchRow.setWidth("100%");
		searchRow.setCaption(this.app.getMessage(ApplicationMessages.CommonSearch));

		// Add search fields
		for (final String visibleColumn : this.visibleColumns)
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

			searchRow.addComponent(searchField);
			searchRow.setExpandRatio(searchField, 2);
		}
	}

	protected void layoutForm()
	{
		this.form.setSizeFull();
		this.form.setCaption(this.app.getMessage(ApplicationMessages.CommonDetails));
		this.form.setDescription(this.app.getMessage(ApplicationMessages.CommonDetailsDescription));
	}

	protected void layoutRight(VerticalLayout right)
	{
		HorizontalLayout buttonRow = new HorizontalLayout();

		right.setSizeFull();
		right.setMargin(true);
		right.setSpacing(true);

		this.layoutComponent();

		buttonRow.addComponent(this.buttonSave);
		buttonRow.addComponent(this.buttonCancel);
		buttonRow.addComponent(this.buttonDelete);

		right.addComponent(this.component);
		right.addComponent(buttonRow);

		right.setExpandRatio(this.component, 1.0f);
	}

	protected void notifyClick(Action action)
	{
		this.actionPrevious = this.actionCurrent;
		this.actionCurrent = action;
	}

	private static class CreateItemListener<T> implements Button.ClickListener
	{
		private static final long serialVersionUID = 1L;
		private ItemsListScreen<T> screen;

		public CreateItemListener(ItemsListScreen<T> itemListScreen)
		{
			this.screen = itemListScreen;
		}

		@Override
		public void buttonClick(ClickEvent event)
		{
			if (event.getButton().equals(this.screen.buttonItemAdd))
			{
				this.screen.notifyClick(Action.CREATE);
			}
			else if (event.getButton().equals(this.screen.buttonItemAdd1))
			{
				this.screen.notifyClick(Action.CREATE_1);
			}
			else if (event.getButton().equals(this.screen.buttonItemAdd2))
			{
				this.screen.notifyClick(Action.CREATE_2);
			}

			this.screen.updateComponent(null);
			this.screen.component.setEnabled(true);

			this.screen.buttonItemAdd.setEnabled(false);
			this.screen.buttonItemAdd1.setEnabled(false);
			this.screen.buttonItemAdd2.setEnabled(false);
			this.screen.buttonItemDelete.setEnabled(false);

			this.screen.buttonSave.setEnabled(true);
			this.screen.buttonDelete.setEnabled(false);
			this.screen.buttonCancel.setEnabled(true);

			this.screen.table.setEnabled(false);
		}
	}

	private static class DeleteItemListener<T> implements Button.ClickListener
	{
		private static final long serialVersionUID = 1L;
		private ItemsListScreen<T> screen;

		public DeleteItemListener(ItemsListScreen<T> itemsListScreen)
		{
			this.screen = itemsListScreen;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void buttonClick(ClickEvent event)
		{
			BeanItem<T> beanItemSuc;
			T bean = null;
			T beanSuc = null;

			this.screen.notifyClick(Action.DELETE);

			// Get selected element
			bean = (T) this.screen.table.getValue();

			// Determine neighbor entry (either successor or predecessor)
			beanItemSuc = (BeanItem<T>) this.screen.table.getItem(this.screen.table.nextItemId(bean));
			if (beanItemSuc == null)
			{
				beanItemSuc = (BeanItem<T>) this.screen.table.getItem(this.screen.table.prevItemId(bean));
			}

			try
			{
				this.screen.deleteBean(bean);

				this.screen.beanItemContainer.removeItem(bean);
			}
			catch (Exception ex)
			{
				logger.log(Level.SEVERE, "Exception: " + ex.getMessage());
			    
			    this.screen.app.showErrorMessage(this.screen.component, ex);
			}

			// fill the form out. If the list does not contain any entries
			// provide dummy and disable the form
			if (this.screen.beanItemContainer.size() == 0)
			{
				beanItemSuc = new BeanItem<T>(this.screen.createBeanInstance());

				this.screen.component.setEnabled(false);

				this.screen.buttonSave.setEnabled(false);
				this.screen.buttonCancel.setEnabled(false);
				this.screen.buttonDelete.setEnabled(false);

				this.screen.buttonItemAdd.setEnabled(true);
				this.screen.buttonItemAdd1.setEnabled(true);
				this.screen.buttonItemAdd2.setEnabled(true);
				this.screen.buttonItemDelete.setEnabled(false);
			}
			else
			{
				// Set selection to the neighbor entry.
				// If there were no elements more, select nothing
				beanSuc = beanItemSuc.getBean();

				this.screen.table.select(beanSuc);
			}

			this.screen.form.setItemDataSource(beanItemSuc, this.screen.getVisibleFields());
		}
	}

	private static class SaveItemListener<T> implements Button.ClickListener
	{
		private static final long serialVersionUID = 1L;
		private static Logger logger = Logger.getLogger(SaveItemListener.class.getName());

		private ItemsListScreen<T> screen;

		public SaveItemListener(ItemsListScreen<T> itemsListScreen)
		{
			this.screen = itemsListScreen;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void buttonClick(ClickEvent event)
		{
			BeanItem<T> beanItem;

			logger.info(">buttonClick");

			this.screen.notifyClick(Action.SAVE);

			beanItem = (BeanItem<T>) this.screen.form.getItemDataSource();

			try
			{
				// Save bean. Distinguish between create and update
				switch (this.screen.actionPrevious)
				{
					case CREATE:
					case CREATE_1:
					case CREATE_2:
						this.screen.createBean(beanItem.getBean());
						break;

					default:
						this.screen.updateBean(beanItem.getBean());
						break;
				}

				// I had several problem with table refresh, so this hard action
				this.screen.beanItemContainer.removeAllItems();
				for (T t : this.screen.getAllBeans())
				{
					this.screen.beanItemContainer.addBean(t);
				}

				this.screen.table.select(beanItem.getBean());
				this.screen.form.setItemDataSource(beanItem, this.screen.getVisibleFields());

				this.screen.buttonSave.setEnabled(true);
				this.screen.buttonCancel.setEnabled(true);
				this.screen.buttonDelete.setEnabled(true);

				this.screen.buttonItemAdd.setEnabled(true);
				this.screen.buttonItemAdd1.setEnabled(true);
				this.screen.buttonItemAdd2.setEnabled(true);
				this.screen.buttonItemDelete.setEnabled(true);
			}
			catch (Exception ex)
			{
				logger.log(Level.SEVERE, "Exception: " + ex.getMessage());

				this.screen.notifyClick(Action.SAVE_FAILURE);

				this.screen.app.showErrorMessage(this.screen.component, ex);

				this.screen.table.select(null);

				this.screen.component.setEnabled(false);

				this.screen.buttonSave.setEnabled(false);
				this.screen.buttonCancel.setEnabled(true);
				this.screen.buttonDelete.setEnabled(false);

				this.screen.buttonItemAdd.setEnabled(true);
				this.screen.buttonItemAdd1.setEnabled(true);
				this.screen.buttonItemAdd2.setEnabled(true);
				this.screen.buttonItemDelete.setEnabled(false);
			}

			this.screen.table.setEnabled(true);

			logger.info("<buttonClick");

		}
	}

	private static class CancelButtonListener<T> implements Button.ClickListener
	{
		private static final long serialVersionUID = 1L;

		private ItemsListScreen<T> screen;

		public CancelButtonListener(ItemsListScreen<T> itemsListScreen)
		{
			this.screen = itemsListScreen;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void buttonClick(ClickEvent event)
		{
			T bean = null;
			BeanItem<T> beanItem = null;
			boolean enableSave = true;
			boolean enableCancel = true;
			boolean enableDelete = true;
			boolean enableItemDelete = true;

			this.screen.notifyClick(Action.CANCEL);

			// refuse currently created bean
			if (this.screen.actionPrevious == Action.CREATE)
			{
				this.screen.component.setEnabled(false);

				enableSave = false;
				enableDelete = false;
				enableCancel = false;
				enableItemDelete = false;

				this.screen.table.select(null);
			}
			// refuse the current selected bean (cancel)
			// read it persistent version from database and update
			else if (this.screen.actionPrevious == Action.SELECT)
			{
				T beanPersistent;

				// retrieve selected item
				beanItem = (BeanItem<T>) this.screen.form.getItemDataSource();
				bean = beanItem.getBean();

				try
				{
					beanPersistent = this.screen.readBean(bean);

					// remove the changed from list and add the refreshed bean
					this.screen.beanItemContainer.removeItem(bean);
					this.screen.beanItemContainer.addBean(beanPersistent);
					beanItem = (BeanItem<T>) this.screen.table.getItem(beanPersistent);

					this.screen.table.select(beanPersistent);
				}
				catch (Exception ex)
				{
					logger.log(Level.SEVERE, "Exception: " + ex.getMessage());

					this.screen.app.showErrorMessage(this.screen.form, ex);
				}
			}
			else if (this.screen.actionPrevious == Action.SAVE_FAILURE)
			{
				this.screen.component.setEnabled(false);

				beanItem = (BeanItem<T>) this.screen.form.getItemDataSource();

				enableSave = false;
				enableDelete = false;
				enableCancel = false;
				enableItemDelete = false;

				this.screen.table.select(null);
			}
			else
			{
				// retrieve selected item
				beanItem = (BeanItem<T>) this.screen.form.getItemDataSource();
			}

			this.screen.updateComponent(bean);

			this.screen.table.setEnabled(true);

			this.screen.buttonSave.setEnabled(enableSave);
			this.screen.buttonCancel.setEnabled(enableCancel);
			this.screen.buttonDelete.setEnabled(enableDelete);

			this.screen.buttonItemAdd.setEnabled(true);
			this.screen.buttonItemAdd1.setEnabled(true);
			this.screen.buttonItemAdd2.setEnabled(true);
			this.screen.buttonItemDelete.setEnabled(enableItemDelete);
		}
	}

	private static class LocalValueChangeListener<T> implements Property.ValueChangeListener
	{
		private static final long serialVersionUID = 1L;
		private ItemsListScreen<T> screen;

		public LocalValueChangeListener(ItemsListScreen<T> itemsListScreen)
		{
			this.screen = itemsListScreen;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void valueChange(ValueChangeEvent event)
		{
			T bean;

			bean = (T) this.screen.table.getValue();

			this.screen.updateComponent(bean);
		}
	}
}