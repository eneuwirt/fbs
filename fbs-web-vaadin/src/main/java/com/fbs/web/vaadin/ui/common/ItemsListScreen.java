package com.fbs.web.vaadin.ui.common;

import java.util.List;

import com.fbs.web.vaadin.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.vaadin.data.Item;
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
	private Class<T> clazz;
	protected MyVaadinApplication app;
	private Table table;
	private Form form;
	private BeanItemContainer<T> beanItemContainer;
	// Additional buttons
	private Button itemAddButton;
	private Button itemDeleteButton;
	// Form buttons
	private Button saveButton;
	private Button cancelButton;
	private Button deleteButton;
	// execute create if save button pushed
	public boolean createInsteadOfSave = false;


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

		this.itemAddButton = new Button("+");
		this.itemAddButton.addListener(new CreateItemListener<T>(this));

		this.itemDeleteButton = new Button("-");
		this.itemDeleteButton.addListener(new DeleteItemListener<T>(this));
		this.itemDeleteButton.setEnabled(false);

		this.saveButton = new Button(this.app.getMessage(ApplicationMessages.CommonSave));
		this.saveButton.addListener(new SaveItemListener<T>(this));
		this.saveButton.setEnabled(false);

		this.cancelButton = new Button(this.app.getMessage(ApplicationMessages.CommonCancel));
		this.cancelButton.addListener(new CancelButtonListener<T>(this));
		this.cancelButton.setEnabled(false);

		this.deleteButton = new Button(this.app.getMessage(ApplicationMessages.CommonDelete));
		this.deleteButton.addListener(new DeleteItemListener<T>(this));	
		this.deleteButton.setEnabled(false);

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

		this.itemDeleteButton.setEnabled(false);

		bottomLeftCorner.addComponent(this.itemAddButton);
		bottomLeftCorner.addComponent(this.itemDeleteButton);
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
		buttonRow.addComponent(this.saveButton);
		buttonRow.addComponent(this.cancelButton);
		buttonRow.addComponent(this.deleteButton);

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

			bean = this.itemsListScreen.createBeanInstance();
			beanItem = new BeanItem<T>(bean);

			this.itemsListScreen.createInsteadOfSave = true;

			this.itemsListScreen.form.setEnabled(true);
			this.itemsListScreen.form.setItemDataSource(beanItem);

			this.itemsListScreen.saveButton.setEnabled(true);
			this.itemsListScreen.itemDeleteButton.setEnabled(false);
			this.itemsListScreen.cancelButton.setEnabled(true);

			this.itemsListScreen.table.select(null);
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
			T bean;
			T beanSuc = null;

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

			// set selection to the neighbor entry. if it was the last element
			// in the list, do not select anything
			if (beanItemSuc != null)
			{
				beanSuc = beanItemSuc.getBean();
			}
			this.itemsListScreen.table.select(beanSuc);

			// and fill the form out. If the list does not contain any entries
			// provide dummy and disable the form
			if (this.itemsListScreen.beanItemContainer.size() == 0)
			{
				beanItemSuc = new BeanItem<T>(this.itemsListScreen.createBeanInstance());

				this.itemsListScreen.form.setEnabled(false);
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

			beanItem = (BeanItem<T>) this.itemsListScreen.form.getItemDataSource();

			if (this.itemsListScreen.createInsteadOfSave)
			{
				T bean;

				bean = this.itemsListScreen.createBean(beanItem.getBean());

				beanItem = new BeanItem<T>(bean);

				this.itemsListScreen.beanItemContainer.addBean(bean);

				this.itemsListScreen.createInsteadOfSave = false;
			}
			else
			{
				this.itemsListScreen.updateBean(beanItem.getBean());
			}

			this.itemsListScreen.table.select(beanItem.getBean());
			this.itemsListScreen.form.setItemDataSource(beanItem);

			this.itemsListScreen.saveButton.setEnabled(true);
			this.itemsListScreen.itemDeleteButton.setEnabled(true);
			this.itemsListScreen.cancelButton.setEnabled(true);
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
			this.itemsListScreen.createInsteadOfSave = false;

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

			// we have really click a row in the table
			if (bean != null)
			{
				beanItem = (BeanItem<T>) this.itemsListScreen.table.getItem(bean);

				this.itemsListScreen.form.setItemDataSource(beanItem);

				// forget the creation. User clicked the table
				this.itemsListScreen.createInsteadOfSave = false;
			}
			
			this.itemsListScreen.form.setEnabled(bean != null|| this.itemsListScreen.createInsteadOfSave);
			this.itemsListScreen.itemDeleteButton.setEnabled(bean != null);
			this.itemsListScreen.saveButton.setEnabled(bean != null || this.itemsListScreen.createInsteadOfSave);
			this.itemsListScreen.cancelButton.setEnabled(bean != null || this.itemsListScreen.createInsteadOfSave);
			this.itemsListScreen.deleteButton.setEnabled(bean != null);
		}

	}

}
