package com.atanor.vserver.vsadmin.client.ui;

import javax.inject.Inject;

import com.smartgwt.client.widgets.layout.HLayout;

public class MainPane extends HLayout {

	@Inject
	public MainPane(final NavigatePane navigatePane, final ContentPane contentPane) {
		setHeight100();
		setWidth100();

		addMembers(navigatePane, contentPane);
	}
}
