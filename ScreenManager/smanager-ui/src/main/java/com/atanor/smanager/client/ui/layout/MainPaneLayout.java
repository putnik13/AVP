package com.atanor.smanager.client.ui.layout;

import com.atanor.smanager.client.ui.DisplayPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;

public class MainPaneLayout extends DockLayoutPanel {

	private final DisplayPanel navigateDisplay;
	private final DisplayPanel editDisplay;

	public MainPaneLayout() {
		super(Unit.PCT);

		// ============ Navigation ============
		{
			navigateDisplay = new DisplayPanel();
			addWest(navigateDisplay, 15);
		}

		// ============ Editor ============
		{
			editDisplay = new DisplayPanel();
			add(editDisplay);
		}
	}

	public DisplayPanel getNavigateDisplay() {
		return navigateDisplay;
	}

	public DisplayPanel getEditDisplay() {
		return editDisplay;
	}

	@Override
	public void onResize() {
		// do nothing since throws exception
	}

}