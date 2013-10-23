package com.atanor.smanager.client.mvp.activities.edit;

import com.atanor.smanager.client.Client;
import com.atanor.smanager.client.mvp.views.EditPresetView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PresetSelectedActivity extends AbstractActivity {

	private final Long presetId;
	private final EditPresetView view;
	
	public PresetSelectedActivity(final Long presetId) {
		this.presetId = presetId;
		this.view = Client.getEditPresetView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.setPreset(presetId);
	}

}
