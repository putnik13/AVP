package com.atanor.vrecorder.services;

import java.util.Date;
import java.util.List;

import com.atanor.vrecorder.domain.entity.Recording;

public interface RecordingDataService {

	List<Recording> getRecordings();

	Long createRecording(String fileName, Date startTime);

	void updateDuration(Long recordingId, Date endTime);
}
