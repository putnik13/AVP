package com.atanor.vserver.vsadmin.client.ui;

import javax.inject.Inject;

import com.smartgwt.client.widgets.layout.VLayout;

public class ContentPane extends VLayout {

	@Inject
	public ContentPane(final HeaderPane headerPane, final SectionsPane sectionsPane) {
		setHeight100();
		setWidth100();

		addChild(headerPane);
		addChild(sectionsPane);
		
		headerPane.bringToFront();
	}
}
