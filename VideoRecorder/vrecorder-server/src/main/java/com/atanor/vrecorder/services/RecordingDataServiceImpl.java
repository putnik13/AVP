package com.atanor.vrecorder.services;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.Validate;

import com.atanor.vrecorder.domain.dao.RecordingDao;
import com.atanor.vrecorder.domain.entity.Recording;
import com.atanor.vrecorder.util.FormatTime;
import com.atanor.vrecorder.util.ImageDecoder;
import com.google.inject.persist.Transactional;

public class RecordingDataServiceImpl implements RecordingDataService {

	@Inject
	private RecordingDao recordingDao;

	private final String outputFolder;

	@Inject
	public RecordingDataServiceImpl(@Named("output.folder") String outputFolder) {
		this.outputFolder = outputFolder;
	}

	@Override
	public List<Recording> getRecordings() {
		return recordingDao.findAll();
	}

	@Override
	public Long createRecording(final String fileName, final Date startTime) {
		Validate.notEmpty(fileName, "fileName can not be empty or null");
		Validate.notNull(startTime, "startTime can not be null");

		final Recording recording = new Recording(fileName);
		recording.setStartTime(startTime);
		return recordingDao.insert(recording);
	}

	@Override
	public void updateDuration(final Long recordingId, final Date endTime) {
		Validate.notNull(recordingId, "recordingId can not be null");
		Validate.notNull(endTime, "endTime can not be null");

		final Recording recording = recordingDao.find(recordingId);
		if (recording == null) {
			throw new IllegalStateException("Can not find recording with id=" + recordingId);
		}

		final String duration = FormatTime.format(endTime.getTime() - recording.getStartTime().getTime());
		recording.setDuration(duration);
		recording.setEndTime(endTime);
		recordingDao.update(recording);
	}

	@Override
	public void saveSnapshot(final Long recordingId, final String snapshotName) {
		Validate.notNull(recordingId, "recordingId can not be null");
		Validate.notEmpty(snapshotName, "snapshotName can not be empty or null");

		final Recording recording = recordingDao.find(recordingId);
		if (recording == null) {
			throw new IllegalStateException("Can not find recording with id=" + recordingId);
		}

		final File file = new File(snapshotName);
		if (!file.exists()) {
			System.out.println("Recording Snapshot is not exist!");
			return;
		}

		recording.setImageBlob(ImageDecoder.encodeImage(file));
		recordingDao.update(recording);
	}

	@Transactional
	@Override
	public void removeRecordings(final List<Recording> recordings) {
		for (final Recording recording : recordings) {
			final Recording recordingFromDb = recordingDao.find(recording.getId());
			recordingDao.delete(recordingFromDb);
			removeFromDisk(recording.getName());
		}
	}

	private void removeFromDisk(final String name) {
		final File file = new File(buildRecordingPath(name));
		if (file.exists()) {
			file.delete();
		}
	}

	private String buildRecordingPath(final String recordingName) {
		return outputFolder + "/" + recordingName;
	}

	@Override
	public List<Recording> getSynchronizationInfo() {
		final List<Recording> recordings = recordingDao.findAll();
		for (final Recording recording : recordings) {
			checkOutdated(recording);
		}
		return recordings;
	}

	private void checkOutdated(final Recording recording) {
		final File file = new File(buildRecordingPath(recording.getName()));
		if (!file.exists()) {
			recording.setOutdated(Boolean.TRUE);
		}
	}
}
