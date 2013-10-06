package com.atanor.smanager.persistence.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.atanor.smanager.persistence.entity.Display;
import com.atanor.smanager.persistence.entity.Hardware;

@Ignore
public class HardwareDAOTest {

	HardwareDAO dao = new HardwareDAO();

	@Before
	public void setUp() throws Exception {
		dao.getEntityManager().getTransaction().begin();
	}

	@After
	public void tearDown() throws Exception {
		dao.getEntityManager().getTransaction().rollback();
	}

	@Test
	public void testInsertRecord() throws Exception {
		Hardware hardware = new Hardware("testModel", "testManufacture");
		Assert.assertNotNull(dao.insert(hardware));
	}

	@Test
	public void testInsertRecordWithDependencies() throws Exception {
		Hardware hardware = new Hardware("testModel", "testManufacture");
		hardware.setDisplay(new Display(10, 20));
		Assert.assertNotNull(dao.insert(hardware));
	}
	
	@Test
	public void testDeleteRecord() throws Exception {
		Hardware hardware = new Hardware("testModel", "testManufacture");

		Long id = dao.insert(hardware);
		hardware = dao.find(id);
		dao.delete(hardware);
		Assert.assertNull(dao.find(id));
	}
	
	@Test
	public void testSelect() throws Exception {
		Hardware hardware = new Hardware("testModel", "testManufacture");
		hardware.setDisplay(new Display(10, 20));
		
		Long id = dao.insert(hardware);

		Hardware hardwareFromDB = dao.find(id);
		Assert.assertNotNull(hardwareFromDB);
		Assert.assertEquals("testModel", hardwareFromDB.getModelName());
		Assert.assertEquals("testManufacture",
				hardwareFromDB.getManufactureName());
		Assert.assertEquals(10, hardwareFromDB.getDisplay().getPhysicalHigh());
		Assert.assertEquals(20, hardwareFromDB.getDisplay().getPhysicalWidth());
	}

	@Test
	public void testUpdate() throws Exception {
		Hardware hardware = new Hardware("testModel", "testManufacture");

		Long id = dao.insert(hardware);

		hardware.setModelName("newModel");

		dao.update(hardware);

		Hardware hardwareFromDB = dao.find(id);
		Assert.assertNotNull(hardwareFromDB);
		Assert.assertEquals("newModel", hardwareFromDB.getModelName());
	}

	public void testGetAll() {
		Hardware hardware1 = new Hardware("testModel0", "testManufacture0");
		Hardware hardware2 = new Hardware("testModel1", "testManufacture1");
		Hardware hardware3 = new Hardware("testModel2", "testManufacture2");

		// Сохраняем все авто
		dao.insert(hardware1);
		dao.insert(hardware2);
		dao.insert(hardware3);

		// Получаем все авто с БД
		List<Hardware> allHardware = dao.findAll();
		Assert.assertEquals(3, allHardware.size());

		Assert.assertEquals("testModel0", allHardware.get(0).getModelName());
		Assert.assertEquals("testManufacture0", allHardware.get(0)
				.getManufactureName());

		Assert.assertEquals("testModel1", allHardware.get(1).getModelName());
		Assert.assertEquals("testManufacture1", allHardware.get(1)
				.getManufactureName());

		Assert.assertEquals("testModel2", allHardware.get(2).getModelName());
		Assert.assertEquals("testManufacture2", allHardware.get(2)
				.getManufactureName());
	}
}
