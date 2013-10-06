package com.atanor.smanager.services;

import java.util.List;

import com.atanor.smanager.domain.entity.Preset;

public interface PresetService {
	
	List<Preset> getAvailablePresets();
}
