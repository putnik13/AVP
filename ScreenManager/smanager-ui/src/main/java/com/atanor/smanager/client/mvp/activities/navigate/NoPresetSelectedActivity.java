package com.atanor.smanager.client.mvp.activities.navigate;

import com.atanor.smanager.client.Client;
import com.atanor.smanager.client.mvp.views.NavigatePresetView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class NoPresetSelectedActivity extends AbstractActivity {

	private final NavigatePresetView view;
	
	public NoPresetSelectedActivity() {
		view = Client.getNavigatePresetView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.cleanState();
	}

}
