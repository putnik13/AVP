package com.atanor.smanager.client.mvp.activities.edit;

import com.atanor.smanager.client.Client;
import com.atanor.smanager.client.mvp.places.PresetSelectedPlace;
import com.atanor.smanager.client.mvp.presenters.EditPresetPresenter;
import com.atanor.smanager.client.mvp.views.EditPresetView;
import com.atanor.smanager.rpc.dto.PresetDto;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.smartgwt.client.util.SC;

public class PresetSelectedActivity extends AbstractActivity implements EditPresetPresenter {

	private final Long presetId;
	private final EditPresetView view;
	
	public PresetSelectedActivity(final Long presetId) {
		this.presetId = presetId;
		this.view = Client.getEditPresetView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.setPresenter(this);
		view.setPreset(presetId);
	}

	@Override
	public void savePreset(final PresetDto preset) {
		Client.getConfigService().savePreset(preset, new AsyncCallback<PresetDto>(){

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not save preset configuration");
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(PresetDto preset) {
				Client.getEditPresetView().setPresetConfiguration(preset);
				Client.getNavigatePresetView().setPresetConfiguration(preset);
				Client.goTo(new PresetSelectedPlace(preset.getId()));
			}});
		
	}

	@Override
	public void applyPreset(PresetDto preset) {
		// TODO Auto-generated method stub
		
	}

}
