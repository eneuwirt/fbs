package com.fbs.web.vaadin.ui.common;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.fbs.web.vaadin.application.ApplicationServices;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
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
public abstract class ItemsListScreen<T> extends HorizontalSplitPanel
{
	private static final long serialVersionUID = 1L;

	public enum Action
	{
		CREATE, SAVE, SAVE_FAILURE, DELETE, CANCEL, SELECT, SELECT_NULL;
	}

	protected Class<T> clazz;
	protected MyVaadinApplication app;
	protected ApplicationServices services;
	// Visible columns for table and visible fields for form
	protected String[] visibleColumns;
	protected String[] visibleFields;
	protected String[] nestedContainerProperties;
	// Elements
	protected Table table;
	protected Form form;
	protected Component component;
	protected BeanItemContainer<T> beanItemContainer;
	// Additional buttons
	protected Button buttonItemAdd;
	protected Button buttonItemDelete;
	// Form buttons
	protected Button buttonSave;
	protected Button buttonCancel;
	protected Button buttonDelete;
	// remember the clicked button
	protected Action actionCurrent;
	protected Action actionPrevious; // Previously clicked button


	public Action getActionCurrent()
	{
		return this.actionCurrent;
	}


	/**
	 * Creates new bean instance with default values.
	 * 
	 * @return
	 */
	protected abstract T createBeanInstance();


	/**
	 * Returns the list of beans to display
	 * 
	 * @return
	 * @throws Exception
	 */
	protected abstract List<T> getAllBeans() throws Exception;


	/**
	 * Persist new bean.
	 * 
	 * @param t
	 * @return created instance. It must not be the same as params instance,
	 *         because several ids shall be generated etc.
	 * @throws Exception
	 */
	protected abstract T createBean(T t) throws Exception;


	/**
	 * Persist bean
	 * 
	 * @param t
	 * @throws Exception
	 */
	protected abstract void updateBean(T t) throws Exception;


	/**
	 * Read from database
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	protected abstract T readBean(T t) throws Exception;


	/**
	 * Delete persistent bean
	 * 
	 * @param t
	 * @throws Exception
	 */
	protected abstract void deleteBean(T t) throws Exception;


	private Collection<String> getVisibleFields()
	{
		return Arrays.asList(visibleFields);
	}


	protected abstract FormFieldFactory getFormFieldFactory();


	/**
	 * Returns a nice name of column.
	 * 
	 * @param propertyId
	 * @return niceName of it
	 */
	protected String getColumnName(String propertyId)
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
		this.form.setEnabled(false);
		this.form.setFormFieldFactory(this.getFormFieldFactory());
		this.resetForm();
		
		this.component = this.getComponent();
		this.component.setEnabled(false);
		this.resetComponent();

		this.beanItemContainer = new BeanItemContainer<T>(this.clazz);
		this.addNestedContainerProperties();

		this.table = new Table();
		this.table.setSelectable(true);
		this.table.setMultiSelect(false);
		this.table.setImmediate(true);
		this.table.setContainerDataSource(beanItemContainer);
		this.table.addListener(new LocalValueChangeListener<T>(this));

		this.buttonItemAdd = new Button("+");
		this.buttonItemAdd.addListener(new CreateItemListener<T>(this));

		this.buttonItemDelete = new Button("-");
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
			// ignore at first time
		}
	}


	protected void resetComponent()
    {
	    return;
    }


	protected Component getComponent()
    {
	    Panel p = new Panel();
	    
	    p.setVisible(false);
	    
	    return p;
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
		VerticalLayout bottomLeftCorner = new VerticalLayout();

		left.setSizeFull();
		left.setSpacing(true);
		left.setMargin(true);

		this.layoutTable();
		this.layoutButtomLeftCorner(bottomLeftCorner);

		left.addComponent(this.table);
		left.addComponent(bottomLeftCorner);

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


	protected void layoutButtomLeftCorner(VerticalLayout bottomLeftCorner)
	{
		HorizontalLayout searchRow = new HorizontalLayout();
		HorizontalLayout buttonRow = new HorizontalLayout();

		bottomLeftCorner.setWidth("100%");

		this.buttonItemDelete.setEnabled(false);
		this.layoutSearchField(searchRow);

		buttonRow.addComponent(this.buttonItemAdd);
		buttonRow.addComponent(this.buttonItemDelete);

		bottomLeftCorner.addComponent(searchRow);
		bottomLeftCorner.addComponent(buttonRow);

		bottomLeftCorner.setExpandRatio(searchRow, 1);

	}


	protected void layoutSearchField(HorizontalLayout searchRow)
	{
		Label searchLabel;

		searchRow.setWidth("100%");

		searchLabel = new Label(this.app.getMessage(ApplicationMessages.CommonSearch));
		searchRow.addComponent(searchLabel);
		searchRow.setExpandRatio(searchLabel, 1);

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
		
		right.setMargin(true);
		right.setSpacing(true);

		this.layoutForm();

		buttonRow.addComponent(this.buttonSave);
		buttonRow.addComponent(this.buttonCancel);
		buttonRow.addComponent(this.buttonDelete);

		right.addComponent(this.form);
		right.addComponent(this.component);

		right.addComponent(buttonRow);

		right.setExpandRatio(this.form, 1.0f);
	}


	private void notifyClick(Action action)
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
			this.screen.notifyClick(Action.CREATE);
			
			this.screen.resetForm();
			this.screen.form.setEnabled(true);
			
			this.screen.resetComponent();
			this.screen.component.setEnabled(true);

			this.screen.buttonItemAdd.setEnabled(false);
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
				this.screen.app.showErrorMessage(this.screen.form, ex);
			}

			// fill the form out. If the list does not contain any entries
			// provide dummy and disable the form
			if (this.screen.beanItemContainer.size() == 0)
			{
				beanItemSuc = new BeanItem<T>(this.screen.createBeanInstance());

				this.screen.form.setEnabled(false);
				this.screen.component.setEnabled(false);

				this.screen.buttonSave.setEnabled(false);
				this.screen.buttonCancel.setEnabled(false);
				this.screen.buttonDelete.setEnabled(false);

				this.screen.buttonItemAdd.setEnabled(true);
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
				if (this.screen.actionPrevious == Action.CREATE)
				{
					this.screen.createBean(beanItem.getBean());
				}
				else
				{
					this.screen.updateBean(beanItem.getBean());
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
				this.screen.buttonItemDelete.setEnabled(true);
			}
			catch (Exception ex)
			{
				logger.log(Level.SEVERE, "Exception: " + ex.getMessage());

				this.screen.notifyClick(Action.SAVE_FAILURE);

				this.screen.app.showErrorMessage(this.screen.form, ex);

				this.screen.table.select(null);

				this.screen.form.setEnabled(false);
				this.screen.component.setEnabled(false);

				this.screen.buttonSave.setEnabled(false);
				this.screen.buttonCancel.setEnabled(true);
				this.screen.buttonDelete.setEnabled(false);

				this.screen.buttonItemAdd.setEnabled(true);
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
			T bean;
			BeanItem<T> beanItem = null;
			boolean enableSave = true;
			boolean enableCancel = true;
			boolean enableDelete = true;
			boolean enableItemDelete = true;

			this.screen.notifyClick(Action.CANCEL);

			if (this.screen.actionPrevious == Action.CREATE)
			{
				bean = this.screen.createBeanInstance();
				beanItem = new BeanItem<T>(bean);

				this.screen.form.setEnabled(false);
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
					this.screen.app.showErrorMessage(this.screen.form, ex);
				}
			}
			else if (this.screen.actionPrevious == Action.SAVE_FAILURE)
			{
				this.screen.resetForm();
				this.screen.form.setEnabled(false);
				
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

			this.screen.form.setItemDataSource(beanItem, this.screen.getVisibleFields());
			
			this.screen.resetComponent();

			this.screen.table.setEnabled(true);

			this.screen.buttonSave.setEnabled(enableSave);
			this.screen.buttonCancel.setEnabled(enableCancel);
			this.screen.buttonDelete.setEnabled(enableDelete);

			this.screen.buttonItemAdd.setEnabled(true);
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
			BeanItem<T> beanItem;
			T bean;

			bean = (T) this.screen.table.getValue();

			// we have really clicked a row in the table or have to select an
			// entry
			if (bean != null)
			{				
				try
                {
					this.screen.notifyClick(Action.SELECT);
	                
					beanItem = (BeanItem<T>) this.screen.table.getItem(bean);

					this.screen.form.setItemDataSource(beanItem, this.screen.getVisibleFields());
					this.screen.form.setEnabled(true);
					
					this.screen.resetComponent();
					this.screen.component.setEnabled(true);

					this.screen.buttonItemAdd.setEnabled(true);
					this.screen.buttonItemDelete.setEnabled(true);

					this.screen.buttonSave.setEnabled(true);
					this.screen.buttonCancel.setEnabled(true);
					this.screen.buttonDelete.setEnabled(true);
                }
                catch (Exception e)
                {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }

			}
		}
	}


	protected void resetForm()
	{
		// fill with dummy
		T t = this.createBeanInstance();
		BeanItem<T> dummy = new BeanItem<T>(t);

		this.form.setItemDataSource(dummy, this.getVisibleFields());
	}
}