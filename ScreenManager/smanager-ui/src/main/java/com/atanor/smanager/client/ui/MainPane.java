package com.atanor.smanager.client.ui;

import com.atanor.smanager.client.mvp.EditPresetMapper;
import com.atanor.smanager.client.mvp.NavigatePresetMapper;
import com.atanor.smanager.client.ui.layout.MainPaneLayout;
import com.google.gwt.user.client.ui.IsWidget;

public class MainPane extends RootPane<MainPaneLayout> implements IsWidget {

	public MainPane() {
		super(new MainPaneLayout());

		bind(new NavigatePresetMapper(), asWidget().getNavigateDisplay());
		bind(new EditPresetMapper(), asWidget().getEditDisplay());
	}

}
