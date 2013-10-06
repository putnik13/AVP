package com.atanor.smanager.domain.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.atanor.smanager.domain.entity.DisplayLayout;
import com.atanor.smanager.domain.entity.Preset;

@Ignore
public class PresetDaoTest extends BaseDaoTest<Preset> {

	@Test
	public void testInsertRecord() throws Exception {
		Preset preset = new Preset(DisplayLayout.ONExONE);
		Assert.assertNotNull(dao.insert(preset));
	}

	@Test
	public void testDeleteRecord() throws Exception {
		Preset preset = new Preset(DisplayLayout.ONExONE);

		Long id = dao.insert(preset);
		preset = dao.find(id);
		dao.delete(preset);
	}

	@Test
	public void testSelect() throws Exception {
		Preset preset = new Preset(DisplayLayout.ONExTWO);

		Long id = dao.insert(preset);

		Preset presetFromDB = dao.find(id);
		Assert.assertNotNull(presetFromDB);
		Assert.assertEquals(DisplayLayout.ONExTWO, presetFromDB.getLayout());
	}

	@Test
	public void testUpdate() throws Exception {
		Preset preset = new Preset(DisplayLayout.ONExONE);

		Long id = dao.insert(preset);

		preset.setLayout(DisplayLayout.ONExTWO);

		dao.update(preset);

		Preset presetFromDB = dao.find(id);
		Assert.assertNotNull(presetFromDB);
		Assert.assertEquals(DisplayLayout.ONExTWO, presetFromDB.getLayout());
	}

	public void testGetAll() {
		Preset preset1 = new Preset(DisplayLayout.ONExONE);
		Preset preset2 = new Preset(DisplayLayout.ONExTWO);
		Preset preset3 = new Preset(DisplayLayout.ONExTHREE);

		dao.insert(preset1);
		dao.insert(preset2);
		dao.insert(preset3);

		List<Preset> allDisplay = dao.findAll();
		Assert.assertEquals(3, allDisplay.size());

		Assert.assertEquals(DisplayLayout.ONExONE, allDisplay.get(0).getLayout());
		Assert.assertEquals(DisplayLayout.ONExTWO, allDisplay.get(1).getLayout());
		Assert.assertEquals(DisplayLayout.ONExTHREE, allDisplay.get(2).getLayout());
	}
	
}
