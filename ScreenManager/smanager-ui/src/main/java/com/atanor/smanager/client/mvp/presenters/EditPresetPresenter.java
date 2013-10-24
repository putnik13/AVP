package com.atanor.smanager.client.mvp.presenters;

import com.atanor.smanager.rpc.dto.PresetDto;

public interface EditPresetPresenter {
	
	void savePreset(PresetDto preset);
	void applyPreset(PresetDto preset);
}
