package com.fbs.web.vaadin.ui.document;

import com.vaadin.event.Action;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;

public class DocumentTree extends HorizontalLayout implements Action.Handler
{
	private static final long serialVersionUID = 1L;

	private static final Action ACTION_ADD = new Action("Add child item");
	private static final Action ACTION_DELETE = new Action("Delete");
	private static final Action[] ACTIONS = new Action[] { ACTION_ADD, ACTION_DELETE };

	final Object[][] planets = new Object[][] { new Object[] { "Aktenummer 7411" },
	        new Object[] { "Aktennummer 4711" }, new Object[] { "Aktennummer 11", "Anschreiben" },
	        new Object[] { "Fall 1234", "Mahnung", "Klage" },
	        new Object[] { "Fall 34567XYZ", "Klageschrift", "Kontaktschreiben", "Gerichtsvollzieher", "Brief an ." },
	        new Object[] { "Fall 1233", "Klageschrift", "Kontaktschreiben", "Gerichtsvollzieher", "Brief an ." },
	        new Object[] { "Fall 1124", "Klageschrift", "Kontaktschreiben", "Gerichtsvollzieher", "Brief an ." },
	        new Object[] { "Akte X", "Mulder", "Scully", "Alien", "Predator" } };

	private Tree tree;
	private Panel panelLeft;
	private Panel panelRight;

	private Button createButton;
	private Button deleteButton;
	private Button moveButton;


	public DocumentTree()
	{
		this.setSizeFull();
		setSpacing(true);

		panelLeft = new Panel("Aktenverwaltung");
		panelLeft.setSizeFull();
		
		this.createButton = new Button("Anlegen");
		this.deleteButton = new Button ("Löschen");
		this.moveButton = new Button("Verschieben");
		HorizontalLayout buttonRow = new HorizontalLayout();
		buttonRow.addComponent(this.createButton);
		buttonRow.addComponent(this.deleteButton);
		buttonRow.addComponent(this.moveButton);

		panelRight = new Panel("Details");
		panelRight.setSizeFull();

		// Let's add a few rows to provoke scrollbars:
		for (int i = 0; i < 20; i++)
		{
			panelRight.addComponent(new Label("The quick brown fox jumps over the lazy dog."));
		}

		// Create new Tree object using a hierarchical container from
		// ExampleUtil class
		tree = new Tree("Akten");
		tree.setSizeFull();

		// Set multiselect mode
		tree.setMultiSelect(false);
		tree.setImmediate(true);

		/* Add planets as root items in the tree. */
		for (int i = 0; i < planets.length; i++)
		{
			String planet = (String) (planets[i][0]);
			tree.addItem(planet);

			if (planets[i].length == 1)
			{
				// The planet has no moons so make it a leaf.
				tree.setChildrenAllowed(planet, false);
			}
			else
			{
				// Add children (moons) under the planets.
				for (int j = 1; j < planets[i].length; j++)
				{
					String moon = (String) planets[i][j];

					// Add the item as a regular item.
					tree.addItem(moon);

					// Set it to be a child.
					tree.setParent(moon, planet);

					// Make the moons look like leaves.
					tree.setChildrenAllowed(moon, false);
				}

				// Expand the subtree.
				tree.expandItemsRecursively(planet);
			}
		}

		this.panelLeft.addComponent(tree);
		this.panelLeft.addComponent(buttonRow);

		this.addComponent(panelLeft);
		this.addComponent(panelRight);

		this.setExpandRatio(panelLeft, 1.0F);
		this.setExpandRatio(panelRight, 1.0F);

	}


	@Override
	public Action[] getActions(Object target, Object sender)
	{

		return null;
	}


	@Override
	public void handleAction(Action action, Object sender, Object target)
	{

	}

}
