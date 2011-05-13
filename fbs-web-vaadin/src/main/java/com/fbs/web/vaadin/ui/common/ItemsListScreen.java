package com.fbs.web.vaadin.ui.common;

import java.util.List;

import com.fbs.web.vaadin.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
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
		CREATE, SAVE, DELETE, CANCEL, SELECT, SELECT_NULL;
	}

	private Class<T> clazz;
	protected MyVaadinApplication app;
	// Elements
	private Table table;
	private Form form;
	private BeanItemContainer<T> beanItemContainer;
	// Additional buttons
	private Button buttonItemAdd;
	private Button buttonItemDelete;
	// Form buttons
	private Button buttonSave;
	private Button buttonCancel;
	private Button buttonDelete;
	// remember the clicked button
	protected Action actionCurrent;
	protected Action actionPrevious; // Previously clicked button


	private void notifyClick(Action action)
	{
		this.actionPrevious = this.actionCurrent;
		this.actionCurrent = action;
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
	 */
	protected abstract List<T> getAllBeans();


	/**
	 * Creates new instance.
	 * 
	 * @param t
	 * @return created instance. It must not be the same as params instance,
	 *         because several ids shall be generated etc.
	 */
	protected abstract T createBean(T t);


	protected abstract void updateBean(T t);


	protected abstract T readBean(T t);


	protected abstract void deleteBean(T t);


	/**
	 * returns the visible columns as required by Table.
	 * 
	 * @return
	 */
	protected abstract String[] getVisibleColumns();


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


	public ItemsListScreen(MyVaadinApplication app, Class<T> clazz)
	{
		super();

		this.app = app;
		this.clazz = clazz;

		this.form = new Form();
		this.form.setImmediate(true);
		this.form.setEnabled(false);

		this.beanItemContainer = new BeanItemContainer<T>(this.clazz);
		for (T t : this.getAllBeans())
		{
			this.beanItemContainer.addBean(t);
		}

		this.table = new Table();
		this.table.setSelectable(true);
		this.table.setMultiSelect(false);
		this.table.setImmediate(true);
		// this.table.setNullSelectionAllowed(true);
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

		this.table.setVisibleColumns(this.getVisibleColumns());

		// Set nicer header names
		for (String propertyId : this.getVisibleColumns())
		{
			String columnName = this.getColumnName(propertyId);

			this.table.setColumnHeader(propertyId, columnName);
		}
	}


	private void layoutButtomLeftCorner(HorizontalLayout bottomLeftCorner)
	{
		bottomLeftCorner.setWidth("100%");

		this.buttonItemDelete.setEnabled(false);

		bottomLeftCorner.addComponent(this.buttonItemAdd);
		bottomLeftCorner.addComponent(this.buttonItemDelete);
		// Add search fields
		for (final String visibleColumn : this.getVisibleColumns())
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


	private void layoutRight(VerticalLayout right)
	{
		this.form.setSizeFull();
		this.form.setImmediate(true);
		this.form.setCaption(this.app.getMessage(ApplicationMessages.CommonDetails));
		this.form.setDescription(this.app.getMessage(ApplicationMessages.CommonDetailsDescription));

		// fill with dummy
		T t = this.createBeanInstance();
		BeanItem<T> dummy = new BeanItem<T>(t);
		this.form.setItemDataSource(dummy);

		HorizontalLayout buttonRow = new HorizontalLayout();
		buttonRow.addComponent(this.buttonSave);
		buttonRow.addComponent(this.buttonCancel);
		buttonRow.addComponent(this.buttonDelete);

		// right.setSizeFull();
		right.addComponent(this.form);
		right.addComponent(buttonRow);

		right.setExpandRatio(this.form, 1.0f);
	}

	private static class CreateItemListener<T> implements Button.ClickListener
	{
		private static final long serialVersionUID = 1L;
		private ItemsListScreen<T> itemsListScreen;


		public CreateItemListener(ItemsListScreen<T> itemListScreen)
		{
			this.itemsListScreen = itemListScreen;
		}


		@Override
		public void buttonClick(ClickEvent event)
		{
			T bean;
			BeanItem<T> beanItem;

			this.itemsListScreen.notifyClick(Action.CREATE);

			bean = this.itemsListScreen.createBeanInstance();
			beanItem = new BeanItem<T>(bean);

			this.itemsListScreen.form.setEnabled(true);
			this.itemsListScreen.form.setItemDataSource(beanItem);

			this.itemsListScreen.buttonItemAdd.setEnabled(false);
			this.itemsListScreen.buttonItemDelete.setEnabled(false);

			this.itemsListScreen.buttonSave.setEnabled(true);
			this.itemsListScreen.buttonDelete.setEnabled(false);
			this.itemsListScreen.buttonCancel.setEnabled(true);

			//this.itemsListScreen.table.select(null);
			this.itemsListScreen.table.setEnabled(false);
		}
	}

	private static class DeleteItemListener<T> implements Button.ClickListener
	{
		private static final long serialVersionUID = 1L;
		private ItemsListScreen<T> itemsListScreen;


		public DeleteItemListener(ItemsListScreen<T> itemsListScreen)
		{
			this.itemsListScreen = itemsListScreen;
		}


		@SuppressWarnings("unchecked")
		@Override
		public void buttonClick(ClickEvent event)
		{
			BeanItem<T> beanItemSuc;
			T bean =null;
			T beanSuc = null;

			this.itemsListScreen.notifyClick(Action.DELETE);

			// Get selected element
			bean = (T) this.itemsListScreen.table.getValue();

			// Determine neighbor entry (either successor or the previous
			beanItemSuc = (BeanItem<T>) this.itemsListScreen.table.getItem(this.itemsListScreen.table.nextItemId(bean));
			if (beanItemSuc == null)
			{
				beanItemSuc = (BeanItem<T>) this.itemsListScreen.table.getItem(this.itemsListScreen.table
				        .prevItemId(bean));
			}

			this.itemsListScreen.deleteBean(bean);
			this.itemsListScreen.beanItemContainer.removeItem(bean);

		
			// and fill the form out. If the list does not contain any entries
			// provide dummy and disable the form
			if (this.itemsListScreen.beanItemContainer.size() == 0)
			{
				beanItemSuc = new BeanItem<T>(this.itemsListScreen.createBeanInstance());

				this.itemsListScreen.form.setEnabled(false);
				
				this.itemsListScreen.buttonSave.setEnabled(false);
				this.itemsListScreen.buttonCancel.setEnabled(false);
				this.itemsListScreen.buttonDelete.setEnabled(false);
				
				this.itemsListScreen.buttonItemAdd.setEnabled(true);
				this.itemsListScreen.buttonItemDelete.setEnabled(false);
			}
			else
			{
				// set selection to the neighbor entry. if it was the last element
				// in the list, do not select anything
				beanSuc = beanItemSuc.getBean();
				
				this.itemsListScreen.table.select(beanSuc);
			}

			this.itemsListScreen.form.setItemDataSource(beanItemSuc);
		}
	}

	private static class SaveItemListener<T> implements Button.ClickListener
	{
		private static final long serialVersionUID = 1L;
		private ItemsListScreen<T> itemsListScreen;


		public SaveItemListener(ItemsListScreen<T> itemsListScreen)
		{
			this.itemsListScreen = itemsListScreen;
		}


		@SuppressWarnings("unchecked")
		@Override
		public void buttonClick(ClickEvent event)
		{
			BeanItem<T> beanItem;

			this.itemsListScreen.notifyClick(Action.SAVE);

			beanItem = (BeanItem<T>) this.itemsListScreen.form.getItemDataSource();

			// Save the new created bean ?
			if (this.itemsListScreen.actionPrevious == Action.CREATE)
			{
				T bean;

				bean = this.itemsListScreen.createBean(beanItem.getBean());

				beanItem = new BeanItem<T>(bean);

				this.itemsListScreen.beanItemContainer.addBean(bean);
			}
			else
			{
				this.itemsListScreen.updateBean(beanItem.getBean());
			}
			
			this.itemsListScreen.table.setEnabled(true);
			this.itemsListScreen.table.select(beanItem.getBean());
			this.itemsListScreen.form.setItemDataSource(beanItem);

			this.itemsListScreen.buttonSave.setEnabled(true);
			this.itemsListScreen.buttonCancel.setEnabled(true);
			this.itemsListScreen.buttonDelete.setEnabled(true);
			
			this.itemsListScreen.buttonItemAdd.setEnabled(true);
			this.itemsListScreen.buttonItemDelete.setEnabled(true);
		}
	}

	private static class CancelButtonListener<T> implements Button.ClickListener
	{
		private static final long serialVersionUID = 1L;

		private ItemsListScreen<T> itemsListScreen;


		public CancelButtonListener(ItemsListScreen<T> itemsListScreen)
		{
			this.itemsListScreen = itemsListScreen;
		}


		@Override
		public void buttonClick(ClickEvent event)
		{
			T bean;
			BeanItem<T> beanItem;
			boolean enableSave = true;
			boolean enableCancel = true;
			boolean enableDelete = true;
			boolean enableItemDelete = true;

			this.itemsListScreen.notifyClick(Action.CANCEL);

			if (this.itemsListScreen.actionPrevious == Action.CREATE)
			{
				bean = this.itemsListScreen.createBeanInstance();
				beanItem = new BeanItem<T>(bean);
				
				this.itemsListScreen.form.setEnabled(false);
				this.itemsListScreen.form.setItemDataSource(beanItem);

				enableSave = false;
				enableDelete = false;
				enableCancel = false;
				enableItemDelete = false;

				this.itemsListScreen.table.select(null);
			}
			
			this.itemsListScreen.table.setEnabled(true);

			this.itemsListScreen.buttonSave.setEnabled(enableSave);
			this.itemsListScreen.buttonCancel.setEnabled(enableCancel);
			this.itemsListScreen.buttonDelete.setEnabled(enableDelete);

			this.itemsListScreen.buttonItemAdd.setEnabled(true);
			this.itemsListScreen.buttonItemDelete.setEnabled(enableItemDelete);
		}
	}

	private static class LocalValueChangeListener<T> implements Property.ValueChangeListener
	{
		private static final long serialVersionUID = 1L;
		private ItemsListScreen<T> itemsListScreen;


		public LocalValueChangeListener(ItemsListScreen<T> itemsListScreen)
		{
			this.itemsListScreen = itemsListScreen;
		}


		@SuppressWarnings("unchecked")
		@Override
		public void valueChange(ValueChangeEvent event)
		{
			BeanItem<T> beanItem;
			T bean;

			bean = (T) this.itemsListScreen.table.getValue();

			// we have really clicked a row in the table
			if (bean != null)
			{
				this.itemsListScreen.notifyClick(Action.SELECT);

				beanItem = (BeanItem<T>) this.itemsListScreen.table.getItem(bean);

				this.itemsListScreen.form.setItemDataSource(beanItem);

				this.itemsListScreen.form.setEnabled(true);

				this.itemsListScreen.buttonItemAdd.setEnabled(true);
				this.itemsListScreen.buttonItemDelete.setEnabled(true);

				this.itemsListScreen.buttonSave.setEnabled(true);
				this.itemsListScreen.buttonCancel.setEnabled(true);
				this.itemsListScreen.buttonDelete.setEnabled(true);
			}
		}

	}
}
