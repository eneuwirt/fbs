package com.fbs.web.vaadin.ui.document;

import java.util.Arrays;
import java.util.List;

import com.vaadin.event.Action;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
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
	private Button createButton2;
	private Button deleteButton;
	private Button moveButton;

	private void createRight()
	{		
		panelRight.setSizeFull();

		Panel panelDocDet = new Panel("Dokumentdetails");
		panelDocDet.setSizeFull();
		panelDocDet.addComponent(new Label("Akte X"));
		panelDocDet.addComponent(new Button("Neue Dokumentenversion Uploaden"));
		panelDocDet.addComponent(new Label("Dokumentname: Schreiben an die Galaktische Allianz."));
		panelDocDet.addComponent(new Label("Aktuelle Version 10"));
		panelDocDet.addComponent(new Label("Status: in Bearbeitung"));
		panelDocDet.addComponent(new Label("Angeleget von Agent Mulder"));
		TextArea ta = new TextArea("Bemerkungen",
        "Ist sehr wichtig die neuen Regeln vom ... zu beachten. Die Aliens reagieren allergisch auf Birkenpollen");
		ta.setWidth("100%");
		ta.setRows(4);

		panelDocDet.addComponent(ta);

		HorizontalLayout permissions = new HorizontalLayout();
		permissions.setSizeFull();
		permissions.setMargin(true);
		permissions.setSpacing(true);
		final List<String> cities = Arrays.asList(new String[] { "Agent Mulder", "Agent Scully", "Irmgard Unglück",
		        "Peter van Bethooven", "Otto der Hausmeister", "Helge Stengel", "Jeder" });
		OptionGroup citySelect = new OptionGroup("Lesen und Schreiben", cities);
		citySelect.setNullSelectionAllowed(true); // user can not 'unselect'
		citySelect.setMultiSelect(true);
		citySelect.select("Agent Mulder"); // select this by default
		citySelect.select("Agent Scully"); // select this by default
		citySelect.setImmediate(true); // send the change to the server at once

		OptionGroup citySelect2 = new OptionGroup("Nur Lesen", cities);
		citySelect2.setNullSelectionAllowed(true); // user can not 'unselect'
		citySelect2.setMultiSelect(true);
		citySelect2.select("Otto der Hausmeister"); // select this by default
		citySelect2.select("Peter van Bethooven"); // select this by default
		citySelect2.setImmediate(true); // send the change to the server at once

		permissions.addComponent(citySelect);
		permissions.addComponent(citySelect2);

		panelDocDet.addComponent(permissions);
		panelDocDet.addComponent(new Button("Speichern"));

		panelRight.addComponent(panelDocDet);

		for (int i = 10; i > 0; i--)
		{
			HorizontalLayout hl1 = new HorizontalLayout();

			hl1.setMargin(true);
			hl1.setSpacing(true);
			hl1.addComponent(new Label("Schreiben an die Galaktische Allianz"));
			hl1.addComponent(new Label("Version " + i));
			hl1.addComponent(new Label("Angelegt von Mulder"));
			hl1.addComponent(new Button("Download"));
			hl1.addComponent(new Button("Löschen"));

			panelRight.addComponent(hl1);
		}
		
	}
	
	private void createLeft()
	{
		panelLeft.setSizeFull();

		this.createButton = new Button("Anlegen Ordner");
		this.createButton2 = new Button("Anlegen Document");
		this.deleteButton = new Button("Löschen");
		this.moveButton = new Button("Verschieben");
		HorizontalLayout buttonRow = new HorizontalLayout();
		buttonRow.addComponent(this.createButton2);
		buttonRow.addComponent(this.createButton);
		buttonRow.addComponent(this.deleteButton);
		buttonRow.addComponent(this.moveButton);
		
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
	}

	public DocumentTree()
	{
		this.setSizeFull();
		setSpacing(true);

		panelLeft = new Panel("Aktenverwaltung");
		this.createLeft();

		panelRight = new Panel("Details");
		this.createRight();
		

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
