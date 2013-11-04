package com.atanor.vrecorder.services;

import java.util.List;

import javax.inject.Inject;

import com.atanor.vrecorder.domain.dao.RecordingDao;
import com.atanor.vrecorder.domain.entity.Recording;

public class RecordingDataServiceImpl implements RecordingDataService {

	@Inject
	private RecordingDao recordingDao;

	@Override
	public List<Recording> getRecordings() {
		return recordingDao.findAll();
	}

}
