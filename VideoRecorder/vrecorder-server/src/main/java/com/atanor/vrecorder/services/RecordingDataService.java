package com.atanor.vrecorder.services;

import java.util.List;

import com.atanor.vrecorder.domain.entity.Recording;

public interface RecordingDataService {

	List<Recording> getRecordings();
}
