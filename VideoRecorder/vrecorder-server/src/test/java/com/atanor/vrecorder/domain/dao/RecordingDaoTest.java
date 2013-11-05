package com.atanor.vrecorder.domain.dao;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.atanor.vrecorder.domain.entity.Recording;

public class RecordingDaoTest extends BaseDaoTest<Recording> {

	@Test
	public void testInsertRecord() throws Exception {
		Recording recording = new Recording();

		Date startTime = new Date();
		Date endTime = new Date(startTime.getTime() + 100);
		recording.setName("Recording.avi");
		recording.setStartTime(startTime);
		recording.setEndTime(endTime);
		recording.setDuration("00:00:03");

		Assert.assertNotNull(dao.insert(recording));
	}

	@Test
	public void testDeleteRecord() throws Exception {
		Recording recording = new Recording();

		Long id = dao.insert(recording);
		recording = dao.find(id);
		dao.delete(recording);
	}

	@Test
	public void testSelect() throws Exception {
		Recording recording = new Recording();

		Date startTime = new Date();
		Date endTime = new Date(startTime.getTime() + 100);
		recording.setName("Recording.avi");
		recording.setStartTime(startTime);
		recording.setEndTime(endTime);
		recording.setDuration("00:00:03");

		Long id = dao.insert(recording);

		Recording recordingFromDB = dao.find(id);
		Assert.assertNotNull(recordingFromDB);
		Assert.assertEquals("Recording.avi", recordingFromDB.getName());
		Assert.assertEquals(startTime.getTime(), recordingFromDB.getStartTime().getTime());
		Assert.assertEquals(endTime.getTime(), recordingFromDB.getEndTime().getTime());
		Assert.assertEquals("00:00:03", recordingFromDB.getDuration());
	}

	@Test
	public void testUpdate() throws Exception {
		Recording recording = new Recording();
		recording.setName("Recording.avi");

		Long id = dao.insert(recording);

		Recording recordingFromDB = dao.find(id);
		Assert.assertNotNull(recordingFromDB);
		Assert.assertEquals("Recording.avi", recordingFromDB.getName());

		recordingFromDB.setName("Recording1.avi");
		dao.update(recordingFromDB);

		recordingFromDB = dao.find(id);
		Assert.assertNotNull(recordingFromDB);
		Assert.assertEquals("Recording1.avi", recordingFromDB.getName());
	}

	@Test
	public void testGetAll() {
		Recording recording1 = new Recording();
		Recording recording2 = new Recording();
		Recording recording3 = new Recording();

		dao.insert(recording1);
		dao.insert(recording2);
		dao.insert(recording3);

		List<Recording> allRecordings = dao.findAll();
		Assert.assertEquals(3, allRecordings.size());
	}
}
