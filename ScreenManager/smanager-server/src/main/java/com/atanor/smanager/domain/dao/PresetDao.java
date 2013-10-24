package com.atanor.smanager.domain.dao;

import com.atanor.smanager.domain.entity.Preset;


public class PresetDao extends GenericDaoImpl<Preset, Long> {

	@Override
	public Class<Preset> getEntityClass() {
		return Preset.class;
	}

}
