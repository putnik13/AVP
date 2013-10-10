package com.atanor.smanager.domain.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.atanor.smanager.domain.entity.Hardware;

@Ignore
public class HardwareDaoTest extends BaseDaoTest<Hardware> {

	@Test
	public void testInsertRecord() throws Exception {
		Hardware hardware = new Hardware("testModel");
		Assert.assertNotNull(dao.insert(hardware));
	}

	@Test
	public void testDeleteRecord() throws Exception {
		Hardware hardware = new Hardware("testModel");

		Long id = dao.insert(hardware);
		hardware = dao.find(id);
		dao.delete(hardware);
		Assert.assertNull(dao.find(id));
	}

	@Test
	public void testSelect() throws Exception {
		Hardware hardware = new Hardware("testModel");

		Long id = dao.insert(hardware);

		Hardware hardwareFromDB = dao.find(id);
		Assert.assertNotNull(hardwareFromDB);
		Assert.assertEquals("testModel", hardwareFromDB.getModelName());
	}

	@Test
	public void testUpdate() throws Exception {
		Hardware hardware = new Hardware("testModel");

		Long id = dao.insert(hardware);

		hardware.setModelName("newModel");

		dao.update(hardware);

		Hardware hardwareFromDB = dao.find(id);
		Assert.assertNotNull(hardwareFromDB);
		Assert.assertEquals("newModel", hardwareFromDB.getModelName());
	}

	public void testGetAll() {
		Hardware hardware1 = new Hardware("testModel0");
		Hardware hardware2 = new Hardware("testModel1");
		Hardware hardware3 = new Hardware("testModel2");

		dao.insert(hardware1);
		dao.insert(hardware2);
		dao.insert(hardware3);

		List<Hardware> allHardware = dao.findAll();
		Assert.assertEquals(3, allHardware.size());

		Assert.assertEquals("testModel0", allHardware.get(0).getModelName());
		Assert.assertEquals("testModel1", allHardware.get(1).getModelName());
		Assert.assertEquals("testModel2", allHardware.get(2).getModelName());
	}
}
