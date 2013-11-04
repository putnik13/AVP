package com.atanor.vrecorder.domain.dao;

import com.atanor.vrecorder.domain.entity.Recording;

public class RecordingDao extends GenericDaoImpl<Recording, Long> {

	@Override
	public Class<Recording> getEntityClass() {
		return Recording.class;
	}

}
