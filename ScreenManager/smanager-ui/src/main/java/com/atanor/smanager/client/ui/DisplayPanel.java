package com.atanor.smanager.client.ui;

import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class DisplayPanel extends SimplePanel implements RequiresResize, ProvidesResize {

	public DisplayPanel() {
	}

	@Override
	public void onResize() {
		Widget child = getWidget();
		if ((child != null) && (child instanceof RequiresResize)) {
			((RequiresResize) child).onResize();
		}
	}

}
