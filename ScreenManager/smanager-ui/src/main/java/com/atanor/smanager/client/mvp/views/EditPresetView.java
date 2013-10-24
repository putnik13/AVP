package com.atanor.smanager.client.mvp.views;

import com.atanor.smanager.client.mvp.presenters.EditPresetPresenter;
import com.atanor.smanager.rpc.dto.HardwareDto;
import com.atanor.smanager.rpc.dto.PresetDto;
import com.google.gwt.user.client.ui.IsWidget;

public interface EditPresetView extends IsWidget {

	void setPresenter(EditPresetPresenter presenter);

	void setPreset(Long presetId);

	void setConfiguration(HardwareDto config);
	
	void setPresetConfiguration(PresetDto preset);
	
	void cleanState();
	
	void onPresetApplied();
}
