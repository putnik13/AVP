package com.atanor.smanager.services;

import com.atanor.smanager.domain.entity.Hardware;
import com.atanor.smanager.domain.entity.Preset;

public interface EquipmentConfigService {

	Hardware getActiveHardware();

	Boolean savePreset(Preset preset);
	
	Preset getPresetById(Long presetId);
}
