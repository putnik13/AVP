package com.atanor.smanager.domain.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.atanor.smanager.domain.entity.Display;


@Ignore
public class DisplayDaoTest extends BaseDaoTest<Display>{

	@Test
	public void testInsertRecord() throws Exception {
		Display display = new Display(10, 20);
		Assert.assertNotNull(dao.insert(display));
	}

	@Test
	public void testDeleteRecord() throws Exception {
		Display display = new Display(10, 20);

		Long id = dao.insert(display);
		display = dao.find(id);
		dao.delete(display);
	}

	@Test
	public void testSelect() throws Exception {
		Display display = new Display(10, 20);

		Long id = dao.insert(display);

		Display displayFromDB = dao.find(id);
		Assert.assertNotNull(displayFromDB);
		Assert.assertEquals(10, displayFromDB.getPhysicalHigh());
		Assert.assertEquals(20, displayFromDB.getPhysicalWidth());
	}

	@Test
	public void testUpdate() throws Exception {
		Display display = new Display(10, 20);

		Long id = dao.insert(display);

		display.setPhysicalHigh(100);

		dao.update(display);

		Display displayFromDB = dao.find(id);
		Assert.assertNotNull(displayFromDB);
		Assert.assertEquals(100, displayFromDB.getPhysicalHigh());
	}

	public void testGetAll() {
		Display display1 = new Display(5, 10);
		Display display2 = new Display(15, 20);
		Display display3 = new Display(25, 30);

		dao.insert(display1);
		dao.insert(display2);
		dao.insert(display3);

		List<Display> allDisplay = dao.findAll();
		Assert.assertEquals(3, allDisplay.size());

		Assert.assertEquals(5, allDisplay.get(0).getPhysicalHigh());
		Assert.assertEquals(10, allDisplay.get(0).getPhysicalWidth());

		Assert.assertEquals(15, allDisplay.get(1).getPhysicalHigh());
		Assert.assertEquals(20, allDisplay.get(1).getPhysicalWidth());

		Assert.assertEquals(25, allDisplay.get(2).getPhysicalHigh());
		Assert.assertEquals(30, allDisplay.get(2).getPhysicalWidth());
	}
}
