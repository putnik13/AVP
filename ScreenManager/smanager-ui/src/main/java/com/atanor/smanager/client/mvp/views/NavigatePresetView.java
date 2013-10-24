package com.atanor.smanager.client.mvp.views;

import com.atanor.smanager.rpc.dto.HardwareDto;
import com.atanor.smanager.rpc.dto.PresetDto;
import com.google.gwt.user.client.ui.IsWidget;

public interface NavigatePresetView extends IsWidget {

	void setPreset(Long presetId);
	
	void setConfiguration(HardwareDto config);
	
	void setPresetConfiguration(PresetDto preset);

	void cleanState();
}
