package com.atanor.smanager.domain.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.atanor.smanager.domain.entity.ActiveScreen;
import com.atanor.smanager.domain.entity.DisplayLayout;
import com.atanor.smanager.domain.entity.Preset;
import com.google.common.collect.Lists;

@Ignore
public class PresetDaoTest extends BaseDaoTest<Preset> {

	@Test
	public void testInsertRecord() throws Exception {
		Preset preset = new Preset(DisplayLayout.TWOxTHREE);

		ActiveScreen scr1 = new ActiveScreen(1, 2, 1, 2, "VIDEO");
		ActiveScreen scr2 = new ActiveScreen(3, 1, 1, 1, "CAMERA1");
		ActiveScreen scr3 = new ActiveScreen(3, 1, 2, 1, "CAMERA2");
		populatePreset(preset, Lists.newArrayList(scr1, scr2, scr3));
		preset.setActiveScreens(Lists.newArrayList(scr1, scr2, scr3));
		
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
		Preset preset = new Preset(DisplayLayout.TWOxTHREE);

		ActiveScreen scr1 = new ActiveScreen(1, 2, 1, 2, "VIDEO");
		ActiveScreen scr2 = new ActiveScreen(3, 1, 1, 1, "CAMERA1");
		ActiveScreen scr3 = new ActiveScreen(3, 1, 2, 1, "CAMERA2");
		populatePreset(preset, Lists.newArrayList(scr1, scr2, scr3));
		preset.setActiveScreens(Lists.newArrayList(scr1, scr2, scr3));

		Long id = dao.insert(preset);

		Preset presetFromDB = dao.find(id);
		Assert.assertNotNull(presetFromDB);

		Assert.assertEquals(DisplayLayout.TWOxTHREE, presetFromDB.getLayout());
		Assert.assertEquals(Integer.valueOf(6), presetFromDB.getLayout().getSectionQuantity());

		Assert.assertEquals(3, presetFromDB.getActiveScreens().size());

		Assert.assertEquals("VIDEO", presetFromDB.getActiveScreens().get(0).getSource());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getActiveScreens().get(0).getStartSectionHz());
		Assert.assertEquals(Integer.valueOf(2), presetFromDB.getActiveScreens().get(0).getWidthInSections());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getActiveScreens().get(0).getStartSectionVt());
		Assert.assertEquals(Integer.valueOf(2), presetFromDB.getActiveScreens().get(0).getHighInSections());

		Assert.assertEquals("CAMERA1", presetFromDB.getActiveScreens().get(1).getSource());
		Assert.assertEquals(Integer.valueOf(3), presetFromDB.getActiveScreens().get(1).getStartSectionHz());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getActiveScreens().get(1).getWidthInSections());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getActiveScreens().get(1).getStartSectionVt());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getActiveScreens().get(1).getHighInSections());

		Assert.assertEquals("CAMERA2", presetFromDB.getActiveScreens().get(2).getSource());
		Assert.assertEquals(Integer.valueOf(3), presetFromDB.getActiveScreens().get(2).getStartSectionHz());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getActiveScreens().get(2).getWidthInSections());
		Assert.assertEquals(Integer.valueOf(2), presetFromDB.getActiveScreens().get(2).getStartSectionVt());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getActiveScreens().get(2).getHighInSections());
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
		Assert.assertEquals(Integer.valueOf(2), presetFromDB.getLayout().getSectionQuantity());
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
		Assert.assertEquals(Integer.valueOf(1), allDisplay.get(0).getLayout().getSectionQuantity());

		Assert.assertEquals(DisplayLayout.ONExTWO, allDisplay.get(1).getLayout());
		Assert.assertEquals(Integer.valueOf(2), allDisplay.get(1).getLayout().getSectionQuantity());

		Assert.assertEquals(DisplayLayout.ONExTHREE, allDisplay.get(2).getLayout());
		Assert.assertEquals(Integer.valueOf(2), allDisplay.get(2).getLayout().getSectionQuantity());
	}
	
	private void populatePreset(Preset preset, List<ActiveScreen> screens){
		for (ActiveScreen screen : screens) {
			screen.setPreset(preset);
		}
	}
}
