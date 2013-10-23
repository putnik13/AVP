package com.atanor.smanager.client.mvp.views;

import com.atanor.smanager.rpc.dto.HardwareDto;
import com.google.gwt.user.client.ui.IsWidget;

public interface EditPresetView extends IsWidget {

	void setPreset(Long presetId);
	
	void setConfiguration(HardwareDto config);
	
	void cleanState();
}
