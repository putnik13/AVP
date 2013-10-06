package com.atanor.smanager.services;

import java.util.List;

import com.atanor.smanager.domain.dao.PresetDao;
import com.atanor.smanager.domain.entity.Preset;
import com.google.inject.Inject;

public class PresetServiceImpl implements PresetService{

	@Inject
	private PresetDao presetDao;
	
	@Override
	public List<Preset> getAvailablePresets() {
		return presetDao.findAll();
	}

}
