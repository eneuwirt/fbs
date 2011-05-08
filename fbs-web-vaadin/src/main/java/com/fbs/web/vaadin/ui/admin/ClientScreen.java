package com.fbs.web.vaadin.ui.admin;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
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
	private static String[] fields =
	{ "First Name", "Last Name", "Company", "Mobile Phone", "Work Phone", "Home Phone", "Work Email", "Home Email",
	        "Street", "Zip", "City", "State", "Country" };
	private static String[] visibleCols = new String[]
	{ "Last Name", "First Name", "Company" };

	private Table contactList = new Table();
	private Form contactEditor = new Form();
	private HorizontalLayout bottomLeftCorner = new HorizontalLayout();
	private Button contactRemovalButton;
	private IndexedContainer addressBookData = createDummyData();


	public ClientScreen()
	{
		super();

		this.initLayout();
		this.initContactAddRemoveButtons();
		this.initAddressList();
		this.initFilteringControls();
	}


	private void initLayout()
	{		        
        contactList.setSizeFull();
        
        bottomLeftCorner.setWidth("100%");
        
        contactEditor.setSizeFull();
        contactEditor.setImmediate(true);
        
        VerticalLayout left = new VerticalLayout();
        left.setSizeFull();   
        left.setSpacing(true);
        left.setMargin(true);
        left.addComponent(contactList);
        left.addComponent(bottomLeftCorner);
        
        VerticalLayout right = new VerticalLayout();
        right.setSizeFull();
        right.addComponent(contactEditor);
        
        this.addComponent(left);
        this.addComponent(right);
        
        
        //as last
        left.setExpandRatio(contactList, 1.0f);
        right.setExpandRatio(contactEditor, 1.0f);
	}


	private void initFilteringControls()
	{
		for (final String pn : visibleCols)
		{
			final TextField sf = new TextField();
			bottomLeftCorner.addComponent(sf);
			sf.setWidth("100%");
			sf.setInputPrompt(pn);
			sf.setImmediate(true);
			bottomLeftCorner.setExpandRatio(sf, 1);

			sf.addListener(new Property.ValueChangeListener()
			{
				public void valueChange(ValueChangeEvent event)
				{
					addressBookData.removeContainerFilters(pn);
					if (sf.toString().length() > 0 && !pn.equals(sf.toString()))
					{
						addressBookData.addContainerFilter(pn, sf.toString(), true, false);
					}
					// getMainWindow().showNotification("" +
					// addressBookData.size() + " matches found");
				}
			});
		}
	}


	private void initContactAddRemoveButtons()
	{
		// New item button
		bottomLeftCorner.addComponent(new Button("+", new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event)
			{
				Object id = contactList.addItem();
				contactList.setValue(id);
			}
		}));

		// Remove item button
		contactRemovalButton = new Button("-", new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event)
			{
				contactList.removeItem(contactList.getValue());
				contactList.select(null);
			}
		});
		contactRemovalButton.setVisible(false);
		bottomLeftCorner.addComponent(contactRemovalButton);
	}


	private String[] initAddressList()
	{
		contactList.setContainerDataSource(addressBookData);
		contactList.setVisibleColumns(visibleCols);
		contactList.setSelectable(true);
		contactList.setImmediate(true);
		contactList.addListener(new Property.ValueChangeListener()
		{
			public void valueChange(ValueChangeEvent event)
			{
				Object id = contactList.getValue();
				contactEditor.setItemDataSource(id == null ? null : contactList.getItem(id));
				contactRemovalButton.setVisible(id != null);
			}
		});
		return visibleCols;
	}


	private static IndexedContainer createDummyData()
	{

		String[] fnames =
		{ "Peter", "Alice", "Joshua", "Mike", "Olivia", "Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene",
		        "Lisa", "Marge" };
		String[] lnames =
		{ "Smith", "Gordon", "Simpson", "Brown", "Clavel", "Simons", "Verne", "Scott", "Allison", "Gates", "Rowling",
		        "Barks", "Ross", "Schneider", "Tate" };

		IndexedContainer ic = new IndexedContainer();

		for (String p : fields)
		{
			ic.addContainerProperty(p, String.class, "");
		}

		for (int i = 0; i < 1000; i++)
		{
			Object id = ic.addItem();
			ic.getContainerProperty(id, "First Name").setValue(fnames[(int) (fnames.length * Math.random())]);
			ic.getContainerProperty(id, "Last Name").setValue(lnames[(int) (lnames.length * Math.random())]);
		}

		return ic;
	}
}
